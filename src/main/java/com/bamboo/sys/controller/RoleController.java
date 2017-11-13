package com.bamboo.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.common.annotation.Log;
import com.bamboo.common.controller.BaseController;
import com.bamboo.common.utils.R;
import com.bamboo.sys.domain.Role;
import com.bamboo.sys.service.RoleService;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "sys/role";
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:role:role")
	@RequestMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@RequestMapping("/list")
	@ResponseBody()
	List<Role> list() {
		List<Role> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@RequestMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@RequestMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		Role roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@RequestMapping("/save")
	@ResponseBody()
	R save(Role role) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@RequestMapping("/update")
	@ResponseBody()
	R update(Role role) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@RequestMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
}
