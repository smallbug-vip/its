package org.hpc.its.realize.factory;

import java.util.Map;

import org.hpc.its.realize.entities.abs.AbstractCar;
import org.hpc.its.realize.entities.abs.AbstractIntersection;
import org.hpc.its.realize.entities.abs.AbstractLane;
import org.hpc.its.realize.entities.abs.AbstractLight;
import org.hpc.its.realize.entities.abs.AbstractMap;
import org.hpc.its.realize.entities.abs.AbstractRoad;

public interface AbstractEntityFactory {

	/**
	 * create Map
	 * 
	 * @return instantiated map
	 */
	public <T extends AbstractMap> T creatMap(Map<String, Object> map);

	/**
	 * create Light
	 * 
	 * @return instantiated light
	 */
	public <T extends AbstractLight> T creatLight(Map<String, Object> map);

	/**
	 * create Road
	 * 
	 * @return instantiated road
	 */
	public <T extends AbstractRoad> T creatRoad(Map<String, Object> map);

	/**
	 * create Intersection
	 * 
	 * @return instantiated Intersection
	 */
	public <T extends AbstractIntersection> T creatIntersection(Map<String, Object> map);

	/**
	 * create Car
	 * 
	 * @return instantiated Car
	 */
	public <T extends AbstractCar> T creatCar(Map<String, Object> map);

	/**
	 * create Lane
	 * 
	 * @return instantiated Lane
	 */
	public <T extends AbstractLane> T creatLane(Map<String, Object> map);
}
