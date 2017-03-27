package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.function.Supplier;

import static indi.yume.tools.avocado.util.ObjectUtil.checkNotNull;

/**
 * Created by yume on 16-7-22.
 */

public class RightWord<R> extends ControlWord<R> {
    private final Supplier<R> action;

    public RightWord(Supplier<R> action) {
        checkNotNull(action);
        this.action = action;
    }

    @Override
    public EndWord<R> else_(Supplier<R> leftAction) {
        return new EndWord<>(action);
    }

    @Override
    public ElseIfWord<R> else_if(Supplier<Boolean> pred) {
        return rightAction -> new RightWord<>(action);
    }
}
