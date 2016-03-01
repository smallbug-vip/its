package edu.hpc.its.area.factory;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.core.StandardRoute;
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

public class StandardEntityFactory {

	/************************ 区域数据 ************************/
	private static String AREANAME = Constant.AREANAME;// 区域名称

	private static Map<Long, StandardRoad> roadMap = new HashMap<>();
	private static Map<Long, StandardLight> lightMap = new HashMap<>();
	private static Map<String, StandardRoad> roadSignMap = new HashMap<>();
	private static List<StandardCar> cars = new ArrayList<>();
	private static Set<Double> speeds = new HashSet<>();
	private static Set<String> routeString = new HashSet<>();
	private static List<StandardCar> GlobalCars = new LinkedList<>();

	/******************* service **********************/
	private static StandardAreaService areaService = null;
	private static StandardCrossService crossService = null;
	private static StandardRoadService roadService = null;
	private static StandardLaneService laneService = null;
	private static StandardLightService lightService = null;
	private static StandardCarService carService = null;
	private static StandardRouteService routeService = null;

	static {
		areaService = (StandardAreaService) ServiceProxy//
				.getServiceProxy(new StandardAreaServiceImpl());
		crossService = (StandardCrossService) ServiceProxy//
				.getServiceProxy(new StandardCrossServiceImpl());
		roadService = (StandardRoadService) ServiceProxy//
				.getServiceProxy(new StandardRoadServiceImpl());
		laneService = (StandardLaneService) ServiceProxy//
				.getServiceProxy(new StandardLaneServiceImpl());
		lightService = (StandardLightService) ServiceProxy//
				.getServiceProxy(new StandardLightServiceImpl());
		carService = (StandardCarService) ServiceProxy//
				.getServiceProxy(new StandardCarServiceImpl());
		routeService = (StandardRouteService) ServiceProxy//
				.getServiceProxy(new StandardRouteServiceImpl());
	}

	/**
	 * 获取区域对象
	 * 
	 * @timestamp Feb 20, 2016 6:10:00 PM
	 * @return
	 */
	public static StandardArea getArea() {
		return areaService.selectAreaByName(AREANAME);
	}

	/**
	 * 获取区域对应的所有路口
	 * 
	 * @timestamp Feb 20, 2016 6:31:45 PM
	 * @param areaId
	 * @return
	 */
	public static List<StandardCross> getCrosses(Long areaId) {
		return crossService.getStandardCrosses(areaId);
	}

	/**
	 * 获取路口相关的路
	 * 
	 * @timestamp Feb 20, 2016 7:09:01 PM
	 * @param id
	 * @return
	 */
	public static List<StandardRoad> getRoads(Long crossId) {
		return roadService.getStandardCrosses(crossId);
	}

	/**
	 * 获取路口相关的信号灯
	 * 
	 * @timestamp Feb 20, 2016 7:09:29 PM
	 * @param id
	 * @return
	 */
	public static List<StandardLight> getLight(Long crossId) {
		return lightService.getStandardLight(crossId);
	}

	/**
	 * 获取指定路的车道
	 * 
	 * @timestamp Feb 20, 2016 10:23:04 PM
	 * @param id
	 * @return
	 */
	public static List<StandardLane> getLanes(Long roadId) {
		return laneService.getStandardLanes(roadId);
	}

	//////////////////// 全局road//////////////////////////
	//// ID索引
	public static void addRoad(StandardRoad road) {
		roadMap.put(road.getId(), road);
	}

	public static StandardRoad getRoad(long id) {
		return roadMap.get(id);
	}

	public static boolean contains(StandardRoad road) {
		return roadMap.containsKey(road.getId());
	}

	public static Map<Long, StandardRoad> getRoadMap() {
		return roadMap;
	}

	//// sign索引
	public static void addSignRoad(StandardRoad road) {
		roadSignMap.put(road.getSign(), road);
	}

	public static StandardRoad getSignRoad(String sign) {
		return roadSignMap.get(sign);
	}

	public static boolean containSignRoad(StandardRoad road) {
		return roadMap.containsKey(road.getSign());
	}

	public static Map<String, StandardRoad> getSignRoadMap() {
		return roadSignMap;
	}

	/**
	 * 初始化sign索引的Map，该map用于车行走时根据路径获取路
	 * 
	 * @timestamp Feb 26, 2016 12:54:55 PM
	 */
	public static void initSignRoadMap() {
		for (Entry<Long, StandardRoad> r : roadMap.entrySet()) {
			roadSignMap.put(r.getValue().getSign(), r.getValue());
		}
	}

	//////////////////// 全局light//////////////////////////
	public static void addLight(StandardLight light) {
		lightMap.put(light.getId(), light);
	}

	public static StandardLight getLight(long id) {
		return lightMap.get(id);
	}

	public static boolean contains(StandardLight light) {
		return lightMap.containsKey(light.getId());
	}

	public static Map<Long, StandardLight> getLightMap() {
		return lightMap;
	}

	//////////////////// 全局car//////////////////////////
	/**
	 * 初始化
	 * 
	 * @timestamp Feb 26, 2016 10:47:08 AM
	 * @param areaId
	 */
	public static void initCars(Long areaId) {
		cars = carService.getStandardCars(areaId);
	}

	/**
	 * 获取
	 * 
	 * @timestamp Feb 26, 2016 10:47:20 AM
	 * @return
	 */
	public static List<StandardCar> getCars() {

		return cars;
	}

	public static StandardCar careateCar() {
		Random random = new Random();
		StandardCar carType = cars.get(random.nextInt(cars.size()));
		StandardCar car = new StandardCar();
		car.setAreaId(carType.getAreaId());
		car.setImage(carType.getImage());
		return car;
	}

	//////////////////// 全局route//////////////////////////
	public static void initRoutes(Long areaId) {
		List<StandardRoute> routes = routeService.getStandardRoutes(areaId);
		for (StandardRoute r : routes) {
			speeds.add(r.getSpeed());
			routeString.add(r.getRouteTable());
		}
	}

	public static Set<Double> getSpeeds() {
		return speeds;
	}

	public static Set<String> getRouteString() {
		return routeString;
	}

	//////////////////// 全局car//////////////////////////
	public static void registerCar(StandardCar car) {
		synchronized (GlobalCars) {
			GlobalCars.add(car);
		}
	}

	public static void removeCar(StandardCar car) {
		synchronized (GlobalCars) {
			GlobalCars.remove(car);
		}
	}

	public static String getCarInfoJson() {
		synchronized (GlobalCars) {
			String str = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BufferedOutputStream bo = new BufferedOutputStream(bos);
			for (StandardCar c : GlobalCars) {
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					FilterProvider filters = new SimpleFilterProvider()//
							.addFilter(c.getClass().getName(), //
									SimpleBeanPropertyFilter.filterOutAllExcept(//
											"xxPoint", "yyPoint", "horizontal", "image", "angle"));
					objectMapper.setFilters(filters);
					objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 8199979396340549207L;

						@Override
						public Object findFilterId(AnnotatedClass ac) {
							return ac.getName();
						}

					});
					objectMapper.writeValue(bo, c);
					bo.write(",".getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {
				bo.flush();
				str = bos.toString();
				bo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
