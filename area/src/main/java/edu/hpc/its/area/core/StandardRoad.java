package edu.hpc.its.area.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Cross;
import edu.hpc.its.area.Lane;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.Road;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.factory.StandardEntityFactory;

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

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING);
		try {
			for (Entry<Integer, Lane> one : oneLane.entrySet()) {
				one.getValue().start();
			}
			for (Entry<Integer, Lane> other : otherLane.entrySet()) {
				other.getValue().start();
			}
		} catch (Exception e) {
			throw new LifecycleException(e);
		}
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.stopInternal();
	}

	@Override
	protected void initInternal() throws LifecycleException {
		super.initInternal();
		double laneWidth = 0;// 一条路上单向车道的宽度总和
		List<StandardLane> lanes = StandardEntityFactory.getLanes(getId());
		for (StandardLane lane : lanes) {
			lane.setStandardRoad(this);// 建立路和道的对应关系
			if (lane.getNumber() > 0) {
				laneWidth += lane.getRealityWidth() / Constant.COMPRESS * Constant.ONECM;
				oneLane.put(lane.getNumber(), lane);
			} else {
				otherLane.put(Math.abs(lane.getNumber()), lane);
			}
		}
		try {
			StandardCross oneLinkCross = (StandardCross) this.getOneCross();
			StandardCross otherLinkCross = (StandardCross) this.getOtherCross();// 获取两个路口
			// 初始化坐标
			if (this.isHorizontal()) {// 如果路为东西向
				this.setYyOne(Constant.ROADREALITYLENGTH * this.ordinateNum);
				this.setYyOther(Constant.ROADREALITYLENGTH * this.ordinateNum);// 纵坐标相同
				if (this.horizontalNum == 1) {// 如果该路西向没有路口
					this.setXxOne(0.0);
					this.setXxOther(oneLinkCross.getXxPoint() - laneWidth);
					this.setLength(this.getXxOther() - this.getXxOne());
				} else if (this.horizontalNum == (Constant.HORIZONTALNUM + 1)) {// 如果该路东向没有路口
					this.setXxOne(Constant.ROADREALITYLENGTH * (Constant.HORIZONTALNUM + 1));
					this.setXxOther(oneLinkCross.getXxPoint() + laneWidth);
					this.setLength(this.getXxOne() - this.getXxOther());
				} else {// 路两侧都有路口
					if (oneLinkCross.getHorizontalNum() > otherLinkCross.getHorizontalNum()) {
						this.setXxOne(oneLinkCross.getXxPoint() - laneWidth);
						this.setXxOther(otherLinkCross.getXxPoint() + laneWidth);
						this.setLength(this.getXxOne() - this.getXxOther());
					} else {
						this.setXxOne(oneLinkCross.getXxPoint() + laneWidth);
						this.setXxOther(otherLinkCross.getXxPoint() - laneWidth);
						this.setLength(this.getXxOther() - this.getXxOne());
					}
				}
			} else {// 如果路为西北向
				this.setXxOne(Constant.ROADREALITYLENGTH * this.horizontalNum);
				this.setXxOther(Constant.ROADREALITYLENGTH * this.horizontalNum);
				if (this.ordinateNum == 1) {// 如果该路北向没有路口
					this.setYyOne(0.0);
					this.setYyOther(oneLinkCross.getYyPoint() - laneWidth);
					this.setLength(this.getYyOther() - this.getYyOne());
				} else if (this.ordinateNum == (Constant.VERTICALNUM + 1)) {// 如果该路南向没有路口
					this.setYyOne(Constant.ROADREALITYLENGTH * (Constant.VERTICALNUM + 1));
					this.setYyOther(oneLinkCross.getYyPoint() + laneWidth);
					this.setLength(this.getYyOne() - this.getYyOther());
				} else {// 路两侧都有路口
					if (oneLinkCross.getOrdinateNum() > otherLinkCross.getOrdinateNum()) {
						this.setYyOne(oneLinkCross.getYyPoint() - laneWidth);
						this.setYyOther(otherLinkCross.getYyPoint() + laneWidth);
						this.setLength(this.getYyOne() - this.getYyOther());
					} else {
						this.setYyOne(oneLinkCross.getYyPoint() + laneWidth);
						this.setYyOther(otherLinkCross.getYyPoint() - laneWidth);
						this.setLength(this.getYyOther() - this.getYyOne());
					}
				}
			}
			// 初始化车道
			
			for (StandardLane lane : lanes) {
				lane.init();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LifecycleException(e);
		}
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.destroyInternal();
	}

	/***************************** BEAN *****************************/
	private int horizontalNum;// 横数第几个
	private int ordinateNum;// 纵数第几个

	private boolean horizontal;// 是否横向
	private boolean open;// 是否与其他区域搭界

	private Double length;// 路长
	private Double width;// 路宽

	private Double xxOne;// 横坐标
	private Double yyOne;// 纵坐标<一端坐标
	private Double xxOther;// 纵坐标
	private Double yyOther;// 纵坐标<-另一端坐标

	private Double realityLength;// 实际路长
	private Double realityWidth;// 实际路宽

	private int troops;// 进入第几个循环队列

	private Cross oneCross;// 路一端的路口
	private Cross otherCross;// 路另一端的路口

	private Map<Integer, Lane> oneLane = new HashMap<>();// 路一边车道,Integer代表几号车道
	private Map<Integer, Lane> otherLane = new HashMap<>();// 路另一边车道

	public Double getXxOne() {
		return xxOne;
	}

	public void setXxOne(Double xxOne) {
		this.xxOne = xxOne;
	}

	public Double getYyOne() {
		return yyOne;
	}

	public void setYyOne(Double yyOne) {
		this.yyOne = yyOne;
	}

	public Double getXxOther() {
		return xxOther;
	}

	public void setXxOther(Double xxOther) {
		this.xxOther = xxOther;
	}

	public Double getYyOther() {
		return yyOther;
	}

	public void setYyOther(Double yyOther) {
		this.yyOther = yyOther;
	}

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

	@Override
	public String toString() {
		return "StandardRoad [horizontalNum=" + horizontalNum + ", ordinateNum=" + ordinateNum + ", horizontal=" + horizontal + ", open=" + open + ", length=" + length + ", width="
				+ width + ", xxOne=" + xxOne + ", yyOne=" + yyOne + ", xxOther=" + xxOther + ", yyOther=" + yyOther + ", realityLength=" + realityLength + ", realityWidth="
				+ realityWidth + ", troops=" + troops + "]";
	}

}
