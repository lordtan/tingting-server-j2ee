package com.mb.common.persistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.mb.common.util.HttpHelper;
import com.mongodb.util.JSON;

/**
 * 百度云存储测试
 * 详情api请参见
 * http://developer.baidu.com/map/index.php?title=lbscloud/api/geodata
 * @author lordtan
 * @date 2015年5月5日
 */
public class BaiduLbscloudTest {
	
	/**
	 * 创建表，使用POST请求
	 * MusicGeo
	 */
	@Test
	public void creatGeoTable(){
		long begain = System.currentTimeMillis();
		
		String url = "http://api.map.baidu.com/geodata/v3/geotable/create";
		Map<String,String> params = new HashMap<String, String>();
		params.put("name", "MusicGeo");   //音乐地理位置
		params.put("geotype", "1");      //1：点；2：线；3：面。默认为1
		params.put("is_published", "1");
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		String rel = HttpHelper.sendPost(url, params);
		System.out.println(rel);
		
		long end = System.currentTimeMillis();
		System.out.println("共计耗时： "+(end-begain));
	}
	
	/**
	 * 删除
	 */
	@Test
	public void deleteGeoTable(){
		long begain = System.currentTimeMillis();
		
		String url = "http://api.map.baidu.com/geodata/v3/geotable/delete";
		Map<String,String> params = new HashMap<String, String>();
		params.put("id", "103250"); //表的id
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		String rel = HttpHelper.sendPost(url, params);
		System.out.println(rel);
		
		long end = System.currentTimeMillis();
		System.out.println(end-begain);
	}
	
	/**
	 * 列表,所有已创建的表
	 */
	@Test
	public void listGeoTable(){
		String url = "http://api.map.baidu.com/geodata/v3/geotable/list";
		Map<String,String> params = new HashMap<String, String>();
		params.put("name", "MusicGeo");   //音乐地理位置
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		String rel = HttpHelper.sendGet(url, params);
//		System.out.println(rel);
		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
		System.out.println(JSON.serialize(obj));
	}
	
	/**
	 * 创建额外的列
	 */
	@Test
	public void createGeoColumn(){
		String url = "http://api.map.baidu.com/geodata/v3/column/create";
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		params.put("geotable_id", "103244");   //表的id
		params.put("name", "持续时间");
		params.put("key", "title");
		params.put("type", "3"); //1： Int64, 2:double, 3,string，4，在线图片url
		params.put("max_length", "32");
		params.put("is_sortfilter_field", "0");
		params.put("is_search_field", "0");
		params.put("Is_index_field", "0");
		String rel = HttpHelper.sendPost(url, params);
//		System.out.println(rel);
//		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
//		System.out.println(JSON.serialize(obj));
		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
		System.out.println(JSON.serialize(obj));
	}
	
	@Test
	public void listGeoColumn(){
		String url = "http://api.map.baidu.com/geodata/v3/column/list";
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		params.put("geotable_id", "103244");   //表的id
		long begain = System.currentTimeMillis();
		/**
		 * 1000个请求耗时160秒，快3分钟了，也有可能是我不会用
		 * 看来这个接口还是放在Android端比较好哦
		 */
		
//		for(int i=0; i<1000; i++){
//			String rel = HttpHelper.sendGet(url, params);
//			System.out.println(i+rel);
//		}
		
		HttpClient client = HttpHelper.getHttpClient();
		
		PoolingHttpClientConnectionManager phccm ;
		
		String rel = HttpHelper.sendGet(url, params);
		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
		System.out.println(JSON.serialize(obj));
		
		long end = System.currentTimeMillis();
		System.out.println("共计耗时： "+(end-begain));
	}
	
	/**
	 * 自己服务器测试，1000个请求1秒左右，请求baidu要3分钟左右
	 * 之后的位置数据还是要存在自己服务器上比较好
	 * 看来性能的瓶颈在baidu服务器
	 */
	@Test
	public void getMyself(){
		long begain = System.currentTimeMillis();
		String url = "http://192.168.1.113:8080/TingTing/login";
		Map<String,String> params = new HashMap<String, String>();
		params.put("user", "111");
//		String rel = HttpHelper.sendGet(url, params);
		
		for(int i=0; i<1000; i++){
			String rel = HttpHelper.sendGet(url, params);
			System.out.println(i+rel);
	    }
//		System.out.println(rel);
//		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
//		System.out.println(JSON.serialize(obj));
		long end = System.currentTimeMillis();
		System.out.println("共计耗时： "+(end-begain));
	}
	
	@Test
	public void detailGeoColumn(){
		String url = "http://api.map.baidu.com/geodata/v3/column/detail";
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		params.put("geotable_id", "103244");   //表的id
		params.put("id", "132788");   //用户id 132788  文件id 132790
		long begain = System.currentTimeMillis();
		
		String rel = HttpHelper.sendGet(url, params);
		HashMap obj = (HashMap) JSON.parse(rel);  //这个工具可以把unicode转换成汉字
		System.out.println(JSON.serialize(obj));
		
		long end = System.currentTimeMillis();
		System.out.println("共计耗时： "+(end-begain));
	}
	
	/**
	 * 用户地理位置
	 */
	@Test
	public void creatPoi(){
		String url = "http://api.map.baidu.com/geodata/v3/column/detail";
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", "flT2ZGl34AVjgzk3Ts0nougX");
		params.put("geotable_id", "103244");   //表的id
	}

}
