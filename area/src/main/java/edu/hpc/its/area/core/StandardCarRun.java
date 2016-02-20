package edu.hpc.its.area.core;

import edu.hpc.its.area.CarRun;
import edu.hpc.its.area.Pipeline;
import edu.hpc.its.area.Rule;
import edu.hpc.its.area.exception.CarRunException;
import edu.hpc.its.area.rule.CarRunRule;

public class StandardCarRun extends StandardMBean implements CarRun {

	protected Pipeline pipeline = new StandardPipeline();

	public StandardCarRun() {
		pipeline.setBasic(new CarRunRule());
	}

	public synchronized void addValve(Rule rule) {

		pipeline.addRule(rule);
	}

	@Override
	public void run(EntityWrap entityWrap) {
		if (entityWrap == null) {
			throw new CarRunException(sManager.getString("standardCarRun.run.err01", StandardCarRun.class));
		}
		pipeline.getFirst().invoke(entityWrap);
	}
}
