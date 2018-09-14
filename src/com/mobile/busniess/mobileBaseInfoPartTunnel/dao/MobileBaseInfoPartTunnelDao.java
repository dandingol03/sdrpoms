package com.mobile.busniess.mobileBaseInfoPartTunnel.dao;

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
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;

@Repository("mobileBaseInfoPartTunnelDao")
public class MobileBaseInfoPartTunnelDao {
	@Autowired
	private DBUtil util;

	/**
	 * 查询隧道基本信息
	 * @param dgm @see DataGridModel
	 * @param baseInfoPartTunnelPo @see BaseInfoPartTunnelPo
	 * @return 组合条件查询出带有分页的道口基本信息的Map集合
	 */
	public Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo,String orgId) {
		//TODO 查询隧道基本信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_tunnel t where 1=1 ");
		//统计数据详情语句
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"t.photos as \"photos\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_tunnel t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询隧道信息
		if (null != baseInfoPartTunnelPo.getName()&& !baseInfoPartTunnelPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartTunnelPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartTunnelPo.getName()).append("%'");
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
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() +andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				
				//行别
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					//将指定值放到对应键中
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));//行别
				}
				//守护情况
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));//守护情况
				}	
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				//隧道入口照片
				Object tempProfile=row.get("fileId");
				List<String> tempProfileList =new ArrayList<String>();
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					row.put("photoUrls",tempProfileList);
				}
				//隧道出口照片
				Object tempProfile1=row.get("photos");
				List<String> tempProfileList1 =new ArrayList<String>();
				if(tempProfile1!=null&&!"".equals(tempProfile1.toString().trim())){
					String []tempFileArray = tempProfile1.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList1.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					row.put("etPhotoUrls",tempProfileList1);
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);

		return result;
	}
	
}
