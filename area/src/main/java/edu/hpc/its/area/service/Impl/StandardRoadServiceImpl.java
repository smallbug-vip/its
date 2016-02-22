package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Road;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.dao.mapper.StandardRoadMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.exception.ServiceException;
import edu.hpc.its.area.service.StandardRoadService;

public class StandardRoadServiceImpl implements StandardRoadService {

	private StandardRoadMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardRoadMapper.class);

	}

	@Override
	public void insertRoad(Road road) throws ServiceException {
		try {
			mapper.insertRoad(road);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

	@Override
	public List<StandardRoad> getStandardCrosses(Long crossId) {
		List<StandardRoad> roads = null;
		try {
			roads = mapper.selectStandardCrosses(crossId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
		return roads;
	}

}
