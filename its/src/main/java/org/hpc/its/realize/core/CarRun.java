package org.hpc.its.realize.core;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hpc.its.realize.entities.a.Car;
import org.hpc.its.realize.entities.a.Lane;
import org.hpc.its.realize.entities.a.Light;
import org.hpc.its.realize.entities.a.Road;
import org.hpc.its.realize.entities.abs.AbstractCar;
import org.hpc.its.realize.entities.abs.AbstractLane;

public class CarRun implements Runnable {

	private Car car = null;// 这辆车
	private Lane currentLane = null;// 车所在的当前车道
	private Road currentRoad = null;// 车所在的当前路
	private float speed = 0;// 车速
	private float distance = 0;// 距离车道末端的距离
	private int changeCount = 0;
	private InitMap initMap = null;
	private Set<CreateCar> createCars = null;
	private String init;

	Logger log = Logger.getLogger(CarRun.class);

	public CarRun(Car car, InitMap initMap, String init) {
		this.init = init;
		this.car = car;
		this.speed = ((float) car.getSpeed()) / 10;
		this.distance = car.getStartDistance();
		this.createCars = initMap.getCreateCars();
		this.initMap = initMap;
	}

	@Override
	public void run() {
		// 得到当前车所在的路
		currentRoad = car.getRoads().get(car.getCount());
		// 得到当前车所在的车道
		currentLane = (Lane) car.getCurrentLine();
		boolean flag = false;
		boolean isEnd = false;
		while (true) {

			// 车道末端距离路口中心的距离
			int cutLength = 2 * currentRoad.getForwardLanes().size() * currentLane.getWidth();
			if (flag) {
				// 设置车的倾斜角度
				car.setAngle(currentRoad.getAngle() + 180);
				// 如果距离末端的距离 < 车道的距离
				while (distance > 0) {
					// 设置车的当前x坐标是：当前车道的末端x坐标 + 车距离车道末端的距离 * cos(180-路得倾角)
					car.setCurrentXPoint((currentLane.getEndXPoint()) + (int) (distance //
							* Math.cos((Math.PI * (180 - currentRoad.getAngle()) / 180))));
					// 设置车的当前y坐标是：当前车道的末端y坐标 - 车距离车道末端的距离 * sin(180-路得倾角)
					car.setCurrentYPoint((currentLane.getEndYPoint()) - (int) (distance //
							* Math.sin((Math.PI * (180 - currentRoad.getAngle()) / 180))));
					judgeDistance(flag);
					// 距离车末端的距离加大
					distance -= speed;
					car.setEndDistance(distance);
					if (changeCount > 0)
						changeCount -= 1;
					// 延时0.1s
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			} else {
				// 设置车的倾斜角度
				car.setAngle(currentRoad.getAngle());
				// 如果距离末端的距离 < 车道的距离
				while (distance < currentRoad.getLength() - cutLength) {
					// 设置车的当前x坐标是：当前车道的末端x坐标 + 车距离车道末端的距离 * cos(180-路得倾角)
					car.setCurrentXPoint((currentLane.getEndXPoint()) + (int) (distance //
							* Math.cos((Math.PI * (180 - currentRoad.getAngle()) / 180))));
					// 设置车的当前y坐标是：当前车道的末端y坐标 - 车距离车道末端的距离 * sin(180-路得倾角)
					car.setCurrentYPoint((currentLane.getEndYPoint()) - (int) (distance //
							* Math.sin((Math.PI * (180 - currentRoad.getAngle()) / 180))));
					judgeDistance(flag);
					// 距离车末端的距离加大
					distance += speed;
					car.setEndDistance(distance);
					if (changeCount > 0)
						changeCount -= 1;
					// 延时0.1s
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
			// 将当前路缓存
			Road oldRoad = car.getRoads().get(car.getCount());
			try {
				// 获取下一条要走的路
				currentRoad = car.getRoads().get(car.getCount() + 1);
			} catch (Exception e) {
				for (CreateCar cc : createCars) {
					if (cc.getCarMap().containsKey(car.getCarId())) {
						cc.getCarMap().remove(car.getCarId());
						break;
					}
				}
				synchronized (initMap) {
					Set<AbstractCar> ac = car.getCurrentLine().getCurrentCars();
					for (AbstractCar c : ac) {
						if (((Car) c).getCarId().longValue() == car.getCarId().longValue()) {
							ac.remove(c);
							break;
						}
					}
				}
				log.info("car id = " + car.getCarId() + " go out! spend time is " + ((System.currentTimeMillis() - car.getTime()) / 1000) + "s,belong to ->" + init);
				return;
			}
			// 将走过的路条数+1默认从0开始
			car.setCount(car.getCount() + 1);
			Random random = new Random();
			// 如果之前从起始端进入道路，就用当前路末端坐标-上一条路末端坐标
			if (flag) {
				isEnd = Math.abs(currentRoad.getEndXPoint() - oldRoad.getEndXPoint()) < 30 && //
						Math.abs(currentRoad.getEndYPoint() - oldRoad.getEndYPoint()) < 30;
			} else {

				// 如果之前从末端进入道路，就用当前路末端坐标 - 上一条路末端坐标
				isEnd = Math.abs(currentRoad.getEndXPoint() - oldRoad.getStartXPoint()) < 30 && //
						Math.abs(currentRoad.getEndYPoint() - oldRoad.getStartYPoint()) < 30;
			}
			Lane lane = null;
			// 判断即将进入下一条路的起始端还是末端
			// 如果是末端进入
			if (isEnd) {
				if (currentRoad.getEndLight() != null)
					judgeLight(currentRoad.getEndLight());
				// 从可走车道中随机选一个
				int randomInt = random.nextInt(currentRoad.getNegativeLanes().size());
				AbstractLane[] as = {};
				as = currentRoad.getNegativeLanes().toArray(as);
				lane = (Lane) as[randomInt];
				synchronized (initMap) {
					currentLane.getCurrentCars().remove(car);
				}
				// 修改车的当前车道
				currentLane = lane;
				car.setCurrentLine(lane);
				synchronized (initMap) {
					currentLane.getCurrentCars().add(car);
				}
				changeCount = 0;
				// 重置距离末端的距离
				distance = 0;
				flag = false;
			} else {
				if (currentRoad.getStartLight() != null)
					judgeLight(currentRoad.getStartLight());

				// 如果是起始端进入
				int randomInt = random.nextInt(currentRoad.getForwardLanes().size());
				AbstractLane[] as = {};
				as = currentRoad.getForwardLanes().toArray(as);
				lane = (Lane) as[randomInt];
				synchronized (initMap) {
					currentLane.getCurrentCars().remove(car);
				}
				// 修改车的当前车道
				currentLane = lane;
				car.setCurrentLine(lane);
				synchronized (initMap) {
					currentLane.getCurrentCars().add(car);
				}
				changeCount = 0;
				// 重置距离末端的距离
				distance = currentRoad.getLength() - cutLength;
				flag = true;
			}
		}
	}

	private void judgeDistance(boolean flag_1) {

		while (true) {
			Lane currentLine = (Lane) car.getCurrentLine();
			boolean flag_0 = false;

			Set<AbstractCar> asc = null;
			synchronized (initMap) {
				asc = currentLine.getCurrentCarsInfo();
				if (asc != null) {
					for (AbstractCar c : asc) {
						// 如果前端进入
						if (flag_1 && car.getEndDistance() - ((Car) c).getEndDistance() > 0//
								&& car.getEndDistance() - ((Car) c).getEndDistance() < 9) {
							flag_0 = true;
							break;
							// 如果从末端进入
						} else if (!flag_1 && car.getEndDistance() - ((Car) c).getEndDistance() < 0//
								&& car.getEndDistance() - ((Car) c).getEndDistance() > -9) {
							flag_0 = true;
							break;
						}
					}
				}
			}

			if (!flag_0)
				return;

			if (changeCount <= 200) {
				// 查看是否可以换道
				// 获取车道对应的路
				Road road = (Road) currentLine.getRoad();
				// 生声缓存车道
				Lane cLane = null;
				// 如果前进车道包含原车道
				if (road.getForwardLanes().contains(currentLane)) {
					// 遍历该方向的原有车道
					for (AbstractLane l : road.getForwardLanes()) {
						// 找到原车道的相邻车道
						if ((((Lane) l).getLaneId().longValue() + 2) == currentLane.getLaneId().longValue()
								|| (((Lane) l).getLaneId().longValue() - 2) == currentLane.getLaneId().longValue()) {
							cLane = (Lane) l;
							// 重置asc变量
							asc = new HashSet<>();
							try {
								// 找到要变更车道上的所有的车
								synchronized (initMap) {
									asc.addAll(cLane.getCurrentCars());
								}
							} catch (Exception e1) {
								asc = null;
							}
							// 声明boolean变量，表示是否有车与该车的位置接近，false表示有
							boolean flag_2 = true;
							if (asc != null && !asc.isEmpty()) {
								// 遍历所有车
								for (AbstractCar c : asc) {
									// 如果位置接近就标志位false
									if (flag_1 && Math.abs(car.getEndDistance() - ((Car) c).getEndDistance()) < 12) {
										flag_2 = false;
										break;
									}
								}
							}
							if (flag_2) {
								synchronized (initMap) {

									currentLane.getCurrentCars().remove(car);
								}
								// 修改车的当前车道
								currentLane = cLane;
								car.setCurrentLine(cLane);
								synchronized (initMap) {

									currentLane.getCurrentCars().add(car);
								}
								changeCount += 100;
								break;
							}
						}
					}
				} else {
					// 遍历该方向的原有车道
					for (AbstractLane l : road.getNegativeLanes()) {
						// 找到原车道的相邻车道
						if ((((Lane) l).getLaneId().longValue() + 2) == currentLane.getLaneId().longValue()
								|| (((Lane) l).getLaneId().longValue() - 2) == currentLane.getLaneId().longValue()) {
							cLane = (Lane) l;
							// 重置asc变量
							asc = new HashSet<>();
							try {
								// 找到要变更车道上的所有的车
								synchronized (initMap) {

									asc.addAll(cLane.getCurrentCars());
								}
							} catch (Exception e1) {
								asc = null;
							}
							// 声明boolean变量，表示是否有车与该车的位置接近，false表示有
							boolean flag_2 = true;
							if (asc != null && !asc.isEmpty()) {
								// 遍历所有车
								for (AbstractCar c : asc) {
									// 如果位置接近就标志位false
									if (flag_1 && Math.abs(car.getEndDistance() - ((Car) c).getEndDistance()) < 12) {
										flag_2 = false;
										break;
									}
								}
							}
							if (flag_2) {

								synchronized (initMap) {

									currentLane.getCurrentCars().remove(car);
								}
								// 修改车的当前车道
								currentLane = cLane;
								car.setCurrentLine(cLane);
								synchronized (initMap) {

									currentLane.getCurrentCars().add(car);
								}
								changeCount += 100;
								break;
							}
						}
					}
				}
			}

			// 延时0.1s
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void judgeLight(Light light) {
		while (true) {
			if (light.getState() == 1)
				return;
			// 延时0.1s
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
