package com.bamboo.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bamboo.common.base.BaseMapper;
import com.bamboo.sys.domain.Menu;

/**
 * 菜单管理
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
