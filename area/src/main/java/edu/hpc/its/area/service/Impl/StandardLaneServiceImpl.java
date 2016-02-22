package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Lane;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.dao.mapper.StandardLaneMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.service.StandardLaneService;

public class StandardLaneServiceImpl implements StandardLaneService {

	private StandardLaneMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardLaneMapper.class);

	}

	@Override
	public void insertLane(Lane lane) {
		try {
			mapper.insertLane(lane);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}

	}

	@Override
	public List<StandardLane> getStandardLanes(Long roadId) {
		List<StandardLane> lanes = null;
		try {
			lanes = mapper.selectStandardLanes(roadId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
		return lanes;
	}

}
