package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Supplier;

import static indi.yume.tools.avocado.functional.FuncUtils.const0;

/**
 * Created by yume on 16-7-22.
 */

public abstract class ControlWord<R> {
    public abstract EndWord<R> else_(Supplier<R> leftAction);

    public abstract ElseIfWord<R> else_if(Supplier<Boolean> pred);

    public ElseIfWord<R> else_if(Boolean pred) {
        return else_if(const0(pred));
    }

    public R else_get(Supplier<R> leftAction) {
        return else_(leftAction).get();
    }

    public Optional<R> else_getOp(Supplier<R> leftAction) {
        return else_(leftAction).getOptional();
    }
}
