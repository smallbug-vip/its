package org.hpc.its.realize.entities.a;

import org.hpc.its.realize.entities.abs.AbstractLane;

/**
 * initialize Lane
 * 
 * @timestamp Nov 14, 2015 12:15:57 AM
 * @author smallbug
 */
@SuppressWarnings("serial")
public class Lane extends AbstractLane {

	private Long laneId;

	private int width = 0;
	private int startXPoint = 0;
	private int startYPoint = 0;

	private int endXPoint = 0;
	private int endYPoint = 0;

	public Long getLaneId() {
		return laneId;
	}

	public void setLaneId(Long laneId) {
		this.laneId = laneId;
	}

	public int getStartXPoint() {
		return startXPoint;
	}

	public void setStartXPoint(int startXPoint) {
		this.startXPoint = startXPoint;
	}

	public int getStartYPoint() {
		return startYPoint;
	}

	public void setStartYPoint(int startYPoint) {
		this.startYPoint = startYPoint;
	}

	public synchronized int getEndXPoint() {
		return endXPoint;
	}

	public void setEndXPoint(int endXPoint) {
		this.endXPoint = endXPoint;
	}

	public synchronized int getEndYPoint() {
		return endYPoint;
	}

	public void setEndYPoint(int endYPoint) {
		this.endYPoint = endYPoint;
	}

	public synchronized int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
