package edu.hpc.its.area.webservice;

/**
 * WebService远程调用
 * 
 * @timestamp Mar 1, 2016 11:17:19 PM
 * @author smallbug
 */
public interface AreaInfoService {

	/**
	 * 获取所有实验的描述信息
	 * 
	 * @timestamp Mar 1, 2016 11:23:52 PM
	 * @return
	 */
	public String getAllExpInfo();

	/**
	 * 通过改变信号灯的频率从而改变车辆穿行时间平均值的变化
	 * 
	 * @timestamp Mar 1, 2016 11:24:13 PM
	 * @param expIds
	 *            那几个实验作对比
	 * @return
	 */
	public String getLight2Time(String[] expIds);
}
