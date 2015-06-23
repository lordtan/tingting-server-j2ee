package com.mb.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mb.beans.momgo.Sound;
import com.mb.beans.sql.User;
import com.mb.common.IServerRemote;
import com.mb.common.util.HttpHelper;
import com.mb.common.util.JsonUtil;

public class RemoteTest {

	public static void main(String[] args) {
		User user = new User();
		user.setAccount("admin");
		user.setPassword("123456");
		
		System.out.println(IServerRemote.DEFAULT_URL+"/user/login");
//		String result = HttpHelper.sendPostData("http://localhost:8080/TingTing/user/register",
		String result = HttpHelper.sendPostData(IServerRemote.REMOTE_URL+"/user/login", user.toString());
		System.out.println(result);
//		String result = HttpHelper.sendPostData(IServerRemote.DEFAULT_URL+"/sound/store", "sdasd");

		/*	Map<String,String> params = new HashMap<String, String>();
		params.put("fileId", "123");
		params.put("type", "mp3");
		String result = HttpHelper.sendPost(IServerRemote.DEFAULT_URL+"/sound/store", params);
		System.out.println(result);
*/
		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("account", "lordtan");
//		params.put("password", "1234");
//		System.out.println(params.toString());
		
/*		Sound sod1 = new Sound();
		sod1.setFileId("123");
		Sound sod2 = new Sound();
		sod2.setFileId("1234");
		List<Sound> sounds = new ArrayList<Sound>();
		sounds.add(sod1);
		sounds.add(sod2);
		
		System.out.println(JsonUtil.obj2json(sounds));*/
	}

}
