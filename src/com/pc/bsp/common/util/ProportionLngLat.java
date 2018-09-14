package com.pc.bsp.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ProportionLngLat {
	/** 
	 * 计算铁路长度
 	 * @param temp 类型 String geometry (LINESTRING MULTILINESTRING)
	 */
	public static double caculateLength(String temp) {
		String eq=temp.substring(0,5);
		double L=0;
		if(StringUtils.equals(eq, "MULTI")){
			temp = temp.replaceAll("MULTILINESTRING", "");
			temp = temp.replaceFirst("\\(", ""); 
			temp = temp.substring(0,temp.length()-1);
			String []temp2 = temp.toString().split("\\),\\(");
			for (int i = 0; i < temp2.length; i++) {
				temp2[i]=temp2[i].toString().replaceAll("\\(", "").replaceAll("\\)", "");
				L+=getLength(temp2[i]);
			}
			return L;
		}else{
			temp = temp.replaceAll("LINESTRING", "");
			temp = temp.replaceAll("\\(", "");
			temp = temp.replaceAll("\\)", "");
			L=getLength(temp);
			return L;
		}
	}
	/** 
	 * 计算铁路长度
 	 * @param temp 类型 String geometry
	 */
	 public static double getLength(String temp) {
			ArrayList<Double> lngArray = new ArrayList<Double>();
			ArrayList<Double> latArray = new ArrayList<Double>();
			while(true){
				int nlng = temp.indexOf(" ");
				String lng=temp.substring(0, nlng);
				lngArray.add(Double.parseDouble(lng));
				temp = temp.substring(nlng+1);
				int nlat = temp.indexOf(",");
				if(nlat<0)
				{
					String lat=temp;
					latArray.add(Double.parseDouble(lat));
					break;
				}else{
					String lat=temp.substring(0, nlat);
					latArray.add(Double.parseDouble(lat));
				}
				temp = temp.substring(nlat+1);
			}
			
			double tempDis = 0;
			for(int j = 1;j<lngArray.size();j++){
				tempDis += Point.getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
			}
			return tempDis;
	 }
    //gps---》 km
    // 1 .根据校准点计算各个点的km
    public static List<Point> caculateKm(List<Point> points) {
	// 首先计算两点之间的距离以及总距离
	for (int i = 0; i < points.size(); i++) {
	    if (i == 0) {
		continue;
	    }
	    Point point = points.get(i);
	    double d = point.caculateDistance(points.get(i - 1));
	    System.out.println("点到点距离----" + d);
	    point.setL_l(d);
	}
	// 计算各个线段比例（线段距离/总距离）
	Point p = null;
	// 第一个校准点位置
	int startCpIndex = 0;
	// 第二个校准点位置
	int endCpIndex = 0;
	// 校准点之间比例和
	for (int i = 0; i < points.size(); i++) {
	    Point point = points.get(i);
	    if (point.isCalibrationPoint()) {
		if (p != null) {
		    point.caculateKmDistance(p.getKm());
		    endCpIndex = i;
		} else {
		    startCpIndex = i;
		}
		p = point;
	    }
	}
	// 标记点前一个的km计算
//	System.out.println(startCpIndex + "************" + endCpIndex);

	// km公里标 已知 km1=30000.123 km2=80112.45 km2-km1=50112.327 点到点距离
	// L_l=56.66562758140623 L_l=38.239541928302906
//	System.err
//		.println(30000.123 + ((50112.327 / (56.66562758140623 + 38.239541928302906)) * 56.66562758140623));
//	System.err
//	.println(10 + ((90 / (154.92020897394417)) * 50.123060696069864));
	double L_l = 0;// 比例
	for (int i = startCpIndex; i < endCpIndex; i++) {
	    Point point = points.get(i + 1);
	    L_l += point.getL_l();
	}
	// 中间
//	System.out.println(points.get(endCpIndex).getKmDistance());//校准点差
//	System.out.println(L_l);//校准点距离和
//	System.out.println(points.get(startCpIndex).getKm());//校准点  1
	
	double Distance_L_l = points.get(endCpIndex).getKmDistance() / L_l;
	double startKm = points.get(startCpIndex).getKm();
	double sum = startKm;
	for (int i = startCpIndex; i < endCpIndex - 1; i++) {
	    Point point = points.get(i + 1);
	    point.setKm(sum += Distance_L_l * point.getL_l());
	}
	// 左侧
	System.out.println("--startKm---"+startKm);
//	double sumLeft = startKm;
//	for (int i = startCpIndex; i > 0; i--) {
//	    Point point = points.get(startCpIndex-1);
//	    System.err.println("point.getL_l()point.getL_l() "+point.getL_l());
//	    point.setKm(sumLeft -= Distance_L_l * point.getL_l());
//	}
	double sumLeft = startKm;
	for (int i = 0; i < startCpIndex ; i++) {
	    Point point = points.get(startCpIndex - i - 1);
	    Point point1 = points.get(startCpIndex - i);
	    point.setKm(sumLeft -= Distance_L_l * point1.getL_l());
	}
	// 右侧
	double endKm = points.get(endCpIndex).getKm();
	double sumRight = endKm;
	for (int i = endCpIndex; i < points.size() - 1; i++) {
	    Point point = points.get(i + 1);
	    point.setKm(sumRight += Distance_L_l * point.getL_l());
	}
	// 遍历全部
	for (int i = 0; i < points.size(); i++) {
	    Point point = points.get(i);
	}
	return points;
    }

    // km转gps
    public static Point EqualProportionCalculation(Point minPoint,
	    Point maxPoint, double km) {
    	System.err.println(minPoint);
    	System.err.println(maxPoint);
	double t = Point.caculateKmDistance(km, maxPoint.getKm())
		/ Point.caculateKmDistance(minPoint.getKm(), maxPoint.getKm());

	double lng = t * minPoint.getLng() + maxPoint.getLng() * (1 - t);
	double lat = t * minPoint.getLat() + maxPoint.getLat() * (1 - t);
System.out.println(lng);System.out.println(lat);
	return new Point(lng, lat, km);
    }
//    Point [orderNumber=0, lng=116.2773335, lat=39.89480272, distance=0.0, kmDistance=0.0, km=5441.0, calibrationPoint=false, ratio=0.0, L_l=0.0]
//    		Point [orderNumber=0, lng=116.27722084522247, lat=39.89480674266815, distance=0.0, kmDistance=0.0, km=5585.0, calibrationPoint=false, ratio=0.0, L_l=0.0]
    public static void main(String[] args) {
    	Point Point1=new Point();
    	Point1.setLat(39.89480272);
    	Point1.setLng(116.2773335);
    	Point1.setKm(5441.0);
    	Point Pointmax=new Point();
    	Pointmax.setLat(39.89480674266815);
    	Pointmax.setLng(116.27722084522247);
    	Point1.setKm(5585.0);
    	System.out.println(EqualProportionCalculation(Point1,Pointmax,5441));
	}
}