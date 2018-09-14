package com.pc.bsp.common.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * @author simple
 * 系统公共数据，系统启动或参数更新时加载至静态变量中
 *
 */
public class PubData {
	//系统参数
	private static HashMap<String, Object> pubDataMap = new HashMap<String, Object>();	
	//系统字典
	private static Map<String,Map<String, Object>> pubDictionaryMapMap
							= new HashMap<String,Map<String, Object>>();
	
	/*public static ArrayList<String> mapColorList= new ArrayList<String>(){*//**
	{
		add("rgba(187,245,252, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(195,248,223, 0.7)");
		add("rgba(199,251,209, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(191,246,240, 0.7)");
		add("rgba(203,253,194, 0.7)");
		add("rgba(210,255,169, 0.7)");
		add("rgba(214,255,153, 0.7)");
		add("rgba(220,255,132, 0.7)");
		add("rgba(224,255,114, 0.7)");
		add("rgba(232,255,88, 0.7)");
		add("rgba(235,255,72, 0.7)");
		add("rgba(242,255,49, 0.7)");
		add("rgba(247,255,31, 0.7)");
		add("rgba(250,255,18, 0.7)");
		add("rgba(253,255,8, 0.7)");
		add("rgba(255,253,0, 0.7)");
		add("rgba(255,245,0, 0.7)");
		add("rgba(255,234,0, 0.7)");
		add("rgba(255,218,0, 0.7)");
		add("rgba(255,204,0, 0.7)");
		add("rgba(255,189,0, 0.7)");
		add("rgba(255,165,0, 0.7)");
		add("rgba(255,148,0, 0.7)");
		add("rgba(255,139,0, 0.7)");
		add("rgba(255,118,0, 0.7)");
		add("rgba(255,103,0, 0.7)");
		add("rgba(255,83,0, 0.7)");
		add("rgba(255,67,0, 0.7)");
		add("rgba(255,52,0, 0.7)");
		add("rgba(255,43,0, 0.7)");}};
	
	//系统参数
	/**
	 * 放值
	 * @param key
	 * @param keyValue
	 */
	public static void setData(String key,String keyValue){
		pubDataMap.put(key,keyValue);
	}
	
	/**
	 * 根据key取对应的值
	 * @param key
	 * @return
	 */
	public static Object getData(String key){
		Object  keyValue = pubDataMap.get(key);
		if(null == keyValue){
		   return ""; 
		}
		return keyValue;
	}
	
	//数据字典
	/**
	 * 放值
	 * @param key
	 * @param keyValue
	 */
	public static void setDictData(String key,Map<String,Object> keyValue){
		pubDictionaryMapMap.put(key,keyValue);
	}
	
	/**
	 * 根据编码取对应的字典列表
	 * @param dictCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> getDictList(String parentDictCode){
		List<Map<String,Object>> tempListMap = (List<Map<String,Object>>)pubDictionaryMapMap.get(parentDictCode).get(parentDictCode+"List");
		List<Map<String,Object>> reListMap = new ArrayList<Map<String,Object>>();
		for(int i=0;i<tempListMap.size();i++){
			Map<String,Object> tempMap = new HashMap<String,Object>();
			tempMap.put("dictData", tempListMap.get(i).get("dictData"));
			tempMap.put("dictName", tempListMap.get(i).get("dictName"));
			reListMap.add(tempMap);
		}
		return reListMap;
	}

	/**
	 * 根据编码和值取对应的名称
	 * @param dictCode,dictData
	 * @return
	 */
	public static String getDictName(String parentDictCode,String dictData){
		if(null==pubDictionaryMapMap.get(parentDictCode).get(dictData)){
			 return ""; 
		}
		return (String)pubDictionaryMapMap.get(parentDictCode).get(dictData);
	}

	/*public static String getMapColor(int maxLength,int con){
		int temp = mapColorList.size()*(maxLength-con)/maxLength;
		if(temp>=mapColorList.size())
			temp=mapColorList.size()-1;
		if(temp<0)
			temp=0;	
		return mapColorList.get(temp);
	}*/

}
