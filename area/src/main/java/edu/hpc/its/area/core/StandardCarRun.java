package edu.hpc.its.area.core;

import edu.hpc.its.area.CarRun;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.Pipeline;
import edu.hpc.its.area.listener.DefineRuleListener;

/**
 * 真正支持车运动的类
 * 
 * @timestamp Feb 24, 2016 7:21:00 PM
 * @author smallbug
 */
public class StandardCarRun implements CarRun {

	/**
	 * 规则管道
	 */
	private Pipeline pipeline = null;

	public StandardCarRun(StandardEntity entity) {
		super();
		pipeline = new StandardPipeline(entity);
		pipeline.addLifecycleListener(new DefineRuleListener());// 注册状态监听器，保证pipeline初始化时装配Rule链
	}

	@Override
	public void run() {
		if (LifecycleState.NEW == pipeline.getState()) {
			pipeline.start();
		}
		pipeline.getFirst().invoke();
	}

}
