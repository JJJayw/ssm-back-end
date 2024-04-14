package org.jay.entity.po;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志(OperLog)实体类
 *
 * @author makejava
 * @since 2024-04-13 10:51:50
 */
@Data
public class OperLog implements Serializable {
	@Serial
	private static final long serialVersionUID = 793470680792222920L;
	/**
	 * 日志主键
	 */
	private Integer operId;
	/**
	 * 操作模块
	 */
	private String title;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * api方法
	 */
	private String method;
	/**
	 * 请求方式
	 */
	private String requestMethod;
	/**
	 * 操作人员
	 */
	private String operName;
	/**
	 * 请求url
	 */
	private String operUrl;
	/**
	 * 操作地址
	 */
	private String operIp;
	/**
	 * 操作状态
	 */
	private Integer status;
	/**
	 * 错误消息
	 */
	private String errormsg;
	/**
	 * 操作时间
	 */
	private Date opertime;


}

