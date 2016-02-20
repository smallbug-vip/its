package edu.hpc.its.area.rule;

import edu.hpc.its.area.core.EntityWrap;

/**
 * 判断是否可以换道
 * 
 * @timestamp Feb 17, 2016 12:14:38 AM
 * @author smallbug
 */
public class ChangeLaneRule extends BaseRule {

	@Override
	public void invoke(EntityWrap entityWrap) {
		getNext().invoke(entityWrap);

	}

}
