package com.bamboo.im.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bamboo.common.controller.BaseController;

@Controller
@RequestMapping("/im/chat")
public class ChatController extends BaseController {
	
	private static String  prefix= "im/chat";

	@GetMapping()
	@RequiresPermissions("im:chat:chat")
	public String chat() {
		return prefix + "/chat";
	}
	
}
