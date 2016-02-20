package edu.hpc.its.area;

/**
 * 生命周期的当前状态，封装了Lifecycle接口中的状态信息
 * 
 * @timestamp Feb 13, 2016 1:26:18 PM
 * @author smallbug
 */
public enum LifecycleState {
	NEW(false, null), // 创建组件
	INITIALIZING(false, Lifecycle.BEFORE_INIT_EVENT), // 组件初始化之前
	INITIALIZED(false, Lifecycle.AFTER_INIT_EVENT), // 组件初始化之后
	STARTING_PREP(false, Lifecycle.BEFORE_START_EVENT), // 组件开启之前
	STARTING(true, Lifecycle.START_EVENT), // 组件开启
	STARTED(true, Lifecycle.AFTER_START_EVENT), // 组件开启之后
	STOPPING_PREP(true, Lifecycle.BEFORE_STOP_EVENT), // 组件停止之前
	STOPPING(false, Lifecycle.STOP_EVENT), // 组件停止
	STOPPED(false, Lifecycle.AFTER_STOP_EVENT), // 组件停止之后
	DESTROYING(false, Lifecycle.BEFORE_DESTROY_EVENT), // 组件注销之前
	DESTROYED(false, Lifecycle.AFTER_DESTROY_EVENT), // 组件注销之后
	FAILED(false, null), // 报错
	MUST_STOP(true, null), // 必须停止
	MUST_DESTROY(false, null);// 必须注销

	private final boolean available;
	private final String lifecycleEvent;

	private LifecycleState(boolean available, String lifecycleEvent) {
		this.available = available;
		this.lifecycleEvent = lifecycleEvent;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getLifecycleEvent() {
		return lifecycleEvent;
	}
}
