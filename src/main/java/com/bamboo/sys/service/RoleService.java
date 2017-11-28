package com.bamboo.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bamboo.common.base.BaseService;
import com.bamboo.sys.domain.Role;
import com.bamboo.sys.domain.RoleMenu;
import com.bamboo.sys.mapper.RoleMapper;
import com.bamboo.sys.mapper.RoleMenuMapper;
import com.bamboo.sys.mapper.UserRoleMapper;

@Service
public class RoleService extends BaseService<RoleMapper, Role> {
	public static final String ROLE_ALL_KEY = "\"role_all\"";

	public static final String DEMO_CACHE_NAME = "role";
	
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	@Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
	public List<Role> list(Long userId) {
		List<Long> rolesIds = userRoleMapper.listRoleId(userId);
		List<Role> roles = this.mapper.list(new HashMap<>());
		for (Role roleDO : roles) {
			roleDO.setSign("false");
			for (Long roleId : rolesIds) {
				if (roleDO.getId() == roleId) {
					roleDO.setSign("true");
					break;
				}
			}
		}
		return roles;
	}
	
	@Transactional
	public boolean save(Role role) {
		int count = mapper.create(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getId();
		List<RoleMenu> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenu rmDo = new RoleMenu();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return count > 0;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	@Transactional
	public boolean remove(Long id) {
		int count = mapper.remove(id);
		roleMenuMapper.removeByRoleId(id);
		return count > 0;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	public boolean update(Role role) {
		int r = mapper.update(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getId();
		roleMenuMapper.removeByRoleId(roleId);
		List<RoleMenu> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenu rmDo = new RoleMenu();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		//roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return r > 0;
	}
	
}
