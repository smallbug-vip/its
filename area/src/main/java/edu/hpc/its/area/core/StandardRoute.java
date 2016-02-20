package edu.hpc.its.area.core;

import edu.hpc.its.area.Route;

/**
 * 行程表
 * 
 * @timestamp Feb 13, 2016 4:24:11 PM
 * @author smallbug
 */
public class StandardRoute extends StandardEntity implements Route {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8827585781384296364L;

	private Double speed;
	private String routeTable;

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getRouteTable() {
		return routeTable;
	}

	public void setRouteTable(String routeTable) {
		this.routeTable = routeTable;
	}

}
