package edu.hpc.its.area.core;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Lane;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.exception.LifecycleException;

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

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING);
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.stopInternal();
	}

	@Override
	protected void initInternal() throws LifecycleException {
		super.initInternal();
		try {
			setWidth(getRealityWidth() / Constant.COMPRESS * Constant.ONECM);
			setLength(standardRoad.getLength());
			if (standardRoad.isHorizontal()) {
				setXxOne(standardRoad.getXxOne());
				setXxOther(standardRoad.getXxOther());
				setYyOne(standardRoad.getYyOne() + number * getWidth());
				setYyOther(standardRoad.getYyOther() + number * getWidth());
			} else {
				setXxOne(standardRoad.getXxOne() + number * getWidth());
				setXxOther(standardRoad.getXxOther() + number * getWidth());
				setYyOne(standardRoad.getYyOne());
				setYyOther(standardRoad.getYyOther());
			}
		} catch (Exception e) {
			throw new LifecycleException(e);
		}

	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.destroyInternal();
	}

	/***************************** BEAN *****************************/
	private Double length;// 道长
	private Double width;// 道宽

	private Double realityLength;// 实际道长
	private Double realityWidth;// 实际道宽

	private Double xxOne;// 横坐标
	private Double yyOne;// 纵坐标<一端坐标
	private Double xxOther;// 纵坐标
	private Double yyOther;// 纵坐标<-另一端坐标

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

	public Double getXxOne() {
		return xxOne;
	}

	public void setXxOne(Double xxOne) {
		this.xxOne = xxOne;
	}

	public Double getYyOne() {
		return yyOne;
	}

	public void setYyOne(Double yyOne) {
		this.yyOne = yyOne;
	}

	public Double getXxOther() {
		return xxOther;
	}

	public void setXxOther(Double xxOther) {
		this.xxOther = xxOther;
	}

	public Double getYyOther() {
		return yyOther;
	}

	public void setYyOther(Double yyOther) {
		this.yyOther = yyOther;
	}

	@Override
	public String toString() {
		return "StandardLane [length=" + length + ", width=" + width + ", realityLength=" + realityLength + ", realityWidth=" + realityWidth + ", number=" + number + "]";
	}

}
