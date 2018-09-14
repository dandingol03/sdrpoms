package com.pc.exhibition.map.dao;



import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CaculateDistance;
import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.Point;
import com.pc.bsp.common.util.PubData;

@Repository("exMapPopWinDao")
public class ExMapPopWinDao {
	@Autowired
	private DBUtil util;

	//广播柱临近线路
	public List<Map<String, Object>> findByDefenceBroadcastId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_defence_broadcast_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_defence_broadcast_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	
	//隐患临近线路
	public List<Map<String, Object>> findByDangerInfoId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM patrol_danger_info_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM patrol_danger_info_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//工作站临近线路
	public List<Map<String, Object>> findByDefenceGuardStationId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_defence_guard_station_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_defence_guard_station_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	
	//宣传点临近线路
	public List<Map<String, Object>> findByDefencePropagandaId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_defence_propaganda_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_defence_propaganda_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}

	//周边场所临近线路
	public List<Map<String, Object>> findByPeripheralPlaceId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_peripheral_place_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
						+ "t.rail_name as railName "
						+ "FROM base_info_peripheral_place_view b  JOIN  base_info_rail_stream t "
						+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//监控临近线路
	public List<Map<String, Object>> findByVideoId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM video_monitor_info_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
						+ "t.rail_name as railName "
						+ "FROM video_monitor_info_view b  JOIN  base_info_rail_stream t "
						+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//桥梁临近线路
	public List<Map<String, Object>> findByPartBridgeId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_bridge_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_bridge_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//涵洞临近线路
	public List<Map<String, Object>> findByPartCulvertId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_culvert_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_culvert_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//道口临近线路
	public List<Map<String, Object>> findByPartJunctionId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_junction_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_junction_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//隧道临近线路
	public List<Map<String, Object>> findByPartTunnelId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_tunnel_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_tunnel_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//车站临近线路
	public List<Map<String, Object>> findByPartStationId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_station_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_station_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}
	//行人易穿行临近线路
	public List<Map<String, Object>> findByHiddenTrajectionId(String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.name as railName "
					+ "FROM base_info_part_trajection_view b  JOIN  base_info_rail t "
					+ "WHERE b.rail_id=t.id and b.rail_steam_id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);	
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "t.rail_name as railName "
					+ "FROM base_info_part_trajection_view b  JOIN  base_info_rail_stream t "
					+ "WHERE b.rail_stream_id=t.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
		}
	}

	//铁路区和街乡镇的数量
	public Map<String, Object> getRailOrgCount(String orgId, String id) {
		// TODO Auto-generated method stub
		Map<String,Object>  params1 = new HashMap<String,Object>(2);
		Map<String,Object>  params2 = new HashMap<String,Object>(2);
		Map<String,Object>  resultMap = new HashMap<String,Object>();
		if(StringUtils.equals(id.substring(0,4),"stre")){
			StringBuffer sqlCity=new StringBuffer();
			if(!orgId.equals("110")){
				sqlCity.append("AND ORG_ID = :orgId ");
				params1.put("orgId", orgId);
			}
			StringBuffer sqlStreet=new StringBuffer();
			if(!orgId.equals("110")){
				sqlStreet.append("AND ORG_ID LIKE :orgId ");
				params2.put("orgId", orgId+"%");
			}
			params1.put("id", id);
			params2.put("id", id);
			String sqlCityCount="SELECT DISTINCT(o.org_name) as cityOrgNames from base_info_rail_view v "
					+ "left join pub_org o on o.ORG_ID=v.org_id "
					+ "where rail_stream_id= :id AND char_length(v.org_id)=6 AND length is not null ";
			resultMap.put("citys",util.getMapList(sqlCityCount+sqlCity, params1));
			String sqlStreetCount="SELECT COUNT(DISTINCT(ORG_ID)) as streetCount from base_info_rail_view where rail_stream_id= :id AND char_length(org_id)=9 AND length is not null ";
			resultMap.putAll(util.getMapObject(sqlStreetCount+sqlStreet, params2)); 
			return resultMap;
		}else{
			StringBuffer sqlCity=new StringBuffer();
			if(!orgId.equals("110")){
				sqlCity.append("AND ORG_ID = :orgId ");
				params1.put("orgId", orgId);
			}
			StringBuffer sqlStreet=new StringBuffer();
			if(!orgId.equals("110")){
				sqlStreet.append("AND ORG_ID LIKE :orgId ");
				params2.put("orgId", orgId+"%");
			}
			params1.put("id", id);
			params2.put("id", id);
			String sqlCityCount="SELECT DISTINCT(o.org_name) as cityOrgNames from base_info_rail_view "
					+ "left join pub_org o on o.ORG_ID=v.org_id "
					+ "where id= :id AND char_length(v.org_id)=6 AND length is not null ";
			resultMap.put("citys",util.getMapList(sqlCityCount+sqlCity, params1));
			String sqlStreetCount="SELECT COUNT(DISTINCT(ORG_ID)) as streetCount from base_info_rail_view where id= :id AND char_length(org_id)=9 AND length is not null ";
			resultMap.putAll(util.getMapObject(sqlStreetCount+sqlStreet, params2)); 
			return resultMap;
		}
	}

	//铁路辖内里程
	public String getRailOrgKM(String orgId, String id) {
		// TODO Auto-generated method stub
		if(StringUtils.equals(id.substring(0,4),"stre")){
			String sql="select length from base_info_rail_view where rail_stream_id=? and org_id=? ";
			List<Map<String, Object>> rowsList=util.getMapList(sql,new Object[]{id,orgId});
			double l=0.0;
			if(rowsList!=null&&rowsList.size()>0){
				for(int i=0;i<rowsList.size();i++){
					Map<String,Object> row=rowsList.get(i);
					l+=ConverterUtils.toDouble(row.get("length"));
				}
			}
			String KM=CommonUtil.KM2MStrbyDec(ConverterUtils.toString(l)) ;
			return KM;
		}else{
			String sql="select length from base_info_rail_view where id=? and org_id=? ";
			List<Map<String, Object>> rowsList=util.getMapList(sql,new Object[]{id,orgId});
			double l=0.0;
			if(rowsList!=null&&rowsList.size()>0){
				for(int i=0;i<rowsList.size();i++){
					Map<String,Object> row=rowsList.get(i);
					l+=ConverterUtils.toDouble(row.get("length"));
				}
			}
			String KM=CommonUtil.KM2MStrbyDec(ConverterUtils.toString(l)) ;
			return KM;
		}
	}
	//进站信号机
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.org_id as \"orgId\"," +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_station_yard_rel t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return util.getMapObject(sql, paramMap);
	}
	/**
	 * 查询  km的上下 记录
	 * @param baseInfoRailId
	 * @param kilometerMark
	 * @param orgId 
	 * @return
	 */
    public List<Map<String, Object>> findLngLatKm(String baseInfoRailId,
	    String kilometerMark, String orgId) {
		// TODO Auto-generated method stub
		String sql = "select * from "
			+ "(select "
			+ "t.rail_id as railId , "
			+ "t.lng as \"lng\", "
			+ "t.lat as \"lat\", "
			+ "cast(t.kilometer_mark  AS signed) as \"kilometerMark\" "
			+ "from base_info_rail_data t "
			+ "where 1=1 AND t.rail_id=:railId and org_id like '"+orgId+"%' "
			+ "AND cast(t.kilometer_mark  AS signed)<=:kilometerMark ORDER BY kilometerMark desc limit 0,1) AS t1"
			+ " union all "
			+ "select * from (select "
			+ "t.rail_id as railId , "
			+ "t.lng as \"lng\", "
			+ "t.lat as \"lat\", "
			+ "cast(t.kilometer_mark  AS signed) as \"kilometerMark\" "
			+ "from base_info_rail_data t "
			+ "where 1=1 AND t.rail_id=:railId and org_id like '"+orgId+"%' "
			+ "AND cast(t.kilometer_mark  AS signed)>=:kilometerMark ORDER BY kilometerMark asc limit 0,1) AS t2";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("railId", baseInfoRailId);
		paramMap.put("kilometerMark", kilometerMark);
		return util.getMapList(sql, paramMap);
    }
    /**
	 * @return
	 */
	public List<Map<String, Object>> getStreamRials(String orgId) {
		// TODO Auto-generated method stub
		String sql = "select DISTINCT(t.rail_stream_id)as id,t.rail_name as name,s.start,s.end from base_info_rail_view t "
				+ "left join base_info_rail_stream as s on s.id=t.rail_stream_id "
				+ "WHERE 1=1 and t.org_id like '"+orgId+"%' and t.length is not null " ;
		List<Map<String, Object>>  rowsList=util.getMapList(sql, new Object[]{});
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//起点路程计算
				Object start=row.get("start");
				if(null!=start&&!"".equals(start.toString().trim())){
					row.put("startStr", CommonUtil.KM2MStrbyDec(start.toString()));
					String [] startArr=CommonUtil.M2KMStrbyDec(start.toString());
					row.put("startKM", startArr[0]);
					row.put("startM", startArr[1]);
				}
				//终点路程计算
				Object end=row.get("end");
				if(null!=end&&!"".equals(end.toString().trim())){
					row.put("endStr", CommonUtil.KM2MStrbyDec(end.toString()));
					String [] endArr=CommonUtil.M2KMStrbyDec(end.toString());
					row.put("endKM", endArr[0]);
					row.put("endM", endArr[1]);
				}
			}
		}
		return rowsList;
	}
	/**
	 * 输入公里标   定位点附近情况
	 * @param lat 
	 * @param lng 
	 * @param r 
	 * @param orgId 
	 */
	public List<Map<String, String>> getNeighbourhoodListPopWin(String lng, String lat, String r, String orgId) {
		 // TODO输入公里标   定位点附近情况
		String point="POINT("+lng+" "+lat+")";
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		List<Map<String, String>> broadcastList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> propagandaList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> junctionList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> dangerList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> bridgeList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> culvertList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> tunnelList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> partStationList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> policeHouseList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> guardStationList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> policeStationList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> trajectionList=new ArrayList<Map<String,String>>();
		
			//广播柱1
			 String	sqlBroadcast = "select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'broadcast' as type "
				 		+ " from base_info_defence_broadcast as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//宣传点2
			 String	sqlPropaganda = "select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'propaganda' as type "
				 		+ " from base_info_defence_propaganda as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			 //道口3
			 String	sqlJunction = "select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'junction' as type "
				 		+ " from base_info_part_junction as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//隐患4
			 String sqlDanger="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'danger' as type "
				 		+ " from patrol_danger_info as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//桥梁5
			 String sqlBridge="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'bridge' as type "
				 		+ " from base_info_part_bridge as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//涵洞6
			 String sqlCluvert="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'culvert' as type "
				 		+ " from base_info_part_culvert as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//隧道7
			 String sqlTunnel="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'tunnel' as type "
				 		+ " from base_info_part_tunnel as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//车站8
			 String sqlStation="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'partStation' as type "
				 		+ " from base_info_part_station as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//工作站9
			 String sqlGuardStation="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'guardStation' as type "
				 		+ " from base_info_defence_guard_station as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//警务站10
			 String sqlPoliceStation="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'policeStation' as type "
				 		+ " from base_info_defence_police_station as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//派出所11
			 String sqlPoliceHouse="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'policeHouse' as type "
				 		+ " from base_info_defence_police_house as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
			//易穿行12
			 String sqlTrajection="select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`lng` AS `lng`,`a`.`lat` AS `lat`,'trajection' as type "
				 		+ " from base_info_part_trajection as a"
				 		+ " where (st_intersects(a.the_geom,ST_Buffer(st_geomfromtext(?),"+r+",ST_Buffer_Strategy('point_circle', 32))) = 1) and a.org_id like '"+orgId+"%' ";
	        	List<Map<String, Object>> broadcasts=util.getMapList(sqlBroadcast, new Object[]{point});
	        	Map<String,String> map=new HashMap<String,String>();
	        	if(broadcasts!=null&&broadcasts.size()>0){
					for(int k=0;k<broadcasts.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=broadcasts.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							broadcastList.add(map);
						}
					}
				}
	        	List<Map<String, Object>> propagandas=util.getMapList(sqlPropaganda, new Object[]{point});
	        	if(propagandas!=null&&propagandas.size()>0){
					for(int k=0;k<propagandas.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=propagandas.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							propagandaList.add(map);
						}
					}
				}
	        	List<Map<String, Object>> junctions=util.getMapList(sqlJunction, new Object[]{point});
	        	if(junctions!=null&&junctions.size()>0){
					for(int k=0;k<junctions.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=junctions.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							junctionList.add(map);
						}
					}
				}
	        	List<Map<String, Object>> dangers=util.getMapList(sqlDanger, new Object[]{point});
	        	if(dangers!=null&&dangers.size()>0){
					for(int k=0;k<dangers.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=dangers.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							dangerList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> bridges=util.getMapList(sqlBridge, new Object[]{point});
	        	if(bridges!=null&&bridges.size()>0){
					for(int k=0;k<bridges.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=bridges.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							bridgeList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> culverts=util.getMapList(sqlCluvert, new Object[]{point});
	        	if(culverts!=null&&culverts.size()>0){
					for(int k=0;k<culverts.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=culverts.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							culvertList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> tunnels=util.getMapList(sqlTunnel, new Object[]{point});
	        	if(tunnels!=null&&tunnels.size()>0){
					for(int k=0;k<tunnels.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=tunnels.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							tunnelList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> partStations=util.getMapList(sqlStation, new Object[]{point});
	        	if(partStations!=null&&partStations.size()>0){
					for(int k=0;k<partStations.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=partStations.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							partStationList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> policeHouses=util.getMapList(sqlPoliceHouse, new Object[]{point});
	        	if(policeHouses!=null&&policeHouses.size()>0){
					for(int k=0;k<policeHouses.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=policeHouses.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							policeHouseList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> guardStations=util.getMapList(sqlGuardStation, new Object[]{point});
	        	if(guardStations!=null&&guardStations.size()>0){
					for(int k=0;k<guardStations.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=guardStations.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							guardStationList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> policeStations=util.getMapList(sqlPoliceStation, new Object[]{point});
	        	if(policeStations!=null&&policeStations.size()>0){
					for(int k=0;k<policeStations.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=policeStations.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							policeStationList.add(map);
						}
					}
				}
	    		List<Map<String, Object>> trajections=util.getMapList(sqlTrajection, new Object[]{point});
	        	if(trajections!=null&&trajections.size()>0){
					for(int k=0;k<trajections.size();k++){
						map=new HashMap<String,String>();
						Map<String,Object> row=trajections.get(k);
						Object lng1=row.get("lng");
						Object lat1=row.get("lat");
						if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())){
							//计算出距离
							double len=CaculateDistance.caculateDistance(ConverterUtils.toDouble(lng),ConverterUtils.toDouble(lat),
									ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1));
							BigDecimal   b   =   new   BigDecimal(len);
							map.put("id",row.get("id").toString());
							map.put("name", row.get("name").toString());
							map.put("lng", lng1.toString());
							map.put("lat", lat1.toString());
							map.put("type", row.get("type").toString());
							map.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
							trajectionList.add(map);
						}
					}
				}
	        	ConverterUtils.mapSorts(broadcastList);
        		list.addAll(broadcastList);
        		ConverterUtils.mapSorts(junctionList);
	        	list.addAll(junctionList);
	        	ConverterUtils.mapSorts(propagandaList);
	        	list.addAll(propagandaList);
	        	ConverterUtils.mapSorts(dangerList);
	        	list.addAll(dangerList);
	        	ConverterUtils.mapSorts(bridgeList);
	        	list.addAll(bridgeList);
	        	ConverterUtils.mapSorts(culvertList);
	        	list.addAll(culvertList);
	        	ConverterUtils.mapSorts(tunnelList);
	        	list.addAll(tunnelList);
	        	ConverterUtils.mapSorts(partStationList);
	        	list.addAll(partStationList);
	        	ConverterUtils.mapSorts(policeHouseList);
	        	list.addAll(policeHouseList);
	        	ConverterUtils.mapSorts(guardStationList);
	        	list.addAll(guardStationList);
	        	ConverterUtils.mapSorts(policeStationList);
	        	list.addAll(policeStationList);
	        	ConverterUtils.mapSorts(trajectionList);
	        	list.addAll(trajectionList);
        	return list;
	}
	/**
	 * @return
	 */
	public List<Map<String, Object>> getBridges() {
		// TODO Auto-generated method stub
		String sql="select id,rail_id as railId,name,middle from base_info_part_station_signal";
		return util.getMapList(sql, new Object[]{});
	}

	/**
	 * @param string3 
	 * @param string2 
	 * @param string 
	 * @return
	 */
	public Integer update(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		String sql="UPDATE base_info_part_station_signal SET lng ='"+string+"',lat='"+string2+"' WHERE id = '"+string3+"'";
		return util.editObject(sql, new Object[]{});
	}

	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object> findByIdVideoCS(String id) {
		// TODO Auto-generated method stub
		String sql = "select " +
				"t.id as \"id\", " +
				"t.address as \"address\", " +
				"t.is_yaw as \"isYaw\", " +
				"t.is_integration as \"isIntegration\", " +
				"t.remark as \"remark\", " +
				"t.unit as \"unit\", " +
				"t.lng , " +
				"t.lat , " +
				"t.org_id as \"orgId\"," +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from video_information t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return util.getMapObject(sql, paramMap);
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>>  findByVideonearByRails(String id) {
		// TODO Auto-generated method stub
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "s.rail_name as railName "
					+ "FROM video_information_view b  JOIN  base_info_rail_stream s "
					+ "WHERE b.rail_stream_id=s.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
	}

	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object> findByIdCSPoliceHouse(String id) {
		// TODO Auto-generated method stub
		String sql = "select " +
				"t.id as \"id\", " +
				"t.address as \"address\", " +
				"t.building_acreage as \"buildingAcreage\", " +
				"t.building_situation as \"buildingSituation\", " +
				"t.remark as \"remark\", " +
				"t.form as \"form\", " +
				"t.lng , " +
				"t.lat , " +
				"t.org_id as \"orgId\"," +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from station_information t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return util.getMapObject(sql, paramMap);
	}

	/**
	 * @param id
	 * @return
	 */
	public Object findByCSPoliceHousenearByRails(String id) {
		// TODO Auto-generated method stub
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sql="SELECT DISTINCT(b.rail_id),"
					+ "s.rail_name as railName "
					+ "FROM station_information_view b  JOIN  base_info_rail_stream s "
					+ "WHERE b.rail_stream_id=s.id and b.id = :id ";
			paramMap.put("id", id);
			return util.getMapList(sql, paramMap);
	}

	/**
	 * @param lng
	 * @param lat
	 * @param streamId 
	 * @return
	 */
	public Map<String, Object> getKm(String lng, String lat, String streamId) {
		// TODO Auto-generated method stub
		String point="POINT("+lng+" "+lat+")";
		String sql=	"SELECT "
						+ "t.id, "
						+ "t.rail_name AS name, "
						+ "t.lng AS lng, "
						+ "t.lat AS lat, "
						+ "t.kilometer_mark AS km, "
						+ "t.the_geom "
					+ "FROM "
						+ "( "
						+ "SELECT * FROM base_info_rail_data AS a WHERE "
						+ "(st_intersects "
							+ "(a.the_geom,"
								+ "st_buffer( "
									+ "st_geomfromtext (?), "
									+ "0.027, "
									+ "st_buffer_strategy ('point_circle', 32) "
								+ ") "
							+ ") = 1) "
						+ ") AS t "
						+ "where rail_id =? "
					+ "ORDER BY "
					+ "( "
						+ "ST_distance ( "
							+ "st_geomfromtext (?), "
							+ "t.the_geom "
						+ ") * 111201.7857885 "
					+ ") ASC ";
		List<Map<String, Object>> list=util.getMapList(sql, new Object[]{point,streamId,point});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Map<String, String>> findner(String lng,String lat, String streamId) {
		// TODO Auto-generated method stub
		String point="POINT("+lng+" "+lat+")";
		String st_buffer_strategy=
				"SELECT "+
				"order_number, "+
				"lng, "+
				"lat, "+
				"rail_id, "+
				"kilometer_mark, "+
				"rail_name "+
			"FROM "+
				"base_info_rail_data AS a "+
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
				") and rail_id=? ";
		List<Map<String, Object>> mapList = util.getMapList(st_buffer_strategy,new Object[]{point,streamId});
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
				map.put("kilometer_mark",row.get("kilometer_mark").toString());
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
}
