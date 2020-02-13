package com.lile.service;

import dto.UserDto;
import dto.request.LoginRequest;
import org.springframework.stereotype.Service;

import com.lile.common.mybits.model.User;
import response.UserInfo;


public interface UserService {
	
	public User getUserById(int id);
	
	public boolean insertUser(User user);

	public String getToken(User user);

	public UserInfo createUser(UserDto userDto);

	public User findUserByphone(String phone);
}
