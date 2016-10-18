package indi.yume.tools.sample

import org.funktionale.collections.tail
import org.junit.Test

/**
 * Created by yume on 16-6-7.
 */
public class Haskell {
    @Test
    fun testRPN() {
        val result = solveRPN("12 23 + 3 * 3 /")
        println("Result=$result")
        assert(result == 35.toDouble())
    }
}

fun solveRPN(expr: String): Double =
    expr.words().fold(emptyList<Double>(), ::foldingFun).first()

private fun foldingFun(data: List<Double>, expr: String): List<Double> =
    when (expr) {
        "+" -> listOf(data.first() + data.tail().first()) + data.tail().tail()
        "-" -> listOf(data.first() - data.tail().first()) + data.tail().tail()
        "*" -> listOf(data.first() * data.tail().first()) + data.tail().tail()
        "/" -> listOf(data.tail().first() / data.first()) + data.tail().tail()
        else -> listOf(expr.toDouble()) + data
    }

fun String.words(): List<String> = split(" ")