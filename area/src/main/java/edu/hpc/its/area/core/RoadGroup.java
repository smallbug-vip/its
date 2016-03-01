package edu.hpc.its.area.core;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import edu.hpc.its.area.Lane;

/**
 * 表示一组路，一组路被一条线程所管理
 * 
 * @timestamp Feb 24, 2016 8:53:48 PM
 * @author smallbug
 */
public class RoadGroup implements Runnable {

	Set<StandardLane> lanes = new HashSet<>();

	public RoadGroup(StandardRoad... roads) {
		initLanes(roads);
	}

	private void initLanes(StandardRoad[] roads) {
		for (StandardRoad road : roads) {
			for (Entry<Integer, Lane> en : road.getOneLane().entrySet()) {
				lanes.add((StandardLane) en.getValue());
			}
			for (Entry<Integer, Lane> en : road.getOtherLane().entrySet()) {
				lanes.add((StandardLane) en.getValue());
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			for (StandardLane lane : lanes) {
				 List<StandardCar> currentCars = lane.getCurrentCar();// 这条道上的车增加路程，以此来模拟车在路上跑
				 for(StandardCar car : currentCars){
					 car.run();
				 }
			}
			try {
				Thread.sleep(10);// 每0.1s扫一次，忽略计算时间，每次路程在加的时候应该/10
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
