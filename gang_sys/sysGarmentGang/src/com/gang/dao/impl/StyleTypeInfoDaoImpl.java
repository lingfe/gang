package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.StyleTypeInfoDao;
import com.gang.entity.StyleTypeInfo;

@Repository
public class StyleTypeInfoDaoImpl implements StyleTypeInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<StyleTypeInfo> getInfoList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from styletypeinfo where isDisplay='是' ");
		//排序
		if(true) {
			sql.append(" order by  mdate desc ");
		}
		//sql.append(" limit ?, ?");
		return jdbcTemplate.query(sql.toString(), new Object[] {  }, new BeanPropertyRowMapper<StyleTypeInfo>(StyleTypeInfo.class));
	
	}

}
