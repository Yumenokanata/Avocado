package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by yume on 16-7-22.
 */

public interface ElseIfWord<R> {
    ControlWord<R> do_(Func0<R> rightAction);
}
