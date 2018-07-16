package com.lile.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lile.common.mybits.model.User;
import com.lile.common.mybits.persistence.UserMapper;
import com.lile.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "/user", description = "用户")
@RequestMapping("user")
@RestController
public class UserController {
	@Autowired()
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	private Log logger =  LogFactory.getLog("UserController");
	@ApiOperation(value="查找用户", notes="根据UserID获取用户")
	@RequestMapping(value="/getUserByid",method=RequestMethod.GET)
	public User getUserByid(@RequestParam(value="id",required=false,defaultValue="1") @ApiParam(value = "用户Id", required = true) int id){
		logger.info("获取对User象--"+id);
		
		return userService.getUserById(id);
	}
	
	@RequestMapping(value="/insertUser",method=RequestMethod.POST)
	public boolean insertUer(@RequestBody User user){
		
		
		return userService.insertUser(user);
	}
	
	
}
