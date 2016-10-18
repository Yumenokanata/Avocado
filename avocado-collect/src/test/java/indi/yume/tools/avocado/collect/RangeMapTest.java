package indi.yume.tools.avocado.collect;

import com.annimon.stream.Stream;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import indi.yume.tools.avocado.collect.RangeMultimap;
import indi.yume.tools.avocado.collect.interval.IntervalTree;
import indi.yume.tools.avocado.collect.interval.daydate.DayDateIntervalTreeMultimap;
import indi.yume.tools.avocado.collect.interval.extension.TypeIntervalTree;
import indi.yume.tools.avocado.model.DayDate;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.util.Timer;

/**
 * Created by yume on 16-5-9.
 */
public class RangeMapTest {
    private int max = 1000;
    private int min = 200;
    private String prefixString = "item";
    private Set<Tuple2<Range<Integer>, String>> testDataSet = new HashSet<>();
    private Set<Integer> testPointSet = new HashSet<>();

    @Before
    public void doOnBefore() {
        Random random = new Random(3);

        for(int i = 0; i < 3000; i++) {
            int range = Math.abs(random.nextInt());
            int maxRange = min + range + random.nextInt(max - min);
            int minRange = maxRange - range;
            testDataSet.add(Tuple2.of(Range.closed(minRange, maxRange),
                    String.format(Locale.getDefault(), "%s: %d-%d", prefixString, minRange, maxRange)));
        }

        for(int i = 0; i < 10; i++) {
            testPointSet.add(min + random.nextInt(max - min));
        }
    }

    @Test
    public void testSum() {
        for(int i = 0; i < 10; i++) {
            testStream();
            testForRange();
            testRangeMap();
            testLongInterval();
            testTypeInterval();
            System.out.println("============================");
        }
    }

//    @Test
    public void testStream() {
        Timer timer = new Timer();

        timer.start();
        Multimap<Integer, Tuple2<Range<Integer>, String>> streamGetData = HashMultimap.create();

        for(Integer point : testPointSet)
            Stream.of(testDataSet)
                    .filter(model -> !model.getData1().contains(point))
                    .forEach(item -> streamGetData.put(point, item));

        timer.stopAndPrint("streamGetData: " + streamGetData.size() + " | ");
    }

//    @Test
    public void testForRange() {
        Timer timer = new Timer();

        timer.start();
        Multimap<Integer, Tuple2<Range<Integer>, String>> forGetData = HashMultimap.create();
//        Map<Integer, Tuple2<Range<Integer>, String>> forGetData = new HashMap<>();
        for(Integer point : testPointSet)
            for(Tuple2<Range<Integer>, String> item : testDataSet)
                if(item.getData1().contains(point))
                    forGetData.put(point, item);
        timer.stopAndPrint("forGetData: " + forGetData.size() + " | ");
    }

//    @Test
    public void testRangeMap() {
        Timer timer = new Timer();

        RangeMultimap<Integer, String> rangeMultimap = new RangeMultimap<>();
        for(Tuple2<Range<Integer>, String> item : testDataSet)
            rangeMultimap.put(item.getData1(), item.getData2());

        timer.start();
        Multimap<Integer, Map.Entry<Range<Integer>, String>> mapGetData = HashMultimap.create();
        for(Integer point : testPointSet) {
            mapGetData.putAll(point, rangeMultimap.getByPoint(point));
        }
        timer.stopAndPrint("mapGetData: " + mapGetData.size() + " | ");
    }

//    @Test
    public void testLongInterval() {
        Timer timer = new Timer();

        IntervalTree<String> rangeTree = new IntervalTree<>();
        for(Tuple2<Range<Integer>, String> item : testDataSet)
            rangeTree.addInterval(item.getData1().lowerEndpoint(), item.getData1().upperEndpoint(), item.getData2());

        timer.start();
//        Multimap<Integer, List<String>> treeGetData = HashMultimap.create();
//        Map<Integer, List<String>> treeGetData = new HashMap<>();
        Multimap<Integer, String> treeGetData = HashMultimap.create();
        for(Integer point : testPointSet) {
            List<String> result = rangeTree.get(point);
            treeGetData.putAll(point, result);
        }
        timer.stop();
        long size = Stream.of(treeGetData)
                .flatMap(map -> Stream.of(map.values()))
//                .flatMap(Stream::of)
                .count();
        timer.print("treeGetData: " + size + " | ");
    }

//    @Test
    public void testTypeInterval() {
        Timer timer = new Timer();

        TypeIntervalTree<Integer, String> rangeTree = new TypeIntervalTree<>();
        for(Tuple2<Range<Integer>, String> item : testDataSet)
            rangeTree.addInterval(item.getData1().lowerEndpoint(), item.getData1().upperEndpoint(), item.getData2());

        timer.start();
//        Multimap<Integer, List<String>> treeGetData = HashMultimap.create();
//        Map<Integer, List<String>> treeGetData = new HashMap<>();
        Multimap<Integer, String> typeGetData = HashMultimap.create();
        for(Integer point : testPointSet) {
            List<String> result = rangeTree.get(point);
            typeGetData.putAll(point, result);
        }

        timer.stop();
        long size = Stream.of(typeGetData)
                .flatMap(map -> Stream.of(map.values()))
//                .flatMap(Stream::of)
                .count();
        timer.print("typeGetData: " + size + " | ");
    }

    public void testDateSet() {
        DayDate startDay = new DayDate();
        Random random = new Random(4);
        final int sumDay = 360;
        final int intervalDay = 30;

        Set<DayDate> testData = new HashSet<>();
        for(int i = 0; i < 999; i++) {
            DayDate newDay = new DayDate(startDay);
            newDay.addDay(random.nextInt(sumDay - intervalDay));
            testData.add(newDay);
        }

        DayDateIntervalTreeMultimap<String> dayDateIntervalTreeMultimap = new DayDateIntervalTreeMultimap<>();

        for(int i = 0; i < 60; i++) {

        }
    }
}
