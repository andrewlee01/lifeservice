package com.lifeservice.service;

import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.lifeservice.model.Server;

public interface ServerService {
	
	Server selectServerById(int serverId);
	
	void addServer(Server server);
	
	void deleteServerByUserId(int userId);
	
	void updateServer(Server server);
	
	List<Server> findServerListByUserId(int userId);
	
	List<Server> findServerListByUserIdAndStatus(int userId,int status);
	
	void updateServerStatus(int serverId, int status);
	
}
