package edu.hpc.its.area.rule;

import edu.hpc.its.area.Contained;
import edu.hpc.its.area.Entity;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.Rule;
import edu.hpc.its.area.core.EntityWrap;
import edu.hpc.its.area.core.StandardMBean;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.util.StringManager;

/**
 * 基础规则
 * 
 * @timestamp Feb 15, 2016 6:45:38 PM
 * @author smallbug
 */
public abstract class BaseRule extends StandardMBean implements Rule, Contained {

	private Entity entity = null;

	protected static final String info = "edu.hpc.its.area.core.BaseRule/1.0";

	protected Rule next = null;

	protected static final StringManager sm = StringManager.getManager("edu.hpc.its.area.core");

	public BaseRule() {

	}

	@Override
	public Entity getEntity() {
		return (entity);
	}

	@Override
	public void setEntity(Entity entity) {
		this.entity = entity;

	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public Rule getNext() {
		return (next);
	}

	@Override
	public void setNext(Rule rule) {
		this.next = rule;
	}

	@Override
	public abstract void invoke(EntityWrap entityWrap);

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING);
		super.startInternal();
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.stopInternal();
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.initInternal();
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		super.destroyInternal();
	}

}
