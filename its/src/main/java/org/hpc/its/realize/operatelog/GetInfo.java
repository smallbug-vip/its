package org.hpc.its.realize.operatelog;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.hpc.its.dao.SaveLog;
import org.hpc.its.entities.Log;
import org.junit.Test;

public class GetInfo {

	private void getInfo(String path) throws Exception {
		path = "log/" + path;
		InputStream in = GetInfo.class.getClassLoader().getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String str = null;
		int i = 0;
		List<Log> logs = new ArrayList<>();
		while ((str = reader.readLine()) != null) {
			if (str.contains("CarRun-Thread")) {
				i++;
				Log log = new Log();
				String str1 = str.substring(str.indexOf("car id = ") + 9);
				String id = str1.substring(0, 6);
				String str2 = str.substring(str.indexOf("time is ") + 8);
				String time = str2.substring(0, str2.lastIndexOf("s"));
				log.setCarId(Long.valueOf(id));
				log.setTime(Long.valueOf(time));
				path = str.substring(str.lastIndexOf("init"));
				log.setFileName(path);
				logs.add(log);
				if (i % 50 == 0) {
					SaveLog.save(logs);
					logs.clear();
				}
			}
			if (logs != null) {
				SaveLog.save(logs);
				logs.clear();
			}
		}
		System.out.println(i);
		reader.close();
		in.close();
	}

	@Test
	public void bootStrap() throws Exception {
		GetInfo getInfo = new GetInfo();
		getInfo.getInfo("car3.log");
	}
}
