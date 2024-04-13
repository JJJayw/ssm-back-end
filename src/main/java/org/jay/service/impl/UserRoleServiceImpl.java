package org.jay.service.impl;

import jakarta.annotation.Resource;
import org.jay.entity.UserRole;
import org.jay.dao.UserRoleDao;
import org.jay.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-04-13 10:51:54
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	private UserRoleDao userRoleDao;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param userId 主键
	 * @return 实例对象
	 */
	@Override
	public UserRole queryById(Long userId) {
		return this.userRoleDao.queryById(userId);
	}

	/**
	 * 分页查询
	 *
	 * @param userRole    筛选条件
	 * @param pageRequest 分页对象
	 * @return 查询结果
	 */
	@Override
	public Page<UserRole> queryByPage(UserRole userRole, PageRequest pageRequest) {
		long total = this.userRoleDao.count(userRole);
		return new PageImpl<>(this.userRoleDao.queryAllByLimit(userRole, pageRequest), pageRequest, total);
	}

	/**
	 * 新增数据
	 *
	 * @param userRole 实例对象
	 * @return 实例对象
	 */
	@Override
	public UserRole insert(UserRole userRole) {
		this.userRoleDao.insert(userRole);
		return userRole;
	}

	/**
	 * 修改数据
	 *
	 * @param userRole 实例对象
	 * @return 实例对象
	 */
	@Override
	public UserRole update(UserRole userRole) {
		this.userRoleDao.update(userRole);
		return this.queryById(userRole.getUserId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param userId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Long userId) {
		return this.userRoleDao.deleteById(userId) > 0;
	}
}
