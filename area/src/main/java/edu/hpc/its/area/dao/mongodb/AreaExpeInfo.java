package edu.hpc.its.area.dao.mongodb;

import java.util.List;

import edu.hpc.its.area.core.StandardArea;

/**
 * 区域实验数据统计操作
 * 
 * @timestamp Feb 29, 2016 6:38:35 PM
 * @author smallbug
 */
public interface AreaExpeInfo {

	/**
	 * 创建实验
	 * 
	 * @timestamp Feb 29, 2016 6:38:30 PM
	 * @param expId
	 * @param area
	 */
	public void createExp(String expId, StandardArea area);

	/**
	 * 根据实验ID查找实验
	 * 
	 * @timestamp Feb 29, 2016 6:39:51 PM
	 * @param expId
	 * @return
	 */
	public String selectExpByExpId(String expId);

	/**
	 * 根据区域ID查找实验
	 * 
	 * @timestamp Feb 29, 2016 6:40:26 PM
	 * @param areaId
	 * @return
	 */
	public List<String> selectExpByAreaId(Long areaId);

	/**
	 * 查找所有实验<br />
	 * 正常情况下，webService应该传xml数据，服务器端解析xml数据以此交互信息，暂时没有按照规范来，导致该方法无法分页
	 * 
	 * @timestamp Feb 29, 2016 6:41:07 PM
	 * @return
	 */
	public List<String> selectAll();

	/**
	 * 根据实验ID删除数据
	 * 
	 * @timestamp Feb 29, 2016 6:43:47 PM
	 * @param expId
	 */
	public void delExpByExpId(String expId);

	/**
	 * 获取本次实验参与进来的车辆数目
	 * 
	 * @timestamp Feb 29, 2016 8:39:16 PM
	 * @param expId
	 */
	public long selectCarNum(String expId);
}
