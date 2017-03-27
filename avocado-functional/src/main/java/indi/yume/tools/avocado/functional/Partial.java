package indi.yume.tools.avocado.functional;

import com.annimon.stream.function.ThrowableSupplier;

import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Partial {
    //(P1) -> R
    public static <P1, R> ThrowableSupplier<R, Exception> part1(Function<P1, R> f, final P1 p1) {
        return () -> f.apply(p1);
    }

    //(P1, P2) -> R
    public static <P1, P2, R> Function<P2, R> part1(BiFunction<P1, P2, R> f, final P1 p1) {
        return p2 -> f.apply(p1, p2);
    }

    public static <P1, P2, R> Function<P1, R> part2(BiFunction<P1, P2, R> f, final P2 p2) {
        return p1 -> f.apply(p1, p2);
    }

    //(P1, P2, P3) -> R
    public static <P1, P2, P3, R> BiFunction<P2, P3, R> part1(Function3<P1, P2, P3, R> f, final P1 p1) {
        return (p2, p3) -> f.apply(p1, p2, p3);
    }

    public static <P1, P2, P3, R> BiFunction<P1, P3, R> part2(Function3<P1, P2, P3, R> f, final P2 p2) {
        return (p1, p3) -> f.apply(p1, p2, p3);
    }

    public static <P1, P2, P3, R> BiFunction<P1, P2, R> part3(Function3<P1, P2, P3, R> f, final P3 p3) {
        return (p1, p2) -> f.apply(p1, p2, p3);
    }

    //(P1, P2, P3, P4) -> R
    public static <P1, P2, P3, P4, R> Function3<P2, P3, P4, R> part1(Function4<P1, P2, P3, P4, R> f, final P1 p1) {
        return (p2, p3, p4) -> f.apply(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Function3<P1, P3, P4, R> part2(Function4<P1, P2, P3, P4, R> f, final P2 p2) {
        return (p1, p3, p4) -> f.apply(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Function3<P1, P2, P4, R> part3(Function4<P1, P2, P3, P4, R> f, final P3 p3) {
        return (p1, p2, p4) -> f.apply(p1, p2, p3, p4);
    }

    public static <P1, P2, P3, P4, R> Function3<P1, P2, P3, R> part4(Function4<P1, P2, P3, P4, R> f, final P4 p4) {
        return (p1, p2, p3) -> f.apply(p1, p2, p3, p4);
    }

    //(P1, P2, P3, P4, P5) -> R
    public static <P1, P2, P3, P4, P5, R> Function4<P2, P3, P4, P5, R> part1(Function5<P1, P2, P3, P4, P5, R> f, final P1 p1) {
        return (p2, p3, p4, p5) -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Function4<P1, P3, P4, P5, R> part2(Function5<P1, P2, P3, P4, P5, R> f, final P2 p2) {
        return (p1, p3, p4, p5) -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Function4<P1, P2, P4, P5, R> part3(Function5<P1, P2, P3, P4, P5, R> f, final P3 p3) {
        return (p1, p2, p4, p5) -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Function4<P1, P2, P3, P5, R> part4(Function5<P1, P2, P3, P4, P5, R> f, final P4 p4) {
        return (p1, p2, p3, p5) -> f.apply(p1, p2, p3, p4, p5);
    }

    public static <P1, P2, P3, P4, P5, R> Function4<P1, P2, P3, P4, R> part5(Function5<P1, P2, P3, P4, P5, R> f, final P5 p5) {
        return (p1, p2, p3, p4) -> f.apply(p1, p2, p3, p4, p5);
    }

    //(P1, P2, P3, P4, P5, P6) -> R
    public static <P1, P2, P3, P4, P5, P6, R> Function5<P2, P3, P4, P5, P6, R> part1(Function6<P1, P2, P3, P4, P5, P6, R> f, final P1 p1) {
        return (p2, p3, p4, p5, p6) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function5<P1, P3, P4, P5, P6, R> part2(Function6<P1, P2, P3, P4, P5, P6, R> f, final P2 p2) {
        return (p1, p3, p4, p5, p6) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P4, P5, P6, R> part3(Function6<P1, P2, P3, P4, P5, P6, R> f, final P3 p3) {
        return (p1, p2, p4, p5, p6) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P5, P6, R> part4(Function6<P1, P2, P3, P4, P5, P6, R> f, final P4 p4) {
        return (p1, p2, p3, p5, p6) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P4, P6, R> part5(Function6<P1, P2, P3, P4, P5, P6, R> f, final P5 p5) {
        return (p1, p2, p3, p4, p6) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P4, P5, R> part6(Function6<P1, P2, P3, P4, P5, P6, R> f, final P6 p6) {
        return (p1, p2, p3, p4, p5) -> f.apply(p1, p2, p3, p4, p5, p6);
    }

    //(P1) -> R
    public static <P1> Action part1(Consumer<P1> f, final P1 p1) {
        return () -> f.accept(p1);
    }

    //(P1, P2) -> R
    public static <P1, P2> Consumer<P2> part1(BiConsumer<P1, P2> f, final P1 p1) {
        return p2 -> f.accept(p1, p2);
    }

    public static <P1, P2> Consumer<P1> part2(BiConsumer<P1, P2> f, final P2 p2) {
        return p1 -> f.accept(p1, p2);
    }
}
