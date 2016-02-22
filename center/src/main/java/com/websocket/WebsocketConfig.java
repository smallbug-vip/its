package com.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class WebsocketConfig implements ServerApplicationConfig {

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		Set<Class<?>> res = new HashSet<>();
		for (Class<?> cs : scanned) {
			if (cs.getPackage().getName().startsWith("edu.hpc.its.center.web")) {
				res.add(cs);
			}
		}
		return res;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		Set<ServerEndpointConfig> res = new HashSet<>();
		return res;
	}
}
