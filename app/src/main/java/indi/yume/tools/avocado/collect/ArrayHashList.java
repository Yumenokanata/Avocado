package indi.yume.tools.avocado.collect;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yume on 16-5-3.
 */
public class ArrayHashList<E> extends ArrayList<E> {
    private Map<Integer, E> itemSet = new HashMap<>();

    public ArrayHashList() {
        super();
    }

    public ArrayHashList(Collection<? extends E> collection) {
        super(collection);

        for(int i = 0; i < size(); i++)
            itemSet.put(i, get(i));
    }

    @Override
    public boolean add(E object) {
        if(super.add(object)) {
            itemSet.put(size() - 1, object);
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, E object) {
        super.add(index, object);
        itemSet.put(index, object);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        return super.addAll(index, collection);
    }

    @Override
    public boolean remove(Object object) {
        return super.remove(object);
    }

//    @NonNull
//    @Override
//    public Collection<E> values() {
//        return null;
//    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return super.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return super.retainAll(collection);
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public void clear() {
        super.clear();
    }

    //============ Map ============

    @Override
    public E set(int index, E object) {
        return super.set(index, object);
    }

    //============ read ============

    @Override
    public boolean contains(Object object) {
        return super.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return super.containsAll(collection);
    }

    @Override
    public int indexOf(Object object) {
        return super.indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return super.lastIndexOf(object);
    }
}
