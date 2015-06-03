package com.mb.common.persistence.momgo;

import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * mongoDB数据操作通用接口
 * @author lordtan
 * @date 2014年11月2日
 */
public interface IMongoDao {

	/**
	 * 根据id查询实体
	 * @param id
	 * @param entityClass
	 * @param collectionName 集合名称
	 * @return
	 */
	public <T> T findById(String id, Class<T> entityClass, String collectionName);
	
	/**
	 * 根据属性查询一个实体
	 * @param property 属性名称
	 * @param value 值
	 * @param collectionName 集合名称
	 * @param entityClass
	 * @return
	 */
	public <T> T findOneByProperty(String property, Object value, String collectionName, Class<T> entityClass); 
	
	/**
	 * 根据条件更新实体
	 * @param property 查询条件，属性
	 * @param value  查询条件，属性值
	 * @param collectionName 集合名称
	 * @param entityClass
	 * @param key  需要更新的key
	 * @param val  需要更新的key的值
	 * @return
	 */
	public <T> T findAndModify(String property, Object value, String collectionName, Class<T> entityClass, String key, Object val); 
	
	/**
	 * 查询地理位置附近的信息
	 * @param centerX 坐标x
	 * @param centerY 坐标y
	 * @param radius  半径
	 * @param collectionName 集合名称
	 * @param entityClass
	 * @return
	 */
	public <T> List<T>  geoNear(double centerX, double centerY, double radius, String collectionName, Class<T> entityClass);
	
	/**
	 * 存储文件
	 * @param content
	 * @param fileId
	 * @param type
	 * @return
	 */
	public GridFSFile storeFile(InputStream content, String fileId, String type);
	
	/**
	 * 根据文件id查询文件
	 * @param fileId
	 * @return
	 */
	public GridFSDBFile findFile(String fileId);
	
	/**
	 * 删除指定实体<br>
	 * 这个会导致物理删除，慎用！
	 * @param property 属性名称
	 * @param value 值
	 * @param collectionName
	 */
	public void removeByProperty(String property, Object value, String collectionName);
	
	/**
	 * 删除文件实体
	 * @param fileId 文件id
	 */
	public void removeFileEntity(String fileId);
	
	/**
	 * 插入数据
	 * @param objectToSave 被保存的对象
	 * @param collectionName 集合名词
	 */
	public void insert(Object objectToSave, String collectionName);
}
