package edu.hpc.its.area.service;

import java.util.List;

import edu.hpc.its.area.Light;
import edu.hpc.its.area.core.StandardLight;

public interface StandardLightService extends ServiceBase {

	/**
	 * 插入
	 * 
	 * @timestamp Feb 20, 2016 7:30:51 PM
	 * @param light
	 */
	public void insertLight(Light light);

	/**
	 * 获取路口相关的灯
	 * 
	 * @timestamp Feb 20, 2016 7:30:58 PM
	 * @param crossId
	 * @return
	 */
	public List<StandardLight> getStandardLight(Long crossId);
}
