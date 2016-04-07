package com.lifeservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeservice.dao.PositionMapper;
import com.lifeservice.dao.ServerMapper;
import com.lifeservice.dao.UserMapper;
import com.lifeservice.model.PositionInfo;
import com.lifeservice.model.SearchServer;
import com.lifeservice.model.Server;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.ServerService;
import com.lifeservice.utils.UtilMethods;
import com.mysql.jdbc.Util;

@Service("serverService")
public class ServerServiceImpl implements ServerService{
	
	@Autowired
	private ServerMapper ServerMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PositionMapper positionMapper;
	
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

	@Override
	public List<Server> searchServer(String KeyWord) {
		ArrayList<String> keyWordList = new ArrayList<String>();
		keyWordList = UtilMethods.fenci(KeyWord);
		keyWordList = UtilMethods.doSomething(keyWordList);
		String sql = UtilMethods.creatSql(keyWordList);
		return ServerMapper.searchServer(sql);
	}

	@Override
	/**
	 * 满足关键字的服务按由远及近排序
	 */
	public TreeSet<SearchServer> orderServerList(List<Server> serverList,float lon,float lat){
		TreeSet<SearchServer> set = new TreeSet<SearchServer>();
		UserInfo user=new UserInfo();
		float distance=0;
		serverList=updateServerPosition(serverList);
		for(int i=0;i<serverList.size();i++){
			user=userMapper.selectByUserId(Integer.parseInt(serverList.get(i).getUserId()));
			distance=(float) UtilMethods.getDistance(serverList.get(i).getJingDu(), serverList.get(i).getWeiDu(), lon, lat);
			distance = distance * 1000;
			SearchServer searchServer=new SearchServer(serverList.get(i),user,distance);
			//searchServer.setUpdateTime(UtilMethods.stringToDate(str));
			set.add(searchServer);
		}
		return set;
	}

	@Override
	public List<Server> updateServerPosition(List<Server> serverList) {
		PositionInfo positional = new PositionInfo();
		for (int i = 0; i < serverList.size(); i++) {
			System.out.println(serverList.get(i));
			if (serverList.get(i).getType().toString().equals("1")) {
				positional = positionMapper.findPositionByUserId(Integer.parseInt(serverList.get(i)
						.getUserId()));
				if(positional != null){
				serverList.get(i).setJingDu(positional.getJingDu());
				serverList.get(i).setWeiDu(positional.getWeiDu());
				}
			}
		}
		return serverList;
	}
	
	


}
