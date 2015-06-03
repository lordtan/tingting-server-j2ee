package com.mb.common.persistence;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mb.beans.momgo.Sound;
import com.mb.beans.sql.User;
import com.mb.common.BaseTest;
import com.mb.common.persistence.momgo.IMongoDao;

/**
 * mongoDB通用数据操作接口测试
 * @author lordtan
 * @date 2014年11月2日
 */
public class MongoDaoTest extends BaseTest{

	@Autowired
	private IMongoDao mongoDao;
	@Autowired
	private MongoTemplate mongoTemplate;  
	
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("tanluole");
		user.setPassword("123456");
		mongoDao.insert(user,"user");
	}
	
	@Test
	public void testGet(){
		Query query = new Query(Criteria.where("name").is("tanluole"));
		User user = mongoTemplate.findOne(query, User.class);
		System.out.println(user.getPassword());
//		mongoDao.findById(id, entityClass, collectionName)
	}
	
	@Test
	public void findAndRemove(){
		Query query = new Query(Criteria.where("name").is("tanluole"));
		List<User> list = mongoTemplate.findAllAndRemove(query,  User.class);
		System.out.println(list.size());
	}
	
	/**
	 * 二进制文件存取测试
	 */
	@Test
	public void saveBinary(){
		byte [] datas = {0,1,0,0,01}; //二进制文件
		Sound music = new Sound();
		music.setId(UUID.randomUUID().toString());
		music.setType("good");
		mongoTemplate.insert(music, "music");
	}
	
	@Test
	public void findAll(){
		Query query = new Query(Criteria.where("type").is("good"));
		
		List<Sound> sounds = mongoTemplate.find(query, Sound.class,"music");
		System.out.println(sounds.size());
	}
	
	@Test
	public void findBinary(){
		Query query = new Query(Criteria.where("type").is("good"));
		Sound music = mongoTemplate.findOne(query, Sound.class);
	}
	
	@Test
	public void gridFS(){
		GridFsTemplate temp;
	}
	
	/**
	 * 地理位置查询
	 */
	@Test
	public void geoTest(){
		double centerX = 888123.23;
		double centerY = 889754.92;
		double radius = 123213213;
		Circle circle = new Circle(centerX, centerY, radius);
		Query query = new Query(new Criteria().withinSphere(circle));
		List<Sound> sounds = mongoTemplate.find(query, Sound.class, "sound");
		
	}
}
