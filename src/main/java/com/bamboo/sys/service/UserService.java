package com.bamboo.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bamboo.common.base.BaseService;
import com.bamboo.sys.domain.Dept;
import com.bamboo.sys.domain.User;
import com.bamboo.sys.domain.UserRole;
import com.bamboo.sys.mapper.DeptMapper;
import com.bamboo.sys.mapper.UserMapper;
import com.bamboo.sys.mapper.UserRoleMapper;

@Service
public class UserService extends BaseService<UserMapper, User>{
	
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	DeptMapper deptMapper;

	public User get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		User user = mapper.queryById(id);
		
		Long deptId = user.getDeptId();
		if(Objects.nonNull(deptId)) {
			Dept d = deptMapper.queryById(user.getDeptId());
			if(Objects.nonNull(d)) {
				user.setDeptName(d.getName());
			}
		}
		
		user.setroleIds(roleIds);
		return user;
	}

	public List<User> list(Map<String, Object> map) {
		return mapper.list(map);
	}

	public int count(Map<String, Object> map) {
		return mapper.count(map);
	}

	@Transactional
	public int save(User user) {
		user.preInsert();
		int count = mapper.create(user);
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

	public boolean update(User user) {
		user.preUpadate();
		int r = mapper.update(user);
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
		return r > 0;
	}

	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return mapper.remove(userId);
	}

	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = mapper.list(params).size() > 0;
		return exit;
	}

	public Set<String> listRoles(Long userId) {
		return null;
	}

	public int resetPwd(User user) {
		int r = mapper.update(user);
		return r;
	}

	@Transactional
	public int batchremove(Long[] userIds) {
		int count = mapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

}
