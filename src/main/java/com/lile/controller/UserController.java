package com.lile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.Mapping;
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
	
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@RequestMapping(value="/getUserByid",method=RequestMethod.GET)
	public User getUserByid(@RequestParam(value="id") @ApiParam(value = "用户Id", required = true) int id){
		
		return userService.getUserById(id);
	}
}
