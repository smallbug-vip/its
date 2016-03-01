package edu.hpc.its.area.core;

import org.apache.log4j.Logger;

import edu.hpc.its.area.Contained;
import edu.hpc.its.area.Entity;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.Pipeline;
import edu.hpc.its.area.Rule;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.util.StringManager;

/**
 * 规则管道
 * 
 * @timestamp Feb 14, 2016 11:39:03 PM
 * @author smallbug
 */
public class StandardPipeline extends StandardBase implements Pipeline, Contained {

	/**
	 * 日志工具
	 */
	Logger log = Logger.getLogger(StandardPipeline.class);
	/**
	 * 异常管理
	 */
	StringManager sManager = StringManager.getManager("cn.smallbug.area.core");

	/**
	 * 基础Rule
	 */
	protected Rule basic = null;

	/**
	 * 第一个与Pipeline相关联的Rule
	 */
	protected Rule first = null;

	/**
	 * 与Pipeline向关联的实体类
	 */
	Entity entity = null;

	public StandardPipeline() {
		super();
	}

	public StandardPipeline(Entity entity) {
		this.entity = entity;
	}

	/**
	 * 获取基础规则
	 */
	@Override
	public Rule getBasic() {
		return (this.basic);
	}

	/**
	 * 设置基础规则
	 */
	@Override
	public void setBasic(Rule rule) {
		Rule oldBasic = this.basic;
		// 如果rule为null，或者与之前rule相同设置失败
		if (rule == null || oldBasic == rule)
			return;
		// 如果有必要的话，可释放旧rule占用的资源
		if (oldBasic != null) {
			if (getState().isAvailable() && (oldBasic instanceof Lifecycle)) {
				try {
					((Lifecycle) oldBasic).stop();
				} catch (LifecycleException e) {
					log.error(sManager.getString("standardPipeline.setBasic.err01", StandardPipeline.class));
				}
			}
			if (oldBasic instanceof Contained) {
				try {
					((Contained) oldBasic).setEntity(null);
				} catch (Throwable t) {
					log.error(sManager.getString("standardPipeline.setBasic.err02", StandardPipeline.class));
				}
			}
		}
		// 为规则设置相关实体
		if (rule instanceof Contained) {
			((Contained) rule).setEntity(this.entity);
		}

		if (getState().isAvailable() && rule instanceof Lifecycle) {
			try {
				((Lifecycle) rule).start();
			} catch (LifecycleException e) {
				log.error(sManager.getString("standardPipeline.setBasic.err03", StandardPipeline.class));
				return;
			}
		}

		Rule current = first;
		while (current != null) {
			if (current.getNext() == oldBasic) {
				current.setNext(rule);
				break;
			}
			current = current.getNext();
		}

		this.basic = rule;

	}

	/**
	 * 增加规则
	 */
	@Override
	public void addRule(Rule rule) {
		// 如果有必要，关联rule与组件关系
		if (rule instanceof Contained)
			((Contained) rule).setEntity(this.entity);

		if (getState().isAvailable()) {
			if (rule instanceof Lifecycle) {
				try {
					((Lifecycle) rule).start();
				} catch (LifecycleException e) {
					log.error(sManager.getString("standardPipeline.addRule.err04", StandardPipeline.class));
				}
			}
		}

		if (first == null) {
			// 如果first为null设置rule为first
			first = rule;
			rule.setNext(basic);
		} else {
			Rule current = first;
			while (current != null) {
				if (current.getNext() == basic) {
					current.setNext(rule);
					rule.setNext(basic);
					break;
				}
				current = current.getNext();
			}
		}
	}

	/**
	 * 移除规则
	 */
	@Override
	public void removeRule(Rule rule) {
		Rule current;
		if (first == rule) {
			first = first.getNext();
			current = null;
		} else {
			current = first;
		}
		while (current != null) {
			if (current.getNext() == rule) {
				current.setNext(rule.getNext());
				break;
			}
			current = current.getNext();
		}

		if (first == basic)
			first = null;

		if (rule instanceof Contained)
			((Contained) rule).setEntity(null);

		if (rule instanceof Lifecycle) {
			if (getState().isAvailable()) {
				try {
					((Lifecycle) rule).stop();
				} catch (LifecycleException e) {
					log.error(sManager.getString("standardPipeline.removeRule.err05", StandardPipeline.class));
				}
			}
			try {
				((Lifecycle) rule).destroy();
			} catch (LifecycleException e) {
				log.error(sManager.getString("standardPipeline.removeRule.err06", StandardPipeline.class));
			}
		}

	}

	/**
	 * 获取第一个规则
	 */
	@Override
	public Rule getFirst() {
		if (first != null) {
			return first;
		}
		return basic;
	}

	/**
	 * 获取关联组件
	 */
	@Override
	public Entity getEntity() {
		return (this.entity);
	}

	/**
	 * 设置关联组件
	 */
	@Override
	public void setEntity(Entity entity) {
		this.entity = entity;

	}

	/**
	 * 开启组件
	 */
	@Override
	protected void startInternal() throws LifecycleException {
		Rule current = first;
		if (current == null) {
			current = basic;
		}
		while (current != null) {
			if (current instanceof Lifecycle)
				((Lifecycle) current).start();
			current = current.getNext();
		}
		setState(LifecycleState.STARTING);

	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initInternal() throws LifecycleException {

	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

}
