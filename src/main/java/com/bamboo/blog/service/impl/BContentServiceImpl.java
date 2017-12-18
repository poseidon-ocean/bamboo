package com.bamboo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bamboo.blog.domain.BlogContent;
import com.bamboo.blog.mapper.ContentMapper;
import com.bamboo.blog.service.BContentService;



@Service
public class BContentServiceImpl implements BContentService {
	
	@Autowired
	private ContentMapper bContentMapper;
	
	@Override
	public BlogContent get(Long cid){
		return bContentMapper.queryById(cid);
	}
	
	@Override
	public List<BlogContent> list(Map<String, Object> map){
		return bContentMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bContentMapper.count(map);
	}
	
	@Override
	public int save(BlogContent bContent){
		return bContentMapper.create(bContent);
	}
	
	@Override
	public int update(BlogContent bContent){
		return bContentMapper.update(bContent);
	}
	
	@Override
	public int remove(Long cid){
		return bContentMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return bContentMapper.batchRemove(cids);
	}
	
}
