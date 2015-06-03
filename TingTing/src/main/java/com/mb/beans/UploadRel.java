package com.mb.beans;

/**
 * 上传结果bean，这里指客户端上传到服务端后返回的数据
 * @author lordtan
 * @date 2014-10-12
 */
public class UploadRel {

	private boolean isSuccess;  //是否上传成功
	private String fileId; //文件id
	private long fileSize;      //文件大小
	private long time;  //上传时间，格林日志时间（毫秒）
	private String error;
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
