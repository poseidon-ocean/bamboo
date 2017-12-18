package com.bamboo.blog.service;

import java.util.List;
import java.util.Map;

import com.bamboo.blog.domain.BlogContent;

/**
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
public interface BContentService {
	
	BlogContent get(Long cid);
	
	List<BlogContent> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BlogContent bContent);
	
	int update(BlogContent bContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}
