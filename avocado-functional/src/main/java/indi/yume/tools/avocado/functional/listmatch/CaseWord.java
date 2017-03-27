package indi.yume.tools.avocado.functional.listmatch;

import com.annimon.stream.Optional;
import com.annimon.stream.function.ThrowableSupplier;

import java.util.List;

import indi.yume.tools.avocado.functional.FuncUtils;
import indi.yume.tools.avocado.util.ObjectUtil;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static indi.yume.tools.avocado.functional.listmatch.ListUtil.first;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.last;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.rest;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.restL;

/**
 * Created by yume on 16-8-15.
 */

public abstract class CaseWord<C, L extends List<C>, T> {
    public abstract CaseWord<C, L, T> case_(
            Predicate<L> matcher,
            Function<L, T> action);

    public abstract CaseWord<C, L, T> case_f(
            BiPredicate<C, L> matcher,
            BiFunction<C, L, T> action);

    public abstract CaseWord<C, L, T> case_l(
            BiPredicate<L, C> matcher,
            BiFunction<L, C, T> action);

    public abstract EndCase<T> else_(Function<L, T> action);

    public CaseWord<C, L, T> empty(ThrowableSupplier<T, Exception> action) {
        return case_(ListUtil::isEmpty, xs -> action.get());
    }

    public CaseWord<C, L, T> matchF(BiFunction<C, L, T> action) {
        return case_f((x, xs) -> true, action);
    }

    public CaseWord<C, L, T> matchL(BiFunction<L, C, T> action) {
        return case_l((xs, x) -> true, action);
    }

    public CaseWord<C, L, T> matchF(C element, Function<L, T> action) {
        return case_f((x, xs) -> ObjectUtil.deepEquals(element, x), (x, xs) -> action.apply(xs));
    }

    public CaseWord<C, L, T> matchL(C element, Function<L, T> action) {
        return case_l((xs, x) -> ObjectUtil.deepEquals(element, x), (xs, x) -> action.apply(xs));
    }

    public EndCase<T> else_(T returnValue) {
        return else_(c -> returnValue);
    }

    public T el_get(Function<L, T> action) {
        return else_(action).get();
    }

    public T el_get(T returnValue) {
        return else_(returnValue).get();
    }

    public Optional<T> el_getOp(Function<L, T> action) {
        return else_(action).getOptional();
    }

    public Optional<T> el_getOp(T returnValue) {
        return else_(returnValue).getOptional();
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class LeftCase<C, L extends List<C>, T> extends CaseWord<C, L, T> {
        private final L checkValue;

        public static <C, L extends List<C>, T> LeftCase<C, L, T> unit(L checkValue) {
            return new LeftCase<>(checkValue);
        }

        @Override
        public CaseWord<C, L, T> case_(Predicate<L> matcher,
                                       Function<L, T> action) {
            if(FuncUtils.runUnsafe(matcher, checkValue))
                return RightCase.unit(checkValue, FuncUtils.runUnsafe(action, checkValue));

            return this;
        }

        @Override
        public CaseWord<C, L, T> case_f(BiPredicate<C, L> matcher,
                                        BiFunction<C, L, T> action) {
            C first = first(checkValue);
            L rest = rest(checkValue);

            if(FuncUtils.runUnsafe(matcher, first, rest))
                return RightCase.unit(checkValue, FuncUtils.runUnsafe(action, first, rest));

            return this;
        }

        @Override
        public CaseWord<C, L, T> case_l(BiPredicate<L, C> matcher,
                                        BiFunction<L, C, T> action) {
            C last = last(checkValue);
            L rest = restL(checkValue);

            if(FuncUtils.runUnsafe(matcher, rest, last))
                return RightCase.unit(checkValue, FuncUtils.runUnsafe(action, rest, last));

            return this;
        }

        @Override
        public EndCase<T> else_(Function<L, T> action) {
            return new EndCase<>(FuncUtils.runUnsafe(action, checkValue));
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class RightCase<C, L extends List<C>, T> extends CaseWord<C, L, T> {
        private final L checkValue;
        private final T returnValue;

        public static <C, L extends List<C>, T> RightCase<C, L, T> unit(L checkValue, T returnValue) {
            return new RightCase<>(checkValue, returnValue);
        }

        @Override
        public CaseWord<C, L, T> case_(Predicate<L> matcher, Function<L, T> action) {
            return this;
        }

        @Override
        public CaseWord<C, L, T> case_f(BiPredicate<C, L> matcher, BiFunction<C, L, T> action) {
            return this;
        }

        @Override
        public CaseWord<C, L, T> case_l(BiPredicate<L, C> matcher, BiFunction<L, C, T> action) {
            return this;
        }

        @Override
        public EndCase<T> else_(Function<L, T> action) {
            return new EndCase<>(returnValue);
        }
    }
}
