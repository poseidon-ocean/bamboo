package com.bamboo.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bamboo.common.base.BaseMapper;
import com.bamboo.sys.domain.UserRole;

/**
 * 用户与角色对应关系
 * 
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int batchSave(List<UserRole> list);

	int batchRemoveByUserId(Long[] ids);
}
