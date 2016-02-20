package edu.hpc.its.area.test.core;

import org.junit.Test;

import edu.hpc.its.area.core.StandardCarRun;
import edu.hpc.its.area.Entity;
import edu.hpc.its.area.core.EntityWrap;
import edu.hpc.its.area.core.StandardCar;

public class TestStandardPipeline {

	/**
	 * setBasic是在CarRunRuleBase初始化时被调用的
	 * 
	 * @timestamp Feb 16, 2016 5:31:48 PM
	 */
	@Test
	public void testBasic() {
		StandardCarRun run = new StandardCarRun();
		StandardCar car = new StandardCar();
		EntityWrap entityWrap = new EntityWrap();
		entityWrap.addEntity(Entity.CAR, car);
		run.run(entityWrap);
	}
}
