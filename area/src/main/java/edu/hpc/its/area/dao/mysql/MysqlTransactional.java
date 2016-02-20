package edu.hpc.its.area.dao.mysql;

import java.util.regex.Matcher;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.aop.FilterChainSub;
import edu.hpc.its.area.service.ServiceBase;
import edu.hpc.its.area.util.MybatisUtil;

/**
 * 管理事务
 * 
 * @timestamp Feb 17, 2016 7:54:36 PM
 * @author smallbug
 */
public class MysqlTransactional implements FilterChainSub {

	private boolean flag = true;
	private SqlSession session = null;

	/**
	 * 数据库操作之前执行，为service注入session
	 */
	@Override
	public void before(Object target, Matcher[] matcher) {
		this.session = MybatisUtil.getSession();
		if (target instanceof ServiceBase) {
			((ServiceBase) target).setMapper(session);
		}
	}

	/**
	 * 数据库操作之后执行，判断是否commit
	 */
	@Override
	public void after(Object target, Matcher[] matcher) {
		if (flag && target instanceof ServiceBase && session != null) {
			for (Matcher m : matcher) {
				if (m.find()) {
					session.commit();
					break;
				}
			}
			flag = false;
		}
	}

	/**
	 * 数据库操作失败，处理异常并事务回滚
	 */
	@Override
	public void operateException(Exception exception, Object target) {
		if (flag && target instanceof ServiceBase && session != null) {
			session.rollback();
			flag = false;
		}
		exception.printStackTrace();
	}

	/**
	 * 关闭session
	 */
	@Override
	public void destroy(Object target) {
		if (session != null) {
			session.close();
			session = null;
		}
		flag = true;
	}

}
