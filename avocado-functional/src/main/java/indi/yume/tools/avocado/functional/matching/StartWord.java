package indi.yume.tools.avocado.functional.matching;

import indi.yume.tools.avocado.functional.FuncUtils;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import lombok.Data;
import io.reactivex.functions.Function;

/**
 * Created by yume on 16-8-15.
 */
@Data
public class StartWord<C> {
    private final C checkValue;

    public <W, T> CaseWord<C, T> case_(
            Function<C, Tuple2<Boolean, W>> matcher,
            Function<W, T> action) {
        final Tuple2<Boolean, W> data = FuncUtils.runUnsafe(matcher, checkValue);

        if(data.getData1())
            return CaseWord.RightCase.unit(checkValue, FuncUtils.runUnsafe(action, data.getData2()));

        return CaseWord.LeftCase.unit(checkValue);
    }

    public <T> CaseWord<C, T> case_pd(
            Function<C, Boolean> matcher,
            Function<C, T> action) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), action);
    }

    public <W, T> CaseWord<C, T> case_(
            Function<C, Tuple2<Boolean, W>> matcher,
            T returnValue) {
        return case_(matcher, w -> returnValue);
    }

    public <T> CaseWord<C, T> case_pd(
            Function<C, Boolean> matcher,
            T returnValue) {
        return case_(v -> Tuple2.<Boolean, C>of(matcher.apply(v), v), w -> returnValue);
    }
}
