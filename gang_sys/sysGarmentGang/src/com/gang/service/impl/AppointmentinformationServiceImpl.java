package com.gang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.AppointmentinformationDao;
import com.gang.entity.Appointmentinformation;
import com.gang.pojo.SelectWhere;
import com.gang.service.AppointmentinformationService;

@Service
public class AppointmentinformationServiceImpl implements AppointmentinformationService {

	@Autowired
	private AppointmentinformationDao appointmentinformationDao;
	
	@Override
	public int deleteWhereId(String id) {
		return appointmentinformationDao.deleteWhereId(id);
	}
	
	@Override
	public int add(Appointmentinformation info) {
		return appointmentinformationDao.add(info);
	}

	@Override
	public List<Appointmentinformation> getInfoList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		return appointmentinformationDao.getInfoList(where,pageIndex, pageNum);
	}

}
