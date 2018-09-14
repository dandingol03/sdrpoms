package com.pc.busniess.baseInfoDefenceGuardStation.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoDefenceGuardStation.po.BaseInfoDefenceGuardStationPo;


@Repository("baseInfoDefenceGuardStationDao")
public class BaseInfoDefenceGuardStationDao{
	@Autowired
	private DBUtil util;
	/**
	 * 根据id获取护路工作站的详细信息
	 * @param id 护路工作站ID
	 * @return 返回对应的护路工作站的详细信息
	 * @author lyf
	 */
	public Map<String, Object> findById(String id) {
		String sql ="select " +
					   "t.id as \"id\", " +
					   "t.name as \"name\", " +
					   "t.adress as \"adress\", " +
					   "t.people_num as \"peopleNum\", " +
					   "t.service_mode as \"serviceMode\", " +
					   "t.patrol_range as \"patrolRange\", " +
					   "t.guard_target as \"guardTarget\" ," +
					   "t.patrol_team as \"patrolTeam\" ," +
					   "t.org_id as \"orgId\" ," +
					   "t.lng as \"lng\" ," +
					   "t.lat as \"lat\" ," +
					   "t.is_accept as \"isAccept\" ," +
					   "t.remark as \"remark\" ," +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "t.photos as \"photos\" ," +
					   "b.ORG_NAME as \"orgName\" " +
					   "from  base_info_defence_guard_station t " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		Object serviceMode=row.get("serviceMode");
		if(serviceMode!=null&&!"".equals(serviceMode.toString().trim())){
			row.put("serviceModeName", PubData.getDictName("GUARD_STATION_SERVICE_MODE", serviceMode.toString()));
		}
		
		Object isAccept=row.get("isAccept");
		if(isAccept!=null&&!"".equals(isAccept.toString().trim())){
			row.put("isAcceptName", PubData.getDictName("ISACCEPT", isAccept.toString()));		
		}
		//记录文件列表
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
			row.put("designProfiles",designProfiles);
			row.put("photosNum",tempFileList.size());
		}
		return row;
	}

	public Map<String, Object> baseInfoDefenceGuardStationQueryList(DataGridModel dgm, BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo,String orgId) {
		/**
		 * TODO
		 * 查询护路工作站信息
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
		sumSql.append("select count(1) from base_info_defence_guard_station t  where 1=1 ");
		String quSql = "select " +
				   "t.id as \"id\", " +
				   "t.name as \"name\", " +
				   "t.adress as \"adress\", " +
				   "t.people_num as \"peopleNum\", " +
				   "t.service_mode as \"serviceMode\", " +
				   "t.patrol_range as \"patrolRange\", " +
				   "t.guard_target as \"guardTarget\" ," +
				   "t.patrol_team as \"patrolTeam\" ," +
				   "t.org_id as \"orgId\" ," +
				   "t.lng as \"lng\" ," +
				   "t.lat as \"lat\" ," +
				   "t.is_accept as \"isAccept\" ," +
				   "t.remark as \"remark\" ," +
				   "o.ORG_NAME as \"descOrgName\", " +
				   "t.photos as \"photos\" ," +
				   "b.ORG_NAME as \"orgName\" " +
				   "from  base_info_defence_guard_station t " +
				   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				   "where 1=1 ";;

		/**
		 * 组装查询条件
		 */
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		/**
		 * 按照名称查询护路工作站信息
		 */
		if (null != baseInfoDefenceGuardStationPo.getName()&& !baseInfoDefenceGuardStationPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefenceGuardStationPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefenceGuardStationPo.getName()).append("%'");
		}
		
		if (null != baseInfoDefenceGuardStationPo.getAdress() && !baseInfoDefenceGuardStationPo. getAdress().equals("")) {
			sqlSb.append(" and t.adress like :adress");
			params.put("adress", "%" + baseInfoDefenceGuardStationPo. getAdress()+ "%");
			sumSql.append(" and t.adress like '%").append(baseInfoDefenceGuardStationPo. getAdress()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoDefenceGuardStationPo.getOrgId() && !baseInfoDefenceGuardStationPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoDefenceGuardStationPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoDefenceGuardStationPo.getOrgId()).append("%'");
		}
		//三公里范围内护路工作站
		if (null != baseInfoDefenceGuardStationPo.getRailId()&& !baseInfoDefenceGuardStationPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_defence_guard_station_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoDefenceGuardStationPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_defence_guard_station_view where rail_stream_id= '").
			append(baseInfoDefenceGuardStationPo.getRailId()).append("') ");
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
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
					Object serviceMode=row.get("serviceMode");
					if(serviceMode!=null&&!"".equals(serviceMode.toString().trim())){
						row.put("serviceModeName", PubData.getDictName("GUARD_STATION_SERVICE_MODE", serviceMode.toString()));
					}
					
					Object isAccept=row.get("isAccept");
					if(isAccept!=null&&!"".equals(isAccept.toString().trim())){
						row.put("isAcceptName", PubData.getDictName("ISACCEPT", isAccept.toString()));		
					}
					//记录文件列表
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
				}
			}
		
		/**
		 * 绑定查询结果('total'和'rows'名称不能修改)
		 */
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList );
		return result;
	}
	
	public int addBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		/**
		 * TODO
		 * 添加服务忌语 
		 */
		String sql = "insert into base_info_defence_guard_station " +
					 "(id, " +
					 "name," +
					 "photos," +
					 "adress," +
					 "people_num," +
					 "service_mode," +
					 "patrol_range," +
					 "guard_target," +
					 "patrol_team," +
					 "the_geom," +
					 "is_accept," +
					 "org_id," +
					 "lng," +
					 "lat," +
					 "remark " +
					 ")values( " +
					 ":id," +
					 ":name," +
					 ":photos," +
					 ":adress," +
					 ":peopleNum," +
					 ":serviceMode," +
					 ":patrolRange," +
					 ":guardTarget," +
					 ":patrolTeam," +
					 "st_geomfromtext(:theGeom),"+
					 ":isAccept," +
					 ":orgId," +
					 ":lng," +
					 ":lat," +
					 ":remark)";
		return util.editObject(sql, baseInfoDefenceGuardStationPo);
	}
	
	public int updateBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		/**
		 * TODO 
		 * 更新护路工作站信息
		 */
		String sql = "update base_info_defence_guard_station set " +
					 "name=:name," +
					 "photos=:photos," +
					 "adress=:adress," +
					 "people_num=:peopleNum," +
					 "service_mode=:serviceMode," +
					 "patrol_range=:patrolRange," +
					 "guard_target=:guardTarget, "+
					 "patrol_team=:patrolTeam,"+
					 "org_id=:orgId ,"+
					 "lng=:lng,"+
					 "is_accept=:isAccept,"+
					 "the_geom=st_geomfromtext(:theGeom),"+
					 "lat=:lat,"+
					 "remark=:remark  "+
					 "where id=:id";
		
		return util.editObject(sql, baseInfoDefenceGuardStationPo);
	}
	
	public int[] deleteBaseInfoDefenceGuardStation(List<String> idList) {
		/**
		 * TODO
		 * 删除护路工作站信息
		 */
		String delSql = "delete from  base_info_defence_guard_station where id=?";
		String delViewSql = "delete from base_info_defence_guard_station_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoDefenceGuardStationAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_defence_guard_station_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoDefenceGuardStationAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_defence_guard_station_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}

}
