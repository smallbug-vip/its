package edu.hpc.its.area.rule;

import org.apache.log4j.Logger;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.Constant;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.dao.mongodb.CarExpeInfo;
import edu.hpc.its.area.dao.mongodb.CarExpeInfoImpl;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 判断是否需要换路
 * 
 * @timestamp Feb 17, 2016 12:18:10 AM
 * @author smallbug
 */
public class ChangeRoadRule extends BaseRule {

	private CarExpeInfo info = new CarExpeInfoImpl();
	private Logger log = Logger.getLogger(ChangeRoadRule.class);

	@Override
	public void invoke() {
		if (getEntity() instanceof Car) {
			StandardCar car = (StandardCar) getEntity();// 当前车辆

			if (car.getLength() <= 0) {// 换路条件
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
						car.setNextCross(cross);

						/////////////////////////// 换车道
						Long roadTime = car.getRoadTime();
						if (Constant.ISNOTEINFO != 0) {
							info.insertRoadTime(Constant.EXPID, car.getCurrentLane().getStandardRoad().getId(), //
									car.getId(), System.currentTimeMillis() - roadTime);// 记录车在这条路上花费的时间
						}
						car.setRoadTime(System.currentTimeMillis());// 重新设置当前时间

						car.getCurrentLane().removeCar(car);
						if (roads[i + 1].isHorizontal()) {
							if (roads[i].getXxOne() - roads[i + 1].getXxOne() > 0) {// 向西走
								car.setCurrentLane((StandardLane) roads[i + 1].getOtherLane().get(Math.abs(car.getCurrentLane().getNumber())));
							} else {// 向东走
								car.setCurrentLane((StandardLane) roads[i + 1].getOneLane().get(Math.abs(car.getCurrentLane().getNumber())));
							}
						} else {
							if (roads[i].getYyOne() - roads[i + 1].getYyOne() > 0) {// 向北
								car.setCurrentLane((StandardLane) roads[i + 1].getOneLane().get(Math.abs(car.getCurrentLane().getNumber())));
							} else {
								car.setCurrentLane(// 向南走
										(StandardLane) roads[i + 1].getOtherLane().get(Math.abs(car.getCurrentLane().getNumber())));
							}
						}
						car.getCurrentLane().addCar(car);
						/////////////////////////// 设置是否横向
						car.setHorizontal(car.getCurrentLane().getStandardRoad().isHorizontal());
						/////////////////////////// 设置Length
						car.setLength(car.getCurrentLane().getLength());
						/////////////////////////// 设置初始坐标
						if (car.getCurrentLane().getStandardRoad().getOtherCross() != null) {
							if (car.getCurrentLane().getStandardRoad().getOneCross() == cross) {
								car.setXxPoint(car.getCurrentLane().getXxOne());
								car.setYyPoint(car.getCurrentLane().getYyOne());
							} else {
								car.setXxPoint(car.getCurrentLane().getXxOther());
								car.setYyPoint(car.getCurrentLane().getYyOther());
							}
						} else {
							car.setXxPoint(car.getCurrentLane().getXxOther());
							car.setYyPoint(car.getCurrentLane().getYyOther());
						}
						return;
					}
				}

				log.info("The car id  is -> " + car.getId() + " come out !");
				if (Constant.ISNOTEINFO != 0) {
					info.insertRoadTime(Constant.EXPID, car.getCurrentLane().getStandardRoad().getId(), //
							car.getId(), System.currentTimeMillis() - car.getRoadTime());// 记录车在这条路上花费的时间
					info.insertAppearTime(Constant.EXPID, car.getId(), System.currentTimeMillis() - car.getAllTime());// 记录车在本次实验中出现的时间
				}
				StandardEntityFactory.removeCar(car);
				car.getCurrentLane().removeCar(car);
				car.setCurrentLane(null);
				car.setNextCross(null);
				car.setRoads(null);
				return;

			}
			getNext().invoke();
		}
	}

}
