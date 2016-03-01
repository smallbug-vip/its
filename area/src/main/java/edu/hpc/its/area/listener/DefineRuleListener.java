package edu.hpc.its.area.listener;

import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.Pipeline;
import edu.hpc.its.area.rule.CarRunRule;
import edu.hpc.its.area.rule.ChangeLaneRule;
import edu.hpc.its.area.rule.ChangeRoadRule;
import edu.hpc.its.area.rule.JudgeLightRule;

/**
 * 组织和注册和Rule
 * 
 * @timestamp Feb 24, 2016 7:58:35 PM
 * @author smallbug
 */
public class DefineRuleListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (Lifecycle.AFTER_INIT_EVENT.equals(event.getType())) {
			if (event.getLifecycle() instanceof Pipeline) {
				defineRule((Pipeline) event.getSource());
			}
		}
	}

	private void defineRule(Pipeline pipe) {
		pipe.setBasic(new CarRunRule());// 车跑
		pipe.addRule(new ChangeLaneRule());// 是否应该换道
		pipe.addRule(new JudgeLightRule());// 观察红绿灯
		pipe.addRule(new ChangeRoadRule());// 是否应该换路
	}
}
