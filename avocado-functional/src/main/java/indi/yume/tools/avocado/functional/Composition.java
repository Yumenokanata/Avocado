package indi.yume.tools.avocado.functional;

import lombok.experimental.UtilityClass;
import rx.functions.Func1;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Composition {
    public static <P1, IP, R> Func1<P1, R> forwardCompose(Func1<P1, IP> f1, Func1<IP, R> f2) {
        return p -> f2.call(f1.call(p));
    }

    public static <P1, IP, R> Func1<P1, R> andThen(Func1<P1, IP> f1, Func1<IP, R> f2) {
        return forwardCompose(f1, f2);
    }

    public static <P1, IP, R> Func1<P1, R> compose(Func1<IP, R> f1, Func1<P1, IP> f2) {
        return p -> f1.call(f2.call(p));
    }
}
