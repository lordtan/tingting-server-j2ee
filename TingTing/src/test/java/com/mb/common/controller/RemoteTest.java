package com.mb.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mb.beans.sql.User;
import com.mb.common.IServerRemote;
import com.mb.common.util.HttpHelper;

public class RemoteTest {

	public static void main(String[] args) {
		User user = new User();
		user.setAccount("lordtan");
		user.setPassword("123456");
		
		System.out.println(IServerRemote.DEFAULT_URL+"/user/login");
//		String result = HttpHelper.sendPostData("http://localhost:8080/TingTing/user/register",
		String result = HttpHelper.sendPostData(IServerRemote.DEFAULT_URL+"/user/login", user.toString());
		System.out.println(result);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", "lordtan");
		params.put("password", "1234");
		System.out.println(params.toString());
	}

}
