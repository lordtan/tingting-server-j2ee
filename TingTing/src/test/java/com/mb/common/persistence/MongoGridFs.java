package com.mb.common.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.map.util.BeanUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.util.DBObjectUtils;

import com.mb.beans.momgo.Sound;
import com.mb.common.BaseTest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * mongodb文件存储系统
 * @author lordtan
 * @date 2015年5月14日
 */
public class MongoGridFs extends BaseTest {
 
	@Autowired
	private GridFsTemplate temp;
	
	/**
	 * 存储文件
	 */
	@Test
	public void storeFile() throws FileNotFoundException{
		InputStream content = new FileInputStream("E:\\zdssdk.mp3");
		/**
		 * 不需要存储文件的额外信息，因为如果文件信息更新了，那就会维护两套东西
		 * 文件只存个id，这样可以解耦
		 */
		temp.store(content, "123", "mp3");
//		temp.store(content, metadata)
	}
	
	/**
	 * 查询文件
	 * @throws IOException 
	 */
	@Test
	public void findFile() throws IOException{
//		Query query = new Query(Criteria.where("name").is("tanluole"));
//		List<GridFSDBFile> files = temp.find(null);
//		System.out.println(files.size());
		Query query = new Query(Criteria.where("filename").is("123"));
		GridFSDBFile file = temp.findOne(query);
		File f = new File("E:\\wokao.mp3");
		file.writeTo(f);
	}
	
	
}
