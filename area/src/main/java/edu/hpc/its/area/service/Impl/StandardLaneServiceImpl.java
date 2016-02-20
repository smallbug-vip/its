package edu.hpc.its.area.service.Impl;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.dao.mapper.StandardLaneMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.exception.ServiceException;
import edu.hpc.its.area.service.StandardLaneService;

public class StandardLaneServiceImpl implements StandardLaneService {

	private StandardLaneMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardLaneMapper.class);

	}

	@Override
	public void insertLane(Lane lane) throws ServiceException {
		try {
			mapper.insertLane(lane);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}

	}

}
