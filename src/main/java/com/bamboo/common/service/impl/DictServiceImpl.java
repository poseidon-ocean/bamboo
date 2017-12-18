package com.bamboo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bamboo.common.domain.Dict;
import com.bamboo.common.mapper.DictMapper;
import com.bamboo.common.service.SysDictService;



@Service
public class DictServiceImpl implements SysDictService {
	@Autowired
	private DictMapper sysDictMapper;
	
	@Override
	public Dict get(Long id){
		return sysDictMapper.queryById(id);
	}
	
	@Override
	public List<Dict> list(Map<String, Object> map){
		return sysDictMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysDictMapper.count(map);
	}
	
	@Override
	public int save(Dict sysDict){
		return sysDictMapper.create(sysDict);
	}
	
	@Override
	public int update(Dict sysDict){
		return sysDictMapper.update(sysDict);
	}
	
	@Override
	public int remove(Long id){
		return sysDictMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysDictMapper.batchRemove(ids);
	}
	
	@Override
	
	public List<Dict> listType(){
		return sysDictMapper.listType();
	};
	
}
