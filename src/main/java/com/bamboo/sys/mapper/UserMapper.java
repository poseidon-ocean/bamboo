package com.bamboo.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bamboo.sys.domain.User;


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
