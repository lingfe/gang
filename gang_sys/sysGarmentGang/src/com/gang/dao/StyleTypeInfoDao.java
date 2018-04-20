package com.gang.dao;

import java.util.List;

import com.gang.entity.StyleTypeInfo;

/**
 * 服装款式类型信息，数据访问层
 * @author lingfe
 * @date 2018.04.16
 */
public interface StyleTypeInfoDao {

	/**
	 * 获取所有服装款式分类信息
	 * @return 数据
	 */
	public List<StyleTypeInfo> getInfoList();
}
