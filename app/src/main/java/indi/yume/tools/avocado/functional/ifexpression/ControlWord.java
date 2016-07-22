package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;
import rx.functions.Func1;

import static indi.yume.tools.avocado.functional.Utils.const0;

/**
 * Created by yume on 16-7-22.
 */

public abstract class ControlWord<R> {
    public abstract EndWord<R> else_(Func0<R> leftAction);

    public abstract ElseIfWord<R> else_if(Func0<Boolean> pred);

    public ElseIfWord<R> else_if(Boolean pred) {
        return else_if(const0(pred));
    }
}
