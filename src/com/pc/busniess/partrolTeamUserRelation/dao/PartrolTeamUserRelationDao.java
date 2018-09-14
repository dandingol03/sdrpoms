package com.pc.busniess.partrolTeamUserRelation.dao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.partrolTeamUserRelation.po.PartrolTeamUserRelationPo;
import com.pc.busniess.partrolTeamUserRelation.po.ResponsibilityLinePo;

/**
 * 巡防人
 * 这个Dao层主要是对数据库的操作
 * @author jiawenlong
 * @version 1.0
 * @since 1.0
 */
@Repository("PartrolTeamUserRelationDao")
public class PartrolTeamUserRelationDao{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DBUtil util;
	
	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object>  mobileFindById(String id) {
		// TODO Auto-generated method stub
		 String sql = "select " +
					"COUNT(DISTINCT(t.id)) as \"count\" " +
					"from patrol_team_user_relation t " +
					"where 1=1 AND t.user_id=?  ";
		return util.getMapObject(sql, new Object[]{id});
	}
	public Map<String, Object> findByCardeId(String id) {
		 String sql = "select " +
				"c.USER_ID as \"id\", " +
				"c.user_name as \"name\", " +
				"c.user_gender as \"userGender\", " +
				"c.user_birthday as \"userBirthday\", " +
				"c.user_telephone as \"userTelephone\", " +
				"c.USER_DEPARTMENT as \"userDepartment\", " +
				"c.user_duty as \"userDuty\", " +
				"c.USER_NAME as \"userName\", "+
				"c.user_account as \"userAccount\" "+
				"from pub_users c " +
				"where 1=1 AND c.USER_ID=?  ";
		 	Map<String, Object> row=util.getMapObject(sql, new Object[]{id});
		 	//户口(字典)
			Object DEPARTMENT=row.get("userDepartment");
			if(DEPARTMENT!=null&&!"".equals(DEPARTMENT.toString().trim())){
				//将指定值放到对应键中
				row.put("userDepartmentName", PubData.getDictName("DEPARTMENT", DEPARTMENT.toString()));
			}
			return row;
	}
	public Map<String, Object> findById(String id) {
		 String sql = "select " +
				"t.id as \"id\", " +
				"a.org_id as \"orgId\", " +
				"c.user_name as \"name\", " +
				"c.user_gender as \"userGender\", " +
				"c.user_birthday as \"userBirthday\", " +
				"t.education as \"education\", " +
				"t.registration as \"registration\", " +
				"t.domicile as \"domicile\", " +
				"t.residence as \"residence\", " +
				"c.user_telephone as \"userTelephone\", " +
				"c.user_duty as \"userDuty\", " +
				"t.id_number as \"idNumber\", " +
				"t.management_unit as \"managementUnit\", " +
				"t.user_id as \"userId\", " +
				"t.team_info_id as \"teamInfoId\", " +
				"t.remark as \"remark\", " +
				"c.USER_NAME as \"userName\", "+
				"c.user_account as \"userAccount\", "+
				"c.LAST_TIME as \"lastTime\", "+
				"a.name as \"teamInfoName\" "+
				"from patrol_team_user_relation t " +
				"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
				"LEFT OUTER JOIN patrol_team_info AS a ON t.team_info_id = a.id " +
				"where 1=1 AND t.user_id=:id  ";
		 String sqltack= "select " +
					"i.id as \"id\", " +
					"i.start_time as \"startTime\", "+
					"i.end_time as \"endTime\", "+
					"i.file_id as \"fileId\", "+
					"i.track as \"track\" "+
					"from patrol_track_info i " +
					"where 1=1 AND i.user_id=:id and  i.end_time >=date_sub(curdate(),interval 1 day) ";
		 	Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
		 	Map<String, Object> row=util.getMapObject(sql, paramMap);
			//性别(字典)
			Object gender=row.get("userGender");
			if(gender!=null&&!"".equals(gender.toString().trim())){
				//将指定值放到对应键中
				row.put("genderName", PubData.getDictName("TASK_GENDER", gender.toString()));
			}
			//学历(字典)
			Object education=row.get("education");
			if(education!=null&&!"".equals(education.toString().trim())){
				//将指定值放到对应键中
				row.put("educationName", PubData.getDictName("EDUCATION", education.toString()));
			}
			//户口(字典)
			Object registration=row.get("registration");
			if(registration!=null&&!"".equals(registration.toString().trim())){
				//将指定值放到对应键中
				row.put("registrationName", PubData.getDictName("REGISTERRD", registration.toString()));
			}
			
			List<Map<String,Object>> rowsList=util.getMapList(sqltack, paramMap);
			if(rowsList!=null&&rowsList.size()>0){
				for (int i = 0; i < rowsList.size(); i++) {
					Map<String,Object> row1=rowsList.get(i);
					//记录文件列表
					Object tempProfile=row1.get("fileId");
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
						row.put("designProfiles",designProfiles);
						row.put("photosNum",tempFileList.size());
					}
				}
			}
			ObjectMapper obj=new ObjectMapper();
			try {
				row.put("trackList",obj.writeValueAsString(rowsList));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return row;
	}
	/**
	 * 查询所有的人信息
	 * @return
	 */
	public List<Map<String, Object>> queryPers() {
		//统计数据详情语句
		String quSql = "select " +
				"t.USER_ID as \"id\", " +
				"t.USER_NAME as \"name\"  " +
				"from pub_users t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * 1.组装分页查询（ 名字,分类的模糊查询）  
	 * @param dgm
	 * @param PatrolTrackInfoPo
	 * @return 查询结果集
	 */
	public Map<String, Object> PartrolTeamUserRelationQueryList(DataGridModel dgm, PartrolTeamUserRelationPo partrolTeamUserRelationPo,String orgId) {
		// TODO 查询巡防人信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		 String quSql="";
		     sumSql.append("select count(1) from patrol_team_user_relation t "
		    		+ "LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " 
		     		+ "where 1=1 "
		     		 );
		//统计数据详情语句
			  quSql = "select " +
					"t.id as \"id\", " +
					"a.org_id as \"orgId\", " +
					"c.user_name as \"name\", " +
					"c.user_gender as \"userGender\", " +
					"c.user_birthday as \"userBirthday\", " +
					"t.education as \"education\", " +
					"t.registration as \"registration\", " +
					"t.domicile as \"domicile\", " +
					"t.residence as \"residence\", " +
					"c.user_telephone as \"userTelephone\", " +
					"c.user_duty as \"userDuty\", " +
					"t.id_number as \"idNumber\", " +
					"t.management_unit as \"managementUnit\", " +
					"t.user_id as \"userId\", " +
					"t.team_info_id as \"teamInfoId\", " +
					"t.remark as \"remark\", " +
					"c.USER_NAME as \"userName\", "+
					"c.user_account as \"userAccount\", "+
					"a.name as \"teamInfoName\" "+
					"from patrol_team_user_relation t " +
					"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
					"LEFT OUTER JOIN patrol_team_info AS a ON t.team_info_id = a.id " +
					"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		//按照队伍id查询巡防人信息     队伍id已知
		if (null !=partrolTeamUserRelationPo.getTeamInfoId()&& !partrolTeamUserRelationPo.getTeamInfoId().equals("")) {
			sqlSb.append(" and t.team_info_id = :teamInfoId");
			params.put("teamInfoId",partrolTeamUserRelationPo.getTeamInfoId());
			sumSql.append(" and t.team_info_id = '"+partrolTeamUserRelationPo.getTeamInfoId()+"'");
		}
		//按照名称查询巡防人信息
		if (null !=partrolTeamUserRelationPo.getUserName()&& !partrolTeamUserRelationPo.getUserName().equals("")) {
			sqlSb.append(" and c.USER_NAME like :userName");
			params.put("userName", "%" +partrolTeamUserRelationPo.getUserName()+ "%");
			sumSql.append(" and c.USER_NAME like '%").append(partrolTeamUserRelationPo.getUserName()).append("%'");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//查询字典
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//性别(字典)
				Object gender=row.get("userGender");
				if(gender!=null&&!"".equals(gender.toString().trim())){
					//将指定值放到对应键中
					row.put("genderName", PubData.getDictName("TASK_GENDER", gender.toString()));
				}
				//学历(字典)
				Object education=row.get("education");
				if(education!=null&&!"".equals(education.toString().trim())){
					//将指定值放到对应键中
					row.put("educationName", PubData.getDictName("EDUCATION", education.toString()));
				}
				//户口(字典)
				Object registration=row.get("registration");
				if(registration!=null&&!"".equals(registration.toString().trim())){
					//将指定值放到对应键中
					row.put("registrationName", PubData.getDictName("REGISTERRD", registration.toString()));
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	/**
	 * 添加巡防人信息
	 * @param PatrolTrackInfoPo
	 * @return
	 */
	public int addPartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO 添加巡防人信息
		String sql = "insert into patrol_team_user_relation " +
				"(id, " +
				"education," +
				"registration," +
				"domicile," +
				"residence," +
				"id_number," +
				"management_unit," +
				"user_id," +
				"team_info_id," +
				"remark" +
				")values( " +
				":id," +
				":education," +
				":registration," +
				":domicile," +
				":residence," +
				":idNumber," +
				":managementUnit," +
				":userId," +
				":teamInfoId," +
				":remark)";
		String theGeom="POINT("+0+" "+0+")";
		String sql2 = "insert into pub_users " +
				"(user_id, " +
				"user_account, " +
				"user_name, " +
				"user_password, " +
				"user_gender, " +
				"user_birthday, " +
				"user_org, " +
				"user_duty, " +
				"user_telephone, " +
				"user_department, " +
				"mail, " +
				"qq_weixin, " +
				"user_desc, " +
				"enable, " +
				"is_sys, " +
				"lng, " +
				"lat, "+
				"the_geom ) " +
				"values (" +
				":userId, " +
				":userAccount, " +
				":userName, " +
				":userPassword, " +
				":userGender, " +
				":userBirthday, " +
				":userOrg, " +
				":userDuty, " +
				":userTelephone, " +
				"0, " +
				":mail, " +
				":qqWeixin, " +
				":userDesc, " +
				"1, " +
				"1, " +
				"0, " +
				"0, " +
				"st_geomfromtext(:theGeom))";
		// 盐值加密
		@SuppressWarnings("deprecation")
		String password = passwordEncoder.encodePassword
				(partrolTeamUserRelationPo.getUserPassword().trim(), partrolTeamUserRelationPo.getUserAccount().trim());
		partrolTeamUserRelationPo.setUserPassword(password);
		partrolTeamUserRelationPo.setTheGeom(theGeom);
		
		String sql3 = "insert into pub_users_roles (id, user_id, role_id) values (:usersRolesId, :userId, 'role_1515502487805')";
		int a= util.editObject(sql2, partrolTeamUserRelationPo);
		int b= util.editObject(sql, partrolTeamUserRelationPo);
		partrolTeamUserRelationPo.setUsersRolesId(NextID.getUUID());
		int c= util.editObject(sql3, partrolTeamUserRelationPo);
		if(a>0&&b>0&&c>0){
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 更新巡防人信息
	 * @param PatrolTrackInfoPo
	 * @return
	 */
	public int updatePartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO 更新巡防人信息
		String sql = "update patrol_team_user_relation set " +
				"registration=:registration," +
				"domicile=:domicile," +
				"residence=:residence," +
				"education=:education," +
				"id_number=:idNumber," +
				"management_unit=:managementUnit," +
				"remark=:remark "+
				"where id=:id";
		String sql2 = "update pub_users set " +
				"user_name = :userName, "+
				"user_gender = :userGender, " +
				"user_birthday = :userBirthday, " +
				"user_duty = :userDuty, "+
				"user_telephone = :userTelephone  " +
				" where user_id=:userId ";
		int a= util.editObject(sql, partrolTeamUserRelationPo);
		int b= util.editObject(sql2, partrolTeamUserRelationPo);
		if(a>0&&b>0){
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 删除巡防人信息
	 * @param idList
	 * @param userList 
	 * @return
	 */
	public int[] deletePartrolTeamUserRelation(List<String> idList, List<String> userList) {
		// TODO 删除巡防人信息
		String delSql = "delete from patrol_team_user_relation where id=?";
		String delUserSql = "delete from pub_users where user_id=?";
		String delRolSql = "delete from pub_users_roles where user_id=? and role_id='role_1515502487805' ";
		util.batchDelete(delUserSql, userList);
		util.batchDelete(delRolSql, userList);
		return util.batchDelete(delSql, idList);
	}
	public List<Map<String, Object>> queryPartStation() {
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\"  " +
				"from base_info_part_station t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * @param partrolTeamUserRelationPo
	 * @return
	 */
	public List<Map<String, Object>> getNewsListByExport(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo,String teamInfoId) {
			// TODO Auto-generated method stub
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer sqlSb =new StringBuffer();
			if (null !=partrolTeamUserRelationPo.getUserName()&& !partrolTeamUserRelationPo.getUserName().equals("")) {
				sqlSb.append(" and c.USER_NAME like :userName");
				params.put("userName", "%" +partrolTeamUserRelationPo.getUserName()+ "%");
			}
			String sql= "select " +
					"t.id as \"id\", " +
					"a.org_id as \"orgId\", " +
					"c.user_name as \"name\", " +
					"c.user_gender as \"gender\", " +
					"c.user_birthday as \"birth\", " +
					"t.education as \"education\", " +
					"t.registration as \"registration\", " +
					"t.domicile as \"domicile\", " +
					"t.residence as \"residence\", " +
					"c.user_telephone as \"telephone\", " +
					"c.user_duty as \"duty\", " +
					"t.id_number as \"idNumber\", " +
					"t.management_unit as \"managementUnit\", " +
					"t.user_id as \"userId\", " +
					"t.team_info_id as \"teamInfoId\", " +
					"t.remark as \"remark\", " +
					"c.USER_NAME as \"userName\", "+
					"a.name as \"teamInfoName\" "+
					" from patrol_team_user_relation t " +
					"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
					"LEFT OUTER JOIN patrol_team_info AS a ON t.team_info_id = a.id " +
					"where 1=1 and team_info_id= :teamInfoId ";
			params.put("teamInfoId", teamInfoId);
			List<Map<String,Object>> rowsList=util.getMapList(sql+sqlSb, params);
			if(rowsList!=null&&rowsList.size()>0){
				for(int i=0;i<rowsList.size();i++){
					Map<String,Object> row=rowsList.get(i);
					//性别(字典)
					Object gender=row.get("gender");
					if(gender!=null&&!"".equals(gender.toString().trim())){
						//将指定值放到对应键中
						row.put("genderName", PubData.getDictName("TASK_GENDER", gender.toString()));
					}
					//学历(字典)
					Object education=row.get("education");
					if(education!=null&&!"".equals(education.toString().trim())){
						//将指定值放到对应键中
						row.put("educationName", PubData.getDictName("EDUCATION", education.toString()));
					}
					//户口(字典)
					Object registration=row.get("registration");
					if(registration!=null&&!"".equals(registration.toString().trim())){
						//将指定值放到对应键中
						row.put("registrationName", PubData.getDictName("REGISTERRD", registration.toString()));
					}
				}
			}
		return  rowsList;
	}
	/**
	 * 责任区段查询
	 * @param dgm
	 * @param responsibilityLinePo
	 * @return
	 */
	public Map<String, Object> responsibilityLineQueryList(DataGridModel dgm,
			ResponsibilityLinePo responsibilityLinePo) {
		// TODO Auto-generated method stub
		// TODO 查询巡防人信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		 String quSql="";
	     sumSql.append("select count(1) from patrol_team_user_line t "
	     		+ "LEFT OUTER JOIN base_info_rail AS r ON r.id = t.rail_id "
	     		+ "where 1=1 ");
		//统计数据详情语句
		  quSql = "select " +
				"t.id as \"id\", " +
				"t.user_id as \"userId\", " +
				"t.rail_id as \"railId\", " +
				"t.start as \"start\", " +
				"t.end as \"end\", " +
				"t.rangeArr as \"range\", " +
				"r.rail_name as \"railName\", " +
				"c.USER_NAME as \"userName\", "+
				"t.file_id as \"fileId\", " +
				"a.name as \"teamInfoName\" "+
				"from patrol_team_user_line t " +
				"LEFT OUTER JOIN base_info_rail_stream AS r ON r.id = t.rail_id " +
				"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
				"LEFT OUTER JOIN patrol_team_user_relation AS p ON t.user_id = p.user_id " +
				"LEFT OUTER JOIN patrol_team_info AS a ON p.team_info_id = a.id " +
				"where 1=1  ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		//按照userid查询巡防人信息     userid已知
		if (null !=responsibilityLinePo.getUserId()&& !responsibilityLinePo.getUserId().equals("")) {
			sqlSb.append(" and t.user_id = :userId");
			params.put("userId",responsibilityLinePo.getUserId());
			sumSql.append(" and t.user_id = '"+responsibilityLinePo.getUserId()+"'");
		}
		if (null != responsibilityLinePo.getRailName()&& !responsibilityLinePo.getRailName().equals("")) {
			sqlSb.append(" and r.name like :name");
			params.put("name", "%" + responsibilityLinePo.getRailName()+ "%");
			sumSql.append(" and r.name like '%").append(responsibilityLinePo.getRailName()).append("%'");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//查询字典
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		for (int i = 0; i < rowsList.size(); i++) {
			Map<String,Object> row=rowsList.get(i);
			//起点路程计算
			Object start=row.get("start");
			if(null!=start&&!"".equals(start.toString().trim())){
				row.put("startStr", CommonUtil.KM2MStrbyDec(start.toString()));
				String [] startArr=CommonUtil.M2KMStrbyDec(start.toString());
				row.put("startKM", startArr[0]);
				row.put("startM", startArr[1]);
			}
			//终点路程计算
			Object end=row.get("end");
			if(null!=end&&!"".equals(end.toString().trim())){
				row.put("endStr", CommonUtil.KM2MStrbyDec(end.toString()));
				String [] endArr=CommonUtil.M2KMStrbyDec(end.toString());
				row.put("endKM", endArr[0]);
				row.put("endM", endArr[1]);
			}
			Object tempProfile=row.get("fileId");
			String designProfileName="";
			String filesName="";
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
					filesName+=tempFileList.get(j).get("FILE_NAME");
				}
				row.put("profileName",designProfileName.toString());
				row.put("filesName",filesName.toString());
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	/**
	 * @param responsibilityLinePo
	 * @return
	 */
	public int addResponsibilityLine(ResponsibilityLinePo responsibilityLinePo) {
		System.err.println(responsibilityLinePo);
		// TODO Auto-generated method stub
		String sql = "insert into patrol_team_user_line " +
				"(id, " +
				"rail_id," +
				"user_id," +
				"start," +
				"end," +
				"file_id," +
				"rangeArr" +
				")values( " +
				":id," +
				":railId," +
				":userId," +
				":start," +
				":end," +
				":fileId," +
				":range)";
		return util.editObject(sql, responsibilityLinePo);
	}
	/**
	 * @param responsibilityLinePo
	 * @return
	 */
	public int updateResponsibilityLine(
			ResponsibilityLinePo responsibilityLinePo) {
		// TODO Auto-generated method stub
		String sql = "update patrol_team_user_line set " +
				"rail_id=:railId," +
				"user_id=:userId," +
				"start=:start," +
				"end=:end, " +
				"file_id=:fileId, " +
				"rangeArr=:range " +
				"where id=:id";
		return util.editObject(sql, responsibilityLinePo);
	}
	/**
	 * @param idList
	 * @return
	 */
	public int[] deleteResponsibilityLine(List<String> idList) {
		// TODO Auto-generated method stub
		System.err.println(idList);
		String delSql = "delete from patrol_team_user_line where id=?";
		return util.batchDelete(delSql, idList);
	}
	/**
	 * @param partrolTeamUserRelationPo
	 * @return
	 */
	public List<Map<String, Object>> partrolTeamUserRelationQueryList(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		  String quSql = "select " +
				"t.id as \"id\", " +
				"t.org_id as \"orgId\", " +
				"c.user_name as \"name\", " +
				"c.user_gender as \"gender\", " +
				"c.user_birthday as \"birth\", " +
				"t.education as \"education\", " +
				"t.registration as \"registration\", " +
				"t.domicile as \"domicile\", " +
				"t.residence as \"residence\", " +
				"c.user_telephone as \"telephone\", " +
				"c.user_duty as \"telephone\", " +
				"t.rail_name as \"railName\", " +
				"t.task_interval as \"taskInterval\", " +
				"t.id_number as \"idNumber\", " +
				"t.management_unit as \"managementUnit\", " +
				"t.user_id as \"userId\", " +
				"t.team_info_id as \"teamInfoId\", " +
				"t.remark as \"remark\", " +
				"c.USER_NAME as \"userName\", "+
				"a.name as \"teamInfoName\" "+
				"from patrol_team_user_relation t " +
				"LEFT OUTER JOIN pub_users AS c ON t.user_id = c.USER_ID " +
				"LEFT OUTER JOIN patrol_team_info AS a ON t.team_info_id = a.id " +
				"where 1=1 and team_info_id= :teamInfoId ";
		  result.put("teamInfoId", partrolTeamUserRelationPo.getTeamInfoId());
		return util.getMapList(quSql, result);
	}
	public int checkTeamData(String id, String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from patrol_track_info where user_id = ?";
		int a=util.getMapList(sql, new Object[] { userId }).size();
		
		String sql2 = "select * from patrol_team_user_line where user_id = ?";
		int b=util.getMapList(sql2, new Object[] { userId }).size();
		if(a==0&&b==0){
			return 1;
		}else{
			return 0;
		}
	}
	public int updatePatrolUserBlongTeam(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo, String teamInfoId) {
		// TODO Auto-generated method stub
		
		String sql = "update patrol_team_user_relation set " +
				" team_info_id =:teamInfoId"+
				"	where user_id=:userId";
		
		String sql2 = "update pub_users set " +
				" user_org = (select a.org_id from (select org_id from patrol_team_info where id=:teamInfoId) as a)  " +
				"	where user_id=:userId";
		partrolTeamUserRelationPo.setTeamInfoId(teamInfoId);
		 int a=util.editObject(sql, partrolTeamUserRelationPo);
		 int b= util.editObject(sql2, partrolTeamUserRelationPo);
		 if(a==0&&b==0){
			 return 0;
		 }else{
			 return 1;
		 }
	}
	/**
	 * @param railId
	 * @param middle
	 * @param endMiddle
	 * @return
	 */
	public List<Double []> findlnglat(Object railId,Object middle,Object endMiddle) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sqlList="SELECT "
				+ "t.id as \"id\", " 
				+ "t.lng as \"lng\", " 
				+ "t.lat as \"lat\"  "  
				+ "FROM "
				+ "base_info_rail_data t "
				+ "WHERE rail_id= :railId "
				+ "AND kilometer_mark >= CAST( :middle  AS signed)"
				+ "AND kilometer_mark <= CAST( :endMiddle  AS signed) "
				+ "ORDER BY rail_id ";
		paramMap.put("railId", railId);
		paramMap.put("middle", middle);
		paramMap.put("endMiddle", endMiddle);
		List<Map<String, Object>> router = util.getMapList(sqlList, paramMap);
		List<Double []> list=new ArrayList<>();
		if(router.size()>0){
			for (int j = 0; j < router.size(); j++) {
	
				Double [] lnglat= new Double [] {Double.valueOf(router.get(j).get("lng").toString()), Double.valueOf(router.get(j).get("lat").toString())};
				list.add(lnglat);
			}	
		}
		return list;
	}
	
}

