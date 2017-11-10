package com.bamboo.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.common.annotation.Log;
import com.bamboo.common.controller.BaseController;
import com.bamboo.common.utils.MD5Utils;
import com.bamboo.common.utils.PageUtils;
import com.bamboo.common.utils.Query;
import com.bamboo.common.utils.R;
import com.bamboo.sys.domain.RoleDO;
import com.bamboo.sys.domain.User;
import com.bamboo.sys.service.RoleService;
import com.bamboo.sys.service.UserService;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:user:user")
	@RequestMapping("")
	String user(Model model) {
		return "sys/user/user";
	}

	@RequestMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<User> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@RequestMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return "sys/user/userAdd";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@RequestMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		User user= userService.get(id);
		model.addAttribute("user", user);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return "sys/user/userEdit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@RequestMapping("/save")
	@ResponseBody
	R save(User user) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@RequestMapping("/update")
	@ResponseBody
	R update(User user) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@RequestMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@RequestMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		System.out.println(r);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequestMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		return !userService.exit(params);// 存在，不通过，false
	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@RequestMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		User userDO = new User();
		userDO.setId(userId);
		model.addAttribute("user", userDO);
		return "sys/user/reset_pwd";
	}

	@Log("提交更改用户密码")
	@RequestMapping("/resetPwd")
	@ResponseBody
	R resetPwd(User user) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(userService.get(user.getId()).getUsername(), user.getPassword()));
		if (userService.resetPwd(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

}
