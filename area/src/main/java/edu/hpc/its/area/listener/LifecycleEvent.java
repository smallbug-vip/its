package edu.hpc.its.area.listener;

import java.util.EventObject;

import edu.hpc.its.area.Lifecycle;

/**
 * 生命周期监听器监听的事件
 * 
 * @timestamp Feb 13, 2016 1:24:22 PM
 * @author smallbug
 */
public final class LifecycleEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {

		super(lifecycle);
		this.type = type;
		this.data = data;
	}

	private Object data = null;

	private String type = null;

	public Object getData() {

		return (this.data);

	}

	public Lifecycle getLifecycle() {

		return (Lifecycle) getSource();

	}

	public String getType() {

		return (this.type);

	}

}
