package edu.hpc.its.area.core;

import edu.hpc.its.area.Lane;

/**
 * 车道
 * 
 * @timestamp Feb 16, 2016 6:15:19 PM
 * @author smallbug
 */
public class StandardLane extends StandardEntity implements Lane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5438528168585936295L;

	private Double length;// 道长
	private Double width;// 道宽

	private Double realityLength;// 实际道长
	private Double realityWidth;// 实际道宽

	private int number;// 几号车道
	private StandardRoad standardRoad;// 所属路

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getRealityLength() {
		return realityLength;
	}

	public void setRealityLength(Double realityLength) {
		this.realityLength = realityLength;
	}

	public Double getRealityWidth() {
		return realityWidth;
	}

	public void setRealityWidth(Double realityWidth) {
		this.realityWidth = realityWidth;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public StandardRoad getStandardRoad() {
		return standardRoad;
	}

	public void setStandardRoad(StandardRoad standardRoad) {
		this.standardRoad = standardRoad;
	}

}
