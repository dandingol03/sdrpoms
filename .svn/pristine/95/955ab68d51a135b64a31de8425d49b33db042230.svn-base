package com.pc.busniess.baseInfoPartTrajection.dao;


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
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;
/**
 * 行人易穿行部位是基本信息的相关逻辑处理
 * @author mdf
 * @version 1.0
 * @since
 */
@Repository("baseInfoPartTrajectionDao")
public class BaseInfoPartTrajectionDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 根据id获取行人易穿行部位的详细信息
	 * @param id 行人易穿行部位ID
	 * @return 返回对应的行人易穿行部位的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		String sql =  "select " +
						"t.id as \"id\", " +
						"t.name as \"name\", " +
						"t.status as \"status\", " +
						"t.address as \"address\", " +
						"t.telephone as \"telephone\", " +
						"t.photos as \"photos\", " +
						"t.org_id as \"orgId\", " +
						"b.ORG_NAME as \"orgName\", " +
						"t.region as \"region\", " +
						"t.conditions as \"conditions\", " +
						"o.ORG_NAME as \"descOrgName\", " +
						"t.lng as \"lng\", " +
						"t.lat as \"lat\", " +
						"t.remark as \"remark\" " +
						"from base_info_part_trajection t " +
						"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
						"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
						"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//易穿行状态
		Object status=row.get("status");
		if(status!=null&&!"".equals(status.toString().trim())){
			row.put("statusName", PubData.getDictName("HIDDEN_TRAJECTION_STATUS", status.toString()));		
		}
		//护栏情况
		Object condition=row.get("conditions");
		if(condition!=null&&!"".equals(condition.toString().trim())){
			row.put("conditionName", PubData.getDictName("GUARDRAIL_CONDITION", condition.toString()));		
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
	/**
	 * 查询行人易穿行部位信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo,String orgId) {
		// TODO 查询行人易穿行部位信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_part_trajection t where 1=1 ");
		String  quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.status as \"status\", " +
				"t.address as \"address\", " +
				"t.telephone as \"telephone\", " +
				"t.photos as \"photos\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.region as \"region\", " +
				"t.conditions as \"conditions\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_trajection t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";;

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询行人易穿行信息
		if (null != baseInfoHiddenTrajectionPo.getName()&& !baseInfoHiddenTrajectionPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoHiddenTrajectionPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoHiddenTrajectionPo.getName()).append("%'");
		}
		if (null != baseInfoHiddenTrajectionPo.getStatus()&& !baseInfoHiddenTrajectionPo.getStatus().equals("")) {
			sqlSb.append(" and t.status like :status");
			params.put("status", "%" + baseInfoHiddenTrajectionPo.getStatus()+ "%");
			sumSql.append(" and t.status like '%").append(baseInfoHiddenTrajectionPo.getStatus()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoHiddenTrajectionPo.getOrgId() && !baseInfoHiddenTrajectionPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoHiddenTrajectionPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoHiddenTrajectionPo.getOrgId()).append("%'");
		}
		//三公里范围内易穿行
		if (null != baseInfoHiddenTrajectionPo.getRailId()&& !baseInfoHiddenTrajectionPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_trajection_view where rail_id=:railId) ";
			params.put("railId",baseInfoHiddenTrajectionPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_trajection_view where rail_id= '").
			append(baseInfoHiddenTrajectionPo.getRailId()).append("') ");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		//组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//易穿行状态
				Object status=row.get("status");
				if(status!=null&&!"".equals(status.toString().trim())){
					row.put("statusName", PubData.getDictName("HIDDEN_TRAJECTION_STATUS", status.toString()));		
				}
				//易穿行状态
				Object condition=row.get("conditions");
				if(condition!=null&&!"".equals(condition.toString().trim())){
					row.put("conditionName", PubData.getDictName("GUARDRAIL_CONDITION", condition.toString()));		
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
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	/**
	 * 添加服务忌语
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public int addBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		if(StringUtils.isBlank(baseInfoHiddenTrajectionPo.getRegion())){
			baseInfoHiddenTrajectionPo.setRegion(new ArrayList<String>().toString());
		}
		// TODO 添加服务忌语
		String sql = "insert into base_info_part_trajection " +
				"(id, " +
				"name," +
				"status," +
				"address," +
				"telephone," +
				"photos," +
				"org_id," +
				"conditions," +
				"lng," +
				"lat," +
				"the_geom," +
				"region," +
				"remark" +
				")values( " +
				":id," +
				":name," +
				":status," +
				":address," +
				":telephone," +
				":photos," +
				":orgId," +
				":conditions," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":region," +
				":remark)";
		return util.editObject(sql, baseInfoHiddenTrajectionPo);
	}
	/**
	 * 更新行人易穿行部位信
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public int updateBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		// TODO 更新行人易穿行部位信息
		String sql = "update base_info_part_trajection set " +
				"name=:name," +
				"status=:status," +
				"address=:address," +
				"telephone=:telephone," +
				"photos=:photos," +
				"org_id=:orgId," +
				"region=:region," +
				"conditions=:conditions," +
				"lng=:lng," +
				"lat=:lat," +
				"the_geom=st_geomfromtext(:theGeom),"+
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoHiddenTrajectionPo);
	}
	/**
	 * 删除行人易穿行部位信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoHiddenTrajection(List<String> idList) {
		// TODO 删除行人易穿行部位信息
		String delSql = "delete from base_info_part_trajection where id=?";
		String delViewSql = "delete from base_info_part_trajection_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoHiddenTrajectionAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_trajection_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoHiddenTrajectionAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_trajection_view (id,name,lng,lat,the_geom,region,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
	
}
