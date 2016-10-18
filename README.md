[![](https://jitpack.io/v/Yumenokanata/Avocado.svg)](https://jitpack.io/#Yumenokanata/Avocado)

# Avocado
一个工具类的集合

1. __大量函数式工具的实现 (科里化Currying、部份施用Partial application、记忆模式、模式匹配...)__
2. 区域树容器：区域树的实现以及扩展的容器  
3. DayDate：日期的封装，以及相应的迭代容器  
4. 元组  
5. JsonUtil：用于生成Json字符串的工具类，相比原生JSONObject快一倍。  
6. 陆续扩展中...  

## 添加到Android studio
Step1: 在根build.gradle中添加仓库：
```groovy
allprojects {
	repositories {
        jcenter()
		maven { url "https://jitpack.io" }
	}
}
```

Step2: 在工程中添加依赖：
```groovy
dependencies {
    compile 'com.github.Yumenokanata.Avocado:avocado:x.y.z' // 基本工具，包括反射工具、基本扩展Model等
    
    compile 'com.github.Yumenokanata.Avocado:avocado-collect:x.y.z' // 区域树容器
    
    compile 'com.github.Yumenokanata.Avocado:avocado-functional:x.y.z' // 函数范式工具的实现
}
```

## 

## 一、函数范式模式的实现

1、 科里化 (Currying):  
```java
Func1<Integer, Func1<Integer, String>> curry = curried((i1, i2) -> i1 + "+" + i2)
```

2、 部份施用 (Partial application):
```java
Func2<Integer, Integer, String> partial = Partial.part2((i1, s2, i3) -> i1 + "+" + s2 + "+" + i3, "2");
```

3、 记忆模式:
[Sample](https://github.com/Yumenokanata/Avocado/blob/master/avocado-functional/src/test/java/indi/yume/tools/avocado/functional/MemoziedTest.java)

```java
Func1<Integer, String> mFun = Memoization.memoize(fun);
```

4、 if表达式DSL (相比命令式的if语句，函数式中控制语句都是有返回值的):
```java
Integer d = if_(1 < 0)
        .do_(() -> 1)
        .else_if(() -> 5 < 9)
        .do_(() -> 2)
        .else_(() -> 4)
        .get();
```

5、 when模式匹配语句DSL。   
可实现类似函数式范式的模式匹配功能, 包括类型判断、区域判断等，并可以简单扩展DSL语句，扩增方法参照[Matcher](https://github.com/Yumenokanata/Avocado/blob/master/avocado-functional/src/main/java/indi/yume/tools/avocado/functional/matching/Matcher.java)。  

```java
Integer d = CaseUtil.when(3f)
        .case_pd(i -> i == 1, 4)
        .case_(eq(2f), i -> 5)
        .case_(is(Float.class), const1(8))
        .case_(in(2f, 6f), 7)
        .el_get(6);
```

6、 match对列表结构的特定模式匹配DSL。     
可实现类似函数式的列表解析操作

```java
String s = match(list)
        .empty(() -> "[]")
        .matchF("1", xs -> "_1")
        .matchL("3", xs -> "_3")
        .matchF((x, xs) -> x + " : " + xs)
        .matchL((xs, x) -> xs + " : " + x)
        .el_get(l -> l.toString());
```


## 二、DayDate

只有年月日的日期容器，实现了Comparable<DayDate>、Parcelable、Serializable接口。

1、 addYear()、addMonth()、addDay()偏移当前日期（传入负数则向前偏移）

2、 getMaxDay()获取当前月份的最大天数

3、 getWhatDayIsIt()当前星期数

4、 ImmutableDayDate 不可变的DayDate

5、 DayDateIterable：DayDate的迭代器，提供对于一个日期区间的迭代
```java
Range<DayDate> dayRange = Range.closed(new DayDate(2015, 9, 3), new DayDate(2016, 9, 3));
for(DayDate dayDate : DayDateIterable.of(dayRange))
```

6、 DayDateIntervalTreeMultimap： DayDate区域容器，保存日期区间与相应数据的对应关系（区间可重复）
```java
DayDateIntervalTreeMultimap<String> map = new DayDateIntervalTreeMultimap<>();
map.addInterval(new DayDate(2015, 9, 3), new DayDate(2015, 10, 3), "data1");
map.addInterval(new DayDate(2015, 9, 16), new DayDate(2015, 10, 12), "data2");

List<String> result = map.get(new DayDate(2015, 9, 15));
```

## 三、区域树容器

目前是借用的[kevinjdolan/intervaltree](https://github.com/kevinjdolan/intervaltree)的实现，并进行了一点扩展

1、 DayDateIntervalTreeMultimap： DayDate区域容器

2、 TypeIntervalTree：泛型扩展，原始IntervalTree只支持long类型，此扩展可以支持所有实现Comparable接口的类型

## 四、元组

Tuple元组的一个简单标准，提供了2~8个属性数量

实现了：  
1. equals()、hashCode()以及toString()  
2. of()静态工厂方法  
3. 全参数和无参数构造  

## 五、JsonUtil

用于生成Json的工具类，相比原生JSONObject快一倍。  
并对内存进行了优化，减少对象创建，以减少GC。  
注意，请采用链式调用，否则可能产生无法预料的结果。  

eg:
```java
   json = JsonUtil.start()
             .add("key1", true)
             .add("key2", 1.0)
             .add("key3", -1200l)
             .add("key4", "object test")
             .addArray("key6", new Action1<JsonUtil.JsonArrayBuilder>() {
                                         @Override
                                         public void call(JsonUtil.JsonArrayBuilder value) {
                                             value.add(false)
                                                     .add(9.6)
                                                     .add(-199l)
                                                     .add("array object test")
                                                     .add(new TestModel())
                                                     .addJson(new Action1<JsonUtil.JsonBuilder>() {
                                                             @Override
                                                             public void call(JsonUtil.JsonBuilder value) {
                                                                 value.add("sub key", "sub object");
                                                             }
                                                         });
                                         }
                    })
             .add("key6", 999)
             .add("key7", new TestModel())
             .end();
```


###License
<pre>
Copyright 2015 Yumenokanata

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
