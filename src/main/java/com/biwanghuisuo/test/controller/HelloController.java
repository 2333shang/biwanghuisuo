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
	
	@RequestMapping("/localbushu")
	public String localbushu() {
		return SSHHelper.exec("62.234.30.21", "root", "Bwhs2020!", 8080, "ls");
	}
	
	@RequestMapping("/cmd")
	public String cmd() {
		System.out.println("start!");
		String result1 = SSHHelper.execCmd("cmd /c D:");
		String result2 = SSHHelper.execCmd("cmd /c cd D:\\project\\STSProject\\biwanghuisuo");
		String result3 = SSHHelper.execCmd("cmd /c git add .");
		System.out.println(result3);
		String result4 = SSHHelper.execCmd("cmd /c git commit -m \"java test git commit\"");
		System.out.println(result4);
		String result5 = SSHHelper.execCmd("cmd /c cd git push -u origin master");
		System.out.println(result5);
		
		return result3 + ":" + result4 + ":" + result5;
	}
}
