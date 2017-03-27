package indi.yume.tools.avocado.functional;

import com.annimon.stream.function.ThrowableSupplier;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import lombok.Data;
import lombok.experimental.UtilityClass;

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

        public <R> UnitFuncOp<R> at(Function<T, R> f) {
            return new UnitFuncOp<>(() -> f.apply(startData));
        }

        public <P1, R> UnitFuncOp<R> at(BiFunction<T, P1, R> f, P1 p1) {
            return new UnitFuncOp<>(() -> f.apply(startData, p1));
        }

        public <P1, P2, R> UnitFuncOp<R> at(Function3<T, P1, P2, R> f, P1 p1, P2 p2) {
            return new UnitFuncOp<>(() -> f.apply(startData, p1, p2));
        }

        public <P1, P2, P3, R> UnitFuncOp<R> at(Function4<T, P1, P2, P3, R> f, P1 p1, P2 p2, P3 p3) {
            return new UnitFuncOp<>(() -> f.apply(startData, p1, p2, p3));
        }

        public <P1, P2, P3, P4, R> UnitFuncOp<R> at(Function5<T, P1, P2, P3, P4, R> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new UnitFuncOp<>(() -> f.apply(startData, p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, R> UnitFuncOp<R> at(Function6<T, P1, P2, P3, P4, P5, R> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new UnitFuncOp<>(() -> f.apply(startData, p1, p2, p3, p4, p5));
        }
    }

    public static class UnitFuncOp<T> {
        private final ThrowableSupplier<T, Exception> act;

        private UnitFuncOp(ThrowableSupplier<T, Exception> act) {
            this.act = act;
        }

        public <R> UnitFuncOp<R> at(Function<T, R> f) {
            return new UnitFuncOp<>(() -> f.apply(act.get()));
        }

        public <P1, R> UnitFuncOp<R> at(BiFunction<T, P1, R> f, P1 p1) {
            return new UnitFuncOp<>(() -> f.apply(act.get(), p1));
        }

        public <P1, P2, R> UnitFuncOp<R> at(Function3<T, P1, P2, R> f, P1 p1, P2 p2) {
            return new UnitFuncOp<>(() -> f.apply(act.get(), p1, p2));
        }

        public <P1, P2, P3, R> UnitFuncOp<R> at(Function4<T, P1, P2, P3, R> f, P1 p1, P2 p2, P3 p3) {
            return new UnitFuncOp<>(() -> f.apply(act.get(), p1, p2, p3));
        }

        public <P1, P2, P3, P4, R> UnitFuncOp<R> at(Function5<T, P1, P2, P3, P4, R> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new UnitFuncOp<>(() -> f.apply(act.get(), p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, R> UnitFuncOp<R> at(Function6<T, P1, P2, P3, P4, P5, R> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new UnitFuncOp<>(() -> f.apply(act.get(), p1, p2, p3, p4, p5));
        }

        public T get() throws RuntimeException {
            try {
                return act.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public T getOr(T defaultValue) {
            try {
                return act.get();
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    public static <T, R> FuncOp<T, R> st(Function<T, R> f) {
        return new FuncOp<>(f);
    }

    public static class FuncOp<T, R> {
        private final Function<T, R> act;

        private FuncOp(Function<T, R> act) {
            this.act = act;
        }

        public <P> FuncOp<T, P> at(Function<R, P> f) {
            return new FuncOp<>(andThen(act, f));
        }

        public <P1, P> FuncOp<T, P> at(BiFunction<R, P1, P> f, P1 p1) {
            return new FuncOp<>(t -> f.apply(act.apply(t), p1));
        }

        public <P1, P2, P> FuncOp<T, P> at(Function3<R, P1, P2, P> f, P1 p1, P2 p2) {
            return new FuncOp<>(t -> f.apply(act.apply(t), p1, p2));
        }

        public <P1, P2, P3, P> FuncOp<T, P> at(Function4<R, P1, P2, P3, P> f, P1 p1, P2 p2, P3 p3) {
            return new FuncOp<>(t -> f.apply(act.apply(t), p1, p2, p3));
        }

        public <P1, P2, P3, P4, P> FuncOp<T, P> at(Function5<R, P1, P2, P3, P4, P> f, P1 p1, P2 p2, P3 p3, P4 p4) {
            return new FuncOp<>(t -> f.apply(act.apply(t), p1, p2, p3, p4));
        }

        public <P1, P2, P3, P4, P5, P> FuncOp<T, P> at(Function6<R, P1, P2, P3, P4, P5, P> f, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return new FuncOp<>(t -> f.apply(act.apply(t), p1, p2, p3, p4, p5));
        }

        public Function<T, R> get() {
            return act;
        }
    }
}
