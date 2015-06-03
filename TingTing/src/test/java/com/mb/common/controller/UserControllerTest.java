package com.mb.common.controller;

import javax.annotation.Resource;

import org.junit.Test;

import com.mb.beans.common.Message;
import com.mb.beans.sql.User;
import com.mb.common.BaseTest;
import com.mb.common.IServerRemote;
import com.mb.common.util.HttpHelper;
import com.mb.service.IUserService;
import com.mb.service.user.UserService;

/**
 * 用户登陆信息测试
 * @author lordtan
 * @date 2015年6月2日
 */
public class UserControllerTest extends BaseTest{

	
	@Resource(type=UserService.class)
	private IUserService userService;
	
	/**
	 * 测试登录
	 */
	@Test
	public void register(){
		User user = new User();
		user.setAccount("lordtan");
		user.setPassword("123456");
		Message mesg = userService.register(user.toString());
		System.out.println(mesg.toString());
	}
	
	/**
	 * 注册远程测试
	 */
	@Test
	public void registerRemote(){
		User user = new User();
		user.setAccount("lordtan");
		user.setPassword("88");
		
		String result = HttpHelper.sendPostData("http://localhost:8080/TingTing/register", user.toString());
		System.out.println(result);
	}
	
	/**
	 * 登陆测试
	 */
	@Test
	public void login(){
		User user = new User();
		user.setAccount("lordtan");
		user.setPassword("123456");
		Message mesg = userService.login(user.toString());
		System.out.println(mesg.toString());
	}
	
	/**
	 * 登录远程测试
	 */
	@Test
	public void loginRemote(){
		
	}
}
