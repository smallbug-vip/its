package edu.hpc.its.area.core;

import edu.hpc.its.area.Car;

/**
 * 车
 * 
 * @timestamp Feb 13, 2016 4:12:17 PM
 * @author smallbug
 */
public class StandardCar extends StandardEntity implements Car {

	/**
	 * 
	 */
	private static final long serialVersionUID = -896347064968801518L;

	private String image;// 车图
	private StandardRoute standardRoute;// 当前行程
	private StandardLane standardLane;// 当前车道
	private boolean horizontal;// 是否横向

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public StandardRoute getStandardRouts() {
		return standardRoute;
	}

	public void setStandardRouts(StandardRoute standardRoute) {
		this.standardRoute = standardRoute;
	}

	public StandardLane getStandardLane() {
		return standardLane;
	}

	public void setStandardLane(StandardLane standardLane) {
		this.standardLane = standardLane;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

}
