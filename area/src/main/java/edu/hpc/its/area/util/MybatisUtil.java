package edu.hpc.its.area.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.hpc.its.area.exception.MyBatisException;

/**
 * mybatis辅助类
 * 
 * @timestamp Feb 17, 2016 1:06:55 PM
 * @author smallbug
 */
public class MybatisUtil {

	private final String resource = "SqlMapConfig.xml";
	private InputStream input;
	private static MybatisUtil mybatisUtil = null;
	private static SqlSessionFactory factory = null;

	/**
	 * 初始化配置文件
	 */
	private MybatisUtil() {
		super();
		try {
			input = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			throw new MyBatisException(e);
		}
	}

	/**
	 * 获取Session
	 * 
	 * @timestamp Feb 17, 2016 11:46:25 AM
	 * @return
	 */
	public static SqlSession getSession() {
		if (mybatisUtil == null)
			createFactory();
		return factory.openSession();
	}

	/**
	 * 关闭session
	 * 
	 * @timestamp Feb 17, 2016 1:05:39 PM
	 * @param session
	 */
	public static void closeSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}

	private synchronized static void createFactory() {
		if (mybatisUtil == null)
			mybatisUtil = new MybatisUtil();
	}
}
