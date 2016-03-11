package org.hpc.its.realize.entities.abs;

import java.util.HashMap;
import java.util.Map;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all intersection,it provides all the properties of
 * intersection and can be extended, the programmer can extends intersections
 * according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 1:22:01 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractIntersection implements ItsEntity {

	// the intersection that is composed of these roads
	protected Map<Long, AbstractRoad> roads = new HashMap<>();

	// this intersection include these traffic lights
	protected Map<Long, AbstractLight> lights = new HashMap<>();

	public Map<Long, AbstractRoad> getRoads() {
		return roads;
	}

	public void setRoads(Map<Long, AbstractRoad> roads) {
		this.roads = roads;
	}

	public Map<Long, AbstractLight> getLights() {
		return lights;
	}

	public void setLights(Map<Long, AbstractLight> lights) {
		this.lights = lights;
	}

}
