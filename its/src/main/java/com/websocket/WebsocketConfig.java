package com.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import org.hpc.its.realize.core.InitMessage;

public class WebsocketConfig implements ServerApplicationConfig {

	private static Map<String, InitMessage> cacheSession = new HashMap<>();
	private static Object obj = new Object();

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		// Deploy all WebSocket endpoints defined by annotations in the examples
		// web application. Filter out all others to avoid issues when running
		// tests on Gump
		Set<Class<?>> res = new HashSet<>();
		for (Class<?> cs : scanned) {
			if (cs.getPackage().getName().startsWith("org.hpc.its.realize.")) {
				res.add(cs);
			}
		}
		return res;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		Set<ServerEndpointConfig> res = new HashSet<>();
		/*
		 * 
		 * if (scanned.contains(EchoEndpoint.class)) {
		 * res.add(ServerEndpointConfig.Builder.create( EchoEndpoint.class,
		 * "/websocket/echoProgrammatic").build()); }
		 */
		return res;
	}

	public static Map<String, InitMessage> getCacheSession() {
		return cacheSession;
	}

	public static synchronized Object getLock() {
		return obj;
	}
}
