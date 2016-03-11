package org.hpc.its.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.hpc.its.entities.Log;
import org.hpc.its.utils.JdbcUtil;

public class SaveLog {

	public static void save(List<Log> logs) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into log_02 (carid,time_,filename) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (Log log : logs) {
				pstmt.setLong(1, log.getCarId());
				pstmt.setLong(2, log.getTime());
				pstmt.setString(3, log.getFileName());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(null, pstmt, conn);
		}
	}
}
