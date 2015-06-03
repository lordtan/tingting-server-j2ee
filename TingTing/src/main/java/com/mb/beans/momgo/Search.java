package com.mb.beans.momgo;

import com.mb.common.util.JsonUtil;

/**
 * 声音搜索
 * @author lordtan
 * @date 2015年6月1日
 */
public class Search {
	
	private double centerX;  //中心x坐标
	private double centerY;  //中心y坐标
	private double radius;   //半径
	public double getCenterX() {
		return centerX;
	}
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	public double getCenterY() {
		return centerY;
	}
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return JsonUtil.obj2json(this);
	}
}
