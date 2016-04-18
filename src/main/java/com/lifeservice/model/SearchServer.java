package com.lifeservice.model;




public class SearchServer extends Server implements Comparable<SearchServer>{
	private double Distance;
	private String userName;
	private String phone;
	
	public int compareTo(SearchServer o) {
//		if (this.Distance == o.Distance) {
//			return 0;
//		}
		return (this.Distance > o.Distance) ? 1 : -1;
	}
	
	public SearchServer(Server server,UserInfo user,double distance){
		this.setUserId(server.getUserId());
		this.setServerId(server.getServerId());
		this.setServerName(server.getServerName());
		this.setServerContent(server.getServerContent());
		this.setType(server.getType());
		this.setKeyWord(server.getKeyWord());
		this.setUpdateTime(server.getUpdateTime());
		this.setJingDu(server.getJingDu());
		this.setWeiDu(server.getWeiDu());
		this.setCity(server.getCity());
		this.setMemo(server.getMemo());
		this.setUserName(user.getUserName());
		this.setPhone(user.getPhone());
		this.Distance=distance;
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
