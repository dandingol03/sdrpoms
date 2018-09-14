package com.pc.exhibition.map.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.PubData;

/**
 * @author simple
 *
 */
@Repository("ExMapDao")
public class ExMapDao {

	@Autowired
	private DBUtil util;

	/**
	 * 获取当前机构下的中心点和放大倍数
	 * @param orgId
	 * @return Map<String,Object> X Y Zoom XY 中心点坐标 Zoom 放大倍数
	 */
	public Map<String,Object> getOrgZoomAndCenter(String orgId){
		Map<String,Object> result = new HashMap<String,Object>();
		String sql ="select X,Y,ZOOM from pub_org where org_id=:orgId";
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgId", orgId);
		List<Map<String,Object>> resultList = util.getMapList(sql, paramMap);
		if(resultList.size()<1){
			result.put("X", "116.46");
			result.put("Y", "40.248");
			result.put("Zoom", "10");
		}else{
			result.put("X", resultList.get(0).get("X"));
			result.put("Y", resultList.get(0).get("Y"));
			result.put("Zoom", resultList.get(0).get("ZOOM"));
		}
		return result;
	}
	/**
	 * 根据当前用户获得相关安保区
	 * @param request
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> getSecurityareaBJBorderInfoList(String orgId){
		String quSql = "select " +
						"ORG_ID, " +
						"ST_AsText(the_geom) as Geom " +
						"from base_info_securityarea_view " +
						"where ORG_ID = ? " +
						"and the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()') ";
		return util.getMapList(quSql, new Object[]{orgId});
	}
	/**
	 * 根据当前用户获得相关边界
	 * @param request
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> getBJBorderInfoList(String orgId){
		String quSql = "select ORG_ID as id, " +
						"ORG_NAME as name, +" +
						"X as lng, " +
						"Y as lat, " +
						"ZOOM as zoom," +
						"ST_AsText(the_geom) as Geom " +
						"from pub_org " +
						"where ORG_ID like ? and " +
						"ORG_ID !=? " +
						"order by cast(ORG_ID as signed) desc ";
		return util.getMapList(quSql, new Object[]{orgId+"%",orgId});
	}
	
	/**
	 * 获得当前用户下涉路机构详细信息
	 * @param orgId
	 * @param level 层级 0 第一级 1 第二级 2 第三级
	 * @return
	 */
	public List<Map<String,Object>> getUserOrgListInfo(String orgId,String level){
		String quSql = "select distinct(a.org_id) as orgId,a.org_name as orgName " +
				  "from base_info_rail_view a where ";
		switch(level){
		default:
		case "0":
			quSql+= "char_length(a.org_id)=3 and a.org_id like ? and a.length is not null";
			break;
		case "1":
			quSql+= "char_length(a.org_id)=6 and a.org_id like ? and a.length is not null";
			break;
		case "2":
			quSql+= "char_length(a.org_id)=9 and a.org_id like ? and a.length is not null";
			break;
		}
		return util.getMapList(quSql, new Object[]{orgId+"%"});
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关铁路信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJRailWayInfoList(String orgId,List<String> railIdList){
		Object[] paramObject = null;
		String questionSql="";
		if(orgId.equals("110")){
			paramObject  = new Object[railIdList.size()];
			for(int i=0;i<railIdList.size();i++){
				questionSql+="a.id=? or ";
				paramObject[i]=railIdList.get(i);
			}
			questionSql = questionSql.substring(0, questionSql.length()-4);
			String quSql = "select a.id,s.id as railStreamId,a.name,s.rail_name as railName,a.classify,"
					+ "ST_AsText(st_intersection ((SELECT the_geom FROM pub_org WHERE ORG_ID = '110'), s.line_geom)) as Geom,"
					+ "s.stream from "
					+ "base_info_rail as a "
					+ "LEFT OUTER JOIN base_info_rail_stream AS s ON s.rail_id =a.id  "
					+ "where "+questionSql+" order by a.classify desc ";
			List<Map<String,Object>> resultList=util.getMapList(quSql, paramObject);
			if(resultList!=null&&resultList.size()>0){
				for(int i=0;i<resultList.size();i++){
					Map<String,Object> row=resultList.get(i);
					Object type=row.get("stream");
					if(type!=null&&!"".equals(type.toString().trim())){
						row.put("streamName", PubData.getDictName("RAIL_STREAM", type.toString()));		
					}
					
				}
			}
			return resultList;
		}else{
			paramObject  = new Object[railIdList.size()+1];
			paramObject[0] = orgId;
			for(int i=0;i<railIdList.size();i++){
				questionSql+="id=? or ";
				paramObject[i+1]=railIdList.get(i);
			}
			questionSql = questionSql.substring(0, questionSql.length()-4);
			String quSql = "select id,rail_stream_id as railStreamId,name,rail_name as railName,classify,ST_AsText(the_geom) as Geom from base_info_rail_view as a where org_id=? " +
					"and the_geom!=ST_GeomFromText('GEOMETRYCOLLECTION()') " +
					"and ("+questionSql+")" +" order by classify desc ";
			List<Map<String,Object>> resultList=util.getMapList(quSql, paramObject);
			if(resultList!=null&&resultList.size()>0){
				for(int i=0;i<resultList.size();i++){
					Map<String,Object> row=resultList.get(i);
					Object type=row.get("stream");
					if(type!=null&&!"".equals(type.toString().trim())){
						row.put("streamName", PubData.getDictName("RAIL_STREAM", type.toString()));		
					}
					
				}
			}
			return resultList;
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关桥涵信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJBridgeCulvertInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 桥涵 base_info_part_bridge base_info_part_culvert
			String quSql = "select a.id,a.name,'bridge' as type,a.lng,a.lat,is_oversize as oversize " +
								"from base_info_part_bridge a ";
			//桥梁范围 1特大0普通
			String bridgeSql="select b.id,b.name,'bridgeYard' as type,ST_AsText(b.range_geom) as Geom,is_oversize as oversize " +
					"from base_info_part_bridge b where b.range_geom is not null and "
					+ "b.id in (select a.id "
					+ "from base_info_part_bridge a ";
			
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
				bridgeSql+="where a.org_id like ? ";
			}else{
				bridgeSql+=")";
			}
			List<Map<String,Object>> bridgeListInfo = util.getMapList(quSql, paramObject);
			quSql = "select a.id,a.name,'culvert' as type,a.lng,a.lat " +
						"from base_info_part_culvert a ";
			
			//涵洞范围
			String culvertSql="select b.id,b.name,'culvertYard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_culvert b where b.range_geom is not null and "
					+ "b.id in (select a.id "
					+ "from base_info_part_culvert a ";
			
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
				culvertSql+="where a.org_id like ? ";
			}else{
				culvertSql+=")";
			}
			
			List<Map<String,Object>> culvertListInfo = util.getMapList(quSql,paramObject);
			for(int i=0;i<culvertListInfo.size();i++){
				bridgeListInfo.add(culvertListInfo.get(i));
			}
			//桥涵范围
			bridgeListInfo.addAll(util.getMapList(bridgeSql,paramObject));
			bridgeListInfo.addAll(util.getMapList(culvertSql,paramObject));
			return bridgeListInfo;
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			//TODO 桥涵 base_info_part_bridge base_info_part_culvert
			String quSql = "select distinct(a.id),a.name,'bridge' as type,a.lng,a.lat,is_oversize as oversize  " +
								"from base_info_part_bridge_view a ";
			//桥梁范围
			String bridgeSql="select b.id,b.name,'bridgeYard' as type,ST_AsText(b.range_geom) as Geom,is_oversize as oversize " +
					"from base_info_part_bridge b where b.range_geom is not null and "
					+ "b.id in (select DISTINCT(a.id) "
					+ "from base_info_part_bridge_view a ";
			
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
				bridgeSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
				bridgeSql+="where a.rail_id in ("+questionSql+")) ";
			}
			List<Map<String,Object>> bridgeListInfo = util.getMapList(quSql, paramObject);
			quSql = "select distinct(a.id),a.name,'culvert' as type,a.lng,a.lat " +
						"from base_info_part_culvert_view a ";
			//涵洞范围
			String culvertSql="select b.id,b.name,'culvertYard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_culvert b where b.range_geom is not null and "
					+ "b.id in (select DISTINCT(a.id) "
					+ "from base_info_part_culvert_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
				culvertSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
				culvertSql+="where a.rail_id in ("+questionSql+")) ";
			}
			List<Map<String,Object>> culvertListInfo = util.getMapList(quSql,paramObject);
			for(int i=0;i<culvertListInfo.size();i++){
				bridgeListInfo.add(culvertListInfo.get(i));
			}
			//桥涵范围
			bridgeListInfo.addAll(util.getMapList(bridgeSql,paramObject));
			bridgeListInfo.addAll(util.getMapList(culvertSql,paramObject));
			return bridgeListInfo;
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关道口信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJJunctionInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 道口 base_info_part_junction
			String quSql = "select a.id,a.name,'junction' as type,a.lng,a.lat " +
						"from base_info_part_junction a ";
			//道口范围
			String junctionSql="select b.id,b.name,'yard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_junction b where b.range_geom is not null and "
					+ "b.id in (select a.id "
					+ "from base_info_part_junction a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
				junctionSql+="where a.org_id like ? ";
			}else{
				junctionSql+=")";
			}
			List<Map<String,Object>> junctionListInfo = util.getMapList(quSql,paramObject);
			junctionListInfo.addAll(util.getMapList(junctionSql,paramObject));
			return junctionListInfo;
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			//TODO 道口 base_info_part_junction
			String quSql = "select distinct(a.id),a.name,'junction' as type,a.lng,a.lat " +
						"from base_info_part_junction_view a ";
			//道口范围
			String junctionSql="select b.id,b.name,'yard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_junction b where b.range_geom is not null and "
					+ "b.id in (select DISTINCT(a.id) "
					+ "from base_info_part_junction_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
				junctionSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
				junctionSql+="where a.rail_id in ("+questionSql+")) ";
			}
			List<Map<String,Object>> junctionListInfo = util.getMapList(quSql,paramObject);
			junctionListInfo.addAll(util.getMapList(junctionSql,paramObject));
			return junctionListInfo;
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关隧道信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTunneltInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			
			//TODO 隧道 base_info_part_tunnel
			String quSql = "select a.id,a.name,a.lng,a.lat,'tunnel' as type  " +
					"from base_info_part_tunnel a ";
			//隧道范围
			String tunnelSql="select b.id,b.name,'yard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_tunnel b where b.range_geom is not null and "
					+ "b.id in (select a.id "
					+ "from base_info_part_tunnel a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
				tunnelSql+="where a.org_id like ? ";
			}else{
				tunnelSql+=")";
			}
			List<Map<String,Object>> tunnelListInfo = util.getMapList(quSql,paramObject);
			tunnelListInfo.addAll(util.getMapList(tunnelSql,paramObject));
			return tunnelListInfo;
			
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			
			//TODO 隧道 base_info_part_tunnel
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat,'tunnel' as type  " +
						"from base_info_part_tunnel_view a ";
			//隧道范围
			String tunnelSql="select b.id,b.name,'yard' as type,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_tunnel b where b.range_geom is not null and "
					+ "b.id in (select DISTINCT(a.id) "
					+ "from base_info_part_tunnel_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
				tunnelSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
				tunnelSql+="where a.rail_id in ("+questionSql+")) ";
			}
			List<Map<String,Object>> tunnelListInfo = util.getMapList(quSql,paramObject);
			tunnelListInfo.addAll(util.getMapList(tunnelSql,paramObject));
			return tunnelListInfo;
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关车站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJStationInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 车站 base_info_part_station
			String quSql = "select a.id,a.name,'station' as type,a.lng,a.lat,a.nature " +
					"from base_info_part_station a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			String quYardSql = "select b.name,'stationYard' as type,b.station_id as id,ST_AsText(b.range_geom) as Geom  from base_info_part_station_yard_rel b where " +
					"b.station_id in (select a.id " +
					"from base_info_part_station a ";
			String sql="select b.id,b.name,'yard' as type,b.nature,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_station b where range_geom is not null and "
					+ "b.id in (select a.id "
					+ "from base_info_part_station a ";
//			base_info_part_station_signal
			String quStationSql = "select b.id,b.name,'signal' as type,b.station_id as stationId,b.lng,b.lat from base_info_part_station_signal b where " +
					"b.station_id in (select a.id " +
					"from base_info_part_station a ";
			if(!orgId.equals("110")){
				quStationSql+="where a.org_id like ?) ";
				quYardSql+="where a.org_id like ?) ";
				sql+="where a.org_id like ?) ";
			}else{
				quStationSql+=") ";
				quYardSql+=") ";
				sql+=") ";
			}
			List<Map<String,Object>> result = util.getMapList(quSql,paramObject);
			result.addAll(util.getMapList(quYardSql,paramObject));
			result.addAll(util.getMapList(sql,paramObject));
			result.addAll(util.getMapList(quStationSql,paramObject));
			System.err.println(result);
			return result;
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			//TODO 车站 base_info_part_station
			String quSql = "select distinct(a.id),a.name,'station' as type,a.lng,a.lat,a.nature " +
					"from base_info_part_station_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
//			String quStationSql = "select b.id,b.name,b.range,'yard' as type,b.station_id as stationId,b.lng,b.lat from base_info_part_station_yard_rel b where " +
//					"b.station_id in (select distinct(a.id) " +
//					"from base_info_part_station_view a ";
			String quYardSql = "select b.name,'stationYard' as type,b.station_id as id,ST_AsText(b.range_geom) as Geom  from base_info_part_station_yard_rel b where " +
					"b.station_id in (select a.id " +
					"from base_info_part_station_view a ";
			String sql="select b.id,b.name,'yard' as type,b.nature,ST_AsText(b.range_geom) as Geom " +
					"from base_info_part_station b where range_geom is not null and "
					+ "b.id in (select distinct(a.id)  "
					+ "from base_info_part_station_view a ";
			String quStationSql = "select b.id,b.name,'signal' as type,b.station_id as stationId,b.lng,b.lat from base_info_part_station_signal b where " +
					"b.station_id in (select distinct(a.id) " +
					"from base_info_part_station_view a ";
			
			if(!orgId.equals("110")){
				quStationSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
				quYardSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
				sql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+")) ";
			}else{
				quStationSql+="where a.rail_id in ("+questionSql+")) ";
				quYardSql+="where a.rail_id in ("+questionSql+")) ";
				sql+="where a.rail_id in ("+questionSql+")) ";
			}
			List<Map<String,Object>> result = util.getMapList(quSql,paramObject);
			result.addAll(util.getMapList(quYardSql,paramObject));
			result.addAll(util.getMapList(quStationSql,paramObject));
			result.addAll(util.getMapList(sql,paramObject));
			System.err.println(util.getMapList(quYardSql,paramObject));
			return result;
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关行人易穿行信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTrajectionInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 行人易穿行 base_info_part_trajection
			String quSql = "select a.id,a.name,a.region,a.lng,a.lat " +
					"from base_info_part_trajection a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			return util.getMapList(quSql,paramObject);
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			//TODO 行人易穿行 base_info_part_trajection
			String quSql = "select distinct(a.id),a.name,a.region,a.lng,a.lat " +
					"from base_info_part_trajection_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点人信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJKeypersonInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 重点人 base_info_keyperson
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from base_info_keyperson a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			return util.getMapList(quSql,paramObject);
		}else{
			String questionSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject  = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					questionSql+="?,";
				}
			}else{
				paramObject  = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					questionSql+="?,";
				}
				
			}
			questionSql = questionSql.substring(0, questionSql.length()-1);
			//TODO 重点人 base_info_keyperson
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from base_info_keyperson_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
//=====================================================================================================================================	
	/**
	 * 根据当前用户机构和铁路获得相关隐患点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJDangerInfoList(String orgId,List<String> railIdList,String dangerType){
		if(railIdList == null){
			//TODO 隐患点 patrol_danger_info
			String quSql = "select a.id,a.name,a.type,a.danger_type as dangerType,a.handle_status as handleStatus,a.lng,a.lat " +
					"from patrol_danger_info a ";
			if(dangerType.equals("undefined")||dangerType==null||dangerType.equals("")){
				if(!orgId.equals("110")){
					quSql+="where a.org_id like ? ";
					return util.getMapList(quSql,new Object[]{orgId+"%"});
				}else{
					return util.getMapList(quSql,new Object[]{});
				}
				
			}else{
				if(!orgId.equals("110")){
					quSql+="where a.danger_type=? " +
							"and a.org_id like ? ";
					return util.getMapList(quSql,new Object[]{dangerType,orgId+"%"});
				}else{
					quSql+="where a.danger_type=? ";
					return util.getMapList(quSql,new Object[]{dangerType});
				}
			}
		}else{
			//TODO 隐患点 patrol_danger_info
			String quSql = "select distinct(a.id),a.name,a.type,a.danger_type as dangerType,a.handle_status as handleStatus,a.lng,a.lat " +
					"from patrol_danger_info_view a ";
			String questionSql ="";
			Object[] paramObject = null;
			
			if(dangerType.equals("undefined")||dangerType==null||dangerType.equals("")){
				if(!orgId.equals("110")){
					paramObject  = new Object[railIdList.size()+1];
					paramObject[0] = orgId+"%";
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+1] = railIdList.get(i);
						questionSql+="?,";
					}
					questionSql = questionSql.substring(0, questionSql.length()-1);
					quSql+="where a.org_id like ? "+
							"and a.rail_id in ("+questionSql+") ";
				}else{
					paramObject  = new Object[railIdList.size()];
					for(int i=0;i<railIdList.size();i++){
						paramObject[i] = railIdList.get(i);
						questionSql+="?,";
					}
					questionSql = questionSql.substring(0, questionSql.length()-1);
					quSql+="where a.rail_id in ("+questionSql+") ";
				}
			}else{
				if(!orgId.equals("110")){
					paramObject  = new Object[railIdList.size()+2];
					paramObject[0] = dangerType;
					paramObject[1] = orgId+"%";
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+2] = railIdList.get(i);
						questionSql+="?,";
					}
					questionSql = questionSql.substring(0, questionSql.length()-1);
					quSql+="where a.danger_type=? " +
							"and a.org_id like ? "+
							"and a.rail_id in ("+questionSql+") ";
				}else{
					paramObject  = new Object[railIdList.size()+1];
					paramObject[0] = dangerType;
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+1] = railIdList.get(i);
						questionSql+="?,";
					}
					questionSql = questionSql.substring(0, questionSql.length()-1);
					quSql+="where a.danger_type=? "+
							"and a.rail_id in ("+questionSql+") ";
				}
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关工作站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJGuardStationInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 工作站 base_info_defence_guard_station
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from base_info_defence_guard_station a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);
			
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 工作站 base_info_defence_guard_station
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from base_info_defence_guard_station_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关宣传点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPropagandaInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 宣传点 base_info_defence_propaganda
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from base_info_defence_propaganda a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 宣传点 base_info_defence_propaganda
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from base_info_defence_propaganda_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
//=====================================================================================================================================
	/**
	 * 根据当前用户机构和铁路获得相关警示柱信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJBroadcastInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 警示柱 base_info_defence_broadcast
			String quSql = "select a.id,a.name,a.lng,a.lat,a.status " +
					"from base_info_defence_broadcast a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 警示柱 base_info_defence_broadcast
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat,a.status " +
					"from base_info_defence_broadcast_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关摄像头信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJVideoMonitorInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 摄像头 video_monitor_info
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from video_monitor_info a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);

		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 摄像头 video_monitor_info
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from video_monitor_info_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关派出所信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPoliceHouseInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 派出所 base_info_defence_police_house
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from base_info_defence_police_house a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 派出所 base_info_defence_police_house
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from base_info_defence_police_house_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关警务站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPoliceStationInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 警务站 base_info_defence_police_station
			String quSql = "select a.id,a.name,a.lng,a.lat " +
					"from base_info_defence_police_station a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			return util.getMapList(quSql, paramObject);
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 警务站 base_info_defence_police_station
			String quSql = "select distinct(a.id),a.name,a.lng,a.lat " +
					"from base_info_defence_police_station_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关专职队员信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTeamMemberInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			//TODO 专职队员 patrol_team_user_relation pub_users
			String quSql = "select a.user_id as id,b.user_name as name,b.lng,b.lat " +
					"from patrol_team_user_relation a,pub_users b " +
					"where a.user_id = b.user_id " +
					"and b.user_org like ? " +
					"and str_to_date(b.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) ";
			return util.getMapList(quSql,new Object[]{orgId+"%"});
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 专职队员 patrol_team_user_relation pub_users
			String quSql = "select distinct(a.user_id) as id,a.user_name as name,a.lng,a.lat " +
					"from online_team_member_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关在线干部信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJCardeInfoList(String orgId,List<String> railIdList){
		if(railIdList == null){
			//TODO 在线干部 pub_users
			String quSql = "select a.user_id as id,a.user_name as name,a.lng,a.lat " +
					"from pub_users a " +
					"where a.user_org like ? " +
					"and str_to_date(a.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) " +
					"and a.user_id not in (select c.user_id from patrol_team_user_relation c) ";
			return util.getMapList(quSql,new Object[]{orgId+"%"});
		}else{
			String denfenseSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					denfenseSql+="?,";
				}
			}
			denfenseSql = denfenseSql.substring(0, denfenseSql.length()-1);
			//TODO 在线干部 pub_users
			String quSql = "select a.user_id as id,a.user_name as name,a.lng,a.lat " +
					"from online_carde_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	/**
	 * 根据当前用户机构和铁路获得周边单位信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @param perPlaceId
	 * @return
	 */
	public List<Map<String,Object>> getUserPeripheralPlaceInfoList(String orgId,List<String> railIdList,List<String> perPlaceIdList,String isImportant){
		//TODO 周边单位 base_info_peripheral_place
		if(railIdList == null){
			String quSql = "select a.id,a.name,a.category as type,a.type as import,a.lng,a.lat " +
					"from base_info_peripheral_place a ";
			if(perPlaceIdList == null){
				if(!orgId.equals("110")){
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where type='1' " +
								"and a.org_id like ?";
					}else{
						quSql +="where a.org_id like ? ";
					}
					return util.getMapList(quSql, new Object[]{orgId+"%"});
				}else{
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where type='1' ";
					}
					return util.getMapList(quSql, new Object[]{});
				}
			}else{
				String typePSql ="";
				Object[] paramObject = null;
				if(!orgId.equals("110")){
					paramObject = new Object[perPlaceIdList.size()+1];
					paramObject[0] = orgId+"%";
					for(int i=0;i<perPlaceIdList.size();i++){
						paramObject[i+1] = perPlaceIdList.get(i);
						typePSql+="?,";
					}
					typePSql = typePSql.substring(0, typePSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where type='1' " +
								"and a.org_id like ? " +
								"and a.category in ("+typePSql+") ";
					}else{
						quSql +="where a.org_id like ? " +
								"and a.category in ("+typePSql+") ";
					}
				}else{
					paramObject = new Object[perPlaceIdList.size()];
					for(int i=0;i<perPlaceIdList.size();i++){
						paramObject[i] = perPlaceIdList.get(i);
						typePSql+="?,";
					}
					typePSql = typePSql.substring(0, typePSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where type='1' " +
								"and a.category in ("+typePSql+") ";
					}else{
						quSql +="where a.category in ("+typePSql+") ";
					}
				}
				return util.getMapList(quSql, paramObject);
			}
		}else{
			String quSql = "select distinct(a.id),a.name,a.category as type,a.type as import,a.lng,a.lat " +
					"from base_info_peripheral_place_view a ";
			String geomPSql ="";
			Object[] paramObject = null;
			if(perPlaceIdList == null){
				if(!orgId.equals("110")){
					paramObject = new Object[railIdList.size()+1];
					paramObject[0] = orgId+"%";
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+1] = railIdList.get(i);
						geomPSql+="?,";
					}
					geomPSql = geomPSql.substring(0, geomPSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where  type='1' " +
								"and a.org_id like ? " +
								"and a.rail_id in ("+geomPSql+") ";
					}else{
						quSql +="where a.org_id like ? " +
								"and a.rail_id in ("+geomPSql+") ";
					}
				}else{
					paramObject = new Object[railIdList.size()];
					for(int i=0;i<railIdList.size();i++){
						paramObject[i] = railIdList.get(i);
						geomPSql+="?,";
					}
					geomPSql = geomPSql.substring(0, geomPSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where  type='1' " +
								"and a.rail_id in ("+geomPSql+") ";
					}else{
						quSql +="where a.rail_id in ("+geomPSql+") ";
					}
				}
			}else{
				String typePSql ="";
				if(!orgId.equals("110")){
					paramObject = new Object[railIdList.size()+perPlaceIdList.size()+1];
					paramObject[0] = orgId+"%";
					for(int i=0;i<perPlaceIdList.size();i++){
						paramObject[i+1] = perPlaceIdList.get(i);
						typePSql+="?,";
					}
					typePSql = typePSql.substring(0, typePSql.length()-1);
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+1+perPlaceIdList.size()] = railIdList.get(i);
						geomPSql+="?,";
					}
					geomPSql = geomPSql.substring(0, geomPSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where  type='1' " +
								"and a.org_id like ? " +
								"and a.category in ("+typePSql+") "+
								"and a.rail_id in ("+geomPSql+") ";
					}else{
						quSql +="where a.org_id like ? " +
								"and a.category in ("+typePSql+") "+
								"and a.rail_id in ("+geomPSql+") ";
					}
				}else{
					paramObject = new Object[railIdList.size()+perPlaceIdList.size()];
					for(int i=0;i<perPlaceIdList.size();i++){
						paramObject[i] = perPlaceIdList.get(i);
						typePSql+="?,";
					}
					typePSql = typePSql.substring(0, typePSql.length()-1);
					for(int i=0;i<railIdList.size();i++){
						paramObject[i+perPlaceIdList.size()] = railIdList.get(i);
						geomPSql+="?,";
					}
					geomPSql = geomPSql.substring(0, geomPSql.length()-1);
					if(isImportant!=null&&!isImportant.equals("undefined")&&!isImportant.equals("")){
						quSql +="where  type='1' " +
								"and a.category in ("+typePSql+") "+
								"and a.rail_id in ("+geomPSql+") ";
					}else{
						quSql +="where a.category in ("+typePSql+") "+
								"and a.rail_id in ("+geomPSql+") ";
					}
				}
			}
			return util.getMapList(quSql, paramObject);
		}
	}
	
	//北京测试实地考察
	public List<Map<String,Object>> getBJVideoInfoList(){
		//TODO 监控测试 video_information
		String quSql = "select a.id,a.lng,a.lat,'video' AS type " +
				"from video_information a where a.lng is not null and a.lat is not null ";
		return util.getMapList(quSql, new Object[]{});
	}

	public List<Map<String, Object>> getBJCSPoliceHouseInfoList() {
		// TODO Auto-generated method stub
		String quSql = "select a.id,a.lng,a.lat,'policeHouse' AS type ,building_situation as buildingSituation " +
				"from station_information a where a.lng is not null and a.lat is not null ";
		return util.getMapList(quSql, new Object[]{});
	}
}
