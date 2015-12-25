package com.mb.common.persistence.sql;

import java.util.List;


/**
 * sql相关的DAO
 * @author lordtan
 * @date 2015年5月24日
 */
public interface ISqlDao {

	/**
	 * 插入一条数据
	 * @param bean
	 * @return
	 */
	public int insert(Object bean);
	
	/**
	 * 更新数据
	 * @param tableName
	 * @param bean
	 * @return
	 */
	public int update(String sql, Object [] args);
	
	/**
	 * 查询
	 * @param sql
	 * @param args
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> query(String sql,Object[] args,Class<T> entityClass);
}
