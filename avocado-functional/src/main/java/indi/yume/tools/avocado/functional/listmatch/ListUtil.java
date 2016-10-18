package indi.yume.tools.avocado.functional.listmatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-10-18.
 */
@UtilityClass
public class ListUtil {
    public static <E, L extends List<E>> boolean isEmpty(L list) {
        return list == null || list.isEmpty();
    }

    public static <E, L extends List<E>> boolean isSingle(L list) {
        return list != null && list.size() == 1;
    }

    public static <E, L extends List<E>> E first(L list) {
        if(list == null || list.isEmpty())
            return null;

        if(list instanceof ArrayList) {
            return ((ArrayList<E>) list).get(0);
        } else if(list instanceof LinkedList) {
            return ((LinkedList<E>) list).getFirst();
        } else {
            return list.get(0);
        }
    }

    public static <E, L extends List<E>> E last(L list) {
        if(list == null || list.isEmpty())
            return null;

        if(list instanceof ArrayList) {
            return ((ArrayList<E>) list).get(list.size() - 1);
        } else if(list instanceof LinkedList) {
            return ((LinkedList<E>) list).getLast();
        } else {
            return list.get(list.size() - 1);
        }
    }

    public static <E, L extends List<E>> L rest(L list) {
        if(list == null)
            return (L) Collections.<E>emptyList();
        if (list.isEmpty())
            return list;

        return (L) list.subList(1, list.size());
    }

    public static <E, L extends List<E>> L restL(L list) {
        if(list == null)
            return (L) Collections.<E>emptyList();
        if (list.isEmpty())
            return list;

        return (L) list.subList(0, list.size() - 1);
    }
}
