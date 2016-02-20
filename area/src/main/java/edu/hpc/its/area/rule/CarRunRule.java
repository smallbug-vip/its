package edu.hpc.its.area.rule;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.Entity;
import edu.hpc.its.area.Rule;
import edu.hpc.its.area.core.EntityWrap;

/**
 * 车跑
 * 
 * @timestamp Feb 15, 2016 7:20:18 PM
 * @author smallbug
 */
public class CarRunRule extends BaseRule implements Rule {

	@Override
	public void invoke(EntityWrap entityWrap) {
		if (entityWrap.getEntity(Entity.CAR) instanceof Car) {
			System.out.println(entityWrap.getEntity(Entity.CAR));
		}
	}
}
