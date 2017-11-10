package com.bamboo.common.base;

import java.io.Serializable;
import java.time.Clock;
import java.util.Date;

import com.bamboo.common.utils.ShiroUtils;
import com.bamboo.sys.domain.User;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5113256345780800235L;

	private Long id;
	
	private String createBy;			//创建者
	
    private Date createTime;			//创建时间
    
    private String modifyBy;			//更新者
    
    private Date modifyTime;			//更新时间
    
    /**
     * 执行新增操作之前需要执行的步骤
     * 在Service类里，调用mapper接口的insert方法以前主动调用
     */
    public void preInsert () {
    	User user = ShiroUtils.getUser();
        if (user != null) {
            this.createBy = user.getName();
            this.modifyBy = user.getName();
        }

        Date now = Date.from(Clock.systemDefaultZone().instant());
        this.setCreateTime(now);
        this.setModifyTime(now);
    }
    /**
     * 执行修改操作之前需要执行的步骤
     * 在Service类里，调用mapper接口的update方法以前主动调用
     */
    public void preUpadate () {
    	User user = ShiroUtils.getUser();
        if (user != null) {
        	this.modifyBy = user.getName();
        }
        Date now = Date.from(Clock.systemDefaultZone().instant());
        this.setModifyTime(now);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
