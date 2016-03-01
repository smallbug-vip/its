package edu.hpc.its.area.util;

import edu.hpc.its.area.exception.QueueException;

/**
 * 数组结构实现的队列
 * 
 * @timestamp Feb 23, 2016 3:32:44 PM
 * @author smallbug
 */
public class ArrayQueue<T> implements Queue<T> {

	private int defaultSize = 10; // 默认队列的长度
	private int front; // 队头
	private int rear; // 队尾
	private int count; // 统计元素个数的计数器
	private int maxSize; // 队的最大长度
	private T[] queue; // 队列

	/**
	 * 默认大小
	 */
	public ArrayQueue() {
		init(defaultSize);
	}

	/**
	 * 指定大小
	 * 
	 * @param maxSize
	 */
	public ArrayQueue(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException("maxSize mast greater than 0!");
		init(maxSize);
	}

	/**
	 * 初始化
	 * 
	 * @timestamp Feb 23, 2016 3:42:20 PM
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	private void init(int maxSize) {
		this.maxSize = maxSize;
		front = rear = 0;
		count = 0;
		queue = (T[]) new Object[maxSize];
	}

	@Override
	public boolean enQueue(T obj) {
		if (obj == null) {
			throw new QueueException("object not null!");
		}
		if (count > 0 && rear == front) {
			return false;
		}
		queue[rear] = obj;
		count++;
		rear = (rear + 1) % maxSize;
		return true;
	}

	@Override
	public T deQueue() {
		if (count == 0 && rear == front) {
			return null;
		}
		T obj = queue[front];
		queue[front] = null;
		count--;
		front = (front + 1) % maxSize;
		return obj;
	}

	@Override
	public boolean isEmpty() {
		return count == 0 ? true : false;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public T getFirst() {
		if (count > 0)
			return queue[front];
		return null;
	}

}
