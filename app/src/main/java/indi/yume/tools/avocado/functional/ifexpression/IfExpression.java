package indi.yume.tools.avocado.functional.ifexpression;

import rx.functions.Func0;

import static indi.yume.tools.avocado.functional.Utils.const0;

/**
 * Created by yume on 16-7-22.
 */

public class IfExpression {
    public static StartIfWord if_(Func0<Boolean> pred) {
        return new StartIfWord(pred);
    }

    public static StartIfWord if_(boolean pred) {
        return new StartIfWord(const0(pred));
    }
}
