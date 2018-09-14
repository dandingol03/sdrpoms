package com.mobile.busniess.mobileBaseInfoPartBridge.dao;


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
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;

@Repository("mobileBaseInfoPartBridgeDao")
public class MobileBaseInfoPartBridgeDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 该方法是对桥梁查询的操作
	 * @param dgm
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo DataGridModel
	 * @return result
	 */
	public Map<String, Object> baseInfoPartBridgeQueryList(DataGridModel dgm, BaseInfoPartBridgePo baseInfoPartBridgePo,String orgId) {
		// TODO 查询桥梁信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_bridge t where 1=1 ");
		//统计数据详情语句
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.classify as \"classify\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.is_shared as \"isShared\", " +  
				"t.purpose as \"purpose\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"t.cross_span as \"crossSpan\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_bridge t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询桥梁信息
		if (null != baseInfoPartBridgePo.getName()&& !baseInfoPartBridgePo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartBridgePo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartBridgePo.getName()).append("%'");
		}
		
		//分类
		if (null != baseInfoPartBridgePo.getClassify() && !baseInfoPartBridgePo.getClassify().equals("")) {
			sqlSb.append(" and t.classify like :classify");
			params.put("classify", "%" + baseInfoPartBridgePo.getClassify()+ "%");
			sumSql.append(" and t.classify like '%").append(baseInfoPartBridgePo.getClassify()).append("%'");
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
		
		//循环遍历数据字典中的选项
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//如果classify不为null或者不为空,就放入List集合中
				Object classify=row.get("classify");
				if(classify!=null&&!"".equals(classify.toString().trim())){
					row.put("classifyName", PubData.getDictName("PART_BRIDGE_CLASSIFY", classify.toString()));		
				}
				//如果stream不为null或者不为空,就放入List集合中
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));		
				}
				//如果guardStatus不为null或者不为空,就放入List集合中
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("railGuardStatus", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));		
				}
				//如果railBureau不为null或者不为空,就放入List集合中
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
				}
				//如果crossSpan不为null或者不为空,就放入List集合中
				Object crossSpan=row.get("crossSpan");
				if(crossSpan!=null&&!"".equals(crossSpan.toString().trim())){
					row.put("crossSpanName", PubData.getDictName("CROSSSPAN", crossSpan.toString()));		
				}
				//如果middle不为null或者不为空,就转换成千米、米的格式,再放入List集合中
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
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
