package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.ClothingInfoDao;
import com.gang.entity.ClothingInfo;
import com.gang.pojo.SelectWhere;

@Repository
public class ClothingInfoDaoImpl implements ClothingInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ClothingInfo> getClothingInfoWhereId(SelectWhere where,String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from clothingInfo where 1=1 ");
		//验证条件非空
		if(where.getSearchKey()!=null&&!"".equals(where.getSearchKey())){
			sql.append(" and id LIKE '%").append(where.getSearchKey()).append("%'");
			sql.append(" or styleName='").append(where.getSearchKey()).append("'");
			sql.append(" or lable='").append(where.getSearchKey()).append("'");
			sql.append(" or clothingName like '%").append(where.getSearchKey()).append("%'");
			sql.append(" or material like '%").append(where.getSearchKey()).append("%'");
		}
		if(where.getEndTime()!=null&&!"".equals(where.getStartTime())&&
				where.getStartTime()!=null&&!"".equals(where.getEndTime())){
			sql.append(" and cdate BETWEEN '").append(where.getStartTime()).append("' ");
			sql.append(" AND '").append(where.getEndTime()).append("' ");
		}
		//条件
		if(id!=null){
			sql.append(" where id = '").append(id).append("' ");
		}
		//条件追加,分页查询
		if(where != null){
			if(where.getPageIndex() !=null && where.getPageNum()!=null){
				sql.append(" limit ").append((where.getPageIndex()-1)*where.getPageNum()).append(",").append(where.getPageNum());
			}
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
