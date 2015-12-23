package com.lifeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeservice.dao.UserMapper;
import com.lifeservice.model.User;
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

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public void saveUser(User user) {
		userMapper.insert(user);
//		抛出运行时异常，sql会自动rollback
//		throw new RuntimeException();
	}

}
