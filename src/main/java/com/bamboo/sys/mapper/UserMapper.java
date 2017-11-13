package com.bamboo.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.bamboo.common.base.BaseMapper;
import com.bamboo.sys.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	
}
