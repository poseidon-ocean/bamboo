package com.bamboo.common.service;

import com.bamboo.common.domain.SysTask;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
public interface TaskScheduleJobService {
	
	SysTask get(Long id);
	
	List<SysTask> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysTask taskScheduleJob);
	
	int update(SysTask taskScheduleJob);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
