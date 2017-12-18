package com.bamboo.common.domain;

import java.util.Date;

import com.bamboo.common.base.BaseEntity;

/**
 * 文件上传
 * 
 */
public class SysFile extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 文件类型
	private Integer type;
	// URL地址
	private String url;
	// 创建时间
	private Date createDate;
	
	
	public SysFile() {
		super();
	}


	public SysFile(Integer type, String url, Date createDate) {
		super();
		this.type = type;
		this.url = url;
		this.createDate = createDate;
	}

	
	/**
	 * 设置：文件类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取：文件类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置：URL地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：URL地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
