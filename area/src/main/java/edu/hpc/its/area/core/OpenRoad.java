package edu.hpc.its.area.core;

/**
 * 一个区域与两一个区域交接的路口
 * 
 * @timestamp Feb 18, 2016 3:40:25 PM
 * @author smallbug
 */
public class OpenRoad {

	private Long id;// 开放道路ID

	private int direction;// 方向 1：东 2：南 3：西 4：北
	private int number;// 在该方向的第几个开放的道路

	private StandardRoad road;// 被开放的路

	private StandardArea standardArea;// 该开放路口属于哪个区域

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public StandardRoad getRoad() {
		return road;
	}

	public void setRoad(StandardRoad road) {
		this.road = road;
	}

	public StandardArea getStandardArea() {
		return standardArea;
	}

	public void setStandardArea(StandardArea standardArea) {
		this.standardArea = standardArea;
	}

}
