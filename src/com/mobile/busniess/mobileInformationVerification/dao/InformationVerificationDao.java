/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.dao 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:41:09 
 */
package com.mobile.busniess.mobileInformationVerification.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.busniess.mobileInformationVerification.po.InformationVerification;
import com.pc.bsp.common.util.CaculateDistance;
import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.SqlUtil;

/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.dao 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:41:09 
 */
@Repository("informationVerificationDao")
public class InformationVerificationDao {

	@Autowired
	private DBUtil util;

	//根据GPS获取监控,按距离排序 video_information
	public Map<String, Object> getInformationVerificationInfo(
			DataGridModel dgm, InformationVerification informationVerification, String orgId) {
		String rangeDanger=informationVerification.toString();
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from video_information t where 1=1 ");
		 quSql = 
				"select t.id \"id\", "
				+ "t.address \"address\", "
				+ "t.lng \"lng\", "
				+ "t.lat \"lat\", "
				+ "t.org_id \"orgId\", "
				+ "t.is_yaw \"isYaw\", "
				+ "t.is_integration \"isIntegration\", "
				+ "t.unit \"unit\", "
				+ "t.lng \"lng\", "
				+ "t.lat \"lat\", "
				+ "t.is_existence \"isExistence\", "
				+ "t.attribute \"attribute\", "
				+ "t.access_condition \"accessCondition\", "
				+ "a.ORG_NAME as \"orgName\", "
				+ "o.ORG_NAME as \"descOrgName\" " 
				+ "from video_information t "
				+"LEFT OUTER JOIN pub_org AS a ON t.org_id = a.ORG_ID " 
				+"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " 
				+"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " 
				+ "where 1=1 ";
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer appendSql=new StringBuffer();
		if(informationVerification.getX1()!=0.0&&informationVerification.getY1()!=0.0
				&&informationVerification.getX2()!=0.0&&informationVerification.getY2()!=0.0){
			appendSql.append(" and ");
			appendSql.append(" mbrcontains ( ");
			appendSql.append(" ST_GeomFromText( '"+rangeDanger+"' ) , ");
			appendSql.append(" `t`.`the_geom` ");
			appendSql.append(" ) = 1 ");
		}
		String order = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			order = SqlUtil.getOrderbySql(dgm);
		}
		String sql = quSql +appendSql.toString()+ sqlSb.toString() + order;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());
		Map<String,String> map2=new HashMap<String,String>();
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		List<Map<String, String>> list1=new ArrayList<Map<String,String>>();
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		Object lng=informationVerification.getLng();
		Object lat=informationVerification.getLat();
		if(rowsList!=null&&rowsList.size()>0){
			for(int k=0;k<rowsList.size();k++){
				map2=new HashMap<String,String>();
				Map<String,Object> row=rowsList.get(k);
				Object lng1=row.get("lng");
				Object lat1=row.get("lat");
				if(lng1!=null&&!"".equals(lng1.toString().trim())&&lat1!=null&&!"".equals(lat1.toString().trim())&&
						lng!=null&&!"".equals(lng.toString().trim())&&lat!=null&&!"".equals(lat.toString().trim())){
					//计算出距离
					double len=CaculateDistance.caculateDistance(
							ConverterUtils.toDouble(informationVerification.getLng()),
							ConverterUtils.toDouble(informationVerification.getLat()),
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
			map.put("rows",list1);
		}else{
			map.put("rows",rowsList);
		}
		map.put("total", util.getObjCount(sumSql.toString()+appendSql.toString()));
		return map;
	}

	//修改监控经纬度以及相关信息
	public int updateInformationVerificationInfo(
			InformationVerification informationVerification) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append("update video_information set ");
		if(StringUtils.isNotBlank(informationVerification.getAddress())){
			sb.append("address=:address,");
		}else if(StringUtils.isNotBlank(informationVerification.getLng())){
			sb.append("lng=:lng,");
		}else if(StringUtils.isNotBlank(informationVerification.getLat())){
			sb.append("lat=:lat,");
		}else if(StringUtils.isNotBlank(informationVerification.getTheGeom())){
			sb.append("the_geom=:st_geomfromtext(:theGeom),");
		}else if(StringUtils.isNotBlank(informationVerification.getIsYaw())){
			sb.append("is_yaw=:isYaw,");
		}else if(StringUtils.isNotBlank(informationVerification.getIsIntegration())){
			sb.append("is_integration=:isIntegration,");
		}else if(StringUtils.isNotBlank(informationVerification.getUnit())){
			sb.append("unit=:unit,");
		}else if(StringUtils.isNotBlank(informationVerification.getIsExistence())){
			sb.append("is_existence=:isExistence,");
		}else if(StringUtils.isNotBlank(informationVerification.getAttribute())){
			sb.append("attribute=:attribute,");
		}else if(StringUtils.isNotBlank(informationVerification.getAccessCondition())){
			sb.append("accessCondition=:accessCondition, ");
		}else if(StringUtils.isNotBlank(informationVerification.getRemark())){
			sb.append("remark=:remark,");
		}
		String sql=sb.toString();
		sql = sql.substring(0,sql.length() - 1);
		sql+=" where id=:id ";
		return util.editObject(sql, informationVerification);
	}

	//添加审查结果 video_review_results
	public int addInformationVerification(
			InformationVerification informationVerification) {
		// TODO Auto-generated method stub
		String addSql="";
		if(informationVerification.getType().equals("video")){
			 addSql="insert into video_review_results (video_id,review_results,user_id,create_date)values(:videoId,:reviewResults,:userId,:createDate)";
		}else if(informationVerification.getType().equals("broadcast")){
			 addSql="insert into broadcast_review_results (broadcast_id,review_results,user_id,create_date)values(:broadcastId,:reviewResults,:userId,:createDate)";
		}
		return util.editObject(addSql, informationVerification);
	}
}
