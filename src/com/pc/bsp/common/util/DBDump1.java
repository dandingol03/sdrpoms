/**
 * 
 */
package com.pc.bsp.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang.StringUtils;

import com.pc.exhibition.map.po.PubMapRailDataPo;

/**
 * @author simple
 *
 */
public class DBDump1 {
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript"); 
	private static double EARTH_RADIUS = 6378.137;  
	/** 计算质心
 	 * @param args
	 * @throws UnsupportedEncodingException 
	 * case 1 :view--->date  
	 * 2   view  len
	 * 3   sdrpoms201806 new len
	 */
	
	public static void main(String[] args) throws UnsupportedEncodingException {
System.out.println(getDistance(39.693158843785966,116.35518267091297,39.693158843785966,116.35518267091295));
		String number = "123.456";
		String intNumber = number.substring(0,number.indexOf("."));
		System.out.println(intNumber); 
		Connection conn = null;
		Scanner scan = new Scanner(System.in);
		try{
			conn = GetJDBCConnection.getJDBCConnection();
			Statement quRail = conn.createStatement();
		        // 从键盘接收数据
		        // next方式接收字符串
		        System.out.println("next方式接收：");
		        System.out.println("1.rail_date：");
		        System.out.println("2.base_info_rail_view 长度计算：");
		        System.out.println("3.new_len长度计算：");
		        System.out.println("next方式接收：");
		        System.out.println("5.new_roads--》stream：");
//		        System.out.println("9.station_id");     // TODO station_id
		        System.out.println("11.org");// TODO org
		        
		        // 判断是否还有输入
		        if (scan.hasNext()) {
		            int str1 = scan.nextInt();
		            System.out.println("输入的数据为：" + str1);
					switch (str1) {
					case 1:
						//stre_1531787072314
						//stre_1531787072474
						//'京广高铁上行','京广高铁下行','京广高铁京西联上行','京广高铁京西联下行','京沪高铁下行','京沪高铁上行',
							String sql = "SELECT DISTINCT(id),start from base_info_rail_stream where rail_name  in ('大秦线上行','大秦线下行')  ";
							ResultSet rs = quRail.executeQuery(sql);
							int i = 0;
							while(rs.next()){
								String start="0";
								if(StringUtils.isNotEmpty(rs.getString("start"))) {
									start=rs.getString("start").toString() ;
								}
								String quSql="select id,rail_name as name,ST_AsText(line_geom) as Geom ,positive_negative from base_info_rail_stream where id='"+rs.getString("id")+"'";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								while(rsQuRailData.next()){
									//
									String temp = rsQuRailData.getString("Geom");
									temp = temp.replaceAll("LINESTRING", "");
									temp = temp.replaceAll("\\(", "");
									temp = temp.replaceAll("\\)", "");
									ArrayList<Double> lngArray = new ArrayList<Double>();
									ArrayList<Double> latArray = new ArrayList<Double>();
									while(true){
										int nlng = temp.indexOf(" ");
										String lng=temp.substring(0, nlng);
										lngArray.add(Double.parseDouble(lng));
										temp = temp.substring(nlng+1);
										int nlat = temp.indexOf(",");
										if(nlat<0){
											String lat=temp;
											latArray.add(Double.parseDouble(lat));
											break;
										}else{
											String lat=temp.substring(0, nlat);
											latArray.add(Double.parseDouble(lat));
										}
										temp = temp.substring(nlat+1);
										i++;
									}
									
									double tempDis =ConverterUtils.toDouble(start) ;
									System.out.println(rsQuRailData.getString("name"));
									String railId = rsQuRailData.getString("id");
									String railName = rsQuRailData.getString("name");
									for(int j = 1;j<lngArray.size();j++){
										double tempD=getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
										if(tempD<10){
											lngArray.remove(j);latArray.remove(j);
										}
									}
									String lng="";String lat="";
									for(int j = 0;j<lngArray.size();j++){
//										if(lng.equals(lngArray.get(j).toString())&&lat.equals(latArray.get(j).toString())){
//											lngArray.remove(j);latArray.remove(j);
//										}
////										(lng.substring(0,10)).equals(lngArray.get(j).toString().substring(0,10))&&(lat.substring(0,10)).equals(latArray.get(j).toString().substring(0,10))
//										if(StringUtils.isNotEmpty(lng)&&StringUtils.isNotEmpty(lat)){
//											lng=lng.substring(0,14);
//											lat=lat.substring(0,14);
//											String lng1=lngArray.get(j).toString().substring(0,14);
//											String lat1=latArray.get(j).toString().substring(0,14);
//											if(lng.equals(lng1)&&lat.equals(lat1)){
//												lngArray.remove(j);latArray.remove(j);
//											}	
//										}
										 lng=lngArray.get(j).toString();
										 lat=latArray.get(j).toString();
										String point="POINT("+lng+" "+lat+")";
										int y=j+1;
										String updateSql = "insert into base_info_rail_data_cece (id,rail_id,rail_name,order_number,lng,lat,the_geom)"
												+ "values('"+NextID.getNextID("mapr")+"','"+railId+"','"+railName+"','"+y+"','"+lng+"','"+lat+"',st_geomfromtext('"+point+"'))";
										
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
									}
//									for (int i = list.size() - 1; i >= 0; i--) {
//									    System.out.println(list.get(i));
//									}
									
	//								
//									if(rsQuRailData.getString("positive_negative").equals("1")){
//										/*******************************************0正方向**************************************************/
//										for(int j = 1;j<lngArray.size();j++){
//											tempDis += getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
//											if(j==1){
//												String updateSql = "update base_info_rail_data_ceshi set kilometer_mark='"+start+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+j+"'";
//												Statement updateLine = conn.createStatement();
//												updateLine.execute(updateSql);
////												System.out.println(updateSql);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//												String updateSql1 = "update base_info_rail_data_ceshi set kilometer_mark='"+tempDis+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+(j+1)+"'";
//												Statement updateLine1 = conn.createStatement();
//												updateLine1.execute(updateSql1);
////												System.out.println(updateSql1);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//											}else{
//												String updateSql = "update base_info_rail_data_ceshi set kilometer_mark='"+tempDis+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+(j+1)+"'";
//												Statement updateLine = conn.createStatement();
//												updateLine.execute(updateSql);
////												System.out.println(updateSql);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//											}
//											
//										}
//									}else if(rsQuRailData.getString("positive_negative").equals("0")){
										/***********************************************1反方向**********************************************/
//										for(int j=lngArray.size()-1;j>0;j--){
//											System.out.println(j);
//										tempDis += getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
//										if(j==lngArray.size()-1){
//											String updateSql = "update base_info_rail_data_ceshi set kilometer_mark='"+start+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+(j+1)+"'";
//												Statement updateLine = conn.createStatement();
//												updateLine.execute(updateSql);
////												System.out.println(updateSql);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//												String updateSql1 = "update base_info_rail_data_ceshi set kilometer_mark='"+tempDis+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+(j)+"'";
//												Statement updateLine1= conn.createStatement();
//												updateLine1.execute(updateSql1);
////												System.out.println(updateSql1);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//										}else{
//											String updateSql = "update base_info_rail_data_ceshi set kilometer_mark='"+tempDis+"' where rail_id='"+rsQuRailData.getString("id")+"' and order_number='"+(j)+"'";
//												Statement updateLine = conn.createStatement();
//												updateLine.execute(updateSql);
////												System.out.println(updateSql);
////												System.out.println(latArray.get(j)+" "+lngArray.get(j)+" "+latArray.get(j-1)+" "+lngArray.get(j-1)+"------"+tempDis);
//										}
//									}
////										/*********************************************************************************************/
//									}
									
			
								}
								i++;
							}
						break;
					case 2:
						String sql2 = "SELECT DISTINCT(id) from base_info_rail_view;";
						ResultSet rs2 = quRail.executeQuery(sql2);
						int i2 = 0;
						while(rs2.next()){
							String quSql="select id,rail_stream_id,name,classify,ST_AsText(the_geom) as Geom ,org_id as orgId from base_info_rail_view where the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()') AND id='"+rs2.getString("id")+"'";
							Statement quRailData = conn.createStatement();
							ResultSet rsQuRailData = quRailData.executeQuery(quSql);
							while(rsQuRailData.next()){
								//
								String temp = rsQuRailData.getString("Geom");
								double tempDis=ProportionLngLat.caculateLength(temp);
//								System.err.println(rsQuRailData.getString("id")+" "+rsQuRailData.getString("orgId")+" "+tempDis);
								//
								String updateSql = "update base_info_rail_view set length='"+tempDis+"' where rail_stream_id='"+rsQuRailData.getString("rail_stream_id")+"' and org_id='"+rsQuRailData.getString("orgId")+"'"
										+ " and the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()')";
								System.out.println(updateSql);
								Statement updateLine = conn.createStatement();
								updateLine.execute(updateSql);
							}
//							System.out.println(i);
							i2++;
						}
						break;
						case 3:
							String sql3 = "select ST_AsText(line_geom) as Geom,rail_name as name from base_info_rail_stream ";
							ResultSet rs3 = quRail.executeQuery(sql3);
							int i3 = 0;
							while(rs3.next()){
								String temp = rs3.getString("Geom");
								temp = temp.replaceAll("LINESTRING", "");
								temp = temp.replaceAll("\\(", "");
								temp = temp.replaceAll("\\)", "");
								ArrayList<Double> lngArray = new ArrayList<Double>();
								ArrayList<Double> latArray = new ArrayList<Double>();
								while(true){
									int nlng = temp.indexOf(" ");
									String lng=temp.substring(0, nlng);
									lngArray.add(Double.parseDouble(lng));
									temp = temp.substring(nlng+1);
									int nlat = temp.indexOf(",");
									if(nlat<0){
										String lat=temp;
										latArray.add(Double.parseDouble(lat));
										break;
									}else{
										String lat=temp.substring(0, nlat);
										latArray.add(Double.parseDouble(lat));
									}
									temp = temp.substring(nlat+1);
									i3++;
								}
								
								double tempDis = 0;
								for(int j = 1;j<lngArray.size();j++){
									tempDis += getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
								}
								System.out.println(tempDis+"------"+rs3.getString("name"));
//								System.out.println(tempDis);
							}
							break;
						case 4:
							int i4 = 0;;
							String t = "";
							String sql4 = "select id from base_info_rail";
							ResultSet rs4 = quRail.executeQuery(sql4);
							while(rs4.next()){
								String quSql = "select lng,lat from base_info_rail_data where rail_id = '"+rs4.getString("id")+"' order by cast(order_number as SIGNED)";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								String lineString = "LINESTRING(";
								while(rsQuRailData.next()){
									lineString+=rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+",";
								}
								lineString = lineString.substring(0,lineString.length()-1);
								lineString+=")";
								String updateSql = "update base_info_rail set line_geom=ST_GeomFromText('"+lineString+"') where id='"+rs4.getString("id")+"'";
								Statement updateLine = conn.createStatement();
								updateLine.execute(updateSql);
								updateSql = "update base_info_rail set range_geom=(ST_BUFFER(ST_GeomFromText('"+lineString+"'),0.015,ST_Buffer_Strategy('end_round',32),ST_Buffer_Strategy('join_round',32))) where id='"+rs4.getString("id")+"'";
								//Statement updateRange = conn.createStatement();
								//updateRange.execute(updateSql);
								if(i4!=21&&i4!=22&&i4!=49){
									Statement updateRange = conn.createStatement();
									updateRange.execute(updateSql);
								}else{
									System.out.println(rs4.getString("id"));
								}
								System.out.println(i4);
								i4++;
							}
							break;
						case 5:
							int i41 = 0;;
							String t1 = "";
							
							String sql41 = "select id from new_roads_20180905 where id in ('37','38')";
//									+ "in('0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64','65','66','67','68','69','70','71','72','73','74','75','76','77','78')";
							ResultSet rs41 = quRail.executeQuery(sql41);
							while(rs41.next()){
								String quSql = "select ID,Name,"
//										+ "qslc,zdlc,qdwz,zdwz,zongchang,zhengfan,"
										+ "ST_AsText(the_geom) as Geom from new_roads_20180905 where id = '"+rs41.getString("id")+"' ";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								String geom="";
								while(rsQuRailData.next()){
									geom=rsQuRailData.getString("Geom").replace("MULTILINESTRING((", "LINESTRING(");
									geom = geom.substring(0,geom.length() - 1);
									System.out.println(i41);
									String updateSql = "UPDATE base_info_rail_stream SET "
//											+ "start='"+rsQuRailData.getString("qslc")+"',"
//													+ "end='"+rsQuRailData.getString("zdlc")+"',"
//															+ "start_address='"+rsQuRailData.getString("qdwz")+"',"
//																	+ "end_address='"+rsQuRailData.getString("zdwz")+"',"
//																			+ "length='"+rsQuRailData.getString("zongchang")+"',"
//																					+ "positive_negative='"+rsQuRailData.getString("zhengfan")+"',"
																							+ "line_geom = st_geomfromtext('"+geom+"') "
																									+ "WHERE rail_name = '"+rsQuRailData.getString("Name")+"'";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
//									if(id.equals("16")){
//										String updateSql = "UPDATE base_info_rail_stream SET line_geom =st_geomfromtext( '"+geom+"') WHERE id = 'stre_1531876720781' ";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
//									}else
//										
//														if(id.equals("17")){
//															String updateSql = "UPDATE base_info_rail_stream SET line_geom = st_geomfromtext('"+geom+"') WHERE id = 'stre_1531876721745' ";
//															Statement updateLine = conn.createStatement();
//															updateLine.execute(updateSql);
//														}
//									rail_name
//									positive_negative
//									line_geom
									
//									System.out.println(geom);
//									String positive_negative="";
//									if(StringUtils.isNotBlank(rsQuRailData.getString("positive_negative"))){
//										positive_negative=rsQuRailData.getString("positive_negative");
//									}
//									String updateSql = "insert into base_info_rail_stream (id,rail_name,positive_negative,line_geom,stream)"
//											+ "values('"+NextID.getNextID("stre")+"','"+rsQuRailData.getString("name")+"','"+positive_negative+"',st_geomfromtext('"+geom+"'),'"+rsQuRailData.getString("remark")+"' )";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
								}
								i41++;
							}
							break;	
						case 6:
//							String km="1000*6+32.02";
//							System.out.println(jse.eval(km));
							int i411 = 0;;
							String t11 = "";
							String sql411 = "select id from new_station";
							ResultSet rs411 = quRail.executeQuery(sql411);
							while(rs411.next()){
//								,ST_AsText(the_geom) as Geom
								String quSql = "select id,name,km,lng,lat,rail_name,remark from new_station where id = '"+rs411.getString("id")+"' ";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								String km="";
								while(rsQuRailData.next()){
									if(StringUtils.isBlank(rsQuRailData.getString("name"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lng"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lat"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("km"))){
										System.out.println("");
										String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
										String updateSql = "insert into base_info_part_station (id,name,middle,lng,lat,the_geom,remark)"
												+ "values('"+NextID.getNextID("psta")+"','"+rsQuRailData.getString("name")+"','','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),'"+rsQuRailData.getString("remark")+"')";
											Statement updateLine = conn.createStatement();
											updateLine.execute(updateSql);
										continue;
									}
									km=rsQuRailData.getString("km").replace("K","1000*").replace("k","1000*").replace("+0","+").replace("+0","+");
									Object KM=jse.eval(km);
									System.out.println(KM);
									String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
									String updateSql = "insert into base_info_part_station (id,name,middle,lng,lat,the_geom,remark)"
											+ "values('"+NextID.getNextID("psta")+"','"+rsQuRailData.getString("name")+"','"+KM+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),'"+rsQuRailData.getString("remark")+"')";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
								}
								i411++;
							}
								String sql11="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_station d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
								ResultSet rs4111 = quRail.executeQuery(sql11);
								while(rs4111.next()){
									String updateSql = "UPDATE base_info_part_station SET org_id = '"+rs4111.getString("orgId")+"' WHERE id = '"+rs4111.getString("id")+"' ";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
								}
								break;	
						case 7:
//							String km="1000*6+32.02";
//							System.out.println(jse.eval(km));
							int i4111 = 0;;
							String t111 = "";
							String sql4111 = "select id from new_jzj";
							ResultSet rs41111 = quRail.executeQuery(sql4111);
							while(rs41111.next()){
//								,ST_AsText(the_geom) as Geom
								String quSql = "select id,name,km,lng,lat,rail_name,station_name from new_jzj where id = '"+rs41111.getString("id")+"' ";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								String km="";
								while(rsQuRailData.next()){
									if(StringUtils.isBlank(rsQuRailData.getString("name"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lng"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lat"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("km"))){
										System.out.println("");
										String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
										String updateSql = "insert into base_info_part_station_signal (id,name,middle,lng,lat,the_geom,station_name,rail_name)"
												+ "values('"+NextID.getNextID("sign")+"','"+rsQuRailData.getString("name")+"','','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),'"+rsQuRailData.getString("station_name")+"','"+rsQuRailData.getString("rail_name")+"')";
											Statement updateLine = conn.createStatement();
											updateLine.execute(updateSql);
										continue;
									}
									km=rsQuRailData.getString("km").replace("K","1000*").replace("k","1000*").replace("+0","+").replace("+0","+");
								
									Object KM=jse.eval(km);
									System.out.println(KM);
									String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
									String updateSql = "insert into base_info_part_station_signal (id,name,middle,lng,lat,the_geom,station_name,rail_name)"
											+ "values('"+NextID.getNextID("sign")+"','"+rsQuRailData.getString("name")+"','"+KM+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),'"+rsQuRailData.getString("station_name")+"','"+rsQuRailData.getString("rail_name")+"')";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
								}
								i4111++;
							}
								String sql111="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_station_signal d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
								ResultSet rs411111 = quRail.executeQuery(sql111);
								while(rs411111.next()){
									String updateSql = "UPDATE base_info_part_station_signal SET org_id = '"+rs411111.getString("orgId")+"' WHERE id = '"+rs411111.getString("id")+"' ";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
								}
								break;	
						case 8:
//							String km="1000*6+32.02";
//							System.out.println(jse.eval(km));
							int i41111 = 0;;
							String t1111 = "";
							String sql41111 = "select id from new_qhsd_201809 where ID in('174','175')";
							ResultSet rs4111111 = quRail.executeQuery(sql41111);
							while(rs4111111.next()){
//								,ST_AsText(the_geom) as Geom
								String quSql = "select id,name,through,lng,type,lat,ST_AsText(the_geom) as the_geom from new_qhsd_201809 where id = '"+rs4111111.getString("id")+"' ";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								String km="";
								while(rsQuRailData.next()){
									if(StringUtils.isBlank(rsQuRailData.getString("name"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lng"))){
										System.out.println("未完成--");
										continue;
									}
									if(StringUtils.isBlank(rsQuRailData.getString("lat"))){
										System.out.println("未完成--");
										continue;
									}
									System.out.println(i41111);
//									 if(StringUtils.isEmpty(rsQuRailData.getString("layer"))){
//											String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
//											//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')   
//											String updateSql = "insert into base_info_part_bridge (cross_span,id,name,lng,lat,the_geom,remark,range_geom)"
//													+ "values('02','"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),'桥',ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
//												Statement updateLine = conn.createStatement();
//												updateLine.execute(updateSql);
//										}else 
//											if(rsQuRailData.getString("layer").equals("桥")){
//											is_oversize
											if(StringUtils.equals(rsQuRailData.getString("type"),"特大桥")){
												String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
												String updateSql ="";
												if(rsQuRailData.getString("through").equals("上跨")){
													 updateSql = "insert into base_info_part_bridge (is_oversize,cross_span,id,name,lng,lat,the_geom,range_geom)"
															//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
															+ "values('1','02','"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
													Statement updateLine = conn.createStatement();
														updateLine.execute(updateSql);
												}else{
													 updateSql = "insert into base_info_part_bridge (is_oversize,cross_span,id,name,lng,lat,the_geom,range_geom)"
															//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
															+ "values('1','01','"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
													Statement updateLine = conn.createStatement();
														updateLine.execute(updateSql);
												}
											}else{
												String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
												String updateSql ="";
												if(rsQuRailData.getString("through").equals("上跨")){
													updateSql = "insert into base_info_part_bridge (is_oversize,cross_span,id,name,lng,lat,the_geom,range_geom)"
															//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
															
															+ "values('0','02','"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
													Statement updateLine = conn.createStatement();
														updateLine.execute(updateSql);
												}else{
													updateSql = "insert into base_info_part_bridge (is_oversize,cross_span,id,name,lng,lat,the_geom,range_geom)"
															//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
															
															+ "values('0','01','"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
													Statement updateLine = conn.createStatement();
														updateLine.execute(updateSql);
												}
											}
									
//									}
//										else
//									if(rsQuRailData.getString("layer").equals("道口")){
//										String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
//										String updateSql = "insert into base_info_part_junction (id,name,lng,lat,the_geom,range_geom)"
//												//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
//												+ "values('"+NextID.getNextID("brid")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";	
//										Statement updateLine = conn.createStatement();
//											updateLine.execute(updateSql);
//									}else
//									if(rsQuRailData.getString("layer").equals("涵洞")){
//										String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
//										String updateSql = "insert into base_info_part_culvert (cross_span,id,name,lng,lat,the_geom,range_geom)"
//												//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
//												+ "values('02','"+NextID.getNextID("culv")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
//										Statement updateLine = conn.createStatement();
//											updateLine.execute(updateSql);
//									}
//									else
//									if(rsQuRailData.getString("layer").equals("隧道")){
//										String point="POINT("+rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+")";
//											
//										String updateSql = "insert into base_info_part_tunnel (id,name,lng,lat,the_geom,range_geom)"
//												//,st_geomfromtext('"+rsQuRailData.getString("the_geom")+"')    ,range_geom
//												
//												+ "values('"+NextID.getNextID("tunn")+"','"+rsQuRailData.getString("name")+"','"+rsQuRailData.getString("lng")+"','"+rsQuRailData.getString("lat")+"',st_geomfromtext('"+point+"'),ST_GeomFromText('"+rsQuRailData.getString("the_geom")+"'))";
//										
//										Statement updateLine = conn.createStatement();
//											updateLine.execute(updateSql);
//									}
								}
								i41111++;
							}
//								String sql1111="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_bridge d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
//								ResultSet rs41111111 = quRail.executeQuery(sql1111);
//								while(rs41111111.next()){
//									String updateSql = "UPDATE base_info_part_bridge SET org_id = '"+rs41111111.getString("orgId")+"' WHERE id = '"+rs41111111.getString("id")+"' ";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
//								}
//								String sql11111="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_junction d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
//								ResultSet rs411111111 = quRail.executeQuery(sql11111);
//								while(rs411111111.next()){
//									String updateSql = "UPDATE base_info_part_junction SET org_id = '"+rs411111111.getString("orgId")+"' WHERE id = '"+rs411111111.getString("id")+"' ";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
//								}
//								String sql111111="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_culvert d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
//								ResultSet rs4111111111 = quRail.executeQuery(sql111111);
//								while(rs4111111111.next()){
//									String updateSql = "UPDATE base_info_part_culvert SET org_id = '"+rs4111111111.getString("orgId")+"' WHERE id = '"+rs4111111111.getString("id")+"' ";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
//								}
//								String sql1111111="SELECT p.ORG_ID as orgId,d.id,d.`name` from base_info_part_tunnel d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 GROUP BY d.id";
//								ResultSet rs41111111111 = quRail.executeQuery(sql1111111);
//								while(rs41111111111.next()){
//									String updateSql = "UPDATE base_info_part_tunnel SET org_id = '"+rs41111111111.getString("orgId")+"' WHERE id = '"+rs41111111111.getString("id")+"' ";
//										Statement updateLine = conn.createStatement();
//										updateLine.execute(updateSql);
//								}
								break;	
						case 9:
							// TODO station_id
//							String km="1000*6+32.02";
//							System.out.println(jse.eval(km));
							int i411111 = 0;;
							String t11111 = "";
							String sql411111 = "select id,station_name,rail_name from base_info_part_station_signal";
							ResultSet rs411111111111 = quRail.executeQuery(sql411111);
							while(rs411111111111.next()){
//								,ST_AsText(the_geom) as Geom
								String quSql = "select id from base_info_part_station where name = '"+rs411111111111.getString("station_name")+"' ";
								Statement quRailData = conn.createStatement();
								ResultSet rsQuRailData = quRailData.executeQuery(quSql);
								
								
								String quSql1 = "select id from base_info_part_station where name = '"+rs411111111111.getString("station_name")+"' ";
								Statement quRailData1 = conn.createStatement();
								ResultSet rsQuRailData1 = quRailData1.executeQuery(quSql1);
								String km="";
								while(rsQuRailData1.next()){
									if(StringUtils.isEmpty(rsQuRailData1.getString("id"))){
										String updateSql = "UPDATE base_info_part_station_signal SET station_id = '' WHERE id = '"+rs411111111111.getString("id")+"' ";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
									}
								String updateSql = "UPDATE base_info_part_station_signal SET station_id = '"+rsQuRailData1.getString("id")+"' WHERE id = '"+rs411111111111.getString("id")+"' ";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
								}
								i411111++;
							}
								break;	
						case 11:
							// TODO org
							String quSql1 = "select DISTINCT(id) as id from base_info_rail_data_ceshi where org_id is null and rail_name in ('京津城际上行') ";
							Statement quRailData1 = conn.createStatement();
							ResultSet rsQuRailData1 = quRailData1.executeQuery(quSql1);
							String km="";
							while(rsQuRailData1.next()){
								String sql11221="SELECT p.ORG_ID as orgId,d.id from base_info_rail_data_ceshi d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 and id='"+rsQuRailData1.getString("id")+"'";
//							String sql11221="SELECT p.ORG_ID as orgId,d.id from base_info_defence_broadcast d JOIN pub_org p WHERE(ST_Intersects (d.the_geom, p.the_geom ) = 1)and CHAR_LENGTH(p.ORG_ID)=9 ";
								ResultSet sss = quRail.executeQuery(sql11221);
								while(sss.next()){
									System.out.println(111);
									String updateSql = "UPDATE base_info_rail_data_ceshi SET org_id = '"+sss.getString("orgId")+"' WHERE id = '"+sss.getString("id")+"' ";
										Statement updateLine = conn.createStatement();
										updateLine.execute(updateSql);
								}	
							}
							break;
						case 12:
							String xq1="select id,name,lng,lat  from base_info_part_station ";
							ResultSet sq1 = quRail.executeQuery(xq1);
							int tem=0;
					        // 1：利用File类找到要操作的对象
				            File file = new File("D:" + File.separator + "demo" + File.separator + "point_station.kml");
				            if(!file.getParentFile().exists()){
				                file.getParentFile().mkdirs();
				            }
				            //2：准备输出流
				            Writer out = new FileWriter(file);
				            out.write("<?xml version='1.0' encoding='utf-8' ?>");	
				            out.write("<kml xmlns='http://www.opengis.net/kml/2.2'>");	
				            out.write("<Document id='root_doc'>");	
				            out.write("<Schema name='axs' id='axs'>");	
				            out.write("	<SimpleField name='Style' type='string'></SimpleField>");	
				            out.write("</Schema>");	
				            out.write("<Folder><name>axs</name>");	
							while(sq1.next()){if(StringUtils.isEmpty(sq1.getString("lat").toString())){continue;}
								System.out.println(sq1.getString("name").toString());
								tem++;
//								i=ConverterUtils.toInt(sq1.getString("a").toString()) ;
								  out.write("<Placemark>");
									out.write("<name>Point#"+sq1.getString("id").toString()+"__"+sq1.getString("name").toString()+"__"+tem+"</name>");
									out.write("<ExtendedData><SchemaData schemaUrl='#axs'>");
										out.write("<SimpleData name='Style'>SYMBOL(id:ogr-sym-3,c:#4d90feff,s:10.000000px,o:#000000ff);LABEL(f:黑体,s:11.000000pt,c:#ffffffff,w:100.000000,o:#000000ff)</SimpleData>");
									out.write("</SchemaData></ExtendedData>");
								      out.write("<Point><coordinates>"+sq1.getString("lng").toString()+","+sq1.getString("lat").toString()+"</coordinates></Point>");
								  out.write("</Placemark>");	
						}
							out.write("</Folder>");	
							out.write("</Document></kml>");	
					          out.close();
							break;
						case 13:
							String sqlxx="SELECT id,level,nature,name from base_info_part_station_a ";
							ResultSet Res = quRail.executeQuery(sqlxx);
							while(Res.next()){
								String updateSql = "UPDATE base_info_part_station SET level = '"+Res.getString("level")+"',nature = '"+Res.getString("nature")+"' WHERE name = '"+Res.getString("name")+"' ";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
							}
							break;
						case 14:
							String sqlx="SELECT id,name,rail_name,signal_down as sd,signal_up as su from new_signal ";
							ResultSet Resu = quRail.executeQuery(sqlx);
							while(Resu.next()){
								String sqlxx1 = "SELECT id,name from base_info_part_station WHERE name = '"+Resu.getString("name")+"' ";
								Statement quRail1 = conn.createStatement();
								ResultSet Resux = quRail1.executeQuery(sqlxx1);
//								while(Resux.next()){
//									String updateSql = "insert into new_signal (id,name,middle,rail_name,station_name,station_id)values('"+NextID.getNextID("sign")+"','"+Resu.getString("rail_name")+"-"+Resux.getString("name")+"-进站机-下"+"','"+Resu.getString("sd")+"','"+Resu.getString("rail_name")+"','"+Resux.getString("name")+"','"+Resux.getString("id")+"')";
//									Statement updateLine = conn.createStatement();
//									updateLine.execute(updateSql);
//								}
								while(Resux.next()){
									String updateSql = "insert into base_info_part_station_signal (id,name,middle,rail_name,station_name,station_id)values('"+NextID.getNextID("sign")+"','"+Resu.getString("rail_name")+"-"+Resux.getString("name")+"-进站机-下"+"','"+Resu.getString("sd")+"','"+Resu.getString("rail_name")+"','"+Resux.getString("name")+"','"+Resux.getString("id")+"')";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
									String updateSql1 = "insert into base_info_part_station_signal (id,name,middle,rail_name,station_name,station_id)values('"+NextID.getNextID("sign")+"','"+Resu.getString("rail_name")+"-"+Resux.getString("name")+"-进站机-上"+"','"+Resu.getString("su")+"','"+Resu.getString("rail_name")+"','"+Resux.getString("name")+"','"+Resux.getString("id")+"')";
									Statement updateLine1 = conn.createStatement();
									updateLine1.execute(updateSql1);
								}
							}
							break;
						case 15:
							String sqlxx1="SELECT id,name from base_info_part_station ";
							ResultSet Res1 = quRail.executeQuery(sqlxx1);
							while(Res1.next()){
								String sqlxx11 = "SELECT rail_name from new_signal WHERE name = '"+Res1.getString("name")+"' ";
								Statement quRail1 = conn.createStatement();
								ResultSet Resux = quRail1.executeQuery(sqlxx11);
								while(Resux.next()){
									String updateSql = "UPDATE base_info_part_station SET rail_name = '"+Resux.getString("rail_name")+"' WHERE id = '"+Res1.getString("id")+"' ";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
								}
							}
							break;
						case 16:
							String sqlxx11="SELECT id from video_information ";
							List<String> ls=new ArrayList<String>();
							ResultSet Res11 = quRail.executeQuery(sqlxx11);
							while(Res11.next()){
								ls.add(Res11.getString("id").toString());
							}
								String sqlxx111 = "SELECT DISTINCT(id) from video_information_view ";
								Statement quRail1 = conn.createStatement();
								ResultSet Resux = quRail1.executeQuery(sqlxx111);
								while(Resux.next()){
									ls.remove(Resux.getString("id").toString());
								}
								System.out.println(ls);
								for (int j = 0; j < ls.size(); j++) {
									String updateSql = "UPDATE video_information_copy SET remark2 = '0' WHERE id = '"+ls.get(j)+"' ";
									Statement updateLine = conn.createStatement();
									updateLine.execute(updateSql);
								}
									
							break;
						case 17:
							String sqlxx1111="SELECT v.id,o.org_name as name from video_information as v LEFT JOIN pub_org as o on o.ORG_ID=SUBSTR(v.org_id,1,6) ";
							List<String> ls1=new ArrayList<String>();
							ResultSet Res111 = quRail.executeQuery(sqlxx1111);
							while(Res111.next()){
								if(StringUtils.isEmpty(Res111.getString("name"))){
									continue;
								}
								String updateSql = "UPDATE video_information_copy SET org_name = '"+Res111.getString("name").toString()+"' WHERE id = '"+Res111.getString("id").toString()+"' ";
								Statement updateLine = conn.createStatement();
								updateLine.execute(updateSql);
							}
							break;
						case 18:
							String sql3e = "select ST_AsText(range_geom) as Geom from base_info_part_bridge where is_oversize='0'  ";
							ResultSet rs3e = quRail.executeQuery(sql3e);
							System.out.println(rs3e.next());
							int i3e = 0;
							while(rs3e.next()){
								String temp = rs3e.getString("Geom");
								temp = temp.replaceAll("MULTIPOLYGON", "");
								temp = temp.replaceAll("\\(\\(\\(", "");
								temp = temp.replaceAll("\\)\\)\\)", "");
								ArrayList<Double> lngArray = new ArrayList<Double>();
								ArrayList<Double> latArray = new ArrayList<Double>();
								while(true){
									int nlng = temp.indexOf(" ");
									String lng=temp.substring(0, nlng);
									lngArray.add(Double.parseDouble(lng));
									temp = temp.substring(nlng+1);
									int nlat = temp.indexOf(",");
									if(nlat<0){
										String lat=temp;
										latArray.add(Double.parseDouble(lat));
										break;
									}else{
										String lat=temp.substring(0, nlat);
										latArray.add(Double.parseDouble(lat));
									}
									temp = temp.substring(nlat+1);
									i3e++;
								}
								System.out.println(lngArray);System.out.println(latArray);
								if(!(lngArray.get(0).toString().equals(lngArray.get(lngArray.size()-1).toString()))){
									System.out.println(i3e);
								}
								if(!(latArray.get(0).toString().equals(latArray.get(latArray.size()-1).toString()))){
									System.out.println(i3e);
								}
							}
							break;
						case 19:
							String sqlxx11111="SELECT id from base_info_rail_copy ";
							ResultSet Res1111 = quRail.executeQuery(sqlxx11111);
							StringBuffer SB=new StringBuffer();
							while(Res1111.next()){
								System.err.println(Res1111.getString("id").toString());
								String sqlxx111111="SELECT ST_AsText(line_geom) as geom from base_info_rail_stream  WHERE rail_id = '"+Res1111.getString("id").toString()+"' ";
								Statement quRail11 = conn.createStatement();
								ResultSet Res11111 = quRail11.executeQuery(sqlxx111111);
//								SB.append("MULTILINESTRING(");
								while(Res11111.next()){
									SB.append(Res11111.getString("geom").toString().replaceAll("LINESTRING", ""));
									SB.append(",");
								}
//								SB.append(")");
								System.err.println(SB.toString());
								String lineString=SB.toString();
								lineString = lineString.substring(0,lineString.length() - 1);
								String updateSql = "UPDATE base_info_rail_copy SET line_geom = ST_GeomFromText('MULTILINESTRING("+lineString+")') WHERE id = '"+Res1111.getString("id").toString()+"' ";
								Statement updateLine = conn.createStatement();
								updateLine.execute(updateSql);
							}
							break;
						case 20:
				        	String sqlsql = " select " +
				            		"t.id as \"id\", " +
				    			"t.order_number as \"orderNumber\", " +
				    			"t.lng as \"lng\", " +
				    			"t.lat as \"lat\", " +
				    			"t.kilometer_mark as \"kilometerMark\" " +
				    			"from base_info_rail_data t " +
				    			"where 1=1 AND t.rail_id='stre_1531876724864' ORDER BY cast(t.order_number  AS signed) ASC";
							ResultSet ResRes = quRail.executeQuery(sqlsql);
							List<Point> points = new ArrayList<>();
							int temp = 0;
							while(ResRes.next()){
								Object lng =ResRes.getString("lng");
								Object lat=ResRes.getString("lat");
								Object kilometerMark = ResRes.getString("kilometerMark");
								Object orderNumber = ResRes.getString("orderNumber");
								if (kilometerMark != null
										&& !"".equals(kilometerMark.toString().trim())) {
									    ++temp;
									    points.add(new Point(Double.parseDouble(lng.toString()),
										    Double.parseDouble(lat.toString()), Double
											    .parseDouble(kilometerMark.toString()),
										    true,ConverterUtils.toInt(orderNumber)));
									} else {
									    points.add(new Point(Double.parseDouble(lng.toString()),
										    Double.parseDouble(lat.toString()),ConverterUtils.toInt(orderNumber)));
									}
							}
							System.out.println(points);
							//******************最近************
//							String st_buffer_strategy=
//							"SELECT "+
//							"order_number, "+
//							"lng, "+
//							"lat, "+
//							"rail_id, "+
//							"rail_name "+
//						"FROM "+
//							"base_info_rail_data_ceshi AS a "+
//						"WHERE "+
//							"( "+
//								"st_intersects ( "+
//									"a.the_geom, "+
//									"st_buffer ( "+
//										"st_geomfromtext ('POINT(116.364890187979 39.8557765781879)'), "+
//										"0.015, "+
//										"st_buffer_strategy ('point_circle', 32) "+
//									") "+
//								") = 1 "+
//							") ";
//							ResultSet st_buffer_strategyRes = quRail.executeQuery(st_buffer_strategy);
//							List<Map<String, String>> list=new ArrayList<Map<String,String>>();
//							Map<String, String> map=new HashMap<String, String>();
//							while(st_buffer_strategyRes.next()){
//								 map=new HashMap<String, String>();
//								//最近
//								int order_number=ConverterUtils.toInt(st_buffer_strategyRes.getString("order_number"));
//								double lng=ConverterUtils.toDouble(st_buffer_strategyRes.getString("lng"));
//								double lat=ConverterUtils.toDouble(st_buffer_strategyRes.getString("lat"));
//								double len=Point.getDistance(lat, lng, 39.8557765781879, 116.364890187979);
//								map.put("order_number",ConverterUtils.toString(order_number) );
//								map.put("lng", ConverterUtils.toString(lng));
//								map.put("lat", ConverterUtils.toString(lat));
//								map.put("len", ConverterUtils.toString(len));
//								list.add(map);
//							}
//							List<Map<String, String>> list2=new ArrayList<Map<String,String>>();
//							list2.addAll(list);
//				        	ConverterUtils.mapSorts(list);
//				        	System.out.println(ConverterUtils.toInt(list.get(0).get("order_number")));
//				        	int orderNum=ConverterUtils.toInt(list.get(0).get("order_number"));
				        	//取出
							//*********************最近*********
							
							
							if (temp == 2) {
							    List<Point> points1 = new ArrayList<>();
							    points1 = ProportionLngLat.caculateKm(points);
							    PubMapRailDataPo pubMapRailDataPo;
							    for (int i1 = 0; i1 < points1.size(); i1++) {
									Point point = points1.get(i1);
									if (point.getKm() == 0.0) {
									} else {
									    pubMapRailDataPo = new PubMapRailDataPo();
									    pubMapRailDataPo.setLng(Double.toString(point.getLng()));
									    pubMapRailDataPo.setLat(Double.toString(point.getLat()));
									    pubMapRailDataPo.setKilometerMark(Double.toString(point
										    .getKm()));
									    String upsqlupsql = "update base_info_rail_data set " +
												"kilometer_mark="+Double.toString(point.getKm())+" " +
												"where lng='"+Double.toString(point.getLng())+"' and lat='"+Double.toString(point.getLat())+"' "+
												"and rail_id='stre_1531876724864' ";
									    Statement updateLine = conn.createStatement();
										updateLine.execute(upsqlupsql);
									}
								    }
							}
							break;
								
					}
		        }
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}finally{
			if(null != conn){
				GetJDBCConnection.closeConnection(conn);
				 
			}
			scan.close();
			System.out.println("Finish");
		}
		
	}

    public static void charOutStream() throws Exception{
        // 1：利用File类找到要操作的对象
            File file = new File("D:" + File.separator + "demo" + File.separator + "test.txt");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            
            //2：准备输出流
            Writer out = new FileWriter(file);
            out.write("测试字符流, 哈哈");
            out.close();
            
        }

    public static void byteOutStream() throws Exception {
        
        //1:使用File类创建一个要操作的文件路径
        File file = new File("D:" + File.separator + "demo" + File.separator + "test.txt"); 
        if(!file.getParentFile().exists()){ //如果文件的目录不存在
            file.getParentFile().mkdirs(); //创建目录
            
        }
        
        //2: 实例化OutputString 对象
        OutputStream output = new FileOutputStream(file);
        
        //3: 准备好实现内容的输出
        
        String msg = "HelloWorld";
        //将字符串变为字节数组
        byte data[] = msg.getBytes();
        output.write(data);
        //4: 资源操作的最后必须关闭
        output.close();
        
        
	}
	
	//view  区域长度计算
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		Connection conn = null;
//		try{
//			conn = GetJDBCConnection.getJDBCConnection();
//			Statement quRail = conn.createStatement();
//			String sql = "SELECT DISTINCT(id) from base_info_rail_view;";
//			ResultSet rs = quRail.executeQuery(sql);
//			int i = 0;;
//			while(rs.next()){
//				String quSql="select id,name,classify,ST_AsText(the_geom) as Geom ,org_id as orgId from base_info_rail_view where the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()') AND id='"+rs.getString("id")+"'";
//				Statement quRailData = conn.createStatement();
//				ResultSet rsQuRailData = quRailData.executeQuery(quSql);
//				while(rsQuRailData.next()){
//					//
//					String temp = rsQuRailData.getString("Geom");
//					double tempDis=ProportionLngLat.caculateLength(temp);
////					System.err.println(rsQuRailData.getString("id")+" "+rsQuRailData.getString("orgId")+" "+tempDis);
//					//
//					String updateSql = "update base_info_rail_view set length='"+tempDis+"' where id='"+rsQuRailData.getString("id")+"' and org_id='"+rsQuRailData.getString("orgId")+"'"
//							+ " and the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()')";
////					System.out.println(updateSql);
//					Statement updateLine = conn.createStatement();
//					updateLine.execute(updateSql);
//				}
////				System.out.println(i);
//				i++;
//			}
//				
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			System.out.println(e);
//		}finally{
//			if(null != conn){
//				GetJDBCConnection.closeConnection(conn);
//			}
//			System.out.println("Finish");
//		}
//		
//	}
	//计算铁路长度
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		Connection conn = null;
//		try{
//			conn = GetJDBCConnection.getJDBCConnection();
//			Statement quRail = conn.createStatement();
//			String sql = "select ST_AsText(the_geom) as Geom from new_roads where id = '8'";
//			ResultSet rs = quRail.executeQuery(sql);
//			int i = 0;
//			while(rs.next()){
//				String temp = rs.getString("Geom");
//				temp = temp.replaceAll("MULTILINESTRING", "");
//				temp = temp.replaceAll("\\(", "");
//				temp = temp.replaceAll("\\)", "");
//				ArrayList<Double> lngArray = new ArrayList<Double>();
//				ArrayList<Double> latArray = new ArrayList<Double>();
//				while(true){
//					int nlng = temp.indexOf(" ");
//					String lng=temp.substring(0, nlng);
//					lngArray.add(Double.parseDouble(lng));
//					temp = temp.substring(nlng+1);
//					int nlat = temp.indexOf(",");
//					if(nlat<0){
//						String lat=temp;
//						latArray.add(Double.parseDouble(lat));
//						break;
//					}else{
//						String lat=temp.substring(0, nlat);
//						latArray.add(Double.parseDouble(lat));
//					}
//					temp = temp.substring(nlat+1);
//					i++;
//				}
//				
//				double tempDis = 0;
//				double tt = 0;
//				for(int j = 1;j<lngArray.size();j++){
//					tt = Math.sqrt(Math.pow((lngArray.get(j)-lngArray.get(j-1)), 2)+Math.pow((latArray.get(j)-latArray.get(j-1)), 2));
//					tempDis += getDistance(latArray.get(j),lngArray.get(j),latArray.get(j-1),lngArray.get(j-1));
//				}
//				System.out.println(tempDis);
//			}
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			System.out.println(e);
//		}finally{
//			if(null != conn){
//				GetJDBCConnection.closeConnection(conn);
//			}
//			System.out.println("Finish");
//		}
//	}
	
	
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
	//temp = temp.replaceAll(",", " ");
	//temp = temp.replaceAll("\\] \\[", ",");
	//temp = temp.replaceAll("\\]", ")");
	//temp = temp.replaceAll("\\[", "(");
	//System.out.println("POLYGON"+temp);
//	String quSql = "select a.range as Geom from base_info_part_station_yard_rel a where id = '"+rs.getString("id")+"' ";
//	Statement quRailData = conn.createStatement();
//	ResultSet rsQuRailData = quRailData.executeQuery(quSql);
//	while(rsQuRailData.next()){
//		String temp = rsQuRailData.getString("Geom");
//		System.out.println(temp);
//		temp = temp.replaceAll(",", " ");
//		temp = temp.replaceAll("\\] \\[", ",");
//		temp = temp.replaceAll("\\]", ")");
//		temp = temp.replaceAll("\\[", "(");
//		System.out.println("POLYGON"+temp);
//		String quSql1 = "select ST_AsText(ST_Centroid(ST_GeomFromText('POLYGON"+temp+"'))) as CenterPoint ";
//		Statement quRailData1 = conn.createStatement();
//		ResultSet rsQuRailData1 = quRailData1.executeQuery(quSql1);
//		while(rsQuRailData1.next()){
//			String temp1 = rsQuRailData1.getString("CenterPoint");
//			temp1 = temp1.substring(6,temp1.length());
//			temp1 = temp1.substring(0,temp1.length()-1);
//			String[] tt = temp1.split(" ");
//			System.out.println(tt[0]);
//			System.out.println(tt[1]);
//			String qq = "update base_info_part_station_yard_rel set lng='"+tt[0]+"',lat='"+tt[1]+"' where id='"+rs.getString("id")+"'";
//			Statement updateRange = conn.createStatement();
//			updateRange.execute(qq);
//		}
//		
//	}
//	//System.out.println(i);
//	//i++;
	
	
	//计算铁路线范围
	/*public static void main(String[] args) throws UnsupportedEncodingException {
		Connection conn = null;
		try{
			conn = GetJDBCConnection.getJDBCConnection();
			Statement quRail = conn.createStatement();
			String sql = "select id from base_info_rail";
			ResultSet rs = quRail.executeQuery(sql);
			int i = 0;;
			String t = "";
			while(rs.next()){
				String quSql = "select lng,lat from base_info_rail_data where rail_id = '"+rs.getString("id")+"' order by cast(order_number as SIGNED)";
				Statement quRailData = conn.createStatement();
				ResultSet rsQuRailData = quRailData.executeQuery(quSql);
				String lineString = "LINESTRING(";
				while(rsQuRailData.next()){
					lineString+=rsQuRailData.getString("lng")+" "+rsQuRailData.getString("lat")+",";
				}
				lineString = lineString.substring(0,lineString.length()-1);
				lineString+=")";
				String updateSql = "update base_info_rail set line_geom=ST_GeomFromText('"+lineString+"') where id='"+rs.getString("id")+"'";
				Statement updateLine = conn.createStatement();
				updateLine.execute(updateSql);
				updateSql = "update base_info_rail set range_geom=(ST_BUFFER(ST_GeomFromText('"+lineString+"'),0.015,ST_Buffer_Strategy('end_round',32),ST_Buffer_Strategy('join_round',32))) where id='"+rs.getString("id")+"'";
				//Statement updateRange = conn.createStatement();
				//updateRange.execute(updateSql);
				if(i!=21&&i!=22&&i!=49){
					Statement updateRange = conn.createStatement();
					updateRange.execute(updateSql);
				}else{
					System.out.println(rs.getString("id"));
				}
				System.out.println(i);
				i++;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}finally{
			if(null != conn){
				GetJDBCConnection.closeConnection(conn);
			}
			System.out.println("Finish");
		}
	}*/
	
/*	String updateSql = "update pub_org set the_geom = ST_GeomFromText('"+rs.getString("Geom")+"') where org_id='"+rs.getString("OrgId")+"'";
	String insertSql = "insert into test(id,name,gis)values('1','测试',ST_GeomFromText('"+rs.getString("Geom")+"'))";
	String updateSql1 = "update test set gis=ST_GeomFromText('MULTIPOLYGON(((116.08154 40.45029,116.86157 40.43655,116.85059 39.76364,116.00189 39.77737,116.08154 40.45029)))') " +
			"where id='1'";*/
}
