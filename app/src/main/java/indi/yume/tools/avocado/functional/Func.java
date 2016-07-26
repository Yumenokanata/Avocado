package indi.yume.tools.avocado.functional;

import lombok.Data;
import lombok.experimental.UtilityClass;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;

import static indi.yume.tools.avocado.functional.Composition.andThen;

/**
 * Created by yume on 16-7-26.
 */
@UtilityClass
public class Func {
    public static <T> StartFunOp<T> st(T t) {
        return new StartFunOp<>(t);
    }

    @Data
    public static class StartFunOp<T> {
        private final T startData;

        public <R> UnitFuncOp<R> at(Func1<T, R> f) {
            return new UnitFuncOp<>(() -> f.call(startData));
        }

        public <P1, R> UnitFuncOp<R> at(Func2<T, P1, R> f, P1 p1) {
            return new UnitFuncOp<>(() -> f.call(startData, p1));
        }

        public <P1, P2, R> UnitFuncOp<R> at(Func3<T, P1, P2, R> f, P1 p1, P2 p2) {
            return new UnitFuncOp<>(() -> f.call(startData, p1, p2));
        }

        public <P1, P2, P3, R> UnitFuncOp<R> at(Func4<T, P1, P2, P3, R> f, P1 p1, P2 p2, P3 p3) {
            return new UnitFuncOp<>(() -> f.call(startData, p1, p2, p3));
        }

        public <P1, P2, P3, P4, R> UnitFuncOp<R> at(Func5<T, P1, P2, P3, P4, R> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new UnitFuncOp<>(() -> f.call(startData, p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, R> UnitFuncOp<R> at(Func6<T, P1, P2, P3, P4, P5, R> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new UnitFuncOp<>(() -> f.call(startData, p1, p2, p3, p4, p5));
        }
    }

    public static class UnitFuncOp<T> {
        private final Func0<T> act;

        private UnitFuncOp(Func0<T> act) {
            this.act = act;
        }

        public <R> UnitFuncOp<R> at(Func1<T, R> f) {
            return new UnitFuncOp<>(() -> f.call(act.call()));
        }

        public <P1, R> UnitFuncOp<R> at(Func2<T, P1, R> f, P1 p1) {
            return new UnitFuncOp<>(() -> f.call(act.call(), p1));
        }

        public <P1, P2, R> UnitFuncOp<R> at(Func3<T, P1, P2, R> f, P1 p1, P2 p2) {
            return new UnitFuncOp<>(() -> f.call(act.call(), p1, p2));
        }

        public <P1, P2, P3, R> UnitFuncOp<R> at(Func4<T, P1, P2, P3, R> f, P1 p1, P2 p2, P3 p3) {
            return new UnitFuncOp<>(() -> f.call(act.call(), p1, p2, p3));
        }

        public <P1, P2, P3, P4, R> UnitFuncOp<R> at(Func5<T, P1, P2, P3, P4, R> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new UnitFuncOp<>(() -> f.call(act.call(), p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, R> UnitFuncOp<R> at(Func6<T, P1, P2, P3, P4, P5, R> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new UnitFuncOp<>(() -> f.call(act.call(), p1, p2, p3, p4, p5));
        }

        public T get() {
            return act.call();
        }
    }

    public static <T, R> FuncOp<T, R> st(Func1<T, R> f) {
        return new FuncOp<>(f);
    }

    public static class FuncOp<T, R> {
        private final Func1<T, R> act;

        private FuncOp(Func1<T, R> act) {
            this.act = act;
        }

        public <P> FuncOp<T, P> at(Func1<R, P> f) {
            return new FuncOp<>(andThen(act, f));
        }

        public <P1, P> FuncOp<T, P> at(Func2<R, P1, P> f, P1 p1) {
            return new FuncOp<>(t -> f.call(act.call(t), p1));
        }

        public <P1, P2, P> FuncOp<T, P> at(Func3<R, P1, P2, P> f, P1 p1, P2 p2) {
            return new FuncOp<>(t -> f.call(act.call(t), p1, p2));
        }

        public <P1, P2, P3, P> FuncOp<T, P> at(Func4<R, P1, P2, P3, P> f, P1 p1, P2 p2, P3 p3) {
            return new FuncOp<>(t -> f.call(act.call(t), p1, p2, p3));
        }

        public <P1, P2, P3, P4, P> FuncOp<T, P> at(Func5<R, P1, P2, P3, P4, P> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new FuncOp<>(t -> f.call(act.call(t), p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, P> FuncOp<T, P> at(Func6<R, P1, P2, P3, P4, P5, P> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new FuncOp<>(t -> f.call(act.call(t), p1, p2, p3, p4, p5));
        }

        public Func1<T, R> get() {
            return act;
        }
    }
}
