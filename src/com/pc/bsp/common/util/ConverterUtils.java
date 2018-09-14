package com.pc.bsp.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* 数据类型转换工具类
* @author jwl
* @version [版本号, 2018年1月30日]
* @see  [相关类/方法]
* @since  [产品/模块版本]
*/
public class ConverterUtils {
    /**
     * 求Map<K,V>中Key(键)的最小值
     * @param map
     * @return
     */
    public static Object getMinKey(Map<String, Double> map) {
        if (map == null) return null;
        Set<String> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[0];
    }
 
    /**
     * 求Map<K,V>中Value(值)的最小值
     * @param map
     * @return
     */
    public static Object getMinValue(Map<String, Double> map) {
        if (map == null) return null;
        Collection<Double> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        return obj[0];
    }
	//List<Map<String,String>>排序
	public static void mapSorts(List<Map<String,String>> map){
        Collections.sort(map,new Comparator<Map<String,String>>() {
 
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                // TODO Auto-generated method stub
            	
            	return (int) (Double.valueOf(o1.get("len"))-Double.valueOf(o2.get("len"))); 
//                if(o1.get("len").compareTo(o2.get("len"))>0){
//                    return 1;                   
//                }
//                return -1;
            }
        });
    }
	//时间对比
	public static int compare_date(String DATE1, String DATE2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	                return -1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	                return 1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	/**
	* 获取当前时间前3分钟
	* @param stuff
	* @return
	*/
	public static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Calendar beforeTime = Calendar.getInstance();
		   beforeTime.add(Calendar.MINUTE, -3);// 3分钟之前的时间
		   Date beforeD = beforeTime.getTime();
		   String time = sdf.format(beforeD);
		return time; 
	}
	 private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', 
         '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	 public static String bytesToHexFun1(byte[] bytes) {
	        // 一个byte为8位，可用两个十六进制位标识
	        char[] buf = new char[bytes.length * 2];
	        int a = 0;
	        int index = 0;
	        for(byte b : bytes) { // 使用除与取余进行转换
	            if(b < 0) {
	                a = 256 + b;
	            } else {
	                a = b;
	            }

	            buf[index++] = HEX_CHAR[a / 16];
	            buf[index++] = HEX_CHAR[a % 16];
	        }

	        return new String(buf);
	    }

	public static byte[] strToByteArray(String str) {
	    if (str == null) {
	        return null;
	    }
	    byte[] byteArray = str.getBytes();
	    return byteArray;
	}
	 private static byte[] toBytes(String str) {
	        if(str == null || str.trim().equals("")) {
	            return new byte[0];
	        }

	        byte[] bytes = new byte[str.length() / 2];
	        for(int i = 0; i < str.length() / 2; i++) {
	            String subStr = str.substring(i * 2, i * 2 + 2);
	            bytes[i] = (byte) Integer.parseInt(subStr, 16);
	        }

	        return bytes;
	    }
	//string转byte[]
	 public static  byte[] convertHexStringToBytes(String str)
	 {
		 String copy=str;
		 copy=copy.replace("[", "");
		 copy=copy.replace("]", "");
		 String[] arr=copy.split(",");
		
		 byte[] res=new byte[arr.length];
		
		 for(int i=0;i<arr.length;i++)
		 {
			 int tmp=Integer.parseInt(arr[i].trim());
			 String hexStr=Integer.toHexString(tmp);
			 byte[] bb= toBytes(hexStr.substring(0, 2));
			 res[i]=bb[0];	 
		 }
		
		 return res;
	 }
	   /**
	     * 2018-04-21 
	     * base_info_propaganda ------> baseInfoPropaganda
	     */
	 public static String toReplaceStr_(String str){
			StringBuffer sb = new StringBuffer();
			Pattern p = Pattern.compile("\\_[a-z|A-Z]");
			Matcher m = p.matcher(str);
			while (m.find()){ 
			m.appendReplacement(sb,m.group().toUpperCase());
			}
			m.appendTail(sb);
			return str=sb.toString().replace("_", "");
    }
    /**
     * <将obj转换为string，如果obj为null则返回defaultVal>
     *
     * @param obj 需要转换为string的对象
     * @param defaultVal 默认值
     * @return obj转换为string
     */
    public static String toString(Object obj, String defaultVal){
        return (obj != null) ? obj.toString() : defaultVal;
    }
    /**
     * <将obj转换为string，默认为空>
     *
     * @param obj 需要转换为string的对象
     * @return 将对象转换为string的字符串
     */
    public static String toString(Object obj){
        return toString(obj, "");
    }
    /**
     * <将对象转换为int>
     *
     * @param obj 需要转换为int的对象
     * @param defaultVal 默认值
     * @return obj转换成的int值
     */
    public static Integer toInt(Object obj, Integer defaultVal) {
        try
        {
            return (obj != null) ? Integer.parseInt(toString(obj, "0")) : defaultVal;
        }catch(Exception e)
        {
        }
        return defaultVal;
    }
    /**
     * <将对象转换为int>
     *
     * @param obj 需要转换为int的对象
     * @param defaultVal 默认值
     * @return obj转换成的int值
     */
    public static Integer toInt(Object obj)
    {
        return toInt(obj, 0);
    }
    /**
     * <将对象转换为Integer>
     *
     * @param obj 需要转换为Integer的对象
     * @return obj转换成的Integer值
     */
    public static Integer toInteger(Object obj)
    {
        return toInt(obj, null);
    }
    /**
     * <将对象转换为int>
     *
     * @param obj 需要转换为int的对象
     * @param defaultVal 默认值
     * @return obj转换成的int值
     */
    public static Float toFloat(Object obj, float defaultVal)
    {
        return (obj != null) ? Float.parseFloat(toString(obj, "0")) : defaultVal;
    }
    /**
     * <将对象转换为Float>
     *
     * @param obj 需要转换为Float的对象
     * @return obj转换成的Float值
     */
    public static Float toFloat(Object obj)
    {
        return toFloat(obj, 0);
    }
    /**
     * <将obj转换为long>
     *
     * @param obj 需要转换的对象
     * @param defaultVal 默认值
     * @return 如果obj为空则返回默认，不为空则返回转换后的long结果
     */
    public static Long toLong(Object obj, long defaultVal)
    {
        return (obj != null) ? Long.parseLong(toString(obj)) : defaultVal;
    }
    /**
     * <将obj转换为long>
     *
     * @param obj 需要转换的对象
     * @return 如果obj为空则返回默认的0l，不为空则返回转换后的long结果
     */
    public static Long toLong(Object obj)
    {
        return toLong(obj, 0l);
    }
    /**
     * 将object转换为double类型，如果出错则返回 defaultVal
     * @param obj 需要转换的对象
     * @param defaultVal 默认值
     * @return 转换后的结果
     */
    public static Double toDouble(Object obj,Double defaultVal)
    {
        try
        {
            return Double.parseDouble(obj.toString());
        }
        catch(Exception e)
        {
            return defaultVal;
        }
    }
    /**
     * 将object转换为double类型，如果出错则返回 0d
     * @param obj 需要转换的对象
     * @return 转换后的结果
     */
    public static double toDouble(Object obj)
    {
        return toDouble(obj,0d);
    }
    /**
     * < 将List<Object>转换为List<Map<String, Object>> >
     *
     * @param list 需要转换的list
     * @return 转换的结果
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> converterForMapList(List<Object> list)
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Object tempObj : list)
        {
            result.add((HashMap<String, Object>)tempObj);
        }
        return result;
    }
}
