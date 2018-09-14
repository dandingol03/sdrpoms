/**
 * 
 */
package com.pc.bsp.common.util;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author simple
 *
 */
public class DBDump {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		Connection conn = null;
		try{
			conn = GetJDBCConnection.getJDBCConnection();
			Statement quRail = conn.createStatement();
			String sql = "select id,ST_AsText(range_geom) as ranger from base_info_rail";
			ResultSet rs = quRail.executeQuery(sql);
			int i = 0;;
			String t = "";
			while(rs.next()){
				t+=rs.getString("ranger").replace("POLYGON", "")+",";
				/*String quSql = "select lng,lat from base_info_rail_data where rail_id = '"+rs.getString("id")+"' order by cast(order_number as SIGNED)";
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
				updateSql = "update base_info_rail set range_geom=(ST_BUFFER(ST_GeomFromText('"+lineString+"'),1,ST_Buffer_Strategy('end_round',32),ST_Buffer_Strategy('join_round',32))) where id='"+rs.getString("id")+"'";
				Statement updateRange = conn.createStatement();
				updateRange.execute(updateSql);*/
				System.out.println(i);
				i++;
			}
			t = t.substring(0, t.length()-1);
			t = "MULTIPOLYGON("+t+";";
			System.out.println(t);
			int m=0;
			m++;
			System.out.println(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}finally{
			if(null != conn){
				GetJDBCConnection.closeConnection(conn);
			}
			System.out.println("Finish");
		}
	}
	
/*	String updateSql = "update pub_org set the_geom = ST_GeomFromText('"+rs.getString("Geom")+"') where org_id='"+rs.getString("OrgId")+"'";
	String insertSql = "insert into test(id,name,gis)values('1','测试',ST_GeomFromText('"+rs.getString("Geom")+"'))";
	String updateSql1 = "update test set gis=ST_GeomFromText('MULTIPOLYGON(((116.08154 40.45029,116.86157 40.43655,116.85059 39.76364,116.00189 39.77737,116.08154 40.45029)))') " +
			"where id='1'";*/
}
