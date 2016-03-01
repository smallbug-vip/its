package edu.hpc.its.area.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.Car;
import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.dao.mapper.StandardCarMapper;
import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.service.StandardCarService;

/**
 * 车Service实现
 * 
 * @timestamp Feb 24, 2016 1:50:55 PM
 * @author smallbug
 */
public class StandardCarServiceImpl implements StandardCarService {

	StandardCarMapper mapper;

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(StandardCarMapper.class);
	}

	@Override
	public void insertCar(Car car) {
		try {
			mapper.insertCar(car);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

	@Override
	public List<StandardCar> getStandardCars(Long areaId) {
		try {
			return mapper.selectStandardCar(areaId);
		} catch (Exception e) {
			throw new MyBatisException(e);
		}
	}

}
