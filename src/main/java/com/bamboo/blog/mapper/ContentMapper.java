package com.bamboo.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bamboo.blog.domain.BlogContent;
import com.bamboo.common.base.BaseMapper;

/**
 * 文章内容
 */
@Mapper
public interface ContentMapper extends BaseMapper<BlogContent> {

}
