package edu.hpc.its.center.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class CenterInfoServiceImpl implements CenterInfoService {
	private AreaInfoServiceImpl infoService;

	public CenterInfoServiceImpl() {
		// wsdl地址
		URL wsdlDocumentLocation;
		try {
			wsdlDocumentLocation = new URL("http://127.0.0.1:8888/area?wsdl");
			// serviceName
			QName serviceName = new QName("http://area.its.hpc.edu", "AreaInfoService");
			// 创建服务视图
			Service service = Service.create(wsdlDocumentLocation, serviceName);
			// 得到porttype
			infoService = service.getPort(AreaInfoServiceImpl.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAllExpInfo() {
		return infoService.getAllExpInfo();
	}

	@Override
	public String getLight2Time(String[] expIds) {
		return infoService.getLight2Time(Arrays.asList(expIds));
	}

}
