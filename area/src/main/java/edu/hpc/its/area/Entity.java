package edu.hpc.its.area;

import java.io.Serializable;

/**
 * 实体接口
 * 
 * @timestamp Feb 16, 2016 10:48:46 PM
 * @author smallbug
 */
public interface Entity extends Serializable,Lifecycle{

	public static final String CAR = "standardCar";
	public static final String CROSS = "standardCross";
	public static final String LANE = "standardLane";
	public static final String LIGHT = "standardLight";
	public static final String AREA = "standardArea";
	public static final String ROAD = "standardRoad";
	public static final String ROUTS = "standardRouts";
}
