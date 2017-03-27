package indi.yume.tools.avocado.functional;

import com.annimon.stream.Optional;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import lombok.Data;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-7-13.
 */
@UtilityClass
public class Memoization {

    public static <P1, R> Function<P1, R> memoize(final Function<P1, R> f) throws Exception {
        return new Function<P1, R>() {
            final MemoizedHandler<Function<P1, R>, MemoizeKey1<P1, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1) {
                return m.invoke(new MemoizeKey1<>(p1));
            }
        };
    }

    public static <P1, P2, R> BiFunction<P1, P2, R> memoize(final BiFunction<P1, P2, R> f) throws Exception {
        return new BiFunction<P1, P2, R>() {
            final MemoizedHandler<BiFunction<P1, P2, R>, MemoizeKey2<P1, P2, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1, P2 p2) {
                return m.invoke(new MemoizeKey2<>(p1, p2));
            }
        };
    }

    public static <P1, P2, P3, R> Function3<P1, P2, P3, R> memoize(final Function3<P1, P2, P3, R> f) throws Exception {
        return new Function3<P1, P2, P3, R>() {
            final MemoizedHandler<Function3<P1, P2, P3, R>, MemoizeKey3<P1, P2, P3, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1, P2 p2, P3 p3) {
                return m.invoke(new MemoizeKey3<>(p1, p2, p3));
            }
        };
    }

    public static <P1, P2, P3, P4, R> Function4<P1, P2, P3, P4, R> memoize(final Function4<P1, P2, P3, P4, R> f) throws Exception {
        return new Function4<P1, P2, P3, P4, R>() {
            final MemoizedHandler<Function4<P1, P2, P3, P4, R>, MemoizeKey4<P1, P2, P3, P4, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1, P2 p2, P3 p3, P4 p4) {
                return m.invoke(new MemoizeKey4<>(p1, p2, p3, p4));
            }
        };
    }

    public static <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R> memoize(final Function5<P1, P2, P3, P4, P5, R> f) throws Exception {
        return new Function5<P1, P2, P3, P4, P5, R>() {
            final MemoizedHandler<Function5<P1, P2, P3, P4, P5, R>, MemoizeKey5<P1, P2, P3, P4, P5, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
                return m.invoke(new MemoizeKey5<>(p1, p2, p3, p4, p5));
            }
        };
    }

    public static <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R> memoize(final Function6<P1, P2, P3, P4, P5, P6, R> f) throws Exception {
        return new Function6<P1, P2, P3, P4, P5, P6, R>() {
            final MemoizedHandler<Function6<P1, P2, P3, P4, P5, P6, R>, MemoizeKey6<P1, P2, P3, P4, P5, P6, R>, R> m = new MemoizedHandler<>(f);

            @Override
            public R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
                return m.invoke(new MemoizeKey6<>(p1, p2, p3, p4, p5, p6));
            }
        };
    }
}

@Data
final class MemoizeKey1<P1, R> implements Function<Function<P1, R>, R> {
    private final P1 p1;

    @Override
    public R apply(Function<P1, R> f) throws Exception {
        return f.apply(p1);
    }
}

@Data
final class MemoizeKey2<P1, P2, R> implements Function<BiFunction<P1, P2, R>, R> {
    private final P1 p1;
    private final P2 p2;

    @Override
    public R apply(BiFunction<P1, P2, R> f) throws Exception {
        return f.apply(p1, p2);
    }
}

@Data
final class MemoizeKey3<P1, P2, P3, R> implements Function<Function3<P1, P2, P3, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;

    @Override
    public R apply(Function3<P1, P2, P3, R> f) throws Exception {
        return f.apply(p1, p2, p3);
    }
}

@Data
final class MemoizeKey4<P1, P2, P3, P4, R> implements Function<Function4<P1, P2, P3, P4, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;

    @Override
    public R apply(Function4<P1, P2, P3, P4, R> f) throws Exception {
        return f.apply(p1, p2, p3, p4);
    }
}

@Data
final class MemoizeKey5<P1, P2, P3, P4, P5, R> implements Function<Function5<P1, P2, P3, P4, P5, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;
    private final P5 p5;

    @Override
    public R apply(Function5<P1, P2, P3, P4, P5, R> f) throws Exception {
        return f.apply(p1, p2, p3, p4, p5);
    }
}

@Data
final class MemoizeKey6<P1, P2, P3, P4, P5, P6, R> implements Function<Function6<P1, P2, P3, P4, P5, P6, R>, R> {
    private final P1 p1;
    private final P2 p2;
    private final P3 p3;
    private final P4 p4;
    private final P5 p5;
    private final P6 p6;

    @Override
    public R apply(Function6<P1, P2, P3, P4, P5, P6, R> f) throws Exception {
        return f.apply(p1, p2, p3, p4, p5, p6);
    }
}

@Data
final class MemoizedHandler<F, K extends Function<F, R>, R> {
    private final F f;
    private final ConcurrentHashMap<K, R> m = new ConcurrentHashMap<>();

    public R invoke(K k) {
        return Optional.ofNullable(m.get(k))
                .orElseGet(() -> {
                    R r = FuncUtils.runUnsafe(k, f);
                    m.putIfAbsent(k, r);
                    return r;
                });
    }
}
