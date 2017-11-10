package com.bamboo.system.mapper;

import com.bamboo.system.domain.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

	User get(Long userId);
	
	List<User> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(User user);
	
	int update(User user);
	
	int remove(Long user_id);
	
	int batchRemove(Long[] userIds);
}
