package org.hpc.its.realize.entities.a;

import org.hpc.its.realize.entities.abs.AbstractRoad;

/**
 * initialize road
 * 
 * @timestamp Nov 12, 2015 5:46:36 PM
 * @author smallbug
 */
@SuppressWarnings("serial")
public class Road extends AbstractRoad {

	private Long roadId;

	// start coordinate
	private int startXPoint;
	private int startYPoint;

	// length of road
	private int length;

	private int reduce = 10;
	// angle of inclination
	private int angle;

	// end coordinate
	private int endXPoint;
	private int endYPoint;

	// draw start coordinate
	private int drawStartXPoint;
	private int drawStartYPoint;

	// draw end coordinate
	private int drawEndXPoint;
	private int drawEndYPoint;

	private Light startLight;
	private Light endLight;

	// initialize road
	public Road(int startXPoint, int startYPoint, int length, int angle) {
		super();
		this.startXPoint = startXPoint;
		this.startYPoint = startYPoint;
		this.length = length;
		this.angle = angle;
		// generate end point
		endXPoint = startXPoint + (int) (length * Math.cos(Math.PI * angle / 180));
		endYPoint = startYPoint + (int) (length * Math.sin(Math.PI * angle / 180));
		initDrawPoint();
	}

	private void initDrawPoint() {
		if (endYPoint - startYPoint == 0) {
			if (endXPoint - startXPoint > 0) {
				drawStartXPoint = startXPoint + reduce;
				drawStartYPoint = startYPoint;
				drawEndXPoint = endXPoint - reduce;
				drawEndYPoint = endYPoint;
			} else {
				drawStartXPoint = startXPoint - reduce;
				drawStartYPoint = startYPoint;
				drawEndXPoint = endXPoint + reduce;
				drawEndYPoint = endYPoint;
			}
			return;
		} else if (startXPoint - endXPoint == 0) {
			if (endYPoint - startYPoint > 0) {
				drawStartXPoint = startXPoint;
				drawStartYPoint = startYPoint + reduce;
				drawEndXPoint = endXPoint;
				drawEndYPoint = endYPoint - reduce;
			} else {
				drawStartXPoint = startXPoint;
				drawStartYPoint = startYPoint - reduce;
				drawEndXPoint = endXPoint;
				drawEndYPoint = endYPoint + reduce;
			}
			return;
		} else if (startXPoint > endXPoint) {
			if (endYPoint > startYPoint) {
				drawStartXPoint = //
				startXPoint - (int) (reduce * Math.cos(Math.PI * (180 - angle) / 180));
				drawStartYPoint = //
				startYPoint + (int) (reduce * Math.sin(Math.PI * (180 - angle) / 180));
				drawEndXPoint = //
				endXPoint + (int) (reduce * Math.cos(Math.PI * (180 - angle) / 180));
				drawEndYPoint = //
				endYPoint - (int) (reduce * Math.sin(Math.PI * (180 - angle) / 180));
			} else {
				drawStartXPoint = startXPoint - (int) (reduce * Math.cos(Math.PI * angle / 180));
				drawStartYPoint = startYPoint - (int) (reduce * Math.sin(Math.PI * angle / 180));
				drawEndXPoint = endXPoint + (int) (reduce * Math.cos(Math.PI * angle / 180));
				drawEndYPoint = endYPoint + (int) (reduce * Math.sin(Math.PI * angle / 180));
			}
			return;
		} else {
			if (endYPoint > startYPoint) {
				drawEndXPoint = endXPoint - (int) (reduce * Math.cos(Math.PI * angle / 180));
				drawEndYPoint = endYPoint - (int) (reduce * Math.sin(Math.PI * angle / 180));
				drawStartXPoint = startXPoint + (int) (reduce * Math.cos(Math.PI * angle / 180));
				drawStartYPoint = startYPoint + (int) (reduce * Math.sin(Math.PI * angle / 180));
			} else {
				drawEndXPoint = //
				endXPoint - (int) (reduce * Math.cos(Math.PI * (180 - angle) / 180));
				drawEndYPoint = //
				endYPoint + (int) (reduce * Math.sin(Math.PI * (180 - angle) / 180));
				drawStartXPoint = //
				startXPoint + (int) (reduce * Math.cos(Math.PI * (180 - angle) / 180));
				drawStartYPoint = //
				startYPoint - (int) (reduce * Math.sin(Math.PI * (180 - angle) / 180));
			}
			return;
		}
	}

	// ************************* get/set **************************//

	public synchronized int getAngle() {
		return angle;
	}

	public synchronized int getStartXPoint() {
		return startXPoint;
	}

	public void setStartXPoint(int startXPoint) {
		this.startXPoint = startXPoint;
	}

	public synchronized int getStartYPoint() {
		return startYPoint;
	}

	public void setStartYPoint(int startYPoint) {
		this.startYPoint = startYPoint;
	}

	public synchronized int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Long getRoadId() {
		return roadId;
	}

	public void setRoadId(Long roadId) {
		this.roadId = roadId;
	}

	public int getDrawStartXPoint() {
		return drawStartXPoint;
	}

	public void setDrawStartXPoint(int drawStartXPoint) {
		this.drawStartXPoint = drawStartXPoint;
	}

	public int getDrawStartYPoint() {
		return drawStartYPoint;
	}

	public void setDrawStartYPoint(int drawStartYPoint) {
		this.drawStartYPoint = drawStartYPoint;
	}

	public int getDrawEndXPoint() {
		return drawEndXPoint;
	}

	public void setDrawEndXPoint(int drawEndXPoint) {
		this.drawEndXPoint = drawEndXPoint;
	}

	public int getDrawEndYPoint() {
		return drawEndYPoint;
	}

	public void setDrawEndYPoint(int drawEndYPoint) {
		this.drawEndYPoint = drawEndYPoint;
	}

	public synchronized int getReduce() {
		return reduce;
	}

	public synchronized void setReduce(int reduce) {
		this.reduce = reduce;
	}

	public synchronized Light getStartLight() {
		return startLight;
	}

	public void setStartLight(Light startLight) {
		this.startLight = startLight;
	}

	public synchronized Light getEndLight() {
		return endLight;
	}

	public void setEndLight(Light endLight) {
		this.endLight = endLight;
	}

}
