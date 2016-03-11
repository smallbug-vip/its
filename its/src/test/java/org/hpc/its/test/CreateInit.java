package org.hpc.its.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.junit.Test;

public class CreateInit {

	@Test
	public void createRoadInit() {

		long laneId = 300001L;
		StringBuilder sb = new StringBuilder();
		for (int i = 200001; i <= 200123; i++) {
			sb.append("<road id='" + i + "' refer='200' angle='' length='160'>\r\n");
			sb.append("<lane id='" + (laneId++) + "' width='5'></lane>\r\n");
			sb.append("<lane id='" + (laneId++) + "' width='5'></lane>\r\n");
			sb.append("<lane id='" + (laneId++) + "' width='5'></lane>\r\n");
			sb.append("<lane id='" + (laneId++) + "' width='5'></lane>\r\n");
			sb.append("</road>\r\n");
		}
		System.out.println(sb.toString());
	}

	@Test
	public void createIntersection() {
		long laneId = 500001L;
		StringBuilder sb = new StringBuilder();
		for (int i = 400001; i <= 400054; i++) {
			sb.append("<intersection id='" + i + "' refer='200'>\r\n");
			sb.append("<road id='200'>\r\n");
			sb.append("<light id='" + laneId++ + "' state='0' group='0' sleep='5000'></light>\r\n");
			sb.append("</road>\r\n");
			sb.append("<road id='200'>\r\n");
			sb.append("<light id='" + laneId++ + "' state='0' group='1' sleep='5000'></light>\r\n");
			sb.append("</road>\r\n");
			sb.append("<road id='200'>\r\n");
			sb.append("<light id='" + laneId++ + "' state='0' group='0' sleep='5000'></light>\r\n");
			sb.append("</road>\r\n");
			sb.append("<road id='200'>\r\n");
			sb.append("<light id='" + laneId++ + "' state='0' group='1' sleep='5000'></light>\r\n");
			sb.append("</road>\r\n");
			sb.append("</intersection>\r\n");
		}
		System.out.println(sb.toString());
	}

	@Test
	public void createRoad() throws Exception {
		String[] cs = { "210.44.72.152:8080/its/image/a.png", "210.44.72.152:8080/its/image/b.png", "210.44.72.152:8080/its/image/c.png", "210.44.72.152:8080/its/image/d.png",
				"210.44.72.152:8080/its/image/e.png", };
		int[] speeds = { 3, 4, 5, 6 };
		InputStream in = CreateInit.class.getClassLoader().getResourceAsStream("road2.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = null;
		StringBuilder sb = new StringBuilder();
		int count = 50;
		int carId = 600001;

		while ((str = br.readLine()) != null) {
			String[] strings = str.split(",");
			Random r = new Random();
			int speed = speeds[r.nextInt(4)];
			sb.append("<car id='" + carId + "' speed='" + speed + "' image='" + cs[r.nextInt(5)] + "' amount='" + count + "' sleep='10000' startDistance='0' endDistance='0'>\r\n");
			for (String s : strings) {
				long l = Long.valueOf(s);
				sb.append("<road id='" + (200000 + l) + "'></road>\r\n");
			}
			sb.append("</car>\r\n");
			carId += count;
		}

		System.out.println(sb.toString());
	}
}
