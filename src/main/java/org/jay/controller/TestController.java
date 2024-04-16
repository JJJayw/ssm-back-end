package org.jay.controller;

import org.jay.entity.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/t", produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public User T(User user) {
		String name = user.getName();
		name = name + "-----";
		user.setName(name);
		return user;
	}
}
