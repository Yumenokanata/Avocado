package indi.yume.tools.avocado.collect;

import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

import indi.yume.tools.avocado.model.Provider1;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.util.LogUtil;
import io.reactivex.functions.BiFunction;

/**
 * Created by yume on 16-5-16.
 */
public class PLazyList<T> {
    private Provider1<Tuple2<T, Object>> listFun;

    private PLazyList(Provider1<Tuple2<T, Object>> listFun) {
        this.listFun = listFun;
    }

    public static <K> PLazyList<K> nil() {
        return new PLazyList<K>(() -> null);
    }

    public PLazyList<T> cons(T head) {
        return new PLazyList<>(() -> Tuple2.of(head, listFun));
    }

    public boolean isEmpty() {
        return listFun.call() == null;
    }

    public T head() {
        Tuple2<T, Object> data = listFun.call();
        if(data == null)
            return null;
        else
            return data.getData1();
    }

    @SuppressWarnings("unchecked")
    public PLazyList<T> tail() {
        Tuple2<T, Object> data = listFun.call();

        if(data == null)
            return PLazyList.<T>nil();
        else
            return new PLazyList<>((Provider1<Tuple2<T, Object>>) data.getData2());
    }

    public <K> K fold(int n, K set, BiFunction<K, T, K> fun) {
        return n == 0 || isEmpty() ? set : tail().fold(n - 1, runSafe(fun, set, head()), fun);
    }

    public <K> K foldAll(K set, BiFunction<K, T, K> fun) {
        return isEmpty() ? set : tail().foldAll(runSafe(fun, set, head()), fun);
    }

    public List<T> take(int n) {
        return fold(n,
                new LinkedList<>(),
                (list, item) ->  {
                    list.add(item);
                    return list;
                });
    }

    public List<T> takeAll() {
        return foldAll(new LinkedList<T>(),
                (list, item) -> {
                    list.add(item);
                    return list;
                });
    }

    public List<T> toList() {
        return takeAll();
    }

    @Nullable
    private static <T1, T2, B> B runSafe(BiFunction<T1, T2, B> fun, T1 t1, T2 t2) {
        try {
            return fun.apply(t1, t2);
        } catch (Exception e) {
            LogUtil.e(e);
            return null;
        }
    }
}
