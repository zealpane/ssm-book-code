package com.gdatacloud.zz.ssm.biz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.gdatacloud.zz.ssm.annotation.Controller;
import com.gdatacloud.zz.ssm.annotation.RequestMapping;
import com.gdatacloud.zz.ssm.annotation.Autowired;
import com.gdatacloud.zz.ssm.biz.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping(value="/helloMvc")
	public void helloMvc(HttpServletResponse response, String name) {
		try {
			String echo = userServiceImpl.getHello(name);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write("从service过来的返回值： " + echo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
