package indi.yume.tools.avocado.collect;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

import java.util.Iterator;

import indi.yume.tools.avocado.model.DayDate;

/**
 * Created by yume on 16-5-4.
 */
public class DayDateIterable implements Iterable<DayDate> {
    private Range<DayDate> dateRange;

    public DayDateIterable(Range<DayDate> dateRange) {
        this.dateRange = dateRange;
    }

    public static DayDateIterable of(Range<DayDate> dateRange) {
        if(!dateRange.hasLowerBound()
                || ! dateRange.hasUpperBound())
            throw new RangeException();

        return new DayDateIterable(dateRange);
    }

    private static class DayDateIterator implements Iterator<DayDate> {
        private Range<DayDate> dateRange;
        private DayDate tempDayDate;
        private DayDate endDayDate;

        private DayDateIterator(Range<DayDate> dateRange) {
            tempDayDate = new DayDate(dateRange.lowerEndpoint());
            if(dateRange.lowerBoundType() == BoundType.OPEN)
                tempDayDate.addDay(1);

            endDayDate = new DayDate(dateRange.upperEndpoint());
            if(dateRange.upperBoundType() == BoundType.OPEN)
                endDayDate.addDay(-1);
        }

        @Override
        public boolean hasNext() {
            return !endDayDate.equals(tempDayDate)
                    && tempDayDate.getTime() < endDayDate.getTime();
        }

        @Override
        public DayDate next() {
            tempDayDate.addDay(1);
            return tempDayDate;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<DayDate> iterator() {
        return new DayDateIterator(dateRange);
    }
}
