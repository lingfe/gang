package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.ClothingInfoDao;
import com.gang.entity.ClothingInfo;

@Repository
public class ClothingInfoDaoImpl implements ClothingInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ClothingInfo> getClothingInfoWhereId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from clothingInfo  ");
		//条件
		if(id!=null){
			sql.append(" where id = '").append(id).append("' ");
		}
		return jdbcTemplate.query(sql.toString(), new Object[] {  }, new BeanPropertyRowMapper<ClothingInfo>(ClothingInfo.class));
	}
	
	@Override
	public List<ClothingInfo> getClothingInfoList(String styletypeinfoId, String styleName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from clothingInfo where isDisplay='是' ");
		//条件
		if(styletypeinfoId!=null){
			sql.append(" and styletypeinfoId = '").append(styletypeinfoId).append("' ");
		}else if(styleName !=null){
			sql.append(" and styleName = '").append(styleName).append("' ");
		}
		//排序
		if(true) {
			sql.append(" order by  cdate desc ");
		}
		//sql.append(" limit ?, ?");
		return jdbcTemplate.query(sql.toString(), new Object[] {  }, new BeanPropertyRowMapper<ClothingInfo>(ClothingInfo.class));
	}

}
