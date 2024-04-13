package org.jay.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户和角色关联表(UserRole)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:54
 */
@Data
public class UserRole implements Serializable {
	@Serial
	private static final long serialVersionUID = -70159779118786055L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;


}

