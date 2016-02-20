package edu.hpc.its.area.dao.mysql;

import java.lang.reflect.Proxy;

import edu.hpc.its.area.aop.ApplicationFilterChain;
import edu.hpc.its.area.aop.Interceptor;
import edu.hpc.its.area.service.ServiceBase;

public class ServiceProxy {

	public static ServiceBase getServiceProxy(ServiceBase service) {
		// 拦截链
		ApplicationFilterChain filterChain = new ApplicationFilterChain();
		filterChain.addChain(new MysqlTransactional());

		Interceptor in = new Interceptor(service, filterChain, "^insert*", "^delete*", "^update*");
		ServiceBase s = (ServiceBase) Proxy.newProxyInstance(ServiceProxy.class.getClassLoader(), //
				service.getClass().getInterfaces(), in);
		return s;
	}

}
