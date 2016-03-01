package edu.hpc.its.area.webservice;

import javax.jws.WebService;

/**
 * webService发布统计信息查询接口，不仅仅针对java程序<br />
 * 如果只有java程序通信的话建议使用JMX<br />
 * 传输速度：socket > JMX > webService
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

}
