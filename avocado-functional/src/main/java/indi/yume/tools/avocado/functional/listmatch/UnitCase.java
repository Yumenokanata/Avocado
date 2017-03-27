package indi.yume.tools.avocado.functional.listmatch;

import indi.yume.tools.avocado.functional.FuncUtils;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
                Function<C, Tuple2<Boolean, W>> matcher,
                Consumer<W> action) {
            final Tuple2<Boolean, W> data = FuncUtils.runUnsafe(matcher, checkValue);

            if(data.getData1()) {
                FuncUtils.runUnsafe(action, data.getData2());
                return UnitCaseWord.RightCase.unit(checkValue);
            }

            return UnitCaseWord.LeftCase.unit(checkValue);
        }

        public UnitCaseWord<C> case_pd(
                Function<C, Boolean> matcher,
                Consumer<C> action) {
            return case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), action);
        }
    }

    public static abstract class UnitCaseWord<C> {
        public abstract <W> UnitCaseWord<C> case_(
                Function<C, Tuple2<Boolean, W>> matcher,
                Consumer<W> action);

        public abstract void else_(Consumer<C> action);

        public UnitCaseWord<C> case_pd(Function<C, Boolean> matcher,
                                       Consumer<C> action){
            return this.case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), action);
        }

        @Data(staticConstructor = "unit")
        @EqualsAndHashCode(callSuper = false)
        public static class LeftCase<C> extends UnitCaseWord<C> {
            private final C checkValue;

            @Override
            public <W> UnitCaseWord<C> case_(
                    Function<C, Tuple2<Boolean, W>> matcher,
                    Consumer<W> action) {
                final Tuple2<Boolean, W> data = FuncUtils.runUnsafe(matcher, checkValue);

                if(data.getData1()) {
                    FuncUtils.runUnsafe(action, data.getData2());
                    return RightCase.unit(checkValue);
                }

                return this;
            }

            @Override
            public void else_(Consumer<C> action) {
                FuncUtils.runUnsafe(action, checkValue);
            }
        }

        @Data(staticConstructor = "unit")
        @EqualsAndHashCode(callSuper = false)
        public static class RightCase<C> extends UnitCaseWord<C> {
            private final C checkValue;

            @Override
            public <W> UnitCaseWord<C> case_(
                    Function<C, Tuple2<Boolean, W>> matcher,
                    Consumer<W> action) {
                return this;
            }

            @Override
            public void else_(Consumer<C> action) {

            }
        }
    }
}
