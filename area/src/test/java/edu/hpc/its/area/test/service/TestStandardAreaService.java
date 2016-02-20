package edu.hpc.its.area.test.service;

import org.junit.Before;
import org.junit.Test;

import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.dao.mysql.ServiceProxy;
import edu.hpc.its.area.service.StandardAreaService;
import edu.hpc.its.area.service.Impl.StandardAreaServiceImpl;

public class TestStandardAreaService {

	StandardAreaService s = null;

	@Before
	public void before() {
		s = (StandardAreaService) ServiceProxy//
				.getServiceProxy(new StandardAreaServiceImpl());
	}

	@Test
	public void testInsert() {
		StandardArea area = new StandardArea();
		area.setIp("192.168.88.131");
		area.setCrossNum(100);
		area.setLength(10000L);
		area.setLightNum(100);
		area.setName("三号区域");
		area.setPort(12000);
		area.setRoadNum(1000);
		area.setWidth(10000L);
		s.insertArea(area);
	}
}
