package edu.hpc.its.area.dao.mongodb;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import edu.hpc.its.area.core.StandardCar;
import edu.hpc.its.area.util.MongoUtil;

/**
 * 操作单次试验中车量数据
 * 
 * @timestamp Feb 29, 2016 6:57:57 PM
 * @author smallbug
 */
public class CarExpeInfoImpl implements CarExpeInfo {

	MongoUtil mongo = new MongoUtil();

	@Override
	public void insertAppearTime(String expId, Long carId, Long time) {
		if (time == null || time <= 0) {
			throw new IllegalArgumentException("time error !");
		}
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("appearTime");
		Document doc = new Document("carId", carId)//
				.append("expId", expId)//
				.append("time", time);
		col.insertOne(doc);
		mongo.closeClient();
	}

	@Override
	public void insertRoadTime(String expId, Long roadId, Long carId, Long time) {
		if (time == null || time <= 0) {
			throw new IllegalArgumentException("time error !");
		}
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("roadTime");
		Document doc = new Document("carId", carId)//
				.append("expId", expId)//
				.append("roadId", roadId)//
				.append("time", time);
		col.insertOne(doc);
		mongo.closeClient();
	}

	@Override
	public void insertCarInfo(String expId, StandardCar car) {
		if (car == null) {
			throw new IllegalArgumentException("car not null !");
		}
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
		Document doc = new Document("carId", car.getId())//
				.append("expId", expId)//
				.append("speed", car.getSpeed())//
				.append("route", car.getRouteString());
		col.insertOne(doc);
		mongo.closeClient();

	}

	@Override
	public void delOneCarInfo(String expId, Long carId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
		col.deleteOne(and(eq("expId", expId), eq("carId", carId)));
		mongo.closeClient();
	}

	@Override
	public void delExpCarInfo(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("roadTime");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
		col = mongo.getDb().getCollection("appearTime");
		col.deleteMany(eq("expId", expId));
		mongo.closeClient();
	}

	@Override
	public List<String> selectCarRoadTime(String expId, Long roadId, Long carId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("roadTime");
		List<String> infos = new ArrayList<>();
		for (Document cur : col.find(and(eq("expId", expId), eq("carId", carId), eq("roadId", roadId)))) {
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
	public String selectCarAppearTime(String expId, Long carId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("appearTime");
		for (Document cur : col.find(and(eq("expId", expId), eq("carId", carId)))) {
			mongo.closeClient();
			return cur.toJson();
		}
		mongo.closeClient();
		return null;
	}

	@Override
	public String selectCarInfo(String expId, Long carId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
		for (Document cur : col.find(and(eq("expId", expId), eq("carId", carId)))) {
			mongo.closeClient();
			return cur.toJson();
		}
		mongo.closeClient();
		return null;
	}

	@Override
	public List<String> selectExpCar(String expId) {
		if (StringUtils.isBlank(expId)) {
			throw new IllegalArgumentException("expId not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("carInfo");
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
	public List<Double> getAvgTime(String[] expIds) {
		if (expIds == null) {
			throw new IllegalArgumentException("expIds not null !");
		}
		MongoCollection<Document> col = mongo.getDb().getCollection("appearTime");
		List<Double> avgTime = new ArrayList<>();
		for (String expId : expIds) {
			FindIterable<Document> it = col.find(eq("expId", expId))//
					.projection(and(include("time"), excludeId()));
			Long i = 0L;
			Long sum = 0L;
			for (Document doc : it) {
				Long d = doc.get("time", Long.class);
				sum += d;
				i++;
			}
			avgTime.add((double) (sum / i / 1000));
		}

		return avgTime;
	}

}
