package edu.hpc.its.area.dao.mysql;

import java.lang.reflect.Method;

public class DaoWrap {

	private Object obj = null;
	private Method method = null;
	private Object[] args = null;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
