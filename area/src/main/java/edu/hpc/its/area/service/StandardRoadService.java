package edu.hpc.its.area.service;

import edu.hpc.its.area.Road;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardRoadService extends ServiceBase{

	public void insertRoad(Road road) throws ServiceException;
}
