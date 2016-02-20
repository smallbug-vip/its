package edu.hpc.its.area;

import edu.hpc.its.area.core.EntityWrap;

/**
 * 定义车行驶规则
 * 
 * @timestamp Feb 16, 2016 5:47:11 PM
 * @author smallbug
 */
public interface CarRun {

	/**
	 * 车跑核心接口
	 * 
	 * @timestamp Feb 16, 2016 9:12:23 PM
	 * @param base
	 */
	public void run(EntityWrap entityWrap);
}
