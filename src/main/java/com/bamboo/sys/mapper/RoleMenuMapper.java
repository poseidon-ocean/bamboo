package com.bamboo.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bamboo.common.base.BaseMapper;
import com.bamboo.sys.domain.RoleMenu;

/**
 * 角色与菜单对应关系
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

	List<Long> listMenuIdByRoleId(Long roleId);
	
	int removeByRoleId(Long roleId);
	
	int batchSave(List<RoleMenu> list);
}
