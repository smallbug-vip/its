package edu.hpc.its.center;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import edu.hpc.its.center.webservice.AreaInfoServiceImpl;

public class TestWebService {

	@Test
	public void test01() throws Exception {
		// wsdl地址
		URL wsdlDocumentLocation = new URL("http://127.0.0.1:8888/area?wsdl");

		// serviceName
		QName serviceName = new QName("http://area.its.hpc.edu", "AreaInfoService");

		// 创建服务视图
		Service service = Service.create(wsdlDocumentLocation, serviceName);

		// 得到porttype
		AreaInfoServiceImpl infoService = service.getPort(AreaInfoServiceImpl.class);

		// 调用 方法
		// 发送xml数据
		// 查询parentId等于1.，起始记录从1开始，结束记录下标为15
		String s = infoService.getAllExpInfo();
		System.out.println(s);
		List<String> list = new ArrayList<>();
		list.add("Exp000001");
		list.add("Exp000002");
		s = infoService.getLight2Time(list);
		System.out.println(s);

	}
}
