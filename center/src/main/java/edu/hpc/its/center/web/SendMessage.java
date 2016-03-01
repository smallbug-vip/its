package edu.hpc.its.center.web;

import java.io.IOException;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hpc.its.center.config.AreaInfo;
import edu.hpc.its.center.config.InitInfo;
import edu.hpc.its.center.util.CommandQueue;
import edu.hpc.its.center.util.GetRemoteData;

/**
 * 发送信息
 * 
 * @timestamp Feb 22, 2016 3:14:49 PM
 * @author smallbug
 */
public class SendMessage {

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(SendMessage.class);

	/**
	 * WebService中的Session
	 */
	private Session session;

	/**
	 * 命令队列，存放客户端下达的命令
	 */
	private CommandQueue<CommandEntity> queue = new CommandQueue<>();

	private GetRemoteData remoteData = new GetRemoteData();
	/**
	 * 全局区域配置信息
	 */
	private InitInfo initInfo;

	public SendMessage(Session session) {
		this.session = session;
	}

	/**
	 * 发送信息主方法
	 * 
	 * @timestamp Feb 22, 2016 3:22:38 PM
	 * @param message
	 * @param initInfo
	 */
	public void sendMessage(String message, InitInfo initInfo) {
		this.initInfo = initInfo;
		parseMessage(message);
		log.info("message -> << " + message + " >> parse success!");
		execute();
		log.info("message -> << " + message + " >> execute success!");
	}

	/**
	 * 发送区域信息
	 * 
	 * @timestamp Feb 22, 2016 3:32:05 PM
	 * @param message
	 */
	private void sendAreaMessage(String[] value) {
		if (value != null && value.length > 0) {
			AreaInfo info = initInfo.getAreaInfoById(value[0]);
			if (info != null) {
				if (value[0].equals(initInfo.getCurrentArea().getAreaId())) {
					initInfo.setCurrentArea(info);// 如果当前ID并非要得到的ID就修改当前区域为要获取的区域
				}
			}
		}
		String data = remoteData.getDate(initInfo, Command.AREACHOOSE);
		broadcast(data);
	}

	/**
	 * 执行消息队列
	 * 
	 * @timestamp Feb 22, 2016 4:25:48 PM
	 */
	private void execute() {
		if (queue != null && queue.size() > 0) {
			while (!queue.isEmpty()) {
				CommandEntity entity = queue.deQueue();
				switch (entity.getCommand()) {
				case Command.AREACHOOSE:// 选择区域进行加载
					sendAreaMessage(entity.getValue());
					break;
				case Command.LOADROADDATA:
					sendRoadMessage(entity.getValue());
					break;
				case Command.LOADLANEDATA:
					sendLaneMessage(entity.getValue());
					break;
				case Command.LOADLIGHTDATA:
					sendLightMessage(entity.getValue());
					break;
				case Command.LOADCARDATA:
					sendCarMessage(entity.getValue());
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 发送车信息
	 * 
	 * @timestamp Feb 26, 2016 2:42:56 PM
	 * @param value
	 */
	private void sendCarMessage(String[] value) {
		String data = remoteData.getDate(initInfo, Command.LOADCARDATA);
		broadcast(data);
	}

	/**
	 * 发送信号灯信息
	 * 
	 * @timestamp Feb 23, 2016 2:02:37 AM
	 * @param value
	 */
	private void sendLightMessage(String[] value) {
		String data = remoteData.getDate(initInfo, Command.LOADLIGHTDATA);
		broadcast(data);
	}

	/**
	 * 发送车道信息
	 * 
	 * @timestamp Feb 23, 2016 1:35:26 AM
	 * @param value
	 */
	private void sendLaneMessage(String[] value) {
		String data = remoteData.getDate(initInfo, Command.LOADLANEDATA);
		broadcast(data);
	}

	/**
	 * 发送路信息
	 * 
	 * @timestamp Feb 22, 2016 9:01:30 PM
	 * @param value
	 */
	private void sendRoadMessage(String[] value) {
		String data = remoteData.getDate(initInfo, Command.LOADROADDATA);
		broadcast(data);
	}

	/**
	 * 解析客户端命令到消息队列中
	 * 
	 * @timestamp Feb 22, 2016 3:48:45 PM
	 * @param message
	 */
	private void parseMessage(String message) {
		message = message.replaceAll("'", "\"");// jackson貌似不支持'
		ObjectMapper mapper = new ObjectMapper();
		try {
			CommandEntity[] list = null;
			try {
				list = mapper.readValue(message, CommandEntity[].class);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (list != null && list.length > 0) {
				for (CommandEntity l : list) {
					queue.enQueue(l);// 放入命令队列
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送信息
	 * 
	 * @timestamp Feb 22, 2016 3:31:35 PM
	 * @param msg
	 *            要发送的字符串
	 */
	private synchronized void broadcast(String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e1) {
				throw new RuntimeException("server close session error!");
			}
		}
	}
}
