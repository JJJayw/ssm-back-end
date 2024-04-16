package org.jay.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jay.entity.dto.LoginUser;
import org.jay.entity.po.User;
import org.jay.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class IndexController {
	@Resource
	private UserService userService;

	@PostMapping("login")
	public ResponseEntity<LoginUser> login(@RequestBody @Validated User user, BindingResult bindingResult) {
		List<ObjectError> allError = bindingResult.getAllErrors();
		allError.forEach(objectError -> log.error("登录时用户校验失败：{}", objectError.getDefaultMessage()));
		if (!allError.isEmpty()) {
			return ResponseEntity.status(500).build();
		}

		LoginUser loginUser = null;
		try {
			loginUser = userService.login(user.getUserName(), user.getPassword());
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(500).build();
		}
		return ResponseEntity.ok().body(loginUser);
	}


}
