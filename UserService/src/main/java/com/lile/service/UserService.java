package com.lile.service;

import dto.UserDto;
import org.springframework.stereotype.Service;

import com.lile.common.mybits.model.User;
import response.UserInfo;


public interface UserService {
	
	public User getUserById(int id);
	
	public boolean insertUser(User user);


	public UserInfo createUser(UserDto userDto);
}
