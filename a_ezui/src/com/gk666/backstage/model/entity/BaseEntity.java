package com.gk666.backstage.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@Column(name="g_id",unique=true,nullable=false)
	private String id;		//PK
	
	@Column(name = "g_create_time")
	private Date createTime = new Date();	//创建时间 
	
	@Column(name = "g_last_update_time")
	private Date lastUpdateTime;	//最后修改时间  刷新当前时间戳 
	
	@Column(name = "g_create_user")
	private String createUser;
	
	@Column(name = "g_last_update_user")
	private String lastUpdateUser;
	
	@Column(name = "g_state")
	private int state = 0;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
