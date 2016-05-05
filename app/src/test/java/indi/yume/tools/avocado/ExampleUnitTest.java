package indi.yume.tools.avocado;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import indi.yume.tools.avocado.collect.DayDateIterable;
import indi.yume.tools.avocado.collect.RangeMultimap;
import indi.yume.tools.avocado.collect.interval.IntervalTree;
import indi.yume.tools.avocado.model.DayDate;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.util.Timer;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }

    @Test
    public void testArrayList() {
        String preFix = "item";

        List<DayDate> list = new ArrayList<>();

        DayDate tempDay = new DayDate();
        for (int i = 0; i < 365; i++) {
            list.add(new DayDate(tempDay));
            tempDay.addDay(1);
        }

        long time = System.currentTimeMillis();
        for (int n = 0; n < 4; n++) {
            List<DayDate> tempList = new ArrayList<>();
            int start = 100;
            tempDay = new DayDate();
            tempDay.addDay(start);

            for (int i = start; i < start + 42; i++) {
//                int itemIndex = list.indexOf(tempDay);
//                DayDate item = list.get(itemIndex);

                if(list.contains(tempDay)) {
                    tempList.add(new DayDate(tempDay));
                }

                tempDay.addDay(1);
            }

            System.out.println("result= " + tempList.size());
        }
        System.out.println("ArrayList: spend time= " + (System.currentTimeMillis() - time));
    }

    @Test
    public void testHashSet() {
        String preFix = "item";

        TreeSet<DayDate> set = new TreeSet<>();

        DayDate tempDay = new DayDate();
        for (int i = 0; i < 365; i++) {
            set.add(new DayDate(tempDay));
            tempDay.addDay(1);
        }

        long time = System.currentTimeMillis();
        Timer timer = new Timer();
        timer.start();
        for (int n = 0; n < 4; n++) {
            List<DayDate> tempList = new ArrayList<>();
            int start = 100;
            tempDay = new DayDate();
            tempDay.addDay(start);

            for (int i = start; i < start + 42; i++) {
//                int itemIndex = set.indexOf(tempDay);
                if(set.contains(tempDay)) {
                    tempList.add(new DayDate(tempDay));
                }

                tempDay.addDay(1);
            }

            System.out.println("result= " + tempList.size());
        }
        timer.stopAndPrint();
        System.out.println("HashSet: spend time= " + (System.currentTimeMillis() - time));
    }

    @Test
    public void testRangeIterable() {
        Timer timer = new Timer();

        Range<DayDate> tempRange = Range.closed(new DayDate(2015, 9, 3), new DayDate(2016, 9, 3));

//        timer.start();
//        DayDate tempDate = new DayDate();
//        for(int i = 0; i < 100; i++) {
//            tempDate.equals(tempDate);
//            tempDate.getTime();
//            tempDate.addDay(1);
//        }
//        timer.stopAndPrint();

        DayDate tempDate = new DayDate();
        timer.start();
        String msg = "";
        for(DayDate dayDate : DayDateIterable.of(tempRange))
            tempDate = dayDate;
        timer.stopAndPrint();
        System.out.println(tempDate.toString());
    }

    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();

        rangeMap.put(Range.closed(0, 4), "0-4");
        rangeMap.put(Range.closed(1, 2), "1-2");

        System.out.println(rangeMap.get(3));
    }

    @Test
    public void testRangeMultimap() {
        Random random = new Random(3);

        int max = 1000;
        int min = 200;
        Set<Tuple2<Range<Integer>, String>> testDataSet = new HashSet<>();
        Set<Integer> testPointSet = new HashSet<>();
        String prefixString = "item";
        Timer timer = new Timer();

        for(int i = 0; i < 100; i++) {
            int range = Math.abs(random.nextInt());
            int maxRange = min + range + random.nextInt(max - min);
            int minRange = maxRange - range;
            testDataSet.add(Tuple2.of(Range.closed(minRange, maxRange),
                    String.format(Locale.getDefault(), "%s: %d-%d", prefixString, minRange, maxRange)));
        }

        for(int i = 0; i < 42; i++) {
            testPointSet.add(min + random.nextInt(max - min));
        }

        timer.start();
        Multimap<Integer, Tuple2<Range<Integer>, String>> forGetData = HashMultimap.create();
//        Map<Integer, Tuple2<Range<Integer>, String>> forGetData = new HashMap<>();
        for(Integer point : testPointSet)
            for(Tuple2<Range<Integer>, String> item : testDataSet)
                if(item.getData1().contains(point))
                    forGetData.put(point, item);
        timer.stopAndPrint("forGetData: ");

        RangeMultimap<Integer, String> rangeMultimap = new RangeMultimap<>();
        for(Tuple2<Range<Integer>, String> item : testDataSet)
            rangeMultimap.put(item.getData1(), item.getData2());

        timer.start();
        Multimap<Integer, Map.Entry<Range<Integer>, String>> mapGetData = HashMultimap.create();
        for(Integer point : testPointSet) {
            mapGetData.putAll(point, rangeMultimap.getByPoint(point));
        }
        timer.stopAndPrint("mapGetData: ");

        IntervalTree<String> rangeTree = new IntervalTree<>();
        for(Tuple2<Range<Integer>, String> item : testDataSet)
            rangeTree.addInterval(item.getData1().lowerEndpoint(), item.getData1().upperEndpoint(), item.getData2());

        timer.start();
        Multimap<Integer, List<String>> treeGetData = HashMultimap.create();
//        Map<Integer, List<String>> treeGetData = new HashMap<>();
        for(Integer point : testPointSet) {
            List<String> result = rangeTree.get(point);
            treeGetData.put(point, result);
        }
        timer.stopAndPrint("treeGetData: ");

//        for(Map.Entry<Integer, Collection<Tuple2<Range<Integer>, String>>> entry : forGetData.asMap().entrySet()) {
//            Collection<List<String>> mapResult = mapGetData.get(entry.getKey());
//            for(Tuple2<Range<Integer>, String> tuple2 : entry.getValue())
//                for(List<String> list : mapResult)
//                    if(!list.contains(tuple2.getData2()))
//                        assertTrue(false);
//        }
    }
}