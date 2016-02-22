package edu.hpc.its.area.culster;

import java.net.Socket;

import org.apache.log4j.Logger;

import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.core.StandardBase;
import edu.hpc.its.area.exception.LifecycleException;

/**
 * 处理器
 * 
 * @timestamp Feb 21, 2016 9:56:29 PM
 * @author smallbug
 */
public class AreaProcessor extends StandardBase implements Runnable {

	/**
	 * 处理器处理哪个连接器
	 */
	private AreaConnector connector;
	/**
	 * 线程名
	 */
	private String threadName;
	/**
	 * 信号标记
	 */
	private boolean available = false;
	/**
	 * 套接字
	 */
	private Socket socket = null;
	/**
	 * 信号标记
	 */
	private boolean stopped = false;
	/**
	 * 线程同步锁
	 */
	private Object threadSync = new Object();
	/**
	 * 后台线程
	 */
	private Thread thread = null;

	/**
	 * 区域
	 */
	private StandardArea area = null;

	/**
	 * 处理命令
	 */
	private ProcessorCommand command = new ProcessorCommand();

	/**
	 * 线程ID
	 */
	private int id;

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(AreaProcessor.class);

	public AreaProcessor(AreaConnector connector, int id) {
		this.id = id;
		this.connector = connector;
		this.threadName = "AreaProcessor[" + connector.getPort() + "][" + id + "]";
		this.area = connector.getArea();
		log.info("AreaProcessor[" + connector.getPort() + "][" + id + "] init ...");
	}

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING);
		threadStart();
	}

	private void threadStart() {
		thread = new Thread(this, threadName);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	public synchronized void assign(Socket socket) {
		while (available) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		this.socket = socket;
		available = true;
		notifyAll();
	}

	private synchronized Socket await() {

		while (!available) {
			try {
				wait();
				log.info("AreaProcessor[" + connector.getPort() + "][" + id + "] go out process pool ！");
			} catch (InterruptedException e) {
			}
		}

		Socket socket = this.socket;
		available = false;

		return (socket);

	}

	@Override
	public void run() {
		while (!stopped) {
			Socket socket = await();
			if (socket == null)
				continue;
			try {
				process(socket);
			} catch (Throwable t) {
				;// TODO
			}
			connector.recycle(this);
			log.info("AreaProcessor[" + connector.getPort() + "][" + id + "] enter into process pool ！");
		}
		synchronized (threadSync) {
			threadSync.notifyAll();
		}
	}

	/**
	 * 处理Socket
	 * 
	 * @timestamp Feb 21, 2016 10:14:22 PM
	 * @param sock
	 */
	private void process(Socket sock) {
		command.process(sock, area);
		notifyAll();
	}

}
