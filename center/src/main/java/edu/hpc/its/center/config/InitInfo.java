package edu.hpc.its.center.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化信息
 * 
 * @timestamp Feb 22, 2016 4:59:06 PM
 * @author smallbug
 */
public class InitInfo {

	/**
	 * 可远程连接区域集合
	 */
	private Map<String, AreaInfo> areaInfos = new HashMap<>();
	/**
	 * 客户端当前连接的区域
	 */
	private AreaInfo currentArea;

	public void addAreaInfo(AreaInfo areaInfo) {
		areaInfos.put(areaInfo.getAreaId(), areaInfo);
	}

	/**
	 * 根据ID获取区域信息
	 * 
	 * @timestamp Feb 22, 2016 4:57:34 PM
	 * @param id
	 * @return
	 */
	public AreaInfo getAreaInfoById(String id) {
		AreaInfo areaInfo = areaInfos.get(id);
		return areaInfo == null ? null : areaInfo;
	}

	/**
	 * 获取所有区域信息
	 * 
	 * @timestamp Feb 22, 2016 4:57:47 PM
	 * @return
	 */
	public Map<String, AreaInfo> getAreaInfos() {
		return areaInfos;
	}

	/**
	 * 获取当前区域信息
	 * 
	 * @timestamp Feb 22, 2016 4:58:13 PM
	 * @return
	 */
	public AreaInfo getCurrentArea() {
		return currentArea;
	}

	/**
	 * 设置当前区域信息
	 * 
	 * @timestamp Feb 22, 2016 4:58:27 PM
	 * @param currentArea
	 */
	public void setCurrentArea(AreaInfo currentArea) {
		this.currentArea = currentArea;
	}

}
