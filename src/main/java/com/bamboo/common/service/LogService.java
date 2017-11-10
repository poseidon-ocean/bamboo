package com.bamboo.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bamboo.common.domain.LogDO;
import com.bamboo.common.domain.PageDO;
import com.bamboo.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
