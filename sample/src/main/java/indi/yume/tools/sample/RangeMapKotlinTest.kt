package indi.yume.tools.sample

import com.annimon.stream.Stream
import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.common.collect.Range
import indi.yume.tools.avocado.collect.RangeMultimap
import indi.yume.tools.avocado.collect.interval.IntervalTree
import indi.yume.tools.avocado.collect.interval.extension.TypeIntervalTree
import indi.yume.tools.avocado.model.tuple.Tuple2
import indi.yume.tools.avocado.util.Timer
import org.funktionale.memoization.memoize
import org.funktionale.partials.invoke
import java.util.*
import java.util.Map

/**
 * Created by yume on 16-5-9.
 */
class RangeMapKotlinTest {
    private val max = 1000
    private val min = 200
    private val prefixString = "item"
    private val testDataSet = HashSet<Tuple2<Range<Int>, String>>()
    private val testPointSet = HashSet<Int>()

    fun doOnBefore() {
        val random = Random(3)

        for (i in 0..2999) {
            val range = Math.abs(random.nextInt())
            val maxRange = min + range + random.nextInt(max - min)
            val minRange = maxRange - range
            testDataSet.add(Tuple2.of(Range.closed(minRange, maxRange),
                    String.format(Locale.getDefault(), "%s: %d-%d", prefixString, minRange, maxRange)))
        }

        for (i in 0..41) {
            testPointSet.add(min + random.nextInt(max - min))
        }
    }

    fun testAll() {
//        testTypeInterval()
//        testRangeMap()
//        testLongInterval()
//        testForRange()
        testStream()

        val fun1 = { i: Int -> i + 1 }
        println(fun1.javaClass.simpleName)
    }

    fun testStream() {
        val timer = Timer()

        timer.start()
        val streamGetData = HashMultimap.create<Int, Tuple2<Range<Int>, String>>()

        val filterFun = { point:Int, model: Tuple2<Range<Int>, String> -> model.data1.contains(point) }.memoize()
        val allListFun = Iterable<Tuple2<Range<Int>, String>>::filter.invoke(testDataSet)
        val checkFun = { point: Int -> Pair(point, allListFun(filterFun(p1 = point))) }

        testPointSet.map { checkFun(it) }
                .forEach { streamGetData.putAll(it.first, it.second) }

        testPointSet.fold(streamGetData,
                {sumData, point ->
                    sumData.putAll(point, testDataSet.filter(filterFun(p1 = point)))
                    sumData
                })

//        for (point in testPointSet)
//            testDataSet.filter { !it.data1.contains(point) }.forEach { streamGetData.put(point, it) }
        val fun1 = { i: Int -> i + 1 }
        val fun2 = { i: Int -> i + 1 }
        println(fun1.javaClass.simpleName)
        timer.stopAndPrint("streamGetData + $fun1 , $fun2 ${fun1 == fun2}: " + streamGetData.size() + " | ")
    }

    fun testForRange() {
        val timer = Timer()

        timer.start()
        val forGetData = HashMultimap.create<Int, Tuple2<Range<Int>, String>>()
        //        Map<Integer, Tuple2<Range<Integer>, String>> forGetData = new HashMap<>();
        for (point in testPointSet)
            for (item in testDataSet)
                if (item.data1.contains(point))
                    forGetData.put(point, item)
        timer.stopAndPrint("forGetData: " + forGetData.size() + " | ")
    }

    fun testRangeMap() {
        val timer = Timer()

        val rangeMultimap = RangeMultimap<Int, String>()
        for (item in testDataSet)
            rangeMultimap.put(item.data1, item.data2)

        timer.start()
        val mapGetData = HashMultimap.create<Int, kotlin.collections.Map.Entry<Range<Int>, String>>()
        for (point in testPointSet) {
            mapGetData.putAll(point, rangeMultimap.getByPoint(point))
        }
        timer.stopAndPrint("mapGetData: " + mapGetData.size() + " | ")
    }

    fun testLongInterval() {
        val timer = Timer()

        val rangeTree = IntervalTree<String>()
        for (item in testDataSet)
            rangeTree.addInterval(item.data1.lowerEndpoint().toLong(), item.data1.upperEndpoint().toLong(), item.data2)

        timer.start()
        //        Multimap<Integer, List<String>> treeGetData = HashMultimap.create();
        //        Map<Integer, List<String>> treeGetData = new HashMap<>();
        val treeGetData = HashMultimap.create<Int, String>()
        for (point in testPointSet) {
            val result = rangeTree.get(point.toLong())
            treeGetData.putAll(point, result)
        }
        timer.stop()
        val size = treeGetData.values().size
        timer.print("treeGetData: $size | ")
    }

    fun testTypeInterval() {
        val timer = Timer()

        val rangeTree = TypeIntervalTree<Int, String>()
        for (item in testDataSet)
            rangeTree.addInterval(item.data1.lowerEndpoint(), item.data1.upperEndpoint(), item.data2)

        timer.start()
        //        Multimap<Integer, List<String>> treeGetData = HashMultimap.create();
        //        Map<Integer, List<String>> treeGetData = new HashMap<>();
        val typeGetData = HashMultimap.create<Int, String>()
        for (point in testPointSet) {
            val result = rangeTree.get(point)
            typeGetData.putAll(point, result)
        }

        timer.stop()
        val size = typeGetData.values().size
        timer.print("typeGetData: $size | ")
    }
}