package com.gang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.UserInfoDao;
import com.gang.entity.UserInfo;
import com.gang.pojo.SelectWhere;
import com.gang.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public void updateLastTime(Date date, String id) {
		userInfoDao.updateLastTime(date, id);
	}
	
	@Override
	public List<UserInfo> getUserInfo(SelectWhere where) {
		return userInfoDao.getUserInfo(where);
	}

	@Override
	public UserInfo loginSelect(String userName, String pwd) {
		return userInfoDao.loginSelect(userName, pwd);
	}

}
