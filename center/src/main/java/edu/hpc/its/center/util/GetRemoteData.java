package edu.hpc.its.center.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.hpc.its.center.config.AreaInfo;
import edu.hpc.its.center.config.InitInfo;

/**
 * 获取远程数据
 * 
 * @timestamp Feb 22, 2016 7:35:08 PM
 * @author smallbug
 */
public class GetRemoteData {

	/**
	 * 获取区域信息
	 * 
	 * @timestamp Feb 22, 2016 6:43:33 PM
	 * @param initInfo
	 * @param commond
	 * @param aREA
	 * @return
	 */
	public String getDate(InitInfo initInfo, String commond) {
		AreaInfo current = initInfo.getCurrentArea();// 浏览器要获取的区域
		String result = null;
		try {
			Socket s = new Socket(current.getAreaIp(), Integer.valueOf(current.getAreaPort()));
			OutputStream out = s.getOutputStream();
			out.write(commond.getBytes("UTF-8"));
			out.flush();
			s.shutdownOutput();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();// 内存输出流
			BufferedInputStream bi = new BufferedInputStream(s.getInputStream());
			BufferedOutputStream bo = new BufferedOutputStream(bos);

			byte[] by = new byte[1024];
			int len = 0;
			while ((len = bi.read(by)) != -1) {
				bo.write(by, 0, len);
			}
			bo.flush();//缓冲器刷新，必须！！！
			result = new String(bos.toByteArray());
			bo.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
