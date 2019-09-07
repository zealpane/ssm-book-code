package com.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/pageIndex")
	public String pageIndex() {
		return "pageIndex";
	}
}