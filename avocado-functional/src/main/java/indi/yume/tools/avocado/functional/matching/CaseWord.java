package indi.yume.tools.avocado.functional.matching;

import com.annimon.stream.Optional;

import indi.yume.tools.avocado.functional.FuncUtils;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import io.reactivex.functions.Function;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yume on 16-8-15.
 */

public abstract class CaseWord<C, T> {
    public abstract <W> CaseWord<C, T> case_(
            Function<C, Tuple2<Boolean, W>> matcher,
            Function<W, T> action);

    public abstract EndCase<T> else_(Function<C, T> action);

    public CaseWord<C, T> case_pd(
            Function<C, Boolean> matcher,
            Function<C, T> action) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), action);
    }

    public <W> CaseWord<C, T> case_(
            Function<C, Tuple2<Boolean, W>> matcher,
            T returnValue) {
        return case_(matcher, w -> returnValue);
    }

    public CaseWord<C, T> case_pd(
            Function<C, Boolean> matcher,
            T returnValue) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), w -> returnValue);
    }

    public EndCase<T> else_(T returnValue) {
        return else_(c -> returnValue);
    }

    public T el_get(Function<C, T> action) {
        return else_(action).get();
    }

    public T el_get(T returnValue) {
        return else_(returnValue).get();
    }

    public Optional<T> el_getOp(Function<C, T> action) {
        return else_(action).getOptional();
    }

    public Optional<T> el_getOp(T returnValue) {
        return else_(returnValue).getOptional();
    }

    @Data(staticConstructor = "unit")
    @EqualsAndHashCode(callSuper = false)
    public static class LeftCase<C, T> extends CaseWord<C, T> {
        private final C checkValue;

        @Override
        public <W> CaseWord<C, T> case_(
                Function<C, Tuple2<Boolean, W>> matcher,
                Function<W, T> action) {
            final Tuple2<Boolean, W> data = FuncUtils.runUnsafe(matcher, checkValue);

            if(data.getData1())
                return RightCase.unit(checkValue, FuncUtils.runUnsafe(action, data.getData2()));

            return this;
        }

        @Override
        public EndCase<T> else_(Function<C, T> action) {
            return new EndCase<>(FuncUtils.runUnsafe(action, checkValue));
        }
    }

    @Data(staticConstructor = "unit")
    @EqualsAndHashCode(callSuper = false)
    public static class RightCase<C, T> extends CaseWord<C, T> {
        private final C checkValue;
        private final T returnValue;

        @Override
        public <W> CaseWord<C, T> case_(
                Function<C, Tuple2<Boolean, W>> matcher,
                Function<W, T> action) {
            return this;
        }

        @Override
        public EndCase<T> else_(Function<C, T> action) {
            return new EndCase<>(returnValue);
        }
    }
}
