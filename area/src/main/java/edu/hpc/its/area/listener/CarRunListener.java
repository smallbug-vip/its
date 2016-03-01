package edu.hpc.its.area.listener;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Constant;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.core.RoadGroup;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 为道路分配执行线程，驱动车行驶
 * 
 * @timestamp Feb 24, 2016 4:19:23 PM
 * @author smallbug
 */
public class CarRunListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getLifecycle() instanceof Area //
				&& Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
			carRun();
		}
	}

	private void carRun() {
		Map<Long, StandardRoad> roads = StandardEntityFactory.getRoadMap();// 获取区域所有路
		int num = roads.size() / Constant.RODENUMONETHREAD;// 路分组的数量可能是num后者num+1
		RoadGroup[] roadGroup = new RoadGroup[num == 0 ? num : num + 1];// 初始化路分组类的数组
		StandardRoad[] roadCache = new StandardRoad[Constant.RODENUMONETHREAD];// 初始化路的缓存数组
		int i = 0, j = 0;// 计数器
		for (Entry<Long, StandardRoad> roadEntry : roads.entrySet()) {
			StandardRoad road = roadEntry.getValue();
			roadCache[i++] = road;// 路放入缓存数组
			if (i % Constant.RODENUMONETHREAD == 0) {// 计数器到之后
				roadGroup[j++] = new RoadGroup(roadCache);// 初始化一个路分组类放入数组中
				i = 0;
			}
		}
		if (i != 0) {// 扫尾，将剩余的路放到最后一个路分组中
			StandardRoad[] rc = new StandardRoad[i];
			System.arraycopy(roadCache, 0, rc, 0, i);
			roadGroup[j] = new RoadGroup(rc);
		}
		roadCache = null;
		/**
		 * 第一种方法：自己开启线程
		 */
//		Thread[] threads = new Thread[roadGroup.length];
//		for (int k = 0; k < roadGroup.length; k++) {
//			threads[k] = new Thread(roadGroup[k]);
//		}
//		i = 0;
//		for (Thread t : threads) {
//			t.setName("Thread-RoadGroup [" + i++ + "]");
//			t.setDaemon(true);
//			t.start();
//		}
		/**
		 * 第二种方法：使用线程池
		 */
		ExecutorService pool = Executors.newFixedThreadPool(roadGroup.length);

		for (int k = 0; k < roadGroup.length; k++) {
			pool.submit(roadGroup[k]);
		}
	}
}
