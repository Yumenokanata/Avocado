package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Reverse {
    public static <P1, P2, R> Func2<P2, P1, R> reverse(Func2<P1, P2, R> f) {
        return (p2, p1) -> f.call(p1, p2);
    }

    public static <P1, P2, P3, R> Func3<P3, P2, P1, R> reverse(Func3<P1, P2, P3, R> f) {
        return (p3, p2, p1) -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3, P4, R> Func4<P4, P3, P2, P1, R> reverse(Func4<P1, P2, P3, P4, R> f) {
        return (p4, p3, p2, p1) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, P5, R> Func5<P5, P4, P3, P2, P1, R> reverse(Func5<P1, P2, P3, P4, P5, R> f) {
        return (p5, p4, p3, p2, p1) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func6<P6, P5, P4, P3, P2, P1, R> reverse(Func6<P1, P2, P3, P4, P5, P6, R> f) {
        return (p6, p5, p4, p3, p2, p1) -> f.call(p1, p2, p3, p4, p5, p6);
    }
}
