/**
 * 
 */
package com.pc.bsp.common.util;

import java.util.Calendar;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author simple
 *
 */
public class NextID {
	private static Calendar now;
	private static Logger logger = Logger.getLogger(NextID.class);
	/**
	 * 根据表名获得主键ID
	 * @param table
	 * @return
	 */
	public static synchronized String getNextID(String table){
		now = Calendar.getInstance();
		
		String id="";
		if(table.length()>4){
			table=table.substring(0, 4);
		}
		
		id = table+"_"+now.getTimeInMillis();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("获取nextID出错",e);
		}
		return id;
	}
	
	/**
	 * 获取权限ID
	 * @param table
	 * @return
	 */
//	public static String getAuthID(String prefix){
//		
//		now = Calendar.getInstance();
//		
//		return prefix+now.getTimeInMillis();
//	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }

}
