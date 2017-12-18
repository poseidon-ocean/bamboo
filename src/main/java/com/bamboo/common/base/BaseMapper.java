package com.bamboo.common.base;

import java.util.List;
import java.util.Map;

/**
 * 所有数据库持久化操作超类
 *
 */
public interface BaseMapper<T> {

	/**
     * 查询列表
     * @param entity
     * @return
     */
    List<T> queryList (T entity);
    
    /**
     * 查询列表 
     * @param map
     * @return
     */
    List<T> list(Map<String,Object> map);
    
    /**
     * 统计
     * @param map
     * @return
     */
    int count(Map<String,Object> map);

    /**
     * 根据ID查询数据
     * @param id 实体类的ID
     * @return
     */
    T queryById (Long id);


    /**
     * 执行插入操作
     * @param entity
     * @return
     */
    int create(T entity);
    
    /**
     * 执行更新操作
     * @param entity
     * @return
     */
    int update (T entity);

    /**
     * 执行删除操作
     * @param entity
     * @return
     */
    int remove (Long id);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchRemove(Long[] ids);
}
