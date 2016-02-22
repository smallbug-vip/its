package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Light;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.dao.mapper.StandardLightMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.service.StandardLightService;

public class StandardLightServiceImpl implements StandardLightService {

	private StandardLightMapper mapper = null;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardLightMapper.class);

	}

	@Override
	public void insertLight(Light light) {
		try {
			mapper.insertLight(light);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}

	}

	@Override
	public List<StandardLight> getStandardLight(Long crossId) {
		List<StandardLight> lights = null;
		try {
			lights = mapper.selectStandardLight(crossId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
		return lights;
	}

}
