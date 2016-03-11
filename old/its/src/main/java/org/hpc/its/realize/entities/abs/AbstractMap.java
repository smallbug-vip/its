package org.hpc.its.realize.entities.abs;

import java.util.HashSet;
import java.util.Set;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all maps,it provides all the properties of map and can be
 * extended, the programmer can extends map according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 12:59:10 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractMap implements ItsEntity {

	// the map include these roads
	protected Set<AbstractRoad> roads = new HashSet<>();

	public Set<AbstractRoad> getRoads() {
		return roads;
	}

}
