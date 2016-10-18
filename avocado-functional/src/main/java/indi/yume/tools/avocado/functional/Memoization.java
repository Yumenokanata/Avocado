package indi.yume.tools.avocado.functional;

import com.annimon.stream.Optional;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;
import lombok.experimental.UtilityClass;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Memoization {

    public static <P1, R> Func1<P1, R> memoize(final Func1<P1, R> f) {
        return new Func1<P1, R>() {
            final MemoizedHandler<Func1<P1, R>, MemoizeKey1<P1, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1) {
                return m.invoke(new MemoizeKey1<>(p1));
            }
        };
    }

    public static <P1, P2, R> Func2<P1, P2, R> memoize(final Func2<P1, P2, R> f) {
        return new Func2<P1, P2, R>() {
            final MemoizedHandler<Func2<P1, P2, R>, MemoizeKey2<P1, P2, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1, P2 p2) {
                return m.invoke(new MemoizeKey2<>(p1, p2));
            }
        };
    }

    public static <P1, P2, P3, R> Func3<P1, P2, P3, R> memoize(final Func3<P1, P2, P3, R> f) {
        return new Func3<P1, P2, P3, R>() {
            final MemoizedHandler<Func3<P1, P2, P3, R>, MemoizeKey3<P1, P2, P3, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1, P2 p2, P3 p3) {
                return m.invoke(new MemoizeKey3<>(p1, p2, p3));
            }
        };
    }

    public static <P1, P2, P3, P4, R> Func4<P1, P2, P3, P4, R> memoize(final Func4<P1, P2, P3, P4, R> f) {
        return new Func4<P1, P2, P3, P4, R>() {
            final MemoizedHandler<Func4<P1, P2, P3, P4, R>, MemoizeKey4<P1, P2, P3, P4, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1, P2 p2, P3 p3, P4 p4) {
                return m.invoke(new MemoizeKey4<>(p1, p2, p3, p4));
            }
        };
    }

    public static <P1, P2, P3, P4, P5, R> Func5<P1, P2, P3, P4, P5, R> memoize(final Func5<P1, P2, P3, P4, P5, R> f) {
        return new Func5<P1, P2, P3, P4, P5, R>() {
            final MemoizedHandler<Func5<P1, P2, P3, P4, P5, R>, MemoizeKey5<P1, P2, P3, P4, P5, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
                return m.invoke(new MemoizeKey5<>(p1, p2, p3, p4, p5));
            }
        };
    }

    public static <P1, P2, P3, P4, P5, P6, R> Func6<P1, P2, P3, P4, P5, P6, R> memoize(final Func6<P1, P2, P3, P4, P5, P6, R> f) {
        return new Func6<P1, P2, P3, P4, P5, P6, R>() {
            final MemoizedHandler<Func6<P1, P2, P3, P4, P5, P6, R>, MemoizeKey6<P1, P2, P3, P4, P5, P6, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R call(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
                return m.invoke(new MemoizeKey6<>(p1, p2, p3, p4, p5, p6));
            }
        };
    }
}

@Data
final class MemoizeKey1<P1, R> implements Func1<Func1<P1, R>, R> {
    private final P1 p1;

    @Override
    public R call(Func1<P1, R> f) {
        return f.call(p1);
    }
}

@Data
final class MemoizeKey2<P1, P2, R> implements Func1<Func2<P1, P2, R>, R> {
    private final P1 p1;
    private final P2 p2;

    @Override
    public R call(Func2<P1, P2, R> f) {
        return f.call(p1, p2);
    }
}

@Data
final class MemoizeKey3<P1, P2, P3, R> implements Func1<Func3<P1, P2, P3, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;

    @Override
    public R call(Func3<P1, P2, P3, R> f) {
        return f.call(p1, p2, p3);
    }
}

@Data
final class MemoizeKey4<P1, P2, P3, P4, R> implements Func1<Func4<P1, P2, P3, P4, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;

    @Override
    public R call(Func4<P1, P2, P3, P4, R> f) {
        return f.call(p1, p2, p3, p4);
    }
}

@Data
final class MemoizeKey5<P1, P2, P3, P4, P5, R> implements Func1<Func5<P1, P2, P3, P4, P5, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;
    private final P5 p5;

    @Override
    public R call(Func5<P1, P2, P3, P4, P5, R> f) {
        return f.call(p1, p2, p3, p4, p5);
    }
}

@Data
final class MemoizeKey6<P1, P2, P3, P4, P5, P6, R> implements Func1<Func6<P1, P2, P3, P4, P5, P6, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;
    private final P5 p5;
    private final P6 p6;

    @Override
    public R call(Func6<P1, P2, P3, P4, P5, P6, R> f) {
        return f.call(p1, p2, p3, p4, p5, p6);
    }
}

@Data
final class MemoizedHandler<F, K extends Func1<F, R>, R> {
    private final F f;
    private final ConcurrentHashMap<K, R> m = new ConcurrentHashMap<>();

    public R invoke(K k) {
        return Optional.ofNullable(m.get(k))
                .orElseGet(() -> {
                    R r = k.call(f);
                    m.putIfAbsent(k, r);
                    return r;
                });
    }
}
