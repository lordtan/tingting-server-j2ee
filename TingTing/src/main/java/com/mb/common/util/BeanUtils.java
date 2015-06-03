package com.mb.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mb.beans.sql.TableName;

/**
 * bean工具类
 * @author lordtan
 * @date 2015年5月24日
 */
public class BeanUtils {

	public static String TABLE_NAME="tableName";  //表的名称，在TableName中标注
	
	/**
	 * 将bean转换成map
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> trans2map(Object obj){
		if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    map.put(key, value);  
                }  
            }
            
            TableName an = obj.getClass().getAnnotation(TableName.class);
            if(null!=an){
            	map.put(TABLE_NAME, an.value());  //标注表名
            }
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
	}
}
