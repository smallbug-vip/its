package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.core.StandardCross;

public interface StandardCrossService extends ServiceBase {

	/**
	 * 插入路口信息
	 * 
	 * @timestamp Feb 20, 2016 6:33:10 PM
	 * @param cross
	 */
	public void insertCross(Cross cross);

	/**
	 * 根据区域ID获取所有路口信息
	 * 
	 * @timestamp Feb 20, 2016 6:33:34 PM
	 * @param areaId
	 * @return
	 */
	public List<StandardCross> getStandardCrosses(Long areaId);
}
