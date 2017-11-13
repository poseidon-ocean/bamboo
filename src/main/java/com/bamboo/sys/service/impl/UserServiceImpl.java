package com.bamboo.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bamboo.sys.domain.Dept;
import com.bamboo.sys.domain.User;
import com.bamboo.sys.domain.UserRole;
import com.bamboo.sys.mapper.DeptMapper;
import com.bamboo.sys.mapper.UserMapper;
import com.bamboo.sys.mapper.UserRoleMapper;
import com.bamboo.sys.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	DeptMapper sysDeptMapper;

	@Override
	public User get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		User user = userMapper.queryById(id);
		
		Long deptId = user.getDeptId();
		if(Objects.nonNull(deptId)) {
			Dept d = sysDeptMapper.queryById(user.getDeptId());
			if(Objects.nonNull(d)) {
				user.setDeptName(d.getName());
			}
		}
		
		user.setroleIds(roleIds);
		return user;
	}

	@Override
	public List<User> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Transactional
	@Override
	public int save(User user) {
		user.preInsert();
		int count = userMapper.create(user);
		Long userId = user.getId();
		List<Long> roles = user.getroleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return count;
	}

	@Override
	public int update(User user) {
		user.preUpadate();
		int r = userMapper.update(user);
		Long userId = user.getId();
		List<Long> roles = user.getroleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return r;
	}

	@Override
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userMapper.list(params).size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(User user) {
		int r = userMapper.update(user);
		return r;
	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

}
