package edu.hpc.its.area.core;

import java.util.HashMap;
import java.util.Map;

import edu.hpc.its.area.Cross;

/**
 * 十字路口
 * 
 * @timestamp Feb 13, 2016 4:11:58 PM
 * @author smallbug
 */
public class StandardCross extends StandardEntity implements Cross {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4788436655613033523L;

	private Integer horizontalNum;// 横数第几个
	private Integer ordinateNum;// 纵数第几个

	private Double xxPoint;// 横坐标
	private Double yyPoint;// 纵坐标
	private Map<Direction, StandardRoad> roads = new HashMap<>();// 各个方向的道路
	private Map<Direction, StandardLight> lights = new HashMap<>();// 各个方向的信号灯

	private StandardArea standardArea;// 所属区域

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

	public Integer getHorizontalNum() {
		return horizontalNum;
	}

	public void setHorizontalNum(Integer horizontalNum) {
		this.horizontalNum = horizontalNum;
	}

	public Integer getOrdinateNum() {
		return ordinateNum;
	}

	public void setOrdinateNum(Integer ordinateNum) {
		this.ordinateNum = ordinateNum;
	}

	public Map<Direction, StandardRoad> getRoads() {
		return roads;
	}

	public void setRoads(Map<Direction, StandardRoad> roads) {
		this.roads = roads;
	}

	public Map<Direction, StandardLight> getLights() {
		return lights;
	}

	public void setLights(Map<Direction, StandardLight> lights) {
		this.lights = lights;
	}

	public StandardArea getStandardArea() {
		return standardArea;
	}

	public void setStandardArea(StandardArea standardArea) {
		this.standardArea = standardArea;
	}

}
