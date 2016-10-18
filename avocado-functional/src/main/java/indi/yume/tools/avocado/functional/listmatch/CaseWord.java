package indi.yume.tools.avocado.functional.listmatch;

import com.annimon.stream.Optional;

import java.util.List;

import indi.yume.tools.avocado.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

import static indi.yume.tools.avocado.functional.listmatch.ListUtil.first;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.last;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.rest;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.restL;

/**
 * Created by yume on 16-8-15.
 */

public abstract class CaseWord<C, L extends List<C>, T> {
    public abstract CaseWord<C, L, T> case_(
            Func1<L, Boolean> matcher,
            Func1<L, T> action);

    public abstract CaseWord<C, L, T> case_f(
            Func2<C, L, Boolean> matcher,
            Func2<C, L, T> action);

    public abstract CaseWord<C, L, T> case_l(
            Func2<L, C, Boolean> matcher,
            Func2<L, C, T> action);

    public abstract EndCase<T> else_(Func1<L, T> action);

    public CaseWord<C, L, T> empty(Func0<T> action) {
        return case_(ListUtil::isEmpty, xs -> action.call());
    }

    public CaseWord<C, L, T> matchF(Func2<C, L, T> action) {
        return case_f((x, xs) -> true, action);
    }

    public CaseWord<C, L, T> matchL(Func2<L, C, T> action) {
        return case_l((xs, x) -> true, action);
    }

    public CaseWord<C, L, T> matchF(C element, Func1<L, T> action) {
        return case_f((x, xs) -> ObjectUtil.deepEquals(element, x), (x, xs) -> action.call(xs));
    }

    public CaseWord<C, L, T> matchL(C element, Func1<L, T> action) {
        return case_l((xs, x) -> ObjectUtil.deepEquals(element, x), (xs, x) -> action.call(xs));
    }

    public EndCase<T> else_(T returnValue) {
        return else_(c -> returnValue);
    }

    public T el_get(Func1<L, T> action) {
        return else_(action).get();
    }

    public T el_get(T returnValue) {
        return else_(returnValue).get();
    }

    public Optional<T> el_getOp(Func1<L, T> action) {
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
        public CaseWord<C, L, T> case_(Func1<L, Boolean> matcher,
                                       Func1<L, T> action) {
            if(matcher.call(checkValue))
                return RightCase.unit(checkValue, action.call(checkValue));

            return this;
        }

        @Override
        public CaseWord<C, L, T> case_f(Func2<C, L, Boolean> matcher,
                                        Func2<C, L, T> action) {
            C first = first(checkValue);
            L rest = rest(checkValue);

            if(matcher.call(first, rest))
                return RightCase.unit(checkValue, action.call(first, rest));

            return this;
        }

        @Override
        public CaseWord<C, L, T> case_l(Func2<L, C, Boolean> matcher,
                                        Func2<L, C, T> action) {
            C last = last(checkValue);
            L rest = restL(checkValue);

            if(matcher.call(rest, last))
                return RightCase.unit(checkValue, action.call(rest, last));

            return this;
        }

        @Override
        public EndCase<T> else_(Func1<L, T> action) {
            return new EndCase<>(action.call(checkValue));
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
        public CaseWord<C, L, T> case_(Func1<L, Boolean> matcher, Func1<L, T> action) {
            return this;
        }

        @Override
        public CaseWord<C, L, T> case_f(Func2<C, L, Boolean> matcher, Func2<C, L, T> action) {
            return this;
        }

        @Override
        public CaseWord<C, L, T> case_l(Func2<L, C, Boolean> matcher, Func2<L, C, T> action) {
            return this;
        }

        @Override
        public EndCase<T> else_(Func1<L, T> action) {
            return new EndCase<>(returnValue);
        }
    }
}
