package org.hpc.its.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, Object>> json2Map(String json) {
		Map<String, Map<String, Object>> maps = null;
		try {
			maps = objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return maps;
	}
}
