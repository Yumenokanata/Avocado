package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.function.Supplier;

/**
 * Created by yume on 16-7-22.
 */

public class LeftWord<R> extends ControlWord<R> {
    @Override
    public EndWord<R> else_(Supplier<R> leftAction) {
        return new EndWord<>(leftAction);
    }

    @Override
    public ElseIfWord<R> else_if(final Supplier<Boolean> pred) {
        return rightAction -> pred.get() ? new RightWord<>(rightAction) : new LeftWord<>();
    }
}
