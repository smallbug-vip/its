package edu.hpc.its.area.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import edu.hpc.its.area.dao.mongodb.AreaExpeInfo;
import edu.hpc.its.area.dao.mongodb.AreaExpeInfoImpl;
import edu.hpc.its.area.dao.mongodb.CarExpeInfo;
import edu.hpc.its.area.dao.mongodb.CarExpeInfoImpl;
import edu.hpc.its.area.dao.mongodb.LightExpeInfo;
import edu.hpc.its.area.dao.mongodb.LightExpeInfoImpl;

/**
 * webService发布统计信息查询接口，不仅仅针对java程序<br />
 * 如果只有java程序通信的话建议使用JMX<br />
 * 传输速度：socket > RMI > webService
 * 
 * @timestamp Feb 29, 2016 6:29:07 PM
 * @author smallbug
 */
@WebService(//
name = "AreaInfoServiceImpl", // portType 的名称
portName = "AreaInfoServicePort", // port 的名称
serviceName = "AreaInfoService", // 服务名称
targetNamespace = "http://area.its.hpc.edu"// 指定命名空间
)
public class AreaInfoServiceImpl implements AreaInfoService {

	private AreaExpeInfo areaExpInfo = new AreaExpeInfoImpl();
	private LightExpeInfo lightExpInfo = new LightExpeInfoImpl();
	private CarExpeInfo carExpInfo = new CarExpeInfoImpl();

	@Override
	public String getAllExpInfo() {
		return areaExpInfo.selectAll().toString();
	}

	@Override
	public String getLight2Time(@WebParam(name = "expIds")String[] expIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"legend\":\"行驶时间\",\"category\":");
		sb.append(lightExpInfo.selectLightTimeByExpIds(expIds));
		sb.append(",\"bar\":");
		sb.append(carExpInfo.getAvgTime(expIds));
		sb.append("}");
		return sb.toString();
	}

}
