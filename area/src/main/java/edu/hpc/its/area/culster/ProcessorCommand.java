package edu.hpc.its.area.culster;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Lane;
import edu.hpc.its.area.core.Direction;
import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.core.StandardCross;
import edu.hpc.its.area.core.StandardLane;
import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.core.StandardRoad;
import edu.hpc.its.area.factory.StandardEntityFactory;

/**
 * 用于处理和响应中心服务器发送来的命令
 * 
 * @timestamp Feb 22, 2016 5:13:17 PM
 * @author smallbug
 */
public class ProcessorCommand {

	/**
	 * 日志
	 */
	Logger log = Logger.getLogger(ProcessorCommand.class);

	/**
	 * 区域
	 */
	private StandardArea area = null;
	/**
	 * 本次会话
	 */
	private Socket socket = null;

	/**
	 * 解析会话
	 * 
	 * @timestamp Feb 22, 2016 8:20:49 PM
	 * @param socket
	 * @param area
	 */
	public void process(Socket socket, Area area) {
		this.area = (StandardArea) area;
		this.socket = socket;
		try {
			BufferedReader re = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String str = re.readLine();
			switch (str) {
			case Command.AREACHOOSE:
				areaInfo();
				log.info("executed command -> " + Command.AREACHOOSE);
				break;
			case Command.LOADLANEDATA:
				laneInfo();
				log.info("executed command -> " + Command.LOADLANEDATA);
				break;
			case Command.LOADLIGHTDATA:
				lightInfo();
				break;
			case Command.LOADCARDATA:
				carInfo();
				break;
			case Command.LOADROADDATA:
				roadInfo();
				log.info("executed command -> " + Command.LOADROADDATA);
				break;
			default:
				break;
			}
			re.close();
			socket.close();
		} catch (IOException e) {
			log.error("---->> client error close!");
		}
	}

	/**
	 * 将路信息写入到Socket中
	 * 
	 * @timestamp Feb 22, 2016 7:48:48 PM
	 */
	private void roadInfo() {
		Map<Long, StandardRoad> roadMap = StandardEntityFactory.getRoadMap();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream bo = new BufferedOutputStream(bos);

		writeHead(bo, "roads");
		int i = 0;
		for (Entry<Long, StandardRoad> roadEntrity : roadMap.entrySet()) {
			StandardRoad road = roadEntrity.getValue();
			i++;
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				FilterProvider filters = new SimpleFilterProvider()//
						.addFilter(road.getClass().getName(), //
								SimpleBeanPropertyFilter.filterOutAllExcept(//
										"xxOne", "yyOne", "xxOther", "yyOther"));
				objectMapper.setFilters(filters);
				objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
					private static final long serialVersionUID = -2068760251610204355L;

					@Override
					public Object findFilterId(AnnotatedClass ac) {
						return ac.getName();
					}
				});
				objectMapper.writeValue(bo, road);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (i != roadMap.size())
					bo.write(",".getBytes(JsonEncoding.UTF8.getJavaName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		send(bos, bo);
	}

	/**
	 * 封装json数据头部
	 * 
	 * @timestamp Feb 22, 2016 11:23:32 PM
	 * @param bo
	 * @param entity
	 */
	private void writeHead(BufferedOutputStream bo, String entity) {
		try {
			bo.write(("{'entity':'" + entity + "','coordinate':[").getBytes(JsonEncoding.UTF8.getJavaName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送json数据
	 * 
	 * @timestamp Feb 22, 2016 11:23:49 PM
	 * @param bos
	 * @param bo
	 */
	private void send(ByteArrayOutputStream bos, BufferedOutputStream bo) {
		try {
			bo.write("]}".getBytes(JsonEncoding.UTF8.getJavaName()));
			bo.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int len = 0;
		byte[] by = new byte[1024];
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			BufferedInputStream bi = new BufferedInputStream(bis);
			BufferedOutputStream sbo = new BufferedOutputStream(socket.getOutputStream());
			while ((len = bi.read(by)) != -1) {
				sbo.write(by, 0, len);
			}
			bos.close();
			sbo.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			log.error("---->> client error close!");
		}
	}

	/**
	 * 将车辆信息写入到Socket中
	 * 
	 * @timestamp Feb 22, 2016 7:49:23 PM
	 */
	private void carInfo() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream bo = new BufferedOutputStream(bos);

		writeHead(bo, "cars");
		try {
			bo.write(StandardEntityFactory.getCarInfoJson().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		send(bos, bo);
	}

	/**
	 * 将信号灯信息写入到Socket中
	 * 
	 * @timestamp Feb 22, 2016 7:48:59 PM
	 */
	private void lightInfo() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream bo = new BufferedOutputStream(bos);

		writeHead(bo, "lights");
		try {
			List<StandardCross> crosses = area.getCrosses();
			for (StandardCross cross : crosses) {
				Map<Direction, StandardLight> lights = cross.getLights();
				for (Entry<Direction, StandardLight> lightEntry : lights.entrySet()) {
					StandardLight light = lightEntry.getValue();
					ObjectMapper objectMapper = new ObjectMapper();
					FilterProvider filters = new SimpleFilterProvider()//
							.addFilter(light.getClass().getName(), //
									SimpleBeanPropertyFilter.filterOutAllExcept(//
											"oneX", "oneY", "otherX", "otherY", "lightState"));
					objectMapper.setFilters(filters);
					objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 4108411603935472019L;

						@Override
						public Object findFilterId(AnnotatedClass ac) {
							return ac.getName();
						}
					});
					objectMapper.writeValue(bo, light);
					try {
						bo.write(",".getBytes(JsonEncoding.UTF8.getJavaName()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		send(bos, bo);

	}

	/**
	 * 将车道信息写入到Socket中
	 * 
	 * @timestamp Feb 22, 2016 7:44:18 PM
	 */
	private void laneInfo() {

		Map<Long, StandardRoad> roadMap = StandardEntityFactory.getRoadMap();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream bo = new BufferedOutputStream(bos);
		writeHead(bo, "lanes");
		for (Entry<Long, StandardRoad> roadEntrity : roadMap.entrySet()) {
			StandardRoad road = roadEntrity.getValue();
			// 一边车道
			Map<Integer, Lane> oneLanes = road.getOneLane();
			for (Entry<Integer, Lane> laneEntrity : oneLanes.entrySet()) {
				StandardLane lane = (StandardLane) laneEntrity.getValue();
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					FilterProvider filters = new SimpleFilterProvider()//
							.addFilter(lane.getClass().getName(), //
									SimpleBeanPropertyFilter.filterOutAllExcept(//
											"xxOne", "yyOne", "xxOther", "yyOther"));
					objectMapper.setFilters(filters);
					objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 4108411603935472019L;

						@Override
						public Object findFilterId(AnnotatedClass ac) {
							return ac.getName();
						}
					});
					objectMapper.writeValue(bo, lane);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					bo.write(",".getBytes(JsonEncoding.UTF8.getJavaName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 另一边车道
			Map<Integer, Lane> otherLanes = road.getOtherLane();
			for (Entry<Integer, Lane> laneEntrity : otherLanes.entrySet()) {
				StandardLane lane = (StandardLane) laneEntrity.getValue();
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					FilterProvider filters = new SimpleFilterProvider()//
							.addFilter(lane.getClass().getName(), //
									SimpleBeanPropertyFilter.filterOutAllExcept(//
											"xxOne", "yyOne", "xxOther", "yyOther"));
					objectMapper.setFilters(filters);
					objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {

						/**
						 * 
						 */
						private static final long serialVersionUID = -1671219236123947679L;

						@Override
						public Object findFilterId(AnnotatedClass ac) {
							return ac.getName();
						}
					});
					objectMapper.writeValue(bo, lane);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					bo.write(",".getBytes(JsonEncoding.UTF8.getJavaName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		send(bos, bo);
	}

	/**
	 * 将区域信息写入到Socket中
	 * 
	 * @timestamp Feb 22, 2016 2:25:07 PM
	 */
	private void areaInfo() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream bo = new BufferedOutputStream(bos);

		writeHead(bo, "areas");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			FilterProvider filters = new SimpleFilterProvider()//
					.addFilter(area.getClass().getName(), //
							SimpleBeanPropertyFilter.serializeAllExcept(//
									"crosses"));
			objectMapper.setFilters(filters);
			objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
				private static final long serialVersionUID = -2068760251610204355L;

				@Override
				public Object findFilterId(AnnotatedClass ac) {
					return ac.getName();
				}

			});
			objectMapper.writeValue(bo, area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		send(bos, bo);
	}

	/**
	 * 输出初始化后的数据，测试使用
	 * 
	 * @timestamp Feb 22, 2016 7:40:43 PM
	 * @param area
	 * @return
	 */
	/*
	 * private String printInfo(StandardArea area) { StringBuilder sb = new
	 * StringBuilder(); for (StandardCross cross : area.getCrosses()) {
	 * sb.append("cross id -> " + cross); for (Entry<Direction, StandardLight>
	 * lights : cross.getLights().entrySet()) { StandardLight light =
	 * lights.getValue(); sb.append("light -> " + light); } for
	 * (Entry<Direction, StandardRoad> roads : cross.getRoads().entrySet()) {
	 * StandardRoad road = roads.getValue(); sb.append("road -> " + road); for
	 * (Entry<Integer, Lane> oneLane : road.getOneLane().entrySet()) {
	 * StandardLane lane = (StandardLane) oneLane.getValue(); sb.append(
	 * "lane -> " + lane); } for (Entry<Integer, Lane> otherLane :
	 * road.getOtherLane().entrySet()) { StandardLane lane = (StandardLane)
	 * otherLane.getValue(); sb.append("lane2 -> " + lane); } } } return
	 * sb.toString(); }
	 */
}
