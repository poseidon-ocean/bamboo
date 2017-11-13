package com.bamboo.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.BuildTree;
import com.bamboo.sys.domain.Dept;
import com.bamboo.sys.mapper.DeptMapper;
import com.bamboo.sys.service.SysDeptService;

@Service
public class DeptServiceImpl implements SysDeptService {
	@Autowired
	private DeptMapper sysDeptMapper;
	
	@Override
	public Dept get(Long deptId){
		return sysDeptMapper.queryById(deptId);
	}
	
	@Override
	public List<Dept> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}
	
	@Override
	public int save(Dept sysDept){
		return sysDeptMapper.create(sysDept);
	}
	
	@Override
	public int update(Dept sysDept){
		return sysDeptMapper.update(sysDept);
	}
	
	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}
	
	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}
	
	@Override
	public Tree<Dept> getTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> SysDepts = sysDeptMapper.list(new HashMap<String,Object>());
		for (Dept SysDept : SysDepts) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(SysDept.getId().toString());
			tree.setParentId(SysDept.getParentId().toString());
			tree.setText(SysDept.getName());
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
