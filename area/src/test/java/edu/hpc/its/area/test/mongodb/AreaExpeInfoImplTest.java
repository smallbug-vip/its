package edu.hpc.its.area.test.mongodb;

import java.util.List;

import org.junit.Test;

import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.dao.mongodb.AreaExpeInfoImpl;

public class AreaExpeInfoImplTest {

	AreaExpeInfoImpl info = new AreaExpeInfoImpl();

	@Test
	public void testCreateExp() {
		StandardArea area = new StandardArea();
		area.setCrossNum(12);
		area.setId(23L);
		area.setLength(232323);
		area.setWidth(543434);
		area.setName("NUM001");
		area.setIp("192.168.88.132");
		area.setLightNum(43);
		info.createExp("Exp0002", area);
	}

	@Test
	public void testSelectExpByExpId() {
		String s = info.selectExpByExpId("Exp0001");
		System.out.println(s);
	}

	@Test
	public void testSelectExpByAreaId() {
		List<String> ss = info.selectExpByAreaId(23L);
		for (String s : ss) {
			System.out.println(s);
		}
	}

	@Test
	public void testSelectAll() {
		List<String> ss = info.selectAll();
		if (ss != null) {
			for (String s : ss) {
				System.out.println(s);
			}
		}

	}

	@Test
	public void testDelExpByExpId() {
//		info.delExpByExpId("Exp000001");
		info.delExpByExpId("Exp000001");
//		info.delExpByExpId("Exp000003");
//		info.delExpByExpId("Exp000004");
//		info.delExpByExpId("Exp000005");
//		info.delExpByExpId("Exp000006");
	}

	@Test
	public void testSelectCarNum() {
		long l = info.selectCarNum("Exp000002");
		System.out.println(l);
	}

}
