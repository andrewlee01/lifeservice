package com.lifeservice.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lifeservice.model.Server;

public interface ServerMapper {
	
	/**
	 * 添加服务
	 * @param server
	 */
	void addServer(Server server);
	
	/**
	 * 根据服务ID获取服务对象
	 * @param serverId
	 * @return
	 */
	Server selectById(int serverId);
	
	/**
	 * 根据UserId删除服务
	 * @param userId
	 */
	void deleteServerByUserId(int userId);
	
	/**
	 * 根据UserId获取用户所有服务
	 * @param userId
	 * @return
	 */
	List<Server> findServerListByUserId(int userId);
	
	/**
	 * 根据UserId和服务状态获取服务
	 * @param userId
	 * @param status
	 * @return
	 */
	List<Server> findServerListByUserIdAndStatus(int userId,@Param("memo")int status);
	
	/**
	 * 根据服务ID更新服务状态
	 * @param serverId
	 * @param status
	 * 	 0 无效 ，1 有效
	 */
	void updateServerStatus(int serverId, @Param("memo")int status);
	
	/**
	 * 更新服务内容
	 * @param server
	 */
	void updateServer(Server server);
	
	/**
	 * 根据服务Id删除服务
	 * @param serverId
	 */
	void deleteServer(int serverId);
	
	/**
	 * 根据关键字搜索服务列表
	 * @param keyWordList
	 * @return
	 */
	List<Server> searchServer(@Param("sql")String sql);
	

}
