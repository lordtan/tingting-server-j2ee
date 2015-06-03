package com.mb.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * http请求帮助
 * @author lordtan
 * @date 2015年5月7日
 */
public class HttpHelper {

	private static HttpClient client;
	
	/**
	 * 创建HttpClient
	 * @return
	 */
	synchronized public static HttpClient getHttpClient(){
		if(null!=client){
			return client;
		}
		
		//这里可能要做些权限什么的过来，先预留
		HttpResponseInterceptor hi = new HttpResponseInterceptor(){
			public void process(HttpResponse response, HttpContext context)
					throws HttpException, IOException {
				//TODO
				
			}
		};
		
		client = HttpClientBuilder.create().addInterceptorFirst(hi).build();
		return client;
	}
	
	synchronized public static String sendPostData(String url, String data){
		String result = "";
		
        /**
         * 发送请求
         */
        try {
      		HttpPost request = new HttpPost(url);
        	request.setEntity(new StringEntity(data));
			HttpResponse response = getHttpClient().execute(request);
//			result = EntityUtils.toString(response.getEntity());
			result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 */
	synchronized public static String sendPost(String url, Map<String,String> params){
		String result = "";

		/**
		 * 转换参数
		 */
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
		
        /**
         * 发送请求
         */
        try {
        	URIBuilder builder = new URIBuilder(url);
      		builder.addParameters(nvps);
      		HttpPost request = new HttpPost(builder.build());
        	
			HttpResponse response = getHttpClient().execute(request);
//			result = EntityUtils.toString(response.getEntity());
			result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}  
		
		return result;
	}
	
	/**
	 * 发送get请求
	 * @param url
	 * @param params
	 * @return
	 */
	synchronized public static String sendGet(String url, Map<String,String> params){
		String result = "";
		
		/**
		 * 转换参数
		 */
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
       
        try {
        	//构建get请求参数
        	URIBuilder builder = new URIBuilder(url);
     		builder.addParameters(nvps);
     		HttpGet request = new HttpGet(builder.build());
     		//执行请求
			HttpResponse response = getHttpClient().execute(request);
			result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}  
        
		return result;
	}
}
