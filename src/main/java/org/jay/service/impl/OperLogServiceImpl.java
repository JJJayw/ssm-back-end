package org.jay.service.impl;

import jakarta.annotation.Resource;
import org.jay.dao.OperLogDao;
import org.jay.entity.po.OperLog;
import org.jay.service.OperLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * 操作日志(OperLog)表服务实现类
 *
 * @author makejava
 * @since 2024-04-13 10:51:51
 */
@Service("operLogService")
public class OperLogServiceImpl implements OperLogService {
	@Resource
	private OperLogDao operLogDao;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param operId 主键
	 * @return 实例对象
	 */
	@Override
	public OperLog queryById(Integer operId) {
		return this.operLogDao.queryById(operId);
	}

	/**
	 * 分页查询
	 *
	 * @param operLog     筛选条件
	 * @param pageRequest 分页对象
	 * @return 查询结果
	 */
	@Override
	public Page<OperLog> queryByPage(OperLog operLog, PageRequest pageRequest) {
		long total = this.operLogDao.count(operLog);
		return new PageImpl<>(this.operLogDao.queryAllByLimit(operLog, pageRequest), pageRequest, total);
	}

	/**
	 * 新增数据
	 *
	 * @param operLog 实例对象
	 * @return 实例对象
	 */
	@Override
	public OperLog insert(OperLog operLog) {
		this.operLogDao.insert(operLog);
		return operLog;
	}

	/**
	 * 修改数据
	 *
	 * @param operLog 实例对象
	 * @return 实例对象
	 */
	@Override
	public OperLog update(OperLog operLog) {
		this.operLogDao.update(operLog);
		return this.queryById(operLog.getOperId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param operId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Integer operId) {
		return this.operLogDao.deleteById(operId) > 0;
	}
}
