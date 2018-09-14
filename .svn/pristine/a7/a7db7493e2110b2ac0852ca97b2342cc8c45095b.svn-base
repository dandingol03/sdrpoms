package com.mobile.busniess.mobileBaseInfoPeripheralPlace.dao;

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
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.dao 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:45:35
 */
@Repository("mobileBaseInfoPeripheralPlaceDao")
public class MobileBaseInfoPeripheralPlaceDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 查询周边场所所有信息
	 * @param dgm
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	public Map<String, Object> baseInfoPeripheralPlaceQueryList(DataGridModel dgm,
			BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo, String orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_peripheral_place t where 1=1 ");
		 quSql = 
				"select t.id \"id\", "
				+ "t.name \"name\", "
				+ "t.category \"category\", "
				+ "t.address \"address\", "
				+ "t.charger \"charger\", "
				+ "t.telephone \"telephone\", "
				+ "t.photos \"photos\", "
				+ "t.org_id \"orgId\", "
				+ "t.type \"type\", "
				+ "a.ORG_NAME as \"orgName\", "
				+ "t.lng \"lng\", "
				+ "t.lat \"lat\", "
				+"o.ORG_NAME as \"descOrgName\", " 
				+ "t.place_description \"description\", "
				+ "t.remark \"remark\" "
				+ "from base_info_peripheral_place t "
				+"LEFT OUTER JOIN pub_org AS a ON t.org_id = a.ORG_ID " 
				+"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " 
				+"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " 
				+ "where 1=1 ";
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != baseInfoPeripheralPlacePo.getName()
				&& !baseInfoPeripheralPlacePo.equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPeripheralPlacePo.getName() + "%");
			sumSql.append("and t.name like '%").append(baseInfoPeripheralPlacePo.getName()).append("%'");
		}
		if (null != baseInfoPeripheralPlacePo.getCategory()&& !baseInfoPeripheralPlacePo.equals("")) {
			sqlSb.append(" and t.category like :category");
			params.put("category", "%" + baseInfoPeripheralPlacePo.getCategory() + "%");
			sumSql.append("and t.category like '%").append(baseInfoPeripheralPlacePo.getCategory()).append("%'");
		}
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql(orgId));
			 andquSql=JurisdictionAppendSql.appendSql();
			 params.put("orgId", orgId);
		}
		String order = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			order = SqlUtil.getOrderbySql(dgm);
		}

		String sql = quSql + sqlSb.toString() +andquSql+ order;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//周边场所类别
				Object category=row.get("category");
				if(category!=null&&!"".equals(category.toString().trim())){
					row.put("peripheralName", PubData.getDictName("PERIPHERAL_PLACE", category.toString()));		
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
		map.put("total", util.getObjCount(sumSql.toString()));
		map.put("rows", rowsList);
		return map;

	}
}
