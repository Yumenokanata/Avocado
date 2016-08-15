package indi.yume.tools.avocado.functional.matching;

import indi.yume.tools.avocado.model.tuple.Tuple2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rx.functions.Func1;

/**
 * Created by yume on 16-8-15.
 */

public abstract class CaseWord<C, T> {
    public abstract <W> CaseWord<C, T> case_(
            Func1<C, Tuple2<Boolean, W>> matcher,
            Func1<W, T> action);

    public abstract EndCase<T> else_(Func1<C, T> action);

    public CaseWord<C, T> case_pd(
            Func1<C, Boolean> matcher,
            Func1<C, T> action) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), action);
    }

    public <W> CaseWord<C, T> case_(
            Func1<C, Tuple2<Boolean, W>> matcher,
            T returnValue) {
        return case_(matcher, w -> returnValue);
    }

    public CaseWord<C, T> case_pd(
            Func1<C, Boolean> matcher,
            T returnValue) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), w -> returnValue);
    }

    public EndCase<T> else_(T returnValue) {
        return else_(c -> returnValue);
    }

    public T el_get(Func1<C, T> action) {
        return else_(action).get();
    }

    public T el_get(T returnValue) {
        return else_(returnValue).get();
    }

    @Data(staticConstructor = "unit")
    @EqualsAndHashCode(callSuper = false)
    public static class LeftCase<C, T> extends CaseWord<C, T> {
        private final C checkValue;

        @Override
        public <W> CaseWord<C, T> case_(
                Func1<C, Tuple2<Boolean, W>> matcher,
                Func1<W, T> action) {
            final Tuple2<Boolean, W> data = matcher.call(checkValue);

            if(data.getData1())
                return RightCase.unit(checkValue, action.call(data.getData2()));

            return this;
        }

        @Override
        public EndCase<T> else_(Func1<C, T> action) {
            return new EndCase<>(action.call(checkValue));
        }
    }

    @Data(staticConstructor = "unit")
    @EqualsAndHashCode(callSuper = false)
    public static class RightCase<C, T> extends CaseWord<C, T> {
        private final C checkValue;
        private final T returnValue;

        @Override
        public <W> CaseWord<C, T> case_(
                Func1<C, Tuple2<Boolean, W>> matcher,
                Func1<W, T> action) {
            return this;
        }

        @Override
        public EndCase<T> else_(Func1<C, T> action) {
            return new EndCase<>(returnValue);
        }
    }
}
