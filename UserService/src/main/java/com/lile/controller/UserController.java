package com.lile.controller;

import com.alibaba.fastjson.JSONObject;
import com.lile.redis.RedisService;
import dto.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.lile.common.mybits.model.User;
import com.lile.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import response.UserInfo;
import utils.DynamicResponse;

import javax.annotation.Resource;

@Api(value = "/user", description = "用户")
@RequestMapping("user")
@RestController
@Slf4j
public class UserController {
	@Autowired()
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Resource
	private RedisService redisServiceImpl;

	private Log logger =  LogFactory.getLog("UserController");
	@ApiOperation(value="查找用户", notes="根据UserID获取用户")
	@RequestMapping(value="/getUserByid",method=RequestMethod.GET)
	@ResponseBody
	public DynamicResponse<User> getUserByid(@RequestParam(value="id",required=false,defaultValue="1") @ApiParam(value = "用户Id", required = true) int id){
		logger.info("获取对User象--"+id);

		DynamicResponse<User> of = DynamicResponse.of(() -> {
			User user = userService.getUserById(id);
			logger.info(JSONObject.toJSONString(user));
			return user;
		});

		return of;
	}

	@ApiOperation(value = "注册用户")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public DynamicResponse<UserInfo> register(@RequestBody UserDto userDto){
		
		return DynamicResponse.of(()->{
			return userService.createUser(userDto);
		});
	}


	@GetMapping("/getStr")
	public String testRedis(){

//		String str = redisServiceImpl.getStr("lile");
		String str =redisServiceImpl.get("lile");
		logger.info(str+"-----");
		return str;
	}

	@ApiOperation("测试插入")
	@GetMapping("/setname/{key}/{value}")
	public Boolean insertTest(@PathVariable String key,@PathVariable String value){
		Boolean result = redisServiceImpl.set("user:test"+key,value);

		return result;
	}
	
}
