package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.function.Supplier;

/**
 * Created by yume on 16-7-22.
 */

public class StartIfWord {
    private final boolean pred;

    public StartIfWord(Supplier<Boolean> pred) {
        this.pred = pred.get();
    }

    public StartIfWord(boolean pred) {
        this.pred = pred;
    }

    public <R> ControlWord<R> do_(Supplier<R> rightAction) {
        return pred ? new RightWord<>(rightAction) : new LeftWord<>();
    }
}
