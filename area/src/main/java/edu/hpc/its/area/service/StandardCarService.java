package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.core.StandardCar;

/**
 * 车service
 * 
 * @timestamp Feb 24, 2016 1:49:23 PM
 * @author smallbug
 */
public interface StandardCarService extends ServiceBase {

	/**
	 * 插入汽车信息
	 * 
	 * @timestamp Feb 24, 2016 1:48:58 PM
	 * @param car
	 */
	public void insertCar(Car car);

	/**
	 * 根据区域ID选择车
	 * 
	 * @timestamp Feb 24, 2016 1:49:12 PM
	 * @param areaId
	 * @return
	 */
	public List<StandardCar> getStandardCars(Long areaId);
}
