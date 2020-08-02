package com.gang.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 预约上门信息表
 * @author lingfe
 * @date 2018.04.16
 */
public class Appointmentinformation {
	
	private String yqPhone;//邀请人电话号码
	
	public String getYqPhone() {
		return yqPhone;
	}
	public void setYqPhone(String yqPhone) {
		this.yqPhone = yqPhone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '预约上门信息表id标识',
	private String fullName;//	  `fullName` VARCHAR(32) NOT NULL COMMENT '姓名',
	private String phone;//	  `phone` VARCHAR(32) NOT NULL COMMENT '电话号码',
	private String region;//|	  `region` VARCHAR(32) NOT NULL COMMENT '地区',
	private String address;//	  `address` VARCHAR(64) NOT NULL COMMENT '详细地址',
	private Integer state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
}
