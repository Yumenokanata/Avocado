package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
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
public class Currying {
    public static <P1, P2, R> Func1<P1, Func1<P2, R>> curried(Func2<P1, P2, R> f) {
        return p1 -> p2 -> f.call(p1, p2);
    }

    public static <P1, P2, P3, R> Func1<P1, Func1<P2, Func1<P3, R>>> curried(Func3<P1, P2, P3, R> f) {
        return p1 -> p2 -> p3 -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3, P4, R> Func1<P1, Func1<P2, Func1<P3, Func1<P4, R>>>> curried(Func4<P1, P2, P3, P4, R> f) {
        return p1 -> p2 -> p3 -> p4 -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, P5, R> Func1<P1, Func1<P2, Func1<P3, Func1<P4, Func1<P5, R>>>>> curried(Func5<P1, P2, P3, P4, P5, R> f) {
        return p1 -> p2 -> p3 -> p4 -> p5 -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func1<P1, Func1<P2, Func1<P3, Func1<P4, Func1<P5, Func1<P6, R>>>>>> curried(Func6<P1, P2, P3, P4, P5, P6, R> f) {
        return p1 -> p2 -> p3 -> p4 -> p5 -> p6 -> f.call(p1, p2, p3, p4, p5, p6);
    }
}
