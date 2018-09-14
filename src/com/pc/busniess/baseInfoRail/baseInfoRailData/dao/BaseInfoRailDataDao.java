package com.pc.busniess.baseInfoRail.baseInfoRailData.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
/**
 * 线路坐标
 * @author xb
 */
@Repository("baseInfoRailDataDao")
public class BaseInfoRailDataDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 查询所有的线路信息（增加线路信息时需要）
	 * @return
	 */
	public List<Map<String, Object>> queryRails() {
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.classify as \"classify\", " +
				"t.start as \"start\", " +
				"t.end as \"end\", " +
				"t.length as \"length\", " +
				"t.station_num as \"stationNum\", " +
				"t.bridge_num as \"bridgeNum\", " +
				"t.tunnel_num as \"tunnelNum\", " +
				"t.junction_num as \"junctionNum\", " +
				"t.culvert_num as \"culvertNum\", " +
				"t.crossrail_num as \"crossrailNum\", " +
				"t.remark as \"remark\" " +
				"from base_info_rail t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}

	/**
	 * 查询线路信息
	 * @param dgm
	 * @param pubMapRailDataPo
	 * @return
	 */
	public Map<String, Object> pubMapRailDataQueryList(DataGridModel dgm, BaseInfoRailDataPo pubMapRailDataPo,String orgId) {
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
				Map<String, Object> result = new HashMap<String, Object>(2);
				
				//统计数据总数语句
				StringBuffer sumSql = new StringBuffer();
				
				String quSql="";
				    sumSql.append("select count(1) from base_info_rail_data t where 1=1  " );
				  //统计数据详情语句
					quSql = "select " +
						"t.id as \"id\", " +
						"t.rail_id as \"railId\", " +
						"t.rail_name as \"railName\", " +
						"cast(t.order_number as SIGNED INTEGER) as \"orderNumber\", " +
						"t.lng as \"lng\", " +
						"t.lat as \"lat\", " +
						"t.kilometer_mark as \"kilometerMark\", " +
						"t.org_id as \"orgId\", " +
						"t.remark as \"remark\", " +
						"o.ORG_NAME as \"descOrgName\", " +
						"b.ORG_NAME as \"orgName\" " +
						"from base_info_rail_data t " +
						"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
						"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
						"where 1=1 and t.rail_id =:railId ";

				// 组装查询条件
				StringBuffer sqlSb = new StringBuffer();
				Map<String, Object> params = new HashMap<String, Object>();
				if (null !=pubMapRailDataPo.getRailId()&& !pubMapRailDataPo.getRailId().equals("")) {
					params.put("railId",pubMapRailDataPo.getRailId());
					sumSql.append(" and t.rail_id = '"+pubMapRailDataPo.getRailId()+"'");
				}
				//按照rail_name查询线路信息
				if (StringUtils.isNotEmpty(pubMapRailDataPo.getRailName())) {
					sqlSb.append(" and t.rail_name like :railName");
					params.put("railName", "%" + pubMapRailDataPo.getRailName()+ "%");
					sumSql.append(" and t.rail_name like '%").append(pubMapRailDataPo.getRailName()).append("%'");
				}
				// 根据 org_id 查询
				if (StringUtils.isNotEmpty(pubMapRailDataPo.getOrgId())) {
					sqlSb.append(" and t.org_id like :orgId");
					params.put("orgId", pubMapRailDataPo.getOrgId()+ "%");
					sumSql.append(" and t.org_id like '").append(pubMapRailDataPo.getOrgId()).append("%'");
				}
				
				// 组装排序规则
				String orderString = "";
				if (StringUtils.isNotBlank(dgm.getSort())) {
					orderString = SqlUtil.getOrderbySql(dgm);
				}
				
				// 组装分页定义
				String sql = quSql + sqlSb.toString() + orderString;
				String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
				List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
				if(rowsList!=null&&rowsList.size()>0){
					for(int i=0;i<rowsList.size();i++){
						Map<String,Object> row=rowsList.get(i);
						Object type=row.get("stream");
						if(type!=null&&!"".equals(type.toString().trim())){
							row.put("streamName", PubData.getDictName("RAIL_STREAM", type.toString()));		
						}
					}
				}
				// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString()));
				result.put("rows", rowsList);

				return result;
	}
	/**
	 * 线路添加
	 * @param pubMapRailDataPo
	 * @return
	 */
	public int addPubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
		// TODO 添加服务忌语
		String sql = "insert into base_info_rail_data_cece " +
				"(id, " +
				"rail_id," +
				"rail_name," +
				"order_number," +
				"lng," +
				"lat," +
				"the_geom," +
				"kilometer_mark," +
				"org_id," +
				"remark" +
				")values( " +
				":id," +
				":railId," +
				":railName," +
				":orderNumber," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":kilometerMark," +
				":orgId," +
				":remark)";
		return util.editObject(sql, pubMapRailDataPo);
	}
	/**
	 * 更新线路信息
	 * @param pubMapRailDataPo
	 * @return
	 */
	public int updatePubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
		// TODO 更新线路信息
		String sql = "update base_info_rail_data set " +
				"rail_id=:railId," +
				"rail_name=:railName," +
				"order_number=:orderNumber," +
				"lng=:lng," +
				"lat=:lat," +
				" the_geom=st_geomfromtext(:theGeom)," +
				"kilometer_mark=:kilometerMark," +
				"org_id=:orgId," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, pubMapRailDataPo);
	}
	/**
	 * 删除线路信息
	 * @param idList
	 * @return
	 */
	public int[] deletePubMapRailData(List<String> idList) {
		// TODO 删除线路信息
		String delSql = "delete from base_info_rail_data where id=?";
		return util.batchDelete(delSql, idList);
	}

	public int updatePubMapRailDataOrderNumAsc(BaseInfoRailDataPo pubMapRailDataPo) {
		// TODO Auto-generated method stub
		String sql = "update base_info_rail_data_cece set " +
				"order_number = CAST(order_number AS SIGNED)+1 " +
				"where rail_id=:railId and CAST(order_number AS SIGNED)>= :orderNumber";
		return util.editObject(sql, pubMapRailDataPo);
	}

	public int updatePubMapRailDataOrderNumDesc(String id) {
		// TODO Auto-generated method stub
		String sql = "update base_info_rail_data set " +
				"order_number = order_number-1 " +
				"where rail_id= (select a.rail_id from (select rail_id from base_info_rail_data where id='"+id+"') as a )"
				+ " and  order_number> (select b.order_number from (select order_number from base_info_rail_data where id='"+id+"') as b)";
		return util.editObject(sql,  new Object[] {});
	}

	/**
	 * 查询  km的上下 记录
	 * @param baseInfoRailId
	 * @param kilometerMark
	 * @return
	 */
    public List<Map<String, Object>> findLngLatKm(String baseInfoRailId,
	    String kilometerMark,String stream) {
    	// TODO Auto-generated method stub
		String sql = "select * from "
			+ "(select "
			+ "t.lng as \"lng\", "
			+ "t.lat as \"lat\", "
			+ "cast(t.kilometer_mark  AS signed) as \"kilometerMark\" "
			+ "from base_info_rail_data t "
			+ "where 1=1 AND t.rail_id=:railId  AND t.stream=:stream "
			+ "AND cast(t.kilometer_mark  AS signed)<=:kilometerMark ORDER BY kilometerMark desc limit 0,1) AS t1"
			+ " union all "
			+ "select * from (select "
			+ "t.lng as \"lng\", "
			+ "t.lat as \"lat\", "
			+ "cast(t.kilometer_mark  AS signed) as \"kilometerMark\" "
			+ "from base_info_rail_data t "
			+ "where 1=1 AND t.rail_id=:railId  AND t.stream=:stream "
			+ "AND cast(t.kilometer_mark  AS signed)>:kilometerMark ORDER BY kilometerMark asc limit 0,1) AS t2";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("railId", baseInfoRailId);
		paramMap.put("kilometerMark", kilometerMark);
		paramMap.put("stream", stream);
		return util.getMapList(sql, paramMap);
    }
	
}
