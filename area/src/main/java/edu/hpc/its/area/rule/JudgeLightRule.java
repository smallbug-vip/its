package edu.hpc.its.area.rule;

import edu.hpc.its.area.core.EntityWrap;

/**
 * 根据红绿灯判断是否需要停车
 * 
 * @timestamp Feb 17, 2016 12:16:37 AM
 * @author smallbug
 */
public class JudgeLightRule extends BaseRule {

	@Override
	public void invoke(EntityWrap entityWrap) {
		getNext().invoke(entityWrap);

	}

}
