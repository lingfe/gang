package com.gang.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表
 * @author lingfe
 * @date 2018.04.16
 */
public class UserInfo {
	
	private Date lastTime;
	
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	private String id;//`id` VARCHAR(64) NOT NULL COMMENT '用户信息表id标识',
	private String appId;//	  `appId` VARCHAR(32) DEFAULT NULL COMMENT 'appid',
	private String userName;//	  `username` VARCHAR(32) NOT NULL COMMENT '用户登录名',
	private String realname;//	  `realname` VARCHAR(32) NOT NULL COMMENT '真实姓名',
	private String avatarUrl;//	  `avatarUrl` VARCHAR(255) DEFAULT NULL COMMENT '头像',
	private String ename;//	  `ename` VARCHAR(32) DEFAULT NULL COMMENT '英文名',
	private String pwd;//	  `pwd` VARCHAR(32) DEFAULT NULL COMMENT '登录密码',
	private String token;//	  `token` VARCHAR(255) DEFAULT NULL COMMENT '第三方token',
	private String tel;//	  `tel` VARCHAR(32) DEFAULT NULL COMMENT '电话',
	private String fax;//	  `fax` VARCHAR(32) DEFAULT NULL COMMENT '传真',
	private  String email;//	  `email` VARCHAR(32) DEFAULT NULL COMMENT '邮件',
	private String mobile;//	  `mobile` VARCHAR(32) DEFAULT NULL COMMENT '移动电话号码',
	private String qq;//Z	  `qq` VARCHAR(32) DEFAULT NULL COMMENT 'QQ',
	private Double balance;//	  `balance` DOUBLE DEFAULT '0' COMMENT '余额',
	private Integer state;//	  `state` INT(11) DEFAULT NULL COMMENT '账号状态：0未启用，1正常，2、异常，3冻结',
	private String idCard;//	  `idCard` VARCHAR(255) DEFAULT NULL COMMENT '身份证号码',
	private	String provinceCode;//	  `provinceCode` VARCHAR(32) DEFAULT NULL COMMENT '省',
	private String provinceName;//	  `provinceName` VARCHAR(64) DEFAULT NULL COMMENT '省',
	private String cityCode;//|	  `cityCode` VARCHAR(32) DEFAULT NULL COMMENT '市',
	private String cityName;//|	  `cityName` VARCHAR(64) DEFAULT NULL COMMENT '市',
	private String regionCode;//	  `regionCode` VARCHAR(32) DEFAULT NULL COMMENT '区',
	private String regionName;//	  `regionName` VARCHAR(64) DEFAULT NULL COMMENT '区',
	private String address;//	  `address` VARCHAR(256) DEFAULT NULL COMMENT '详细地址',
	private String remark;//|	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	public String msg;
	public int status;

}
