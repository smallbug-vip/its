package org.hpc.its.realize.entities.abs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all lane,it provides all the properties of lane and can
 * be extended, the programmer can extends lanes according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 1:17:53 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractLane implements ItsEntity {

	// lane belong to this road
	protected AbstractRoad road;

	// there are many cars on this driveway current
	protected Set<AbstractCar> currentCars = Collections.synchronizedSet(new HashSet<>());

	public AbstractRoad getRoad() {
		return road;
	}

	public void setRoad(AbstractRoad road) {
		this.road = road;
	}

	public synchronized Set<AbstractCar> getCurrentCars() {
		return currentCars;
	}

	public synchronized Set<AbstractCar> getCurrentCarsInfo() {
		Set<AbstractCar> currentCarsInfo = null;
			currentCarsInfo = new HashSet<>(currentCars);
		return currentCarsInfo;
	}

}
