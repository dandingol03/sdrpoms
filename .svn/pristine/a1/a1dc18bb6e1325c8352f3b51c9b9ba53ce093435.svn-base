/**   
 * @Package: com.pc.bsp.common.util 
 * @author: jwl   
 * @date: 2018年4月2日 上午10:35:41 
 */
package com.pc.bsp.common.util;

/**   
 * @Package: com.pc.bsp.common.util 
 * @author: jwl   
 * @date: 2018年4月2日 上午10:35:41 
 */
public class CaculateDistance {
    public static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6378137; // 地球的半径 6378137 6371229
    private static double distance; 
    public static double caculateDistance(double lng,double lat,double lng1,double lat1) {
		double x, y;
		x = (lng - lng1) * PI * R
			* Math.cos(((lat + lat1) / 2) * PI / 180) / 180;
		y = (lat - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance;
    }
}
