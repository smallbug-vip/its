package org.hpc.its.realize.view;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.hpc.its.realize.core.InitMessage;

import com.websocket.GetHttpSessionConfigurator;
import com.websocket.WebsocketConfig;

@ServerEndpoint(value = "/view01", configurator = GetHttpSessionConfigurator.class)
public class WebShow {

	Logger log = Logger.getLogger(WebShow.class);

	private Session session = null;
	private HttpSession httpSession = null;

	@OnOpen
	public void start(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		this.session = session;
		this.httpSession = httpSession;
		log.info("ip : " + httpSession.getAttribute("remoteIp") + " connection!");
	}

	@OnClose
	public void end(Session session) {
		System.out.println("end!");
	}

	@OnMessage
	public void receive(String message, EndpointConfig config) {
		InitMessage initMessage = null;
		if (WebsocketConfig.getCacheSession().containsKey(httpSession.getAttribute("mapId"))) {
			initMessage = WebsocketConfig.getCacheSession()//
					.get(httpSession.getAttribute("mapId"));
			initMessage.setSession(session);
			initMessage.sendMessage();
		} else {
			initMessage = new InitMessage(message, session, httpSession);
			WebsocketConfig.getCacheSession()//
					.put((String) httpSession.getAttribute("mapId"), initMessage);
			initMessage.sendMessage();
		}
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		log.error("error: " + t.toString(), t);
	}

}
