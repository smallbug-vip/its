package edu.hpc.its.area.service;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardAreaService extends ServiceBase{

	public void insertArea(Area area) throws ServiceException;
}
