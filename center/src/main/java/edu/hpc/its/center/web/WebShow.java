package edu.hpc.its.center.web;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import edu.hpc.its.center.config.AreaInfo;
import edu.hpc.its.center.config.InitInfo;
import edu.hpc.its.center.util.Dom4jUtil;

/**
 * HTML5与界面交互数据的Servlet
 * 
 * @timestamp Feb 22, 2016 12:34:24 AM
 * @author smallbug
 */
@ServerEndpoint(value = "/view01")
public class WebShow {

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(WebShow.class);
	/**
	 * 发送信息
	 */
	private SendMessage sendMessage = null;
	/**
	 * WebService的Session
	 */
	private Session session = null;

	/**
	 * 建立连接后的初始化信息
	 */
	private InitInfo initInfo;

	@OnOpen
	public void start(Session session, EndpointConfig config) {
		this.session = session;
		init();// 如果有后台管理系统的话，建议一个Session对应一个初始化而不是一次WebService连接对应一次初始化
	}

	/**
	 * 根据配置文件初始化区域信息
	 * 
	 * @timestamp Feb 22, 2016 5:20:28 PM
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		initInfo = new InitInfo();
		String path = WebShow.class.getClassLoader()//
				.getResource("areas.xml").toString();
		try {
			InputStream in = new FileInputStream(path.substring(6));
			Document document = Dom4jUtil.getDocument(in);
			Element eleRoot = document.getRootElement();
			List<Element> areaList = eleRoot.elements("area");
			AreaInfo info;
			boolean isCurrentArea = true;
			for (Element e : areaList) {
				info = new AreaInfo();
				List<Element> propertyList = e.elements("property");
				for (Element p : propertyList) {
					String name = p.attributeValue("name");
					String value = p.attributeValue("value");
					PropertyDescriptor pd = new PropertyDescriptor(name, info.getClass());
					Method m = pd.getWriteMethod();
					m.invoke(info, value);
				}
				initInfo.addAreaInfo(info);
				if (isCurrentArea) {
					initInfo.setCurrentArea(info);
					isCurrentArea = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void end(Session session) {
		System.out.println("end!");
	}

	@OnMessage
	public void receive(String message, EndpointConfig config) {
		if (sendMessage == null) {
			createSendMessage();
		}
		sendMessage.sendMessage(message, initInfo);
	}

	private synchronized void createSendMessage() {
		if (sendMessage == null) {
			sendMessage = new SendMessage(session);
		}

	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		log.error("error: " + t.toString(), t);
	}

}
