package com.lile.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Resource
	private UserDao userDao;
	@Resource
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

	// jwt获取token
	@Override
	public  String getToken(User user){
		return JWT.create().withAudience(user.getId()+"")
				.sign(Algorithm.HMAC256("lile"));  // 用lile加密，也可以将密钥保存在配置文件中
	}

	@Override
	public UserInfo createUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto,user);
		String salt = PasswordUtil.getSalt();
		String pwd256 = PasswordUtil.sha256(salt,userDto.getPwd());  //加盐生成密码
		user.setPassword(pwd256);
		user.setSalt(salt);
		user.setCreateTime(LocalDateTime.now());
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
