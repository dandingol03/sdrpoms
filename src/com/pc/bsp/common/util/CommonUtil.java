package com.pc.bsp.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 基本函数表
 * @author D.Steven
 *
 */
public class CommonUtil {
	/**
	 * 机构ID抹零
	 * @param oid
	 * @return
	 */
	public static String delZero(String oid) {

		if (oid == null || oid.length() != 12) {
			return oid;
		}

		String s[] = { oid.substring(0, 2), oid.substring(2, 4),
				oid.substring(4, 6), oid.substring(6, 9), oid.substring(9, 12) };

		String likeOid = "";
		try {
			for (int i = 0; i < s.length; i++) {
				if (Integer.parseInt(s[i]) == 0) {
					break;
				}
				likeOid += s[i];
			}
		} catch (NumberFormatException e) {
			return oid;
		}

		return likeOid;
	}
	/**
	 * 适用于get，set方法成对的Po，删除其属性值结尾处的空格。
	 * @param po 要转换的Po
	 * @return  删除其属性值结尾处空格后的Po
	 */
	@SuppressWarnings("unchecked")
	public static <T> T objectFieldTrim(T po){	
		// 适用于get，set方法对应的Po，删除其属性值结尾处的空格。
		try{
			Class<T> tClass=(Class<T>) po.getClass();
			for(Method method: tClass.getMethods()){
				if(method!=null){
					if(method.getName().startsWith("get")&&method.getReturnType().equals(String.class)){				
						Object object=method.invoke(po);
						if(object!=null){						
							String re=object.toString();
							String getMethodName=method.getName();
							String setMethodName="set"+getMethodName.substring(3, getMethodName.length());//						
							Method setMethod=tClass.getMethod(setMethodName, String.class);
							if(setMethod!=null){
								if(re!=null)
								   setMethod.invoke(po, re.trim());
							}						
						}
					}
				}
			}
		}			
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("CommonUtil中objectField方法出错");
			e.printStackTrace();
		} 		
		return po;		
	}
	
	/**
	 * 字符串BigDecimal加法
	 * @param summand被加数
	 * @param addend加数
	 * @return
	 */
	public static String addStrbyDec(String summand,String addend){
		if(null==summand||summand.equals("")){
			summand="0";
		}
		if(null==addend||addend.equals("")){
			addend="0";
		}
		return (new BigDecimal(summand)).add(new BigDecimal(addend)).toString();
	}
	
	/**
	 * 字符串BigDecimal减法
	 * @param minuend被减数
	 * @param subtrahend减数
	 * @return
	 */
	public static String subtractStrbyDec(String minuend,String subtrahend){
		if(null==minuend||minuend.equals("")){
			minuend="0";
		}
		if(null==subtrahend||subtrahend.equals("")){
			subtrahend="0";
		}
		return (new BigDecimal(minuend)).subtract(new BigDecimal(subtrahend)).toString();
	}
	
	/**
	 * 字符串BigDecimal乘法
	 * @param multiplicand被乘数
	 * @param multiplier乘数
	 * @return
	 */
	public static String multiplyStrbyDec(String multiplicand,String multiplier){
		if(null==multiplicand||multiplicand.equals("")){
			multiplicand="0";
		}
		if(null==multiplier||multiplier.equals("")){
			multiplier="0";
		}
		return (new BigDecimal(multiplicand)).multiply(new BigDecimal(multiplier)).toString();
	}
	
	/**
	 * 字符串BigDecimal除法
	 * @param dividend被除数
	 * @param divider除数
	 * @param nulmber保留位数
	 * @return
	 */
	public static String divideStrbyDec(String dividend,String divider,int nulmber){
		if(null==dividend||dividend.equals("")){
			dividend="0";
		}
		if(null==divider||divider.equals("")){
			return "0";
		}
		return (new BigDecimal(dividend)).divide(new BigDecimal(divider),nulmber,RoundingMode.HALF_UP).toString();
	}
	
	/**
	 * 字符串BigDecimal K2+820 换算成2820
	 * @param Km千米数 2
	 * @param M米数 820
	 * @return 2820
	 */
	public static String KM2MStrbyDec(String Km,String M){
		if(null==Km||Km.equals("")){
			Km="0";
		}
		if(null==M||M.equals("")){
			M="0";
		}
		return (new BigDecimal(Km)).multiply(new BigDecimal("1000")).add(new BigDecimal(M)).toString();
	}
	/**
	 * 字符串  2820换算成 K2+820 用于前台查询展示
	 * @param KStr千米数2820
	 * @return xx公里
	 */
	public static String KM2MStrbyDec(String Str){
		if(null==Str||Str.equals("")){
			return "0";
		}
		String[] result=M2KMStrbyDec(Str);

//		String KM=ConverterUtils.toString();
		double   f   = (ConverterUtils.toDouble(result[1])+(ConverterUtils.toDouble(result[0])*1000))/1000;  
		BigDecimal   b   =   new   BigDecimal(f);  
//		double   KM   =   b.setScale(3,BigDecimal.ROUND_DOWN).doubleValue();  
		double   KM   =   b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();  
		return ConverterUtils.toString(KM)+"公里";
//		return result[0]+"."+Integer.parseInt(result[1].split("\\.")[0])+"公里";
	}
	public static void main(String[] args) {
		String a="290020";
		System.out.println(KM2MStrbyDec(a));
	}
	/**
	 * 字符串BigDecimal 2820 换算成 K2+820
	 * @param M米数 2820
	 * @return K2+820
	 */
	public static String[] M2KMStrbyDec(String M){
		String[] result=new String[2];
		if(null==M||M.equals("")){
			M="0";
		}
		result[0] = (new BigDecimal(M)).divide(new BigDecimal("1000"),0,RoundingMode.DOWN).toString();
		result[1]= new BigDecimal(M).subtract(
				((new BigDecimal(M)).divide(new BigDecimal("1000"),0,RoundingMode.DOWN)).multiply(new BigDecimal("1000"))
				).toString();
		return result;
	}
}
