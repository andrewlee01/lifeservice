package com.lifeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeservice.dao.UserMapper;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int addUser(UserInfo userInfo) {
		return userMapper.insert(userInfo);
	}

	@Override
	public UserInfo findUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo findUserByUserId(int userId) {
		
		return userMapper.selectByUserId(userId);
	}
	
	


}