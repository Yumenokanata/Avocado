package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;
import rx.functions.Func1;

import static indi.yume.tools.avocado.util.ObjectUtil.checkNotNull;

/**
 * Created by yume on 16-7-22.
 */

public class RightWord<R> extends ControlWord<R> {
    private final Func0<R> action;

    public RightWord(Func0<R> action) {
        checkNotNull(action);
        this.action = action;
    }

    @Override
    public EndWord<R> else_(Func0<R> leftAction) {
        return new EndWord<>(action);
    }

    @Override
    public ElseIfWord<R> else_if(Func0<Boolean> pred) {
        return rightAction -> new RightWord<>(action);
    }
}
