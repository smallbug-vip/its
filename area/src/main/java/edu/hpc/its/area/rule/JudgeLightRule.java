package edu.hpc.its.area.rule;

import java.util.Map;
import java.util.Map.Entry;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.core.Direction;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.core.StandardRoad;

/**
 * 根据红绿灯判断是否需要停车
 * 
 * @timestamp Feb 17, 2016 12:16:37 AM
 * @author smallbug
 */
public class JudgeLightRule extends BaseRule {

	@Override
	public void invoke() {
		if (getEntity() instanceof Car) {
			StandardCar car = (StandardCar) getEntity();// 当前车辆
			if (car.getLength() <= 0) {// 观察信号灯条件
				StandardRoad[] roads = car.getRoads();// 整个行程
				for (int i = 0; i < roads.length - 1; i++) {
					if (roads[i] == car.getCurrentLane().getStandardRoad()) {// 找到当前路在整个行程的位置
						StandardCross cross = null;// 计算交叉路口
						if (roads[i].getOtherCross() == null) {
							cross = (StandardCross) roads[i].getOneCross();
						} else {
							if (roads[i].getOneCross() == roads[i + 1].getOneCross()) {
								cross = (StandardCross) roads[i].getOneCross();
							} else if (roads[i].getOneCross() == roads[i + 1].getOtherCross()) {
								cross = (StandardCross) roads[i].getOneCross();
							} else if (roads[i].getOtherCross() == roads[i + 1].getOneCross()) {
								cross = (StandardCross) roads[i].getOtherCross();
							} else if (roads[i].getOtherCross() == roads[i + 1].getOtherCross()) {
								cross = (StandardCross) roads[i].getOtherCross();
							}
						}
						if (cross != null) {
							Map<Direction, StandardRoad> roadMap = cross.getRoads();
							for (Entry<Direction, StandardRoad> road : roadMap.entrySet()) {
								if (road.getValue() == roads[i + 1]) {
									StandardLight light = cross.getLights().get(road.getKey());
									if (light.getLightState() > 0) {
										getNext().invoke();
									} else {
										return;
									}
								}
							}
						}
					} // if end
				} // for end
				getNext().invoke();
			} else {
				getNext().invoke();
			}
		}
	}

}
