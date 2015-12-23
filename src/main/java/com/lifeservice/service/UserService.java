package com.lifeservice.service;

import com.lifeservice.model.User;

public interface UserService {
	public User getUserById(int id);
	
	public void saveUser(User user);
}
