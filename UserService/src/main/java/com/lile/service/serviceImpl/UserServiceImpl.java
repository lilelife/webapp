package com.lile.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.lile.common.mybits.model.UserExample;
import com.lile.common.mybits.persistence.UserMapper;
import com.lile.service.UserService;
import dto.UserDto;
import dto.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lile.common.mybits.model.User;
import com.lile.dao.UserDao;
import response.UserInfo;
import utils.PasswordUtil;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired()
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(int id) {

		User userById = userDao.findUserById(id);
		logger.info(JSONObject.toJSONString(userById));
		return userById;
	}

	@Override
	public boolean insertUser(User user) {

		return userDao.insertUser(user);
	}

	@Override
	public UserInfo createUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto,user);
		String salt = PasswordUtil.getSalt();
		String pwd256 = PasswordUtil.sha256(salt,userDto.getPwd());
		user.setPassword(pwd256);
		user.setSalt(salt);
		user.setCreateTime(new Date());
		UserInfo userInfo = new UserInfo();
		
		userDao.insertUser(user);
		BeanUtils.copyProperties(user,userInfo);
		int id = user.getId();
		userInfo.setId(id);
		log.info("create new user:" +JSONObject.toJSONString(userInfo));
		return userInfo;
	}

	@Override
	public User findUserByphone(String phone) {
		UserExample userExample = new UserExample();
		userExample.or().andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);

		if(users.isEmpty()){
			return null;
		}
		return users.get(0);
	}
}
