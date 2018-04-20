package com.gang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.StyleTypeInfoDao;
import com.gang.entity.StyleTypeInfo;
import com.gang.service.StyleTypeInfoService;

@Service
public class StyleTypeInfoServiceImpl implements StyleTypeInfoService {

	@Autowired
	private StyleTypeInfoDao styleTypeInfoDao;
	
	@Override
	public List<StyleTypeInfo> getInfoList() {
		return styleTypeInfoDao.getInfoList();
	}

}
