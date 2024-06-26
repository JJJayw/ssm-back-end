package org.jay.entity.po;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(User)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:53
 */
@Data
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = -27077246034268608L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户账号
	 */
	@NotNull
	@Size(min = 5, max = 15, message = "用户名的长度应该在5~15之间")
	private String userName;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	@Pattern(regexp = "/^1[3456789]\\d{9}$/")
	private String phonenumber;
	/**
	 * 用户性别（0男 1女 2未知）
	 */
	private String sex;
	/**
	 * 头像地址
	 */
	private String avatar;
	/**
	 * 密码
	 */
	@NotNull
	@Size(min = 5, max = 15, message = "密码的长度应该在5~15之间")
	private String password;
	/**
	 * 帐号状态（0正常 1停用）
	 */
	private String status;
	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	private String delFlag;
	/**
	 * 最后登录IP
	 */
	private String loginIp;
	/**
	 * 最后登录时间
	 */
	private Date loginDate;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;


}

