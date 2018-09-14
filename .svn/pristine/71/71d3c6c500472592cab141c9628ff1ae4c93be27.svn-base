package com.pc.busniess.patrolManagementTask.dao;

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
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;

@Repository("patrolDangerInfoDao")
public class PatrolDangerInfoDao {
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
						String designProfileName="";
						String photosName="";
						if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
							String []tempFileArray = tempProfile.toString().split(",");
							String tempFileName="'1'";
							for(int j=0;j<tempFileArray.length;j++){
								tempFileName+=","+"'"+tempFileArray[j]+"'";
							}
							String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
							List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
							for(int j=0;j<tempFileList.size();j++){
								designProfileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
										tempFileList.get(j).get("FILE_NAME")+"</a>"+
										"</br>";
								 photosName+=tempFileList.get(j).get("FILE_NAME");
							}
							row.put("profileName",designProfileName.toString());
							row.put("photosName",photosName.toString());
						}
						//处置照片
						Object tempProfiles=row.get("photoss");
						String designProfileNames="";
						String photossName="";
						if(tempProfiles!=null&&!"".equals(tempProfiles.toString().trim())){
							String []tempFileArray = tempProfiles.toString().split(",");
							String tempFileName="'1'";
							for(int j=0;j<tempFileArray.length;j++){
								tempFileName+=","+"'"+tempFileArray[j]+"'";
							}
							String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
							List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
							for(int j=0;j<tempFileList.size();j++){
								designProfileNames+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
										tempFileList.get(j).get("FILE_NAME")+"</a>"+
										"</br>";
								photossName+=tempFileList.get(j).get("FILE_NAME");
							}
							row.put("profileNames",designProfileNames.toString());
							row.put("photossName",photossName.toString());
						}
						
					}
				}
				
				// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString()));
				result.put("rows", rowsList);
				return result;
	}
	/**
	 * 隐患上报添加
	 * @param patrolDangerInfoPo
	 * @return
	 * 2018-04-04
	 * 
	 * 隐患添加类别  字典值（DANGERS_TYPE）
	 */
	public int addDangerInfo(PatrolDangerInfoPo patrolDangerInfoPo) {
		// TODO 添加信息
		String middle=CommonUtil.KM2MStrbyDec(patrolDangerInfoPo.getMiddleKM(),patrolDangerInfoPo.getMiddleM());
		patrolDangerInfoPo.setMiddle(middle);
		String sql = "insert into patrol_danger_info " +
				"(id, " +
				"name," +
				"description," +
				"photos," +
				"report_user_id," +
				"rail_id," +
				"middle," +
				"handle_status," +
				"danger_type," +
				"org_id," +
				"charger," +
				"telephone," +
				"report_time," +
				"lng," +
				"lat," +
				"the_geom," +
				"type," +
				"remark" +
				")values( " +
				":id," +
				":name," +
				":description," +
				":photos," +
				":reportUserId," +
				":railId," +
				":middle," +
				":handleStatus," +
				":dangerType," +
				":orgId," +
				":charger," +
				":telephone," +
				":reportTime," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":type," +
				":remark)";
		return util.editObject(sql, patrolDangerInfoPo);
	}
	/**
	 * 隐患上报修改
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public int updateDangerInfo(PatrolDangerInfoPo patrolDangerInfoPo) { 
		// TODO Auto-generated method stub
		String middle=CommonUtil.KM2MStrbyDec(patrolDangerInfoPo.getMiddleKM(),patrolDangerInfoPo.getMiddleM());
		patrolDangerInfoPo.setMiddle(middle);
		 String sql= "update patrol_danger_info set " +
						"name=:name," +
						"description=:description," +
						"photos=:photos," +
						"rail_id=:railId," +
						"middle=:middle," +
						"handle_status=:handleStatus," +
						"danger_type=:dangerType," +
						"org_id=:orgId," +
						"charger=:charger," +
						"telephone=:telephone," +
						"telephone=:telephone," +
						"report_time=:reportTime," +
						"lng=:lng," +
						"lat=:lat," +
						"the_geom=st_geomfromtext(:theGeom),"+
						"remark=:remark," +
						"report_user_id=:reportUserId " +
						"where id=:id";
		return util.editObject(sql, patrolDangerInfoPo);
	}
	/**
	 *  隐患删除
	 * @param idList
	 * @return
	 */
	public int[] deleteDangerInfo(List<String> idList) {
		// TODO Auto-generated method stub
		String delSql = "delete from patrol_danger_info where id=?";
		String delViewSql = "delete from patrol_danger_info_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteDangerInfoAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from patrol_danger_info_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveDangerInfoAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into patrol_danger_info_view (id,name,lng,lat,the_geom,org_id,rail_id,type,danger_type,handle_status,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?,?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
	
	/**
	 *  隐患处置修改
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public int updateDangerInfoDispose(PatrolDangerInfoPo patrolDangerInfoPo) {
		// TODO Auto-generated method stub
				String sql = "update patrol_danger_info set " +
								"handle_way=:handleWay," +
								"handle_status=:handleStatus," +
								"result_dis=:resultDis," +
								"photoss=:photoss, " +
								"treat_user_id=:treatUserId " +
								"where id=:id";
				return util.editObject(sql, patrolDangerInfoPo);
	}
	/**
	 * 修改隐患关联表的处理结果状态
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public int updateDangerInfoDisposeAuths(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		// TODO Auto-generated method stub
		String sql = "update patrol_danger_info_view set " +
				"handle_status=:handleStatus " +
				"where id=:id ";
		return util.editObject(sql, patrolDangerInfoPo);
	}
	public Integer dangerCount(PatrolDangerInfoPo patrolDangerInfoPo) {
	    // TODO Auto-generated method stub
	    String Sql = "select count(1) from patrol_danger_info t where 1=1 and handle_status='01'";
	    return util.getObjCount(Sql);
	}
}
