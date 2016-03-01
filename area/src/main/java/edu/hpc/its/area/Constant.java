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
	public static String AREAIP = "127.0.0.1";// 运行该区域机器的IP地址
	public static String AREANAME = "NUM001";// 区域名称
	public static int AREAPORT = 12200;//// 运行该区域机器的开放的端口号

	public static int HORIZONTALNUM = 5;// 横向5个路口
	public static int VERTICALNUM = 5;// 纵向5个路口
	public static double ROADLENGTH = 100000;// 路长100000cm
	public static int LANENUM = 3;// 一个路有3条单向行道，即一个路是6条车道
	public static double LANEWIDE = 200;// 道宽150cm

	public static double ROADREALITYLENGTH = ROADLENGTH / COMPRESS * ONECM;// 路在浏览器中的实际长度

	/************************ 信号灯数据 ************************/
	public static double READTIME = 30000;// 红灯时间
	public static double GREENTIME = 30000;// 绿灯时间

	/************************ 连接器 ************************/
	public static int MINPROCESSORS = 5;// Socket处理器最小个数
	public static int MAXPROCESSORS = 20;// Socket处理器最大个数

	/************************ 车行驶数据 ************************/
	public static long CARCOMEOOUT = 10000;// 隔多长时间路口出一次车
	public static int CARCOMEOOUTNUM = 0;// 一个路口出一种类型的车多少次，默认是0即无限制一直出
	public static int RODENUMONETHREAD = 10;// 一根线程管理多少条路
	public static String EXPID = "Exp000001";// 实验ID
	public static int ISNOTEINFO = 0;// 是否使用mongodb记录数据，默认是0不记录

	/**
	 * 常量计算变量
	 * 
	 * @timestamp Feb 24, 2016 8:51:49 PM
	 */
	public static void caculate() {
		ROADREALITYLENGTH = ROADLENGTH / COMPRESS * ONECM;
	}
}
