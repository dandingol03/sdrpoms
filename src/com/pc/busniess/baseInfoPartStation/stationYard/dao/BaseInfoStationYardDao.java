package com.pc.busniess.baseInfoPartStation.stationYard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoPartStation.stationYard.po.BaseInfoStationYardPo;
/**
 * 
* @author jwl E-mail:1183011789@qq.com
* @version 创建时间：2017年12月25日 上午10:24:15
* @version 1.0 
* @since 1.0
* 类说明
 */
@Repository("baseInfoStationYardDao")
public class BaseInfoStationYardDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 根据id获取站场的详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id) {
		/**
		 * 修改数据内容  战场的数据从车站取
		 * 03-26-18
		 */
		String sql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.org_id as \"orgId\"," +
				"t.file_id as \"fileId\"," +
				"t.remark as \"remark\"," +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_station_yard_rel t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return util.getMapObject(sql, paramMap);
	}
	/**
	 * 该方法是对站场查询的操作
	 * @param dgm
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo DataGridModel
	 * @return result
	 */
	public Map<String, Object> baseInfoStationYardQueryList(DataGridModel dgm, BaseInfoStationYardPo baseInfoStationYardPo,String orgId) {
		// TODO 查询站场信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_station_yard_rel t where 1=1 ");
		    quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.org_id as \"orgId\"," +
				"t.file_id as \"fileId\"," +
				"t.remark as \"remark\"," +
				"o.ORG_NAME as \"descOrgName\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_station_yard_rel t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 and t.station_id=:stationId ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null !=baseInfoStationYardPo.getStationId()&& !baseInfoStationYardPo.getStationId().equals("")) {
			params.put("stationId",baseInfoStationYardPo.getStationId());
			sumSql.append(" and t.station_id = '"+baseInfoStationYardPo.getStationId()+"'");
		}
		//按照名称查询车站信息
		if (null != baseInfoStationYardPo.getName()&& !baseInfoStationYardPo.getName().equals("")) {
		  sqlSb.append(" and t.name like :name");
		  params.put("name", "%" + baseInfoStationYardPo.getName()+ "%");
		  sumSql.append(" and t.name like '%").append(baseInfoStationYardPo.getName()).append("%'");
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
				//记录文件列表
				Object tempProfile=row.get("fileId");
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
	 * 该方法是对增加站场信息的操作
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo 
	 * @return 增加的行数
	 */
	public int addBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		// TODO 添加服务忌语
		String sql = "insert into base_info_part_station_yard_rel " +
				"(id," +
				"name," +
				"lng,"+
				"lat,"+
				"org_id,"+
				"file_id,"+
				"station_id,"+
				"the_geom,"+
				"remark" +
				")values( " +
				":id," +
				":name," +
				":lng,"+
				":lat,"+
				":orgId,"+
				":fileId,"+
				":stationId,"+
				"st_geomfromtext(:theGeom),"+
				":remark)";
		return util.editObject(sql, baseInfoStationYardPo);
	}
	/**
	 * 该方法是对更新站场信息的操作
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo 
	 * @return 更新的行数
	 */
	public int updateBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		// TODO 更新站场信息
		String sql = "update base_info_part_station_yard_rel set " +
				"name=:name,"+
				"lng=:lng,"+
				"lat=:lat,"+
				"lat=:lat,"+
				"org_id=:orgId,"+
				"file_id=:fileId,"+
				"the_geom=st_geomfromtext(:theGeom),"+
				"station_id=:stationId,"+
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoStationYardPo);
	}
	/**
	 * 该方法是对删除站场信息的操作
	 * @param idList
	 * @see List<String>
	 * @return 删除的行数
	 */
	public int[] deleteBaseInfoStationYard(List<String> idList) {
		// TODO 删除站场信息
		String delSql = "delete from base_info_part_station_yard_rel where id=?";
		return util.batchDelete(delSql, idList);
	}
	
}
