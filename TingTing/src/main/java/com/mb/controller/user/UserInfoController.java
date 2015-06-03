package com.mb.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.beans.common.Message;
import com.mb.service.IUserService;
import com.mb.service.user.UserService;

/**
 * 用户基本信息，账号，密码等
 * 以及注册，登陆
 * @author lordtan
 * @date 2015年5月21日
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Resource(type=UserService.class)
	private IUserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody Message login(@RequestBody String user){
		return userService.login(user);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody Message register(@RequestBody String user){
		return userService.register(user);
	}
}
