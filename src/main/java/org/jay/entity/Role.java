package org.jay.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 角色信息表(Role)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:51
 */
@Data
public class Role implements Serializable {
	@Serial
	private static final long serialVersionUID = 133361558717190092L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色标签
	 */
	private String roleTag;
	/**
	 * 显示顺序
	 */
	private Integer roleSort;
	/**
	 * 角色状态（0正常 1停用）
	 */
	private String status;
	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	private String delFlag;
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

