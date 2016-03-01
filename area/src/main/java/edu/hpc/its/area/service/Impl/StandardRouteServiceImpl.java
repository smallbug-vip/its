package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Route;
import edu.hpc.its.area.core.StandardRoute;
import edu.hpc.its.area.dao.mapper.StandardRouteMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.service.StandardRouteService;

/**
 * 行车路线Service
 * 
 * @timestamp Feb 24, 2016 11:08:33 AM
 * @author smallbug
 */
public class StandardRouteServiceImpl implements StandardRouteService {

	StandardRouteMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		mapper = session.getMapper(StandardRouteMapper.class);
	}

	@Override
	public void insertRoute(Route route) {
		try {
			mapper.insertRoute(route);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

	@Override
	public List<StandardRoute> getStandardRoutes(Long areaId) {
		try {
			return mapper.selectStandardRoute(areaId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

}
