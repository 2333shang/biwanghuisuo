package com.biwanghuisuo.test.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String test() {
		System.out.println(111);
		return "Hello World! 2333";
	}

	@RequestMapping("/bushu")
	public String bushu() {
		String result = null;
		try {
			String shpath = "/biwanghuisuo/bushu.sh";
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
