package edu.hpc.its.area.culster;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.AccessControlException;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import edu.hpc.its.area.Constant;
import edu.hpc.its.area.LifecycleState;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.core.StandardBase;
import edu.hpc.its.area.exception.LifecycleException;
import edu.hpc.its.area.factory.ServerSocketFactory;

/**
 * 连接器
 * 
 * @timestamp Feb 21, 2016 9:56:42 PM
 * @author smallbug
 */
public class AreaConnector extends StandardBase implements Runnable {

	/**
	 * ServerSocket实例，用于获取Socket
	 */
	private ServerSocket serverSocket = null;

	/**
	 * ServerSocket工厂
	 */
	private ServerSocketFactory factory = null;

	/**
	 * 监听端口号
	 */
	private int port = Constant.AREAPORT;

	/**
	 * 处理器池中的最小处理器个数
	 */
	protected int minProcessors = Constant.MINPROCESSORS;

	/**
	 * 处理器池中的最大处理器个数
	 */
	private int maxProcessors = Constant.MAXPROCESSORS;

	/**
	 * 现在处理器池中处理器的个数
	 */
	private int curProcessors = 0;

	/**
	 * 本对象线程
	 */
	private Thread thread = null;

	/**
	 * 线程名
	 */
	private String threadName = null;

	/**
	 * 监听地址
	 */
	private String address = null;

	/**
	 * ServerSocket队列的最大长度
	 */
	private int acceptCount = 10;

	/**
	 * 处理器池
	 */
	private LinkedList<AreaProcessor> processors = new LinkedList<AreaProcessor>();

	/**
	 * 线程同步锁
	 */
	private Object threadSync = new Object();
	/**
	 * 关闭信号
	 */
	private boolean stopped = false;

	/**
	 * 区域
	 */
	StandardArea area = null;

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(AreaConnector.class);

	public AreaConnector() {
		super();
	}

	public AreaConnector(StandardArea area) {
		this.area = area;
	}

	@Override
	protected void startInternal() throws LifecycleException {
		threadName = "AreaConnector[" + port + "]";
		setState(LifecycleState.STARTING);
		threadStart();

		for (; curProcessors < minProcessors;) {
			if ((maxProcessors > 0) && (curProcessors >= maxProcessors))
				break;
			AreaProcessor processor = newProcessor();
			recycle(processor);
		}

	}

	/**
	 * 将处理器装入处理器池
	 * 
	 * @timestamp Feb 21, 2016 8:47:30 PM
	 * @param processor
	 */
	public void recycle(AreaProcessor processor) {
		processors.push(processor);
	}

	/**
	 * 创建新处理器
	 * 
	 * @timestamp Feb 21, 2016 8:47:56 PM
	 * @return
	 */
	private AreaProcessor newProcessor() {
		AreaProcessor processor = new AreaProcessor(this, curProcessors++);
		processor.start();
		log.info("AreaProcessor[" + getPort() + "][" + (curProcessors - 1) + "] started !");
		return (processor);
	}

	/**
	 * 开启线程
	 * 
	 * @timestamp Feb 21, 2016 8:48:18 PM
	 */
	private void threadStart() {
		thread = new Thread(this, threadName);
		// thread.setDaemon(true);
		thread.start();
		log.info("Connector Thread -> " + threadName + " start!");
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initInternal() throws LifecycleException {
		try {
			serverSocket = open();
		} catch (Exception e) {
			throw new LifecycleException(e);
		}

	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	/**
	 * 开启ServerSocket
	 * 
	 * @timestamp Feb 21, 2016 8:40:01 PM
	 * @return
	 * @throws Exception
	 */
	private ServerSocket open() throws Exception {
		ServerSocketFactory factory = getFactory();
		if (address == null) {
			try {
				log.info("ServerSocket init, port = " + port);
				return (factory.createSocket(port, acceptCount));
			} catch (BindException be) {
				throw new BindException(be.getMessage() + ":" + port);
			}
		}
		try {
			log.info("ServerSocket init, port = " + port);
			InetAddress is = InetAddress.getByName(address);
			try {
				return (factory.createSocket(port, acceptCount, is));
			} catch (BindException be) {
				throw new BindException(be.getMessage() + ":" + address + ":" + port);
			}
		} catch (Exception e) {
			try {
				log.info("ServerSocket init, port = " + port);
				return (factory.createSocket(port, acceptCount));
			} catch (BindException be) {
				throw new BindException(be.getMessage() + ":" + port);
			}
		}
	}

	/**
	 * 获取ServerSocket工厂
	 * 
	 * @timestamp Feb 21, 2016 8:24:27 PM
	 * @return
	 */
	private ServerSocketFactory getFactory() {

		if (this.factory == null) {
			synchronized (this) {
				this.factory = new ServerSocketFactory();
			}
		}
		return (this.factory);

	}

	@Override
	public void run() {
		while (!stopped) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (AccessControlException ace) {
				continue;
			} catch (IOException e) {
				try {
					synchronized (threadSync) {
						if (!stopped) {
							serverSocket.close();
							serverSocket = open();
						}
					}
				} catch (Exception ioe) {
					throw new LifecycleException(e);
				}
				continue;
			}

			AreaProcessor processor = getProcessor();
			if (processor == null) {
				try {
					socket.close();
				} catch (IOException e) {
					;
				}
				continue;
			}
			processor.assign(socket);

		}
		synchronized (threadSync) {
			threadSync.notifyAll();
		}

	}

	/**
	 * 获取处理器
	 * 
	 * @timestamp Feb 21, 2016 9:06:08 PM
	 * @return
	 */
	private AreaProcessor getProcessor() {
		synchronized (processors) {
			if (processors.size() > 0) {
				return processors.pop();
			}
			if ((maxProcessors > 0) && (curProcessors < maxProcessors)) {
				AreaProcessor p = newProcessor();
				return p;
			} else {
				if (maxProcessors < 0) {
					return (newProcessor());
				} else {
					return (null);
				}
			}
		}
	}

	public int getPort() {
		return port;
	}

	public StandardArea getArea() {
		return area;
	}

	public void setArea(StandardArea area) {
		this.area = area;
	}

}
