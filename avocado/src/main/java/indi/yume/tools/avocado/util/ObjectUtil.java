package indi.yume.tools.avocado.util;

import java.util.Arrays;

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

    public static boolean deepEquals(Object a, Object b) {
        if (a == null || b == null) {
            return a == b;
        } else if (a == b) {
            return true;
        } else if (a instanceof Object[] && b instanceof Object[]) {
            return Arrays.deepEquals((Object[]) a, (Object[]) b);
        } else if (a instanceof boolean[] && b instanceof boolean[]) {
            return Arrays.equals((boolean[]) a, (boolean[]) b);
        } else if (a instanceof byte[] && b instanceof byte[]) {
            return Arrays.equals((byte[]) a, (byte[]) b);
        } else if (a instanceof char[] && b instanceof char[]) {
            return Arrays.equals((char[]) a, (char[]) b);
        } else if (a instanceof double[] && b instanceof double[]) {
            return Arrays.equals((double[]) a, (double[]) b);
        } else if (a instanceof float[] && b instanceof float[]) {
            return Arrays.equals((float[]) a, (float[]) b);
        } else if (a instanceof int[] && b instanceof int[]) {
            return Arrays.equals((int[]) a, (int[]) b);
        } else if (a instanceof long[] && b instanceof long[]) {
            return Arrays.equals((long[]) a, (long[]) b);
        } else if (a instanceof short[] && b instanceof short[]) {
            return Arrays.equals((short[]) a, (short[]) b);
        }
        return a.equals(b);
    }
}
