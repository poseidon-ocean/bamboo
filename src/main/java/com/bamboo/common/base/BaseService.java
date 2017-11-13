package com.bamboo.common.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有服务类的超级父类
 */
public abstract class BaseService <D extends BaseMapper <T>, T extends BaseEntity> {

    @Autowired
    protected D mapper;
    
    public boolean insert (T entity){
        return mapper.create(entity) > 0;
    }
    
    public boolean update (T entity){
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
    public List<T> queryList (T entity) throws Exception{
        return mapper.queryList(entity);
    }

    /**
     * 删除数据
     * @param entity
     * @return
     */
    public boolean delete (Long id) throws Exception{
        int res = mapper.remove(id);
        return res > 0;
    }
}
