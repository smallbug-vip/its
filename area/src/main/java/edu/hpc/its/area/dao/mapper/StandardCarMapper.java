package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.core.StandardCar;

/**
 * 车
 * 
 * @timestamp Feb 24, 2016 1:47:32 PM
 * @author smallbug
 */
public interface StandardCarMapper {

	/**
	 * 插入汽车信息
	 * 
	 * @timestamp Feb 24, 2016 1:45:46 PM
	 * @param car
	 * @throws Exception
	 */
	public void insertCar(Car car) throws Exception;

	/**
	 * 根据区域ID选择车
	 * 
	 * @timestamp Feb 24, 2016 1:46:29 PM
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<StandardCar> selectStandardCar(Long areaId) throws Exception;
}
