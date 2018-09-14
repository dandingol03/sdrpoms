package com.pc.busniess.patrolTrackInfo.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.patrolManagementTask.po.PatrolManagementTaskPo;


/**
 * 这个Dao层主要是对数据库的操作
 * @author jiawenlong
 */
@Repository("PatrolTrackInfoDao")
public class PatrolTrackInfoDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 查询所有的人信息
	 * @return
	 */
	public List<Map<String, Object>> queryPers() {
		//统计数据详情语句
		String quSql = "select " +
				"t.USER_ID as \"id\", " +
				"t.USER_NAME as \"name\"  " +
				"from pub_users t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * 1.组装分页查询（ 名字,分类的模糊查询）  
	 * @param dgm
	 * @param PatrolTrackInfoPo
	 * @return 查询结果集
	 */
	public Map<String, Object> PatrolTrackInfoQueryList(DataGridModel dgm, PatrolManagementTaskPo patrolManagementTaskPo) {
		// TODO 查询巡防轨迹信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from patrol_track_info t where 1=1 ");
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.user_id as \"userId\", " +
				"t.start_time as \"startTime\", " +
				"t.end_time as \"endTime\", " +
				"t.track as \"track\", " +
				"t.remark as \"remark\", " +
				"c.USER_NAME as \"userName\" "+
				"from patrol_track_info t " +
				"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null !=patrolManagementTaskPo.getUserId()&& !patrolManagementTaskPo.getUserId().equals("")) {
			sqlSb.append(" and t.user_id = :userId");
			params.put("userId",patrolManagementTaskPo.getUserId());
			sumSql.append(" and t.user_id = '"+patrolManagementTaskPo.getUserId()+"'");
		}
		if(patrolManagementTaskPo.getStartTime()!=null&&!patrolManagementTaskPo.getStartTime().equals(""))
		{
			sqlSb.append(" and UNIX_TIMESTAMP(str_to_date(start_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"+patrolManagementTaskPo.getStartTime().trim()+"','%Y-%m-%d %H:%i:%s')) ");
			sumSql.append(" and UNIX_TIMESTAMP(str_to_date(start_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"+patrolManagementTaskPo.getStartTime().trim()+"','%Y-%m-%d %H:%i:%s'))");
		}
		if(patrolManagementTaskPo.getEndTime()!=null&&!patrolManagementTaskPo.getEndTime().equals(""))
		{
			sqlSb.append(" and UNIX_TIMESTAMP(str_to_date(end_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"+patrolManagementTaskPo.getEndTime().trim()+"','%Y-%m-%d %H:%i:%s'))");
			sumSql.append(" and UNIX_TIMESTAMP(str_to_date(end_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"+patrolManagementTaskPo.getEndTime().trim()+"','%Y-%m-%d %H:%i:%s'))");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//查询巡防轨迹关联数量
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	
	/**
	 * 删除巡防轨迹信息
	 * @param idList
	 * @return
	 */
	public int[] deletePatrolTrackInfo(List<String> idList) {
		// TODO 删除巡防轨迹信息
		String delSql = "delete from patrol_track_info where id=?";
		return util.batchDelete(delSql, idList);
	}
	/**
	 * 车站查询
	 * @return
	 */
	public List<Map<String, Object>> queryPartStation() {
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\"  " +
				"from base_info_part_station t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	
}
