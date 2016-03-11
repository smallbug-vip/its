package org.hpc.its.realize.entities.a;

import java.util.ArrayList;
import java.util.List;

import org.hpc.its.realize.entities.abs.AbstractCar;

@SuppressWarnings("serial")
public class Car extends AbstractCar {

	private Long carId;// car id
	private int speed;// car speed
	private String image; // car image
	private int width; // car width
	private int height;// car height
	private int angle;// image rotation angle
	private List<Road> roads = new ArrayList<>();// car path
	private int count = 0;// current path

	private int startDistance = 0;// length from the end of the road at start
	private float endDistance = 0;// length from the end of the road at end

	private int currentXPoint;// current x coordinate
	private int currentYPoint;// current y coordinate
	private long time = 0;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public synchronized int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public synchronized List<Road> getRoads() {
		return roads;
	}

	public synchronized int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public synchronized int getStartDistance() {
		return startDistance;
	}

	public void setStartDistance(int startDistance) {
		this.startDistance = startDistance;
	}

	public synchronized float getEndDistance() {
		return endDistance;
	}

	public synchronized void setEndDistance(float endDistance) {
		this.endDistance = endDistance;
	}

	public synchronized int getCurrentXPoint() {
		return currentXPoint;
	}

	public synchronized void setCurrentXPoint(int currentXPoint) {
		this.currentXPoint = currentXPoint;
	}

	public synchronized int getCurrentYPoint() {
		return currentYPoint;
	}

	public synchronized void setCurrentYPoint(int currentYPoint) {
		this.currentYPoint = currentYPoint;
	}

	public synchronized int getAngle() {
		return angle;
	}

	public synchronized void setAngle(int angle) {
		this.angle = angle;
	}

}
