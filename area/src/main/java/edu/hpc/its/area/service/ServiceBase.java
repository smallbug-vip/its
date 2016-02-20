package edu.hpc.its.area.service;

import org.apache.ibatis.session.SqlSession;

public interface ServiceBase {

	public void setMapper(SqlSession session);
}
