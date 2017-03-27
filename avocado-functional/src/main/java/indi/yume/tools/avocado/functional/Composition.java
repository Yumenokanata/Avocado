package indi.yume.tools.avocado.functional;

import io.reactivex.functions.Function;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Composition {
    public static <P1, IP, R> Function<P1, R> forwardCompose(Function<P1, IP> f1, Function<IP, R> f2) {
        return p -> f2.apply(f1.apply(p));
    }

    public static <P1, IP, R> Function<P1, R> andThen(Function<P1, IP> f1, Function<IP, R> f2) {
        return forwardCompose(f1, f2);
    }

    public static <P1, IP, R> Function<P1, R> compose(Function<IP, R> f1, Function<P1, IP> f2) {
        return p -> f1.apply(f2.apply(p));
    }
}
