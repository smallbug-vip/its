package edu.hpc.its.area.service.Impl;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.dao.mapper.StandardAreaMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.service.StandardAreaService;

public class StandardAreaServiceImpl implements StandardAreaService {

	private StandardAreaMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardAreaMapper.class);
	}

	@Override
	public void insertArea(Area area) {
		try {
			mapper.insertArea(area);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

	@Override
	public StandardArea selectAreaByName(String name) {
		StandardArea area = null;
		try {
			area = (StandardArea) mapper.selectAreaByName(name);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
		return area;
	}

}
