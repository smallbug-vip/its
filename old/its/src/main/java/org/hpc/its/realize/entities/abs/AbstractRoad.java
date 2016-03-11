package org.hpc.its.realize.entities.abs;

import java.util.HashSet;
import java.util.Set;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all road,it provides all the properties of road and can
 * be extended, the programmer can extends roads according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 1:06:15 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractRoad implements ItsEntity {

	// forward driveway, car traveling forward on these lane
	protected Set<AbstractLane> forwardLanes = new HashSet<>();
	// negative driveway, car traveling negative on these lane
	protected Set<AbstractLane> negativeLanes = new HashSet<>();

	// the road passing these intersections
	protected AbstractIntersection startIntersections;
	protected AbstractIntersection endIntersections;

	// the road belongs to this map
	protected AbstractMap map;

	public synchronized Set<AbstractLane> getForwardLanes() {
		return forwardLanes;
	}

	public synchronized Set<AbstractLane> getNegativeLanes() {
		return negativeLanes;
	}

	public AbstractIntersection getStartIntersections() {
		return startIntersections;
	}

	public void setStartIntersections(AbstractIntersection startIntersections) {
		this.startIntersections = startIntersections;
	}

	public AbstractIntersection getEndIntersections() {
		return endIntersections;
	}

	public void setEndIntersections(AbstractIntersection endIntersections) {
		this.endIntersections = endIntersections;
	}

	public AbstractMap getMap() {
		return map;
	}

	public void setMap(AbstractMap map) {
		this.map = map;
	}

}
