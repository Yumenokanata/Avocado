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
public class Partial {
    //(P1) -> R
    public static <P1, R> Func0<R> part1(Func1<P1, R> f, final P1 p1) {
        return () -> f.call(p1);
    }

    //(P1, P2) -> R
    public static <P1, P2, R> Func1<P2, R> part1(Func2<P1, P2, R> f, final P1 p1) {
        return p2 -> f.call(p1, p2);
    }

    public static <P1, P2, R> Func1<P1, R> part2(Func2<P1, P2, R> f, final P2 p2) {
        return p1 -> f.call(p1, p2);
    }

    //(P1, P2, P3) -> R
    public static <P1, P2, P3, R> Func2<P2, P3, R> part1(Func3<P1, P2, P3, R> f, final P1 p1) {
        return (p2, p3) -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3, R> Func2<P1, P3, R> part2(Func3<P1, P2, P3, R> f, final P2 p2) {
        return (p1, p3) -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3, R> Func2<P1, P2, R> part3(Func3<P1, P2, P3, R> f, final P3 p3) {
        return (p1, p2) -> f.call(p1, p2, p3);
    }

    //(P1, P2, P3, P4) -> R
    public static <P1, P2, P3, P4, R> Func3<P2, P3, P4, R> part1(Func4<P1, P2, P3, P4, R> f, final P1 p1) {
        return (p2, p3, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Func3<P1, P3, P4, R> part2(Func4<P1, P2, P3, P4, R> f, final P2 p2) {
        return (p1, p3, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Func3<P1, P2, P4, R> part3(Func4<P1, P2, P3, P4, R> f, final P3 p3) {
        return (p1, p2, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Func3<P1, P2, P3, R> part4(Func4<P1, P2, P3, P4, R> f, final P4 p4) {
        return (p1, p2, p3) -> f.call(p1, p2, p3, p4);
    }

    //(P1, P2, P3, P4, P5) -> R
    public static <P1, P2, P3, P4, P5, R> Func4<P2, P3, P4, P5, R> part1(Func5<P1, P2, P3, P4, P5, R> f, final P1 p1) {
        return (p2, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Func4<P1, P3, P4, P5, R> part2(Func5<P1, P2, P3, P4, P5, R> f, final P2 p2) {
        return (p1, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Func4<P1, P2, P4, P5, R> part3(Func5<P1, P2, P3, P4, P5, R> f, final P3 p3) {
        return (p1, p2, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Func4<P1, P2, P3, P5, R> part4(Func5<P1, P2, P3, P4, P5, R> f, final P4 p4) {
        return (p1, p2, p3, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Func4<P1, P2, P3, P4, R> part5(Func5<P1, P2, P3, P4, P5, R> f, final P5 p5) {
        return (p1, p2, p3, p4) -> f.call(p1, p2, p3, p4, p5);
    }

    //(P1, P2, P3, P4, P5, P6) -> R
    public static <P1, P2, P3, P4, P5, P6, R> Func5<P2, P3, P4, P5, P6, R> part1(Func6<P1, P2, P3, P4, P5, P6, R> f, final P1 p1) {
        return (p2, p3, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func5<P1, P3, P4, P5, P6, R> part2(Func6<P1, P2, P3, P4, P5, P6, R> f, final P2 p2) {
        return (p1, p3, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func5<P1, P2, P4, P5, P6, R> part3(Func6<P1, P2, P3, P4, P5, P6, R> f, final P3 p3) {
        return (p1, p2, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func5<P1, P2, P3, P5, P6, R> part4(Func6<P1, P2, P3, P4, P5, P6, R> f, final P4 p4) {
        return (p1, p2, p3, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func5<P1, P2, P3, P4, P6, R> part5(Func6<P1, P2, P3, P4, P5, P6, R> f, final P5 p5) {
        return (p1, p2, p3, p4, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func5<P1, P2, P3, P4, P5, R> part6(Func6<P1, P2, P3, P4, P5, P6, R> f, final P6 p6) {
        return (p1, p2, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    //(P1) -> R
    public static <P1> Action0 part1(Action1<P1> f, final P1 p1) {
        return () -> f.call(p1);
    }

    //(P1, P2) -> R
    public static <P1, P2> Action1<P2> part1(Action2<P1, P2> f, final P1 p1) {
        return p2 -> f.call(p1, p2);
    }

    public static <P1, P2> Action1<P1> part2(Action2<P1, P2> f, final P2 p2) {
        return p1 -> f.call(p1, p2);
    }

    //(P1, P2, P3) -> R
    public static <P1, P2, P3> Action2<P2, P3> part1(Action3<P1, P2, P3> f, final P1 p1) {
        return (p2, p3) -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3> Action2<P1, P3> part2(Action3<P1, P2, P3> f, final P2 p2) {
        return (p1, p3) -> f.call(p1, p2, p3);
    }

    public static <P1, P2, P3> Action2<P1, P2> part3(Action3<P1, P2, P3> f, final P3 p3) {
        return (p1, p2) -> f.call(p1, p2, p3);
    }

    //(P1, P2, P3, P4) -> R
    public static <P1, P2, P3, P4> Action3<P2, P3, P4> part1(Action4<P1, P2, P3, P4> f, final P1 p1) {
        return (p2, p3, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4> Action3<P1, P3, P4> part2(Action4<P1, P2, P3, P4> f, final P2 p2) {
        return (p1, p3, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4> Action3<P1, P2, P4> part3(Action4<P1, P2, P3, P4> f, final P3 p3) {
        return (p1, p2, p4) -> f.call(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4> Action3<P1, P2, P3> part4(Action4<P1, P2, P3, P4> f, final P4 p4) {
        return (p1, p2, p3) -> f.call(p1, p2, p3, p4);
    }

    //(P1, P2, P3, P4, P5) -> R
    public static <P1, P2, P3, P4, P5> Action4<P2, P3, P4, P5> part1(Action5<P1, P2, P3, P4, P5> f, final P1 p1) {
        return (p2, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5> Action4<P1, P3, P4, P5> part2(Action5<P1, P2, P3, P4, P5> f, final P2 p2) {
        return (p1, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5> Action4<P1, P2, P4, P5> part3(Action5<P1, P2, P3, P4, P5> f, final P3 p3) {
        return (p1, p2, p4, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5> Action4<P1, P2, P3, P5> part4(Action5<P1, P2, P3, P4, P5> f, final P4 p4) {
        return (p1, p2, p3, p5) -> f.call(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5> Action4<P1, P2, P3, P4> part5(Action5<P1, P2, P3, P4, P5> f, final P5 p5) {
        return (p1, p2, p3, p4) -> f.call(p1, p2, p3, p4, p5);
    }

    //(P1, P2, P3, P4, P5, P6) -> R
    public static <P1, P2, P3, P4, P5, P6> Action5<P2, P3, P4, P5, P6> part1(Action6<P1, P2, P3, P4, P5, P6> f, final P1 p1) {
        return (p2, p3, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6> Action5<P1, P3, P4, P5, P6> part2(Action6<P1, P2, P3, P4, P5, P6> f, final P2 p2) {
        return (p1, p3, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6> Action5<P1, P2, P4, P5, P6> part3(Action6<P1, P2, P3, P4, P5, P6> f, final P3 p3) {
        return (p1, p2, p4, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6> Action5<P1, P2, P3, P5, P6> part4(Action6<P1, P2, P3, P4, P5, P6> f, final P4 p4) {
        return (p1, p2, p3, p5, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6> Action5<P1, P2, P3, P4, P6> part5(Action6<P1, P2, P3, P4, P5, P6> f, final P5 p5) {
        return (p1, p2, p3, p4, p6) -> f.call(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6> Action5<P1, P2, P3, P4, P5> part6(Action6<P1, P2, P3, P4, P5, P6> f, final P6 p6) {
        return (p1, p2, p3, p4, p5) -> f.call(p1, p2, p3, p4, p5, p6);
    }
}
