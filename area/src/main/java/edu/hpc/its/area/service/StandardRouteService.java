package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Route;
import edu.hpc.its.area.core.StandardRoute;

public interface StandardRouteService extends ServiceBase {

	/**
	 * 插入线路信息
	 * 
	 * @timestamp Feb 24, 2016 10:59:40 AM
	 * @param route
	 */
	public void insertRoute(Route route);

	/**
	 * 
	 * @timestamp Feb 24, 2016 11:00:12 AM
	 * @param areaId
	 * @return
	 */
	public List<StandardRoute> getStandardRoutes(Long areaId);
}
