package indi.yume.tools.sample;

import android.util.TimingLogger;

import com.annimon.stream.Stream;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Range;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import indi.yume.tools.avocado.collect.RangeMultimap;
import indi.yume.tools.avocado.collect.interval.Interval;
import indi.yume.tools.avocado.collect.interval.IntervalTree;
import indi.yume.tools.avocado.collect.interval.extension.TypeIntervalTree;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.util.Timer;

import static fj.data.Array.array;
import static fj.data.List.iterableList;

/**
 * Created by yume on 16-5-9.
 */
public class RangeMapCheck {
    private int max = 1000;
    private int min = 200;
    private String prefixString = "item";
    private Set<Tuple2<Range<Integer>, String>> testDataSet = new HashSet<>();
    private Set<Integer> testPointSet = new HashSet<>();

    public void setUp() {
        Random random = new Random(3);

        for(int i = 0; i < 3000; i++) {
            int range = Math.abs(random.nextInt());
            int maxRange = min + range + random.nextInt(max - min);
            int minRange = maxRange - range;
            testDataSet.add(Tuple2.of(Range.closed(minRange, maxRange),
                    String.format(Locale.getDefault(), "%s: %d-%d", prefixString, minRange, maxRange)));
        }

//        for(int i = 0; i < 120; i++) {
//            testPointSet.add(min + random.nextInt(max - min));
//        }
        int startRange = min + random.nextInt(max - min - 42);
        for(int i = 0; i < 42; i++)
            testPointSet.add(startRange + i);
    }

    public void testSum() {
        TimingLogger logger = new TimingLogger("TAG", "testSum");
        testTypeInterval();
        logger.addSplit("testTypeInterval");
        testRangeMap();
        logger.addSplit("testRangeMap");
        testLongInterval();
        logger.addSplit("testLongInterval");
        testForRange();
        logger.addSplit("testForRange");
        logger.dumpToLog();
//        testStream();
    }

//    @Test
    public void testStream() {
        Timer timer = new Timer();

        timer.start();
        Multimap<Integer, Tuple2<Range<Integer>, String>> streamGetData = HashMultimap.create();

        fj.data.List<Tuple2<Range<Integer>, String>> dataList = iterableList(testDataSet);
        iterableList(testPointSet)
                .foldLeft((sumSet, point) -> {
                    sumSet.putAll(point, dataList.filter(model -> model.getData1().contains(point)));
                    return sumSet;
                }, streamGetData);

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
        Integer[] range = testPointSet.toArray(new Integer[testPointSet.size()]);
        int startRange = range[0];
        int endRange = range[range.length - 1];

        List<Interval<String>> subTreeList = rangeTree.getIntervals(startRange, endRange);
        IntervalTree<String> subTree = new IntervalTree<>(subTreeList);
        for(Integer point : testPointSet) {
            List<String> result = subTree.get(point);
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
}
