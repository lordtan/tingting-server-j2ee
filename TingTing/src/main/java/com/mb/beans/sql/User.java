package com.mb.beans.sql;


import com.mb.common.util.JsonUtil;

/**
 * 用户账户信息
 * 很贱，直接取名叫user还不行，非得叫t_user
 * postgresql数据库大小写敏感，
 * 建数据库的时候注意所有的字段都是小写，实体则无所谓
 * 终于搞定，大小写的问题困扰我很长时间
 * @author lordtan
 * @date 2015年5月25日
 */
@TableName("t_user")
public class User{

	private String id; //id
	private String name; //昵称
	private String account; //账户
	private String password; //密码
	private int age; //年龄
	private int sex; //性别
	private String phone; //电话
	private int isActive;  //是激活，1 激活 0禁用
	private long loginDate; //最后登录时间，格林尼治时间UT，到当前的毫秒数
	private long createdDate;  //创建时间，格林尼治时间UT，到当前的毫秒数
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public long getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(long loginDate) {
		this.loginDate = loginDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return JsonUtil.obj2json(this);
	}
}
