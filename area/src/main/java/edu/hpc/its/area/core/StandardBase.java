package edu.hpc.its.area.core;

import org.apache.log4j.Logger;

import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.LifecycleSupport;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.listener.LifecycleListener;
import edu.hpc.its.area.util.StringManager;

/**
 * 实体基类
 * 
 * @timestamp Feb 13, 2016 4:16:05 PM
 * @author smallbug
 */
public abstract class StandardBase implements Lifecycle {

	/**
	 * 当前状态
	 */
	protected volatile LifecycleState state = LifecycleState.NEW;

	/**
	 * 生命周期支持类
	 */
	protected LifecycleSupport support = new LifecycleSupport(this);

	/**
	 * 日志
	 */
	protected Logger log = Logger.getLogger(StandardPipeline.class);

	/**
	 * 异常管理器
	 */
	protected StringManager sManager = StringManager.getManager("cn.smallbug.area");

	/**
	 * 添加监听器
	 */
	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		support.addLifecycleListener(listener);
	}

	/**
	 * 获取所有监听器
	 */
	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return support.findLifecycleListeners();
	}

	/**
	 * 执行监听器
	 * 
	 * @timestamp Feb 16, 2016 2:54:18 PM
	 * @param lifecycleEvent
	 * @param data
	 */
	private void fireLifecycleEvent(String lifecycleEvent, Object data) {
		support.fireLifecycleEvent(lifecycleEvent, data);
	}

	/**
	 * 移除监听器
	 */
	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		support.removeLifecycleListener(listener);
	}

	/**
	 * 被子类重写，实际开启组件过程
	 * 
	 * @timestamp Feb 17, 2016 12:07:07 AM
	 * @throws LifecycleException
	 */
	protected abstract void startInternal() throws LifecycleException;

	/**
	 * 被子类复写，实际停止组件过程
	 * 
	 * @timestamp Feb 17, 2016 12:07:46 AM
	 * @throws LifecycleException
	 */
	protected abstract void stopInternal() throws LifecycleException;

	/**
	 * 被子类复写，实际初始化过程
	 * 
	 * @timestamp Feb 17, 2016 12:08:14 AM
	 * @throws LifecycleException
	 */
	protected abstract void initInternal() throws LifecycleException;

	/**
	 * 被子类复写，实际注销过程
	 * 
	 * @timestamp Feb 17, 2016 12:08:52 AM
	 * @throws LifecycleException
	 */
	protected abstract void destroyInternal() throws LifecycleException;

	/**
	 * 实体类公共初始化方法
	 */
	@Override
	public final synchronized void init() throws LifecycleException {
		if (!state.equals(LifecycleState.NEW)) {
			invalidTransition(Lifecycle.BEFORE_INIT_EVENT);
		}
		setStateInternal(LifecycleState.INITIALIZING, null, false);

		try {
			initInternal();
		} catch (Throwable t) {
			setStateInternal(LifecycleState.FAILED, null, false);
			// TODO
			throw new LifecycleException(sManager.getString("lifecycleBase.initFail", toString()), t);
		}
		setStateInternal(LifecycleState.INITIALIZED, null, false);

	}

	/**
	 * 组件开启
	 */
	@Override
	public final synchronized void start() throws LifecycleException {
		if (LifecycleState.STARTING_PREP.equals(state) //
				|| LifecycleState.STARTING.equals(state) //
				|| LifecycleState.STARTED.equals(state)) {

			if (log.isDebugEnabled()) {
				Exception e = new LifecycleException();
				// TODO
				log.debug(sManager.getString("lifecycleBase.alreadyStarted", toString()), e);
				// TODO
			} else if (log.isInfoEnabled()) {
				log.info(sManager.getString("lifecycleBase.alreadyStarted", toString()));
			}

			return;
		}

		if (state.equals(LifecycleState.NEW)) {
			init();
		} else if (state.equals(LifecycleState.FAILED)) {
			stop();
		} else if (!state.equals(LifecycleState.INITIALIZED) && !state.equals(LifecycleState.STOPPED)) {
			invalidTransition(Lifecycle.BEFORE_START_EVENT);
		}

		setStateInternal(LifecycleState.STARTING_PREP, null, false);

		try {
			startInternal();
		} catch (Throwable t) {
			setStateInternal(LifecycleState.FAILED, null, false);
			throw new LifecycleException(sManager.getString("lifecycleBase.startFail", toString()), t);
		}

		if (state.equals(LifecycleState.FAILED) || state.equals(LifecycleState.MUST_STOP)) {
			stop();
		} else {
			if (!state.equals(LifecycleState.STARTING)) {
				invalidTransition(Lifecycle.AFTER_START_EVENT);
			}

			setStateInternal(LifecycleState.STARTED, null, false);
		}

	}

	/**
	 * 组件停止
	 */
	@Override
	public final synchronized void stop() throws LifecycleException {
		if (LifecycleState.STOPPING_PREP.equals(state) || LifecycleState.STOPPING.equals(state) || LifecycleState.STOPPED.equals(state)) {

			if (log.isDebugEnabled()) {
				Exception e = new LifecycleException();
				log.debug(sManager.getString("lifecycleBase.alreadyStopped", toString()), e);
			} else if (log.isInfoEnabled()) {
				log.info(sManager.getString("lifecycleBase.alreadyStopped", toString()));
			}

			return;
		}

		if (state.equals(LifecycleState.NEW)) {
			state = LifecycleState.STOPPED;
			return;
		}

		if (!state.equals(LifecycleState.STARTED) && //
				!state.equals(LifecycleState.FAILED) && //
				!state.equals(LifecycleState.MUST_STOP)) {
			invalidTransition(Lifecycle.BEFORE_STOP_EVENT);
		}

		if (state.equals(LifecycleState.FAILED)) {
			fireLifecycleEvent(BEFORE_STOP_EVENT, null);
		} else {
			setStateInternal(LifecycleState.STOPPING_PREP, null, false);
		}

		try {
			stopInternal();
		} catch (Throwable t) {
			setStateInternal(LifecycleState.FAILED, null, false);
			throw new LifecycleException(sManager.getString("lifecycleBase.stopFail", toString()), t);
		}

		if (state.equals(LifecycleState.MUST_DESTROY)) {
			setStateInternal(LifecycleState.STOPPED, null, false);

			destroy();
		} else if (!state.equals(LifecycleState.FAILED)) {
			if (!state.equals(LifecycleState.STOPPING)) {
				invalidTransition(Lifecycle.AFTER_STOP_EVENT);
			}

			setStateInternal(LifecycleState.STOPPED, null, false);
		}
	}

	/**
	 * 组件注销
	 */
	@Override
	public final synchronized void destroy() throws LifecycleException {
		if (LifecycleState.FAILED.equals(state)) {
			try {
				stop();
			} catch (LifecycleException e) {
				log.warn(sManager.getString("lifecycleBase.destroyStopFail", toString()), e);
			}
		}

		if (LifecycleState.DESTROYING.equals(state) || LifecycleState.DESTROYED.equals(state)) {

			if (log.isDebugEnabled()) {
				Exception e = new LifecycleException();
				log.debug(sManager.getString("lifecycleBase.alreadyDestroyed", toString()), e);
			} else if (log.isInfoEnabled()) {
				log.info(sManager.getString("lifecycleBase.alreadyDestroyed", toString()));
			}

			return;
		}

		if (!state.equals(LifecycleState.STOPPED) && //
				!state.equals(LifecycleState.FAILED) && //
				!state.equals(LifecycleState.NEW) && //
				!state.equals(LifecycleState.INITIALIZED)) {
			invalidTransition(Lifecycle.BEFORE_DESTROY_EVENT);
		}

		setStateInternal(LifecycleState.DESTROYING, null, false);

		try {
			destroyInternal();
		} catch (Throwable t) {
			setStateInternal(LifecycleState.FAILED, null, false);
			throw new LifecycleException(sManager.getString("lifecycleBase.destroyFail", toString()), t);
		}

		setStateInternal(LifecycleState.DESTROYED, null, false);

	}

	/**
	 * 获取当前状态
	 */
	@Override
	public LifecycleState getState() {
		return state;
	}

	/**
	 * 获取当前状态名称
	 */
	@Override
	public String getStateName() {
		return getState().toString();
	}

	/**
	 * 设置状态
	 * 
	 * @timestamp Feb 16, 2016 2:32:23 PM
	 * @param state
	 * @throws LifecycleException
	 */
	protected synchronized void setState(LifecycleState state) throws LifecycleException {
		setStateInternal(state, null, true);
	}

	/**
	 * 改变状态
	 * 
	 * @timestamp Feb 16, 2016 2:17:45 PM
	 * @param state
	 * @param data
	 * @param check
	 * @throws LifecycleException
	 */
	private synchronized void setStateInternal(LifecycleState state, Object data, boolean check) throws LifecycleException {

		if (check) {
			if (state == null) {
				invalidTransition("null");
				return;
			}

			if (!(state == LifecycleState.FAILED //
					|| (this.state == LifecycleState.STARTING_PREP && state == LifecycleState.STARTING)
					|| (this.state == LifecycleState.STOPPING_PREP && state == LifecycleState.STOPPING)
					|| (this.state == LifecycleState.FAILED && state == LifecycleState.STOPPING))) {
				invalidTransition(state.name());
			}
		}

		this.state = state;
		String lifecycleEvent = state.getLifecycleEvent();

		if (lifecycleEvent != null) {
			fireLifecycleEvent(lifecycleEvent, data);
		}
	}

	/**
	 * 错误过渡
	 * 
	 * @timestamp Feb 16, 2016 2:55:17 PM
	 * @param type
	 * @throws LifecycleException
	 */
	private void invalidTransition(String type) throws LifecycleException {
		// TODO
		String msg = sManager.getString("lifecycleBase.invalidTransition", type, toString(), state);
		throw new LifecycleException(msg);
	}
}
