package org.hpc.its.realize.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.hpc.its.realize.configuration.AbstractAConfiguration;
import org.hpc.its.realize.entities.a.Intersection;
import org.hpc.its.realize.entities.a.Light;
import org.hpc.its.realize.entities.a.Map_;
import org.hpc.its.realize.entities.a.Road;
import org.hpc.its.realize.factory.AbstractEntityFactory;
import org.hpc.its.realize.factory.FactoryA;
import org.hpc.its.utils.Dom4jUtil;
import org.hpc.its.utils.JacksonUtil;

/**
 * initialize map
 * 
 * @timestamp Nov 12, 2015 10:24:25 PM
 * @author smallbug
 */
public class InitMap {

	private String message;
	private AbstractEntityFactory factory = new FactoryA();
	private Logger log = Logger.getLogger(InitMap.class);
	private Map_ map_ = null;
	Document document = null;
	private LinkedHashMap<String, Road> roadMap = new LinkedHashMap<String, Road>();
	private Map<String, Intersection> intersectionMap = new HashMap<String, Intersection>();
	private Set<CreateCar> createCars = new HashSet<>();
	private Map<Long, Light> lightMap = new HashMap<>();
	private String init;

	public InitMap(String message, AbstractAConfiguration configuration, String init) {
		this.message = message;
		this.init = init;
		InputStream in = InitMap.class//
				.getClassLoader()//
				.getResourceAsStream(init + ".xml");
		document = Dom4jUtil.getDocument(in);
		init();
	}

	private void init() {
		Map<String, Map<String, Object>> maps = JacksonUtil.json2Map(message.replaceAll("'", "\""));
		if (maps.containsKey("screenSize")) {
			Map<String, Object> m = maps.get("screenSize");
			int screen_width = Integer.parseInt((String) m.get("screen_width"));
			int screen_height = Integer.parseInt((String) m.get("screen_height"));

			Map<String, Object> mapInfo = new HashMap<String, Object>();
			mapInfo.put("screen_width", screen_width);
			mapInfo.put("screen_height", screen_height);

			map_ = factory.creatMap(mapInfo);
			log.info("initialize map SUCCESS!");

			initRoad();
			log.info("initialize roads SUCCESS!");

			initIntersection();
			log.info("initialize intersections SUCCESS!");

			changeLight();
			log.info("initialize changeLight SUCCESS!");

			initCar();
			log.info("initialize initCar SUCCESS!");
		}
	}

	@SuppressWarnings("unchecked")
	private void initCar() {
		List<Element> mapElements = document.getRootElement().elements("map");
		// traversal map
		for (Element eMap : mapElements) {
			List<Element> carElements = eMap.element("cars").elements("car");
			for (Element car : carElements) {
				Map<String, Object> carAttrs = new HashMap<>();
				List<Attribute> attrs = car.attributes();
				for (Attribute attr : attrs) {
					carAttrs.put(attr.getName(), attr.getValue());
				}
				List<Element> roadIds = car.elements("road");
				List<Road> roads = new ArrayList<>();
				for (Element e : roadIds) {
					roads.add(roadMap.get(String.valueOf(e.attributeValue("id"))));
				}
				carAttrs.put("roads", roads);
				CreateCar createCar = new CreateCar(carAttrs, factory, this,init);
				new Thread(createCar).start();
				createCars.add(createCar);
			}
		}
	}

	private void changeLight() {
		for (Map.Entry<Long, Map<Long, Light>> intersections : map_.getChangTime().entrySet()) {
			Intersection inter = intersectionMap.get(String.valueOf(intersections.getKey()));
			for (Map.Entry<Long, Light> ls : intersections.getValue().entrySet()) {
				ChangeLight ch = new ChangeLight(inter, ls.getKey(), ls.getValue());
				new Thread(ch).start();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initIntersection() {
		List<Element> mapElements = document.getRootElement().elements("map");
		// traversal map
		for (Element eMap : mapElements) {
			List<Element> intersectionElements = eMap.element("intersections").elements("intersection");
			// traversal intersections of a map
			for (Element eIntersection : intersectionElements) {
				Map<String, Object> intersectionAttrs = new HashMap<>();
				List<Attribute> attrs = eIntersection.attributes();
				// attribute of intersections put into HashMap
				for (Attribute attr : attrs) {
					intersectionAttrs.put(attr.getName(), attr.getValue());
				}
				if ("null".equals(String.valueOf(intersectionAttrs.get("refer")))) {
					intersectionAttrs.put("xPoint", map_.getWidth() / 2);
					intersectionAttrs.put("yPoint", map_.getHeight() / 2);
				} else {
					intersectionAttrs.put("refer", roadMap.get(intersectionAttrs.get("refer")));
				}
				Intersection intersection = factory.creatIntersection(intersectionAttrs);
				List<Element> roadsList = eIntersection.elements("road");
				// every road of intersection
				for (Element eRoad : roadsList) {
					Road tRoad = roadMap.get(eRoad.attribute("id").getValue());
					Element eLight = eRoad.element("light");
					Map<String, Object> lightAttrs = new HashMap<>();
					List<Attribute> InAttrs = eLight.attributes();
					for (Attribute attr : InAttrs) {
						lightAttrs.put(attr.getName(), attr.getValue());
					}
					lightAttrs.put("road", tRoad);
					lightAttrs.put("intersection", intersection);
					Light light = factory.creatLight(lightAttrs);

					Map<Long, Light> changTime = map_.getChangTime()//
							.get(intersection.getIntersectionId());
					if (changTime == null) {
						Map<Long, Light> ms = new HashMap<>();
						ms.put(light.getLightId(), light);

						// TODO
						map_.getChangTime().put(intersection.getIntersectionId(), ms);
					} else {
						if (changTime.get(light.getLightId()) == null) {
							changTime.put(light.getLightId(), light);
							map_.getChangTime().put(intersection.getIntersectionId(), changTime);
						}
					}
					lightMap.put(light.getLightId(), light);
				}
				intersectionMap.put(String.valueOf(intersection.getIntersectionId()), intersection);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initRoad() {
		List<Element> mapElements = document.getRootElement().elements("map");
		// traversal map
		for (Element eMap : mapElements) {
			List<Element> roadElements = eMap.element("roads").elements("road");
			// traversal road of a map
			for (Element eRoad : roadElements) {
				Map<String, Object> mapAttrs = new HashMap<>();
				List<Attribute> attrs = eRoad.attributes();
				// attribute of road put into HashMap
				for (Attribute attr : attrs) {
					mapAttrs.put(attr.getName(), attr.getValue());
				}
				if ("null".equals(String.valueOf(mapAttrs.get("refer")))) {
					mapAttrs.put("xPoint", map_.getWidth() / 2);
					mapAttrs.put("yPoint", map_.getHeight() / 2);
				} else {
					mapAttrs.put("roadMap", roadMap);
				}
				Road road = factory.creatRoad(mapAttrs);
				// obtain lane list
				List<Element> laneElements = eRoad.elements("lane");
				// traversal lane
				for (Element eLane : laneElements) {
					Map<String, Object> eAttrs = new HashMap<>();
					List<Attribute> laneAttrs = eLane.attributes();
					for (Attribute attr : laneAttrs) {
						eAttrs.put(attr.getName(), attr.getValue());
					}
					eAttrs.put("road", road);
					factory.creatLane(eAttrs);
				}
				road.setMap(map_);
				map_.getRoads().add(road);
				roadMap.put(String.valueOf(road.getRoadId()), road);
			} // traversal road
		}
	}

	public void getMessage() {

	}

	/****************************** get entity *****************************/

	public Map_ getMap_() {
		return map_;
	}

	public LinkedHashMap<String, Road> getRoadMap() {
		return roadMap;
	}

	public Map<String, Intersection> getIntersectionMap() {
		return intersectionMap;
	}

	public Map<Long, Light> getLightMap() {
		return lightMap;
	}

	public synchronized Set<CreateCar> getCreateCars() {
		return createCars;
	}

}
