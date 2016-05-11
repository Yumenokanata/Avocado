package indi.yume.tools.avocado.collect.interval.daydate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The Node class contains the interval tree information for one single node
 * 
 * @author Kevin Dolan
 */
class DayDateIntervalNode<Type> {

	private SortedMap<DayDateInterval<Type>, List<DayDateInterval<Type>>> intervals;
	private long center;
	private DayDateIntervalNode<Type> leftNode;
	private DayDateIntervalNode<Type> rightNode;
	
	DayDateIntervalNode() {
		intervals = new TreeMap<DayDateInterval<Type>, List<DayDateInterval<Type>>>();
		center = 0;
		leftNode = null;
		rightNode = null;
	}
	
	DayDateIntervalNode(List<DayDateInterval<Type>> intervalList) {
		
		intervals = new TreeMap<DayDateInterval<Type>, List<DayDateInterval<Type>>>();
		
		SortedSet<Long> endpoints = new TreeSet<Long>();
		
		for(DayDateInterval<Type> interval: intervalList) {
			endpoints.add(interval.getStart());
			endpoints.add(interval.getEnd());
		}
		
		long median = getMedian(endpoints);
		center = median;
		
		List<DayDateInterval<Type>> left = new ArrayList<DayDateInterval<Type>>();
		List<DayDateInterval<Type>> right = new ArrayList<DayDateInterval<Type>>();
		
		for(DayDateInterval<Type> interval : intervalList) {
			if(interval.getEnd() < median)
				left.add(interval);
			else if(interval.getStart() > median)
				right.add(interval);
			else {
				List<DayDateInterval<Type>> posting = intervals.get(interval);
				if(posting == null) {
					posting = new ArrayList<DayDateInterval<Type>>();
					intervals.put(interval, posting);
				}
				posting.add(interval);
			}
		}

		if(left.size() > 0)
			leftNode = new DayDateIntervalNode<Type>(left);
		if(right.size() > 0)
			rightNode = new DayDateIntervalNode<Type>(right);
	}

	/**
	 * Perform a stabbing query on the node
	 * @param time the time to query at
	 * @return	   all intervals containing time
	 */
	List<DayDateInterval<Type>> stab(long time) {
		List<DayDateInterval<Type>> result = new ArrayList<DayDateInterval<Type>>();

		for(Entry<DayDateInterval<Type>, List<DayDateInterval<Type>>> entry : intervals.entrySet()) {
			if(entry.getKey().contains(time))
				for(DayDateInterval<Type> interval : entry.getValue())
					result.add(interval);
			else if(entry.getKey().getStart() > time)
				break;
		}
		
		if(time < center && leftNode != null)
			result.addAll(leftNode.stab(time));
		else if(time > center && rightNode != null)
			result.addAll(rightNode.stab(time));
		return result;
	}
	
	/**
	 * Perform an interval intersection query on the node
	 * @param target the interval to intersect
	 * @return		   all intervals containing time
	 */
	List<DayDateInterval<Type>> query(DayDateInterval<?> target) {
		List<DayDateInterval<Type>> result = new ArrayList<DayDateInterval<Type>>();
		
		for(Entry<DayDateInterval<Type>, List<DayDateInterval<Type>>> entry : intervals.entrySet()) {
			if(entry.getKey().intersects(target))
				for(DayDateInterval<Type> interval : entry.getValue())
					result.add(interval);
			else if(entry.getKey().getStart() > target.getEnd())
				break;
		}
		
		if(target.getStart() < center && leftNode != null)
			result.addAll(leftNode.query(target));
		if(target.getEnd() > center && rightNode != null)
			result.addAll(rightNode.query(target));
		return result;
	}
	
	long getCenter() {
		return center;
	}

	void setCenter(long center) {
		this.center = center;
	}

	DayDateIntervalNode<Type> getLeft() {
		return leftNode;
	}

	void setLeft(DayDateIntervalNode<Type> left) {
		this.leftNode = left;
	}

	DayDateIntervalNode<Type> getRight() {
		return rightNode;
	}

	void setRight(DayDateIntervalNode<Type> right) {
		this.rightNode = right;
	}
	
	/**
	 * @param set the set to look on
	 * @return	  the median of the set, not interpolated
	 */
	private Long getMedian(SortedSet<Long> set) {
		int i = 0;
		int middle = set.size() / 2;
		for(Long point : set) {
			if(i == middle)
				return point;
			i++;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(center + ": ");
		for(Entry<DayDateInterval<Type>, List<DayDateInterval<Type>>> entry : intervals.entrySet()) {
			sb.append("[" + entry.getKey().getStart() + "," + entry.getKey().getEnd() + "]:{");
			for(DayDateInterval<Type> interval : entry.getValue()) {
				sb.append("("+interval.getStart()+","+interval.getEnd()+","+interval.getData()+")");
			}
			sb.append("} ");
		}
		return sb.toString();
	}
	
}
