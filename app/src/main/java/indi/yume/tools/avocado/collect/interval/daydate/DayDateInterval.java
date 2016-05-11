package indi.yume.tools.avocado.collect.interval.daydate;

import indi.yume.tools.avocado.model.DayDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * The DayDateInterval class maintains an interval with some associated data
 * @author Kevin Dolan
 * 
 * @param <Type> The type of data being stored
 */
public class DayDateInterval<Type> implements Comparable<DayDateInterval<Type>> {

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private long start;
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private long end;
	@Getter
	@Setter
	private Type data;
	
	DayDateInterval(long start, long end, Type data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	public DayDateInterval(DayDate start, DayDate end, Type data) {
		this.start = start.getTime();
		this.end = end.getTime();
		this.data = data;
	}

	public DayDate getStartDayDate() {
		return new DayDate(start);
	}

	public void setStartDayDate(DayDate startDate) {
		start = startDate.getTime();
	}

	public DayDate getEndDayDate() {
		return new DayDate(end);
	}

	public void setEndDayDate(DayDate endDate) {
		end = endDate.getTime();
	}
	
	/**
	 * @param time
	 * @return	true if this interval contains time (invlusive)
	 */
	boolean contains(long time) {
		return time <= end && time >= start;
	}

	public boolean containsDayDate(DayDate day) {
		long time = day.getTime();
		return time <= end && time >= start;
	}
	
	/**
	 * @param other
	 * @return	return true if this interval intersects other
	 */
	public boolean intersects(DayDateInterval<?> other) {
		return other.getEnd() >= start && other.getStart() <= end;
	}
	
	/**
	 * Return -1 if this interval's start time is less than the other, 1 if greater
	 * In the event of a tie, -1 if this interval's end time is less than the other, 1 if greater, 0 if same
	 * @param other
	 * @return 1 or -1
	 */
	@Override
	public int compareTo(DayDateInterval<Type> other) {
		if(start < other.getStart())
			return -1;
		else if(start > other.getStart())
			return 1;
		else if(end < other.getEnd())
			return -1;
		else if(end > other.getEnd())
			return 1;
		else
			return 0;
	}
}
