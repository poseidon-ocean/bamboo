package com.bamboo.sys.domain;

import java.util.List;

import com.bamboo.common.base.BaseEntity;

public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	// 用户名
	private String username;
	// 用户真实姓名
	private String name;
	// 密码
	private String password;
	// 部门
	private Long deptId;
	private String deptName;
	// 邮箱
	private String email;
	// 手机号
	private String mobile;
	// 状态 0:禁用，1:正常
	private Integer status;
	//角色
	private List<Long> roleIds;

	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置：状态 0:禁用，1:正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：状态 0:禁用，1:正常
	 */
	public Integer getStatus() {
		return status;
	}

	public List<Long> getroleIds() {
		return roleIds;
	}

	public void setroleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	
}
