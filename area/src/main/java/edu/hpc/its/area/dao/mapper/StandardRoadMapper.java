package edu.hpc.its.area.dao.mapper;

import java.util.List;

import edu.hpc.its.area.Road;
import edu.hpc.its.area.core.StandardRoad;

public interface StandardRoadMapper {

	public void insertRoad(Road road) throws Exception;

	public List<StandardRoad> selectStandardCrosses(Long crossId) throws Exception;
}
