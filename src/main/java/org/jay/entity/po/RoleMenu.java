package org.jay.entity.po;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色和菜单关联表(RoleMenu)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:52
 */
@Data
public class RoleMenu implements Serializable {
	@Serial
	private static final long serialVersionUID = -87318615164378743L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 菜单ID
	 */
	private Long menuId;


}

