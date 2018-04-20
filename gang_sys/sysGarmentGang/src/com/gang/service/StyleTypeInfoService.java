package com.gang.service;

import java.util.List;

import com.gang.entity.StyleTypeInfo;

/**
 * 服装款式类型信息，业务逻辑层
 * @author lingfe
 * @date 2018.04.16
 */
public interface StyleTypeInfoService {
	
	/**
	 * 获取所有服装款式分类信息
	 * @return 数据
	 */
	public List<StyleTypeInfo> getInfoList();
}
