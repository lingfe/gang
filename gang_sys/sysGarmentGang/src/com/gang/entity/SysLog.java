package com.gang.entity;

import java.util.Date;

/**
 * 系统日志表
 * @author 13068	lingfe
 * @date 2018.04.25
 */
public class SysLog {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(String abnormal) {
		this.abnormal = abnormal;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	private String id;//	id VARCHAR(64) NOT NULL COMMENT '系统日志表id标识',
	private String ip;//	ip VARCHAR(64) DEFAULT NULL COMMENT '来源id',
	private String userName;//	userName VARCHAR(64) DEFAULT '游客' COMMENT '用户名',
	private String model;//	model TEXT  DEFAULT NULL COMMENT '模块',
	private String operationType;//	operationType TEXT DEFAULT NULL COMMENT '操作类型',
	private String abnormal;//	abnormal TEXT DEFAULT NULL COMMENT '异常',
	private Integer state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
}
