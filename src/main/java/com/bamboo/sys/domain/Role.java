package com.bamboo.sys.domain;

import java.util.List;

import com.bamboo.common.base.BaseEntity;

public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String sign;
	private String remark;
	
	private List<Long> menuIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}

}
