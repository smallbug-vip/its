package edu.hpc.its.center.config;

/**
 * 区域信息
 * 
 * @timestamp Feb 22, 2016 4:50:08 PM
 * @author smallbug
 */
public class AreaInfo {

	private String areaId;
	private String areaName;
	private String areaPort;
	private String areaIp;

	private String onecm;// 一厘米对应40 Double值
	private String compress;// 实际长度/压缩后长度，压缩率

	private String horizontalNum;// 横向路口个数
	private String verticalNum;// 纵向路口个数
	private String roadLength;// 路长100000cm
	private String laneNum;// 一个路有laneNum条单向行道，即一个路是laneNum*2条车道
	private String laneWide;// 道宽

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaPort() {
		return areaPort;
	}

	public void setAreaPort(String areaPort) {
		this.areaPort = areaPort;
	}

	public String getAreaIp() {
		return areaIp;
	}

	public void setAreaIp(String areaIp) {
		this.areaIp = areaIp;
	}

	public String getOnecm() {
		return onecm;
	}

	public void setOnecm(String onecm) {
		this.onecm = onecm;
	}

	public String getCompress() {
		return compress;
	}

	public void setCompress(String compress) {
		this.compress = compress;
	}

	public String getHorizontalNum() {
		return horizontalNum;
	}

	public void setHorizontalNum(String horizontalNum) {
		this.horizontalNum = horizontalNum;
	}

	public String getVerticalNum() {
		return verticalNum;
	}

	public void setVerticalNum(String verticalNum) {
		this.verticalNum = verticalNum;
	}

	public String getRoadLength() {
		return roadLength;
	}

	public void setRoadLength(String roadLength) {
		this.roadLength = roadLength;
	}

	public String getLaneNum() {
		return laneNum;
	}

	public void setLaneNum(String laneNum) {
		this.laneNum = laneNum;
	}

	public String getLaneWide() {
		return laneWide;
	}

	public void setLaneWide(String laneWide) {
		this.laneWide = laneWide;
	}

}
