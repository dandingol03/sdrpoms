package com.pc.busniess.baseInfoDefencePoliceHouse.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoDefencePoliceHouse.po.BaseInfoDefencePoliceHousePo;

@Repository("baseInfoDefencePoliceHouseDao")
public class BaseInfoDefencePoliceHouseDao{
	
	@Autowired
	private DBUtil util;
	//派出所
	public Map<String, Object> findById(String id) {
		String sql = "select " +
						"t.id as \"id\", " +
						"t.name as \"name\", " +
						"t.org_id as \"orgId\", " +
						"t.lng as \"lng\", " +
						"t.lat as \"lat\", " +
						"t.adress as \"adress\", " +
						"t.jurisdiction as \"jurisdiction\", " +
						"t.file_id as \"fileId\", " +
						"t.remark as \"remark\", " +
						"t.charger as \"charger\", " +
						"o.ORG_NAME as \"descOrgName\", " +
						"t.telephone as \"telephone\", " +
						"b.ORG_NAME as \"orgName\" " +
						"from base_info_defence_police_house t " +
						"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
						"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//记录文件列表
		Object tempProfile=row.get("fileId");
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
	
	/**
	 * 查询派出所信息
	 * @param dgm
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	public Map<String, Object> baseInfoDefencePoliceHouseQueryList(DataGridModel dgm,BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo,String orgId) {
		// TODO 查询派出所信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		
		//统计数据详情语句
		 String quSql="";
			    sumSql.append("select count(1) from base_info_defence_police_house t where 1=1 ");
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.adress as \"adress\", " +
				"t.jurisdiction as \"jurisdiction\", " +
				"t.file_id as \"fileId\", " +
				"t.remark as \"remark\", " +
				"t.charger as \"charger\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.telephone as \"telephone\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_defence_police_house t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询派出所信息
		if (null != baseInfoDefencePoliceHousePo.getName()&& !baseInfoDefencePoliceHousePo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefencePoliceHousePo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefencePoliceHousePo.getName()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoDefencePoliceHousePo.getOrgId() && !baseInfoDefencePoliceHousePo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoDefencePoliceHousePo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoDefencePoliceHousePo.getOrgId()).append("%'");
		}
		//三公里范围内派出所
		if (null != baseInfoDefencePoliceHousePo.getRailId()&& !baseInfoDefencePoliceHousePo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_defence_police_house_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoDefencePoliceHousePo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_defence_police_house_view where rail_stream_id= '").
			append(baseInfoDefencePoliceHousePo.getRailId()).append("') ");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object file=row.get("fileId");
				String fileName="";
				String filePhotosName="";
				if(file!=null&&!"".equals(file.toString().trim())){
					String []tempFileArray = file.toString().split(",");
					String tempFileName="'1'";
					for(int j=0;j<tempFileArray.length;j++){
						tempFileName+=","+"'"+tempFileArray[j]+"'";
					}
					String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
					List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
					for(int j=0;j<tempFileList.size();j++){
						fileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
								tempFileList.get(j).get("FILE_NAME")+"</a>"+
								"</br>";
						filePhotosName+=tempFileList.get(j).get("FILE_NAME");
					}
					row.put("profileName",fileName.toString());
					row.put("photosName",filePhotosName.toString());
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);

		return result;
	}
	/**
	 * 添加派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	public int addBaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		// TODO 添加服务忌语
		String sql = "insert into base_info_defence_police_house " +
				"(id, " +
				"name," +
				"org_id," +
				"lng," +
				"lat," +
				"adress," +
				"charger," +
				"telephone," +
				"jurisdiction," +
				"the_geom," +
				"file_id," +
				"remark" +
				")values( " +
				":id," +
				":name," +
				":orgId," +
				":lng," +
				":lat," +
				":adress," +
				":charger," +
				":telephone," +
				":jurisdiction," +
				"st_geomfromtext(:theGeom),"+
				":fileId," +
				":remark)";
		return util.editObject(sql, baseInfoDefencePoliceHousePo);
	}
	/**
	 * 更新派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	public int updateBaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		// TODO 更新派出所信息
		String sql = "update base_info_defence_police_house set " +
				"name=:name," +
				"org_id=:orgId," +
				"lng=:lng," +
				"lat=:lat," +
				"adress=:adress," +
				"charger=:charger," +
				"telephone=:telephone," +
				"jurisdiction=:jurisdiction," +
				"the_geom=st_geomfromtext(:theGeom),"+
				"file_id=:fileId," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoDefencePoliceHousePo);
	}
	/**
	 * 删除派出所信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoDefencePoliceHouse(List<String> idList) {
		// TODO 删除派出所信息
		String delSql = "delete from base_info_defence_police_house where id=?";
		String delViewSql = "delete from base_info_defence_police_house_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoDefencePoliceHouseAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_defence_police_house_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoDefencePoliceHouseAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_defence_police_house_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
