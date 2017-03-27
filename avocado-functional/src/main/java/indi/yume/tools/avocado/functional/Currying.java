package indi.yume.tools.avocado.functional;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import lombok.experimental.UtilityClass;
import io.reactivex.functions.Function;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Currying {
    public static <P1, P2, R> Function<P1, Function<P2, R>> curried(BiFunction<P1, P2, R> f) {
        return p1 -> p2 -> f.apply(p1, p2);
    }

    public static <P1, P2, P3, R> Function<P1, Function<P2, Function<P3, R>>> curried(Function3<P1, P2, P3, R> f) {
        return p1 -> p2 -> p3 -> f.apply(p1, p2, p3);
    }

    public static <P1, P2, P3, P4, R> Function<P1, Function<P2, Function<P3, Function<P4, R>>>> curried(Function4<P1, P2, P3, P4, R> f) {
        return p1 -> p2 -> p3 -> p4 -> f.apply(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, P5, R> Function<P1, Function<P2, Function<P3, Function<P4, Function<P5, R>>>>> curried(Function5<P1, P2, P3, P4, P5, R> f) {
        return p1 -> p2 -> p3 -> p4 -> p5 -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function<P1, Function<P2, Function<P3, Function<P4, Function<P5, Function<P6, R>>>>>> curried(Function6<P1, P2, P3, P4, P5, P6, R> f) {
        return p1 -> p2 -> p3 -> p4 -> p5 -> p6 -> f.apply(p1, p2, p3, p4, p5, p6);
    }
}
