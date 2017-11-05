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

@RequestMapping("User")
@RestController
public class UserController {
	@Autowired()
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value="/getUserByid",method=RequestMethod.GET)
	public User getUserByid(@RequestParam(value="id") int id){
		
		return userService.getUserById(id);
	}
}
