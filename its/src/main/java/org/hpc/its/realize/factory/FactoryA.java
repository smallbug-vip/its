package org.hpc.its.realize.factory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hpc.its.realize.entities.a.Car;
import org.hpc.its.realize.entities.a.Intersection;
import org.hpc.its.realize.entities.a.Lane;
import org.hpc.its.realize.entities.a.Light;
import org.hpc.its.realize.entities.a.Map_;
import org.hpc.its.realize.entities.a.Road;
import org.hpc.its.realize.entities.abs.AbstractCar;
import org.hpc.its.realize.entities.abs.AbstractIntersection;
import org.hpc.its.realize.entities.abs.AbstractLane;
import org.hpc.its.realize.entities.abs.AbstractLight;
import org.hpc.its.realize.entities.abs.AbstractMap;
import org.hpc.its.realize.entities.abs.AbstractRoad;

@SuppressWarnings("unchecked")
public class FactoryA implements AbstractEntityFactory {

	Logger log = Logger.getLogger(FactoryA.class);

	@Override
	public <T extends AbstractMap> T creatMap(Map<String, Object> map) {
		Map_ m = new Map_();
		m.setWidth((int) map.get("screen_width"));
		m.setHeight((int) map.get("screen_height"));
		return (T) m;
	}

	@Override
	public <T extends AbstractLight> T creatLight(Map<String, Object> map) {
		Intersection intersection = (Intersection) map.get("intersection");
		Road road = (Road) map.get("road");
		// System.out.println("roadid = " + road.getRoadId() + " -> " +
		// "road -> " + road);
		Light light = new Light();
		light.setAngle(road.getAngle());
		light.setLightId(Long.parseLong((String) map.get("id")));
		light.setRoad(road);
		light.setGroup(Integer.parseInt((String) map.get("group")));
		light.setRed(Integer.parseInt((String) map.get("red")));
		light.setGreen(Integer.parseInt((String) map.get("green")));
		light.setIntersection(intersection);
		light.setState(Integer.parseInt((String) map.get("state")));

		// the road start intersection is this intersection
		if (Math.abs(intersection.getCenterXPoint() - road.getStartXPoint()) < 30 && //
				Math.abs(intersection.getCenterYPoint() - road.getStartYPoint()) < 30) {
			light.setCenterXPoint(road.getDrawStartXPoint());
			light.setCenterYPoint(road.getDrawStartYPoint());
			light.setPort(0);
			road.setStartLight(light);
			road.setStartIntersections(intersection);
		} else {
			// the road end intersection is this intersection
			light.setCenterXPoint(road.getDrawEndXPoint());
			light.setCenterYPoint(road.getDrawEndYPoint());
			light.setPort(1);
			road.setEndLight(light);
			road.setEndIntersections(intersection);
		}
		light.initCoordinate();
		// build relation
		intersection.getRoads().put(road.getRoadId(), road);
		intersection.getLights().put(light.getLightId(), light);
		light.setIntersection(intersection);
		return (T) light;
	}

	@Override
	public <T extends AbstractRoad> T creatRoad(Map<String, Object> map) {
		Road road = null;
		if (map.get("roadMap") == null) {
			road = new Road(//
					(int) map.get("xPoint"), (int) map.get("yPoint"), //
					Integer.parseInt((String) map.get("length")),//
					Integer.parseInt((String) map.get("angle")));
		} else {
			LinkedHashMap<String, Road> roadMap = (LinkedHashMap<String, Road>) map.get("roadMap");
			Road r = roadMap.get((String) map.get("refer"));
			road = new Road(//
					r.getEndXPoint(), r.getEndYPoint(), //
					Integer.parseInt((String) map.get("length")),//
					Integer.parseInt((String) map.get("angle")));
		}
		road.setRoadId(Long.parseLong((String) map.get("id")));
		return (T) road;
	}

	@Override
	public <T extends AbstractIntersection> T creatIntersection(Map<String, Object> map) {
		Intersection intersection = new Intersection();
		intersection.setIntersectionId(Long.parseLong((String) map.get("id")));
		if ("null".equals(map.get("refer"))) {
			intersection.setCenterXPoint((int) map.get("xPoint"));
			intersection.setCenterYPoint((int) map.get("yPoint"));
		} else {
			Road road = (Road) map.get("refer");
			intersection.setCenterXPoint(road.getEndXPoint());
			intersection.setCenterYPoint(road.getEndYPoint());
		}
		return (T) intersection;
	}

	@Override
	public <T extends AbstractCar> T creatCar(Map<String, Object> map) {
		Car car = new Car();
		car.setCarId(Long.parseLong((String) map.get("id"))//
				+ (int) map.get("amount"));
		car.setImage((String) map.get("image"));
		car.setSpeed(Integer.parseInt((String) map.get("speed")));
		car.getRoads().addAll((List<Road>) map.get("roads"));
		car.setCount(0);
		car.setStartDistance(Integer.parseInt((String) map.get("startDistance")));
		car.setEndDistance(Integer.parseInt((String) map.get("endDistance")));

		Random random = new Random();
		Road road = car.getRoads().get(0);
		car.setAngle(road.getAngle());
		int randomInt = random.nextInt(road.getForwardLanes().size());
		AbstractLane[] as = {};
		as = car.getRoads().get(0).getNegativeLanes().toArray(as);
		Lane lane = (Lane) as[randomInt];
		car.setCurrentLine(lane);
		car.setTime(System.currentTimeMillis());
		car.setCurrentXPoint(lane.getEndXPoint()
				+ (int) (car.getStartDistance() * Math.cos((Math.PI * (180 - road.getAngle()) / 180))));
		car.setCurrentYPoint(lane.getEndYPoint()
				- (int) (car.getStartDistance() * Math.sin((Math.PI * (180 - road.getAngle()) / 180))));
		return (T) car;
	}

	@Override
	public <T extends AbstractLane> T creatLane(Map<String, Object> map) {
		Road road = (Road) map.get("road");
		Lane lane = new Lane();
		lane.setRoad(road);
		lane.setLaneId(Long.parseLong((String) map.get("id")));
		lane.setWidth(Integer.parseInt((String) map.get("width")));
		if (Long.parseLong((String) map.get("id")) % 2 == 0) {
			road.getForwardLanes().add(lane);
			lane.setStartXPoint(//
			(int) (road.getDrawStartXPoint() - lane.getWidth() * (road.getForwardLanes().size())
					* Math.sin(Math.PI * road.getAngle() / 180)));
			lane.setEndXPoint(//
			(int) (road.getDrawEndXPoint() - lane.getWidth() * (road.getForwardLanes().size())
					* Math.sin(Math.PI * road.getAngle() / 180)));
			lane.setStartYPoint(//
			(int) (road.getDrawStartYPoint() + lane.getWidth() * (road.getForwardLanes().size())
					* Math.cos(Math.PI * road.getAngle() / 180)));
			lane.setEndYPoint(//
			(int) (road.getDrawEndYPoint() + lane.getWidth() * (road.getForwardLanes().size())
					* Math.cos(Math.PI * road.getAngle() / 180)));
		} else {
			road.getNegativeLanes().add(lane);
			lane.setStartXPoint(//
			(int) (road.getDrawStartXPoint() + lane.getWidth() * (road.getForwardLanes().size() + 1)
					* Math.sin(Math.PI * road.getAngle() / 180)));
			lane.setEndXPoint(//
			(int) (road.getDrawEndXPoint() + lane.getWidth() * (road.getForwardLanes().size() + 1)
					* Math.sin(Math.PI * road.getAngle() / 180)));
			lane.setStartYPoint(//
			(int) (road.getDrawStartYPoint() - lane.getWidth() * (road.getForwardLanes().size() + 1)
					* Math.cos(Math.PI * road.getAngle() / 180)));
			lane.setEndYPoint(//
			(int) (road.getDrawEndYPoint() - lane.getWidth() * (road.getForwardLanes().size() + 1)
					* Math.cos(Math.PI * road.getAngle() / 180)));
		}
		return (T) lane;
	}
}
