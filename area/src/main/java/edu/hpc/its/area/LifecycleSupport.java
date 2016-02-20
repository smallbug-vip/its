package edu.hpc.its.area;

import edu.hpc.its.area.listener.LifecycleEvent;
import edu.hpc.its.area.listener.LifecycleListener;

/**
 * 生命周期辅助类
 * 
 * @timestamp Feb 13, 2016 3:53:49 PM
 * @author smallbug
 */
public final class LifecycleSupport {

	public LifecycleSupport(Lifecycle lifecycle) {

		super();
		this.lifecycle = lifecycle;

	}

	/**
	 * 生命周期辅助类关联对象
	 */
	private Lifecycle lifecycle = null;
	/**
	 * 生命周期监听器
	 */
	private LifecycleListener listeners[] = new LifecycleListener[0];
	/**
	 * 锁
	 */
	private final Object listenersLock = new Object();

	/**
	 * 添加监听器
	 * 
	 * @timestamp Feb 16, 2016 2:25:50 PM
	 * @param listener
	 */
	public void addLifecycleListener(LifecycleListener listener) {

		synchronized (listenersLock) {
			LifecycleListener results[] = new LifecycleListener[listeners.length + 1];
			for (int i = 0; i < listeners.length; i++)
				results[i] = listeners[i];
			results[listeners.length] = listener;
			listeners = results;
		}
	}

	/**
	 * 获取所有监听器
	 * 
	 * @timestamp Feb 16, 2016 2:25:21 PM
	 * @return
	 */
	public LifecycleListener[] findLifecycleListeners() {

		return listeners;

	}

	/**
	 * 遍历监听器
	 * 
	 * @timestamp Feb 8, 2016 2:13:49 PM
	 * @param type
	 *            事件类型
	 * @param data
	 *            事件对象
	 */
	public void fireLifecycleEvent(String type, Object data) {
		LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
		LifecycleListener interested[] = listeners;
		for (int i = 0; i < interested.length; i++)
			interested[i].lifecycleEvent(event);
	}

	/**
	 * 移除监听器
	 * 
	 * @timestamp Feb 16, 2016 2:25:08 PM
	 * @param listener
	 */
	public void removeLifecycleListener(LifecycleListener listener) {

		synchronized (listenersLock) {
			int n = -1;
			for (int i = 0; i < listeners.length; i++) {
				if (listeners[i] == listener) {
					n = i;
					break;
				}
			}
			if (n < 0)
				return;
			LifecycleListener results[] = new LifecycleListener[listeners.length - 1];
			int j = 0;
			for (int i = 0; i < listeners.length; i++) {
				if (i != n)
					results[j++] = listeners[i];
			}
			listeners = results;
		}
	}
}
