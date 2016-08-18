package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;

/**
 * Created by yume on 16-7-22.
 */

public class StartIfWord {
    private final boolean pred;

    public StartIfWord(Func0<Boolean> pred) {
        this.pred = pred.call();
    }

    public StartIfWord(boolean pred) {
        this.pred = pred;
    }

    public <R> ControlWord<R> do_(Func0<R> rightAction) {
        return pred ? new RightWord<>(rightAction) : new LeftWord<R>();
    }
}
