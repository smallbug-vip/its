package edu.hpc.its.area.core;

import edu.hpc.its.area.Entity;

/**
 * 抽象实体类，StandardMap，StandardCar，StandardRoad等，实体类都是这个类的子类
 * 
 * @timestamp Feb 16, 2016 9:17:40 PM
 * @author smallbug
 */
public class StandardEntity extends StandardMBean implements Entity {

	private Long id;// ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
