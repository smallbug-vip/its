package edu.hpc.its.area;

/**
 * <p>
 * 规则类可以选择是否实现该接口，该接口的实现类可以根据该接口中的方法至多与一个<code>StandardBase<code>的子类相关联
 * <p>
 * 
 * @timestamp Feb 14, 2016 11:00:00 PM
 * @author smallbug
 */
public interface Contained {
	/**
	 * 返回关联实体类
	 * 
	 * @timestamp Feb 14, 2016 10:59:57 PM
	 * @return
	 */
	public Entity getEntity();

	/**
	 * 设置关联实体类
	 * 
	 * @timestamp Feb 14, 2016 11:03:55 PM
	 * @param entity
	 */
	public void setEntity(Entity entity);
}
