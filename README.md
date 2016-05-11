[![](https://jitpack.io/v/Yumenokanata/Avocado.svg)](https://jitpack.io/#Yumenokanata/Avocado)

# Avocado
一个工具类的集合

1. 区域树容器：区域树的实现以及扩展的容器  
2. DayDate：日期的封装，以及相应的迭代容器  
3. 元组  
4. JsonUtil：用于生成Json字符串的工具类，相比原生JSONObject快一倍。  
5. 陆续扩展中...  

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
    compile 'com.github.Yumenokanata:Avocado:x.y.z'
}
```

## 

## 一、DayDate

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

## 二、区域树容器

目前是借用的(kevinjdolan/intervaltree)[https://github.com/kevinjdolan/intervaltree]的实现，并进行了一点扩展

1、 DayDateIntervalTreeMultimap： DayDate区域容器

2、 TypeIntervalTree：泛型扩展，原始IntervalTree只支持long类型，此扩展可以支持所有实现Comparable接口的类型

## 三、元组

Tuple元组的一个简单标准，提供了2~8个属性数量

实现了：
1. equals()、hashCode()以及toString()
2. of()静态工厂方法
3. 全参数和无参数构造

## 四、JsonUtil

用于生成Json的工具类，特定情况下相比Gson的Json生成速度快20%,相比原生JSONObject快一倍。  
并对内存进行了优化，减少GC。  
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
