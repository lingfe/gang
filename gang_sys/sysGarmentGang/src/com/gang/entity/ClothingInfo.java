package com.gang.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 服装信息表
 * @author lingfe
 * @date 2018.04.16
 */
public class ClothingInfo {
	
	/**
	 * 封面图
	 */
	private String imgCover;
	
	public String getImgCover() {
		return imgCover;
	}
	public void setImgCover(String imgCover) {
		this.imgCover = imgCover;
	}
	/**
	 * 标签
	 */
	private String lable;
	/**
	 * 材质说明
	 */
	private String material;
	/**
	 * 备注
	 */
	private String remark;
	
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStyleTypeInfoId() {
		return styleTypeInfoId;
	}
	public void setStyleTypeInfoId(String styleTypeInfoId) {
		this.styleTypeInfoId = styleTypeInfoId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getClothingName() {
		return clothingName;
	}
	public void setClothingName(String clothingName) {
		this.clothingName = clothingName;
	}
	public String getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getTagPrice() {
		return tagPrice;
	}
	public void setTagPrice(String tagPrice) {
		this.tagPrice = tagPrice;
	}
	public String getImgArray() {
		return imgArray;
	}
	public void setImgArray(String imgArray) {
		this.imgArray = imgArray;
	}
	public String getInfoImgArray() {
		return infoImgArray;
	}
	public void setInfoImgArray(String infoImgArray) {
		this.infoImgArray = infoImgArray;
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
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '服装信息表id标识',
	private String styleTypeInfoId;//	  `styletypeinfoId` VARCHAR(64)  NULL COMMENT '服装款式分类信息id',
	private String styleName;//	  `styleName` VARCHAR(32) NOT NULL COMMENT '款式名称',
	private String clothingName;//	  `clothingName` VARCHAR(255) NOT NULL COMMENT '服装名称',
	private String salesPrice;//	  `salesPrice` VARCHAR(32) DEFAULT NULL COMMENT '销售价',
	private String tagPrice;//	  `tagPrice` VARCHAR(32) DEFAULT NULL COMMENT '吊牌价',
	private String imgArray;//	  `imgArray` TEXT COMMENT '轮播图',
	private String infoImgArray;//	  `infoImgArray` TEXT COMMENT '服装信息详细图',
	private String isDisplay;//`isDisplay` VARCHAR(32) DEFAULT '是' COMMENT '是否显示',
	private Integer state;//	  `state` INT(11) DEFAULT '0' COMMENT '状态,0..',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate;//	  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
	private String version;//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
}
