package indi.yume.tools.avocado.functional;

import com.annimon.stream.function.Supplier;

import indi.yume.tools.avocado.util.LogUtil;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.IntFunction;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class FuncUtils {
    public static <T> Function<T, T> identity() {
        return t -> t;
    }

    public static <T> Supplier<T> const0(final T t) {
        return () -> t;
    }

    public static <P1, T> Function<P1, T> const1(final T t) {
        return p1 -> t;
    }

    public static <P1, P2, T> BiFunction<P1, P2, T> const2(final T t) {
        return (p1, p2) -> t;
    }

    public static <P1, P2, P3, T> Function3<P1, P2, P3, T> const3(final T t) {
        return (p1, p2, p3) -> t;
    }

    public static <P1, P2, P3, P4, T> Function4<P1, P2, P3, P4, T> const4(final T t) {
        return (p1, p2, p3, p4) -> t;
    }

    public static <P1, P2, P3, P4, P5, T> Function5<P1, P2, P3, P4, P5, T> const5(final T t) {
        return (p1, p2, p3, p4, p5) -> t;
    }

    public static <P1, P2, P3, P4, P5, P6, T> Function6<P1, P2, P3, P4, P5, P6, T> const6(final T t) {
        return (p1, p2, p3, p4, p5, p6) -> t;
    }

    public static Supplier<Void> voidFun() {
        return () -> null;
    }

    public static Supplier<Void> voidFun0(final Action act) {
        return () -> {
            try {
                act.run();
            } catch (Exception e) {
                LogUtil.e(e);
            }
            return null;
        };
    }

    public static <P1> Function<P1, Void> voidFun1(final Consumer<P1> act) {
        return p1 -> {
            act.accept(p1);
            return null;
        };
    }

    public static <P1, P2> BiFunction<P1, P2, Void> voidFun2(final BiConsumer<P1, P2> act) {
        return (p1, p2) -> {
            act.accept(p1, p2);
            return null;
        };
    }

    public static void runUnsafe(Action fun) {
        try {
            fun.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2> void runUnsafe(BiConsumer<T1, T2> fun, T1 t1, T2 t2) {
        try {
            fun.accept(t1, t2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2, B> B runUnsafe(BiFunction<T1, T2, B> fun, T1 t1, T2 t2) {
        try {
            return fun.apply(t1, t2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2> boolean runUnsafe(BiPredicate<T1, T2> fun, T1 t1, T2 t2) {
        try {
            return fun.test(t1, t2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean runUnsafe(BooleanSupplier fun) {
        try {
            return fun.getAsBoolean();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void runUnsafe(Cancellable fun) {
        try {
            fun.cancel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void runUnsafe(Consumer<T> fun, T t) {
        try {
            fun.accept(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, R> R runUnsafe(Function<T, R> fun, T t) {
        try {
            return fun.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2, T3, R> R runUnsafe(Function3<T1, T2, T3, R> fun, T1 t1, T2 t2, T3 t3) {
        try {
            return fun.apply(t1, t2, t3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2, T3, T4, R> R runUnsafe(Function4<T1, T2, T3, T4, R> fun, T1 t1, T2 t2, T3 t3, T4 t4) {
        try {
            return fun.apply(t1, t2, t3, t4);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T1, T2, T3, T4, T5, R> R runUnsafe(Function5<T1, T2, T3, T4, T5, R> fun,
                                                      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        try {
            return fun.apply(t1, t2, t3, t4, t5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <R> R runUnsafe(IntFunction<R> fun, int t) {
        try {
            return fun.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void runUnsafe(LongConsumer fun, long t) {
        try {
            fun.accept(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> boolean runUnsafe(Predicate<T> fun, T t) {
        try {
            return fun.test(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
