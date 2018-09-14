package com.pc.exhibition.test;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.Point;
import com.pc.bsp.common.util.ProportionLngLat;

/**
 * @author simple
 *
 */
@Repository("jUnitDao")
public class JUnitDao {

	@Autowired
	private  DBUtil util;
	public static void main(String[] args) {
	}
	public List<Point> test1(String railId) {
		String sql="select " +
	            	"t.id as \"id\", " +
	    			"t.order_number as \"orderNumber\", " +
	    			"t.lng as \"lng\", " +
	    			"t.lat as \"lat\", " +
	    			"t.kilometer_mark as \"kilometerMark\" " +
	    			"from base_info_rail_data_cece t " +
	    			"where 1=1 AND t.rail_id=? ORDER BY CAST(order_number AS SIGNED) ";
		List<Map<String, Object>> rowsList=util.getMapList(sql, new Object[]{railId});
		List<Point> pointList = new ArrayList<Point>();
		List<Point> pointList2 = new ArrayList<Point>();
		int temp = 0;
		int tempindex = 0;
		boolean bo =true;
		boolean bobo =false;
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				if ( row.get("kilometerMark") != null&& !"".equals( row.get("kilometerMark").toString().trim())) {
					++tempindex;
				}
			}
		}
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object id =row.get("id");
				Object lng =row.get("lng");
				Object lat=row.get("lat");
				Object kilometerMark = row.get("kilometerMark");
				Object orderNumber = row.get("orderNumber");
				 if(bo){
					 if(temp!=0){
						if(temp%2==0){
							 List<Point> list = ProportionLngLat.caculateKm(pointList);
								List<Object[]> objList = new ArrayList<Object[]>();
								for (int i1 = 0; i1 < list.size(); i1++) {
									Point point1 = list.get(i1);
									Object[] obj = new Object[2];
									obj[0] = Double.toString(point1.getKm());
									obj[1] = point1.getId();
									objList.add(obj);
								}
								String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
								 util.batchOperate(upsql, objList);
//								int []a=JUnitDao.updateRailKm(objList);
//								System.out.println(Arrays.toString(a));
							 pointList = new ArrayList<Point>();
							 temp=0;
							 bo=false;
							 bobo=true;
						 } 
					 }
					 }
				if (kilometerMark != null&& !"".equals(kilometerMark.toString().trim())) {
					 ++temp;
					 System.out.println(temp);
					 Point point=new Point(Double.parseDouble(lng.toString()),Double.parseDouble(lat.toString()), Double.parseDouble(kilometerMark.toString()),
							    true,ConverterUtils.toInt(orderNumber),id.toString());
					 pointList.add(point);
					 pointList2.add(point);
					 //够2个校准点执行  12/23/4
					 tempindex-=1;
					 if(tempindex==0){
						 System.out.println("最后个校准点执行"+kilometerMark);
					 }else{
						 if(bobo){
							 if(temp!=0){
								 if(temp==1){
									 System.err.println(pointList2);
									 Point point2= pointList2.get(pointList2.size()-2);
									 Point point3=new Point(point2.getLng(),point2.getLat(), point2.getKm(),
											    true,point2.getOrderNumber(),point2.getId());
									 pointList.add(0,point3);
									 System.err.println(pointList);
									 List<Point> list = ProportionLngLat.caculateKm(pointList);
										List<Object[]> objList = new ArrayList<Object[]>();
										for (int i1 = 0; i1 < list.size(); i1++) {
											Point point1 = list.get(i1);
											Object[] obj = new Object[2];
											obj[0] = Double.toString(point1.getKm());
											obj[1] = point1.getId();
											objList.add(obj);
										}
										String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
										 util.batchOperate(upsql, objList);
										 pointList = new ArrayList<Point>();
										 temp=0;
								 }
								} 
						 }
					 }
					} else {
						pointList.add(new Point(Double.parseDouble(lng.toString()),
						    Double.parseDouble(lat.toString()),ConverterUtils.toInt(orderNumber),id.toString()));
						//最后
						if(rowsList.size()-1==i){
							if(temp!=0){
//								if(temp%2==0){
//									 List<Point> list = ProportionLngLat.caculateKm(pointList);
//										List<Object[]> objList = new ArrayList<Object[]>();
//										for (int i1 = 0; i1 < list.size(); i1++) {
//											Point point = list.get(i1);
//											Object[] obj = new Object[2];
//											obj[0] = Double.toString(point.getKm());
//											obj[1] = point.getId();
//											objList.add(obj);
//										}
//										String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//										 util.batchOperate(upsql, objList);
//								 }else{
									 System.err.println(pointList2);
									 Point point2= pointList2.get(pointList2.size()-2);
									 Point point3=new Point(point2.getLng(),point2.getLat(), point2.getKm(),
											    true,point2.getOrderNumber(),point2.getId());
									 pointList.add(0,point3);
									 System.err.println(pointList);
									 List<Point> list = ProportionLngLat.caculateKm(pointList);
										List<Object[]> objList = new ArrayList<Object[]>();
										for (int i1 = 0; i1 < list.size(); i1++) {
											Point point = list.get(i1);
											Object[] obj = new Object[2];
											obj[0] = Double.toString(point.getKm());
											obj[1] = point.getId();
											objList.add(obj);
										}
										String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
										 util.batchOperate(upsql, objList);
								 }
//							}	
						}
					}
			}
		}
//		if(tempindex%2==0){
//			System.out.println("ou");
//			if(rowsList!=null&&rowsList.size()>0){
//				for(int i=0;i<rowsList.size();i++){
//					Map<String,Object> row=rowsList.get(i);
//					Object id =row.get("id");
//					Object lng =row.get("lng");
//					Object lat=row.get("lat");
//					Object kilometerMark = row.get("kilometerMark");
//					Object orderNumber = row.get("orderNumber");
//					if (kilometerMark != null&& !"".equals(kilometerMark.toString().trim())) {
//						if(temp!=0){
//							if(temp%2==0){
//								 List<Point> list = ProportionLngLat.caculateKm(pointList);
//									List<Object[]> objList = new ArrayList<Object[]>();
//									for (int i1 = 0; i1 < list.size(); i1++) {
//										Point point1 = list.get(i1);
//										Object[] obj = new Object[2];
//										obj[0] = Double.toString(point1.getKm());
//										obj[1] = point1.getId();
//										objList.add(obj);
//									}
//									String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//									 util.batchOperate(upsql, objList);
//								 pointList = new ArrayList<Point>();
//								 temp=0;
//							 }
//							}
//						 ++temp;
//						 System.out.println(temp);
//							//够2个校准点执行
//						 pointList.add(new Point(Double.parseDouble(lng.toString()),Double.parseDouble(lat.toString()), Double.parseDouble(kilometerMark.toString()),
//								    true,ConverterUtils.toInt(orderNumber),id.toString()));
//						} else {
//							pointList.add(new Point(Double.parseDouble(lng.toString()),
//							    Double.parseDouble(lat.toString()),ConverterUtils.toInt(orderNumber),id.toString()));
//							//最后
//							if(rowsList.size()-1==i){
//								if(temp!=0){
//									if(temp%2==0){
//										 List<Point> list = ProportionLngLat.caculateKm(pointList);
//											List<Object[]> objList = new ArrayList<Object[]>();
//											for (int i1 = 0; i1 < list.size(); i1++) {
//												Point point = list.get(i1);
//												Object[] obj = new Object[2];
//												obj[0] = Double.toString(point.getKm());
//												obj[1] = point.getId();
//												objList.add(obj);
//											}
//											String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//											 util.batchOperate(upsql, objList);
//											 pointList = new ArrayList<Point>();
//											 temp=0;
//									 }
//								}	
//							}
//						}
//				}
//			}
//		}else{
//			System.out.println("ji");
//			if(rowsList!=null&&rowsList.size()>0){
//				for(int i=0;i<rowsList.size();i++){
//					Map<String,Object> row=rowsList.get(i);
//					Object id =row.get("id");
//					Object lng =row.get("lng");
//					Object lat=row.get("lat");
//					Object kilometerMark = row.get("kilometerMark");
//					Object orderNumber = row.get("orderNumber");
//					
//					if (kilometerMark != null&& !"".equals(kilometerMark.toString().trim())) {
//						 ++temp;
//						 System.out.println(temp);
//						 Point point=new Point(Double.parseDouble(lng.toString()),Double.parseDouble(lat.toString()), Double.parseDouble(kilometerMark.toString()),
//								    true,ConverterUtils.toInt(orderNumber),id.toString());
//						 pointList.add(point);
//							//够2个校准点执行
//							if(temp!=0){
//								if(temp%2==0){
//									 List<Point> list = ProportionLngLat.caculateKm(pointList);
//										List<Object[]> objList = new ArrayList<Object[]>();
//										for (int i1 = 0; i1 < list.size(); i1++) {
//											Point point1 = list.get(i1);
//											Object[] obj = new Object[2];
//											obj[0] = Double.toString(point1.getKm());
//											obj[1] = point1.getId();
//											objList.add(obj);
//										}
//										String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//										 util.batchOperate(upsql, objList);
////										int []a=JUnitDao.updateRailKm(objList);
////										System.out.println(Arrays.toString(a));
//									 pointList = new ArrayList<Point>();
//									 temp=0;
//								 }
//								}
//						 pointList2.add(point);
//						} else {
//							pointList.add(new Point(Double.parseDouble(lng.toString()),
//							    Double.parseDouble(lat.toString()),ConverterUtils.toInt(orderNumber),id.toString()));
//							//最后
//							if(rowsList.size()-1==i){
//								if(temp!=0){
//									if(temp%2==0){
//										 List<Point> list = ProportionLngLat.caculateKm(pointList);
//											List<Object[]> objList = new ArrayList<Object[]>();
//											for (int i1 = 0; i1 < list.size(); i1++) {
//												Point point = list.get(i1);
//												Object[] obj = new Object[2];
//												obj[0] = Double.toString(point.getKm());
//												obj[1] = point.getId();
//												objList.add(obj);
//											}
//											String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//											 util.batchOperate(upsql, objList);
//									 }else{
//										 System.err.println(pointList2);
//										 pointList.add(0,pointList2.get(pointList2.size()-2));
//										 System.err.println(pointList);
//										 List<Point> list = ProportionLngLat.caculateKm(pointList);
//											List<Object[]> objList = new ArrayList<Object[]>();
//											for (int i1 = 0; i1 < list.size(); i1++) {
//												Point point = list.get(i1);
//												Object[] obj = new Object[2];
//												obj[0] = Double.toString(point.getKm());
//												obj[1] = point.getId();
//												objList.add(obj);
//											}
//											String upsql = "update base_info_rail_data_cece set kilometer_mark=? where id=? ";
//											 util.batchOperate(upsql, objList);
//									 }
//								}	
//							}
//						}
//				}
//			}
//		}
		return pointList;
	}
	/**
	 * @param objList 
	 * @return 
	 * 
	 */
	public  int[] updateRailKm(List<Object[]> objList) {
		for (int i = 0; i < objList.size(); i++) {
			System.err.println(Arrays.toString(objList.get(i)));
		}
		// TODO Auto-generated method stub
		String upsql = "update base_info_rail_data_京哈线 set kilometer_mark=? where id=? ";
		return util.batchOperate(upsql, objList);
	}

	/**
	 * @param railId 
	 * @return 
	 * 
	 */
	public List<Map<String, String>> findner(String lng,String lat, String railId) {
		// TODO Auto-generated method stub
		String point="POINT("+lng+" "+lat+")";
		System.err.println(point);
		System.err.println(railId);
		String st_buffer_strategy=
				"SELECT "+
				"order_number, "+
				"lng, "+
				"lat, "+
				"rail_id, "+
				"rail_name "+
			"FROM "+
				"base_info_rail_data_cece AS a "+
			"WHERE "+
				"( "+
					"st_intersects ( "+
						"a.the_geom, "+
						"st_buffer ( "+
							"st_geomfromtext (?), "+
							"0.015, "+
							"st_buffer_strategy ('point_circle', 32) "+
						") "+
					") = 1 "+
				") and  rail_id=? ";
		List<Map<String, Object>> mapList = util.getMapList(st_buffer_strategy,new Object[]{point,railId});
		Map<String, String> map=new HashMap<String, String>();
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		if(mapList!=null&&mapList.size()>0){
			for(int i=0;i<mapList.size();i++){
				 map=new HashMap<String, String>();
				Map<String,Object> row=mapList.get(i);
				int order_number=ConverterUtils.toInt(row.get("order_number"));
				double lng1=ConverterUtils.toDouble(row.get("lng"));
				double lat1=ConverterUtils.toDouble(row.get("lat"));
				double len=Point.getDistance(lat1, lng1,ConverterUtils.toDouble(lat),ConverterUtils.toDouble(lng));
				map.put("order_number",ConverterUtils.toString(order_number) );
				map.put("lng", ConverterUtils.toString(lng));
				map.put("lat", ConverterUtils.toString(lat));
				map.put("len", ConverterUtils.toString(len));
				list.add(map);
			}
		}
		List<Map<String, String>> list2=new ArrayList<Map<String,String>>();
		list2.addAll(list);
    	ConverterUtils.mapSorts(list);
    	System.err.println(ConverterUtils.toInt(list.get(0).get("order_number")));
    	return list;
	}

	/**
	 * 
	 */
	public List<Map<String, Object>> findStation() {
		// TODO Auto-generated method stub
		String sql=" SELECT r.id,r.lng,r.lat,r.rail_subname,r.middle,s.id as railId FROM `new_station_rail` r ,base_info_rail_stream s where s.rail_name=r.rail_subname  and r.middle is not null and rail_subname is not null";
		return util.getMapList(sql, new Object[]{});
	}


	/**
	 * @param railId
	 * @return
	 */
	public List<Map<String, Object>> findrailname(String railId) {
		// TODO Auto-generated method stub
		String sql=" SELECT * from `base_info_rail_data_京哈线` where rail_name is null ";
		return util.getMapList(sql, new Object[]{});
	}
	
}
