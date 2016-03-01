package edu.hpc.its.area.core;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.CarRun;

/**
 * 车
 * 
 * @timestamp Feb 13, 2016 4:12:17 PM
 * @author smallbug
 */
public class StandardCar extends StandardEntity implements Car, CarRun {

	/**
	 * 
	 */
	private static final long serialVersionUID = -896347064968801518L;

	////////////////////////// Car Run////////////////////////////////////
	private StandardCarRun carRun = new StandardCarRun(this);
	private boolean horizontal;// 是否横向

	private StandardLane currentLane;// 当前车道
	private volatile Double xxPoint;
	private volatile Double yyPoint;// <-横纵坐标

	private volatile Double length;// 取自currentLane，length为0表示路已走完
	private volatile int angle = 0;// 图片旋转的角度
	private StandardRoad[] roads;// 要走那些路，数组长度为0时表示走完
	private Double speed;// 速度
	private StandardCross nextCross;

	private String routeString;// 行车路线
	private Long roadTime = 0L;
	private Long allTime = 0L;

	@Override
	public void run() {
		try {
			carRun.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////////////////////// BEAN////////////////////////////////////
	private String image;// 车图片
	private Long areaId;

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public StandardLane getCurrentLane() {
		return currentLane;
	}

	public void setCurrentLane(StandardLane currentLane) {
		this.currentLane = currentLane;
	}

	public Double getXxPoint() {
		return xxPoint;
	}

	public void setXxPoint(Double xxPoint) {
		this.xxPoint = xxPoint;
	}

	public Double getYyPoint() {
		return yyPoint;
	}

	public void setYyPoint(Double yyPoint) {
		this.yyPoint = yyPoint;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public StandardRoad[] getRoads() {
		return roads;
	}

	public void setRoads(StandardRoad[] roads) {
		this.roads = roads;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public StandardCross getNextCross() {
		return nextCross;
	}

	public void setNextCross(StandardCross nextCross) {
		this.nextCross = nextCross;
	}

	public Long getRoadTime() {
		return roadTime;
	}

	public void setRoadTime(Long roadTime) {
		this.roadTime = roadTime;
	}

	public Long getAllTime() {
		return allTime;
	}

	public void setAllTime(Long allTime) {
		this.allTime = allTime;
	}

	public String getRouteString() {
		return routeString;
	}

	public void setRouteString(String routeString) {
		this.routeString = routeString;
	}

	@Override
	public String toString() {
		return "StandardCar [carRun=" + carRun + ", horizontal=" + horizontal + ", xxPoint=" + xxPoint + ", yyPoint=" + yyPoint + ", length=" + length + ", angle=" + angle
				+ ", speed=" + speed + ", image=" + image + ", areaId=" + areaId + "]";
	}

}
