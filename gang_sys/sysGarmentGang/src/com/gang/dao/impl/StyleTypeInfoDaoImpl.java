package com.gang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.StyleTypeInfoDao;
import com.gang.entity.StyleTypeInfo;
import com.gang.pojo.SelectWhere;

@Repository
public class StyleTypeInfoDaoImpl implements StyleTypeInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addStyleInfo(StyleTypeInfo info) {
		StringBuffer sql=new StringBuffer();
		sql.append("INSERT  INTO ");
		sql.append("`styletypeinfo`(`id`,`styleName`,`superiorId`,`isDisplay`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) ");
		sql.append("VALUES ");
		sql.append("(?,?,?,?,?,?,?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), new Object[] { info.getId(),info.getStyleName(),info.getSuperiorId(),
				info.getIsDisplay(),info.getState(),info.getCdate(),info.getMdate(),
				info.getCreator(),info.getModify(),info.getVersion()});
	}
	
	@Override
	public int updateIsDisplay(String id, String isDisplay) {
		String sql="update styletypeinfo set isDisplay=? where id=?";
		return jdbcTemplate.update(sql, new Object[] { isDisplay,id});
	}
	
	@Override
	public int deleteWhereId(String id) {
		String sql="delete from styletypeinfo where id=?";
		return jdbcTemplate.update(sql, new Object[] { id});
	}
	
	@Override
	public List<StyleTypeInfo> getInfoList(SelectWhere where) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from styletypeinfo where 1=1 ");
		
		//验证条件非空
		if(where != null){
			if(where.getSearchKey()!=null&&!"".equals(where.getSearchKey())){
				sql.append(" and id LIKE '%").append(where.getSearchKey()).append("%'");
				sql.append(" or styleName='").append(where.getSearchKey()).append("'");
			}
			if(where.getEndTime()!=null&&!"".equals(where.getStartTime())&&
					where.getStartTime()!=null&&!"".equals(where.getEndTime())){
				sql.append(" and cdate BETWEEN '").append(where.getStartTime()).append("' ");
				sql.append(" AND '").append(where.getEndTime()).append("' ");
			}
		}else {
			sql.append(" and isDisplay='是' ");
		}

		//排序
		if(true) {
			sql.append(" order by  mdate desc ");
		}
		//条件追加,分页查询
		if(where != null){
			if(where.getPageIndex() !=null && where.getPageNum()!=null){
				sql.append(" limit ").append((where.getPageIndex()-1)*where.getPageNum()).append(",").append(where.getPageNum());
			}
		}
		return jdbcTemplate.query(sql.toString(), new Object[] {  }, new BeanPropertyRowMapper<StyleTypeInfo>(StyleTypeInfo.class));
	}

}
