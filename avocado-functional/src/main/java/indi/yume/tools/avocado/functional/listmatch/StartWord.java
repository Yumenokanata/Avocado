package indi.yume.tools.avocado.functional.listmatch;

import java.util.List;

import lombok.Data;
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
@Data
public class StartWord<C, L extends List<C>> {
    private final L checkValue;

    public <T> CaseWord<C, L, T> case_(
            Func1<L, Boolean> matcher,
            Func1<L, T> action) {
        final Boolean right = matcher.call(checkValue);

        if (right)
            return CaseWord.RightCase.unit(checkValue, action.call(checkValue));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, L, T> case_f(
            Func2<C, L, Boolean> matcher,
            Func2<C, L, T> action) {
        C first = first(checkValue);
        L rest = rest(checkValue);

        if(matcher.call(first, rest))
            return CaseWord.RightCase.unit(checkValue, action.call(first, rest));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, L, T> case_l(
            Func2<L, C, Boolean> matcher,
            Func2<L, C, T> action) {
        C last = last(checkValue);
        L rest = restL(checkValue);

        if(matcher.call(rest, last))
            return CaseWord.RightCase.unit(checkValue, action.call(rest, last));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> EndCase<T> else_(Func1<L, T> action) {
        return new EndCase<>(action.call(checkValue));
    }

    public <T> CaseWord<C, L, T> empty(Func0<T> action) {
        return case_(ListUtil::isEmpty, xs -> action.call());
    }
}
