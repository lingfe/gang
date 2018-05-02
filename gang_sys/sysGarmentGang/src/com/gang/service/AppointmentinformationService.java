package com.gang.service;

import java.util.List;

import com.gang.entity.Appointmentinformation;
import com.gang.pojo.SelectWhere;

/**
 * 上门预约，业务逻辑层
 * @author lingfe
 * @date 2018.04.18
 *
 */
public interface AppointmentinformationService {
	
	/**
	 * 根据id删除
	 * @param id	id主键
	 * @author 13068	lingfe
	 * @return	结果
	 */
	public int deleteWhereId(String id);
	
	/**
	 * 分页查询所有预约数据
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @author 13068	lingfe
	 * @return 数据集合
	 */
	public List<Appointmentinformation> getInfoList(SelectWhere where,Integer pageIndex,Integer pageNum);

	/**
	 * 添加一条上门预约信息
	 * @param info	实体
	 * @author lingfe
	 * @return 结果
	 */
	public int add(Appointmentinformation info);
}