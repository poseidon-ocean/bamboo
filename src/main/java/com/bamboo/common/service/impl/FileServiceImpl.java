package com.bamboo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bamboo.common.domain.SysFile;
import com.bamboo.common.mapper.FileMapper;
import com.bamboo.common.service.SysFileService;



@Service
public class FileServiceImpl implements SysFileService {
	@Autowired
	private FileMapper sysFileMapper;
	
	@Override
	public SysFile get(Long id){
		return sysFileMapper.queryById(id);
	}
	
	@Override
	public List<SysFile> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(SysFile sysFile){
		return sysFileMapper.create(sysFile);
	}
	
	@Override
	public int update(SysFile sysFile){
		return sysFileMapper.update(sysFile);
	}
	
	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}
	
}
