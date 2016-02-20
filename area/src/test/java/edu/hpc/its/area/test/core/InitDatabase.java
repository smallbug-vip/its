package edu.hpc.its.area.test.core;

import java.util.HashMap;
import java.util.Map;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.core.Direction;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.dao.mysql.ServiceProxy;
import edu.hpc.its.area.service.StandardAreaService;
import edu.hpc.its.area.service.StandardCrossService;
import edu.hpc.its.area.service.StandardLaneService;
import edu.hpc.its.area.service.StandardLightService;
import edu.hpc.its.area.service.StandardRoadService;
import edu.hpc.its.area.service.Impl.StandardAreaServiceImpl;
import edu.hpc.its.area.service.Impl.StandardCrossServiceImpl;
import edu.hpc.its.area.service.Impl.StandardLaneServiceImpl;
import edu.hpc.its.area.service.Impl.StandardLightServiceImpl;
import edu.hpc.its.area.service.Impl.StandardRoadServiceImpl;

public class InitDatabase {

	/************************ 实际长度与浏览器显示长度换算关系数据 ************************/
	private double ONECM = 40.00;// 一厘米对应40 Double值
	private double COMPRESS = 1720;// 实际长度/压缩后长度，压缩率

	/************************ 区域数据 ************************/
	private String AREAIP = "192.168.88.131";// 运行该区域机器的IP地址
	private String AREANAME = "一号区域";// 区域名称
	private int AREAPORT = 12200;//// 运行该区域机器的开放的端口号

	private int HORIZONTALNUM = 5;// 横向20个路口
	private int VERTICALNUM = 5;// 纵向20个路口
	private double ROADLENGTH = 100000;// 路长100000cm
	private int LANENUM = 3;// 一个路有3条单向行道，即一个路是6条车道
	private double LANEWIDE = 150;// 道宽150cm

	private double ROADREALITYLENGTH = ROADLENGTH / COMPRESS * ONECM;// 路在浏览器中的实际长度

	/************************ 信号灯数据 ************************/
	private double READTIME = 60 * 1000;// 红灯时间
	private double GREENTIME = 30 * 1000;// 绿灯时间

	/******************* service **********************/

	private StandardAreaService areaService = null;
	private StandardCrossService crossService = null;
	private StandardRoadService roadService = null;
	private StandardLaneService laneService = null;
	private StandardLightService lightService = null;

	/******************* cache **********************/

	private Map<String, Cross> crossCache = new HashMap<>();

	/******************* main **********************/
	public static void main(String[] args) {
		InitDatabase init = new InitDatabase();
		StandardArea area = new StandardArea();
		init.initArea(area);
		init.initCross(area);
	}

	/**
	 * 初始化交叉路口
	 * 
	 * @timestamp Feb 19, 2016 9:41:44 PM
	 */
	private void initCross(StandardArea area) {

		for (int i = 1; i <= VERTICALNUM; i++) {
			for (int j = 1; j <= HORIZONTALNUM; j++) {
				StandardCross cross = new StandardCross();
				cross.setHorizontalNum(j);
				cross.setOrdinateNum(i);
				cross.setStandardArea(area);
				cross.setXxPoint(j * ROADREALITYLENGTH);
				cross.setYyPoint(i * ROADREALITYLENGTH);
				crossService.insertCross(cross);
				crossCache.put("" + i + j, cross);
				initRoad(cross, i, j);
				initLight(cross, i, j);
			}
		}
	}

	/**
	 * 初始化灯
	 * 
	 * @timestamp Feb 20, 2016 11:06:41 AM
	 * @param cross
	 * @param i
	 *            纵向第几个
	 * @param j
	 *            横向第几个
	 */
	private void initLight(StandardCross cross, int i, int j) {

		for (Direction d : Direction.values()) {
			StandardLight light = new StandardLight();
			light.setStandardCross(cross);
			light.setRed(READTIME);
			light.setGreen(GREENTIME);
			light.setSize(LANENUM == 1 //
					? LANEWIDE / COMPRESS * ONECM //
					: (LANEWIDE / COMPRESS * ONECM) * (LANENUM - 1));
			light.setDirection(d);
			light.setLightState(d.getDirection() % 2 == 0 ? 1 : 0);
			double part = LANENUM * (LANEWIDE / COMPRESS * ONECM);
			if (d == Direction.NORTH) {// 北
				light.setCenterX(cross.getXxPoint());
				light.setCenterY(cross.getYyPoint() - part);
			} else if (d == Direction.SOUTH) {// 南
				light.setCenterX(cross.getXxPoint());
				light.setCenterY(cross.getYyPoint() + part);
			} else if (d == Direction.WEST) {// 西
				light.setCenterX(cross.getXxPoint() - part);
				light.setCenterY(cross.getYyPoint());
			} else if (d == Direction.EAST) {// 东
				light.setCenterX(cross.getXxPoint() + part);
				light.setCenterY(cross.getYyPoint());
			}
			lightService.insertLight(light);
		}
	}

	/**
	 * 初始化路
	 * 
	 * @timestamp Feb 19, 2016 11:12:44 PM
	 * @param cross
	 * @param i
	 *            纵向第几个
	 * @param j
	 *            横向第几个
	 */
	private void initRoad(StandardCross cross, int i, int j) {

		//////////// 横向路
		StandardRoad hRoad = new StandardRoad();
		hRoad.setHorizontalNum(j);
		hRoad.setOrdinateNum(i);
		hRoad.setHorizontal(true);
		if (j == 1)
			hRoad.setOneCross(null);
		else
			hRoad.setOneCross(crossCache.get("" + i + (j - 1)));
		hRoad.setOtherCross(cross);

		if (j == 1) {
			hRoad.setOpen(true);
		} else {
			hRoad.setOpen(false);
		}
		hRoad.setRealityLength(ROADLENGTH);
		roadService.insertRoad(hRoad);
		initLane(hRoad);
		if (j == HORIZONTALNUM) {
			StandardRoad aRoad = new StandardRoad();
			aRoad.setHorizontalNum(j + 1);
			aRoad.setOrdinateNum(i);
			aRoad.setHorizontal(true);
			aRoad.setOneCross(cross);
			aRoad.setOtherCross(null);
			aRoad.setOpen(true);
			aRoad.setRealityLength(ROADLENGTH);
			roadService.insertRoad(aRoad);
			initLane(aRoad);
		}

		//////////// 纵向路
		StandardRoad oRoad = new StandardRoad();
		oRoad.setHorizontalNum(j);
		oRoad.setOrdinateNum(i);
		oRoad.setHorizontal(false);
		if (i == 1)
			oRoad.setOneCross(null);
		else
			oRoad.setOneCross(crossCache.get("" + (i - 1) + j));
		oRoad.setOtherCross(cross);

		if (i == 1) {
			oRoad.setOpen(true);
		} else {
			oRoad.setOpen(false);
		}
		oRoad.setRealityLength(ROADLENGTH);
		roadService.insertRoad(oRoad);
		initLane(oRoad);
		if (i == VERTICALNUM) {
			StandardRoad aRoad = new StandardRoad();
			aRoad.setHorizontalNum(j);
			aRoad.setOrdinateNum(i + 1);
			aRoad.setHorizontal(false);
			aRoad.setOneCross(cross);
			aRoad.setOtherCross(null);
			aRoad.setOpen(true);
			aRoad.setRealityLength(ROADLENGTH);
			roadService.insertRoad(aRoad);
			initLane(aRoad);
		}
	}

	/**
	 * 初始化车道
	 * 
	 * @timestamp Feb 20, 2016 10:40:40 AM
	 * @param hRoad
	 */
	private void initLane(StandardRoad hRoad) {

		for (int i = -LANENUM; i <= LANENUM; i++) {
			if (i != 0) {
				StandardLane lane = new StandardLane();
				lane.setRealityLength(hRoad.getRealityLength());
				lane.setRealityWidth(LANEWIDE);
				lane.setStandardRoad(hRoad);
				lane.setNumber(i);
				laneService.insertLane(lane);
			}
		}
	}

	/**
	 * 初始化区域
	 * 
	 * @timestamp Feb 19, 2016 8:44:48 PM
	 */
	private void initArea(StandardArea area) {
		this.areaService = (StandardAreaService) ServiceProxy//
				.getServiceProxy(new StandardAreaServiceImpl());
		this.crossService = (StandardCrossService) ServiceProxy//
				.getServiceProxy(new StandardCrossServiceImpl());
		this.roadService = (StandardRoadService) ServiceProxy//
				.getServiceProxy(new StandardRoadServiceImpl());
		this.laneService = (StandardLaneService) ServiceProxy//
				.getServiceProxy(new StandardLaneServiceImpl());
		this.lightService = (StandardLightService) ServiceProxy//
				.getServiceProxy(new StandardLightServiceImpl());

		area.setIp(AREAIP);
		area.setCrossNum(HORIZONTALNUM * VERTICALNUM);

		double AREALENGTH = ROADREALITYLENGTH * (HORIZONTALNUM + 1);// 浏览器中显示的长度
		double AREAWIDTH = ROADREALITYLENGTH * (VERTICALNUM + 1);// 浏览器中显示的宽度

		area.setLength(AREALENGTH);
		area.setWidth(AREAWIDTH);
		area.setLightNum(area.getCrossNum() * 4);
		area.setName(AREANAME);
		area.setPort(AREAPORT);
		area.setRoadNum((HORIZONTALNUM + 1) * (VERTICALNUM + 1) * HORIZONTALNUM);
		areaService.insertArea(area);
	}
}
