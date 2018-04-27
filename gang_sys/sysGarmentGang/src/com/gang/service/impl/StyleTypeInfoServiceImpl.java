package com.gang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.StyleTypeInfoDao;
import com.gang.entity.StyleTypeInfo;
import com.gang.pojo.SelectWhere;
import com.gang.service.StyleTypeInfoService;

@Service
public class StyleTypeInfoServiceImpl implements StyleTypeInfoService {

	@Autowired
	private StyleTypeInfoDao styleTypeInfoDao;
	
	@Override
	public int addStyleInfo(StyleTypeInfo info) {
		return styleTypeInfoDao.addStyleInfo(info);
	}
	
	@Override
	public int updateIsDisplay(String id, String isDisplay) {
		return styleTypeInfoDao.updateIsDisplay(id, isDisplay);
	}
	
	@Override
	public int deleteWhereId(String id) {
		return styleTypeInfoDao.deleteWhereId(id);
	}
	
	@Override
	public List<StyleTypeInfo> getInfoList(SelectWhere where) {
		return styleTypeInfoDao.getInfoList(where);
	}

}
