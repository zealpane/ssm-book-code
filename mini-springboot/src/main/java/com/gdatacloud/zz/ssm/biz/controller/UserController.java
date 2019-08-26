package com.gdatacloud.zz.ssm.biz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.gdatacloud.zz.ssm.annotation.Controller;
import com.gdatacloud.zz.ssm.annotation.RequestMapping;
import com.gdatacloud.zz.ssm.annotation.Component;
import com.gdatacloud.zz.ssm.biz.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Component
	private UserService userService;
	
	@RequestMapping(value="/helloMvc")
	public void helloMvc(HttpServletResponse response, String name) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write("hello, " + name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
