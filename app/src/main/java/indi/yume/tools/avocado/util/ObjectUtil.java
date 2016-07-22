package indi.yume.tools.avocado.util;

import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-22.
 */
@UtilityClass
public class ObjectUtil {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <R> R cast(Object value) {
        return (R) value;
    }
}
