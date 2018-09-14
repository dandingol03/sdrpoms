package com.mobile.busniess.mobileBaseInfoDefencePoliceStation.dao;

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
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoDefencePoliceStation.po.BaseInfoPoliceStationPo;

@Repository("mobileBaseInfoPoliceStationDao")
public class MobileBaseInfoPoliceStationDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 查询警务站信息
	 * @param dgm
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	public Map<String, Object> baseInfoPoliceStationQueryList(DataGridModel dgm,BaseInfoPoliceStationPo baseInfoPoliceStationPo,String orgId) {
		// TODO 查询警务站信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		
		//统计数据详情语句
		 String quSql="";
			    sumSql.append("select count(1) from base_info_defence_police_station t where 1=1 ");
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.adress as \"adress\", " +
				"t.jurisdiction as \"jurisdiction\", " +
				"t.file_id as \"fileId\", " +
				"t.remark as \"remark\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.charger as \"charger\", " +
				"t.telephone as \"telephone\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_defence_police_station t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询警务站信息
		if (null != baseInfoPoliceStationPo.getName()&& !baseInfoPoliceStationPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPoliceStationPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPoliceStationPo.getName()).append("%'");
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
				//上报照片
				Object tempProfile=row.get("fileId");
				List<String> tempProfileList =new ArrayList<String>();
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					//处置照片
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
