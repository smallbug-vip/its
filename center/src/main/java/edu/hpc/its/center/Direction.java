package edu.hpc.its.center;

/**
 * 东南西北四个方向
 * 
 * @timestamp Feb 18, 2016 10:41:55 PM
 * @author smallbug
 */
public enum Direction {

	NORTH("north"), SOUTH("south"), WEST("west"), EAST("east");

	private String str;

	private Direction(String str) {
		this.str = str;
	}

	public String getDirection() {
		return this.str;
	}
}
