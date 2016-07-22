package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Utils {
    public static <T> Func1<T, T> identity() {
        return t -> t;
    }

    public static <T> Func0<T> constant0(final T t) {
        return () -> t;
    }

    public static <P1, T> Func1<P1, T> constant1(final T t) {
        return p1 -> t;
    }

    public static <P1, P2, T> Func2<P1, P2, T> constant2(final T t) {
        return (p1, p2) -> t;
    }

    public static <P1, P2, P3, T> Func3<P1, P2, P3, T> constant3(final T t) {
        return (p1, p2, p3) -> t;
    }

    public static <P1, P2, P3, P4, T> Func4<P1, P2, P3, P4, T> constant4(final T t) {
        return (p1, p2, p3, p4) -> t;
    }

    public static <P1, P2, P3, P4, P5, T> Func5<P1, P2, P3, P4, P5, T> constant5(final T t) {
        return (p1, p2, p3, p4, p5) -> t;
    }

    public static <P1, P2, P3, P4, P5, P6, T> Func6<P1, P2, P3, P4, P5, P6, T> constant6(final T t) {
        return (p1, p2, p3, p4, p5, p6) -> t;
    }
}
