package indi.yume.tools.avocado.collect.interval.extension;

/**
 * The TypeInterval class maintains an interval with some associated data
 * @author Kevin Dolan
 * 
 * @param <Type> The type of data being stored
 */
public class TypeInterval<VALUE extends Comparable<VALUE>, Type> implements Comparable<TypeInterval<VALUE, Type>> {

	private VALUE start;
	private VALUE end;
	private Type data;
	
	public TypeInterval(VALUE start, VALUE end, Type data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	public VALUE getStart() {
		return start;
	}

	public void setStart(VALUE start) {
		this.start = start;
	}

	public VALUE getEnd() {
		return end;
	}

	public void setEnd(VALUE end) {
		this.end = end;
	}

	public Type getData() {
		return data;
	}

	public void setData(Type data) {
		this.data = data;
	}
	
	/**
	 * @param time
	 * @return	true if this interval contains time (invlusive)
	 */
	public boolean contains(VALUE time) {
		return start.compareTo(time) <= 0 && end.compareTo(time) >= 0;
	}
	
	/**
	 * @param other
	 * @return	return true if this interval intersects other
	 */
	public boolean intersects(TypeInterval<VALUE, ?> other) {
		return start.compareTo(other.getEnd()) <= 0 && end.compareTo(other.getStart()) >= 0;
	}
	
	/**
	 * Return -1 if this interval's start time is less than the other, 1 if greater
	 * In the event of a tie, -1 if this interval's end time is less than the other, 1 if greater, 0 if same
	 * @param other
	 * @return 1 or -1
	 */
	@Override
	public int compareTo(TypeInterval<VALUE, Type> other) {
		if(start.compareTo(other.getStart()) < 0)
			return -1;
		else if(start.compareTo(other.getStart()) > 0)
			return 1;
		else if(end.compareTo(other.getEnd()) < 0)
			return -1;
		else if(end.compareTo(other.getEnd()) > 0)
			return 1;
		else
			return 0;
	}
}
