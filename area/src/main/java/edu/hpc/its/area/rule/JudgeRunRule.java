package edu.hpc.its.area.rule;

import edu.hpc.its.area.Car;

/**
 * 判断是否可以前进
 * 
 * @timestamp Feb 17, 2016 12:14:07 AM
 * @author smallbug
 */
public class JudgeRunRule extends BaseRule {

	@Override
	public void invoke() {
		if (getEntity() instanceof Car) {
//			System.out.println("JudgeRunRule -> " + getEntity());
		}
		getNext().invoke();
	}

}
