package com.gang.service;

import com.gang.entity.Appointmentinformation;

/**
 * 上门预约，业务逻辑层
 * @author lingfe
 * @date 2018.04.18
 *
 */
public interface AppointmentinformationService {

	/**
	 * 添加一条上门预约信息
	 * @param info	实体
	 * @author lingfe
	 * @return 结果
	 */
	public int add(Appointmentinformation info);
}