package edu.hpc.its.area.service;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardAreaService extends ServiceBase {

	/**
	 * 插入区域信息
	 * 
	 * @timestamp Feb 20, 2016 6:06:58 PM
	 * @param area
	 * @throws ServiceException
	 */
	public void insertArea(Area area);

	/**
	 * 根据名称查询区域信息
	 * 
	 * @timestamp Feb 20, 2016 6:05:38 PM
	 * @param name
	 * @return
	 */
	public StandardArea selectAreaByName(String name);
}
