package edu.hpc.its.area;

/**
 * 全局常量
 * 
 * @timestamp Feb 21, 2016 9:47:11 AM
 * @author smallbug
 */
public class Constant {

	/************************ 实际长度与浏览器显示长度换算关系数据 ************************/
	public static double ONECM = 40.00;// 一厘米对应40 Double值
	public static double COMPRESS = 1720;// 实际长度/压缩后长度，压缩率

	/************************ 区域数据 ************************/
	public static String AREAIP = "192.168.88.131";// 运行该区域机器的IP地址
	public static String AREANAME = "一号区域";// 区域名称
	public static int AREAPORT = 12200;//// 运行该区域机器的开放的端口号

	public static int HORIZONTALNUM = 5;// 横向20个路口
	public static int VERTICALNUM = 5;// 纵向20个路口
	public static double ROADLENGTH = 100000;// 路长100000cm
	public static int LANENUM = 3;// 一个路有3条单向行道，即一个路是6条车道
	public static double LANEWIDE = 150;// 道宽150cm

	public static double ROADREALITYLENGTH = ROADLENGTH / COMPRESS * ONECM;// 路在浏览器中的实际长度

	/************************ 信号灯数据 ************************/
	public static double READTIME = 60 * 1000;// 红灯时间
	public static double GREENTIME = 30 * 1000;// 绿灯时间

}
