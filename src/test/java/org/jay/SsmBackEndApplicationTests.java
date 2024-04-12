package org.jay;

//import org.jay.entity.User;
import org.jay.entity.User;
import org.jay.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class SsmBackEndApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	void contextLoads() {
		List<User> select = userMapper.select();
		System.out.println(select);
	}

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	void testSet() {
		redisTemplate.boundValueOps("name").set("zhangsan");
	}

	@Test
	void testGet() {
		Object name = redisTemplate.boundValueOps("name").get();
		System.out.println(name);
	}

}
