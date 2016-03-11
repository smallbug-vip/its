package org.hpc.its.realize.entities.a;

import org.hpc.its.realize.entities.abs.AbstractIntersection;

@SuppressWarnings("serial")
public class Intersection extends AbstractIntersection {

	private Long intersectionId;

	private int centerXPoint;
	private int centerYPoint;

	public Long getIntersectionId() {
		return intersectionId;
	}

	public void setIntersectionId(Long intersectionId) {
		this.intersectionId = intersectionId;
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

}
