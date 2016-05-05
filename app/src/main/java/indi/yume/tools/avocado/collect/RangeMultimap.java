package indi.yume.tools.avocado.collect;

import android.support.annotation.NonNull;

import com.google.common.collect.BoundType;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import indi.yume.tools.avocado.model.DayDate;
import indi.yume.tools.avocado.util.Timer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yume on 16-5-4.
 */
public class RangeMultimap<K extends Comparable, V> implements Map<Range<K>, V> {
    private final NavigableMap<K, HashSet<RangeMapEntry<K, V>>> entriesByLowerBound = new TreeMap<>();
    private final NavigableMap<K, HashSet<RangeMapEntry<K, V>>> entriesByUpperBound = new TreeMap<>();

    private final HashMultimap<Range<K>, V> allValueMap = HashMultimap.create();

    @Data
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    private static final class RangeMapEntry<K extends Comparable, V> implements Map.Entry<Range<K>, V> {
        private final Range<K> range;
        private final V value;

        @Override
        public Range<K> getKey() {
            return range;
        }

        @Override
        public V setValue(V object) {
            return value;
        }
    }

    public int size() {
        return allValueMap.size();
    }

    public boolean isEmpty() {
        return allValueMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return allValueMap.containsKey(key);
    }

    public boolean containsPoint(K point) {
        return !getByPoint(point).isEmpty();
    }

    public boolean containsValue(Object value) {
        return allValueMap.containsValue(value);
    }

    @NonNull
    @Override
    public Set<Entry<Range<K>, V>> entrySet() {
        return allValueMap.entries();
    }

    @Override
    public V get(Object key) {
        Range<K> keyRange;
        try {
            keyRange = (Range<K>) key;
        } catch (ClassCastException e) {
            return null;
        }

        Set<V> set = getAll(keyRange);
        return set.isEmpty() ? null : set.iterator().next();
    }

    public Set<V> getAll(Range<K> keyRange) {
        return allValueMap.get(keyRange);
    }

    public Set<Map.Entry<Range<K>, V>> getByPoint(K point) {
        Timer timer = new Timer(getClass().getSimpleName());
        timer.start();
        SortedMap<K, HashSet<RangeMapEntry<K, V>>> entryLowerMap = entriesByLowerBound.headMap(point, true);
        SortedMap<K, HashSet<RangeMapEntry<K, V>>> entryUpperMap = entriesByUpperBound.tailMap(point, true);

        SortedMap<K, HashSet<RangeMapEntry<K, V>>> resultMap;
        if(entryLowerMap.size() <= entryUpperMap.size()) {
            resultMap = entryLowerMap;
        } else {
            resultMap = entryUpperMap;
        }

        HashSet<Map.Entry<Range<K>, V>> resultSet = new HashSet<>();
        for(RangeMapEntry<K, V> entry : Iterables.concat(resultMap.values()))
            if(entry.getRange().contains(point))
                resultSet.add(entry);
        return resultSet;
    }

    @Override
    public V put(Range<K> key, V value) {
        allValueMap.put(key, value);

        HashSet<RangeMapEntry<K, V>> lowerHashSet = entriesByLowerBound.get(key.lowerEndpoint());
        if(lowerHashSet == null)
            lowerHashSet = new HashSet<>();

        lowerHashSet.add(RangeMapEntry.of(key, value));
        entriesByLowerBound.put(key.lowerEndpoint(), lowerHashSet);

        HashSet<RangeMapEntry<K, V>> upperHashSet = entriesByUpperBound.get(key.upperEndpoint());
        if(upperHashSet == null)
            upperHashSet = new HashSet<>();

        upperHashSet.add(RangeMapEntry.of(key, value));
        entriesByUpperBound.put(key.upperEndpoint(), upperHashSet);

        return value;
    }

    @Override
    public void putAll(Map<? extends Range<K>, ? extends V> map) {
        for(Map.Entry<? extends Range<K>, ? extends V> entry : map.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public V remove(Object key) {
        V value = get(key);

        if(value == null)
            return null;

        Range<K> keyRange;
        try {
            keyRange = (Range<K>) key;
        } catch (ClassCastException e) {
            return null;
        }

        remove(keyRange, value);

        return value;
    }

    public boolean remove(Range<K> keyRange, V value) {
        if(!allValueMap.remove(keyRange, value))
            return false;

        HashSet<RangeMapEntry<K, V>> lowerHashSet = entriesByLowerBound.get(keyRange.lowerEndpoint());
        lowerHashSet.remove(RangeMapEntry.<K, V>of(keyRange, value));
        HashSet<RangeMapEntry<K, V>> upperHashSet = entriesByUpperBound.get(keyRange.upperEndpoint());
        upperHashSet.remove(RangeMapEntry.<K, V>of(keyRange, value));

        return true;
    }

    public boolean putAll(Range<K> key, Iterable<? extends V> values) {
        allValueMap.putAll(key, values);

        HashSet<RangeMapEntry<K, V>> lowerHashSet = entriesByLowerBound.get(key.lowerEndpoint());
        if(lowerHashSet == null)
            lowerHashSet = new HashSet<>();

        for(V value : values)
            lowerHashSet.add(RangeMapEntry.of(key, value));
        entriesByLowerBound.put(key.lowerEndpoint(), lowerHashSet);

        HashSet<RangeMapEntry<K, V>> upperHashSet = entriesByUpperBound.get(key.upperEndpoint());
        if(upperHashSet == null)
            upperHashSet = new HashSet<>();

        for(V value : values)
            lowerHashSet.add(RangeMapEntry.of(key, value));
        entriesByLowerBound.put(key.lowerEndpoint(), upperHashSet);

        return true;
    }

    public void clear() {
        entriesByLowerBound.clear();
        entriesByUpperBound.clear();
        allValueMap.clear();
    }

    @NonNull
    public Set<Range<K>> keySet() {
        return allValueMap.keySet();
    }

    @NonNull
    public Collection<V> values() {
        return allValueMap.values();
    }

    public Collection<Map.Entry<Range<K>, V>> entries() {
        return allValueMap.entries();
    }

    public Map<Range<K>, Collection<V>> asMap() {
        return allValueMap.asMap();
    }
}
