package com.lifeservice.service;

import java.util.List;


import com.lifeservice.model.Server;

public interface ServerService {
	
	Server selectServerById(int serverId);
	
	void deleteServerByUserId(int userId);
	
	List<Server> findServerListByUserId(int userId);
	
	void updateServerStatus(int serverId, int status);
	
}
