package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Road;
import edu.hpc.its.area.core.StandardRoad;

public interface StandardRoadService extends ServiceBase {

	/**
	 * 插入路信息
	 * 
	 * @timestamp Feb 20, 2016 7:11:56 PM
	 * @param road
	 */
	public void insertRoad(Road road);

	/**
	 * 获取与路口相关的所有路
	 * 
	 * @timestamp Feb 20, 2016 7:12:06 PM
	 * @param crossId
	 * @return
	 */
	public List<StandardRoad> getStandardCrosses(Long crossId);
}
