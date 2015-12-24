package com.lifeservice.dao;

import java.util.List;

import com.lifeservice.model.PositionInfo;


public interface PositionMapper {
	
	PositionInfo findPositionByUserId(int userId);
	
	void updatePositional(PositionInfo positionInfo);
	
	List<PositionInfo> getPositionByCity(String city);
}
