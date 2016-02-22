package edu.hpc.its.area.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Cross;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.factory.StandardEntityFactory;

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

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING);
		for (Entry<Direction, StandardLight> light : lights.entrySet()) {
			light.getValue().start();
		}
		for (Entry<Direction, StandardRoad> road : roads.entrySet()) {
			road.getValue().start();
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
		// 初始化坐标
		xxPoint = Constant.ROADREALITYLENGTH * horizontalNum;
		yyPoint = Constant.ROADREALITYLENGTH * ordinateNum;
		// 初始化路
		List<StandardRoad> roadList = StandardEntityFactory.getRoads(getId());
		if (roadList != null && roadList.size() > 0) {
			for (StandardRoad road : roadList) {
				if (!StandardEntityFactory.contains(road))
					StandardEntityFactory.addRoad(road);
				else
					road = StandardEntityFactory.getRoad(road.getId());
				// 判断方向
				if (road.getHorizontalNum() == this.horizontalNum && road.getOrdinateNum() == this.ordinateNum) {
					if (road.isHorizontal()) {
						roads.put(Direction.WEST, road);
						if (road.getOneCross() != null) {
							road.setOtherCross(this);
						} else {
							road.setOneCross(this);
						}
						continue;
					} else {
						roads.put(Direction.NORTH, road);
						if (road.getOneCross() != null) {
							road.setOtherCross(this);
						} else {
							road.setOneCross(this);
						}
						continue;
					}
				} else {
					if (road.getHorizontalNum() == this.horizontalNum) {
						roads.put(Direction.SOUTH, road);
						if (road.getOneCross() != null) {
							road.setOtherCross(this);
						} else {
							road.setOneCross(this);
						}
						continue;
					} else {
						roads.put(Direction.EAST, road);
						if (road.getOneCross() != null) {
							road.setOtherCross(this);
						} else {
							road.setOneCross(this);
						}
						continue;
					}
				}
			}
		} else {
			throw new LifecycleException("roads's number is 0!");
		}
		// 初始化灯
		List<StandardLight> lights = StandardEntityFactory.getLight(getId());
		if (lights != null && lights.size() > 0) {
			for (StandardLight light : lights) {
				light.setStandardCross(this);
				this.lights.put(light.getDirection(), light);
				light.init();
			}
		} else {
			throw new LifecycleException("lights's number is 0!");
		}
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.destroyInternal();
	}

	/***************************** BEAN *****************************/

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

	@Override
	public String toString() {
		return "StandardCross [horizontalNum=" + horizontalNum + ", ordinateNum=" + ordinateNum + ", xxPoint=" + xxPoint + ", yyPoint=" + yyPoint + ", standardArea=" + standardArea
				+ "]";
	}

}
