package com.gang.dao;

import com.gang.entity.Appointmentinformation;

/**
 * 上门预约，数据访问层
 * @author lingfe
 * @date 2018.04.18
 *
 */
public interface AppointmentinformationDao {

	/**
	 * 添加一条上门预约信息
	 * @param info	实体
	 * @author lingfe
	 * @return 结果
	 */
	public int add(Appointmentinformation info);
}
