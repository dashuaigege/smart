package com.smart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.smart.domain.User;

@Repository // 通过Spring注解定义一个Dao
public class UserDao {
	private JdbcTemplate jdbcTemplate;

	private final static String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM t_user WHERE user_name=? AND password=?";
	private final static String Find_USER_SQL = "SELECT * FROM t_user WHERE user_name=?";
	private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";

	@Autowired // 自动转配，利用Spring的Ioc，注入JdbcTemplate的Bean
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getmatchCount(String userName, String password) {
		return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[] { userName, password }, Integer.class);
	}

	public User findUserByUserName(final String userName) {
		final User user = new User();
		jdbcTemplate.query(Find_USER_SQL, new Object[] { userName },
				// 匿名类方式实现回调函数
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						user.setUserId(rs.getInt("user_id"));
						user.setUserName(userName);
						user.setCredits(rs.getInt("credits"));
					}
				});
		return user;
	}

	public void updateLoginInfo(User user) {
		jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,
				new Object[] { user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId() });
	}
}
