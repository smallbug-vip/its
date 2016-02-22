package edu.hpc.its.area.dao.mysql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import edu.hpc.its.area.core.Direction;

/**
 * mybatis Direction枚举转换类
 * 
 * @timestamp Feb 20, 2016 10:14:35 PM
 * @author smallbug
 */
public class EnumTypeHandler implements TypeHandler<Direction> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Direction parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null) {
			ps.setObject(i, parameter.getDirection());
		} else {
			ps.setString(i, null);
		}
	}

	@Override
	public Direction getResult(ResultSet rs, String columnName) throws SQLException {
		for (Direction d : Direction.values()) {
			if (d.getDirection() == rs.getInt(columnName)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public Direction getResult(ResultSet rs, int columnIndex) throws SQLException {

		for (Direction d : Direction.values()) {
			if (d.getDirection() == rs.getInt(columnIndex)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public Direction getResult(CallableStatement cs, int columnIndex) throws SQLException {
		for (Direction d : Direction.values()) {
			if (d.getDirection() == cs.getInt(columnIndex)) {
				return d;
			}
		}
		return null;
	}

}
