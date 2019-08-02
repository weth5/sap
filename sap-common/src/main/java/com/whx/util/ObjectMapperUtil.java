package com.whx.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtil {
	private static ObjectMapper mapper =new ObjectMapper();
	//1:将对象转化为JSON
	public static String toJson(Object obj) {
		String result;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	public static <T> T toObject(String json,Class<T> target) {
		T t;
		try {
			t = mapper.readValue(json, target);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return t;
	}
}