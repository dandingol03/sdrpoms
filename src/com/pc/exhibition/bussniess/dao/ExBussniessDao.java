package com.pc.exhibition.bussniess.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.PubData;

/**
 * @author simple
 *
 */
@Repository("ExBussniessService")
public class ExBussniessDao {

	@Autowired
	private DBUtil util;
	
	/**
	 * 获得机构列表
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getUserOrgList(String orgId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgId", orgId + "%");
		//TODO 获得机构列表
		if(orgId.length()==3){
			String quSql = "select org_id as orgId," +
					   "org_name as orgName " +
					   "from pub_org " +
					   "where org_id like :orgId " +
					   "and char_length(org_id)<7 " +
					   "order by org_id";
			List<Map<String,Object>> orgList = util.getMapList(quSql, paramMap);
			resultMap.put("orgList", orgList);
			return resultMap;
		}else{
			String quSql = "select org_id as orgId," +
					   "org_name as orgName " +
					   "from pub_org " +
					   "where org_id like :orgId " +
					   "order by org_id";
			List<Map<String,Object>> orgList = util.getMapList(quSql, paramMap);
			resultMap.put("orgList", orgList);
			return resultMap;
		}
	}
	/**
	 * 获得机构统计信息 地球半径 6371.393 3.1415926535897932384626433 111201.78578851907506104564601732
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getUserOrgListInfo(String orgId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgId", orgId + "%");
		//TODO 获得下辖区和街道个数(涉及铁路线路)
		String quSql = "select count(distinct(a.org_id)) as aeraCount " +
				  "from base_info_rail_view a where char_length(a.org_id)=6 and a.org_id like :orgId and a.length is not null";
		Map<String,Object> aeraCount = util.getMapObject(quSql, paramMap); 
		
		quSql = "select count(distinct(a.org_id)) as streetCount " +
				  "from base_info_rail_view a where char_length(a.org_id)=9 and a.org_id like :orgId and a.length is not null";
		Map<String,Object> orgCount = util.getMapObject(quSql, paramMap); 
		orgCount.put("aeraCount", aeraCount.get("aeraCount"));
		resultMap.put("orgCount", orgCount);
		
		//TODO 获取铁路类型和条数
		paramMap.put("orgId", orgId);
		List<Map<String,Object>> railTypeList = PubData.getDictList("RAIL_CLASSIFY");
		quSql = "select count(DISTINCT(a.id)) as '全部线路',convert(sum(a.length)/1000,decimal(12,0)) as '总里程' ";
		for(int i=railTypeList.size()-1;i>=0;i--){
			quSql +=",count(a.classify='"+railTypeList.get(i).get("dictData")+"' or null) as '"+railTypeList.get(i).get("dictName")+"' ";
		}
		quSql +="from base_info_rail_view a " +
				"where a.org_id=:orgId " +
				"and a.length is not null ";
		
		Map<String,Object> tempCountMap = util.getMapObject(quSql, paramMap);
		resultMap.put("totalDistance", tempCountMap.get("总里程"));
		Map<String,Object> totalMap = new HashMap<String,Object>();
		totalMap.put("dictName", "全部线路");
		totalMap.put("dictData", "total");
		railTypeList.add(0,totalMap);
		List<Map<String,Object>> railList = new ArrayList<Map<String,Object>>();
		for(int i=railTypeList.size()-1;i>=0;i--){
			int tempCount = Integer.parseInt(tempCountMap.get(railTypeList.get(i).get("dictName")).toString());
			Map<String,Object> tempRailMap = new HashMap<String,Object>();
			tempRailMap.put("dictName", railTypeList.get(i).get("dictName"));
			tempRailMap.put("dictData", tempCount);
			railList.add(0,tempRailMap);
			/*if(tempCount<1){
				railTypeList.remove(i);
			}*/
		}
		resultMap.put("railTypeList", railTypeList);
		resultMap.put("railList", railList);
		return resultMap;
	}
	
	/**
	 * 根据机构和铁路ID获得铁路ID、名称和分类 以及数量 (全部类型以及数据字典类型)
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserRailListInfo(String orgId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//TODO 获得铁路所有列表与类型数据
		String quSql = "select distinct(a.id) as railId," +
					   "a.name as railName," +
					   "a.classify as railType " +
					   "from base_info_rail_view a " +
					   "where a.org_id=:orgId " +
					   "and a.length is not null " +
					   "order by a.classify ";
		paramMap.put("orgId", orgId);
		List<Map<String,Object>> railList = util.getMapList(quSql, paramMap);
		resultMap.put("railList", railList);
		return resultMap;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点部位统计信息
	 * @param request
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserKeyPartListInfo(String orgId,List<String> railIdList){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 桥涵 base_info_part_bridge base_info_part_culvert
			String quSql = "select count(a.id) as bridgeCount " +
								"from base_info_part_bridge a ";
			
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> bridgeCount = util.getMapObject(quSql, paramObject);
			quSql = "select count(a.id) as culvertCount " +
						"from base_info_part_culvert a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> culvertCount = util.getMapObject(quSql,paramObject);
			String bcCount = String.valueOf(Integer.parseInt(bridgeCount.get("bridgeCount").toString())+Integer.parseInt(culvertCount.get("culvertCount").toString()));
			resultMap.put("bcCount",bcCount);
			
			//TODO 道口 base_info_part_junction
			quSql = "select count(a.id) as junctionCount " +
						"from base_info_part_junction a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> junctionCount = util.getMapObject(quSql,paramObject);
			resultMap.put("junctionCount",junctionCount.get("junctionCount").toString());
			
			//TODO 隧道 base_info_part_tunnel
			quSql = "select count(a.id) as tunnelCount " +
					"from base_info_part_tunnel a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> tunnelCount = util.getMapObject(quSql,paramObject);
			resultMap.put("tunnelCount", tunnelCount.get("tunnelCount").toString());
			
			//TODO 车站 base_info_part_station
			quSql = "select count(a.id) as stationCount " +
					"from base_info_part_station a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> stationCount = util.getMapObject(quSql,paramObject);
			resultMap.put("stationCount", stationCount.get("stationCount").toString());
			
			//TODO 行人易穿行 base_info_part_trajection
			quSql = "select count(a.id) as trajectionCount " +
					"from base_info_part_trajection a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> trajectionCount = util.getMapObject(quSql,paramObject);
			resultMap.put("trajectionCount", trajectionCount.get("trajectionCount").toString());
			
			//TODO 重点人 base_info_keyperson
			quSql = "select count(a.id) as keyPersonCount " +
					"from base_info_keyperson a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> keyPersonCount = util.getMapObject(quSql,paramObject);
			resultMap.put("keyPersonCount", keyPersonCount.get("keyPersonCount").toString());
			//TODO 隐患点 patrol_danger_info
			quSql = "select count(a.id) as dangerCount " +
					"from patrol_danger_info a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? ";
			}
			Map<String,Object> dangerCount = util.getMapObject(quSql,paramObject);
			resultMap.put("dangerCount", dangerCount.get("dangerCount").toString());
		}else{
			for (int k = 0; k < railIdList.size(); k++) {
				String id=railIdList.get(k).substring(0,4);
				if(StringUtils.equals(id,"stre")){
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
					String quSql = "select count(distinct(a.id)) as bridgeCount " +
										"from base_info_part_bridge_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> bridgeCount = util.getMapObject(quSql, paramObject);
					quSql = "select count(distinct(a.id)) as culvertCount " +
								"from base_info_part_culvert_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> culvertCount = util.getMapObject(quSql, paramObject);
					String bcCount = String.valueOf(Integer.parseInt(bridgeCount.get("bridgeCount").toString())+Integer.parseInt(culvertCount.get("culvertCount").toString()));
					resultMap.put("bcCount",bcCount);
					
					//TODO 道口 base_info_part_junction
					quSql = "select count(distinct(a.id)) as junctionCount " +
								"from base_info_part_junction_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> junctionCount = util.getMapObject(quSql, paramObject);
					resultMap.put("junctionCount",junctionCount.get("junctionCount").toString());
					
					//TODO 隧道 base_info_part_tunnel
					quSql = "select count(distinct(a.id)) as tunnelCount " +
							"from base_info_part_tunnel_view a " ;
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					
					Map<String,Object> tunnelCount = util.getMapObject(quSql, paramObject);
					resultMap.put("tunnelCount", tunnelCount.get("tunnelCount").toString());
					
					//TODO 车站 base_info_part_station
					quSql = "select count(distinct(a.id)) as stationCount " +
							"from base_info_part_station_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					
					Map<String,Object> stationCount = util.getMapObject(quSql, paramObject);
					resultMap.put("stationCount", stationCount.get("stationCount").toString());
					
					//TODO 行人易穿行 base_info_part_trajection
					quSql = "select count(distinct(a.id)) as trajectionCount " +
							"from base_info_part_trajection_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> trajectionCount = util.getMapObject(quSql, paramObject);
					resultMap.put("trajectionCount", trajectionCount.get("trajectionCount").toString());
					
					//TODO 重点人 base_info_keyperson
					quSql = "select count(distinct(a.id)) as keyPersonCount " +
							"from base_info_keyperson_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> keyPersonCount = util.getMapObject(quSql, paramObject);
					resultMap.put("keyPersonCount", keyPersonCount.get("keyPersonCount").toString());
					//TODO 隐患点 patrol_danger_info
					quSql = "select count(distinct(a.id)) as dangerCount " +
							"from patrol_danger_info_view a ";
					if(!orgId.equals("110")){
						quSql+="where a.org_id like ? "+
								"and a.rail_stream_id in ("+questionSql+") ";
					}else{
						quSql+="where a.rail_stream_id in ("+questionSql+") ";
					}
					Map<String,Object> dangerCount = util.getMapObject(quSql, paramObject);
					resultMap.put("dangerCount", dangerCount.get("dangerCount").toString());
				
					return resultMap;
				}
			}
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
			String quSql = "select count(distinct(a.id)) as bridgeCount " +
								"from base_info_part_bridge_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> bridgeCount = util.getMapObject(quSql, paramObject);
			quSql = "select count(distinct(a.id)) as culvertCount " +
						"from base_info_part_culvert_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> culvertCount = util.getMapObject(quSql, paramObject);
			String bcCount = String.valueOf(Integer.parseInt(bridgeCount.get("bridgeCount").toString())+Integer.parseInt(culvertCount.get("culvertCount").toString()));
			resultMap.put("bcCount",bcCount);
			
			//TODO 道口 base_info_part_junction
			quSql = "select count(distinct(a.id)) as junctionCount " +
						"from base_info_part_junction_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> junctionCount = util.getMapObject(quSql, paramObject);
			resultMap.put("junctionCount",junctionCount.get("junctionCount").toString());
			
			//TODO 隧道 base_info_part_tunnel
			quSql = "select count(distinct(a.id)) as tunnelCount " +
					"from base_info_part_tunnel_view a " ;
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			
			Map<String,Object> tunnelCount = util.getMapObject(quSql, paramObject);
			resultMap.put("tunnelCount", tunnelCount.get("tunnelCount").toString());
			
			//TODO 车站 base_info_part_station
			quSql = "select count(distinct(a.id)) as stationCount " +
					"from base_info_part_station_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> stationCount = util.getMapObject(quSql, paramObject);
			resultMap.put("stationCount", stationCount.get("stationCount").toString());
			
			//TODO 行人易穿行 base_info_part_trajection
			quSql = "select count(distinct(a.id)) as trajectionCount " +
					"from base_info_part_trajection_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> trajectionCount = util.getMapObject(quSql, paramObject);
			resultMap.put("trajectionCount", trajectionCount.get("trajectionCount").toString());
			
			//TODO 重点人 base_info_keyperson
			quSql = "select count(distinct(a.id)) as keyPersonCount " +
					"from base_info_keyperson_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> keyPersonCount = util.getMapObject(quSql, paramObject);
			resultMap.put("keyPersonCount", keyPersonCount.get("keyPersonCount").toString());
			//TODO 隐患点 patrol_danger_info
			quSql = "select count(distinct(a.id)) as dangerCount " +
					"from patrol_danger_info_view a ";
			if(!orgId.equals("110")){
				quSql+="where a.org_id like ? "+
						"and a.rail_id in ("+questionSql+") ";
			}else{
				quSql+="where a.rail_id in ("+questionSql+") ";
			}
			Map<String,Object> dangerCount = util.getMapObject(quSql, paramObject);
			resultMap.put("dangerCount", dangerCount.get("dangerCount").toString());
		}
		return resultMap;
	}
	
	/**
	 * 根据当前用户机构和铁路获得隐患点类型统计
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getPlaceDangerListInfo(String orgId,List<String> railIdList){
		//TODO 隐患点 patrol_danger_info
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//隐患分类
			List<Map<String,Object>> dangerTypeList = PubData.getDictList("DANGERS_TYPE");
			String quSql = "select count(a.id) as '全部' ";
			for(int i=dangerTypeList.size()-1;i>=0;i--){
				quSql +=",count(distinct(if(a.danger_type='"+dangerTypeList.get(i).get("dictData")+"',a.id,null))) as '"+dangerTypeList.get(i).get("dictName")+"' ";
			}
			quSql +="from patrol_danger_info a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> tempCountMap = util.getMapObject(quSql, paramObject);
			Map<String,Object> totalMap = new HashMap<String,Object>();
			totalMap.put("dictName", "全部");
			totalMap.put("dictData", "total");
			dangerTypeList.add(0,totalMap);
			for(int i=dangerTypeList.size()-1;i>=0;i--){
				int tempCount = Integer.parseInt(tempCountMap.get(dangerTypeList.get(i).get("dictName")).toString());
				/*if(tempCount<1){
					dangerTypeList.remove(i);
					continue;
				}*/
				dangerTypeList.get(i).put("dictCount", tempCount);
			}
			resultMap.put("dangerTypeList", dangerTypeList);
		}else{
			String dangerSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					dangerSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					dangerSql+="?,";
				}
			}
			dangerSql = dangerSql.substring(0, dangerSql.length()-1);
			//隐患分类
			List<Map<String,Object>> dangerTypeList = PubData.getDictList("DANGERS_TYPE");
			String quSql = "select count(distinct(a.id)) as '全部' ";
			for(int i=dangerTypeList.size()-1;i>=0;i--){
				quSql +=",count(distinct(if(a.danger_type='"+dangerTypeList.get(i).get("dictData")+"',a.id,null))) as '"+dangerTypeList.get(i).get("dictName")+"' ";
			}
			quSql +="from patrol_danger_info_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+dangerSql+") ";
			}else{
				quSql +="where a.rail_id in ("+dangerSql+") ";
			}
			Map<String,Object> tempCountMap = util.getMapObject(quSql, paramObject);
			Map<String,Object> totalMap = new HashMap<String,Object>();
			totalMap.put("dictName", "全部");
			totalMap.put("dictData", "total");
			dangerTypeList.add(0,totalMap);
			for(int i=dangerTypeList.size()-1;i>=0;i--){
				int tempCount = Integer.parseInt(tempCountMap.get(dangerTypeList.get(i).get("dictName")).toString());
				/*if(tempCount<1){
					dangerTypeList.remove(i);
					continue;
				}*/
				dangerTypeList.get(i).put("dictCount", tempCount);
			}
			resultMap.put("dangerTypeList", dangerTypeList);
		}
		return resultMap;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关联防点位统计信息
	 * @param request
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserJDenfenseListInfo(String orgId,List<String> railIdList){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 工作站 base_info_defence_guard_station
			String quSql = "select count(a.id) as stationCount " +
					"from base_info_defence_guard_station a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> stationCount = util.getMapObject(quSql, paramObject);
			resultMap.put("stationCount", stationCount.get("stationCount").toString());
			
			//TODO 宣传点 base_info_defence_propaganda
			quSql = "select count(a.id) as propagandaCount " +
					"from base_info_defence_propaganda a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> propagandaCount = util.getMapObject(quSql, paramObject);
			resultMap.put("propagandaCount", propagandaCount.get("propagandaCount").toString());
			
			//TODO 警示柱 base_info_defence_broadcast
			quSql = "select count(a.id) as broadcastCount " +
					"from base_info_defence_broadcast a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> broadcastCount = util.getMapObject(quSql, paramObject);
			resultMap.put("broadcastCount", broadcastCount.get("broadcastCount").toString());
			
			//TODO 摄像头 video_monitor_info
			quSql = "select count(a.id) as monitorCount " +
					"from video_monitor_info a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> monitorCount = util.getMapObject(quSql, paramObject);
			resultMap.put("monitorCount", monitorCount.get("monitorCount").toString());

			//TODO 派出所 base_info_defence_police_house
			quSql = "select count(a.id) as policeHouseCount " +
					"from base_info_defence_police_house a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> policeHouseCount = util.getMapObject(quSql,paramObject);
			resultMap.put("policeHouseCount", policeHouseCount.get("policeHouseCount").toString());
			
			//TODO 警务站 base_info_defence_police_station
			quSql = "select count(a.id) as policeStationCount " +
					"from base_info_defence_police_station a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			Map<String,Object> policeStationCount = util.getMapObject(quSql,paramObject);
			resultMap.put("policeStationCount", policeStationCount.get("policeStationCount").toString());
			
			//TODO 专职队员 patrol_team_user_relation pub_users
			quSql = "select count(a.user_id) as teamUserCount " +
					"from patrol_team_user_relation a,pub_users b " +
					"where a.user_id = b.user_id " +
					"and b.user_org like ? " +
					"and str_to_date(b.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) ";
			Map<String,Object> teamUserCount = util.getMapObject(quSql,new Object[]{orgId+"%"});
			resultMap.put("teamUserCount", teamUserCount.get("teamUserCount").toString());
			
			//TODO 在线干部 pub_users
			quSql = "select count(a.user_id) as userCount " +
					"from pub_users a " +
					"where a.user_org like ? " +
					"and str_to_date(a.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) " +
					"and a.user_id not in (select c.user_id from patrol_team_user_relation c) ";
			Map<String,Object> userCount = util.getMapObject(quSql,new Object[]{orgId+"%"});
			resultMap.put("userCount", userCount.get("userCount").toString());
		}else{
			for (int k = 0; k < railIdList.size(); k++) {
				String id=railIdList.get(k).substring(0,4);
				if(StringUtils.equals(id,"stre")){
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
					String quSql = "select count(distinct(a.id)) as stationCount " +
							"from base_info_defence_guard_station_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					Map<String,Object> stationCount = util.getMapObject(quSql, paramObject);
					resultMap.put("stationCount", stationCount.get("stationCount").toString());
					
					//TODO 宣传点 base_info_defence_propaganda
					quSql = "select count(distinct(a.id)) as propagandaCount " +
							"from base_info_defence_propaganda_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> propagandaCount = util.getMapObject(quSql, paramObject);
					resultMap.put("propagandaCount", propagandaCount.get("propagandaCount").toString());
					
					//TODO 警示柱 base_info_defence_broadcast
					quSql = "select count(distinct(a.id)) as broadcastCount " +
							"from base_info_defence_broadcast_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> broadcastCount = util.getMapObject(quSql, paramObject);
					resultMap.put("broadcastCount", broadcastCount.get("broadcastCount").toString());
					
					//TODO 摄像头 video_monitor_info
					quSql = "select count(distinct(a.id)) as monitorCount " +
							"from video_monitor_info_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> monitorCount = util.getMapObject(quSql, paramObject);
					resultMap.put("monitorCount", monitorCount.get("monitorCount").toString());

					//TODO 派出所 base_info_defence_police_house
					quSql = "select count(distinct(a.id)) as policeHouseCount " +
							"from base_info_defence_police_house_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> policeHouseCount = util.getMapObject(quSql, paramObject);
					resultMap.put("policeHouseCount", policeHouseCount.get("policeHouseCount").toString());
					
					//TODO 警务站 base_info_defence_police_station
					quSql = "select count(distinct(a.id)) as policeStationCount " +
							"from base_info_defence_police_station_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> policeStationCount = util.getMapObject(quSql, paramObject);
					resultMap.put("policeStationCount", policeStationCount.get("policeStationCount").toString());
					
					//TODO 专职队员 patrol_team_user_relation pub_users
					quSql = "select count(distinct(a.user_id)) as teamUserCount " +
							"from online_team_member_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
					
					Map<String,Object> teamUserCount = util.getMapObject(quSql, paramObject);
					resultMap.put("teamUserCount", teamUserCount.get("teamUserCount").toString());
					
					//TODO 在线干部 pub_users
					quSql = "select count(distinct(a.user_id)) as userCount " +
							"from online_carde_view a ";
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+denfenseSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+denfenseSql+") ";
					}
							
					Map<String,Object> userCount = util.getMapObject(quSql, paramObject);
					resultMap.put("userCount", userCount.get("userCount").toString());
					return resultMap;
				}
			}
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
			String quSql = "select count(distinct(a.id)) as stationCount " +
					"from base_info_defence_guard_station_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			Map<String,Object> stationCount = util.getMapObject(quSql, paramObject);
			resultMap.put("stationCount", stationCount.get("stationCount").toString());
			
			//TODO 宣传点 base_info_defence_propaganda
			quSql = "select count(distinct(a.id)) as propagandaCount " +
					"from base_info_defence_propaganda_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> propagandaCount = util.getMapObject(quSql, paramObject);
			resultMap.put("propagandaCount", propagandaCount.get("propagandaCount").toString());
			
			//TODO 警示柱 base_info_defence_broadcast
			quSql = "select count(distinct(a.id)) as broadcastCount " +
					"from base_info_defence_broadcast_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> broadcastCount = util.getMapObject(quSql, paramObject);
			resultMap.put("broadcastCount", broadcastCount.get("broadcastCount").toString());
			
			//TODO 摄像头 video_monitor_info
			quSql = "select count(distinct(a.id)) as monitorCount " +
					"from video_monitor_info_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> monitorCount = util.getMapObject(quSql, paramObject);
			resultMap.put("monitorCount", monitorCount.get("monitorCount").toString());

			//TODO 派出所 base_info_defence_police_house
			quSql = "select count(distinct(a.id)) as policeHouseCount " +
					"from base_info_defence_police_house_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> policeHouseCount = util.getMapObject(quSql, paramObject);
			resultMap.put("policeHouseCount", policeHouseCount.get("policeHouseCount").toString());
			
			//TODO 警务站 base_info_defence_police_station
			quSql = "select count(distinct(a.id)) as policeStationCount " +
					"from base_info_defence_police_station_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> policeStationCount = util.getMapObject(quSql, paramObject);
			resultMap.put("policeStationCount", policeStationCount.get("policeStationCount").toString());
			
			//TODO 专职队员 patrol_team_user_relation pub_users
			quSql = "select count(distinct(a.user_id)) as teamUserCount " +
					"from online_team_member_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
			
			Map<String,Object> teamUserCount = util.getMapObject(quSql, paramObject);
			resultMap.put("teamUserCount", teamUserCount.get("teamUserCount").toString());
			
			//TODO 在线干部 pub_users
			quSql = "select count(distinct(a.user_id)) as userCount " +
					"from online_carde_view a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+denfenseSql+") ";
			}else{
				quSql +="where a.rail_id in ("+denfenseSql+") ";
			}
					
			Map<String,Object> userCount = util.getMapObject(quSql, paramObject);
			resultMap.put("userCount", userCount.get("userCount").toString());
		}
		return resultMap;
	}

	
	/**
	 * 根据当前用户机构和铁路获得周边单位统计信息
	 * @param request
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserPeripheralPlaceListInfo(String orgId,List<String> railIdList){
		//TODO 周边单位 base_info_peripheral_place
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(railIdList == null){
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[]{orgId+"%"};
			}
			//TODO 获取数据字典 
			List<Map<String,Object>> perPlaceTypeList = PubData.getDictList("PERIPHERAL_PLACE");
			String quSql = "select count(a.id) as '全部' ";
			String quImport = "";
			for(int i=perPlaceTypeList.size()-1;i>=0;i--){
				quSql +=",count(distinct(if(a.category='"+perPlaceTypeList.get(i).get("dictData")+"',a.id,null))) as '"+perPlaceTypeList.get(i).get("dictName")+"' ";
			}
			quSql +="from base_info_peripheral_place a ";
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? ";
			}
			quImport = quSql;
			if(!orgId.equals("110")){
				quImport+="and a.type='1' ";
			}else{
				quImport+="where a.type='1' ";
			}
			
			Map<String,Object> tempCountMap = util.getMapObject(quSql,paramObject);
			Map<String,Object> tempImportCountMap = util.getMapObject(quImport,paramObject);
			
			Map<String,Object> totalMap = new HashMap<String,Object>();
			totalMap.put("dictName", "全部");
			totalMap.put("dictData", "total");
			perPlaceTypeList.add(0,totalMap);
			for(int i=perPlaceTypeList.size()-1;i>=0;i--){
				perPlaceTypeList.get(i).put("dictCount", tempCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
				perPlaceTypeList.get(i).put("dictImportCount", tempImportCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
				/*if(tempCount<1){
					perPlaceTypeList.remove(i);
				}*/
			}
			resultMap.put("perPlaceTypeList", perPlaceTypeList);
		}else{
			for (int j = 0; j < railIdList.size(); j++) {
				String id=railIdList.get(j).substring(0,4);
				if(StringUtils.equals(id,"stre")){
					String perPSql ="";
					Object[] paramObject = null;
					if(!orgId.equals("110")){
						paramObject = new Object[railIdList.size()+1];
						paramObject[0] = orgId+"%";
						for(int i=0;i<railIdList.size();i++){
							paramObject[i+1] = railIdList.get(i);
							perPSql+="?,";
						}
					}else{
						paramObject = new Object[railIdList.size()];
						for(int i=0;i<railIdList.size();i++){
							paramObject[i] = railIdList.get(i);
							perPSql+="?,";
						}
					}
					perPSql = perPSql.substring(0, perPSql.length()-1);
					//TODO 获取数据字典 
					List<Map<String,Object>> perPlaceTypeList = PubData.getDictList("PERIPHERAL_PLACE");
					String quSql = "select count(distinct(a.id)) as '全部' ";
					String quImport = "";
					for(int i=perPlaceTypeList.size()-1;i>=0;i--){
						quSql +=",count(distinct(if(a.category='"+perPlaceTypeList.get(i).get("dictData")+"',a.id,null))) as '"+perPlaceTypeList.get(i).get("dictName")+"' ";
					}
					quSql +="from base_info_peripheral_place_view a ";
					quImport = quSql;
					if(!orgId.equals("110")){
						quSql +="where a.org_id like ? " +
								"and a.rail_stream_id in ("+perPSql+") ";
						quImport +="where a.org_id like ? " +
								"and type='1' "+
								"and a.rail_stream_id in ("+perPSql+") ";
					}else{
						quSql +="where a.rail_stream_id in ("+perPSql+") ";
						quImport +="where type='1' "+
								"and a.rail_stream_id in ("+perPSql+") ";
					}
					
					Map<String,Object> tempCountMap = util.getMapObject(quSql, paramObject);
					Map<String,Object> tempImportCountMap = util.getMapObject(quImport, paramObject);
					
					Map<String,Object> totalMap = new HashMap<String,Object>();
					totalMap.put("dictName", "全部");
					totalMap.put("dictData", "total");
					perPlaceTypeList.add(0,totalMap);
					for(int i=perPlaceTypeList.size()-1;i>=0;i--){
						perPlaceTypeList.get(i).put("dictCount", tempCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
						perPlaceTypeList.get(i).put("dictImportCount", tempImportCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
					}
					resultMap.put("perPlaceTypeList", perPlaceTypeList);
					return resultMap;	
				}
			}
			String perPSql ="";
			Object[] paramObject = null;
			if(!orgId.equals("110")){
				paramObject = new Object[railIdList.size()+1];
				paramObject[0] = orgId+"%";
				for(int i=0;i<railIdList.size();i++){
					paramObject[i+1] = railIdList.get(i);
					perPSql+="?,";
				}
			}else{
				paramObject = new Object[railIdList.size()];
				for(int i=0;i<railIdList.size();i++){
					paramObject[i] = railIdList.get(i);
					perPSql+="?,";
				}
			}
			perPSql = perPSql.substring(0, perPSql.length()-1);
			//TODO 获取数据字典 
			List<Map<String,Object>> perPlaceTypeList = PubData.getDictList("PERIPHERAL_PLACE");
			String quSql = "select count(distinct(a.id)) as '全部' ";
			String quImport = "";
			for(int i=perPlaceTypeList.size()-1;i>=0;i--){
				quSql +=",count(distinct(if(a.category='"+perPlaceTypeList.get(i).get("dictData")+"',a.id,null))) as '"+perPlaceTypeList.get(i).get("dictName")+"' ";
			}
			quSql +="from base_info_peripheral_place_view a ";
			quImport = quSql;
			if(!orgId.equals("110")){
				quSql +="where a.org_id like ? " +
						"and a.rail_id in ("+perPSql+") ";
				quImport +="where a.org_id like ? " +
						"and type='1' "+
						"and a.rail_id in ("+perPSql+") ";
			}else{
				quSql +="where a.rail_id in ("+perPSql+") ";
				quImport +="where type='1' "+
						"and a.rail_id in ("+perPSql+") ";
			}
			
			Map<String,Object> tempCountMap = util.getMapObject(quSql, paramObject);
			Map<String,Object> tempImportCountMap = util.getMapObject(quImport, paramObject);
			
			Map<String,Object> totalMap = new HashMap<String,Object>();
			totalMap.put("dictName", "全部");
			totalMap.put("dictData", "total");
			perPlaceTypeList.add(0,totalMap);
			for(int i=perPlaceTypeList.size()-1;i>=0;i--){
				perPlaceTypeList.get(i).put("dictCount", tempCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
				perPlaceTypeList.get(i).put("dictImportCount", tempImportCountMap.get(perPlaceTypeList.get(i).get("dictName")).toString());
				/*if(tempCount<1){
					perPlaceTypeList.remove(i);
				}*/
			}
			resultMap.put("perPlaceTypeList", perPlaceTypeList);
		}
		return resultMap;
	}

}
