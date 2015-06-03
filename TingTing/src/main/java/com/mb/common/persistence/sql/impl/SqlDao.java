package com.mb.common.persistence.sql.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.mb.common.persistence.sql.ISqlDao;
import com.mb.common.util.BeanUtils;

/**
 * sql相关的DAO，这里的数据库主要是用MariaDB（MySQL替代品）
 * @author lordtan
 * @date 2015年5月12日
 */
@Service("sqlDao")
public class SqlDao implements ISqlDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(Object bean) {
		Map<String,Object> args = BeanUtils.trans2map(bean);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
		insert.withTableName((String) args.get(BeanUtils.TABLE_NAME));
		return insert.execute(args);
	}

	public int update(String sql, Object [] args) {
		return jdbcTemplate.update(sql, args);
	}

	public <T> List<T> query(String sql, Object[] args, Class<T> entityClass) {
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(entityClass));
	}
}
