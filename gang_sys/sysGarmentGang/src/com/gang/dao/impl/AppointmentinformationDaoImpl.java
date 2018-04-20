package com.gang.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.AppointmentinformationDao;
import com.gang.entity.Appointmentinformation;

@Repository
public class AppointmentinformationDaoImpl implements AppointmentinformationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(Appointmentinformation info) {
		String sql = "INSERT  INTO `appointmentinformation`"
				+ "(`id`,`fullName`,`phone`,`region`,`address`,"
				+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
				+ " values (?,?,?,?,?,"
				+ "?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { info.getId(),info.getFullName(),info.getPhone(),info.getRegion(),info.getAddress(),
				info.getState(),info.getCdate(),info.getMdate(),info.getCreator(),info.getModify(),info.getVersion()});
	}

}
