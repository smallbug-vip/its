package edu.hpc.its.area.util;

import edu.hpc.its.area.exception.QueueException;

/**
 * 链表结构实现的循环队列
 * 
 * @timestamp Feb 23, 2016 3:32:02 PM
 * @author smallbug
 */
public class LinkedQueue<T> implements Queue<T> {

	private Node<T> front; // 队头
	private Node<T> rear; // 队尾
	private int count; // 计数器

	private int maxSize = -1;// 最大元素个数

	public LinkedQueue() {
		init();
	}

	public LinkedQueue(int maxSize) {
		if (maxSize <= 0) {
			throw new IllegalArgumentException("maxSize mast greater than 0!");
		}
		init();
		this.maxSize = maxSize;
	}

	public void init() {
		front = rear = null;
		count = 0;
	}

	@Override
	public boolean enQueue(T obj) {
		if (obj == null) {
			throw new QueueException("object not null!");
		}
		if (maxSize != -1 && maxSize == count)
			return false;
		Node<T> node = new Node<T>(obj, null);
		if (rear != null) {
			rear.next = node;
		}
		rear = node;
		if (front == null) {
			front = node;
		}
		count++;
		return true;
	}

	@Override
	public T deQueue() {
		if (isEmpty())
			return null;
		T t = front.getElement();
		front = front.next;
		count--;
		return t;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public T getFirst() {
		if (!isEmpty()) {
			return front.getElement();
		} else {
			return null;
		}
	}

	private class Node<D> {

		private D element; // 数据域
		private Node<D> next; // 指针域

		// 头结点的构造方法
		public Node(Node<D> nextval) {
			this.next = nextval;
		}

		// 非头结点的构造方法
		public Node(D obj, Node<D> nextval) {
			this(nextval);
			this.element = obj;
			this.next = nextval;
		}

		// 获得当前的数据域的值
		public D getElement() {
			return this.element;
		}
	}
}
