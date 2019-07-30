package com.gdatacloud.zz.ssm.biz.service.impl;

import com.gdatacloud.zz.ssm.annotation.Service;
import com.gdatacloud.zz.ssm.biz.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String getHello(String name) {
		String ret = "你好，" + name;
		return ret;
	}

}
