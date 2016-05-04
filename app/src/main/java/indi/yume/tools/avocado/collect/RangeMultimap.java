package indi.yume.tools.avocado.collect;

import android.support.annotation.NonNull;

import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.BoundType;
import com.google.common.collect.Cut;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;

import java.util.Collection;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import indi.yume.tools.avocado.model.DayDate;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yume on 16-5-4.
 */
public class RangeMultimap<K extends Comparable, V> implements Multimap<K, V> {
    private final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound = new TreeMap<>();
    private final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByUpperBound = new TreeMap<>();

    @Data
    @AllArgsConstructor
    private static class Cut<T extends Comparable> implements Comparable<Cut<T>> {
        private BoundType type;
        private T value;

        @Override
        public int compareTo(@NonNull Cut<T> another) {
            if(value == null && another.value == null)
                return 0;

            if(value == null)
                return -1;
            if(another.value == null)
                return 1;
            return value.compareTo(another.value);
        }
    }

    @Data
    @AllArgsConstructor
    private static final class RangeMapEntry<K extends Comparable, V> {
        private final Range<K> range;
        private final V value;
    }

    @Override
    public int size() {
        return entriesByLowerBound.size();
    }

    @Override
    public boolean isEmpty() {
        return entriesByLowerBound.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        entriesByLowerBound.floorEntry(key);
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public boolean containsEntry(Object key, Object value) {
        return false;
    }

    @Override
    public boolean put(K key, V value) {
        return false;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        return false;
    }

    @Override
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        return false;
    }

    @Override
    public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
        return null;
    }

    @Override
    public Collection<V> removeAll(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Collection<V> get(K key) {
        return null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Multiset<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        return null;
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return null;
    }
}
