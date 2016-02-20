package edu.hpc.its.area.core;

import java.util.HashMap;
import java.util.Map;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.Lane;
import edu.hpc.its.area.Road;

/**
 * 路
 * 
 * @timestamp Feb 13, 2016 4:17:42 PM
 * @author smallbug
 */
public class StandardRoad extends StandardEntity implements Road {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7660218726060235973L;

	private int horizontalNum;// 横数第几个
	private int ordinateNum;// 纵数第几个

	private boolean horizontal;// 是否横向
	private boolean open;// 是否与其他区域搭界

	private Double length;// 路长
	private Double width;// 路宽

	private Double realityLength;// 实际路长
	private Double realityWidth;// 实际路宽

	private int troops;// 进入第几个循环队列

	private Cross oneCross;// 路一端的路口
	private Cross otherCross;// 路另一端的路口

	private Map<Integer, Lane> oneLane = new HashMap<>();// 路一边车道,Integer代表几号车道
	private Map<Integer, Lane> otherLane = new HashMap<>();// 路另一边车道

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getHorizontalNum() {
		return horizontalNum;
	}

	public void setHorizontalNum(int horizontalNum) {
		this.horizontalNum = horizontalNum;
	}

	public int getOrdinateNum() {
		return ordinateNum;
	}

	public void setOrdinateNum(int ordinateNum) {
		this.ordinateNum = ordinateNum;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getRealityLength() {
		return realityLength;
	}

	public void setRealityLength(Double realityLength) {
		this.realityLength = realityLength;
	}

	public Double getRealityWidth() {
		return realityWidth;
	}

	public void setRealityWidth(Double realityWidth) {
		this.realityWidth = realityWidth;
	}

	public int getTroops() {
		return troops;
	}

	public void setTroops(int troops) {
		this.troops = troops;
	}

	public Cross getOneCross() {
		return oneCross;
	}

	public void setOneCross(Cross oneCross) {
		this.oneCross = oneCross;
	}

	public Cross getOtherCross() {
		return otherCross;
	}

	public void setOtherCross(Cross otherCross) {
		this.otherCross = otherCross;
	}

	public Map<Integer, Lane> getOneLane() {
		return oneLane;
	}

	public void setOneLane(Map<Integer, Lane> oneLane) {
		this.oneLane = oneLane;
	}

	public Map<Integer, Lane> getOtherLane() {
		return otherLane;
	}

	public void setOtherLane(Map<Integer, Lane> otherLane) {
		this.otherLane = otherLane;
	}

}
