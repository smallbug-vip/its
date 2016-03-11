package org.hpc.its.realize.core;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.hpc.its.realize.configuration.AbstractAConfiguration;
import org.hpc.its.realize.configuration.ConfigurationAFromProperties;
import org.hpc.its.realize.entities.a.Car;
import org.hpc.its.realize.entities.a.Lane;
import org.hpc.its.realize.entities.a.Light;
import org.hpc.its.realize.entities.a.Map_;
import org.hpc.its.realize.entities.a.Road;
import org.hpc.its.realize.entities.abs.AbstractLane;
import org.hpc.its.realize.entities.abs.AbstractRoad;

public class InitMessage {

	private String message;
	private Session session = null;
	private AbstractAConfiguration configuration;
	InitMap initMap = null;
	Logger log = Logger.getLogger(InitMessage.class);

	public InitMessage(String message, Session session, HttpSession httpSession) {
		super();
		this.message = message;
		this.session = session;
		configuration = ConfigurationAFromProperties.getConfiguration();
		this.initMap = new InitMap(this.message, configuration,(String)httpSession.getAttribute("mapId"));
		log.info(message+"__"+httpSession.getAttribute("mapId"));
	}

	public String sendMessage() {
		// send road message
		sendRoadsMessage();
		// send light message
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					sendLightsMessage();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}).start();
		// send car message
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					sendCarsMessage();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}).start();
		return null;
	}

	private void sendCarsMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("{'entity':'cars','coordinate':[");
		// get all local of car append
		Set<CreateCar> cs = initMap.getCreateCars();
		for (CreateCar c : cs) {
			// this local have all car
			Map<Long, Car> carMap = c.getCarMap();
			for (Entry<Long, Car> eCar : carMap.entrySet()) {
				Car car = eCar.getValue();
				sb.append(//
				"{'image':'" //
						+ car.getImage() + "','angle':'" //
						+ car.getAngle() + "','coor':[" //
						+ car.getCurrentXPoint() + "," //
						+ car.getCurrentYPoint() + "]},");
			}
		}
		sb.append("]}");
		broadcast(sb.toString());
	}

	private void sendLightsMessage() {
		Map<Long, Light> lights = initMap.getLightMap();
		StringBuilder sb = new StringBuilder();
		sb.append("{'entity':'lights','coordinate':[");
		for (Map.Entry<Long, Light> light : lights.entrySet()) {
			Light l = light.getValue();
			sb.append("{'state':'" + l.getState() + "','coor':[" + l.getCenterXPoint() + ","
					+ l.getCenterYPoint()//
					+ "," + l.getLeftXPoint() + "," + l.getLeftYPoint()//
					+ "," + l.getRightXPoint() + "," + l.getRightYPoint() + "]},");
		}
		sb.append("]}");
		broadcast(sb.toString());
	}

	private void sendRoadsMessage() {
		Map_ m = initMap.getMap_();
		StringBuilder sb = new StringBuilder();
		sb.append("{'entity':'roads','coordinate':[");
		for (AbstractRoad r : m.getRoads()) {
			sb.append("[" + //
					((Road) r).getDrawStartXPoint() + "," //
					+ ((Road) r).getDrawStartYPoint() + "," //
					+ ((Road) r).getDrawEndXPoint() + "," //
					+ ((Road) r).getDrawEndYPoint() + "]," //
			);
			for (AbstractLane l : r.getForwardLanes()) {
				Lane nl = ((Lane) l);
				sb.append("[" + //
						nl.getStartXPoint() + "," //
						+ nl.getStartYPoint() + "," //
						+ nl.getEndXPoint() + "," //
						+ nl.getEndYPoint() + "]," //
				);
			}
			for (AbstractLane l : r.getNegativeLanes()) {
				Lane nl = ((Lane) l);
				sb.append("[" + //
						nl.getStartXPoint() + "," //
						+ nl.getStartYPoint() + "," //
						+ nl.getEndXPoint() + "," //
						+ nl.getEndYPoint() + "]," //
				);
			}
		}
		sb.append("]}");
		broadcast(sb.toString());
	}

	private synchronized void broadcast(String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e1) {
				throw new RuntimeException("server close session error!");
			}
		}
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
