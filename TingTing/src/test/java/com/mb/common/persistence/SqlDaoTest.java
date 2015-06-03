package com.mb.common.persistence;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.mb.beans.sql.User;
import com.mb.common.BaseTest;
import com.mb.common.persistence.sql.ISqlDao;
import com.mb.common.persistence.sql.impl.SqlDao;

public class SqlDaoTest extends BaseTest {
	
	@Resource(type=SqlDao.class)
	private ISqlDao sqldao; 
	
	@Test
	public void testInsertUser(){
		User u = new User();
		u.setId(UUID.randomUUID().toString().replace("-", ""));
		u.setIsActive(1);
		u.setLoginDate(new Date().getTime());
		u.setName("我靠");
		u.setAccount("lordtan");
		u.setPassword("123456");
//		System.out.println(u.toString());
		sqldao.insert(u); //user属于数据大小写敏感
	}
}
