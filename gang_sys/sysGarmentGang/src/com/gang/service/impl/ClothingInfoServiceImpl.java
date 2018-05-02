package com.gang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.ClothingInfoDao;
import com.gang.entity.ClothingInfo;
import com.gang.pojo.SelectWhere;
import com.gang.service.ClothingInfoService;

@Service
public class ClothingInfoServiceImpl implements ClothingInfoService {
	
	@Autowired
	private ClothingInfoDao clothingInfoDao;

	@Override
	public List<ClothingInfo> getClothingInfoList(String styletypeinfoId, String styleName) {
		return clothingInfoDao.getClothingInfoList(styletypeinfoId, styleName);
	}

	@Override
	public List<ClothingInfo> getClothingInfoWhereId(SelectWhere where,String id) {
		return clothingInfoDao.getClothingInfoWhereId(where,id);
	}

	@Override
	public int addClothingInfo(ClothingInfo info) {
		return clothingInfoDao.addClothingInfo(info);
	}

	@Override
	public int updateIsDisplay(String id, String isDisplay) {
		return clothingInfoDao.updateIsDisplay(id, isDisplay);
	}

	@Override
	public int deleteWhereId(String id) {
		return clothingInfoDao.deleteWhereId(id);
	}


}
