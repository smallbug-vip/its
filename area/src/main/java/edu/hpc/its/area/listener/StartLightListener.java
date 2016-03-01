package edu.hpc.its.area.listener;

import java.util.Map;
import java.util.Map.Entry;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 启动为信号灯计时的线程，用来切换灯的状态
 * 
 * @timestamp Feb 23, 2016 6:06:32 PM
 * @author smallbug
 */
public class StartLightListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getLifecycle() instanceof Area //
				&& Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
			Thread lightListener = new Thread(new Runnable() {

				Map<Long, StandardLight> lightMap = StandardEntityFactory.getLightMap();

				@Override
				public void run() {
					while (true) {
						for (Entry<Long, StandardLight> l : lightMap.entrySet()) {
							StandardLight light = l.getValue();
							light.setTime(light.getTime() + 1 * 1000);
							if (light.getLightState() == 1 && light.getTime() >= light.getGreen()) {
								light.setTime(0.0);
								light.setLightState(0);
							} else if (light.getLightState() == 0 && light.getTime() >= light.getRed()) {
								light.setTime(0.0);
								light.setLightState(1);
							}
						}
						try {
							Thread.sleep(1000 * 1);// 一秒扫一次
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			});
			lightListener.setName("LightListener");
			lightListener.setDaemon(true);
			lightListener.start();
		}
	}
}
