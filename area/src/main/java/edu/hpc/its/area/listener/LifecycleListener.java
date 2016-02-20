package edu.hpc.its.area.listener;

/**
 * 生命周期监听器
 * 
 * @timestamp Feb 13, 2016 1:23:57 PM
 * @author smallbug
 */
public interface LifecycleListener {
	/**
	 * 监听事件
	 * 
	 * @timestamp Feb 13, 2016 1:25:04 PM
	 * @param event
	 */
	public void lifecycleEvent(LifecycleEvent event);

}
