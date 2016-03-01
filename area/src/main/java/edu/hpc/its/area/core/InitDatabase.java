package edu.hpc.its.area.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Cross;
import edu.hpc.its.area.dao.mysql.ServiceProxy;
import edu.hpc.its.area.service.StandardAreaService;
import edu.hpc.its.area.service.StandardCarService;
import edu.hpc.its.area.service.StandardCrossService;
import edu.hpc.its.area.service.StandardLaneService;
import edu.hpc.its.area.service.StandardLightService;
import edu.hpc.its.area.service.StandardRoadService;
import edu.hpc.its.area.service.StandardRouteService;
import edu.hpc.its.area.service.Impl.StandardAreaServiceImpl;
import edu.hpc.its.area.service.Impl.StandardCarServiceImpl;
import edu.hpc.its.area.service.Impl.StandardCrossServiceImpl;
import edu.hpc.its.area.service.Impl.StandardLaneServiceImpl;
import edu.hpc.its.area.service.Impl.StandardLightServiceImpl;
import edu.hpc.its.area.service.Impl.StandardRoadServiceImpl;
import edu.hpc.its.area.service.Impl.StandardRouteServiceImpl;

public class InitDatabase {

	/******************* service **********************/

	private StandardAreaService areaService = null;
	private StandardCrossService crossService = null;
	private StandardRoadService roadService = null;
	private StandardLaneService laneService = null;
	private StandardLightService lightService = null;
	private StandardRouteService routeService = null;
	private StandardCarService carService = null;

	/******************* cache **********************/

	private Map<String, Cross> crossCache = new HashMap<>();

	/******************* main **********************/
	public void init() {
		InitDatabase init = new InitDatabase();
		StandardArea area = new StandardArea();
		init.initArea(area);
		init.initCross(area);
		init.initRoute(area);
		init.initCar(area);
	}

	/**
	 * 初始化车
	 * 
	 * @timestamp Feb 24, 2016 1:43:57 PM
	 * @param area
	 */
	private void initCar(StandardArea area) {
		for (char a = 'a'; a <= 'e'; a++) {
			StandardCar car = new StandardCar();
			String image = "127.0.0.1:8080/center/image/" + String.valueOf(a) + ".png";
			car.setImage(image);
			car.setAreaId(area.getId());
			carService.insertCar(car);
		}

	}

	/**
	 * 初始路线
	 * 
	 * @timestamp Feb 23, 2016 10:21:32 PM
	 */

	private void initRoute(StandardArea area) {
		int[] num1 = new int[Constant.HORIZONTALNUM];
		int[] num2 = new int[Constant.VERTICALNUM];
		///////////////////////////// 路线图第一部分
		for (int i = 1; i <= Constant.HORIZONTALNUM; i++) {
			StringBuilder sbH = new StringBuilder();
			for (int j = 1; j <= Constant.VERTICALNUM + 1; j++) {
				sbH.append("H|" + i + "|" + j + ",");
				if (i == 1 && j <= (Constant.VERTICALNUM)) {
					num2[j - 1] = i * 10 + j;
				}
			}
			String h = sbH.toString().substring(0, sbH.toString().length() - 1);
			reverse(h, area);
		}
		///////////////////////////// 路线图第二部分
		for (int i = 1; i <= Constant.VERTICALNUM; i++) {
			StringBuilder sbN = new StringBuilder();
			for (int j = 1; j <= Constant.HORIZONTALNUM + 1; j++) {
				sbN.append("N|" + j + "|" + i + ",");
				if (i == 1 && j <= Constant.HORIZONTALNUM) {
					num1[j - 1] = i + j * 10;
				}
			}
			String n = sbN.toString().substring(0, sbN.toString().length() - 1);
			reverse(n, area);
		}
	}

	/**
	 * 翻转线路，生成逆向行驶数组，并保存
	 * 
	 * @timestamp Feb 23, 2016 11:41:56 PM
	 * @param h
	 */
	private void reverse(String h, StandardArea area) {
		String[] rs = h.split(",");
		List<String> list = new ArrayList<>();
		Collections.addAll(list, rs);

		String one = list.toString()//
				.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
		Collections.reverse(list);
		String two = list.toString()//
				.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
		for (int i = 30; i <= 100; i = i + 10) {// 30KM/H到100KM/H不等
			StandardRoute route = new StandardRoute();
			route.setSpeed(Double.parseDouble(String.format("%.2f", (i / 3.6 * 100))));// 速度单位-->cm/s
			route.setRouteTable(one);
			route.setAreaId(area.getId());
			routeService.insertRoute(route);
			route = new StandardRoute();
			route.setSpeed(Double.parseDouble(String.format("%.2f", (i / 3.6 * 100))));// 速度单位-->cm/s
			route.setRouteTable(two);
			route.setAreaId(area.getId());
			routeService.insertRoute(route);
		}

	}

	/**
	 * 初始化交叉路口
	 * 
	 * @timestamp Feb 19, 2016 9:41:44 PM
	 */
	private void initCross(StandardArea area) {

		for (int i = 1; i <= Constant.VERTICALNUM; i++) {
			for (int j = 1; j <= Constant.HORIZONTALNUM; j++) {
				StandardCross cross = new StandardCross();
				cross.setHorizontalNum(j);
				cross.setOrdinateNum(i);
				cross.setStandardArea(area);
				cross.setXxPoint(j * Constant.ROADREALITYLENGTH);
				cross.setYyPoint(i * Constant.ROADREALITYLENGTH);
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
			light.setRed(Constant.READTIME);
			light.setGreen(Constant.GREENTIME);
			light.setSize(Constant.LANENUM == 1 //
					? Constant.LANEWIDE / Constant.COMPRESS * Constant.ONECM //
					: (Constant.LANEWIDE / Constant.COMPRESS * Constant.ONECM) * (Constant.LANENUM - 1));
			light.setDirection(d);
			light.setLightState(d.getDirection() > 2 ? 1 : 0);
			double part = Constant.LANENUM * (Constant.LANEWIDE / Constant.COMPRESS * Constant.ONECM);
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
		hRoad.setRealityLength(Constant.ROADLENGTH);
		roadService.insertRoad(hRoad);
		initLane(hRoad);
		if (j == Constant.HORIZONTALNUM) {
			StandardRoad aRoad = new StandardRoad();
			aRoad.setHorizontalNum(j + 1);
			aRoad.setOrdinateNum(i);
			aRoad.setHorizontal(true);
			aRoad.setOneCross(cross);
			aRoad.setOtherCross(null);
			aRoad.setOpen(true);
			aRoad.setRealityLength(Constant.ROADLENGTH);
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
		oRoad.setRealityLength(Constant.ROADLENGTH);
		roadService.insertRoad(oRoad);
		initLane(oRoad);
		if (i == Constant.VERTICALNUM) {
			StandardRoad aRoad = new StandardRoad();
			aRoad.setHorizontalNum(j);
			aRoad.setOrdinateNum(i + 1);
			aRoad.setHorizontal(false);
			aRoad.setOneCross(cross);
			aRoad.setOtherCross(null);
			aRoad.setOpen(true);
			aRoad.setRealityLength(Constant.ROADLENGTH);
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

		for (int i = -Constant.LANENUM; i <= Constant.LANENUM; i++) {
			if (i != 0) {
				StandardLane lane = new StandardLane();
				lane.setRealityLength(hRoad.getRealityLength());
				lane.setRealityWidth(Constant.LANEWIDE);
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
		this.routeService = (StandardRouteService) ServiceProxy//
				.getServiceProxy(new StandardRouteServiceImpl());
		this.carService = (StandardCarService) ServiceProxy//
				.getServiceProxy(new StandardCarServiceImpl());
		area.setIp(Constant.AREAIP);
		area.setCrossNum(Constant.HORIZONTALNUM * Constant.VERTICALNUM);

		double AREALENGTH = Constant.ROADREALITYLENGTH * (Constant.HORIZONTALNUM + 1);// 浏览器中显示的长度
		double AREAWIDTH = Constant.ROADREALITYLENGTH * (Constant.VERTICALNUM + 1);// 浏览器中显示的宽度

		area.setLength(AREALENGTH);
		area.setWidth(AREAWIDTH);
		area.setLightNum(area.getCrossNum() * 4);
		area.setName(Constant.AREANAME);
		area.setPort(Constant.AREAPORT);
		area.setRoadNum((Constant.HORIZONTALNUM + 1) * (Constant.VERTICALNUM + 1) * Constant.HORIZONTALNUM);
		areaService.insertArea(area);
	}
}
