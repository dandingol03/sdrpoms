package com.pc.busniess.patrolManagementTask.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.patrolManagementTask.po.PatrolTeamInfoPo;
/**
 * 巡防队伍信息 CRUD
 * @author jiawenlong
 * Dao
 */
@Repository("PatrolManagementTaskDao")
public class PatrolManagementTaskDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 1.组装分页查询（ 名字,分类的模糊查询）  
	 * @param dgm
	 * @param PatrolTeamInfoPo
	 * @return 查询结果集
	 */
	public Map<String, Object> patrolManagementTaskQueryList(DataGridModel dgm,PatrolTeamInfoPo patrolTeamInfoPo,String orgId) {
		// TODO 查询队伍信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		 
		String quSql="";
		    sumSql.append("select count(1) from patrol_team_info t where 1=1 "); // where t.org_id = '"+orgId+"'" +"or t.org_id='' "
		//统计数据详情语句
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.charger as \"charger\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from patrol_team_info t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		//按照名称查询队伍信息
		if (null != patrolTeamInfoPo.getName()&& !patrolTeamInfoPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + patrolTeamInfoPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(patrolTeamInfoPo.getName()).append("%'");
		}
		// 根据 org_id 查询
		if (StringUtils.isNotEmpty(patrolTeamInfoPo.getOrgId())) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", patrolTeamInfoPo.getOrgId()+"%");
			sumSql.append(" and t.org_id like '").append(patrolTeamInfoPo.getOrgId()).append("%' ");
		}
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql(orgId));
			 andquSql=JurisdictionAppendSql.appendSql();
			 params.put("orgId", orgId);
		}
		
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() +andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList = util.getMapList(pageQuerySql, params);
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	/**
	 * 添加队伍信息
	 * @param PatrolManagementTaskPo
	 * @return
	 */
	public int addPatrolManagementTask(PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO 添加队伍信息
		String sql = "insert into patrol_team_info " +
				"(id, " +
				"name," +
				"charger," +
				"telephone," +
				"org_id " +
				")values( " +
				":id," +
				":name," +
				":charger," +
				":telephone," +
				":orgId)";
		return util.editObject(sql, patrolTeamInfoPo);
	}
	/**
	 * 修改队伍信息
	 * @param PatrolManagementTaskPo
	 * @return
	 */
	public int updatePatrolManagementTask(PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO 修改队伍信息
		String sql = "update patrol_team_info set " +
				"name=:name," +
				"charger=:charger," +
				"telephone=:telephone," +
				"org_id=:orgId " +
				"where id=:id";
		String sql2 = "update pub_users set " +
				"user_org =:orgId where user_id in (select a.user_id from (select u.user_id from patrol_team_user_relation u"
				+ " where u.team_info_id=:id) as a)";
	    util.editObject(sql2, patrolTeamInfoPo);
	    return util.editObject(sql, patrolTeamInfoPo);
	}
	/**
	 * 删除队伍信息
	 * @param idList
	 * @return
	 */
	public int[] deletePatrolManagementTask(List<String> idList) {
		// TODO 删除队伍信息
		String delSql = "delete from patrol_team_info where id=?";
		return util.batchDelete(delSql, idList);
	}

	
	
	/**
	 * 队伍信息查询全部地图
	 * @param dgm
	 * @return
	 */
	public List<Map<String, Object>> patrolManagementTaskQueryList() {
		// TODO Auto-generated method stub
		String quSql = "select " +
					"t.id as \"id\", " +
					"t.name as \"name\", " +
					"t.charger as \"charger\", " +
					"t.telephone as \"telephone\", " +
					"t.org_id as \"orgId\", " +
					"b.ORG_NAME as \"orgName\" " +
					"from patrol_team_info t " +
					"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * @param dgm
	 * @param partrolTeamUserRelationPo
	 * @param patrolTeamInfoPo
	 * @param orgId 
	 * @return
	 */
	public Map<String, Object> taskAndUserQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo, String orgId) {
		// TODO Auto-generated method stub
		// TODO 查询队伍信息
				//结果集 分为2个 第一个为总数 total  第二为详细的rows 
				Map<String, Object> result = new HashMap<String, Object>(2);
				//统计数据总数语句
				StringBuffer sumSql = new StringBuffer();
				 
				String quSql="";
				    sumSql.append( "SELECT "+
							"count(t.id) "+
						"FROM " +
						"(SELECT "+
							"id, "+
							"name AS name,"+
							"'team' as type "+
						"FROM "+
							"patrol_team_info te"
							+ " where te.org_id like '"+orgId+"%' "+
						" UNION "+
							"SELECT "+
								 "u.USER_ID as id, "+
								 "u.USER_NAME AS name, "+
								 "'team_user' as type "+
							"FROM "+
								"patrol_team_user_relation r "
								+ "LEFT JOIN pub_users u on u.USER_ID=r.user_id "+
							"WHERE "
							+ " r.team_info_id in ( "
							+ " SELECT "
							+ " te.id "
							+ " FROM "
							+ " patrol_team_info te"
							+ " where te.org_id like '"+orgId+"%' ))as t where 1=1 ");
				//统计数据详情语句
				 quSql = "SELECT "+
							"t.id, "+
							"t.name , "+
							"t.type "+
						"FROM " +
						"(SELECT "+
							"id, "+
							"name AS name,"+
							"'team' as type "+
						"FROM "+
							"patrol_team_info te"
							+ " where te.org_id like '"+orgId+"%' "+
						" UNION "+
							"SELECT "+
								 "u.USER_ID as id, "+
								 "u.USER_NAME AS name, "+
								 "'team_user' as type "+
							"FROM "+
								"patrol_team_user_relation r "
								+ "LEFT JOIN pub_users u on u.USER_ID=r.user_id  "+
							"WHERE "
							+ " r.team_info_id in ( "
							+ " SELECT "
							+ " te.id "
							+ " FROM "
							+ " patrol_team_info te"
							+ " where te.org_id like '"+orgId+"%' ))as t where 1=1 ";
				// 组装查询条件
				StringBuffer sqlSb = new StringBuffer();
				Map<String, Object> params = new HashMap<String, Object>();
				//按照名称查询队伍信息
				if (null != patrolTeamInfoPo.getName()&& !patrolTeamInfoPo.getName().equals("")) {
					sqlSb.append(" and t.name like :name");
					params.put("name", "%" + patrolTeamInfoPo.getName()+ "%");
					sumSql.append(" and t.name like '%").append(patrolTeamInfoPo.getName()).append("%'");
				}
				// 组装排序规则
				String orderString = "";
				if (StringUtils.isNotBlank(dgm.getSort())) {
					orderString = SqlUtil.getOrderbySql(dgm);
				}
				
				// 组装分页定义
				String sql = quSql + sqlSb.toString() + orderString;
				String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
				
				List<Map<String,Object>> rowsList = util.getMapList(pageQuerySql, params);
				
				// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString()));
				result.put("rows", rowsList);
				return result;
	}
	public int checkPatrolData(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from patrol_team_user_relation p , patrol_team_info t "
				+ " where p.team_info_id = t.id and t.id = ?";
		int a=util.getMapList(sql, new Object[] {id}).size();
		if(a==0){
			return 1;
		}else{
			return 0;
		}
	}
	public List<Map<String, Object>> queryTeamList() {
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\" " +
				"from patrol_team_info t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
}
