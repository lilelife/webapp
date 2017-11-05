package com.lile.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lile.common.mybits.model.User;
import com.lile.dao.UserDao;
import com.lile.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired()
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

}
