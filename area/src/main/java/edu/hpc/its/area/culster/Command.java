package edu.hpc.its.area.culster;

/**
 * 命令接口
 * 
 * @timestamp Feb 22, 2016 6:53:15 PM
 * @author smallbug
 */
public interface Command {

	public static final String GET = "get";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String ADD = "add";
	public static final String START = "start";
	public static final String STOP = "stop";

	public static final String AREACHOOSE = "areaChoose";// 加载区域信息
	public static final String LOADROADDATA = "loadRoadData";// 加载道路信息
	public static final String LOADLANEDATA = "loadLaneData";// 加载车道信息
	public static final String LOADLIGHTDATA = "loadLightData";// 加载信号灯信息
	public static final String LOADCARDATA = "loadCarData";// 加载车辆信息

	/**
	 * 获取命令
	 * 
	 * @timestamp Feb 22, 2016 6:55:07 PM
	 * @return
	 */
	public String getCommand();

	/**
	 * 设置命令
	 * 
	 * @timestamp Feb 22, 2016 6:55:14 PM
	 * @param command
	 */
	public void setCommand(String command);

	/**
	 * 获取命令参数
	 * 
	 * @timestamp Feb 22, 2016 6:55:24 PM
	 * @return
	 */
	public String[] getValue();

	/**
	 * 设置命令参数
	 * 
	 * @timestamp Feb 22, 2016 6:55:47 PM
	 * @param value
	 */
	public void setValue(String[] value);
}