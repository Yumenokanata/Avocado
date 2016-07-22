package indi.yume.tools.avocado.functional.patternmatch;

import com.annimon.stream.Optional;

import rx.functions.Func1;

import static indi.yume.tools.avocado.util.ObjectUtil.checkNotNull;

/**
 * Created by yume on 16-7-22.
 */
public class Case<W, R>{
    private final Func1<W, Boolean> predicate;
    private final Optional<Func1<W, R>> action;

    public Case(Func1<W, Boolean> predicate, Func1<W, R> action) {
        checkNotNull(predicate);
        this.predicate = predicate;
        this.action = Optional.ofNullable(action);
    }

    public boolean check(W w) {
        return predicate.call(w);
    }

    R act(W w) {
        return action.map(a -> a.call(w)).orElse(null);
    }
}
