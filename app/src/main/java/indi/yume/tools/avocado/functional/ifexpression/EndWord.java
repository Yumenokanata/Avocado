package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.Optional;

import rx.functions.Func0;

/**
 * Created by yume on 16-7-22.
 */

public class EndWord<R> {
    private final Func0<R> action;

    public EndWord(Func0<R> action) {
        this.action = action;
    }

    public R get() {
        return action.call();
    }

    public Optional<R> getOptional() {
        return Optional.ofNullable(action.call());
    }
}
