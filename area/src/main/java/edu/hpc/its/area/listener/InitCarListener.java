package edu.hpc.its.area.listener;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 初始化车数据
 * 
 * @timestamp Feb 26, 2016 10:28:11 AM
 * @author smallbug
 */
public class InitCarListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getLifecycle() instanceof Area //
				&& Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
			StandardEntityFactory.initCars(((StandardArea)event.getLifecycle()).getId());
			StandardEntityFactory.initRoutes(((StandardArea)event.getLifecycle()).getId());
			StandardEntityFactory.initSignRoadMap();
		}
	}
}
