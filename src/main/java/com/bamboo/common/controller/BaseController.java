package com.bamboo.common.controller;

import org.springframework.stereotype.Controller;

import com.bamboo.common.utils.ShiroUtils;
import com.bamboo.system.domain.User;

@Controller
public class BaseController {
	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

}