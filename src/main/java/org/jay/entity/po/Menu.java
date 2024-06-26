package org.jay.entity.po;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权限表(Menu)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:49
 */
@Data
public class Menu implements Serializable {
	@Serial
	private static final long serialVersionUID = -97311925634566643L;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 权限标识
	 */
	private String perms;
	/**
	 * 父菜单ID
	 */
	private Long parentId;
	/**
	 * 显示顺序
	 */
	private Integer orderNum;
	/**
	 * 路由地址
	 */
	private String path;
	/**
	 * 组件路径
	 */
	private String component;
	/**
	 * 菜单类型（M目录 C菜单 F按钮）
	 */
	private String menuType;
	/**
	 * 菜单状态（0显示 1隐藏）
	 */
	private String visible;
	/**
	 * 菜单图标
	 */
	private String icon;
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

