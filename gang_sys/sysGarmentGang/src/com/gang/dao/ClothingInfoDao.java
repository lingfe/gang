package com.gang.dao;

import java.util.List;

import com.gang.entity.ClothingInfo;
import com.gang.pojo.SelectWhere;

/**
 * 服装信息，数据访问层
 * @author lingfe
 * 
 */
public interface ClothingInfoDao {
	
	/**
	 * 添加一条服装信息
	 * @param info	实体
	 * @author 13068	lingfe
	 * @return	数据
	 */
	public int addClothingInfo(ClothingInfo info);
	
	/**
	 * 根据id修改为隐藏或显示
	 * @param id		id主键
	 * @param isDisplay	是否显示
	 * @author 13068	lingfe
	 * @return 结果
	 */
	public int updateIsDisplay(String id,String isDisplay);
	
	/**
	 * 根据id删除
	 * @param id	id主键
	 * @author 13068	lingfe
	 * @return	结果
	 */
	public int deleteWhereId(String id);

	/**
	 * 根据id获取服装信息，如果id为空则获取所有
	 * @param id 服装信息id
	 * @author lingfe
	 * @return 数据集合
	 */
	public List<ClothingInfo> getClothingInfoWhereId(SelectWhere where,String id);

	/**
	 * 根据服装款式类型查询服装信息
	 * @param styletypeinfoId	服装款式类型id
	 * @param styleName			服装款式类型名称
	 * @author lingfe
	 * @return 数据集合
	 */
	public List<ClothingInfo> getClothingInfoList(String styletypeinfoId,String styleName);
	
}
