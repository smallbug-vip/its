package edu.hpc.its.area;

/**
 * 定义Pipeline的基本操作
 * 
 * @timestamp Feb 14, 2016 10:18:15 PM
 * @author smallbug
 */
public interface Pipeline extends Lifecycle{

	/**
	 * 获取基础Rule
	 * 
	 * @timestamp Feb 14, 2016 11:34:27 PM
	 * @return
	 */
	public Rule getBasic();

	/**
	 * 设置基础Rule
	 * 
	 * @timestamp Feb 14, 2016 11:34:45 PM
	 * @param rule
	 */
	public void setBasic(Rule rule);

	/**
	 * 增加Rule
	 * 
	 * @timestamp Feb 14, 2016 11:34:55 PM
	 * @param rule
	 */
	public void addRule(Rule rule);

	/**
	 * 移除Rule
	 * 
	 * @timestamp Feb 14, 2016 11:35:08 PM
	 * @param rule
	 */
	public void removeRule(Rule rule);

	/**
	 * 获取第一个Rule
	 * 
	 * @timestamp Feb 14, 2016 11:35:22 PM
	 * @return
	 */
	public Rule getFirst();

	/**
	 * 获取实体类
	 * 
	 * @timestamp Feb 15, 2016 12:03:36 AM
	 * @return
	 */
	public Entity getEntity();

	/**
	 * 设置实体类
	 * 
	 * @timestamp Feb 15, 2016 12:03:43 AM
	 * @param base
	 */
	public void setEntity(Entity entity);
}
