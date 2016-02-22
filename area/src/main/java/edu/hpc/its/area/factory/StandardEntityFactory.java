package edu.hpc.its.area.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class StandardEntityFactory {

	/************************ 区域数据 ************************/
	private static String AREANAME = "一号区域";// 区域名称

	/******************* service **********************/

	private static Map<Long, StandardRoad> roadMap = new HashMap<>();

	private static StandardAreaService areaService = null;
	private static StandardCrossService crossService = null;
	private static StandardRoadService roadService = null;
	private static StandardLaneService laneService = null;
	private static StandardLightService lightService = null;

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

}
