package org.hpc.its.realize.entities.a;

import org.hpc.its.realize.entities.abs.AbstractLight;

@SuppressWarnings("serial")
public class Light extends AbstractLight {

	private Long lightId;
	// the line length
	private int size = 10;
	// 0 red, 1 green, 2 yellow, ...
	private int state = 0;

	private int centerXPoint = 0;
	private int centerYPoint = 0;

	// the road angle
	private int angle = 0;
	// 0 the road start port, 1 the road end port
	private int port = 0;

	private int leftXPoint;
	private int leftYPoint;

	private int rightXPoint;
	private int rightYPoint;

	private int group;
	private int red;
	private int green;

	public void initCoordinate() {
		leftXPoint = centerXPoint + (int) (size * Math.sin(Math.PI * angle / 180));
		leftYPoint = centerYPoint - (int) (size * Math.cos(Math.PI * angle / 180));
		rightXPoint = centerXPoint - (int) (size * Math.sin(Math.PI * angle / 180));
		rightYPoint = centerYPoint + (int) (size * Math.cos(Math.PI * angle / 180));
	}

	public Long getLightId() {
		return lightId;
	}

	public void setLightId(Long lightId) {
		this.lightId = lightId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public synchronized int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCenterXPoint() {
		return centerXPoint;
	}

	public void setCenterXPoint(int centerXPoint) {
		this.centerXPoint = centerXPoint;
	}

	public int getCenterYPoint() {
		return centerYPoint;
	}

	public void setCenterYPoint(int centerYPoint) {
		this.centerYPoint = centerYPoint;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getLeftXPoint() {
		return leftXPoint;
	}

	public void setLeftXPoint(int leftXPoint) {
		this.leftXPoint = leftXPoint;
	}

	public int getLeftYPoint() {
		return leftYPoint;
	}

	public void setLeftYPoint(int leftYPoint) {
		this.leftYPoint = leftYPoint;
	}

	public int getRightXPoint() {
		return rightXPoint;
	}

	public void setRightXPoint(int rightXPoint) {
		this.rightXPoint = rightXPoint;
	}

	public int getRightYPoint() {
		return rightYPoint;
	}

	public void setRightYPoint(int rightYPoint) {
		this.rightYPoint = rightYPoint;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

}
