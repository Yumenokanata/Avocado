package indi.yume.tools.avocado.functional.listmatch;

import com.annimon.stream.function.ThrowableSupplier;

import java.util.List;

import indi.yume.tools.avocado.functional.FuncUtils;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import lombok.Data;

import static indi.yume.tools.avocado.functional.listmatch.ListUtil.first;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.last;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.rest;
import static indi.yume.tools.avocado.functional.listmatch.ListUtil.restL;

/**
 * Created by yume on 16-8-15.
 */
@Data
public class StartWord<C, L extends List<C>> {
    private final L checkValue;

    public <T> CaseWord<C, L, T> case_(
            Predicate<L> matcher,
            Function<L, T> action) {
        final Boolean right = FuncUtils.runUnsafe(matcher, checkValue);

        if (right)
            return CaseWord.RightCase.unit(checkValue, FuncUtils.runUnsafe(action, checkValue));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, L, T> case_f(
            BiPredicate<C, L> matcher,
            BiFunction<C, L, T> action) {
        C first = first(checkValue);
        L rest = rest(checkValue);

        if(FuncUtils.runUnsafe(matcher, first, rest))
            return CaseWord.RightCase.unit(checkValue, FuncUtils.runUnsafe(action, first, rest));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, L, T> case_l(
            BiPredicate<L, C> matcher,
            BiFunction<L, C, T> action) {
        C last = last(checkValue);
        L rest = restL(checkValue);

        if(FuncUtils.runUnsafe(matcher, rest, last))
            return CaseWord.RightCase.unit(checkValue, FuncUtils.runUnsafe(action, rest, last));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> EndCase<T> else_(Function<L, T> action) {
        return new EndCase<>(FuncUtils.runUnsafe(action, checkValue));
    }

    public <T> CaseWord<C, L, T> empty(ThrowableSupplier<T, Exception> action) {
        return case_(ListUtil::isEmpty, xs -> action.get());
    }
}
