package edu.hpc.its.area.service;

import edu.hpc.its.area.Light;
import edu.hpc.its.area.exception.ServiceException;

public interface StandardLightService extends ServiceBase{

	public void insertLight(Light light) throws ServiceException;
}
