package indi.yume.tools.sample

/**
 * Created by yume on 16-5-16.
 */
class PLazyList<T> private constructor(val list: () -> Pair<T, Any>?) {
    constructor(): this({ null })

    fun cons(head: T): PLazyList<T> = PLazyList({ Pair(head, list)})

    fun head(): T? = list()?.first

    fun tail(): PLazyList<T> {
        val data = list()

        return if(data == null) {
            PLazyList()
        }else {
            PLazyList(data.second as () -> Pair<T, Any>?)
        }
    }

    fun isEmpty(): Boolean = list() == null

    fun <K> fold(n: Int, acc: K, f: (K, T) -> K): K =
        if(n == 0 || isEmpty()) {
            acc
        } else {
            tail().fold(n - 1, f(acc, head()!!), f)
        }

    fun <K> foldAll(acc: K, f: (K, T) -> K): K =
            if(isEmpty()) {
                acc
            } else {
                tail().foldAll(f(acc, head()!!), f)
            }

    fun take(n: Int): List<T> =
            fold(n, emptyList<T>()) { list, item -> list + item }

    fun takeAll(): List<T> =
            foldAll(emptyList()) { list, item -> list + item }

    fun toList(): List<T> = takeAll()
}