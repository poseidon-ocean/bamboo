package com.bamboo.common.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有服务类的超级父类
 */
public abstract class BaseService <D extends BaseMapper <T>, T extends BaseEntity> {

    @Autowired
    protected D mapper;
    
    public boolean insert (T entity){
    	entity.preInsert();
        return mapper.create(entity) > 0;
    }
    
    public boolean update (T entity){
    	entity.preUpadate();
        return mapper.update(entity) > 0;
    }

    public T queryById (Long id){
        return mapper.queryById(id);
    }

    /**
     * 查询列表
     * @param entity
     * @return
     */
    public List<T> queryList (T entity){
        return mapper.queryList(entity);
    }

    /**
     * 删除数据
     * @param entity
     * @return
     */
    public boolean delete (Long id){
        int res = mapper.remove(id);
        return res > 0;
    }
    
    /**
     * 按条件查询列表信息
     * @param map
     * @return
     */
    public List<T> list(Map<String, Object> map){
    	return mapper.list(map);
    }
    
    /**
     * 按条件统计数量
     * @param map
     * @return
     */
    public int count(Map<String, Object> map) {
		return mapper.count(map);
	}
    
}
