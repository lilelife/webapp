package com.lile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordController {
	
	@RequestMapping("/hello")
	public String index(){
		return "hellow rod";
	}
	
}
