@file:JvmName("DoOptionUtil")
package indi.yume.tools.avocado.funktional.doexp

import com.annimon.stream.Optional

/**
 * Created by yume on 16-12-17.
 */
fun <T1> do_(obsF: () -> Optional<T1>): DoOData1<T1> = DoOData1(obsF)

data class DoOData1<T1>(val obs1: () -> Optional<T1>) {
    fun <T2> do_(obsF: () -> Optional<T2>): DoOData2<T1, T2> = DoOData2(obs1, { t1 -> obsF() })

    fun <T2> do_1(obsF: (T1) -> Optional<T2>): DoOData2<T1, T2> = DoOData2(obs1, { obsF(it) })
}

data class DoOData2<T1, T2> (val obs1: () -> Optional<T1>,
                             val obs2: (T1) -> Optional<T2>) {

    fun <T3> do_(obsF: () -> Optional<T3>): DoOData3<T1, T2, T3> = do_12 { t1, t2 -> obsF() }

    fun <T3> do_1(obsF: (T1) -> Optional<T3>): DoOData3<T1, T2, T3> = do_12 { t1, t2 -> obsF(t1) }

    fun <T3> do_2(obsF: (T2) -> Optional<T3>): DoOData3<T1, T2, T3> = do_12 { t1, t2 -> obsF(t2) }

    fun <T3> do_12(obsF: (T1, T2) -> Optional<T3>): DoOData3<T1, T2, T3> =
            DoOData3(obs1, obs2, { t1, t2 -> obsF(t1, t2) })

    fun <T3> return_1(obsF: (T1) -> T3): Optional<T3> = obs1().map { t1 -> obsF(t1) }

    fun <T3> return_2(obsF: (T2) -> T3): Optional<T3> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t2) } }

    fun <T3> return_12(obsF: (T1, T2) -> T3): Optional<T3> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t1, t2) } }

    fun yield_(): Optional<T2> = obs1().flatMap { t1 -> obs2(t1) }

    fun <T3> yield_1(obsF: (T1) -> Optional<T3>): Optional<T3> =
            obs1().flatMap { t1 -> obsF(t1) }

    fun <T3> yield_2(obsF: (T2) -> Optional<T3>): Optional<T3> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obsF(t2) } }

    fun <T3> yield_12(obsF: (T1, T2) -> Optional<T3>): Optional<T3> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obsF(t1, t2) } }
}

data class DoOData3<T1, T2, T3> (
        val obs1: () -> Optional<T1>,
        val obs2: (T1) -> Optional<T2>,
        val obs3: (T1, T2) -> Optional<T3>) {

    fun <T4> do_(obsF: () -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF() })

    fun <T4> do_1(obsF: (T1) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t1) })

    fun <T4> do_2(obsF: (T2) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t2) })

    fun <T4> do_3(obsF: (T3) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t3) })

    fun <T4> do_12(obsF: (T1, T2) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t1, t2) })

    fun <T4> do_23(obsF: (T2, T3) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t2, t3) })

    fun <T4> do_13(obsF: (T1, T3) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            do_all({ t1, t2, t3 -> obsF(t1, t3) })

    fun <T4> do_all(obsF: (T1, T2, T3) -> Optional<T4>): DoOData4<T1, T2, T3, T4> =
            DoOData4(obs1, obs2, obs3, { t1, t2, t3 -> obsF(t1, t2, t3) })

    fun <T4> return_1(obsF: (T1) -> T4): Optional<T4> = obs1().map { t1 -> obsF(t1) }

    fun <T4> return_2(obsF: (T2) -> T4): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t2) } }

    fun <T4> return_3(obsF: (T3) -> T4): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obs3(t1, t2).map { t3 -> obsF(t3) } } }

    fun <T4> return_12(obsF: (T1, T2) -> T4): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t1, t2) } }

    fun <T4> return_23(obsF: (T2, T3) -> T4): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).map { t3 -> obsF(t2, t3) } } }

    fun <T4> return_13(obsF: (T1, T2) -> T4): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t1, t2) } }

    fun <T4> return_123(obsF: (T1, T2, T3) -> T4): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).map { t3 -> obsF(t1, t2, t3) } } }

    fun yield_(): Optional<T3> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 -> obs3(t1, t2) } }

    fun <T4> yield_1(obsF: (T1) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 -> obsF(t1) }

    fun <T4> yield_2(obsF: (T2) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obsF(t2) } }

    fun <T4> yield_3(obsF: (T3) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 -> obsF(t3) } } }

    fun <T4> yield_12(obsF: (T1, T2) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 -> obsF(t1, t2) } }

    fun <T4> yield_23(obsF: (T2, T3) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 -> obsF(t2, t3) } } }

    fun <T4> yield_13(obsF: (T1, T2) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obsF(t1, t2) } }

    fun <T4> yield_123(obsF: (T1, T2, T3) -> Optional<T4>): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 -> obsF(t1, t2, t3) } } }
}

data class DoOData4<T1, T2, T3, T4> (
        val obs1: () -> Optional<T1>,
        val obs2: (T1) -> Optional<T2>,
        val obs3: (T1, T2) -> Optional<T3>,
        val obs4: (T1, T2, T3) -> Optional<T4>) {

    fun <T5> return_1(obsF: (T1) -> T5): Optional<T5> =
            obs1().map { t1 -> obsF(t1) }

    fun <T5> return_2(obsF: (T2) -> T5): Optional<T5> =
            obs1().flatMap { t1 -> obs2(t1).map { t2 -> obsF(t2) } }

    fun <T5> return_3(obsF: (T3) -> T5): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).map { t3 -> obsF(t3) } } }

    fun <T5> return_4(obsF: (T4) -> T5): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 ->
                        obs4(t1, t2, t3).map { t4 -> obsF(t4) } } } }

    fun <T5> return_all(obsF: (T1, T2, T3, T4) -> T5): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 ->
                        obs4(t1, t2, t3).map { t4 -> obsF(t1, t2, t3, t4) } } } }

    fun yield_(): Optional<T4> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 ->
                        obs4(t1, t2, t3) } } }

    fun <T5> yield_1(obsF: (T1) -> Optional<T5>): Optional<T5> =
            obs1().flatMap { t1 -> obsF(t1) }

    fun <T5> yield_2(obsF: (T2) -> Optional<T5>): Optional<T5> =
            obs1().flatMap { t1 -> obs2(t1).flatMap { t2 -> obsF(t2) } }

    fun <T5> yield_3(obsF: (T3) -> Optional<T5>): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 -> obsF(t3) } } }

    fun <T5> yield_4(obsF: (T4) -> Optional<T5>): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 ->
                        obs4(t1, t2, t3).flatMap { t4 -> obsF(t4) } } } }

    fun <T5> yield_all(obsF: (T1, T2, T3, T4) -> Optional<T5>): Optional<T5> =
            obs1().flatMap { t1 ->
                obs2(t1).flatMap { t2 ->
                    obs3(t1, t2).flatMap { t3 ->
                        obs4(t1, t2, t3).flatMap { t4 ->
                            obsF(t1, t2, t3, t4) } } } }
}
