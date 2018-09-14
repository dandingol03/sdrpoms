package com.pc.bsp.common.util;



public class Point {
	private static double EARTH_RADIUS = 6378.137;  
    public static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6378137; // 地球的半径6378137 6371229
    private int  orderNumber;
    private String  railId;
    private String  id;
    private double lng;
    private double lat;
    private double distance; // 根据lag lat 计算的长度
    private double kmDistance; // 根据公里标计算的长度
    private double km = 0;
    private boolean calibrationPoint;// 是否校准点
    
    private double ratio; //
    private double L_l; //
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRailId() {
		return railId;
	}

	public void setRailId(String railId) {
		this.railId = railId;
	}

	public double getL_l() {
        return L_l;
    }

    public void setL_l(double l_l) {
        L_l = l_l;
    }

    public void setDistance(double distance) {
	this.distance = distance;
    }
    public Point() {}
    public Point(double lng, double lat) {
	super();
	this.lng = lng;
	this.lat = lat;
    }

    public Point(double lng, double lat, boolean calibrationPoint) {
	super();
	this.lng = lng;
	this.lat = lat;
	this.calibrationPoint = calibrationPoint;
	
    }
    public Point(double lng, double lat, int orderNumber) {
	super();
	this.lng = lng;
	this.lat = lat;
	this.orderNumber = orderNumber;
    }
    public Point(double km) {
	super();
	this.km = km;
    }

    public Point(double lng, double lat, double km) {
	super();
	this.lng = lng;
	this.lat = lat;
	this.km = km;
	this.calibrationPoint = false;
    }

    public Point(double lng, double lat, double km, boolean calibrationPoint, int orderNumber,String id) {
	super();
	this.lng = lng;
	this.lat = lat;
	this.km = km;
	this.calibrationPoint = calibrationPoint;
	this.orderNumber = orderNumber;
	this.id = id;
    }
    public Point(double lng, double lat,   int orderNumber,String id) {
    	super();
    	this.lng = lng;
    	this.lat = lat;
    	this.orderNumber = orderNumber;
    	this.id = id;
        }
    public Point(double lng, double lat, double km, boolean calibrationPoint, int orderNumber) {
	super();
	this.lng = lng;
	this.lat = lat;
	this.km = km;
	this.calibrationPoint = calibrationPoint;
	this.orderNumber = orderNumber;
    }
    public double getLng() {
	return lng;
    }

    public void setLng(double lng) {
	this.lng = lng;
    }

    public double getLat() {
	return lat;
    }

    public void setLat(double lat) {
	this.lat = lat;
    }
    
    

    public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	

    @Override
	public String toString() {
		return "Point [orderNumber=" + orderNumber + ", lng=" + lng + ", lat="
				+ lat + ", distance=" + distance + ", kmDistance=" + kmDistance
				+ ", km=" + km + ", calibrationPoint=" + calibrationPoint
				+ ", ratio=" + ratio + ", L_l=" + L_l + "]";
	}

	public double getDistance() {
	return distance;
    }

    public double getKm() {
	return km;
    }

    public void setKm(double km) {
	this.km = km;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getKmDistance() {
	return kmDistance;
    }

    public void setKmDistance(double kmDistance) {
	this.kmDistance = kmDistance;
    }
    
    public boolean isCalibrationPoint() {
        return calibrationPoint;
    }

    public void setCalibrationPoint(boolean calibrationPoint) {
        this.calibrationPoint = calibrationPoint;
    }

    /**
     * 根据经纬度计算距离
     * @param point
     * @return
     */
    public double caculateDistance(Point point) {
	double x, y;
	x = (point.lng - lng) * PI * R
		* Math.cos(((point.lat + lat) / 2) * PI / 180) / 180;
	y = (point.lat - lat) * PI * R / 180;
	distance = Math.hypot(x, y);
	return distance;
    }
    /**
     * 根据经纬度计算距离
     * @param point
     * @return
     */
    public double caculateDistance(double lng,double lat,double lng1,double lat1) {
	double x, y;
	x = (lng - lng1) * PI * R
		* Math.cos(((lat + lat1) / 2) * PI / 180) / 180;
	y = (lat - lat1) * PI * R / 180;
	distance = Math.hypot(x, y);
	return distance;
    }

    /**
     * 根据km计算距离
     * @param prevPointKm
     * @return
     */
    public double caculateKmDistance(double prevPointKm) {
	kmDistance = this.km - prevPointKm;
	return kmDistance;
    }

    public static double caculateDistance(Point point1, Point point2) {
	double distance, x, y;
	x = (point2.lng - point1.lng) * PI * R
		* Math.cos(((point2.lat + point1.lat) / 2) * PI / 180) / 180;
	y = (point2.lat - point1.lat) * PI * R / 180;
	distance = Math.hypot(x, y);
	return distance;
    }

    public static double caculateKmDistance(double point1Km, double point2Km) {
	return point2Km - point1Km;
    }
    
	public static double getDistance(double lat1, double lng1, double lat2,  double lng2) {  
		double radLat1 = rad(lat1);  
		double radLat2 = rad(lat2);  
		double a = radLat1 - radLat2;  
		double b = rad(lng1) - rad(lng2);  
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
		+ Math.cos(radLat1) * Math.cos(radLat2)  
		* Math.pow(Math.sin(b / 2), 2)));  
		s = s * EARTH_RADIUS;  
		s = Math.round(s * 10000d) / 10000d;  
		s = s*1000;  
		return s;  
	}
    private static double rad(double d) {  
        return d * Math.PI / 180.0;  
    }  
}
