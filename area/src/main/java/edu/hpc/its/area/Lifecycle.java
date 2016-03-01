package edu.hpc.its.area;

import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.listener.LifecycleListener;

/**
 * 生命周期接口
 * 
 * @timestamp Feb 14, 2016 10:43:03 PM
 * @author smallbug
 */
public interface Lifecycle {

	/**
	 * 初始化之前
	 */
	public static final String BEFORE_INIT_EVENT = "before_init";
	/**
	 * 初始化之后
	 */
	public static final String AFTER_INIT_EVENT = "after_init";
	/**
	 * 组件开启
	 */
	public static final String START_EVENT = "start";
	/**
	 * 组件开启之前
	 */
	public static final String BEFORE_START_EVENT = "before_start";
	/**
	 * 组件开启之后
	 */
	public static final String AFTER_START_EVENT = "after_start";
	/**
	 * 组件开启停止
	 */
	public static final String STOP_EVENT = "stop";
	/**
	 * 组件停止之前
	 */
	public static final String BEFORE_STOP_EVENT = "before_stop";
	/**
	 * 组件停止之后
	 */
	public static final String AFTER_STOP_EVENT = "after_stop";
	/**
	 * 注销之后
	 */
	public static final String AFTER_DESTROY_EVENT = "after_destroy";
	/**
	 * 注销之前
	 */
	public static final String BEFORE_DESTROY_EVENT = "before_destroy";
	/**
	 * 周期事件
	 */
	public static final String PERIODIC_EVENT = "periodic";
	/**
	 * 开始配置
	 */
	public static final String CONFIGURE_START_EVENT = "configure_start";
	/**
	 * 停止配置
	 */
	public static final String CONFIGURE_STOP_EVENT = "configure_stop";

	/**
	 * 增加监听器
	 * 
	 * @timestamp Feb 16, 2016 2:40:06 PM
	 * @param listener
	 */
	public Lifecycle addLifecycleListener(LifecycleListener listener);

	/**
	 * 获取所有监听器
	 * 
	 * @timestamp Feb 16, 2016 2:40:17 PM
	 * @return
	 */
	public LifecycleListener[] findLifecycleListeners();

	/**
	 * 移除监听器
	 * 
	 * @timestamp Feb 16, 2016 2:40:33 PM
	 * @param listener
	 */
	public void removeLifecycleListener(LifecycleListener listener);

	/**
	 * 初始化
	 * 
	 * @timestamp Feb 16, 2016 2:40:40 PM
	 * @throws LifecycleException
	 */
	public void init() throws LifecycleException;

	/**
	 * 开始
	 * 
	 * @timestamp Feb 16, 2016 2:40:48 PM
	 * @throws LifecycleException
	 */
	public void start() throws LifecycleException;

	/**
	 * 停止
	 * 
	 * @timestamp Feb 16, 2016 2:40:54 PM
	 * @throws LifecycleException
	 */
	public void stop() throws LifecycleException;

	/**
	 * 注销
	 * 
	 * @timestamp Feb 16, 2016 2:41:01 PM
	 * @throws LifecycleException
	 */
	public void destroy() throws LifecycleException;

	/**
	 * 获取状态
	 * 
	 * @timestamp Feb 16, 2016 2:41:12 PM
	 * @return
	 */
	public LifecycleState getState();

	/**
	 * 获取状态名字
	 * 
	 * @timestamp Feb 16, 2016 2:41:20 PM
	 * @return
	 */
	public String getStateName();
}
