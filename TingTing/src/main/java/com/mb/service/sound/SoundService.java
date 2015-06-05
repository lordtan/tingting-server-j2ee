package com.mb.service.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.mb.beans.common.Message;
import com.mb.beans.momgo.FileInfo;
import com.mb.beans.momgo.Search;
import com.mb.beans.momgo.Sound;
import com.mb.common.constant.IMongoCollections;
import com.mb.common.persistence.momgo.IMongoDao;
import com.mb.common.persistence.momgo.impl.MongoDao;
import com.mb.common.util.JsonUtil;
import com.mb.service.ISoundService;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * 声音服务
 * @author lordtan
 * @date 2015年5月21日
 */
@Service
public class SoundService implements ISoundService, IMongoCollections{
	
	@Resource(type=MongoDao.class)
	private IMongoDao mongoDao;


	public Message publish(InputStream content, String fileInfo) {
		Message mesg = Message.getInstance();
		FileInfo file = JsonUtil.json2obj(fileInfo, FileInfo.class);
		Sound sod = JsonUtil.json2obj(file.getSound(), Sound.class);
		mongoDao.insert(sod, SOUND);
		mongoDao.storeFile(content, file.getFileId(), file.getType());
		mesg.setMsg("发布成功");
		mesg.setState(Message.SUCCESS);

		return mesg;
	}

	public Message cancel(String sound) {
		Message mesg = Message.getInstance();
		Sound sod = JsonUtil.json2obj(sound, Sound.class);
		mongoDao.removeByProperty("id", sod.getId(), SOUND);  //删除声音信息
		mongoDao.removeFileEntity(sod.getFileId());  //删除实体
		mesg.setMsg("取消成功");
		mesg.setState(Message.SUCCESS);

		return mesg;
	}

	public GridFSFile store(InputStream content, String fileId, String type) {
		return mongoDao.storeFile(content, fileId, type);
	}

	public GridFSDBFile listen(String fileId) {
		Sound sound = mongoDao.findOneByProperty("fileId", fileId, SOUND, Sound.class);
		sound.setListened((sound.getListened()+1)); //收听加1
		mongoDao.findAndModify("fileId", fileId, SOUND, Sound.class, "listened", sound.getListened()); //更新收听次数
		return mongoDao.findFile(fileId);
	}

	public Message search(double centerX, double centerY, double radius) {
		List<Sound> sounds = mongoDao.geoNear(centerX, centerY, radius, SOUND, Sound.class);
		return Message.getInstance().setState(Message.SUCCESS).setMsg(JsonUtil.obj2json(sounds)); //男人就是要长一点
	}

	public Message search(String searchStr) {
		Search search = JsonUtil.json2obj(searchStr, Search.class);
		if(search.getCenterX()==0 || search.getCenterY()==0 || search.getRadius()==0){
			return Message.getInstance().setState(Message.FAILED).setMsg("参数不完整");
		}
		
		return search(search.getCenterX(),search.getCenterY(),search.getRadius());
	}

	public Message great(String id) {
		Sound sound = mongoDao.findOneByProperty("id", id, SOUND, Sound.class);
		sound.setGreat(sound.getGreat()+1);//点赞+1
		mongoDao.findAndModify("id", "id", SOUND, Sound.class, "great", sound.getGreat()); //更新收听次数
		return Message.getInstance().setState(Message.SUCCESS).setMsg("点赞成功");
	}

	public Message degrade(String id) {
		Sound sound = mongoDao.findOneByProperty("id", id, SOUND, Sound.class);
		sound.setDegrade(sound.getDegrade()+1);//点踩+1
		mongoDao.findAndModify("id", "id", SOUND, Sound.class, "great", sound.getGreat()); //更新收听次数
		return Message.getInstance().setState(Message.SUCCESS).setMsg("点踩成功");
	}
	
	
}
