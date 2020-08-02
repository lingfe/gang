package com.gang.dao;

import java.util.Date;
import java.util.List;

import com.gang.entity.UserInfo;
import com.gang.pojo.SelectWhere;

/**
 * 用户信息数据访问接口
 * @author 13068 lingfe
 * @date 2018.04.25
 */
public interface UserInfoDao {
	
	/**
	 * 移动端用户注册
	 * @param user	用户注册数据
	 * @return	结果
	 * @author 13068、	lingfe
	 */
	public int add(UserInfo user);
	
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
