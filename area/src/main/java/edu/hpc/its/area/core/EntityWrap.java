package edu.hpc.its.area.core;

import java.util.HashMap;
import java.util.Map;

import edu.hpc.its.area.Entity;

/**
 * 实体包装类
 * 
 * @timestamp Feb 16, 2016 10:43:14 PM
 * @author smallbug
 */
public class EntityWrap {

	/**
	 * 实体集合
	 */
	private Map<String, Entity> entities = new HashMap<>();

	/**
	 * 获取所有实体
	 * 
	 * @timestamp Feb 16, 2016 11:46:51 PM
	 * @return
	 */
	public Map<String, Entity> getEntities() {
		return entities;
	}

	/**
	 * 设置实体集合
	 * 
	 * @timestamp Feb 16, 2016 11:47:04 PM
	 * @param entities
	 */
	public void setEntities(Map<String, Entity> entities) {
		this.entities = entities;
	}

	/**
	 * 增加一个实体
	 * 
	 * @timestamp Feb 16, 2016 11:47:23 PM
	 * @param key
	 * @param entity
	 */
	public void addEntity(String key, Entity entity) {
		entities.put(key, entity);
	}

	/**
	 * 获取一个实体
	 * 
	 * @timestamp Feb 16, 2016 11:47:33 PM
	 * @param key
	 * @return
	 */
	public Entity getEntity(String key) {
		if (key != null)
			return entities.get(key);
		return null;
	}
}
