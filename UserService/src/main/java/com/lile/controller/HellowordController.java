package com.lile.controller;

import com.lile.handler.UserLoginToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordController {

	@UserLoginToken
	@GetMapping("/hello")
	public String index(){
		return "hellow rod";
	}
	
}
