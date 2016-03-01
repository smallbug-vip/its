package edu.hpc.its.area.listener;

import java.util.Random;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Lane;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 定义车的初始坐标，和当前车道，说明车要上路了
 * 
 * @timestamp Feb 24, 2016 4:19:23 PM
 * @author smallbug
 */
public class CarComeOutListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getLifecycle() instanceof Area //
				&& Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
			carComeOut();
		}
	}

	private void carComeOut() {
		Thread t = new Thread(new Runnable() {
			int count = Constant.CARCOMEOOUTNUM;

			@Override
			public void run() {
				while (Constant.CARCOMEOOUTNUM == 0 ? true : (count-- > 0)) {// CARCOMEOOUTNUM==0将一直循环，否则循环给定的次数
					Random random = new Random();
					for (Double d : StandardEntityFactory.getSpeeds()) {// 遍历所有的速度
						for (String route : StandardEntityFactory.getRouteString()) {// 每一个速度对应一遍所有的线路
							String[] rs = route.split(",");
							/////////////////////////// 初始化车，随机选取车型
							StandardCar car = StandardEntityFactory.careateCar();
							/////////////////////////// 设置车要走的路线
							StandardRoad[] rArray = new StandardRoad[rs.length];
							int i = 0;
							for (String str : rs) {
								rArray[i++] = StandardEntityFactory.getSignRoad(str);
							}
							i = 0;
							car.setRoads(rArray);
							/////////////////////////// 设置车速
							car.setSpeed(d);
							/////////////////////////// 设置当前车道
							Lane lane = null;
							if (new Integer(rs[0].split("\\|")[1]) == (Constant.VERTICALNUM + 1)//是否为东向或者南向进入
									|| new Integer(rs[0].split("\\|")[2]) == (Constant.HORIZONTALNUM + 1)) {
								if ("H".equals(rs[0].split("\\|")[0])) {
									lane = (StandardLane) rArray[0].getOtherLane()
											.get(random.nextInt(rArray[0].getOtherLane().size()) + 1);
								} else {
									lane = (StandardLane) rArray[0].getOneLane()
											.get(random.nextInt(rArray[0].getOneLane().size()) + 1);
								}
							} else {
								if ("H".equals(rs[0].split("\\|")[0])) {
									lane = (StandardLane) rArray[0].getOneLane()
											.get(random.nextInt(rArray[0].getOneLane().size()) + 1);
								} else {
									lane = (StandardLane) rArray[0].getOtherLane()
											.get(random.nextInt(rArray[0].getOtherLane().size()) + 1);
								}
								
							}

							car.setCurrentLane((StandardLane) lane);
							/////////////////////////// 设置是否横向
							car.setHorizontal(car.getCurrentLane().getStandardRoad().isHorizontal());
							/////////////////////////// 设置Length
							car.setLength(car.getCurrentLane().getLength());
							/////////////////////////// 设置初始坐标
							StandardCross cross = (StandardCross) rArray[0].getOtherCross();
							if (cross == null) {//// ->很大的局限性，只支持一端cross为null的路，即只可从路口出发
								car.setXxPoint(car.getCurrentLane().getXxOne());
								car.setYyPoint(car.getCurrentLane().getYyOne());
							}
							((StandardLane) lane).addCar(car);
							StandardEntityFactory.registerCar(car);
						}
						try {
							Thread.sleep(Constant.CARCOMEOOUT);// 默认是10s);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		t.setDaemon(true);
		t.setName("Thread-CarComeOut");
		t.start();
	}
}
