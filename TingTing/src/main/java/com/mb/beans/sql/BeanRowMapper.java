package com.mb.beans.sql;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * 将bean转换为spring的rowmapper
 * 其实可以不必这么大费周章，Spring的BeanPropertyRowMapper早已解决了这个问题
 * @author lordtan
 * @date 2015年5月25日
 * @param <T>
 */
public class BeanRowMapper<T> implements RowMapper<T> {
	private static Log log = LogFactory.getLog(BeanRowMapper.class);
	
    private Class<T> c;
	
	public BeanRowMapper(Class<T> c){
		this.c = c;
	}
	
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T instance;
		try {
			instance = c.newInstance();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
        
        //根据数据库字段匹配属性，进行set
		ResultSetMetaData rsMetaData = rs.getMetaData();
		for(int i=1;i<=rsMetaData.getColumnCount();i++){
			if(rs.getObject(i)==null){
				continue;
			}
			
			String name = rsMetaData.getColumnName(i);
			
			String[] temp = name.split("_");
			StringBuffer sb = new StringBuffer();
			for(String t : temp){
				
				t = t.toLowerCase();
				t = (t.charAt(0) + "").toUpperCase() + t.substring(1);
				sb.append(t);
			}
			
			Method method = null;
			try {
				
				if("java.math.BigDecimal".equals(rsMetaData.getColumnClassName(i))){
					//对预警策略信息做特殊信息
					if( sb.toString().equals("Yjyctime")){
						method = c.getMethod("set" + sb.toString(), BigDecimal.class);
						method.invoke(instance, ((BigDecimal)rs.getObject(i)));
					}else{
						method = c.getMethod("set" + sb.toString(), Integer.class);
						method.invoke(instance, (Integer)((BigDecimal)rs.getObject(i)).intValue());
					}
					
				}else if("java.sql.Timestamp".equals(rsMetaData.getColumnClassName(i)) || "java.sql.Date".equals(rsMetaData.getColumnClassName(i))){
					
					method = c.getMethod("set" + sb.toString(), Date.class);
					method.invoke(instance, new Date(rs.getTimestamp(i).getTime()));
					
				}else{
					method = c.getMethod("set" + sb.toString(), Class.forName(rsMetaData.getColumnClassName(i)));
					method.invoke(instance, rs.getObject(i));
				}
				
			} catch(NoSuchMethodException e){
				log.warn(e);
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}
		
		return instance;
	}

}
