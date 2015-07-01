package com.mb.common.persistence.momgo.impl;


import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.mb.common.persistence.momgo.IMongoDao;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * mongoDB数据操作
 * @author lordtan
 * @date 2014年11月2日
 */
@Service
public class MongoDao implements IMongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;  
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	public <T> T findById(String id, Class<T> entityClass, String collectionName) {
		return mongoTemplate.findById(id, entityClass, collectionName);
	}

	public <T> T findOneByProperty(String property, Object value, String collectionName,
			Class<T> entityClass) {
		Query query = new Query(Criteria.where(property).is(value));
		return mongoTemplate.findOne(query, entityClass, collectionName);
	}
	

	public <T> T findAndModify(String property, Object value,
			String collectionName, Class<T> entityClass, String key, Object val) {
		Query query = new Query(Criteria.where(property).is(value));
		Update update = Update.update(key, val);
		return mongoTemplate.findAndModify(query, update, entityClass, collectionName);
	}

	public <T> List<T> geoNear(double centerX, double centerY, double radius,
			String collectionName, Class<T> entityClass) {
		Point po = new Point(centerX, centerY);
		Distance dis = new Distance(radius, Metrics.KILOMETERS);
		Circle circle = new Circle(po, dis);
		Query query = new Query(Criteria.where("gpslocation").within(circle)); //这个gpslocation不应该写死在这，以后再说吧，先做demo
		return mongoTemplate.find(query,entityClass, collectionName);
	}
	

	public GridFSFile storeFile(InputStream content, String fileId, String type) {
		return gridFsTemplate.store(content, fileId);
	}

	public GridFSDBFile findFile(String fileId) {
		Query query = new Query(Criteria.where("filename").is(fileId));
		return gridFsTemplate.findOne(query);
	}

	public void removeByProperty(String property, Object value, String collectionName) {
		Query query = new Query(Criteria.where(property).is(value));
		mongoTemplate.remove(query, collectionName);
	}

	public void removeFileEntity(String fileId) {
		Query query = new Query(Criteria.where("filename").is(fileId));
		gridFsTemplate.delete(query);
	}

	public void insert(Object objectToSave,String collectionName) {
		mongoTemplate.insert(objectToSave, collectionName);
	}

}
