package com.lifeservice.service;

import java.util.List;

import com.lifeservice.model.PositionInfo;
import com.lifeservice.model.Server;



public interface PositionService {
	
	void savePosition(PositionInfo positionInfo);
	
	boolean isHasPositionInfo(int userId);
	
	void updatePosition(PositionInfo positionInfo);
	
	List<PositionInfo> getPositionByCity(String city);
	
	List<Server> updateServerPosition(List<Server> serverList);
	

}
