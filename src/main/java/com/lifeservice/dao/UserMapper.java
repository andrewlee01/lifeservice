package com.lifeservice.dao;


import com.lifeservice.model.UserInfo;

public interface UserMapper {
//    int deleteByPrimaryKey(Integer id);
//
	int insert(UserInfo userInfo);
//
//    int insertSelective(User record);
//
    UserInfo selectByUserName(String userName);
    
    UserInfo selectByUserId(int userId);
}