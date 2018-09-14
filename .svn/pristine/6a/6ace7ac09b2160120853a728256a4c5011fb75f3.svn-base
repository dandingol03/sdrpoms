package com.mobile.busniess.mobileBaseInfoPartStation.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;

@Repository("mobileBaseInfoPartStationDao")
public class MobileBaseInfoPartStationDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 该方法是对车站查询的操作
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return result
	 */
	public Map<String, Object> baseInfoPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo,String orgId) {
		// TODO 查询车站信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_part_station t where 1=1 ");
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.number as \"number\", " +
				"t.level as \"level\", " +
				"t.nature as \"nature\", " +
				"t.is_highspeed as \"isHighspeed\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.state as \"state\", " +
				"t.telephone as \"telephone\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_station t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		//按照名称查询车站信息
		if (null != baseInfoPartStationPo.getName()&& !baseInfoPartStationPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartStationPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartStationPo.getName()).append("%'");
		}
		//按照等级查询车站信息
		if (null != baseInfoPartStationPo.getLevel()&& !baseInfoPartStationPo.getLevel().equals("")) {
			sqlSb.append(" and t.level like :level");
			params.put("level", "%" + baseInfoPartStationPo.getLevel()+ "%");
			sumSql.append(" and t.level like '%").append(baseInfoPartStationPo.getLevel()).append("%'");
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

		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//如果level不为null或者不为空，那么就放入list集合中
				Object level=row.get("level");
				if(level!=null&&!"".equals(level.toString().trim())){
					row.put("levelName", PubData.getDictName("PART_STATION_LEVEL", level.toString()));		
				}
				//如果nature不为null或者不为空，那么就放入list集合中
				Object nature=row.get("nature");
				if(nature!=null&&!"".equals(nature.toString().trim())){
					row.put("natureName", PubData.getDictName("PART_STATION_NATURE", nature.toString()));		
				}
				//如果railBureau不为null或者不为空，那么就放入list集合中
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
				}
				//如果state不为null或者不为空，那么就放入list集合中
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));		
				}
				//如果middle不为null或者不为空，那么就换算成千米、米的格式,再放入List集合中
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				//照片
				Object tempProfile=row.get("fileId");
				List<String> tempProfileList =new ArrayList<String>();
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					row.put("photoUrls",tempProfileList);
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString()));
				result.put("rows", rowsList);
				return result;
	}
}
