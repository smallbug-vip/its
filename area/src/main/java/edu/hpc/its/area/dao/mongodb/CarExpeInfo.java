package edu.hpc.its.area.dao.mongodb;

import java.util.List;

import edu.hpc.its.area.core.StandardCar;

/**
 * 操作单次试验中车量数据
 * 
 * @timestamp Feb 29, 2016 7:03:09 PM
 * @author smallbug
 */
public interface CarExpeInfo {

	/**
	 * 记录车从进入这片区域到走出这片区域一共花费多长时间
	 * 
	 * @timestamp Feb 29, 2016 7:00:27 PM
	 * @param expId
	 *            实验ID
	 * @param carId
	 *            车辆ID
	 * @param time
	 *            花费时间
	 */
	public void insertAppearTime(String expId, Long carId, Long time);

	/**
	 * 记录车行驶通过一条道路的时间
	 * 
	 * @timestamp Feb 29, 2016 7:02:56 PM
	 * @param expId
	 * @param roadId
	 * @param carId
	 * @param time
	 */
	public void insertRoadTime(String expId, Long roadId, Long carId, Long time);

	/**
	 * 记录车辆信息
	 * 
	 * @timestamp Feb 29, 2016 7:07:48 PM
	 * @param expId
	 * @param car
	 */
	public void insertCarInfo(String expId, StandardCar car);

	/**
	 * 删除一个车的数据
	 * 
	 * @timestamp Feb 29, 2016 7:08:35 PM
	 * @param expId
	 * @param carId
	 */
	public void delOneCarInfo(String expId, Long carId);

	/**
	 * 删除一次试验中所有车的数据
	 * 
	 * @timestamp Feb 29, 2016 7:09:38 PM
	 * @param expId
	 */
	public void delExpCarInfo(String expId);

	/**
	 * 根据实验ID，路ID，车ID查找该车通过这条路花费的时间
	 * 
	 * @timestamp Feb 29, 2016 7:12:27 PM
	 * @param expId
	 * @param carId
	 * @param roadId
	 * @return
	 */
	public List<String> selectCarRoadTime(String expId, Long roadId, Long carId);

	/**
	 * 根据实验ID，车ID查找该车在本次试验中出现的时间
	 * 
	 * @timestamp Feb 29, 2016 7:14:08 PM
	 * @param expId
	 * @param carId
	 * @return
	 */
	public String selectCarAppearTime(String expId, Long carId);

	/**
	 * 查询车的数据
	 * 
	 * @timestamp Feb 29, 2016 7:15:21 PM
	 * @param expId
	 * @param carId
	 * @return
	 */
	public String selectCarInfo(String expId, Long carId);

	/**
	 * 获取一次实验的所有车
	 * 
	 * @timestamp Feb 29, 2016 8:28:00 PM
	 * @param expId
	 * @return
	 */
	public List<String> selectExpCar(String expId);
}
