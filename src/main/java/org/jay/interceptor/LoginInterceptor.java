package org.jay.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jay.constant.Constants;
import org.jay.entity.dto.LoginUser;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private ObjectMapper objectMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		设置白名单
		List<String> whiteName = List.of("/login");
		if (whiteName.contains(request.getRequestURI())) {
			return true;
		}
//		如果不是白名单的请求，检测
//		判断有没有Authorization这个请求头
		ResponseEntity<String> res = ResponseEntity.status(401).body("Bad Credentials");
		String authorization = request.getHeader(Constants.HEAD_AUTHORIZATION);
		if (authorization == null) {
			response.getWriter().write(objectMapper.writeValueAsString(res));
			return false;
		}
//			从redis中获取值
		String value = stringRedisTemplate.opsForValue().get(Constants.TOKEN_PREFIX + authorization);
		LoginUser loginUser = objectMapper.readValue(value, LoginUser.class);
		if (loginUser == null) {
			response.getWriter().write(objectMapper.writeValueAsString(res));
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
