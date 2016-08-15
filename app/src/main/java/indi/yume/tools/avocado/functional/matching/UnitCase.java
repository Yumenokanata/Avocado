package indi.yume.tools.avocado.functional.matching;

import com.annimon.stream.Optional;

import indi.yume.tools.avocado.model.tuple.Tuple2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by yume on 16-8-15.
 */

public class UnitCase {
    public static <T> UnitStartWord<T> when(T value) {
        return new UnitStartWord<>(value);
    }

    @Data
    public static class UnitStartWord<C> {
        private final C checkValue;

        public <W> UnitCaseWord<C> case_(
                Func1<C, Tuple2<Boolean, W>> matcher,
                Action1<W> action) {
            final Tuple2<Boolean, W> data = matcher.call(checkValue);

            if(data.getData1()) {
                action.call(data.getData2());
                return UnitCaseWord.RightCase.unit(checkValue);
            }

            return UnitCaseWord.LeftCase.unit(checkValue);
        }

        public UnitCaseWord<C> case_pd(
                Func1<C, Boolean> matcher,
                Action1<C> action) {
            return case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), action);
        }
    }

    public static abstract class UnitCaseWord<C> {
        public abstract <W> UnitCaseWord<C> case_(
                Func1<C, Tuple2<Boolean, W>> matcher,
                Action1<W> action);

        public abstract void else_(Action1<C> action);

        public UnitCaseWord<C> case_pd(Func1<C, Boolean> matcher,
                                       Action1<C> action){
            return this.case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), action);
        }

        @Data(staticConstructor = "unit")
        @EqualsAndHashCode(callSuper = false)
        public static class LeftCase<C> extends UnitCaseWord<C> {
            private final C checkValue;

            @Override
            public <W> UnitCaseWord<C> case_(
                    Func1<C, Tuple2<Boolean, W>> matcher,
                    Action1<W> action) {
                final Tuple2<Boolean, W> data = matcher.call(checkValue);

                if(data.getData1()) {
                    action.call(data.getData2());
                    return RightCase.unit(checkValue);
                }

                return this;
            }

            @Override
            public void else_(Action1<C> action) {
                action.call(checkValue);
            }
        }

        @Data(staticConstructor = "unit")
        @EqualsAndHashCode(callSuper = false)
        public static class RightCase<C> extends UnitCaseWord<C> {
            private final C checkValue;

            @Override
            public <W> UnitCaseWord<C> case_(
                    Func1<C, Tuple2<Boolean, W>> matcher,
                    Action1<W> action) {
                return this;
            }

            @Override
            public void else_(Action1<C> action) {

            }
        }
    }
}
