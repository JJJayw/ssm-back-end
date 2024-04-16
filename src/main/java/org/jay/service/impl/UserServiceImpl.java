package org.jay.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jay.constant.Constants;
import org.jay.dao.UserDao;
import org.jay.entity.dto.LoginUser;
import org.jay.entity.po.User;
import org.jay.exception.PasswordIncorrectException;
import org.jay.exception.UserNotFount;
import org.jay.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


/**
 * 用户信息表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-13 10:51:54
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private ObjectMapper objectMapper;

	@Resource
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 通过ID查询单条数据
	 *
	 * @param userId 主键
	 * @return 实例对象
	 */
	@Override
	public User queryById(Long userId) {
		return this.userDao.queryById(userId);
	}

	/**
	 * 分页查询
	 *
	 * @param user        筛选条件
	 * @param pageRequest 分页对象
	 * @return 查询结果
	 */
	@Override
	public Page<User> queryByPage(User user, PageRequest pageRequest) {
		long total = this.userDao.count(user);
		return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
	}

	/**
	 * 新增数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User insert(User user) {
		this.userDao.insert(user);
		return user;
	}

	/**
	 * 修改数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User update(User user) {
		this.userDao.update(user);
		return this.queryById(user.getUserId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param userId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Long userId) {
		return this.userDao.deleteById(userId) > 0;
	}

	@Override
	public LoginUser login(String userName, String password) throws JsonProcessingException {
//		1. 使用用户名查询用户，没有查询到，说明没有该用户
		User user = userDao.queryByUserName(userName);
		if (user == null) throw new UserNotFount("执行登录功能[" + userName + "]用户不存在");

//		2. 如果查到了。比较密码，密码如果不正确，登录失败
		if (!password.equals(user.getPassword())) {
			log.info("执行登录功能[{}]该用户密码输入错误", userName);
			throw new PasswordIncorrectException("执行登录功能[" + userName + "]该用户密码输入错误");
		}
//		3. 如果验证成功了，
//		    （1）生成token
		String token = UUID.randomUUID().toString();
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
//		通过IP获取其所属IP地址
		ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteHost() + "&json=true", String.class);
		String body = result.getBody();
		Map<String, String> map = objectMapper.readValue(body, new TypeReference<>() {
		});
		String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");
//		    （2）封装一个登录用户LoginUser，保存在redis
		LoginUser loginUser = LoginUser.builder()
				.userId(user.getUserId())
				.token(token)
				.ipaddr(request.getRemoteAddr())
				.loginTime(new Date())
				.os(userAgent.getOperatingSystem().getName())
				.browser(userAgent.getBrowser().getName())
				.loginLocation(location)
				.user(user)
				.build();
		String json = objectMapper.writeValueAsString(loginUser);
		stringRedisTemplate.opsForValue().set(Constants.TOKEN_PREFIX + token, json, Duration.ofSeconds(60));
		return loginUser;
	}
}
