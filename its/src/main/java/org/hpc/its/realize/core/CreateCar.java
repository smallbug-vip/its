package org.hpc.its.realize.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hpc.its.realize.entities.a.Car;
import org.hpc.its.realize.factory.AbstractEntityFactory;

/**
 * 对应配置文件中<car></car>
 * 
 * @timestamp Nov 18, 2015 11:04:51 PM
 * @author smallbug
 */
public class CreateCar implements Runnable {

	private Map<String, Object> carAttrs = null;
	private AbstractEntityFactory factory = null;
	private Map<Long, Car> carMap = Collections.synchronizedMap(new HashMap<>());
	private InitMap initMap = null;
	private int amount;
	private int sleep;
	private String init;

	public CreateCar(Map<String, Object> carAttrs, AbstractEntityFactory factory, InitMap initMap, String init) {
		this.init = init;
		this.carAttrs = carAttrs;
		this.factory = factory;
		this.initMap = initMap;
		this.amount = Integer.parseInt((String) carAttrs.get("amount"));
		this.sleep = Integer.parseInt((String) carAttrs.get("sleep"));
		this.carAttrs.remove("sleep");
	}

	@Override
	public void run() {
		for (int i = 0; i < amount; i++) {
			carAttrs.put("amount", i);
			Car car = factory.creatCar(carAttrs);
			carMap.put(car.getCarId(), car);
			new Thread(new CarRun(car, initMap, init)).start();
			try {
				// 每 sleep/1000 出现一两车
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public synchronized Map<Long, Car> getCarMap() {
		return carMap;
	}

}
