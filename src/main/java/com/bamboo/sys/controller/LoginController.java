package com.bamboo.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.common.annotation.Log;
import com.bamboo.common.controller.BaseController;
import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.MD5Utils;
import com.bamboo.common.utils.R;
import com.bamboo.common.utils.ShiroUtils;
import com.bamboo.sys.domain.Menu;
import com.bamboo.sys.service.MenuService;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		logger.info(getUser().getName());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
