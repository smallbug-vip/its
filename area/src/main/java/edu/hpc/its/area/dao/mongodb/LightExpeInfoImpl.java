package edu.hpc.its.area.dao.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

import edu.hpc.its.area.core.StandardLight;
import edu.hpc.its.area.util.MongoUtil;

/**
 * 操作单次试验中信号灯数据
 * 
 * @timestamp Feb 29, 2016 7:33:09 PM
 * @author smallbug
 */
public class LightExpeInfoImpl implements LightExpeInfo {

	MongoUtil mongo = new MongoUtil();

	@Override
	public void insertLightInfo(String expId, StandardLight light) {
		if (light == null) {
			throw new IllegalArgumentException("light not null !");
		}
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("lightInfo");
		Document doc = new Document("lightId", light.getId())//
				.append("expId", expId)//
				.append("crossId", light.getStandardCross().getId())//
				.append("green", light.getGreen())//
				.append("red", light.getRed());
		col.insertOne(doc);
		mongo.closeClient();
	}

	@Override
	public void delLightInfo(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("lightInfo");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
	}

	@Override
	public List<String> selectLightInfo(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("lightInfo");
		List<String> infos = new ArrayList<>();
		for (Document cur : col.find(eq("expId", expId))) {
			infos.add(cur.toJson());
		}
		if (infos.size() == 0) {
			mongo.closeClient();
			return null;
		}
		mongo.closeClient();
		return infos;
	}

	@Override
	public List<Double> selectLightTimeByExpIds(String[] expIds) {
		if (expIds == null) {
			throw new IllegalArgumentException("expIds not null !");
		}
		List<Double> dd = new ArrayList<>();
		MongoCollection<Document> col = mongo.getDb().getCollection("lightInfo");
		for (String expId : expIds) {
			Document d = col.find(eq("expId", expId)).first();
			dd.add(d.getDouble("red"));
		}
		return dd;
	}

}
