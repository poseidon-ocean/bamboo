package com.bamboo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.bamboo.common.utils.ShiroUtils;
import com.bamboo.sys.domain.User;

@Controller
public class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
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