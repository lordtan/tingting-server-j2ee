package com.mb.common.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * json攻击
 * json转对象，对象转json
 * 这里再封装一遍主要是为了统一管理，和写起来方便，不用再抛异常了
 * @author lordtan
 * @date 2015年5月21日
 */
public class JsonUtil {
	private static ObjectMapper objectMapper = null;
	
	public static ObjectMapper getObjectMapper(){
		if(null==objectMapper){
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}
	
	/**
	 * json字符串转换为对象
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T json2obj(String json, Class<T> type){
		try {
			return (T) getObjectMapper().readValue(json, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转换为json
	 * @param obj
	 * @return
	 */
	public static String obj2json(Object obj){
		 try {
			return getObjectMapper().writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
