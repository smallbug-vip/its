package edu.hpc.its.area.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 获取Mongodb数据库
 * 
 * @timestamp Feb 28, 2016 12:27:55 PM
 * @author smallbug
 */
public class MongoUtil {

	private String url;
	private Integer port;
	private String databaseName;
	private MongoDatabase db = null;
	private MongoClient client;

	public MongoUtil() {
		Properties prop = new Properties();
		InputStream in = MongoUtil.class.getClassLoader().getResourceAsStream("mongo.properties");
		try {
			prop.load(in);
			url = prop.getProperty("url");
			port = new Integer(prop.getProperty("port"));
			databaseName = prop.getProperty("databaseName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库
	 * 
	 * @timestamp Feb 28, 2016 4:12:01 PM
	 * @return
	 */
	public MongoDatabase getDb() {
		client = new MongoClient(url, port);
		db = client.getDatabase(databaseName);
		return db;
	}

	/**
	 * 关闭连接
	 * 
	 * @timestamp Feb 28, 2016 4:11:54 PM
	 */
	public void closeClient() {
		if (client != null) {
			client.close();
			client = null;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> json2Map(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> maps = null;
		try {
			maps = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maps;
	}
}
