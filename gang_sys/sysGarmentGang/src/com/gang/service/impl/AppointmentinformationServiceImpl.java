package com.gang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.AppointmentinformationDao;
import com.gang.entity.Appointmentinformation;
import com.gang.service.AppointmentinformationService;

@Service
public class AppointmentinformationServiceImpl implements AppointmentinformationService {

	@Autowired
	private AppointmentinformationDao appointmentinformationDao;
	
	@Override
	public int add(Appointmentinformation info) {
		return appointmentinformationDao.add(info);
	}

}
