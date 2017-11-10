package com.bamboo.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.BuildTree;
import com.bamboo.system.domain.DeptDO;
import com.bamboo.system.mapper.DeptMapper;
import com.bamboo.system.service.SysDeptService;

@Service
public class DeptServiceImpl implements SysDeptService {
	@Autowired
	private DeptMapper sysDeptMapper;
	
	@Override
	public DeptDO get(Long deptId){
		return sysDeptMapper.get(deptId);
	}
	
	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}
	
	@Override
	public int save(DeptDO sysDept){
		return sysDeptMapper.save(sysDept);
	}
	
	@Override
	public int update(DeptDO sysDept){
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
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> SysDepts = sysDeptMapper.list(new HashMap<String,Object>());
		for (DeptDO SysDept : SysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(SysDept.getDeptId().toString());
			tree.setParentId(SysDept.getParentId().toString());
			tree.setText(SysDept.getName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
	
}