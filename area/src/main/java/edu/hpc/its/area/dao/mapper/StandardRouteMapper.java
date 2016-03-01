package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Route;
import edu.hpc.its.area.core.StandardRoute;

public interface StandardRouteMapper {

	public void insertRoute(Route route) throws Exception;

	public List<StandardRoute> selectStandardRoute(Long areaId) throws Exception;
}
