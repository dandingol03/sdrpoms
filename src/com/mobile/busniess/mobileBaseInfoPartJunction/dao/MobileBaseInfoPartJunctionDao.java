package com.mobile.busniess.mobileBaseInfoPartJunction.dao;

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
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;

@Repository("mobileBaseInfoPartJunctionDao")
public class MobileBaseInfoPartJunctionDao {
	@Autowired
	private DBUtil util;
	
	
	/**
	 * 查询所有的铁路信息（参考铁路线时需要）
	 * @return 含有参考铁路线信息的List集合
	 */
	public List<Map<String, Object>> queryRails() {
		//TODO 查询所有的铁路信息（参考铁路线时需要）
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\" " +
				"from base_info_rail t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	
	/**
	 * 查询道口基本信息
	 * @param dgm @see DataGridModel
	 * @param baseInfoPartJunctionPo @see BaseInfoPartJunctionPo
	 * @return 组合条件查询出带有分页的道口基本信息的Map集合
	 */
	public Map<String, Object> baseInfoPartJunctionQueryList(DataGridModel dgm, BaseInfoPartJunctionPo baseInfoPartJunctionPo,String orgId) {
		//TODO 查询道口基本信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_junction t where 1=1 ");
		//统计数据详情语句
	    quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.is_height_limit as \"isHeightLimit\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.road_classify as \"roadClassify\", " +
				"t.train_num as \"trainNum\", " +
				"t.state as \"state\", " +
				"t.number as \"number\", " +
				"t.width as \"width\", " +
				"t.material as \"material\", " +
				"t.level as \"level\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_junction t " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询道口信息
		if (null != baseInfoPartJunctionPo.getName()&& !baseInfoPartJunctionPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartJunctionPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartJunctionPo.getName()).append("%'");
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
				//行别
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));
				}
				//守护情况
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));
				}	
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				//隶属铁路局(公司)
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));
				}
				//运营状态
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));
				}
				//道路类别
				Object roadClassify=row.get("roadClassify");
				if(roadClassify!=null&&!"".equals(roadClassify.toString().trim())){
					row.put("roadClassifyName", PubData.getDictName("PART_JUNCTION_ROAD_CLASSIFY", roadClassify.toString()));
				}
				//铺面材料
				Object material=row.get("material");
				if(material!=null&&!"".equals(material.toString().trim())){
					row.put("materialName", PubData.getDictName("PART_JUNCTION_MATERIAL", material.toString()));
				}
				//道口等级
				Object level=row.get("level");
				if(level!=null&&!"".equals(level.toString().trim())){
					row.put("levelName", PubData.getDictName("PART_JUNCTION_LEVEL", level.toString()));
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
