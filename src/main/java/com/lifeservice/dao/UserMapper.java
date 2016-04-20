package com.lifeservice.dao;


import org.apache.ibatis.annotations.Param;

import com.lifeservice.model.UserInfo;

public interface UserMapper {
	
	int insert(UserInfo userInfo);
	
    UserInfo selectByUserName(String userName);
    
    UserInfo selectByUserId(int userId);
    
    void updateUser(@Param("userId")int userId,@Param("key")String key,@Param("value")String value);
    
    UserInfo selectByPhone(String phone);
}