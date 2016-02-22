package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Light;
import edu.hpc.its.area.core.StandardLight;

public interface StandardLightMapper {

	/**
	 * 插入
	 * 
	 * @timestamp Feb 20, 2016 7:35:36 PM
	 * @param light
	 * @throws Exception
	 */
	public void insertLight(Light light) throws Exception;

	/**
	 * 获取
	 * 
	 * @timestamp Feb 20, 2016 7:35:42 PM
	 * @param crossId
	 * @throws Exception
	 */
	public List<StandardLight> selectStandardLight(Long crossId) throws Exception;
}
