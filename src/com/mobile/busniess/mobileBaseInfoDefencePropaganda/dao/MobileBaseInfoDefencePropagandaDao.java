package com.mobile.busniess.mobileBaseInfoDefencePropaganda.dao;

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
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;
@Repository("mobileBaseInfoDefencePropagandaDao")
public class MobileBaseInfoDefencePropagandaDao{
	@Autowired
	private DBUtil util;
	
	public Map<String, Object> baseInfoDefencePropagandaQueryList(DataGridModel dgm, BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo,String orgId) {
		/**
		 * TODO
		 * 查询护路宣传点位信息
		 * 结果集 分为2个 第一个为总数 total  第二为详细的rows 
		 */
		Map<String, Object> result = new HashMap<String, Object>(2);
		/**
		 * 统计数据总数语句
		 */
		StringBuffer sumSql = new StringBuffer();
		
		/**
		 * 统计数据详情语句
		 */
		    String quSql="";
			    sumSql.append("select count(1) from base_info_defence_propaganda t where 1=1 " );
		       quSql = "select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.adress as \"adress\", " +
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.photos as \"photos\", " +
					   "t.org_id as \"orgId\", " +
					   "t.remark as \"remark\", " +
					   "t.construction_unit as \"constructionUnit\", " +
					   "t.content as \"content\", " +
					   "t.pro_type as \"proType\", " +
					   "t.pro_time as \"proTime\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "b.ORG_NAME as \"orgName\" " +
					   "from base_info_defence_propaganda t " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 ";

		/**
		 * 组装查询条件
		 */
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		/**
		 * 按照名称护路宣传点位信息
		 */
		if (null != baseInfoDefencePropagandaPo.getName()&& !baseInfoDefencePropagandaPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefencePropagandaPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefencePropagandaPo.getName()).append("%'");
		}
		if (null != baseInfoDefencePropagandaPo.getAdress()&& !baseInfoDefencePropagandaPo.getAdress().equals("")) {
			sqlSb.append(" and t.adress like :adress");
			params.put("adress", "%" + baseInfoDefencePropagandaPo.getAdress()+ "%");
			sumSql.append(" and t.adress like '%").append(baseInfoDefencePropagandaPo.getAdress()).append("%'");
		}
		
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql(orgId));
			 andquSql=JurisdictionAppendSql.appendSql();
			 params.put("orgId", orgId);
		}
		/**
		 * 组装排序规则
		 */
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		/**
		 * 组装分页定义
		 */
		String sql = quSql + sqlSb.toString() +andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object proType=row.get("proType");
				if(proType!=null&&!"".equals(proType.toString().trim())){
					row.put("proTypeName", PubData.getDictName("DEFENCE_PRO_TYPE", proType.toString()));		
				}
				//上报照片
				Object tempProfile=row.get("photos");
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
		/**
		 * 绑定查询结果('total'和'rows'名称不能修改)
		 */
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",rowsList);

		return result;
	}
	
	
}