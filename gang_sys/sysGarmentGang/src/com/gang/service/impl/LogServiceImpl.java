package com.gang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gang.dao.LogDao;
import com.gang.entity.AccessLog;
import com.gang.entity.SysLog;
import com.gang.pojo.SelectWhere;
import com.gang.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public int addSysLog(SysLog log) {
		//赋值
		log.setId(UUID.randomUUID().toString());
		log.setCdate(new Date());
		log.setMdate(log.getCdate());
		log.setVersion("0");
		log.setState(0);
		log.setModify("无");

		return logDao.addSysLog(log);
	}

	@Override
	public int addAccessLogInfo(AccessLog log) {
		//赋值
		log.setId(UUID.randomUUID().toString());
		log.setCdate(new Date());
		log.setMdate(log.getCdate());
		log.setVersion("0");
		log.setState(0);
		log.setModify("无");

		return logDao.addAccessLogInfo(log);
	}

	@Override
	public List<SysLog> getSysLogList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		return logDao.getSysLogList(where,pageIndex, pageNum);
	}

	@Override
	public List<AccessLog> getAccessLogList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		return logDao.getAccessLogList(where,pageIndex, pageNum);
	}

}
