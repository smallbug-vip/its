package edu.hpc.its.area.service.Impl;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Light;
import edu.hpc.its.area.dao.mapper.StandardLightMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.exception.ServiceException;
import edu.hpc.its.area.service.StandardLightService;

public class StandardLightServiceImpl implements StandardLightService {

	private StandardLightMapper mapper = null;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardLightMapper.class);

	}

	@Override
	public void insertLight(Light light) throws ServiceException {
		try {
			mapper.insertLight(light);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}

	}

}
