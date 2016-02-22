package edu.hpc.its.area.core;

/**
 * 东南西北四个方向
 * 
 * @timestamp Feb 18, 2016 10:41:55 PM
 * @author smallbug
 */
public enum Direction {

	NORTH(1), // 北
	SOUTH(2), // 南
	WEST(3), // 西
	EAST(4);// 东

	private int i;

	private Direction(int i) {
		this.i = i;
	}

	public int getDirection() {
		return i;
	}

}
