package com.gang.dao;

import java.util.List;

import com.gang.entity.StyleTypeInfo;
import com.gang.pojo.SelectWhere;

/**
 * 服装款式类型信息，数据访问层
 * @author lingfe
 * @date 2018.04.16
 */
public interface StyleTypeInfoDao {
	
	/**
	 * 添加服装款式类型
	 * @param info	实体
	 * @author 13068	lingfe
	 * @return 结果
	 */
	public int addStyleInfo(StyleTypeInfo info);
	
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
	 * 获取所有服装款式分类信息
	 * @return 数据
	 */
	public List<StyleTypeInfo> getInfoList(SelectWhere where);
}
