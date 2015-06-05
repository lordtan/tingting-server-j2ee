package com.mb.beans.momgo;

import com.mb.common.util.JsonUtil;

/**
 * 文件信息
 * @author lordtan
 * @date 2015年6月5日
 */
public class FileInfo {

	private String fileId; //文件id
	private String type;  //文件类型
	private String sound;  //声音信息json文件，对应的bean为Sound
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	@Override
	public String toString() {
		return JsonUtil.obj2json(this);
	}
}
