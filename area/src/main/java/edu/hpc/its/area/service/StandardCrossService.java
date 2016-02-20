package edu.hpc.its.area.service;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardCrossService extends ServiceBase{

	public void insertCross(Cross cross) throws ServiceException;
}
