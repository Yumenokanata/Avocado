package indi.yume.tools.sample

import org.funktionale.composition.compose
import org.funktionale.composition.forwardCompose
import org.funktionale.currying.curried
import org.funktionale.memoization.memoize
import org.funktionale.partials.invoke
import org.junit.Test

/**
 * Created by yume on 16-5-16.
 */
class PLazyListTest {
    @Test
    fun testList() {
        var list = PLazyList<Int>()

        for(i in 1..10)
            list = list.cons(i);

//        while(!list.isEmpty()) {
//            println("head=> ${list.head()}")
//            list = list.tail()
//        }
        val simFunClazz = TestClass::sigmaSum
        val fun2Clazz:(IntRange) -> ((Int) -> Int) -> Int = TestClass::sigmaSum.curried().invoke(TestClass())

        val simFun = ::sigmaSumFun
        val fun2:(IntRange) -> ((Int) -> Int) -> Int = ::sigmaSumFun.curried()

        val add5 = { num: Int -> num + 5 }
        val multiplyBy2 = { i: Int -> i * 2 }

        val forwardComposeFun = add5 forwardCompose multiplyBy2
        assert(forwardComposeFun(1) == (1 + 5) * 2)

        val composeFun = add5 compose multiplyBy2
        assert(composeFun(1) == (1 * 2) + 5)

        val prefixAndPostfix: (String, String, String) -> String = { prefix:String, x:String, postfix:String -> "${prefix}${x}${postfix}"}

        //passing just the third parameter will return a new function (String, String) -> String
        val prefixAndBang: (String, String) -> String = prefixAndPostfix(p1 = "!")

        //passing just the first parameter will return a new function (String) -> String
        val hello: (String) -> String = prefixAndBang(p1 = "Hello, ")

        println(list.take(3).joinToString())
    }

    @Test
    fun mainTest() {

        var fib: (Long) -> Long = { it } //Declared ahead to be used inside recursively
        fib = { n: Long ->
            if (n < 2) n else fib(n - 1) + fib(n - 2)
        }
        var m: (Long) -> Long = { it }
        m = { n: Long ->
            if (n < 2) n else m(n - 1) + m(n - 2)
        }.memoize()

        println(timeElapsed { fib(40) }) //2788

        println(timeElapsed { m(40) })  //4
    }

    fun timeElapsed(body: () -> Unit): Long {
        val start = System.currentTimeMillis()
        body()
        val end = System.currentTimeMillis()
        return end - start
    }
}

fun sigmaSumFun(range: IntRange, f: (Int) -> Int): Int {
    var result = 0
    for(i in range) result += i
    return result
}

class TestClass {
    fun sigmaSum(range: IntRange, f: (Int) -> Int): Int {
        var result = 0
        for(i in range) result += i
        return result
    }
}