package com.mb.service;

import java.io.InputStream;

import com.mb.beans.common.Message;
import com.mb.beans.momgo.Search;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * 声音相关接口
 * @author lordtan
 * @date 2015年5月20日
 */
public interface ISoundService {

	/**
	 * 发布声音，存储声音相关信息，不存储实体文件
	 * @param sound 声音
	 * @return
	 */
	public Message publish(String sound);
	
	/**
	 * 取消发布，删除声音的相关信息以及实体文件
	 * @param sound
	 * @return
	 */
	public Message cancel(String sound);
	
	/**
	 * 存储声音，存储实体文件
	 * @param content 声音内容
	 * @param fileId 文件id
	 * @param type 文件类型
	 * @return
	 */
	public GridFSFile store(InputStream content, String fileId, String type);
	
	/**
	 * 收听声音
	 * @param soundId 声音文件id
	 * @return
	 */
	public GridFSDBFile listen(String fileId);
	
	/**
	 * 搜索声音
	 * @param x 用户所在地图x位置
	 * @param y 用户所在地图y位置
	 * @param radius 搜索半径
	 * @return
	 */
	public Message search(double centerX, double centerY, double radius);
	
	/**
	 * 搜索
	 * @param search @see com.mb.beans.momgo.Search
	 * @return
	 */
	public Message search(String search);
	
	/**
	 * 顶
	 * @param sound
	 * @return
	 */
	public Message great(String id);
	
	/**
	 * 踩
	 * @param sound
	 * @return
	 */
	public Message degrade(String id);
}
