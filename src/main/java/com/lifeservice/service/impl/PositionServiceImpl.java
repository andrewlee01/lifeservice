package com.lifeservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeservice.model.PositionInfo;
import com.lifeservice.service.PositionService;

@Service("positionService")
public class PositionServiceImpl implements PositionService{

	@Override
	public void savePosition(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isHasPositionInfo(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePosition(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PositionInfo> getPositionByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
