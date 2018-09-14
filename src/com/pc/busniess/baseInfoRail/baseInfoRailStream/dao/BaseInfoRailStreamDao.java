package com.pc.busniess.baseInfoRail.baseInfoRailStream.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
/**
 * 线路坐标
 * @author xb
 */
@Repository("baseInfoRailStreamDao")
public class BaseInfoRailStreamDao {
	@Autowired
	private DBUtil util;

	/**
	 * @param railId
	 * @return
	 */
	public List<Map<String, Object>> findById(String railId) {
		// TODO Auto-generated method stub
		String sql ="";
		if(StringUtils.equals(railId.substring(0,4),"rail")){
			sql= "select " +
					"t.id as \"id\", " +
					"t.rail_name as \"railName\" " +
					"from base_info_rail_stream t " +
					"where 1=1 and t.rail_id =? ";
			return util.getMapList(sql,new Object[]{railId});
		}else{
			sql= "select " +
					"t.id as \"id\", " +
					"t.rail_name as \"railName\" " +
					"from base_info_rail_stream t " +
					"where 1=1 and t.id =? ";
			return util.getMapList(sql,new Object[]{railId});
		}
	}
	/**
	 * 查询线路信息
	 * @param dgm
	 * @param pubMapRailDataPo
	 * @return
	 */
	public Map<String, Object> baseInfoRailStreamQueryList(DataGridModel dgm, BaseInfoRailDataPo pubMapRailDataPo) {
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_rail_stream t where 1=1  " );
		  //统计数据详情语句
			quSql = "select " +
				"t.id as \"id\", " +
				"t.rail_name as \"railName\", " +
				"t.start_address as \"startAddress\", " +
				"t.end_address as \"endAddress\", " +
				"t.stream as \"stream\", " +
				"t.start as \"start\", " +
				"t.end as \"end\", " +
				"t.length as \"length\" " +
				"from base_info_rail_stream t " +
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
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);

		return result;
	}


}
