package com.bamboo.common.service.impl;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bamboo.common.domain.ScheduleJob;
import com.bamboo.common.domain.SysTask;
import com.bamboo.common.mapper.TaskMapper;
import com.bamboo.common.quartz.utils.QuartzManager;
import com.bamboo.common.service.TaskScheduleJobService;
import com.bamboo.common.utils.ScheduleJobUtils;

@Service
public class JobServiceImpl implements TaskScheduleJobService {
	
	@Autowired
	private TaskMapper taskScheduleJobMapper;


	@Override
	public SysTask get(Long id) {
		return taskScheduleJobMapper.queryById(id);
	}

	@Override
	public List<SysTask> list(Map<String, Object> map) {
		return taskScheduleJobMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return taskScheduleJobMapper.count(map);
	}

	@Override
	public int save(SysTask taskScheduleJob) {
		return taskScheduleJobMapper.create(taskScheduleJob);
	}

	@Override
	public int update(SysTask taskScheduleJob) {
		return taskScheduleJobMapper.update(taskScheduleJob);
	}

	@Override
	public int remove(Long id) {
		QuartzManager quartzManager = new QuartzManager();
		try {
			SysTask scheduleJob = get(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			return taskScheduleJobMapper.remove(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int batchRemove(Long[] ids) {
		QuartzManager quartzManager = new QuartzManager();
		for (Long id : ids) {
			try {
				SysTask scheduleJob = get(id);
				quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			} catch (SchedulerException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskScheduleJobMapper.batchRemove(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		QuartzManager quartzManager = new QuartzManager();
		List<SysTask> jobList = taskScheduleJobMapper.list(new HashMap<String,Object>());
		for (SysTask scheduleJob : jobList) {
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
	}
	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		QuartzManager quartzManager = new QuartzManager();
		SysTask scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
		} else if ("start".equals(cmd)) {
			scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 update(scheduleJob);
	}
	
	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		QuartzManager quartzManager = new QuartzManager();
		SysTask scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 update(scheduleJob);
	}

}
