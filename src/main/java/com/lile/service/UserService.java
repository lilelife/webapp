package com.lile.service;

import org.springframework.stereotype.Service;

import com.lile.common.mybits.model.User;


public interface UserService {
	
	public User getUserById(int id);
	
	public boolean insertUser(User user);
}
