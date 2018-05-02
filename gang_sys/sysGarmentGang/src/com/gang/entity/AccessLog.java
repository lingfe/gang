package com.gang.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 访问日志表
 * @author 13068	lingfe
 * @date 2018.04.25
 *
 */
public class AccessLog {
	
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
	public String getAccessModel() {
		return accessModel;
	}
	public void setAccessModel(String accessModel) {
		this.accessModel = accessModel;
	}
	public String getAccessOperationType() {
		return accessOperationType;
	}
	public void setAccessOperationType(String accessOperationType) {
		this.accessOperationType = accessOperationType;
	}
	public String getAccessAbnormal() {
		return accessAbnormal;
	}
	public void setAccessAbnormal(String accessAbnormal) {
		this.accessAbnormal = accessAbnormal;
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
	private String id;//	id VARCHAR(64) NOT NULL COMMENT '访问日志表id标识',
	private String ip;//	ip VARCHAR(64) DEFAULT NULL COMMENT '访问ip',
	private String accessModel;//	accessModel TEXT DEFAULT NULL COMMENT '访问模块',
	private String accessOperationType;//	accessOperationType TEXT DEFAULT NULL COMMENT '访问操作类型',
	private String accessAbnormal;//	accessAbnormal TEXT DEFAULT NULL COMMENT '访问异常',
	private Integer state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
}
