package edu.hpc.its.area.core;

import java.util.Map.Entry;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.culster.AreaConnector;
import edu.hpc.its.area.factory.StandardEntityFactory;

public class Bootstrap {

	public static void main(String[] args) {
		 StandardArea area = StandardEntityFactory.getArea();
		try {
			 area.init();
			 area.start();
			// printInfo(area);
			AreaConnector connector = new AreaConnector(area);
			connector.init();
			connector.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * start之后结合各类的toString方法，用于打印系统开启后个参数的初始化信息，测试时使用
	 * 
	 * @timestamp Feb 21, 2016 3:08:06 PM
	 * @param area
	 */
	private static void printInfo(StandardArea area) {
		for (StandardCross cross : area.getCrosses()) {
			System.out.println("cross id -> " + cross);
			for (Entry<Direction, StandardLight> lights : cross.getLights().entrySet()) {
				StandardLight light = lights.getValue();
				System.out.println("light -> " + light);
			}
			for (Entry<Direction, StandardRoad> roads : cross.getRoads().entrySet()) {
				StandardRoad road = roads.getValue();
				System.out.println("road -> " + road);
				for (Entry<Integer, Lane> oneLane : road.getOneLane().entrySet()) {
					StandardLane lane = (StandardLane) oneLane.getValue();
					System.out.println("lane -> " + lane);
				}
				for (Entry<Integer, Lane> otherLane : road.getOtherLane().entrySet()) {
					StandardLane lane = (StandardLane) otherLane.getValue();
					System.out.println("lane2 -> " + lane);
				}
			}
		}
	}
}
