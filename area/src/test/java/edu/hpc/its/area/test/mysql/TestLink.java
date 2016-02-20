package edu.hpc.its.area.test.mysql;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import edu.hpc.its.area.dao.mapper.TestLinkEntityMapper;
import edu.hpc.its.area.test.TestLinkEntity;
import edu.hpc.its.area.util.MybatisUtil;

public class TestLink {

	@Test
	public void testGetSession() {
		SqlSession session = MybatisUtil.getSession();
		System.out.println(session);
	}

	@Test
	public void testSave() {
		SqlSession session = MybatisUtil.getSession();
		try {
			transaction(session);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			MybatisUtil.closeSession(session);
		}
	}

	private void transaction(SqlSession session) {
		TestLinkEntityMapper mapper = session.getMapper(TestLinkEntityMapper.class);
		TestLinkEntity testLink = new TestLinkEntity();
		testLink.setName("小虫");
		mapper.insertTestLinkEntity(testLink);
		System.out.println(testLink.getId());
	}
}
