package com.lifeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeservice.dao.PositionMapper;
import com.lifeservice.model.PositionInfo;
import com.lifeservice.model.Server;
import com.lifeservice.service.PositionService;

@Service("positionService")
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionMapper positionMapper;
	
	@Override
	public void savePosition(PositionInfo positionInfo) {
		positionMapper.savePosition(positionInfo);
	}

	@Override
	public PositionInfo getPositionInfoByUserId(int userId) {
		return positionMapper.findPositionByUserId(userId);
	}

	@Override
	public void updatePosition(PositionInfo positionInfo) {
		positionMapper.updatePositional(positionInfo);
	}

	@Override
	public List<PositionInfo> getPositionByCity(String city) {
		return positionMapper.getPositionByCity(city);
	}

	@Override
	public List<Server> updateServerPosition(List<Server> serverList) {
		return serverList;
	}

}
