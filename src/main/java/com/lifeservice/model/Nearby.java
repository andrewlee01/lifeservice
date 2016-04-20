package com.lifeservice.model;


public class Nearby {
	/**
	 * 用户Id
	 */
	private int userId;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 提供服务（服务名称）
	 */
	private String provide;
	/**
	 * 服务内容
	 */
	private String serverContent;
	/**
	 * 电话号码
	 */
	private String phoneNum;
	/**
	 * 相隔准确距离
	 */
	private double distance;
	/**
	 * 头像路径
	 */
	private String touXiangPath;
	/**
	 * 用户经度
	 */
	private float jingDu;
	/**
	 * 用户纬度
	 */
	private float weiDu;
	/**
	 * 更新时间
	 */
	//private String updateTime;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProvide() {
		return provide;
	}
	public void setProvide(String provide) {
		this.provide = provide;
	}
	public String getServerContent() {
		return serverContent;
	}
	public void setServerContent(String serverContent) {
		this.serverContent = serverContent;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getTouXiangPath() {
		return touXiangPath;
	}
	public void setTouXiangPath(String touXiangPath) {
		this.touXiangPath = touXiangPath;
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
	
	
}
