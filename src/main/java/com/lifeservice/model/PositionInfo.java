package com.lifeservice.model;

import java.util.Date;

public class PositionInfo {
	
	private int positionId;
	private int userId;
	private float jingDu;
	private float weiDu;
	private Date updateTime;
	private String province;
	private String city;
	private String memo;
	
	
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getJingDu() {
		return jingDu;
	}
	public void setJingDu(float jingDu) {
		this.jingDu = jingDu;
	}
	public float getWeiDu() {
		return weiDu;
	}
	public void setWeiDu(float weiDu) {
		this.weiDu = weiDu;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
