package com.pc.bsp.org.util;

/**
 * @author simple
 *
 */
public class GenOrgDescID {
	
	/**
	 * 根据父节点id和已经存在的子节点的数量生成4位新的机构描述id
	 * @param parentId
	 * @param orgCount
	 * @return
	 */
	public synchronized static String get4NewId(String parentId, int orgCount){
		String childId = "";
		orgCount ++;
		
		if(orgCount >= 0 && orgCount < 10){
			childId = "000"+orgCount;
		}else if(orgCount >= 10 && orgCount < 100){
			childId = "00"+orgCount;
		}else if(orgCount >= 100 && orgCount < 1000){
			childId = "0"+orgCount;
		}else if(orgCount >= 1000 && orgCount < 10000){
			childId = ""+orgCount;
		}
		
		return parentId + childId;
	}
	
	/**
	 * 根据父节点id和已经存在的子节点的数量生成2位新的菜单描述id
	 * @param parentId
	 * @param menuCount
	 * @return
	 */
	public synchronized static String get2NewId(String parentId, int menuCount){
		String childId = "";
		menuCount ++;
		
		if(menuCount >= 0 && menuCount < 10){
			childId = "0"+menuCount;
		}else if(menuCount >= 10 && menuCount < 100){
			childId = ""+menuCount;
		}
		return parentId + childId;
	}
	
	/**
	 * 根据整形参数生成机构码id格式的4位字符串
	 * @param count
	 * @return
	 */
	public static String get4ID(int count){
		String returnID = "";
		
		if(count >= 0 && count < 10){
			returnID = "000"+count;
		}else if(count >= 10 && count < 100){
			returnID = "00"+count;
		}else if(count >= 100 && count < 1000){
			returnID = "0"+count;
		}else if(count >= 1000 && count < 10000){
			returnID = ""+count;
		}
		
		return returnID;
	}

}
