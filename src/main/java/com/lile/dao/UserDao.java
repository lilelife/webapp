package com.lile.dao;

import com.lile.common.mybits.model.User;

public interface UserDao {
	
	public User findUserById (int id);
	
	public boolean insertUser(User user);
}
