package com.bamboo.sys.service;

import com.bamboo.common.domain.Tree;
import com.bamboo.sys.domain.Dept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface SysDeptService {
	
	Dept get(Long deptId);
	
	List<Dept> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Dept sysDept);
	
	int update(Dept sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<Dept> getTree();
}
