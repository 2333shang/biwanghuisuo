package com.biwanghuisuo.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String test() {
		System.out.println(111);
		return "Hello World! 2333";
	}
}
