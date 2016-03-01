package edu.hpc.its.area.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.culster.AreaConnector;
import edu.hpc.its.area.factory.StandardEntityFactory;
import edu.hpc.its.area.listener.CarComeOutListener;
import edu.hpc.its.area.listener.CarRunListener;
import edu.hpc.its.area.listener.InitCarListener;
import edu.hpc.its.area.listener.StartLightListener;
import edu.hpc.its.area.webservice.AreaInfoServiceImpl;

public class Bootstrap {

	public static void main(String[] args) {
		readProperties();// 初始化全局变量
		// 判断是否需要初始化数据库
		if (args != null && args.length > 0) {
			if ("init".equals(args[0])) {
				System.out.println("Are you sure you want to init database?(Y/N)");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String parm;
				try {
					parm = reader.readLine();
					if (parm != null && ("y".equals(parm.trim()) || "Y".equals(parm.trim()))) {
						InitDatabase init = new InitDatabase();
						init.init();
						System.out.println("INIT DATA SUCCESS !");
						return;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		StandardArea area = StandardEntityFactory.getArea();
		area.addLifecycleListener(new StartLightListener())//
				.addLifecycleListener(new InitCarListener())//
				.addLifecycleListener(new CarComeOutListener())//
				.addLifecycleListener(new CarRunListener());
		try {
			area.init();
			area.start();
			// printInfo(area);
			AreaConnector connector = new AreaConnector(area);
			connector.init();
			connector.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Endpoint.publish("http://" + Constant.AREAIP + ":8888/area", new AreaInfoServiceImpl());
	}

	/**
	 * 读取配置文件，初始化各个全局变量的值，不配置可以使用默认值
	 * 
	 * @timestamp Feb 24, 2016 4:01:33 PM
	 */
	private static void readProperties() {
		InputStream in = Bootstrap.class.getClassLoader().getResourceAsStream("info.properties");
		if (in != null) {
			try {
				Properties prop = new Properties();
				String str = null;
				prop.load(in);
				str = prop.getProperty("ONECM");
				if (str != null) {
					Constant.ONECM = new Double(str);
				}
				str = prop.getProperty("COMPRESS");
				if (str != null) {
					Constant.COMPRESS = new Double(str);
				}
				str = prop.getProperty("AREAIP");
				if (str != null) {
					Constant.AREAIP = str;
				}
				str = prop.getProperty("AREANAME");
				if (str != null) {
					Constant.AREANAME = str;
				}
				str = prop.getProperty("AREAPORT");
				if (str != null) {
					Constant.AREAPORT = new Integer(str);
				}
				str = prop.getProperty("HORIZONTALNUM");
				if (str != null) {
					Constant.HORIZONTALNUM = new Integer(str);
				}
				str = prop.getProperty("VERTICALNUM");
				if (str != null) {
					Constant.VERTICALNUM = new Integer(str);
				}
				str = prop.getProperty("ROADLENGTH");
				if (str != null) {
					Constant.ROADLENGTH = new Double(str);
				}
				str = prop.getProperty("LANENUM");
				if (str != null) {
					Constant.LANENUM = new Integer(str);
				}
				str = prop.getProperty("LANEWIDE");
				if (str != null) {
					Constant.LANEWIDE = new Double(str);
				}
				str = prop.getProperty("READTIME");
				if (str != null) {
					Constant.READTIME = new Double(str);
				}
				str = prop.getProperty("GREENTIME");
				if (str != null) {
					Constant.GREENTIME = new Double(str);
				}
				str = prop.getProperty("MINPROCESSORS");
				if (str != null) {
					Constant.MINPROCESSORS = new Integer(str);
				}
				str = prop.getProperty("MAXPROCESSORS");
				if (str != null) {
					Constant.MAXPROCESSORS = new Integer(str);
				}
				str = prop.getProperty("CARCOMEOOUT");
				if (str != null) {
					Constant.CARCOMEOOUT = new Long(str);
				}
				str = prop.getProperty("CARCOMEOOUTNUM");
				if (str != null) {
					Constant.CARCOMEOOUTNUM = new Integer(str);
				}
				str = prop.getProperty("RODENUMONETHREAD");
				if (str != null) {
					Constant.RODENUMONETHREAD = new Integer(str);
				}
				str = prop.getProperty("EXPID");
				if (str != null) {
					Constant.EXPID = str;
				}
				str = prop.getProperty("ISNOTEINFO");
				if (str != null) {
					Constant.ISNOTEINFO = new Integer(str);
				}
				Constant.caculate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * start之后结合各类的toString方法，用于打印系统开启后个参数的初始化信息，测试时使用
	 * 
	 * @timestamp Feb 21, 2016 3:08:06 PM
	 * @param area
	 */
	/*
	 * private static void printInfo(StandardArea area) { for (StandardCross
	 * cross : area.getCrosses()) { System.out.println("cross id -> " + cross);
	 * for (Entry<Direction, StandardLight> lights :
	 * cross.getLights().entrySet()) { StandardLight light = lights.getValue();
	 * System.out.println("light -> " + light); } for (Entry<Direction,
	 * StandardRoad> roads : cross.getRoads().entrySet()) { StandardRoad road =
	 * roads.getValue(); System.out.println("road -> " + road); for
	 * (Entry<Integer, Lane> oneLane : road.getOneLane().entrySet()) {
	 * StandardLane lane = (StandardLane) oneLane.getValue();
	 * System.out.println("lane -> " + lane); } for (Entry<Integer, Lane>
	 * otherLane : road.getOtherLane().entrySet()) { StandardLane lane =
	 * (StandardLane) otherLane.getValue(); System.out.println("lane2 -> " +
	 * lane); } } } }
	 */
}
