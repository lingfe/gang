package com.gang.dao;

import java.util.List;

import com.gang.entity.ClothingInfo;

/**
 * 服装信息，数据访问层
 * @author lingfe
 * 
 */
public interface ClothingInfoDao {

	/**
	 * 根据id获取服装信息，如果id为空则获取所有
	 * @param id 服装信息id
	 * @author lingfe
	 * @return 数据集合
	 */
	public List<ClothingInfo> getClothingInfoWhereId(String id);

	/**
	 * 根据服装款式类型查询服装信息
	 * @param styletypeinfoId	服装款式类型id
	 * @param styleName			服装款式类型名称
	 * @author lingfe
	 * @return 数据集合
	 */
	public List<ClothingInfo> getClothingInfoList(String styletypeinfoId,String styleName);
	
}
