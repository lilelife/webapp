package com.lile.dao.daoImpl;

import com.lile.common.mybits.model.User;
import com.lile.common.mybits.model.UserExample;
import com.lile.common.mybits.persistence.UserMapper;
import com.lile.dao.UserDao;
import dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import response.UserInfo;


@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(user)!=0;
	}

}
