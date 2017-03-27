package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.Optional;

import com.annimon.stream.function.Supplier;

/**
 * Created by yume on 16-7-22.
 */

public class EndWord<R> {
    private final Supplier<R> action;

    public EndWord(Supplier<R> action) {
        this.action = action;
    }

    public R get() {
        return action.get();
    }

    public Optional<R> getOptional() {
        return Optional.ofNullable(action.get());
    }
}
