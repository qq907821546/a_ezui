package com.gk666.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	/**
	 * 解读json串
	 * @param jsonStr
	 * @param ifCharset 是否设置字符集  0为不设置 大于0为设置   全英文不需要设置
	 * @return	
	 * 读取Map中值  例：String accessToken = map.get("access_token").toString();
	 */
	public static Map readJson(String jsonStr,int ifCharset){
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			if(ifCharset>0){
				jsonStr = new String(jsonStr.getBytes("ISO-8859-1"),"utf-8");
			}
			map = mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			map = null;
			e.printStackTrace();
		}
		return map;
	}
	
	
	public static String getJson(Object object){
		if(object == null)
			return null;
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}
	
	public static String getJsonFromArray(Object object){
		if(object == null)
			return null;
		JSONArray json = JSONArray.fromObject(object);
		return json.toString();
	}
	
	/**
	 * 向前台抛出json数据的时候可以用此方法  
	 * @param object	Array数组  List
	 * @return
	 */
	public static String getJsonFormatDateFromArray(Object object){
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		
		return jsonArray.toString();
	}
	
	/**
	 * 向前台抛出json数据的时候可以使用此方法
	 * @param object	单个实体类	Map HashMap Entity
	 * @return
	 */
	public static String getJsonFormatDateFromObject(Object object,int totalCount){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", object);
		map.put("total", totalCount);
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		
		return jsonObject.toString();
	}
	
	/**
	 * 解读miniui传入后台的实体类list格式的json串
	 * @param json
	 * @param entityClass
	 * @return
	 */
	public static List readJsonFormatDateToListByEntityClass(String json,Class entityClass){
		
		if(StringUtil.isNullOrEmpty(json))
			return null;
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		
		JSONArray jsonArray= JSONArray.fromObject(json,jsonConfig);
		return JSONArray.toList(jsonArray, entityClass);
	}
	
	public static Object readJsonToObjectByEntityClass(String json,Class entityClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return JSONObject.toBean(jsonObject,entityClass);
	}
	
	
}
