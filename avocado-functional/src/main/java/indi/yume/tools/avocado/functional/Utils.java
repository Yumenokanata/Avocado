package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Action4;
import rx.functions.Action5;
import rx.functions.Action6;
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

    public static <T> Func0<T> const0(final T t) {
        return () -> t;
    }

    public static <P1, T> Func1<P1, T> const1(final T t) {
        return p1 -> t;
    }

    public static <P1, P2, T> Func2<P1, P2, T> const2(final T t) {
        return (p1, p2) -> t;
    }

    public static <P1, P2, P3, T> Func3<P1, P2, P3, T> const3(final T t) {
        return (p1, p2, p3) -> t;
    }

    public static <P1, P2, P3, P4, T> Func4<P1, P2, P3, P4, T> const4(final T t) {
        return (p1, p2, p3, p4) -> t;
    }

    public static <P1, P2, P3, P4, P5, T> Func5<P1, P2, P3, P4, P5, T> const5(final T t) {
        return (p1, p2, p3, p4, p5) -> t;
    }

    public static <P1, P2, P3, P4, P5, P6, T> Func6<P1, P2, P3, P4, P5, P6, T> const6(final T t) {
        return (p1, p2, p3, p4, p5, p6) -> t;
    }

    public static Func0<Void> voidFun() {
        return () -> null;
    }

    public static Func0<Void> voidFun0(final Action0 act) {
        return () -> {
            act.call();
            return null;
        };
    }

    public static <P1> Func1<P1, Void> voidFun1(final Action1<P1> act) {
        return p1 -> {
            act.call(p1);
            return null;
        };
    }

    public static <P1, P2> Func2<P1, P2, Void> voidFun2(final Action2<P1, P2> act) {
        return (p1, p2) -> {
            act.call(p1, p2);
            return null;
        };
    }

    public static <P1, P2, P3> Func3<P1, P2, P3, Void> voidFun3(final Action3<P1, P2, P3> act) {
        return (p1, p2, p3) -> {
            act.call(p1, p2, p3);
            return null;
        };
    }

    public static <P1, P2, P3, P4> Func4<P1, P2, P3, P4, Void> voidFun4(final Action4<P1, P2, P3, P4> act) {
        return (p1, p2, p3, p4) -> {
            act.call(p1, p2, p3, p4);
            return null;
        };
    }

    public static <P1, P2, P3, P4, P5> Func5<P1, P2, P3, P4, P5, Void> voidFun5(final Action5<P1, P2, P3, P4, P5> act) {
        return (p1, p2, p3, p4, p5) -> {
            act.call(p1, p2, p3, p4, p5);
            return null;
        };
    }

    public static <P1, P2, P3, P4, P5, P6> Func6<P1, P2, P3, P4, P5, P6, Void> voidFun6(final Action6<P1, P2, P3, P4, P5, P6> act) {
        return (p1, p2, p3, p4, p5, p6) -> {
            act.call(p1, p2, p3, p4, p5, p6);
            return null;
        };
    }
}
