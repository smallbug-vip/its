/**
 * 
 */
package edu.hpc.its.area.dao.mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

import edu.hpc.its.area.core.StandardArea;
import edu.hpc.its.area.exception.MongoException;
import edu.hpc.its.area.util.MongoUtil;

/**
 * 记录实验数据
 * 
 * @timestamp Feb 28, 2016 12:33:10 PM
 * @author smallbug
 */
public class AreaExpeInfoImpl implements AreaExpeInfo {

	MongoUtil mongo = new MongoUtil();

	public void createExp(String expId, StandardArea area) {
		if (area == null) {
			throw new IllegalArgumentException("area not null !");
		}
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		String s = selectExpByExpId(expId);
		if (s != null) {
			throw new MongoException("expId already exist! ");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("experiment");
		Document doc = new Document("expId", expId)//
				.append("areaId", area.getId())//
				.append("areaCrossNum", area.getCrossNum())//
				.append("areaLength", area.getLength())//
				.append("areaWidth", area.getWidth())//
				.append("areaLightNum", area.getLightNum())//
				.append("areaIP", area.getIp())//
				.append("areaName", area.getName());
		col.insertOne(doc);
		mongo.closeClient();
	}

	public String selectExpByExpId(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("experiment");
		for (Document cur : col.find(eq("expId", expId))) {
			String s = cur.toJson();
			mongo.closeClient();
			return s;
		}
		mongo.closeClient();
		return null;
	}

	public List<String> selectExpByAreaId(Long areaId) {
		
		MongoCollection<Document> col = mongo.getDb().getCollection("experiment");
		List<String> infos = new ArrayList<>();
		for (Document cur : col.find(eq("areaId", areaId))) {
			infos.add(cur.toJson());
		}
		if (infos.size() == 0) {
			mongo.closeClient();
			return null;
		}
		mongo.closeClient();
		return infos;
	}

	public List<String> selectAll() {
		MongoCollection<Document> col = mongo.getDb().getCollection("experiment");
		List<String> infos = new ArrayList<>();
		for (Document cur : col.find(exists("expId")).sort(ascending("expId"))) {
			infos.add(cur.toJson());
		}
		if (infos.size() == 0) {
			mongo.closeClient();
			return null;// 如果size=0返回null
		}
		mongo.closeClient();
		return infos;
	}

	@Override
	public void delExpByExpId(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("experiment");
		col.deleteOne(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("carInfo");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("roadTime");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("appearTime");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("lightInfo");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
	}

	@Override
	public long selectCarNum(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
		return col.count(eq("expId", expId));
	}
}
