package edu.hpc.its.area.service;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardLaneService extends ServiceBase{

	public void insertLane(Lane lane) throws ServiceException;
}
