package edu.hpc.its.area.rule;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Rule;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardLane;

/**
 * 车跑
 * 
 * @timestamp Feb 15, 2016 7:20:18 PM
 * @author smallbug
 */
public class CarRunRule extends BaseRule implements Rule {

	@Override
	public void invoke() {
		if (getEntity() instanceof Car) {
			StandardCar car = (StandardCar) getEntity();
			double part = car.getSpeed() / Constant.COMPRESS * Constant.ONECM / 100;
			StandardLane lane = car.getCurrentLane();
			car.setLength(car.getLength() - part);

			if (car.getCurrentLane().getStandardRoad().getOtherCross() == null//
					&& car.getCurrentLane().getStandardRoad() != car.getRoads()[0]) {
				if (lane.getNumber() > 0) {
					if (lane.getStandardRoad().isHorizontal()) {
						car.setXxPoint(lane.getXxOther() + (lane.getLength() - car.getLength()));
						car.setAngle(180);// 向东行驶
					} else {
						car.setYyPoint(lane.getYyOther() - (lane.getLength() - car.getLength()));
						car.setAngle(90);// 向南行驶
					}
				} else {
					if (lane.getStandardRoad().isHorizontal()) {
						car.setXxPoint(lane.getXxOther() - (lane.getLength() - car.getLength()));
						car.setAngle(0);// 向西行驶
					} else {
						car.setYyPoint(lane.getYyOther() + (lane.getLength() - car.getLength()));
						car.setAngle(270);// 向北行驶
					}
				}
				return;
			}
			if (car.getNextCross() != null && //
					car.getCurrentLane().getStandardRoad().getOneCross() != car.getNextCross()) {
				if (lane.getNumber() > 0) {
					if (lane.getStandardRoad().isHorizontal()) {
						car.setXxPoint(lane.getXxOther() + (lane.getLength() - car.getLength()));
						car.setAngle(180);// 向东行驶
					} else {
						car.setYyPoint(lane.getYyOther() - (lane.getLength() - car.getLength()));
						car.setAngle(90);// 向南行驶
					}
				} else {
					if (lane.getStandardRoad().isHorizontal()) {
						car.setXxPoint(lane.getXxOther() - (lane.getLength() - car.getLength()));
						car.setAngle(0);// 向西行驶
					} else {
						car.setYyPoint(lane.getYyOther() + (lane.getLength() - car.getLength()));
						car.setAngle(270);// 向北行驶
					}
				}
				return;
			}
			if (lane.getNumber() > 0) {
				if (lane.getStandardRoad().isHorizontal()) {
					car.setXxPoint(lane.getXxOne() + (lane.getLength() - car.getLength()));
					car.setAngle(180);// 向东行驶
				} else {
					car.setYyPoint(lane.getYyOne() - (lane.getLength() - car.getLength()));
					car.setAngle(90);// 向北行驶
				}
			} else {
				if (lane.getStandardRoad().isHorizontal()) {
					car.setXxPoint(lane.getXxOne() - (lane.getLength() - car.getLength()));
					car.setAngle(0);// 向西行驶
				} else {
					car.setYyPoint(lane.getYyOne() + (lane.getLength() - car.getLength()));
					car.setAngle(270);// 向南行驶
				}
			}

			if (car.getLength() < 0) {
				car.setLength(0.0);
			}
		}
	}
}
