package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Utils {
    public static <T> Func1<T, T> identity() {
        return t -> t;
    }

    public static <P1, T> Func1<P1, T> constant1(final T t) {
        return p1 -> t;
    }

    public static <T> Func0<T> constant0(final T t) {
        return () -> t;
    }
}
