package com.bamboo.common.mapper;

import com.bamboo.common.base.BaseMapper;
import com.bamboo.common.domain.SysLog;


import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 */
@Mapper
public interface LogMapper extends BaseMapper<SysLog>{

	
}
