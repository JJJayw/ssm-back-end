package org.jay.service.impl;

import jakarta.annotation.Resource;
import org.jay.dao.RoleMenuDao;
import org.jay.entity.po.RoleMenu;
import org.jay.service.RoleMenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-13 10:51:53
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
	@Resource
	private RoleMenuDao roleMenuDao;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param roleId 主键
	 * @return 实例对象
	 */
	@Override
	public RoleMenu queryById(Long roleId) {
		return this.roleMenuDao.queryById(roleId);
	}

	/**
	 * 分页查询
	 *
	 * @param roleMenu    筛选条件
	 * @param pageRequest 分页对象
	 * @return 查询结果
	 */
	@Override
	public Page<RoleMenu> queryByPage(RoleMenu roleMenu, PageRequest pageRequest) {
		long total = this.roleMenuDao.count(roleMenu);
		return new PageImpl<>(this.roleMenuDao.queryAllByLimit(roleMenu, pageRequest), pageRequest, total);
	}

	/**
	 * 新增数据
	 *
	 * @param roleMenu 实例对象
	 * @return 实例对象
	 */
	@Override
	public RoleMenu insert(RoleMenu roleMenu) {
		this.roleMenuDao.insert(roleMenu);
		return roleMenu;
	}

	/**
	 * 修改数据
	 *
	 * @param roleMenu 实例对象
	 * @return 实例对象
	 */
	@Override
	public RoleMenu update(RoleMenu roleMenu) {
		this.roleMenuDao.update(roleMenu);
		return this.queryById(roleMenu.getRoleId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param roleId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Long roleId) {
		return this.roleMenuDao.deleteById(roleId) > 0;
	}
}
