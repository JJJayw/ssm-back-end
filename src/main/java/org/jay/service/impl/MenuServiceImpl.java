package org.jay.service.impl;

import jakarta.annotation.Resource;
import org.jay.entity.Menu;
import org.jay.dao.MenuDao;
import org.jay.service.MenuService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-13 10:51:50
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param menuId 主键
	 * @return 实例对象
	 */
	@Override
	public Menu queryById(Long menuId) {
		return this.menuDao.queryById(menuId);
	}

	/**
	 * 分页查询
	 *
	 * @param menu        筛选条件
	 * @param pageRequest 分页对象
	 * @return 查询结果
	 */
	@Override
	public Page<Menu> queryByPage(Menu menu, PageRequest pageRequest) {
		long total = this.menuDao.count(menu);
		return new PageImpl<>(this.menuDao.queryAllByLimit(menu, pageRequest), pageRequest, total);
	}

	/**
	 * 新增数据
	 *
	 * @param menu 实例对象
	 * @return 实例对象
	 */
	@Override
	public Menu insert(Menu menu) {
		this.menuDao.insert(menu);
		return menu;
	}

	/**
	 * 修改数据
	 *
	 * @param menu 实例对象
	 * @return 实例对象
	 */
	@Override
	public Menu update(Menu menu) {
		this.menuDao.update(menu);
		return this.queryById(menu.getMenuId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param menuId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Long menuId) {
		return this.menuDao.deleteById(menuId) > 0;
	}
}
