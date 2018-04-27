package com.gang.service;

import java.util.Date;
import java.util.List;

import com.gang.entity.UserInfo;
import com.gang.pojo.SelectWhere;

/**
 * 用户信息业务逻辑层
 * @author 13068	lingfe
 * @date 2018.04.25
 */
public interface UserInfoService {
	
	/**
	 * 获取所有用户数据
	 * @author 13068	lingfe
	 * @return	数据
	 */
	public List<UserInfo> getUserInfo(SelectWhere where);
	
	/**
	 * 修改最后一次登陆时间
	 * @param date	登陆时间
	 * @param id	用户id
	 * @author 13068	lingfe
	 */
	public void updateLastTime(Date date,String id);
	
	/**
	 * 用户登陆查询
	 * @param userName	用户名
	 * @param pwd		登陆密码
	 * @author 13068	lingfe
	 * @return
	 */
	public UserInfo loginSelect(String userName,String pwd);
}
