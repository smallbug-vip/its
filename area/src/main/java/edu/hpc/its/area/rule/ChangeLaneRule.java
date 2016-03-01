package edu.hpc.its.area.rule;

import java.util.ListIterator;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.Constant;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardLane;

/**
 * 判断是否可以换道
 * 
 * @timestamp Feb 17, 2016 12:14:38 AM
 * @author smallbug
 */
public class ChangeLaneRule extends BaseRule {

	@Override
	public void invoke() {
		if (getEntity() instanceof Car) {
			StandardCar car = (StandardCar) getEntity();
			StandardLane currentLane = car.getCurrentLane();
			if (isChangeRoad(car, currentLane)) {// 不需要换道
				getNext().invoke();
			} else {
				if (Constant.LANENUM == 1) {// 单车道
					return;// 停车等待
				}
				if (currentLane.getNumber() > 0) {
					if (currentLane.getNumber() == 1) {// 在最里面车道
						StandardLane lane1 = (StandardLane) currentLane.getStandardRoad().getOneLane().get(2);
						if (lane1 != null && isChangeRoad(car, lane1)) {// 可以换道
							changeRoad(car, lane1);
							return;
						}
					} else if (currentLane.getNumber() > 1) {
						StandardLane lane2 = (StandardLane) currentLane.getStandardRoad().getOneLane().get(currentLane.getNumber() - 1);
						if (lane2 != null && isChangeRoad(car, lane2)) {// 可以换道
							changeRoad(car, lane2);
							return;
						}
						StandardLane lane3 = (StandardLane) currentLane.getStandardRoad().getOneLane().get(currentLane.getNumber() + 1);
						if (lane3 != null && isChangeRoad(car, lane3)) {// 可以换道
							changeRoad(car, lane3);
							return;
						}
					}
				} else {
					if (currentLane.getNumber() == -1) {// 在最里面车道
						StandardLane lane1 = (StandardLane) currentLane.getStandardRoad().getOtherLane().get(2);
						if (lane1 != null && isChangeRoad(car, lane1)) {// 可以换道
							changeRoad(car, lane1);
							return;
						}
					} else if (currentLane.getNumber() < -1) {
						StandardLane lane2 = (StandardLane) currentLane.getStandardRoad().getOtherLane().get(-currentLane.getNumber() - 1);
						if (lane2 != null && isChangeRoad(car, lane2)) {// 可以换道
							changeRoad(car, lane2);
							return;
						}
						StandardLane lane3 = (StandardLane) currentLane.getStandardRoad().getOtherLane().get(-currentLane.getNumber() + 1);
						if (lane3 != null && isChangeRoad(car, lane3)) {// 可以换道
							changeRoad(car, lane3);
							return;
						}
					}
				}
			}
		}
	}

	/**
	 * 判断该车是否应该换道
	 * 
	 * @timestamp Feb 27, 2016 2:09:37 PM
	 * @param car
	 * @param currentLane
	 * @return
	 */
	private boolean isChangeRoad(StandardCar car, StandardLane currentLane) {
		int carWidth = 15;
		ListIterator<StandardCar> ci = currentLane.getCurrentCar().listIterator();
		while (ci.hasNext()) {
			StandardCar c = ci.next();
			if (0 < car.getLength() - c.getLength() && car.getLength() - c.getLength() < carWidth) {// 如果两车靠近
				return false;
			}
		}
		return true;
	}

	/**
	 * 换道
	 * 
	 * @timestamp Feb 26, 2016 10:17:08 PM
	 * @param car
	 * @param lane1
	 */
	private void changeRoad(StandardCar car, StandardLane lane) {
		StandardLane currentLane = car.getCurrentLane();
		currentLane.removeCar(car);
		lane.addCar(car);
		car.setCurrentLane(lane);
		if (car.getCurrentLane().getStandardRoad().isHorizontal()) {
			car.setYyPoint(car.getCurrentLane().getYyOne());
		} else {
			car.setXxPoint(car.getCurrentLane().getXxOne());
		}
	}
}
