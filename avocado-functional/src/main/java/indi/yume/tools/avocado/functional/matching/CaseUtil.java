package indi.yume.tools.avocado.functional.matching;

/**
 * Created by yume on 16-8-15.
 */

public class CaseUtil {
    public static <T> StartWord<T> when(T value) {
        return new StartWord<T>(value);
    }
}
