package indi.yume.tools.avocado.functional.doexp;

import com.annimon.stream.Optional;
import com.annimon.stream.function.BiFunction;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;

import indi.yume.tools.avocado.model.Function3;
import indi.yume.tools.avocado.model.Function4;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yume on 16-12-15.
 */

public class DoOption {

    public static <T1> DoData1<T1> do_(Supplier<Optional<T1>> obsF) {
        return new DoData1<>(obsF);
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData1<T1> {
        private final Supplier<Optional<T1>> obs1;

        public <T2> DoData2<T1, T2> do_(Supplier<Optional<T2>> obsF) {
            return new DoData2<>(obs1, t1 -> obsF.get());
        }

        public <T2> DoData2<T1, T2> do_1(Function<T1, Optional<T2>> obsF) {
            return new DoData2<>(obs1, obsF);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData2<T1, T2> {
        private final Supplier<Optional<T1>> obs1;
        private final Function<T1, Optional<T2>> obs2;

        public <T3> DoData3<T1, T2, T3> do_(Supplier<Optional<T3>> obsF) {
            return do_12((t1, t2) -> obsF.get());
        }

        public <T3> DoData3<T1, T2, T3> do_1(Function<T1, Optional<T3>> obsF) {
            return do_12((t1, t2) -> obsF.apply(t1));
        }

        public <T3> DoData3<T1, T2, T3> do_2(Function<T2, Optional<T3>> obsF) {
            return do_12((t1, t2) -> obsF.apply(t2));
        }

        public <T3> DoData3<T1, T2, T3> do_12(BiFunction<T1, T2, Optional<T3>> obsF) {
            return new DoData3<>(obs1, obs2, obsF);
        }

        public <T3> Optional<T3> return_1(Function<T1, T3> obsF) {
            return obs1.get().map(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T3> Optional<T3> return_2(Function<T2, T3> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T3> Optional<T3> return_12(BiFunction<T1, T2, T3> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t1, t2))
            );
        }

        public Optional<T2> yield() {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1)
            );
        }

        public <T3> Optional<T3> yield_1(Function<T1, Optional<T3>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T3> Optional<T3> yield_2(Function<T2, Optional<T3>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T3> Optional<T3> yield_12(BiFunction<T1, T2, Optional<T3>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t1, t2))
            );
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData3<T1, T2, T3> {
        private final Supplier<Optional<T1>> obs1;
        private final Function<T1, Optional<T2>> obs2;
        private final BiFunction<T1, T2, Optional<T3>> obs3;

        public <T4> DoData4<T1, T2, T3, T4> do_(Supplier<Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.get());
        }

        public <T4> DoData4<T1, T2, T3, T4> do_1(Function<T1, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t1));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_2(Function<T2, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t2));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_3(Function<T3, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_12(BiFunction<T1, T2, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t1, t2));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_23(BiFunction<T2, T3, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t2, t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_13(BiFunction<T1, T3, Optional<T4>> obsF) {
            return do_all((t1, t2, t3) -> obsF.apply(t1, t3));
        }

        public <T4> DoData4<T1, T2, T3, T4> do_all(Function3<T1, T2, T3, Optional<T4>> obsF) {
            return new DoData4<>(obs1, obs2, obs3, obsF);
        }

        public <T4> Optional<T4> return_1(Function<T1, T4> obsF) {
            return obs1.get().map(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T4> Optional<T4> return_2(Function<T2, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T4> Optional<T4> return_3(Function<T3, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).map(t3 ->
                                    obsF.apply(t3)))
            );
        }

        public <T4> Optional<T4> return_12(BiFunction<T1, T2, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t1, t2))
            );
        }

        public <T4> Optional<T4> return_23(BiFunction<T2, T3, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).map(t3 ->
                                    obsF.apply(t2, t3)))
            );
        }

        public <T4> Optional<T4> return_13(BiFunction<T1, T2, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t1, t2))
            );
        }

        public <T4> Optional<T4> return_123(Function3<T1, T2, T3, T4> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).map(t3 ->
                                    obsF.apply(t1, t2, t3)))
            );
        }

        public Optional<T3> yield() {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2)
                    )
            );
        }

        public <T4> Optional<T4> yield_1(Function<T1, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T4> Optional<T4> yield_2(Function<T2, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T4> Optional<T4> yield_3(Function<T3, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obsF.apply(t3)))
            );
        }

        public <T4> Optional<T4> yield_12(BiFunction<T1, T2, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t1, t2))
            );
        }

        public <T4> Optional<T4> yield_23(BiFunction<T2, T3, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obsF.apply(t2, t3)))
            );
        }

        public <T4> Optional<T4> yield_13(BiFunction<T1, T2, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t1, t2))
            );
        }

        public <T4> Optional<T4> yield_123(Function3<T1, T2, T3, Optional<T4>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obsF.apply(t1, t2, t3)))
            );
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DoData4<T1, T2, T3, T4> {
        private final Supplier<Optional<T1>> obs1;
        private final Function<T1, Optional<T2>> obs2;
        private final BiFunction<T1, T2, Optional<T3>> obs3;
        private final Function3<T1, T2, T3, Optional<T4>> obs4;

        public <T5> Optional<T5> return_1(Function<T1, T5> obsF) {
            return obs1.get().map(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T5> Optional<T5> return_2(Function<T2, T5> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).map(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T5> Optional<T5> return_3(Function<T3, T5> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).map(t3 ->
                                    obsF.apply(t3)))
            );
        }

        public <T5> Optional<T5> return_4(Function<T4, T5> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obs4.apply(t1, t2, t3).map(t4 ->
                                            obsF.apply(t4))))
            );
        }

        public <T5> Optional<T5> return_all(Function4<T1, T2, T3, T4, T5> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obs4.apply(t1, t2, t3).map(t4 ->
                                            obsF.apply(t1, t2, t3, t4))))
            );
        }

        public Optional<T4> yield() {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obs4.apply(t1, t2, t3)))
            );
        }

        public <T5> Optional<T5> yield_1(Function<T1, Optional<T5>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obsF.apply(t1)
            );
        }

        public <T5> Optional<T5> yield_2(Function<T2, Optional<T5>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obsF.apply(t2))
            );
        }

        public <T5> Optional<T5> yield_3(Function<T3, Optional<T5>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obsF.apply(t3)))
            );
        }

        public <T5> Optional<T5> yield_4(Function<T4, Optional<T5>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obs4.apply(t1, t2, t3).flatMap(t4 ->
                                            obsF.apply(t4))))
            );
        }

        public <T5> Optional<T5> yield_all(Function4<T1, T2, T3, T4, Optional<T5>> obsF) {
            return obs1.get().flatMap(t1 ->
                    obs2.apply(t1).flatMap(t2 ->
                            obs3.apply(t1, t2).flatMap(t3 ->
                                    obs4.apply(t1, t2, t3).flatMap(t4 ->
                                            obsF.apply(t1, t2, t3, t4))))
            );
        }
    }
}
