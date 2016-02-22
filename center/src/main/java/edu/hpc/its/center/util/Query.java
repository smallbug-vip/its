package edu.hpc.its.center.util;

/**
 * 队列接口
 * 
 * @timestamp Feb 22, 2016 3:39:09 PM
 * @author smallbug
 * @param <T>
 */
public interface Query<T> {

	/**
	 * 队列是否为空
	 * 
	 * @timestamp Feb 22, 2016 3:39:18 PM
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 进队列
	 * 
	 * @timestamp Feb 22, 2016 3:39:29 PM
	 * @param data
	 */
	public void enQueue(T data);

	/**
	 * 出队列
	 * 
	 * @timestamp Feb 22, 2016 3:39:36 PM
	 * @return
	 */
	public T deQueue();

	/**
	 * 清空队列
	 * 
	 * @timestamp Feb 22, 2016 3:45:23 PM
	 */
	public void clear();

	/**
	 * 队列元素个数
	 * 
	 * @timestamp Feb 22, 2016 4:11:45 PM
	 * @return
	 */
	public int size();
}
