package com.mobile.busniess.mobileBaseInfoDefenceBroadcast.dao;

/**
 *  @author lyf
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CaculateDistance;
import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;

@Repository("mobileBaseInfoDefenceBroadcastDao")
public class MobileBaseInfoDefenceBroadcastDao{
	@Autowired
	private DBUtil util;

	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm, BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastpo,String orgId) {
		
		/**
		 * TODO 
		 * 查询广播警示柱信息
		 */
		/**
		 * 结果集 分为2个 第一个为总数 total  第二为详细的rows 
		 */
		Map<String, Object> result = new HashMap<String, Object>(2);
		/**
		 * 统计数据总数语句
		 */
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_defence_broadcast t where 1=1 ");
		/**
		 * 统计数据详情语句
		 */
		String quSql = "select " +
				   "t.id as \"id\", " +
				   "t.number as \"number\", " +
				   "t.name as \"name\", " +
				   "t.adress as \"adress\", " +
				   "t.status as \"status\", " +
				   "t.voicebroadcast as \"voicebroadcast\", " +
				   "t.broadcasting as \"broadcasting\", " +
				   "t.monitor_id as \"monitorId\", " +
				   "t.charger as \"charger\", " +
				   "t.telephone as \"telephone\", " +
				   "t.org_id as \"orgId\", " +
				   "t.lng as \"lng\", " +
				   "t.lat as \"lat\", " +
				   "o.ORG_NAME as \"descOrgName\", " +
				   "t.bro_type as \"broType\", " +
				   "t.remark as \"remark\" ," +
				   "t.photos as \"photos\" ," +
				   "b.ORG_NAME as \"orgName\" " +
				   "from base_info_defence_broadcast t " +
				   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				   "where 1=1 ";
		
		/**
		 * 组装查询条件
		 */
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		String rangeDanger=baseInfoDefenceBroadcastpo.toString();
		StringBuffer appendSql=new StringBuffer();
		if(baseInfoDefenceBroadcastpo.getX1()!=0.0&&baseInfoDefenceBroadcastpo.getY1()!=0.0
				&&baseInfoDefenceBroadcastpo.getX2()!=0.0&&baseInfoDefenceBroadcastpo.getY2()!=0.0){
			appendSql.append(" and ");
			appendSql.append(" mbrcontains ( ");
			appendSql.append(" ST_GeomFromText( '"+rangeDanger+"' ) , ");
			appendSql.append(" `t`.`the_geom` ");
			appendSql.append(" ) = 1 ");
		}
		/**
		 * 按照名称查询广播警示柱信息
		 */
		if (null != baseInfoDefenceBroadcastpo.getName()&& !baseInfoDefenceBroadcastpo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefenceBroadcastpo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefenceBroadcastpo.getName()).append("%'");
		}
		
		if (null != baseInfoDefenceBroadcastpo.getAdress() && !baseInfoDefenceBroadcastpo.getAdress().equals("")) {
			sqlSb.append(" and t.adress like :adress");
			params.put("adress", "%" + baseInfoDefenceBroadcastpo.getAdress()+ "%");
			sumSql.append(" and t.adress like '%").append(baseInfoDefenceBroadcastpo.getAdress()).append("%'");
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
		String sql = quSql + sqlSb.toString()+andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		Map<String,String> map2=new HashMap<String,String>();
		List<Map<String, String>> list1=new ArrayList<Map<String,String>>();
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		Object lng=baseInfoDefenceBroadcastpo.getLng();
		Object lat=baseInfoDefenceBroadcastpo.getLat();
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object status=row.get("status");
				if(status!=null&&!"".equals(status.toString().trim())){
					row.put("statusName", PubData.getDictName("BROADCAST_STATUS", status.toString()));		
				}
				Object broType=row.get("broType");
				if(broType!=null&&!"".equals(broType.toString().trim())){
					row.put("broTypeName", PubData.getDictName("BROADCASTTYPE", broType.toString()));		
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
				map2=new HashMap<String,String>();
				Object lng1=row.get("lng");
				Object lat1=row.get("lat");
				if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())&&
						lng!=null&&!"".equals(lng.toString().trim())&&lat!=null&&!"".equals(lat.toString().trim())){
					//计算出距离
					double len=CaculateDistance.caculateDistance(
							ConverterUtils.toDouble(lng),
							ConverterUtils.toDouble(lat),
							ConverterUtils.toDouble(lng1),ConverterUtils.toDouble(lat1)
							);
					BigDecimal   b   =   new   BigDecimal(len);
					for (String string : row.keySet()) {
						if(row.get(string)!=null&&!"".equals(row.get(string).toString().trim())){
							map2.put(string, row.get(string).toString());
						}
					}
					map2.put("len", ConverterUtils.toString( b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
					list.add(map2);
				}
			
			}
		}
		if(lng!=null&&!"".equals(lng.toString().trim())&&lat!=null&&!"".equals(lat.toString().trim())){
			ConverterUtils.mapSorts(list);
			list1.addAll(list);	
			result.put("rows",list1);
			
		}else{
			result.put("rows",rowsList);
		}
		/**
		 * 绑定查询结果('total'和'rows'名称不能修改)
		 */
		result.put("total", util.getObjCount(sumSql.toString()));
		return result;
	}
}
