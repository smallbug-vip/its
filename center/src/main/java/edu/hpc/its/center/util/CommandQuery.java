package edu.hpc.its.center.util;

import java.util.LinkedList;

/**
 * 命令队列，用于处理客户端下达的命令
 * 
 * @timestamp Feb 22, 2016 3:36:28 PM
 * @author smallbug
 */
public class CommandQuery<T> implements Query<T> {
	/**
	 * 命令队列
	 */
	private LinkedList<T> commandQuery = new LinkedList<>();

	@Override
	public boolean isEmpty() {
		return commandQuery.isEmpty();
	}

	@Override
	public void enQueue(T data) {
		commandQuery.offerLast(data);
	}

	@Override
	public T deQueue() {
		return commandQuery.removeFirst();
	}

	@Override
	public void clear() {
		commandQuery.clear();
	}

	@Override
	public int size() {
		return commandQuery.size();
	}

}
