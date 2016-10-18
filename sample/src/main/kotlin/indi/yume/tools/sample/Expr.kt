package indi.yume.tools.sample

/**
 * Created by yume on 16-5-17.
 */
sealed class Expr<T> {
    abstract fun testFun()

    class Left : Expr<Nothing>() {
        override fun testFun() {
            throw UnsupportedOperationException()
        }
    }
}


