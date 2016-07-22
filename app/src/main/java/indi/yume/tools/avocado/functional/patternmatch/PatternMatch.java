package indi.yume.tools.avocado.functional.patternmatch;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.google.common.collect.Iterables;

import indi.yume.tools.avocado.functional.Utils;
import lombok.experimental.UtilityClass;
import rx.functions.Action1;
import rx.functions.Func1;

import static indi.yume.tools.avocado.functional.Utils.voidFun1;
import static indi.yume.tools.avocado.util.ObjectUtil.cast;
import static indi.yume.tools.avocado.util.ObjectUtil.checkNotNull;

/**
 * Created by yume on 16-7-22.
 */
@UtilityClass
public class PatternMatch {
    public static <W, R> R when(W w, Case<W, R>... caseList) {
        return Stream.of(caseList)
                .filter(c -> c.check(w))
                .findFirst()
                .map(c -> c.act(w))
                .orElse(null);
    }

    public static <T, R> Case<T, R> c_pd(Func1<T, Boolean> predicate, Func1<T, R> action) {
        return new Case<>(predicate, action);
    }

    public static <T, R> Case<T, R> c_eq(Object value, Func1<T, R> action) {
        Func1<T, Boolean> f = w -> Optional.ofNullable(value).map(v -> v.equals(w)).orElse(value == w);
        return new Case<>(f, action);
    }

    public static <T, C extends T, R> Case<T, R> c_is(Class<C> clazz, Func1<C, R> action) {
        checkNotNull(clazz);
        checkNotNull(action);
        return new Case<>(clazz::isInstance, t -> action.call(cast(t)));
    }

    public static <T, R> Case<T, R> c_nis(Class<T> clazz, Func1<T, R> action) {
        checkNotNull(clazz);
        checkNotNull(action);
        return new Case<>(o -> !clazz.isInstance(o), action::call);
    }

    public static <T, R> Case<T, R> c_el(Func1<T, R> action) {
        return new Case<>(Utils.const1(true), action);
    }

    /*
     * start <= value < end
     */
    public static <T extends Comparable<T>, R> Case<T, R> c_in(Comparable<T> start, Comparable<T> end, Func1<T, R> action) {
        return new Case<>(v -> start.compareTo(v) <= 0 && end.compareTo(v) > 0, action);
    }

    public static <T, R> Case<T, R> c_in(Iterable<T> iterable, Func1<T, R> action) {
        return new Case<>(n -> Iterables.contains(iterable, n), action);
    }

    public static <T extends Comparable<T>, R> Case<T, R> c_nin(Comparable<T> start, Comparable<T> end, Func1<T, R> action) {
        return new Case<>(v -> start.compareTo(v) > 0 || end.compareTo(v) <= 0, action);
    }

    public static <T, R> Case<T, R> c_nin(Iterable<T> iterable, Func1<T, R> action) {
        return new Case<>(n -> !Iterables.contains(iterable, n), action);
    }

    //  ============== Void ===============

    public static <T> Case<T, Void> c_pd(Func1<T, Boolean> predicate, Action1<T> action) {
        return new Case<>(predicate, voidFun1(action));
    }

    public static <T> Case<T, Void> c_eq(Object value, Action1<T> action) {
        Func1<T, Boolean> f = w -> Optional.ofNullable(value).map(v -> v.equals(w)).orElse(value == w);
        return new Case<>(f, voidFun1(action));
    }

    public static <T, C extends T> Case<T, Void> c_is(Class<C> clazz, Action1<C> action) {
        checkNotNull(clazz);
        checkNotNull(action);
        return new Case<>(clazz::isInstance, voidFun1(c -> action.call(cast(c))));
    }

    public static <T, C extends T> Case<T, Void> c_nis(Class<C> clazz, Action1<C> action) {
        checkNotNull(clazz);
        checkNotNull(action);
        return new Case<>(o -> !clazz.isInstance(o), voidFun1(c -> action.call(cast(c))));
    }

    public static <T> Case<T, Void> c_el(Action1<T> action) {
        return new Case<>(Utils.const1(true), voidFun1(action));
    }

    /*
     * start <= value < end
     */
    public static <T extends Comparable<T>, R> Case<T, Void> c_in(Comparable<T> start, Comparable<T> end, Action1<T> action) {
        return new Case<>(v -> start.compareTo(v) <= 0 && end.compareTo(v) > 0, voidFun1(action));
    }

    public static <T> Case<T, Void> c_in(Iterable<T> iterable, Action1<T> action) {
        return new Case<>(n -> Iterables.contains(iterable, n), voidFun1(action));
    }

    public static <T extends Comparable<T>, R> Case<T, Void> n_nin(Comparable<T> start, Comparable<T> end, Action1<T> action) {
        return new Case<>(v -> start.compareTo(v) > 0 || end.compareTo(v) <= 0, voidFun1(action));
    }

    public static <T> Case<T, Void> n_nin(Iterable<T> iterable, Action1<T> action) {
        return new Case<>(n -> !Iterables.contains(iterable, n), voidFun1(action));
    }
}
