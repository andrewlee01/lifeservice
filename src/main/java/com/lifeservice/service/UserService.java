package com.lifeservice.service;

import com.lifeservice.model.UserInfo;

public interface UserService {
//	public User getUserById(int id);
//	
//	public void saveUser(User user);
	/**
	 * 添加用户
	 * @param userInfo
	 */
	public int addUser(UserInfo userInfo);
	
	
	/**
	 * 通过用户名查找用户对象
	 * @param userName
	 * @return
	 */
	public UserInfo findUserByUsername(String userName);
	
	/**
	 * 通过userId查找用户对象
	 * @param userId
	 * @return
	 */
	public UserInfo findUserByUserId(int userId);
	
	/**
	 * 根据userId更新UserInfo
	 * @param userId
	 * @param key 
	 * 	字段名
	 * @param value
	 *  更新的值
	 */
	public String updateUser(int userId, String key, String value);
}
