package edu.hpc.its.area.core;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.Light;
import edu.hpc.its.area.exception.LifecycleException;

/**
 * 信号灯
 * 
 * @timestamp Feb 13, 2016 4:17:08 PM
 * @author smallbug
 */
public class StandardLight extends StandardEntity implements Light {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6798525560369598671L;

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
		double part = Constant.LANENUM * (Constant.LANEWIDE / Constant.COMPRESS * Constant.ONECM);
		try {
			// 初始化坐标
			if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH) {
				setOneX(centerX - part);
				setOneY(centerY);
				setOtherX(centerX + part);
				setOtherY(centerY);
			} else {
				setOneX(centerX);
				setOneY(centerY - part);
				setOtherX(centerX);
				setOtherY(centerY + part);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.destroyInternal();
	}

	/***************************** BEAN *****************************/
	private Double size;// 灯线长
	private int lightState = 0;// 状态 0:红,1:绿

	private Double centerX;// 灯的横坐标
	private Double centerY;// 灯的纵坐标

	private Double oneX;
	private Double oneY;
	private Double otherX;
	private Double otherY;// 四个字段用来画灯线

	private Direction direction;

	private volatile Double red;// 红灯亮多长时间
	private volatile Double green;// 绿灯亮多长时间

	private StandardCross standardCross;// 所属的路口

	// @SuppressWarnings("unused")
	// private int sqldirec;// mabatis转换枚举类型使用
	// public void setSqldirec(int sqldirec) {
	// for (Direction d : Direction.values()) {
	// if (d.getDirection() == sqldirec) {
	// setDirection(d);
	// break;
	// }
	// }
	// }被mybatis自定义类型转换器取代

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public int getLightState() {
		return lightState;
	}

	public void setLightState(int lightState) {
		this.lightState = lightState;
	}

	public Double getCenterX() {
		return centerX;
	}

	public void setCenterX(Double centerX) {
		this.centerX = centerX;
	}

	public Double getCenterY() {
		return centerY;
	}

	public void setCenterY(Double centerY) {
		this.centerY = centerY;
	}

	public Double getOneX() {
		return oneX;
	}

	public void setOneX(Double oneX) {
		this.oneX = oneX;
	}

	public Double getOneY() {
		return oneY;
	}

	public void setOneY(Double oneY) {
		this.oneY = oneY;
	}

	public Double getOtherX() {
		return otherX;
	}

	public void setOtherX(Double otherX) {
		this.otherX = otherX;
	}

	public Double getOtherY() {
		return otherY;
	}

	public void setOtherY(Double otherY) {
		this.otherY = otherY;
	}

	public Double getRed() {
		return red;
	}

	public void setRed(Double red) {
		this.red = red;
	}

	public Double getGreen() {
		return green;
	}

	public void setGreen(Double green) {
		this.green = green;
	}

	public StandardCross getStandardCross() {
		return standardCross;
	}

	public void setStandardCross(StandardCross standardCross) {
		this.standardCross = standardCross;
	}

	@Override
	public String toString() {
		return "StandardLight [size=" + size + ", lightState=" + lightState + ", centerX=" + centerX + ", centerY=" + centerY + ", oneX=" + oneX + ", oneY=" + oneY + ", otherX="
				+ otherX + ", otherY=" + otherY + ", direction=" + direction + ", red=" + red + ", green=" + green + "]";
	}

}
