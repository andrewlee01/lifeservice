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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteServerByUserId(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Server> findServerListByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateServerStatus(int serverId, int status) {
		// TODO Auto-generated method stub
		
	}

}
