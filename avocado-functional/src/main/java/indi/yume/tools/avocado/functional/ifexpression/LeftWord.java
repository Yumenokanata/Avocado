package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;

/**
 * Created by yume on 16-7-22.
 */

public class LeftWord<R> extends ControlWord<R> {
    @Override
    public EndWord<R> else_(Func0<R> leftAction) {
        return new EndWord<>(leftAction);
    }

    @Override
    public ElseIfWord<R> else_if(final Func0<Boolean> pred) {
        return rightAction -> pred.call() ? new RightWord<>(rightAction) : new LeftWord<>();
    }
}
