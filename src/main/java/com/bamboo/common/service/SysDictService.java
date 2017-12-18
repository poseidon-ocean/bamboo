package com.bamboo.common.service;

import com.bamboo.common.domain.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface SysDictService {
	
	Dict get(Long id);
	
	List<Dict> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Dict sysDict);
	
	int update(Dict sysDict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<Dict> listType();
}
