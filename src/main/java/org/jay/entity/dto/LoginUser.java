package org.jay.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jay.entity.po.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户唯一标识
	 */
	private String token;
	/**
	 * 登录时间
	 */
	private Date loginTime;
	/**
	 * 登录IP地址
	 */
	private String ipaddr;

	/**
	 * 登录地点
	 */
	private String loginLocation;
	/**
	 * 浏览器类型
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
	/**
	 * 用户信息
	 */
	private User user;
}
