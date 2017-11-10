package com.bamboo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bamboo.system.domain.User;

@Service
public interface UserService {
	User get(Long id);

	List<User> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(User user);

	int update(User user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(User user);

}
