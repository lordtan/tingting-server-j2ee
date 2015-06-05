package com.mb.common;

/**
 * 远程服务器地址
 * @author lordtan
 * @date 2015年6月3日
 */
public interface IServerRemote {

//	public static String IP = "120.25.217.252"; //服务器IP地址
	public static String IP = "192.168.1.103"; //服务器IP地址
	public static String PORT = "8080";  //端口
	public static String ROOT = "/TingTing";
	public static String DEFAULT_URL = "http://"+IP+":"+PORT+ROOT;
	
}
