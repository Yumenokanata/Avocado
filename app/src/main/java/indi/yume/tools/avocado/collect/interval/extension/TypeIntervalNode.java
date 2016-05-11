package indi.yume.tools.avocado.collect.interval.extension;

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
public class TypeIntervalNode<VALUE extends Comparable<VALUE>, Type> {

	private SortedMap<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>> intervals;
	private VALUE center;
	private TypeIntervalNode<VALUE, Type> leftNode;
	private TypeIntervalNode<VALUE, Type> rightNode;

	public TypeIntervalNode() {
		intervals = new TreeMap<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>>();
		center = null;
		leftNode = null;
		rightNode = null;
	}

	public TypeIntervalNode(List<TypeInterval<VALUE, Type>> intervalList) {

		intervals = new TreeMap<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>>();

		SortedSet<VALUE> endpoints = new TreeSet<VALUE>();

		for(TypeInterval<VALUE, Type> interval: intervalList) {
			endpoints.add(interval.getStart());
			endpoints.add(interval.getEnd());
		}

		VALUE median = getMedian(endpoints);
		center = median;

		List<TypeInterval<VALUE, Type>> left = new ArrayList<TypeInterval<VALUE, Type>>();
		List<TypeInterval<VALUE, Type>> right = new ArrayList<TypeInterval<VALUE, Type>>();

		for(TypeInterval<VALUE, Type> interval : intervalList) {
			if(interval.getEnd().compareTo(median) < 0)
				left.add(interval);
			else if(interval.getStart().compareTo(median) > 0)
				right.add(interval);
			else {
				List<TypeInterval<VALUE, Type>> posting = intervals.get(interval);
				if(posting == null) {
					posting = new ArrayList<TypeInterval<VALUE, Type>>();
					intervals.put(interval, posting);
				}
				posting.add(interval);
			}
		}

		if(left.size() > 0)
			leftNode = new TypeIntervalNode<VALUE, Type>(left);
		if(right.size() > 0)
			rightNode = new TypeIntervalNode<VALUE, Type>(right);
	}

	/**
	 * Perform a stabbing query on the node
	 * @param time the time to query at
	 * @return	   all intervals containing time
	 */
	public List<TypeInterval<VALUE, Type>> stab(VALUE time) {
		List<TypeInterval<VALUE, Type>> result = new ArrayList<TypeInterval<VALUE, Type>>();

		for(Entry<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>> entry : intervals.entrySet()) {
			if(entry.getKey().contains(time))
				for(TypeInterval<VALUE, Type> interval : entry.getValue())
					result.add(interval);
			else if(entry.getKey().getStart().compareTo(time) > 0)
				break;
		}

		if(time.compareTo(center) < 0 && leftNode != null)
			result.addAll(leftNode.stab(time));
		else if(time.compareTo(center) > 0 && rightNode != null)
			result.addAll(rightNode.stab(time));
		return result;
	}

	/**
	 * Perform an interval intersection query on the node
	 * @param target the interval to intersect
	 * @return		   all intervals containing time
	 */
	public List<TypeInterval<VALUE, Type>> query(TypeInterval<VALUE, ?> target) {
		List<TypeInterval<VALUE, Type>> result = new ArrayList<TypeInterval<VALUE, Type>>();

		for(Entry<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>> entry : intervals.entrySet()) {
			if(entry.getKey().intersects(target))
				for(TypeInterval<VALUE, Type> interval : entry.getValue())
					result.add(interval);
			else if(entry.getKey().getStart().compareTo(target.getEnd()) > 0)
				break;
		}

		if(target.getStart().compareTo(center) < 0 && leftNode != null)
			result.addAll(leftNode.query(target));
		if(target.getEnd().compareTo(center) > 0 && rightNode != null)
			result.addAll(rightNode.query(target));
		return result;
	}

	public VALUE getCenter() {
		return center;
	}

	public void setCenter(VALUE center) {
		this.center = center;
	}

	public TypeIntervalNode<VALUE, Type> getLeft() {
		return leftNode;
	}

	public void setLeft(TypeIntervalNode<VALUE, Type> left) {
		this.leftNode = left;
	}

	public TypeIntervalNode<VALUE, Type> getRight() {
		return rightNode;
	}

	public void setRight(TypeIntervalNode<VALUE, Type> right) {
		this.rightNode = right;
	}

	/**
	 * @param set the set to look on
	 * @return	  the median of the set, not interpolated
	 */
	private VALUE getMedian(SortedSet<VALUE> set) {
		int i = 0;
		int middle = set.size() / 2;
		for(VALUE point : set) {
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
		for(Entry<TypeInterval<VALUE, Type>, List<TypeInterval<VALUE, Type>>> entry : intervals.entrySet()) {
			sb.append("[" + entry.getKey().getStart() + "," + entry.getKey().getEnd() + "]:{");
			for(TypeInterval<VALUE, Type> interval : entry.getValue()) {
				sb.append("("+interval.getStart()+","+interval.getEnd()+","+interval.getData()+")");
			}
			sb.append("} ");
		}
		return sb.toString();
	}
	
}
