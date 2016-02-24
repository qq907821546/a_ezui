package com.gk666.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;


public class WriteUtil {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public WriteUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	/*public void writeAjaxMsg(JsonData jsonData) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			pw = response.getWriter();
			String val = JSONObject.fromObject(jsonData,getJsonConfig()).toString();
			pw.write(val);
			request.setAttribute("my_responseEntity", val);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}*/
	public void writeAjaxMsg(List list) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			pw = response.getWriter();
			String val = JSONArray.fromObject(list,getJsonConfig()).toString();
			pw.write(val);
			request.setAttribute("my_responseEntity", val);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	/*public void writeAjaxMsg(UserVo vo) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			pw = response.getWriter();
			String val = JSONObject.fromObject(vo,getJsonConfig()).toString();
			pw.write(val);
			request.setAttribute("my_responseEntity", val);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}*/
	public void writeEasyUIData(Object obj,int totalCount) {
		PrintWriter pw = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", obj);
			map.put("total", totalCount);
			JsonConfig jsonConfig = getJsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					return value == null ? "" : sd.format(value);
				}
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return null;
				}
			});
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			pw = response.getWriter();
			String val = JSONObject.fromObject(map,jsonConfig).toString();
			pw.write(val);
			request.setAttribute("my_responseEntity", val);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	JsonConfig getJsonConfig() {
		JsonConfig jc = new JsonConfig();
		// 实现属性过滤器接口并重写apply()方法
		PropertyFilter pf = new PropertyFilter() {
			// 返回true则跳过此属性，返回false则正常转换
			public boolean apply(Object source, String name, Object value) {
				if (value == null || String.valueOf(value).equals("")) {
					return true;
				}
				return false;
			}
		};
		// 将过滤器放入json-config中
		jc.setJsonPropertyFilter(pf);
		return jc;
	}
	

}
