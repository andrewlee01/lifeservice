package com.lifeservice.service;

import java.util.List;
import java.util.TreeSet;

import com.lifeservice.model.SearchServer;
import com.lifeservice.model.Server;

public interface ServerService {
	
	Server selectServerById(int serverId);
	
	void addServer(Server server);
	
	void deleteServerByUserId(int userId);
	
	void updateServer(Server server);
	
	List<Server> findServerListByUserId(int userId,int type);
	
	List<Server> findServerListByUserIdAndStatus(int userId,int status);
	
	void updateServerStatus(int serverId, int status);
	
	void deleteServer(int serverId);
	
	List<Server> searchServer(String keyWord);
	
	public TreeSet<SearchServer> orderServerList(List<Server> serverList,float lon,float lat);
	
	public List<Server> updateServerPosition(List<Server> serverList);
	
}
