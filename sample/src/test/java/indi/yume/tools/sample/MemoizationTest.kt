package indi.yume.tools.sample

import org.funktionale.memoization.memoize
import org.junit.Test

/**
 * Created by yume on 16-5-17.
 */
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