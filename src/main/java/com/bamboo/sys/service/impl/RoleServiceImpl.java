//package com.bamboo.sys.service.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.bamboo.sys.domain.Role;
//import com.bamboo.sys.domain.RoleMenu;
//import com.bamboo.sys.mapper.RoleMapper;
//import com.bamboo.sys.mapper.RoleMenuMapper;
//import com.bamboo.sys.mapper.UserMapper;
//import com.bamboo.sys.mapper.UserRoleMapper;
//import com.bamboo.sys.service.RoleService;
//
//@Service
//public class RoleServiceImpl implements RoleService {
//
//	public static final String ROLE_ALL_KEY = "\"role_all\"";
//
//	public static final String DEMO_CACHE_NAME = "role";
//
//	@Autowired
//	RoleMapper roleMapper;
//	@Autowired
//	RoleMenuMapper roleMenuMapper;
//	@Autowired
//	UserMapper userMapper;
//	@Autowired
//	UserRoleMapper userRoleMapper;
//
//	@Override
//	public List<Role> list() {
//		List<Role> roles = roleMapper.list(new HashMap<>());
//		return roles;
//	}
//
//	@Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
//	@Override
//	public List<Role> list(Long userId) {
//		List<Long> rolesIds = userRoleMapper.listRoleId(userId);
//		List<Role> roles = roleMapper.list(new HashMap<>());
//		for (Role roleDO : roles) {
//			roleDO.setSign("false");
//			for (Long roleId : rolesIds) {
//				if (roleDO.getId() == roleId) {
//					roleDO.setSign("true");
//					break;
//				}
//			}
//		}
//		return roles;
//	}
//
//	@Transactional
//	@Override
//	public int save(Role role) {
//		int count = roleMapper.create(role);
//		List<Long> menuIds = role.getMenuIds();
//		Long roleId = role.getId();
//		List<RoleMenu> rms = new ArrayList<>();
//		for (Long menuId : menuIds) {
//			RoleMenu rmDo = new RoleMenu();
//			rmDo.setRoleId(roleId);
//			rmDo.setMenuId(menuId);
//			rms.add(rmDo);
//		}
//		roleMenuMapper.removeByRoleId(roleId);
//		if (rms.size() > 0) {
//			roleMenuMapper.batchSave(rms);
//		}
//		return count;
//	}
//
//	@CacheEvict(value = DEMO_CACHE_NAME)
//	@Transactional
//	@Override
//	public int remove(Long id) {
//		int count = roleMapper.remove(id);
//		roleMenuMapper.removeByRoleId(id);
//		return count;
//	}
//
//	@Override
//	public Role get(Long id) {
//		Role roleDO = roleMapper.queryById(id);
//		return roleDO;
//	}
//
//	@CacheEvict(value = DEMO_CACHE_NAME)
//	@Override
//	public int update(Role role) {
//		int r = roleMapper.update(role);
//		List<Long> menuIds = role.getMenuIds();
//		Long roleId = role.getId();
//		roleMenuMapper.removeByRoleId(roleId);
//		List<RoleMenu> rms = new ArrayList<>();
//		for (Long menuId : menuIds) {
//			RoleMenu rmDo = new RoleMenu();
//			rmDo.setRoleId(roleId);
//			rmDo.setMenuId(menuId);
//			rms.add(rmDo);
//		}
//		//roleMenuMapper.removeByRoleId(roleId);
//		if (rms.size() > 0) {
//			roleMenuMapper.batchSave(rms);
//		}
//		return r;
//	}
//
//}
