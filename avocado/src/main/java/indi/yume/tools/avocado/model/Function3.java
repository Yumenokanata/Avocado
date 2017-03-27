package indi.yume.tools.avocado.model;

import io.reactivex.annotations.NonNull;

/**
 * Created by yume on 17-3-24.
 */

public interface Function3<T1, T2, T3, R> {
    /**
     * Calculate a value based on the input values.
     * @param t1 the first value
     * @param t2 the second value
     * @param t3 the third value
     * @return the result value
     */
    @NonNull
    R apply(@NonNull T1 t1, @NonNull T2 t2, @NonNull T3 t3);
}
