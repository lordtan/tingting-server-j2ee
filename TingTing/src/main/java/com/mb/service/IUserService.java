package com.mb.service;

import com.mb.beans.common.Message;

/**
 * 用户接口，用户基本信息维护
 * @author lordtan
 * @date 2015年5月20日
 */
public interface IUserService {

	/**
	 * 登陆
	 * @param user 用户信息
	 * @return
	 */
	public Message login(String user);
	
	/**
	 * 注册
	 * @param user 用户信息
	 * @return
	 */
	public Message register(String user);
	
	/**
	 * 修改用户信息
	 * @param user 修改后的用户信息
	 * @return
	 */
	public String modify(String property, String value);
}
