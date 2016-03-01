package edu.hpc.its.area.test.mongodb;

import java.util.List;

import org.junit.Test;

import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.dao.mongodb.CarExpeInfo;
import edu.hpc.its.area.dao.mongodb.CarExpeInfoImpl;

public class CarExpeInfoImplTest {
	CarExpeInfo info = new CarExpeInfoImpl();

	@Test
	public void testInsertAppearTime() {
		info.insertAppearTime("Exp0002", 34L, 12233L);
	}

	@Test
	public void testInsertRoadTime() {
		info.insertRoadTime("Exp0002", 24L, 35L, 234L);
	}

	@Test
	public void testInsertCarInfo() {
		StandardCar car = new StandardCar();
		car.setId(34L);
		car.setSpeed(343.2);
		info.insertCarInfo("Exp0002", car);

	}

	@Test
	public void testDelOneCarInfo() {
		info.delOneCarInfo("Exp0002", 34L);
	}

	@Test
	public void testDelExpCarInfo() {
		info.delExpCarInfo("Exp0002");
	}

	@Test
	public void testSelectCarRoadTime() {
		List<String> ss = info.selectCarRoadTime("Exp0002", 24L, 35L);
		if (ss != null) {
			for (String s : ss) {
				System.out.println(s);
			}
		}
	}

	@Test
	public void testSelectCarAppearTime() {
		String s = info.selectCarAppearTime("Exp0002", 34L);
		System.out.println(s);
	}

	@Test
	public void testSelectCarInfo() {
		String s = info.selectCarInfo("Exp000002", 34L);
		System.out.println(s);
	}

	@Test
	public void testSelectExpCar() {
		List<String> ss = info.selectExpCar("Exp000002");
		if (ss != null) {
			for (String s : ss) {
				System.out.println(s);
			}
		}
	}

	@Test
	public void testGetAvgTime() {
		String[] s = { "Exp000002", "Exp000001" };
		System.out.println(info.getAvgTime(s));
	}

}
