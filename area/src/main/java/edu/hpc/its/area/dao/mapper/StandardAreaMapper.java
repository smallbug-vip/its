package edu.hpc.its.area.dao.mapper;

import edu.hpc.its.area.Area;

public interface StandardAreaMapper {

	/**
	 * 插入区域
	 * 
	 * @timestamp Feb 20, 2016 6:05:27 PM
	 * @param area
	 * @throws Exception
	 */
	public void insertArea(Area area) throws Exception;

	/**
	 * 根据名称查询区域
	 * 
	 * @timestamp Feb 20, 2016 6:06:49 PM
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Area selectAreaByName(String name) throws Exception;
}
