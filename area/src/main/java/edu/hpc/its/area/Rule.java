package edu.hpc.its.area;

import edu.hpc.its.area.core.EntityWrap;

/**
 * 规则接口
 * 
 * @timestamp Feb 14, 2016 10:42:50 PM
 * @author smallbug
 */
public interface Rule {

	/**
	 * 获取版本信息
	 * 
	 * @timestamp Feb 15, 2016 7:18:04 PM
	 * @return
	 */
	public String getInfo();

	/**
	 * 获取下一个规则
	 * 
	 * @timestamp Feb 15, 2016 7:18:13 PM
	 * @return
	 */
	public Rule getNext();

	/**
	 * 设置下一个规则
	 * 
	 * @timestamp Feb 15, 2016 7:18:24 PM
	 * @param rule
	 */
	public void setNext(Rule rule);

	/**
	 * 执行规则
	 * 
	 * @timestamp Feb 15, 2016 7:18:38 PM
	 * @param base
	 */
	public void invoke(EntityWrap entityWrap);
}
