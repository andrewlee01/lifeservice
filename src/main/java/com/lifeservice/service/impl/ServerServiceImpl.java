package com.lifeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeservice.dao.ServerMapper;
import com.lifeservice.model.Server;
import com.lifeservice.service.ServerService;

@Service("serverService")
public class ServerServiceImpl implements ServerService{
	
	@Autowired
	private ServerMapper ServerMapper;
	
	@Override
	public Server selectServerById(int serverId) {
		return ServerMapper.selectById(serverId);
	}

	@Override
	public void addServer(Server server) {
		ServerMapper.addServer(server);
	}
	
	@Override
	public void deleteServerByUserId(int userId) {
		ServerMapper.deleteServerByUserId(userId);
	}


	@Override
	public void updateServer(Server server) {
		ServerMapper.updateServer(server);
	}
	
	@Override
	public List<Server> findServerListByUserId(int userId) {
		return ServerMapper.findServerListByUserId(userId);
	}

	@Override
	public List<Server> findServerListByUserIdAndStatus(int userId, int status) {
		return ServerMapper.findServerListByUserIdAndStatus(userId, status);
	}
	
	@Override
	public void updateServerStatus(int serverId, int status) {
		ServerMapper.updateServerStatus(serverId, status);
	}

	@Override
	public void deleteServer(int serverId) {
		ServerMapper.deleteServer(serverId);
	}



}
