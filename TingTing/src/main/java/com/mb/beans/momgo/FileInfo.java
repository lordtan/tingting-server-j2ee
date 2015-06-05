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
	
	@Override
	public String toString() {
		return JsonUtil.obj2json(this);
	}
}
