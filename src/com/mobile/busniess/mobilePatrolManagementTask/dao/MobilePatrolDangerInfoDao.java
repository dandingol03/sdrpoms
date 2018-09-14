package com.mobile.busniess.mobilePatrolManagementTask.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;

@Repository("mobilePatrolDangerInfoDao")
public class MobilePatrolDangerInfoDao {
	@Autowired
	private DBUtil util;

	/**
	 * 根据 id 获取隐患的详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id) {
		String sql = "select " +
				"t.id as \"id\"," +
				"t.name as \"name\"," +
				"t.description as \"description\"," +
				"t.photos as \"photos\"," +
				"t.rail_id as \"railId\"," +
				"t.middle as \"middle\"," +
				"t.org_id as \"orgId\"," +
				"t.lng as \"lng\"," +
				"t.lat as \"lat\"," +
				"t.charger as \"charger\"," +
				"t.telephone as \"telephone\"," +
				"t.report_user_id as \"reportUserId\"," +
				"t.treat_user_id as \"treatUserId\"," +
				"t.report_time as \"reportTime\"," +
				"t.handle_status as \"handleStatus\"," +
				"t.handle_way as \"handleWay\"," +
				"t.result_dis as \"resultDis\"," +
				"t.photoss as \"photoss\"," +
				"t.danger_type as \"dangerType\"," +
				"c.name as \"railName\", " +
				"b.ORG_NAME as \"orgName\"," +
				"report.USER_NAME as \"reportUserName\","+
				"treat.USER_NAME as \"treatUserName\","+
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from patrol_danger_info t " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_users AS report ON t.report_user_id = report.USER_ID " +
				"LEFT OUTER JOIN pub_users AS treat ON t.treat_user_id = treat.USER_ID " +
				"LEFT OUTER JOIN pub_org_desc AS dd ON dd.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON dd.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//中心里程
		Object middle=row.get("middle");
		if(null!=middle&&!"".equals(middle.toString().trim())){
			row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
			String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
			row.put("middleKM", middleArr[0]);
			row.put("middleM", middleArr[1]);
		}
		Object handleStatus=row.get("handleStatus");
		if(handleStatus!=null&&!"".equals(handleStatus.toString().trim())){
			row.put("handleStatusName", PubData.getDictName("PATROL_HANDLE_STATUS", handleStatus.toString()));		
		}
		//隐患类别
		Object dangerType=row.get("dangerType");
		if(dangerType!=null&&!"".equals(dangerType.toString().trim())){
			row.put("dangeTypeName", PubData.getDictName("DANGERS_TYPE", dangerType.toString()));		
		}
		
		//上报照片
		Object tempProfile=row.get("photos");
		List<String> designProfiles=new ArrayList<String>();
		if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
			String []tempFileArray = tempProfile.toString().split(",");
			String tempFileName="'1'";
			for(int j=0;j<tempFileArray.length;j++){
				tempFileName+=","+"'"+tempFileArray[j]+"'";
			}
			String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
			List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
			for(int j=0;j<tempFileList.size();j++){
				designProfiles.add(tempFileList.get(j).get("FILE_ID").toString());
			}
		}
		//处置照片
		Object file=row.get("photoss");
		if(file!=null&&!"".equals(file.toString().trim())){
			String []tempFileArray = file.toString().split(",");
			String tempFileName="'1'";
			for(int j=0;j<tempFileArray.length;j++){
				tempFileName+=","+"'"+tempFileArray[j]+"'";
			}
			String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
			List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
			for(int j=0;j<tempFileList.size();j++){
				designProfiles.add(tempFileList.get(j).get("FILE_ID").toString());
			}
		}
		row.put("designProfiles",designProfiles);
		row.put("photosNum",designProfiles.size());
		return row;
	}
	/**
	 *  隐患上报查询
	 * @param dgm
	 * @param patrolDangerInfoPo
	 * @return
	 * 2018-04-04
	 * 隐患添加类别  字典值（DANGERS_TYPE）
	 */
	public Map<String, Object> dangerInfoQueryList(DataGridModel dgm,
			PatrolDangerInfoPo patrolDangerInfoPo,String orgId) {
				// TODO Auto-generated method stub
				//结果集 分为2个 第一个为总数 total  第二为详细的rows 
				Map<String, Object> result = new HashMap<String, Object>(2);
				
				//统计数据总数语句
				StringBuffer sumSql = new StringBuffer();
				
				String quSql="";
				    sumSql.append("select count(1) from patrol_danger_info t where 1=1 ");
				//统计数据详情语句
				 quSql = "select " +
						"t.id as \"id\"," +
						"t.name as \"name\"," +
						"t.description as \"description\"," +
						"t.photos as \"photos\"," +
						"t.rail_id as \"railId\"," +
						"t.middle as \"middle\"," +
						"t.org_id as \"orgId\"," +
						"t.lng as \"lng\"," +
						"t.lat as \"lat\"," +
						"t.charger as \"charger\"," +
						"t.telephone as \"telephone\"," +
						"t.report_user_id as \"reportUserId\"," +
						"t.treat_user_id as \"treatUserId\"," +
						"t.report_time as \"reportTime\"," +
						"t.handle_status as \"handleStatus\"," +
						"t.handle_way as \"handleWay\"," +
						"t.result_dis as \"resultDis\"," +
						"t.photoss as \"photoss\"," +
						"t.danger_type as \"dangerType\"," +
						"c.name as \"railName\", " +
						"b.ORG_NAME as \"orgName\"," +
						"report.USER_NAME as \"reportUserName\","+
						"treat.USER_NAME as \"treatUserName\","+
						"o.ORG_NAME as \"descOrgName\", " +
						"t.remark as \"remark\" " +
						"from patrol_danger_info t " +
						"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
						"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
						"LEFT OUTER JOIN pub_users AS report ON t.report_user_id = report.USER_ID " +
						"LEFT OUTER JOIN pub_users AS treat ON t.treat_user_id = treat.USER_ID " +
						"LEFT OUTER JOIN pub_org_desc AS dd ON dd.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON dd.ORG_ID = o.ORG_ID " +
						"where 1=1 ";
				// 组装查询条件
				StringBuffer sqlSb = new StringBuffer();
				Map<String, Object> params = new HashMap<String, Object>();
				//按照名称查询铁路信息
				if (null != patrolDangerInfoPo.getName()&& !patrolDangerInfoPo.getName().equals("")) {
					sqlSb.append(" and t.name like :name");
					params.put("name", "%" + patrolDangerInfoPo.getName()+ "%");
					sumSql.append(" and t.name like '%").append(patrolDangerInfoPo.getName()).append("%'");
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
						//中心里程
						Object middle=row.get("middle");
						if(null!=middle&&!"".equals(middle.toString().trim())){
							row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
							String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
							row.put("middleKM", middleArr[0]);
							row.put("middleM", middleArr[1]);
						}
						Object handleStatus=row.get("handleStatus");
						if(handleStatus!=null&&!"".equals(handleStatus.toString().trim())){
							row.put("handleStatusName", PubData.getDictName("PATROL_HANDLE_STATUS", handleStatus.toString()));		
						}
						//隐患类别
						Object dangerType=row.get("dangerType");
						if(dangerType!=null&&!"".equals(dangerType.toString().trim())){
							row.put("dangeTypeName", PubData.getDictName("DANGERS_TYPE", dangerType.toString()));		
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
						//处置照片
						Object tempProfiles=row.get("photoss");
						List<String> tempProfilesList =new ArrayList<String>();
						if(tempProfiles!=null&&!"".equals(tempProfiles.toString().trim())){
							String []tempFileArray = tempProfiles.toString().split(",");
							for(int j=0;j<tempFileArray.length;j++){
								tempProfilesList.add("/file/showPicFile?fileId="+tempFileArray[j]);
							}
							//处置照片
							row.put("resultPhotoUrls",tempProfilesList);
						}
					}
				}
				
				// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString()));
				result.put("rows", rowsList);
				return result;
	}
	/**
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public Map<String, Object> findByDangerId(
			String id) {
		// TODO Auto-generated method stub
		String sql = "select " +
					"t.id as \"id\"," +
					"t.name as \"name\"," +
					"t.description as \"description\"," +
					"t.photos as \"photos\"," +
					"t.rail_id as \"railId\"," +
					"t.middle as \"middle\"," +
					"t.org_id as \"orgId\"," +
					"t.lng as \"lng\"," +
					"t.lat as \"lat\"," +
					"t.charger as \"charger\"," +
					"t.telephone as \"telephone\"," +
					"t.report_user_id as \"reportUserId\"," +
					"t.treat_user_id as \"treatUserId\"," +
					"t.report_time as \"reportTime\"," +
					"t.handle_status as \"handleStatus\"," +
					"t.handle_way as \"handleWay\"," +
					"t.result_dis as \"resultDis\"," +
					"t.photoss as \"photoss\"," +
					"t.danger_type as \"dangerType\"," +
					"c.name as \"railName\", " +
					"b.ORG_NAME as \"orgName\"," +
					"report.USER_NAME as \"reportUserName\","+
					"treat.USER_NAME as \"treatUserName\","+
					"o.ORG_NAME as \"descOrgName\", " +
					"t.remark as \"remark\" " +
					"from patrol_danger_info t " +
					"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
					"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					"LEFT OUTER JOIN pub_users AS report ON t.report_user_id = report.USER_ID " +
					"LEFT OUTER JOIN pub_users AS treat ON t.treat_user_id = treat.USER_ID " +
					"LEFT OUTER JOIN pub_org_desc AS dd ON dd.ORG_ID = SUBSTR(t.org_id,1,6) " +
					"LEFT OUTER JOIN pub_org AS o ON dd.ORG_ID = o.ORG_ID " +
					"where 1=1 and t.id=? ";
		Map<String, Object> row=util.getMapObject(sql, new Object[]{id});
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				Object handleStatus=row.get("handleStatus");
				if(handleStatus!=null&&!"".equals(handleStatus.toString().trim())){
					row.put("handleStatusName", PubData.getDictName("PATROL_HANDLE_STATUS", handleStatus.toString()));		
				}
				//隐患类别
				Object dangerType=row.get("dangerType");
				if(dangerType!=null&&!"".equals(dangerType.toString().trim())){
					row.put("dangeTypeName", PubData.getDictName("DANGERS_TYPE", dangerType.toString()));		
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
				//处置照片
				Object tempProfiles=row.get("photoss");
				List<String> tempProfilesList =new ArrayList<String>();
				if(tempProfiles!=null&&!"".equals(tempProfiles.toString().trim())){
					String []tempFileArray = tempProfiles.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfilesList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					//处置照片
					row.put("resultPhotoUrls",tempProfilesList);
				}
		return row;
	}
}
