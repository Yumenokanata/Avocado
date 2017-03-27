package indi.yume.tools.avocado

import org.junit.Test

import indi.yume.tools.avocado.funktional.doexp.rx.*
import io.reactivex.Flowable

/**
 * Created by yume on 16-12-17.
 */
class MyClassTest {

    @Test
    fun testDoUtil() {
        do_({ Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9) })
                .do_({ Flowable.just(2, 3) })
                .do_12({ t1, t2 -> Flowable.just("" + t1 + t2) })
                .yield_()
                .subscribe({ System.out.println() })
    }
}