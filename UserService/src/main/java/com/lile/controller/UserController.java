package com.lile.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lile.redis.RedisService;
import dto.UserDto;
import dto.request.LoginRequest;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.lile.common.mybits.model.User;
import com.lile.service.UserService;

import response.UserInfo;
import utils.Checker;
import utils.DynamicResponse;
import utils.ErrorCode;
import utils.PasswordUtil;

import javax.annotation.Resource;

@Api(value = "/user", description = "用户")
@RequestMapping("users")
@RestController
@Slf4j
public class UserController {
	@Resource
	private UserService userService;

	@Resource
	private RedisService redisServiceImpl;

	private Log logger =  LogFactory.getLog("UserController");

	@ApiOperation(value="查找用户", notes="根据UserID获取用户")
	@RequestMapping(value="/id",method=RequestMethod.GET)
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
	@PutMapping
	@ResponseBody
	public DynamicResponse<UserInfo> register(@RequestBody UserDto userDto){
		
		return DynamicResponse.of(()->{
			return userService.createUser(userDto);
		});
	}

	@ApiOperation(value = "用户登录")
	@ApiResponses(
			{@ApiResponse(code=200,message="成功",response = DynamicResponse.class)}
	)
	@PostMapping
	@CrossOrigin
	@ResponseBody
	public DynamicResponse<User>  login (@RequestBody LoginRequest loginRequest){

		return DynamicResponse.of((()->{
			User user = userService.findUserByphone(loginRequest.getPhone());
			Checker.checkNoNull(user,ErrorCode.NOT_USER.throwSupplier("用户不存在"));
			Checker.checkTrue(PasswordUtil.sha256(user.getSalt(),loginRequest.getPwd()).equals(user.getPassword()),
					ErrorCode.LOGIN_FAILURE.throwSupplier("密码不正确"));
			// todo 处理token 封装res
			String token = userService.getToken(user);

			return  user;
		}));

	}



	@ApiOperation("测试插入")
	@GetMapping("/setname/{key}/{value}")
	public Boolean insertTest(@PathVariable String key,@PathVariable String value){
		Boolean result = redisServiceImpl.set("user:test"+key,value);

		return result;
	}
	
}
