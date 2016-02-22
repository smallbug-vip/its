package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.core.StandardCross;

public interface StandardCrossMapper {

	/**
	 * 插入
	 * 
	 * @timestamp Feb 20, 2016 6:38:46 PM
	 * @param cross
	 * @throws Exception
	 */
	public void insertCross(Cross cross) throws Exception;

	/**
	 * 根据区域ID获取路口
	 * 
	 * @timestamp Feb 20, 2016 6:38:53 PM
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<StandardCross> selectStandardCrosses(Long areaId) throws Exception;
}
