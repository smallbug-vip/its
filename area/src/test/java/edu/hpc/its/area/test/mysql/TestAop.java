package edu.hpc.its.area.test.mysql;

import org.junit.Test;

import edu.hpc.its.area.dao.mysql.ServiceProxy;
import edu.hpc.its.area.service.LinkEntityService;
import edu.hpc.its.area.service.Impl.LinkEntityServiceImpl;
import edu.hpc.its.area.test.TestLinkEntity;

public class TestAop {

	@Test
	public void testInsert() {
		// 创建实体
		TestLinkEntity entity = new TestLinkEntity();
		entity.setName("小猫");

		LinkEntityService s = (LinkEntityService) ServiceProxy//
				.getServiceProxy(new LinkEntityServiceImpl());

		s.insertLinkEntity(entity);
	}

	@Test
	public void testSelect() {

		LinkEntityService s = (LinkEntityService) ServiceProxy//
				.getServiceProxy(new LinkEntityServiceImpl());

		TestLinkEntity entity = s.selectLinkEntityById(28L);
		System.out.println(entity.getId() + "-----" + entity.getName());
	}

	@Test
	public void testDelete() {
		LinkEntityService s = (LinkEntityService) ServiceProxy//
				.getServiceProxy(new LinkEntityServiceImpl());
		s.deleteLinkEntityById(37L);
	}
}
