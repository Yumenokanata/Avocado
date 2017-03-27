package indi.yume.tools.avocado.functional.matching;

import com.annimon.stream.Stream;

import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.util.ObjectUtil;
import lombok.experimental.UtilityClass;
import io.reactivex.functions.Function;

import static indi.yume.tools.avocado.util.ObjectUtil.cast;
import static indi.yume.tools.avocado.util.ObjectUtil.checkNotNull;

/**
 * Created by yume on 16-8-15.
 */
@UtilityClass
public class Matcher {
    public static <C> Function<C, Tuple2<Boolean, C>> pd(Function<C, Boolean> predicate) {
        return value -> Tuple2.of(predicate.apply(value), value);
    }

    public static <C, T extends C> Function<C, Tuple2<Boolean, T>> is(Class<T> clazz) {
        checkNotNull(clazz);
        return check -> Tuple2.of(clazz.isInstance(check), cast(check));
    }

    public static <C, T extends C> Function<C, Tuple2<Boolean, C>> not_is(Class<T> clazz) {
        checkNotNull(clazz);
        return check -> Tuple2.of(!clazz.isInstance(check), check);
    }

    public static <C> Function<C, Tuple2<Boolean, C>> isNull() {
        return check -> Tuple2.of(check == null, check);
    }

    public static <C> Function<C, Tuple2<Boolean, C>> eq(final Object value) {
        return check -> Tuple2.of(ObjectUtil.deepEquals(check, value), check);
    }

    /*
     * start <= value < end
     */
    public static <C extends Comparable<C>> Function<C, Tuple2<Boolean, C>> in(Comparable<C> start,
                                                                            Comparable<C> end) {
        return check -> Tuple2.of(start.compareTo(check) <= 0 && end.compareTo(check) > 0, check);
    }

    public static <C> Function<C, Tuple2<Boolean, C>> in(Iterable<C> iterable) {
        return check -> Tuple2.of(Stream.of(iterable).anyMatch(i -> ObjectUtil.deepEquals(check, i)), check);
    }

    public static <C extends Comparable<C>> Function<C, Tuple2<Boolean, C>> not_in(Comparable<C> start,
                                                                                Comparable<C> end) {
        return check -> Tuple2.of(start.compareTo(check) > 0 || end.compareTo(check) <= 0, check);
    }

    public static <C> Function<C, Tuple2<Boolean, C>> not_in(Iterable<C> iterable) {
        return check -> Tuple2.of(!Stream.of(iterable).anyMatch(i -> ObjectUtil.deepEquals(check, i)), check);
    }
}
