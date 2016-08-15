package indi.yume.tools.avocado.functional.matching;

import indi.yume.tools.avocado.model.tuple.Tuple2;
import lombok.Data;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by yume on 16-8-15.
 */
@Data
public class StartWord<C> {
    private final C checkValue;

    public <W, T> CaseWord<C, T> case_(
            Func1<C, Tuple2<Boolean, W>> matcher,
            Func1<W, T> action) {
        final Tuple2<Boolean, W> data = matcher.call(checkValue);

        if(data.getData1())
            return CaseWord.RightCase.unit(checkValue, action.call(data.getData2()));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, T> case_pd(
            Func1<C, Boolean> matcher,
            Func1<C, T> action) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), action);
    }

    public <W, T> CaseWord<C, T> case_(
            Func1<C, Tuple2<Boolean, W>> matcher,
            T returnValue) {
        return case_(matcher, w -> returnValue);
    }

    public <T> CaseWord<C, T> case_pd(
            Func1<C, Boolean> matcher,
            T returnValue) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.call(v), v), w -> returnValue);
    }
}
