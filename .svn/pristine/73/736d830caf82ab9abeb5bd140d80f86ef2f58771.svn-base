package com.pc.busniess.baseInfoRail.dao;


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
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
/**
 * 铁路
 * @author jiawenlong
 * Dao
 */
@Repository("baseInfoRailDao")
public class BaseInfoRailDao{
	
	@Autowired
	private DBUtil util;
	
	//区域铁路
	//POINT(116.429280995453 39.9007618383674)
	//0.02697798402001680992373166574387
	public List<Map<String, Object>> findRegionalRail(String point,String r) {
		String sql=
		"SELECT b.id as railId ,s.id as railStreamId FROM `base_info_rail` `b` "
		+ "LEFT JOIN base_info_rail_stream s on s.rail_id=b.id "
		+ "WHERE "+
			"(st_intersects ( "+
					"s.line_geom, "+
					"ST_Buffer ( "+
						"ST_GeomFromText ( "+
							"? "+
						"), "+
						"?, "+
						"ST_Buffer_Strategy ('point_circle', 32) "+
					") "+
			") = 1) ";
		return util.getMapList(sql, new Object[]{point,r});
	}	
	/**
	 * 获取铁路的全部信息
	 */
	public List<Map<String, Object>> findAllRails(String orgId) {
		String sql = "select DISTINCT(t.rail_stream_id )as id,t.rail_name as name from base_info_rail_view t WHERE 1=1 and org_id like '"+orgId+"%' " ;
		List<Map<String, Object>>  list=util.getMapList(sql, new Object[]{});
		return list;
	}
	/**
	 * 根据id获取铁路的详细信息（根据铁路id数量统计）
	 * @param id 铁路ID
	 * @return 
	 * @author jwl
	 * @param orgId 
	 */
	public Map<String, Object> findByMaxminKm(String id, String orgId) {
		 String sql ="";
			sql ="select " +
					"t.kilometer_mark as \"kilometerMark\", " +
					"s.positive_negative as \"negative\", " +
					"t.rail_name as \"name\" " +
					"from base_info_rail_data t,base_info_rail_stream s " +
					"WHERE s.id=t.rail_id AND t.rail_id=:id and t.org_id like '"+orgId+"%' order by  CAST(t.order_number AS SIGNED) asc";
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			//1、 根据id获取到对应铁路信息
			List<Map<String, Object>> rowsList=util.getMapList(sql, paramMap);
			Map<String,Object> row=new HashMap<String,Object>();
			System.err.println(rowsList);
			if(rowsList!=null&&rowsList.size()>0){
				Map<String,Object> startRow=rowsList.get(0);
				if(StringUtils.equals("1", startRow.get("negative").toString())){
					//起点路程计算
					Object start=startRow.get("kilometerMark");
					if(null!=start&&!"".equals(start.toString().trim())){
						row.put("startStr", CommonUtil.KM2MStrbyDec(start.toString()));
						String [] startArr=CommonUtil.M2KMStrbyDec(start.toString());
						row.put("startKM", startArr[0]);
						row.put("startM", startArr[1]);
					}
					Map<String,Object> endRow=rowsList.get(rowsList.size()-1);
					//终点路程计算
					Object end=endRow.get("kilometerMark");
					if(null!=end&&!"".equals(end.toString().trim())){
						row.put("endStr", CommonUtil.KM2MStrbyDec(end.toString()));
						String [] endArr=CommonUtil.M2KMStrbyDec(end.toString());
						row.put("endKM", endArr[0]);
						row.put("endM", endArr[1]);
					}
					row.put("name", startRow.get("name"));	
				}else{
					//起点路程计算
					Map<String,Object> endRow=rowsList.get(rowsList.size()-1);
					Object start=endRow.get("kilometerMark");
					if(null!=start&&!"".equals(start.toString().trim())){
						row.put("startStr", CommonUtil.KM2MStrbyDec(start.toString()));
						String [] startArr=CommonUtil.M2KMStrbyDec(start.toString());
						row.put("startKM", startArr[0]);
						row.put("startM", startArr[1]);
					}
					//终点路程计算
					Object end=startRow.get("kilometerMark");
					if(null!=end&&!"".equals(end.toString().trim())){
						row.put("endStr", CommonUtil.KM2MStrbyDec(end.toString()));
						String [] endArr=CommonUtil.M2KMStrbyDec(end.toString());
						row.put("endKM", endArr[0]);
						row.put("endM", endArr[1]);
					}
					row.put("name", startRow.get("name"));	
				}
			}
			return row;
	}
	/**
	 * 根据id获取铁路的详细信息（根据铁路id数量统计）
	 * @param id 铁路ID
	 * @return 
	 * @author jwl
	 */
	public Map<String, Object> findById(String id) {
		 String sql ="";
			sql ="select " +
					"s.rail_name as \"name\", " +
					"t.classify as \"classify\", " +
					"s.start_address as \"startAddress\", " +
					"s.end_address as \"endAddress\", " +
					"s.start as \"start\", " +
					"s.end as \"end\", " +
					"s.stream as \"stream\" " +
					"from base_info_rail t " +
					"LEFT OUTER JOIN base_info_rail_stream as s ON t.id = s.rail_id "+
					"WHERE 1=1 AND s.id=:id ";
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			//1、 根据id获取到对应铁路信息
			Map<String, Object> row=util.getMapObject(sql, paramMap);
			Object classify=row.get("classify");
			if(classify!=null&&!"".equals(classify.toString().trim())){
				row.put("classifyName", PubData.getDictName("RAIL_CLASSIFY", classify.toString()));		
			}
			Object stream1=row.get("stream");
			if(stream1!=null&&!"".equals(stream1.toString().trim())){
				row.put("streamName", PubData.getDictName("RAIL_STREAM", stream1.toString()));		
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
			return row;
	}
	public List<Map<String, Object>> find(String id) {
		 String sql ="";
		 if(StringUtils.equals(id.substring(0,4),"stre")){
			sql ="select " +
//					"s.rail_name as \"name\", " +
					"t.name as \"name\", " +
					"s.rail_name as \"railName\", " +
					"t.classify as \"classify\", " +
					"s.start_address as \"startAddress\", " +
					"s.end_address as \"endAddress\", " +
					"s.stream as \"stream\" " +
					"from base_info_rail t " +
					"LEFT OUTER JOIN base_info_rail_stream as s ON t.id = s.rail_id "+
					"WHERE 1=1 AND s.id=:id ";
		 }else{
			 sql ="select " +
						"t.name as \"name\", " +
						"s.rail_name as \"railName\", " +
						"t.classify as \"classify\", " +
						"s.start_address as \"startAddress\", " +
						"s.end_address as \"endAddress\", " +
						"s.stream as \"stream\" " +
						"from base_info_rail t " +
						"LEFT OUTER JOIN base_info_rail_stream as s ON t.id = s.rail_id "+
						"WHERE 1=1 AND t.id=:id ";
		 }
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			//1、 根据id获取到对应铁路信息
			List<Map<String, Object>> rowsList=util.getMapList(sql, paramMap);
			if(rowsList!=null&&rowsList.size()>0){
				for (int i = 0; i < rowsList.size(); i++) {
					Map<String,Object> row=rowsList.get(i);
					Object classify=row.get("classify");
					if(classify!=null&&!"".equals(classify.toString().trim())){
						row.put("classifyName", PubData.getDictName("RAIL_CLASSIFY", classify.toString()));		
					}
					Object stream1=row.get("stream");
					if(stream1!=null&&!"".equals(stream1.toString().trim())){
						row.put("streamName", PubData.getDictName("RAIL_STREAM", stream1.toString()));		
					}
				}
			}
			return rowsList;
	}
	
	/**
	 * 1.组装分页查询（ 名字,分类的模糊查询）  
	 * 通过视图查询铁路关联的车站,桥梁,隧道,道口,涵洞,公跨铁数量.
	 * @param dgm
	 * @param baseInfoRailPo
	 * @return 查询结果集
	 */
	public Map<String, Object> baseInfoRailQueryList(DataGridModel dgm, BaseInfoRailPo baseInfoRailPo) {
		// TODO 查询铁路信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_rail t "
				+ "where 1=1 ");

		//统计数据详情语句
		String quSql =
						"select " +
						"t.id as \"id\", " +
						"t.name as \"name\", " +
						"t.classify as \"classify\", " +
						"t.state as \"state\", " +
						"t.remark as \"remark\", " +
						"t.rail_level as \"railLevel\" " +
						"from base_info_rail t " +
						"WHERE 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		//按照名称查询铁路信息
		if (null != baseInfoRailPo.getName()&& !baseInfoRailPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoRailPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoRailPo.getName()).append("%'");
		}
		
		//分类
		if (null != baseInfoRailPo.getClassify() && !baseInfoRailPo.getClassify().equals("")) {
			sqlSb.append(" and t.classify like :classify");
			params.put("classify", "%" + baseInfoRailPo.getClassify()+ "%");
			sumSql.append(" and t.classify like '%").append(baseInfoRailPo.getClassify()).append("%'");
		}
		
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//查询铁路关联数量
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//判断铁路关联数量
				//字典铁路分类
				Object classify=row.get("classify");
				if(classify!=null&&!"".equals(classify.toString().trim())){
					row.put("classifyName", PubData.getDictName("RAIL_CLASSIFY", classify.toString()));		
				}
				//字典铁路状态
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));		
				}
				//字典铁路线别
				Object railLevel=row.get("railLevel");
				if(railLevel!=null&&!"".equals(railLevel.toString().trim())){
					row.put("railLevelName", PubData.getDictName("RAIL_LEVEL", railLevel.toString()));		
				}
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
	 * 添加铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	public int addBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		// TODO 添加铁路信息
		//起点换算
		String end=CommonUtil.KM2MStrbyDec(baseInfoRailPo.getEndKM(),baseInfoRailPo.getEndM());
		//终点换算
		String start=CommonUtil.KM2MStrbyDec(baseInfoRailPo.getStartKM(),baseInfoRailPo.getStartM());
		baseInfoRailPo.setStart(start);
		baseInfoRailPo.setEnd(end);

		String sql = "insert into base_info_rail " +
				"(id, " +
				"name," +
				"number," +
				"rail_level," +
				"state," +
				"start_id," +
				"end_id," +
				"classify," +
				"start," +
				"end," +
				"length," +
				"line_geom," +
				"range_geom," +
				"remark" +
				")values( " +
				":id," +
				":name," +
				":number," +
				":railLevel," +
				":state," +
				":startId," +
				":endId," +
				":classify," +
				":start," +
				":end," +
				":length," +
				"st_geomfromtext('POINT(0 0)'),"+
				"st_geomfromtext('POINT(0 0)'),"+
				":remark)";
		return util.editObject(sql, baseInfoRailPo);
	}
	/**
	 * 更新铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	public int updateBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		// TODO 更新铁路信息
		//起点换算
		String end=CommonUtil.KM2MStrbyDec(baseInfoRailPo.getEndKM(),baseInfoRailPo.getEndM());
		//终点换算
		String start=CommonUtil.KM2MStrbyDec(baseInfoRailPo.getStartKM(),baseInfoRailPo.getStartM());
		baseInfoRailPo.setStart(start);
		baseInfoRailPo.setEnd(end);
		String sql = "update base_info_rail set " +
				"name=:name," +
				"number=:number," +
				"rail_level=:railLevel," +
				"state=:state," +
				"start_id=:startId," +
				"end_id=:endId," +
				"classify=:classify," +
				"start=:start," +
				"end=:end," +
				"length=:length," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoRailPo);
	}
	/**
	 * 删除铁路信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoRail(List<String> idList) {
		// TODO 删除铁路信息
		String delSql = "delete from base_info_rail where id=?";
		return util.batchDelete(delSql, idList);
	}
}
