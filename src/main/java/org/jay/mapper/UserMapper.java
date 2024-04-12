package org.jay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jay.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user")
	List<User> select();
}
