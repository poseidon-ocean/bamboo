package com.bamboo.common.domain;

import com.bamboo.common.base.BaseEntity;

/**
 * 字典表
 */
public class Dict extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	//标签名
	private String name;
	//数据值
	private String value;
	//类型
	private String type;
	//描述
	private String description;
	//排序（升序）
	private Integer sort;
	//父级编号
	private Long parentId;
	//备注信息
	private String remarks;
	//删除标记
	private String delFlag;

	/**
	 * 设置：标签名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标签名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：数据值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：数据值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 设置：父级编号
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级编号
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：删除标记
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标记
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
