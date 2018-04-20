package com.gang.entity;

import java.util.Date;

/**
 * 服装款式分类信息表
 * @author lingfe
 * @date 2018.04.16
 */
public class StyleTypeInfo {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getSuperiorId() {
		return superiorId;
	}
	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
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
	private String id;//`id` VARCHAR(64) NOT NULL COMMENT '服装款式分类信息表id标识',
	private String styleName;//	  `styleName` VARCHAR(32) NOT NULL COMMENT '款式名称',
	private String superiorId;//	  `superiorId` VARCHAR(64) DEFAULT NULL COMMENT '上级id',
	private String isDisplay;//	  `isDisplay` VARCHAR(32) DEFAULT '是' COMMENT '是否显示',
	private Integer state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
}
