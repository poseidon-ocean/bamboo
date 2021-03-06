package com.bamboo.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bamboo.common.base.BaseService;
import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.BuildTree;
import com.bamboo.sys.domain.Menu;
import com.bamboo.sys.mapper.MenuMapper;
import com.bamboo.sys.mapper.RoleMenuMapper;

@Service
public class MenuService extends BaseService<MenuMapper, Menu> {
	@Autowired
	MenuMapper menuMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	/**
	 * @param 用户ID
	 * @return 树形菜单
	 */
	@Cacheable
	public Tree<Menu> getSysMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	public List<Menu> list() {
		List<Menu> menus = menuMapper.list(new HashMap<>());
		return menus;
	}


	public Tree<Menu> getTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.list(new HashMap<>());
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	public Tree<Menu> getTree(Long id) {
		// 根据roleId查询权限
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.list(new HashMap<String, Object>());
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>();
			Long menuId = sysMenuDO.getId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	public List<Tree<Menu>> listMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

}
