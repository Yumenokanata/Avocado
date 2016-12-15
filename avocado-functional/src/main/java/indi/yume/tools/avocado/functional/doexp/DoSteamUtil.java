package indi.yume.tools.avocado.functional.doexp;

import com.annimon.stream.Stream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

/**
 * Created by yume on 16-12-14.
 */

public class DoSteamUtil {

    public static <T1> DoData1<T1> do_(Func0<Stream<T1>> obsF) {
        return new DoData1<>(obsF);
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData1<T1> {
        private final Func0<Stream<T1>> obs1;

        public <T2> DoData2<T1, T2> do_(Func0<Stream<T2>> obsF) {
            return new DoData2<>(obs1, t1 -> obsF.call());
        }

        public <T2> DoData2<T1, T2> do_1(Func1<T1, Stream<T2>> obsF) {
            return new DoData2<>(obs1, obsF::call);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData2<T1, T2> {
        private final Func0<Stream<T1>> obs1;
        private final Func1<T1, Stream<T2>> obs2;

        public <T3> DoData3<T1, T2, T3> do_(Func0<Stream<T3>> obsF) {
            return do_12((t1, t2) -> obsF.call());
        }

        public <T3> DoData3<T1, T2, T3> do_1(Func1<T1, Stream<T3>> obsF) {
            return do_12((t1, t2) -> obsF.call(t1));
        }

        public <T3> DoData3<T1, T2, T3> do_2(Func1<T2, Stream<T3>> obsF) {
            return do_12((t1, t2) -> obsF.call(t2));
        }

        public <T3> DoData3<T1, T2, T3> do_12(Func2<T1, T2, Stream<T3>> obsF) {
            return new DoData3<>(obs1, obs2, obsF::call);
        }

        public <T3> Stream<T3> return_1(Func1<T1, T3> obsF) {
            return obs1.call().map(t1 ->
                    obsF.call(t1)
            );
        }

        public <T3> Stream<T3> return_2(Func1<T2, T3> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t2))
            );
        }

        public <T3> Stream<T3> return_12(Func2<T1, T2, T3> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t1, t2))
            );
        }

        public Stream<T2> yield() {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1)
            );
        }

        public <T3> Stream<T3> yield_1(Func1<T1, Stream<T3>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obsF.call(t1)
            );
        }

        public <T3> Stream<T3> yield_2(Func1<T2, Stream<T3>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t2))
            );
        }

        public <T3> Stream<T3> yield_12(Func2<T1, T2, Stream<T3>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t1, t2))
            );
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData3<T1, T2, T3> {
        private final Func0<Stream<T1>> obs1;
        private final Func1<T1, Stream<T2>> obs2;
        private final Func2<T1, T2, Stream<T3>> obs3;

        public <T4> DoData4<T1, T2, T3, T4> do_(Func0<Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call());
        }

        public <T4> DoData4<T1, T2, T3, T4> do_1(Func1<T1, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t1));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_2(Func1<T2, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t2));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_3(Func1<T3, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_12(Func2<T1, T2, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t1, t2));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_23(Func2<T2, T3, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t2, t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_13(Func2<T1, T3, Stream<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.call(t1, t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_all(Func3<T1, T2, T3, Stream<T4>> obsF) {
            return new DoData4<>(obs1, obs2, obs3, obsF::call);
        }

        public <T4> Stream<T4> return_1(Func1<T1, T4> obsF) {
            return obs1.call().map(t1 ->
                    obsF.call(t1)
            );
        }

        public <T4> Stream<T4> return_2(Func1<T2, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t2))
            );
        }

        public <T4> Stream<T4> return_3(Func1<T3, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).map(t3 ->
                                    obsF.call(t3)))
            );
        }

        public <T4> Stream<T4> return_12(Func2<T1, T2, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t1, t2))
            );
        }

        public <T4> Stream<T4> return_23(Func2<T2, T3, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).map(t3 ->
                                    obsF.call(t2, t3)))
            );
        }

        public <T4> Stream<T4> return_13(Func2<T1, T2, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t1, t2))
            );
        }

        public <T4> Stream<T4> return_123(Func3<T1, T2, T3, T4> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).map(t3 ->
                                    obsF.call(t1, t2, t3)))
            );
        }

        public Stream<T3> yield() {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2)
                    )
            );
        }

        public <T4> Stream<T4> yield_1(Func1<T1, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obsF.call(t1)
            );
        }

        public <T4> Stream<T4> yield_2(Func1<T2, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t2))
            );
        }

        public <T4> Stream<T4> yield_3(Func1<T3, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obsF.call(t3)))
            );
        }

        public <T4> Stream<T4> yield_12(Func2<T1, T2, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t1, t2))
            );
        }

        public <T4> Stream<T4> yield_23(Func2<T2, T3, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obsF.call(t2, t3)))
            );
        }

        public <T4> Stream<T4> yield_13(Func2<T1, T2, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t1, t2))
            );
        }

        public <T4> Stream<T4> yield_123(Func3<T1, T2, T3, Stream<T4>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obsF.call(t1, t2, t3)))
            );
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData4<T1, T2, T3, T4> {
        private final Func0<Stream<T1>> obs1;
        private final Func1<T1, Stream<T2>> obs2;
        private final Func2<T1, T2, Stream<T3>> obs3;
        private final Func3<T1, T2, T3, Stream<T4>> obs4;

        public <T5> Stream<T5> return_1(Func1<T1, T5> obsF) {
            return obs1.call().map(t1 ->
                    obsF.call(t1)
            );
        }

        public <T5> Stream<T5> return_2(Func1<T2, T5> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).map(t2 ->
                            obsF.call(t2))
            );
        }

        public <T5> Stream<T5> return_3(Func1<T3, T5> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).map(t3 ->
                                    obsF.call(t3)))
            );
        }

        public <T5> Stream<T5> return_4(Func1<T4, T5> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obs4.call(t1, t2, t3).map(t4 ->
                                            obsF.call(t4))))
            );
        }

        public <T5> Stream<T5> return_all(Func4<T1, T2, T3, T4, T5> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obs4.call(t1, t2, t3).map(t4 ->
                                            obsF.call(t1, t2, t3, t4))))
            );
        }

        public Stream<T4> yield() {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obs4.call(t1, t2, t3)))
            );
        }

        public <T5> Stream<T5> yield_1(Func1<T1, Stream<T5>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obsF.call(t1)
            );
        }

        public <T5> Stream<T5> yield_2(Func1<T2, Stream<T5>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obsF.call(t2))
            );
        }

        public <T5> Stream<T5> yield_3(Func1<T3, Stream<T5>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obsF.call(t3)))
            );
        }

        public <T5> Stream<T5> yield_4(Func1<T4, Stream<T5>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obs4.call(t1, t2, t3).flatMap(t4 ->
                                            obsF.call(t4))))
            );
        }

        public <T5> Stream<T5> yield_all(Func4<T1, T2, T3, T4, Stream<T5>> obsF) {
            return obs1.call().flatMap(t1 ->
                    obs2.call(t1).flatMap(t2 ->
                            obs3.call(t1, t2).flatMap(t3 ->
                                    obs4.call(t1, t2, t3).flatMap(t4 ->
                                            obsF.call(t1, t2, t3, t4))))
            );
        }
    }
}
