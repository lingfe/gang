package com.gang.service;

import java.util.List;

import com.gang.entity.AccessLog;
import com.gang.entity.SysLog;
import com.gang.pojo.SelectWhere;


/**
 * 日志信息，业务逻辑层
 * @author 13068	lingfe
 * @date 2018.04.26
 *
 */
public interface LogService{
	
	/**
	 * 添加一条系统日志
	 * @param log	实体
	 * @author 13068	lingfe
	 * @return	结果
	 */
	public int addSysLog(SysLog log);
	
	/**
	 * 添加一条访问日志
	 * @param log	实体
	 * @author 13068	lingfe
	 * @return 结果
	 */
	public int addAccessLogInfo(AccessLog log);
	
	/**
	 * 获取系统日志信息,参数如果为空则获取所有
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @author 13068 lingfe
	 * @return 数据集合
	 */
	public List<SysLog> getSysLogList(SelectWhere where,Integer pageIndex,Integer pageNum);

	/**
	 * 获取访问日志信息，参数如果为空就获取所有
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @author 13068	lingfe
	 * @return 数据集合
	 */
	public List<AccessLog> getAccessLogList(SelectWhere where,Integer pageIndex,Integer pageNum);
}
