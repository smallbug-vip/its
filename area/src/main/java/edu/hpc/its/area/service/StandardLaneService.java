package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.core.StandardLane;

public interface StandardLaneService extends ServiceBase {

	/**
	 * 插入
	 * 
	 * @timestamp Feb 20, 2016 10:24:28 PM
	 * @param lane
	 */
	public void insertLane(Lane lane);

	/**
	 * 获取一条路的所有车道
	 * 
	 * @timestamp Feb 20, 2016 10:24:35 PM
	 * @param roadId
	 * @return
	 */
	public List<StandardLane> getStandardLanes(Long roadId);
}
