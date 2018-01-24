package com.bamboo.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bamboo.common.base.BaseService;
import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.BuildTree;
import com.bamboo.sys.domain.Dept;
import com.bamboo.sys.mapper.DeptMapper;

/**
 * 部门管理
 * 
 */
@Service
public class DeptService extends BaseService<DeptMapper, Dept>{

	public int batchRemove(Long[] deptIds){
		return mapper.batchRemove(deptIds);
	}
	
	public Tree<Dept> getTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> depts = mapper.list(new HashMap<String,Object>());
		for (Dept dept : depts) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(dept.getId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Dept> t = BuildTree.build(trees);
		return t;
	}
	
}
