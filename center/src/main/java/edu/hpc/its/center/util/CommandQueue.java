package edu.hpc.its.center.util;

/**
 * 命令队列，用于处理客户端下达的命令
 * 
 * @timestamp Feb 22, 2016 3:36:28 PM
 * @author smallbug
 */
public class CommandQueue<T> extends LinkedQueue<T> {

	@Override
	public boolean enQueue(T obj) {
		return super.enQueue(obj);
	}

	@Override
	public T deQueue() {
		return super.deQueue();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public T getFirst() {
		return super.getFirst();
	}

}
