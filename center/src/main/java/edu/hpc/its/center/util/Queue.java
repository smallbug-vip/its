package edu.hpc.its.center.util;

/**
 * 队列接口
 * 
 * @timestamp Feb 23, 2016 3:28:20 PM
 * @author smallbug
 * @param <T>
 */
public interface Queue<T> {

	/**
	 * 入队列
	 * 
	 * @timestamp Feb 23, 2016 3:28:31 PM
	 * @param obj
	 * @return
	 */
	public boolean enQueue(T obj);

	/**
	 * 出队列
	 * 
	 * @timestamp Feb 23, 2016 3:28:51 PM
	 * @return
	 */
	public T deQueue();

	/**
	 * 队列是否为空
	 * 
	 * @timestamp Feb 23, 2016 3:28:59 PM
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 队列中元素个数
	 * 
	 * @timestamp Feb 23, 2016 3:29:36 PM
	 * @return
	 */
	public int size();

	/**
	 * 获取对头元素（不删除）
	 * 
	 * @timestamp Feb 23, 2016 3:30:03 PM
	 * @return
	 */
	public T getFirst();
}
