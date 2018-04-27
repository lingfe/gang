package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.LogDao;
import com.gang.entity.AccessLog;
import com.gang.entity.SysLog;
import com.gang.pojo.SelectWhere;

@Repository
public class LogDaoImpl implements LogDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addAccessLogInfo(AccessLog log) {
		String sql="INSERT  INTO `accesslog` "
				+ "(`id`,`ip`,`accessModel`,"
				+ "`accessOperationType`,`accessAbnormal`,"
				+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
				+ "VALUES "
				+ "(?,?,?,"
				+ "?,?,"
				+ "?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { 
				log.getId(),log.getIp(),log.getAccessModel(),
				log.getAccessOperationType(),log.getAccessAbnormal(),
				log.getState(),log.getCdate(),log.getMdate(),log.getCreator(),log.getModify(),log.getVersion()
		});
	
	}
	
	@Override
	public int addSysLog(SysLog log) {
		String sql="INSERT  INTO `syslog`"
				+ "(`id`,`ip`,`userName`,"
				+ "`model`,`operationType`,`abnormal`,"
				+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
				+ "VALUES "
				+ "(?,?,?,"
				+ "?,?,?,"
				+ "?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { 
				log.getId(),log.getIp(),log.getUserName(),
				log.getModel(),log.getOperationType(),log.getAbnormal(),
				log.getState(),log.getCdate(),log.getMdate(),log.getCreator(),log.getModify(),log.getVersion()
		});
	}

	@Override
	public List<SysLog> getSysLogList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from sysLog ");
		//验证条件非空
		if(where.getEndTime()!=null&&where.getStartTime()!=null){
			sql.append(" where ");
			sql.append(" cdate BETWEEN '").append(where.getStartTime()).append("' ");
			sql.append(" AND '").append(where.getEndTime()).append("' ");
		}
		//排序
		sql.append(" ORDER BY cdate DESC ");
		//条件追加,分页查询
		if(pageIndex !=null && pageNum!=null){
			sql.append(" limit ").append((pageIndex-1)*pageNum).append(",").append(pageNum);
		}
		
		List<SysLog> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysLog>(SysLog.class));
		return  list;
	}

	@Override
	public List<AccessLog> getAccessLogList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from AccessLog ");
		//验证条件非空
		if(where.getEndTime()!=null&&where.getStartTime()!=null){
			sql.append(" where ");
			sql.append(" cdate BETWEEN '").append(where.getStartTime()).append("' ");
			sql.append(" AND '").append(where.getEndTime()).append("' ");
		}
		//排序
		sql.append(" ORDER BY cdate DESC ");
		
		//条件追加,分页查询
		if(pageIndex !=null && pageNum!=null){
			sql.append(" limit ").append((pageIndex-1)*pageNum).append(",").append(pageNum);
		}
		
		List<AccessLog> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AccessLog>(AccessLog.class));
		return  list;
	}

}
