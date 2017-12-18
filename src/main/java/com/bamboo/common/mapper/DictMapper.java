package com.bamboo.common.mapper;

import com.bamboo.common.base.BaseMapper;
import com.bamboo.common.domain.Dict;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * 
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict>{

	List<Dict> listType();
}
