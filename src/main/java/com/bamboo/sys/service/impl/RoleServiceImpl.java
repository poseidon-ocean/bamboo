package com.bamboo.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bamboo.sys.domain.RoleDO;
import com.bamboo.sys.domain.RoleMenuDO;
import com.bamboo.sys.mapper.RoleMapper;
import com.bamboo.sys.mapper.RoleMenuMapper;
import com.bamboo.sys.mapper.UserMapper;
import com.bamboo.sys.mapper.UserRoleMapper;
import com.bamboo.sys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	public static final String ROLE_ALL_KEY = "\"role_all\"";

	public static final String DEMO_CACHE_NAME = "role";

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public List<RoleDO> list() {
		List<RoleDO> roles = roleMapper.list(new HashMap<>());
		return roles;
	}

	@Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
	@Override
	public List<RoleDO> list(Long userId) {
		List<Long> rolesIds = userRoleMapper.listRoleId(userId);
		List<RoleDO> roles = roleMapper.list(new HashMap<>());
		for (RoleDO roleDO : roles) {
			roleDO.setRoleSign("false");
			for (Long roleId : rolesIds) {
				if (roleDO.getRoleId() == roleId) {
					roleDO.setRoleSign("true");
					break;
				}
			}
		}
		return roles;
	}

	@Transactional
	@Override
	public int save(RoleDO role) {
		int count = roleMapper.save(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return count;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	@Transactional
	@Override
	public int remove(Long id) {
		int count = roleMapper.remove(id);
		roleMenuMapper.removeByRoleId(id);
		return count;
	}

	@Override
	public RoleDO get(Long id) {
		RoleDO roleDO = roleMapper.get(id);
		return roleDO;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	@Override
	public int update(RoleDO role) {
		int r = roleMapper.update(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		roleMenuMapper.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		//roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return r;
	}

}
