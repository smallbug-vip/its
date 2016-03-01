package edu.hpc.its.area.test.mongodb;

import java.util.List;

import org.junit.Test;

import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.dao.mongodb.LightExpeInfo;
import edu.hpc.its.area.dao.mongodb.LightExpeInfoImpl;

public class LightExpeInfoImplTest {

	LightExpeInfo info = new LightExpeInfoImpl();

	@Test
	public void testInsertLightInfo() {
		StandardLight light = new StandardLight();
		light.setId(34L);
		light.setGreen(23000.0);
		light.setRed(34343.0);
		StandardCross cross = new StandardCross();
		cross.setId(23L);
		light.setStandardCross(cross);
		info.insertLightInfo("Exp0002", light);
	}

	@Test
	public void testDelLightInfo() {
		info.delLightInfo("Exp0002");
	}

	@Test
	public void testSelectLightInfo() {
		List<String> ss = info.selectLightInfo("Exp0002");
		if (ss != null) {
			for (String s : ss) {
				System.out.println(s);
			}
		}
	}

	@Test
	public void testSelectLightTimeByExpIds() {
		System.out.println(info.selectLightTimeByExpIds(new String[] { "Exp000001", "Exp000002" }));
	}

}
