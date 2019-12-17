package com.lile.dao;


import com.lile.common.mybits.model.User;
import dto.UserDto;
import response.UserInfo;

public interface UserDao {
	
	 User findUserById(int id);
	
	 boolean insertUser(User user);

}
