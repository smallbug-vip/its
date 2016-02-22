package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.core.StandardLane;

public interface StandardLaneMapper {

	/**
	 * 插入
	 * 
	 * @timestamp Feb 20, 2016 10:26:08 PM
	 * @param lane
	 * @throws Exception
	 */
	public void insertLane(Lane lane) throws Exception;

	/**
	 * 查询相关车道
	 * 
	 * @timestamp Feb 20, 2016 10:26:14 PM
	 * @param roadId
	 */
	public List<StandardLane> selectStandardLanes(Long roadId) throws Exception;
}
