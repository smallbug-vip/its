package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Cross;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.dao.mapper.StandardCrossMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.exception.ServiceException;
import edu.hpc.its.area.service.StandardCrossService;

public class StandardCrossServiceImpl implements StandardCrossService {

	private StandardCrossMapper mapper = null;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardCrossMapper.class);
	}

	@Override
	public void insertCross(Cross cross) throws ServiceException {
		try {
			mapper.insertCross(cross);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

	@Override
	public List<StandardCross> getStandardCrosses(Long areaId) {
		List<StandardCross> scs = null;
		try {
			scs = mapper.selectStandardCrosses(areaId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
		return scs;
	}

}
