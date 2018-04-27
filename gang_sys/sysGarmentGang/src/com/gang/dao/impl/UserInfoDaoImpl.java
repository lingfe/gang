package com.gang.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gang.dao.UserInfoDao;
import com.gang.entity.UserInfo;
import com.gang.pojo.SelectWhere;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void updateLastTime(Date date, String id) {
		String sql = "update userinfo set lastTime = ? where id = ? ";
		jdbcTemplate.update(sql, new Object[] { date, id });
	}

	@Override
	public List<UserInfo> getUserInfo(SelectWhere where) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from userinfo where 1=1 ");
		// 验证条件非空
		if (where != null) {
			if (where.getSearchKey() != null && !"".equals(where.getSearchKey())) {
				sql.append(" and id LIKE '%").append(where.getSearchKey()).append("%'");
				sql.append(" or userName='").append(where.getSearchKey()).append("'");
			}
			if (where.getEndTime() != null && !"".equals(where.getStartTime()) && where.getStartTime() != null
					&& !"".equals(where.getEndTime())) {
				sql.append(" and cdate BETWEEN '").append(where.getStartTime()).append("' ");
				sql.append(" AND '").append(where.getEndTime()).append("' ");
			}
		}

		// 排序
		if (true) {
			sql.append(" order by  mdate desc ");
		}
		// 条件追加,分页查询
		if (where != null) {
			if (where.getPageIndex() != null && where.getPageNum() != null) {
				sql.append(" limit ").append((where.getPageIndex() - 1) * where.getPageNum()).append(",")
						.append(where.getPageNum());
			}
		}
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
	}

	@Override
	public UserInfo loginSelect(String userName, String pwd) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from userinfo where userName=? and pwd=? ");
		List<UserInfo> st = jdbcTemplate.query(sql.toString(), new Object[] { userName, pwd },
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return (st == null || st.size() == 0) ? null : st.get(0);
	}

}
