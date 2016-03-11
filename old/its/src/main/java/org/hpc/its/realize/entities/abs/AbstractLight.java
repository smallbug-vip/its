package org.hpc.its.realize.entities.abs;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all light,it provides all the properties of light and can
 * be extended, the programmer can extends lights according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 1:14:31 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractLight implements ItsEntity {

	// the light belongs to this intersection
	protected AbstractIntersection intersection;

	// the light belongs to this road
	protected AbstractRoad road;

	public AbstractIntersection getIntersection() {
		return intersection;
	}

	public void setIntersection(AbstractIntersection intersection) {
		this.intersection = intersection;
	}

	public AbstractRoad getRoad() {
		return road;
	}

	public void setRoad(AbstractRoad road) {
		this.road = road;
	}

}
