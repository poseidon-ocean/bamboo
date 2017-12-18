package com.bamboo.common.service;

import com.bamboo.common.domain.SysFile;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public interface SysFileService {
	
	SysFile get(Long id);
	
	List<SysFile> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysFile sysFile);
	
	int update(SysFile sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
