package com.gk666.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtil {
	/**
	 * 向请求方打印信息
	 * @param response
	 * @param msg
	 */
	public static void responseOut(HttpServletResponse response,String msg){
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = null;
         try {
        	 out = response.getWriter();
             out.println(msg);                      
         }catch (Exception e) {
        	 e.printStackTrace();
         }finally {   
        	out.flush();
            out.close();
        }
	}
	
	/**
	 * 将字节流转换为字符串
	 * */ 
	public static String readStreamParameter(ServletInputStream in){
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader=null;
		try{
			//reader = new BufferedReader(new InputStreamReader(in,"ISO-8859-1"));
			reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
			String line=null;
			while((line = reader.readLine())!=null){
				buffer.append(line);
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=reader){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 执行HTTPClient请求   Url传参
	 * @param urlStr   请求地址
	 * @param ifCharset 是否设置字符集  0为不设置 大于0为设置   全英文不需要设置
	 * @return	请求结果
	 */
	public static String excuteGetClient(String urlStr,int ifCharset){
		String resultStr = null;
		HttpClient httpclient = new HttpClient();
		try {
			urlStr = URLEncoder.encode(urlStr, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		HttpMethod method = new GetMethod(urlStr);
		if(ifCharset >0){
			method.setRequestHeader("Content-type", "text/xml; charset=utf-8");
		}
		try {
			httpclient.executeMethod(method);
			resultStr = method.getResponseBodyAsString();
		} catch (Exception e) {
			resultStr = null;
			e.printStackTrace();
		} 
		return resultStr;
	}
	
	/**
	 * Post数据到对应的地址
	 * @param urlStr
	 * @param postStr
	 * @param charsetType 0不设置  1"Content-type", "text/xml; charset=utf-8"   2"Content-type", "application/json; charset=utf-8"
	 * @return
	 */
	public static String excutePostClient(String urlStr,String postStr,int charsetType){
		String resultStr = null;
		try {
			urlStr = URLEncoder.encode(urlStr, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PostMethod method = new PostMethod(urlStr);
		if(charsetType==1){
			method.setRequestHeader("Content-type", "text/xml; charset=utf-8"); //设置头
		}else if(charsetType == 2){
			method.setRequestHeader("Content-type", "application/json; encoding=utf-8"); //设置头
		}
		method.setRequestBody(postStr);				//设置内容
        HttpClient httpclient = new HttpClient();      		// 创建 HttpClient的实例
        try {
			int result = httpclient.executeMethod(method);//执行请求
			resultStr = method.getResponseBodyAsString();	// 返回内容
		} catch (Exception e) {
			e.printStackTrace();
		}  		
        method.releaseConnection();                                 // 释放连接	
		return resultStr;
	}
}
