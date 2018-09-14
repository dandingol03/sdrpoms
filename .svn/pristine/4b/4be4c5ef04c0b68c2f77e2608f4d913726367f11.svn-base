package com.mobile.busniess.mobileBaseInfoPartTrajection.dao;


import java.util.ArrayList;
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
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;

@Repository("mobileBaseInfoPartTrajectionDao")
public class MobileBaseInfoPartTrajectionDao{
	
	@Autowired
	private DBUtil util;

	/**
	 * 查询行人易穿行部位信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo,String orgId) {
		// TODO 查询行人易穿行部位信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_part_trajection t where 1=1 ");
		String  quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.status as \"status\", " +
				"t.address as \"address\", " +
				"t.telephone as \"telephone\", " +
				"t.photos as \"photos\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.region as \"region\", " +
				"t.conditions as \"conditions\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_trajection t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";;

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询行人易穿行信息
		if (null != baseInfoHiddenTrajectionPo.getName()&& !baseInfoHiddenTrajectionPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoHiddenTrajectionPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoHiddenTrajectionPo.getName()).append("%'");
		}
		if (null != baseInfoHiddenTrajectionPo.getStatus()&& !baseInfoHiddenTrajectionPo.getStatus().equals("")) {
			sqlSb.append(" and t.status like :status");
			params.put("status", "%" + baseInfoHiddenTrajectionPo.getStatus()+ "%");
			sumSql.append(" and t.status like '%").append(baseInfoHiddenTrajectionPo.getStatus()).append("%'");
		}
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql());
			 andquSql=JurisdictionAppendSql.appendSql();
			 params.put("orgId", orgId);
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		//组装分页定义
		String sql = quSql + sqlSb.toString() +andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//易穿行状态
				Object status=row.get("status");
				if(status!=null&&!"".equals(status.toString().trim())){
					row.put("statusName", PubData.getDictName("HIDDEN_TRAJECTION_STATUS", status.toString()));		
				}
				//易穿行状态
				Object condition=row.get("conditions");
				if(condition!=null&&!"".equals(condition.toString().trim())){
					row.put("conditionName", PubData.getDictName("GUARDRAIL_CONDITION", condition.toString()));		
				}
				//照片
				Object tempProfile=row.get("photos");
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
