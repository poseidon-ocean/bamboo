package com.bamboo.common.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamboo.common.domain.SysLog;
import com.bamboo.common.domain.PageDO;
import com.bamboo.common.mapper.LogMapper;
import com.bamboo.common.service.LogService;
import com.bamboo.common.utils.Query;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	LogMapper logMapper;

	@Override
	public PageDO<SysLog> queryList(Query query) {
		List<SysLog> logs = logMapper.list(query);
		int total = logMapper.count(query);
		PageDO<SysLog> page = new PageDO<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	@Override
	public int remove(Long id) {
		int count = logMapper.remove(id);
		return count;
	}

	@Override
	public int batchRemove(Long[] ids){
		return logMapper.batchRemove(ids);
	}
}
