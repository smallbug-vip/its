package edu.hpc.its.area.core;

import java.rmi.Remote;

import edu.hpc.its.area.Entity;

/**
 * 抽象实体类，StandardMap，StandardCar，StandardRoad等，实体类都是这个类的子类
 * 
 * @timestamp Feb 16, 2016 9:17:40 PM
 * @author smallbug
 */
public class StandardEntity extends StandardMBean implements Entity, Remote {

	private Long id;// ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardEntity other = (StandardEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
