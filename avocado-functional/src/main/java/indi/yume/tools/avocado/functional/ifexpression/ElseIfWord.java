package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.function.Supplier;

/**
 * Created by yume on 16-7-22.
 */

public interface ElseIfWord<R> {
    ControlWord<R> do_(Supplier<R> rightAction);
}
