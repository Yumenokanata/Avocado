package indi.yume.tools.avocado.functional.ifexpression;

import com.annimon.stream.function.Supplier;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yume on 16-7-22.
 */

public class IfExpression {
    public static StartIfWord if_(Supplier<Boolean> pred) {
        return new StartIfWord(pred);
    }

    public static StartIfWord if_(boolean pred) {
        return new StartIfWord(pred);
    }

    public static StartIfWord if_(int num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(long num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(float num) {
        return new StartIfWord(num != 0f);
    }

    public static StartIfWord if_(double num) {
        return new StartIfWord(num != 0d);
    }

    public static StartIfWord if_(Boolean pred) {
        return new StartIfWord(pred);
    }

    public static StartIfWord if_(Integer num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(Long num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(Float num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(Double num) {
        return new StartIfWord(num != 0);
    }

    public static StartIfWord if_(List list) {
        return new StartIfWord(list != null && !list.isEmpty());
    }

    public static StartIfWord if_(Set set) {
        return new StartIfWord(set != null && !set.isEmpty());
    }

    public static StartIfWord if_(Map map) {
        return new StartIfWord(map != null && !map.isEmpty());
    }

    public static StartIfWord if_(Iterable iterable) {
        return new StartIfWord(iterable == null || !iterable.iterator().hasNext());
    }

    public static StartIfWord if_(String string) {
        return new StartIfWord(string != null && !"".equals(string));
    }

    public static StartIfWord if_(Object object) {
        return new StartIfWord(object != null);
    }
}
