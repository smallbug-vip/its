package edu.hpc.its.area.dao.mongodb;

import java.util.List;

import edu.hpc.its.area.core.StandardLight;

/**
 * 操作单次试验中信号灯数据
 * 
 * @timestamp Feb 29, 2016 7:29:16 PM
 * @author smallbug
 */
public interface LightExpeInfo {

	/**
	 * 统计信号灯数据
	 * 
	 * @timestamp Feb 29, 2016 7:26:23 PM
	 * @param expId
	 * @param light
	 */
	public void insertLightInfo(String expId, StandardLight light);

	/**
	 * 删除一次试验中信号灯的数据
	 * 
	 * @timestamp Feb 29, 2016 7:27:15 PM
	 * @param expId
	 */
	public void delLightInfo(String expId);

	/**
	 * 统计一次试验中信号灯数据
	 * 
	 * @timestamp Feb 29, 2016 7:28:15 PM
	 * @param expId
	 * @return
	 */
	public List<String> selectLightInfo(String expId);

	/**
	 * 获取各试验中信号灯的变化频率
	 * 
	 * @timestamp Mar 2, 2016 12:54:01 AM
	 * @param expIds
	 * @return
	 */
	public List<Double> selectLightTimeByExpIds(String[] expIds);
}
