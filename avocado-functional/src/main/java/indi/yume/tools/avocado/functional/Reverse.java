package indi.yume.tools.avocado.functional;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Reverse {
    public static <P1, P2, R> BiFunction<P2, P1, R> reverse(BiFunction<P1, P2, R> f) {
        return (p2, p1) -> f.apply(p1, p2);
    }

    public static <P1, P2, P3, R> Function3<P3, P2, P1, R> reverse(Function3<P1, P2, P3, R> f) {
        return (p3, p2, p1) -> f.apply(p1, p2, p3);
    }

    public static <P1, P2, P3, P4, R> Function4<P4, P3, P2, P1, R> reverse(Function4<P1, P2, P3, P4, R> f) {
        return (p4, p3, p2, p1) -> f.apply(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, P5, R> Function5<P5, P4, P3, P2, P1, R> reverse(Function5<P1, P2, P3, P4, P5, R> f) {
        return (p5, p4, p3, p2, p1) -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function6<P6, P5, P4, P3, P2, P1, R> reverse(Function6<P1, P2, P3, P4, P5, P6, R> f) {
        return (p6, p5, p4, p3, p2, p1) -> f.apply(p1, p2, p3, p4, p5, p6);
    }
}
