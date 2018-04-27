package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.AppointmentinformationDao;
import com.gang.entity.Appointmentinformation;
import com.gang.pojo.SelectWhere;

@Repository
public class AppointmentinformationDaoImpl implements AppointmentinformationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Appointmentinformation> getInfoList(SelectWhere where,Integer pageIndex, Integer pageNum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from appointmentinformation where 1=1 ");
		
		//验证条件非空
		if(where.getSearchKey()!=null&&!"".equals(where.getSearchKey())){
			sql.append(" and id LIKE '%").append(where.getSearchKey()).append("%'");
			sql.append(" or fullName='").append(where.getSearchKey()).append("'");
			sql.append(" or phone='").append(where.getSearchKey()).append("'");
			sql.append(" or region like '%").append(where.getSearchKey()).append("%'");
		}
		if(where.getEndTime()!=null&&!"".equals(where.getStartTime())&&
				where.getStartTime()!=null&&!"".equals(where.getEndTime())){
			sql.append(" and cdate BETWEEN '").append(where.getStartTime()).append("' ");
			sql.append(" AND '").append(where.getEndTime()).append("' ");
		}
		
		//排序
		sql.append(" ORDER BY cdate DESC ");
		
		//条件追加,分页查询
		if(pageIndex !=null && pageNum!=null){
			sql.append(" limit ").append((pageIndex-1)*pageNum).append(",").append(pageNum);
		}
		
		List<Appointmentinformation> list = jdbcTemplate.query(sql.toString(), new Object[]{}, new BeanPropertyRowMapper<Appointmentinformation>(Appointmentinformation.class));
		return  list;
	}
	
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
