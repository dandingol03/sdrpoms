package com.pc.busniess.dataRetrieval.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
@Repository("DataRetrievalDao")
public class DataRetrievalDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 1.组装分页查询（ 名字,分类的模糊查询）  
	 * @param dgm
	 * @param PatrolTeamInfoPo
	 * @return 查询结果集
	 */
	public Map<String, Object> dataRetrievalQueryList(DataGridModel dgm, String name, String orgId) {
		// TODO 查询队伍信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("SELECT count(distinct(id)) FROM "
					 + " (SELECT distinct(a.id), a.name, b.org_id AS orgId FROM base_info_rail AS a LEFT OUTER JOIN base_info_rail_data AS b ON a.id = b.rail_id"
				//重点部位
				+ " union SELECT id, name, org_id AS orgId FROM base_info_part_bridge "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_part_culvert "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_part_junction "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_part_tunnel "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_part_station "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_hidden_trajection "
				+ " union SELECT id, name, org_id AS orgId FROM patrol_danger_info "
				//重点人
				+ " union SELECT id, name, org_id AS orgId FROM base_info_keyperson "
				//联防点位
				+ " union SELECT id, name, org_id AS orgId FROM base_info_defence_guard_station "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_defence_propaganda "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_defence_broadcast "
				+ " union SELECT id, name, org_id AS orgId FROM video_monitor_info "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_defence_police_house "
				+ " union SELECT id, name, org_id AS orgId FROM base_info_defence_police_station "
				//在线队员
				+"union select a.user_id as id,b.user_name as name,user_org AS orgId " +
					"from patrol_team_user_relation a,pub_users b " +
					"where a.user_id = b.user_id " +
					"and b.user_org like :userOrg " +
					"and str_to_date(b.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) "
				//在线干部
				+"union select a.user_id as id,a.user_name as name,user_org AS orgId " +
					"from pub_users a " +
					"where a.user_org like :userOrg " +
					"and str_to_date(a.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) " +
					"and a.user_id not in (select c.user_id from patrol_team_user_relation c) "
				//周边场所
				+ " union SELECT id, name, org_id AS orgId FROM base_info_peripheral_place  ) AS allData ");
	
		//统计数据详情语句
		
		String quSql = "SELECT distinct(id), name, lng, lat, url, dataType FROM "
					 + " (SELECT distinct(a.id), a.name, '' AS lng, '' AS lat, b.org_id AS orgId, 'map/baseInfoRail' AS url, 'rail' AS dataType FROM base_info_rail AS a LEFT OUTER JOIN base_info_rail_data AS b ON a.id = b.rail_id"
				////重点部位
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPartBridge' AS url, 'partBridge' AS dataType FROM base_info_part_bridge "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPartCulvert' AS url, 'culvert' AS dataType FROM base_info_part_culvert "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPartJunction' AS url, 'junction' AS dataType FROM base_info_part_junction "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPartTunnel' AS url, 'tunnel' AS dataType FROM base_info_part_tunnel "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPartStation' AS url, 'partStation' AS dataType FROM base_info_part_station "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoHiddenTrajection' AS url, 'hiddenTrajection' AS dataType FROM base_info_hidden_trajection "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/dangerInfo' AS url, 'danger' AS dataType FROM patrol_danger_info "
				//重点人
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/keyperson' AS url, 'keyperson' AS dataType FROM base_info_keyperson "
				//联防点位
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoDefenceGuardStation' AS url, 'defenceGuardStation' AS dataType FROM base_info_defence_guard_station "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoDefencePropaganda' AS url, 'defencePropaganda' AS dataType FROM base_info_defence_propaganda "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoDefenceBroadcast' AS url, 'defenceBroadcast' AS dataType FROM base_info_defence_broadcast "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/videoMonitorInfo' AS url, 'videoMonitor' AS dataType FROM video_monitor_info "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoDefencePoliceHouse' AS url, 'defencePoliceHouse' AS dataType FROM base_info_defence_police_house "
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPoliceStation' AS url, 'policeStation' AS dataType FROM base_info_defence_police_station "
				//在线队员
				+"union select a.user_id as id,b.user_name as name,user_org AS orgId,'map/teamMemberInfo' AS url, 'teamMember' AS dataType " +
					"from patrol_team_user_relation a,pub_users b " +
					"where a.user_id = b.user_id " +
					"and b.user_org like :userOrg " +
					"and str_to_date(b.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) "
				//在线干部
				+"union select a.user_id as id,a.user_name as name,user_org AS orgId,'map/cardeInfo' AS url, 'carde' AS dataType " +
					"from pub_users a " +
					"where a.user_org like :userOrg " +
					"and str_to_date(a.last_time,'%Y-%m-%d %H:%i:%s')>DATE_SUB(now(),interval -3 minute) " +
					"and a.user_id not in (select c.user_id from patrol_team_user_relation c) "
				//周边场所
				+ " union SELECT id, name, lng, lat, org_id AS orgId, 'map/baseInfoPeripheralPlace' AS url, 'peripheralPlace' AS dataType FROM base_info_peripheral_place  ) AS allData";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer(quSql);
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name)) {
			sqlSb.append(" AND allData.name LIKE :name");
			params.put("name", "%" + name+ "%");
			sumSql.append(" AND allData.name LIKE '%").append(name).append("%'");
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userOrg", orgId+"%");
		// 获取该orgId对应的 orgId 以及其下级的orgId
		if (StringUtils.isNotBlank(orgId) && !StringUtils.equals("110", orgId)) {
			String sql = "select od.org_id from pub_org_desc od " +
					"where od.id like (select concat(id, '%') from pub_org_desc where org_id = :orgId)";
			paramMap.put("orgId", orgId);
			List<Map<String,Object>> orgIdList = util.getMapList(sql, paramMap);
			String resultSql = "(1 = 1)";
			if(orgIdList.size() > 0){
				resultSql = "(";
				for(int i=0;i<orgIdList.size();i++){
					resultSql += ("or allData.orgId" + " = '" + orgIdList.get(i).get("org_id").toString() + "'") ;
				}
				resultSql = resultSql.replaceFirst("or", "");
				resultSql += ")";
			}
			sqlSb.append(" AND " + resultSql);
			sumSql.append(" AND " + resultSql);
		}
		
		
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = sqlSb.toString().replaceFirst("AND", "WHERE") + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString().replaceFirst("AND", "WHERE")));
		result.put("rows", rowsList);
		return result;
	}
}
