package com.pc.busniess.baseInfoPartStation.dao;


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
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;
/**
 * 这个DAO主要是用来对数据库的操作
 * @author lyc
 * @version 1.0
 * @since 1.0 这个从1.0版本开始
 */
@Repository("baseInfoPartStationDao")
public class BaseInfoPartStationDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 根据id获取车站的详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id) {
		String sql = "select " +
					"t.id as \"id\", " +
					"t.name as \"name\", " +
					"t.number as \"number\", " +
					"t.level as \"level\", " +
					"t.nature as \"nature\", " +
					"t.is_highspeed as \"isHighspeed\", " +
					"t.rail_id as \"railId\", " +
					"c.name as \"railName\", " +
					"t.middle as \"middle\", " +
					"t.state as \"state\", " +
					"t.telephone as \"telephone\", " +
					"t.rail_bureau as \"railBureau\", " +
					"t.org_id as \"orgId\", " +
					"t.lng as \"lng\", " +
					"t.lat as \"lat\", " +
					"t.file_id as \"fileId\", " +
					"o.ORG_NAME as \"descOrgName\", " +
					"t.remark as \"remark\", " +
					"b.ORG_NAME as \"orgName\" " +
					"from base_info_part_station t " +
					"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
					"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//如果level不为null或者不为空，那么就放入list集合中
		Object level=row.get("level");
		if(level!=null&&!"".equals(level.toString().trim())){
			row.put("levelName", PubData.getDictName("PART_STATION_LEVEL", level.toString()));		
		}
		//如果nature不为null或者不为空，那么就放入list集合中
		Object nature=row.get("nature");
		if(nature!=null&&!"".equals(nature.toString().trim())){
			row.put("natureName", PubData.getDictName("PART_STATION_NATURE", nature.toString()));		
		}
		//如果railBureau不为null或者不为空，那么就放入list集合中
		Object railBureau=row.get("railBureau");
		if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
			row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
		}
		//如果state不为null或者不为空，那么就放入list集合中
		Object state=row.get("state");
		if(state!=null&&!"".equals(state.toString().trim())){
			row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));		
		}
		//如果middle不为null或者不为空，那么就换算成千米、米的格式,再放入List集合中
		Object middle=row.get("middle");
		if(null!=middle&&!"".equals(middle.toString().trim())){
			row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
			String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
			row.put("middleKM", middleArr[0]);
			row.put("middleM", middleArr[1]);
		}
		
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
	 * 查询所有的铁路信息（增加线路信息时需要）
	 * @return
	 */
	public List<Map<String, Object>> queryRails() {
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\" " +
				"from base_info_rail t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * 该方法是对车站查询的操作
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return result
	 */
	public Map<String, Object> baseInfoPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo,String orgId) {
		// TODO 查询车站信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_part_station t where 1=1 ");
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.number as \"number\", " +
				"t.level as \"level\", " +
				"t.nature as \"nature\", " +
				"t.is_highspeed as \"isHighspeed\", " +
				"t.rail_id as \"railId\", " +
				"s.rail_name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.state as \"state\", " +
				"t.telephone as \"telephone\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_station t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail_stream as s ON t.rail_id = s.id "+
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		//按照名称查询车站信息
		if (null != baseInfoPartStationPo.getName()&& !baseInfoPartStationPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartStationPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartStationPo.getName()).append("%'");
		}
		//按照等级查询车站信息
		if (null != baseInfoPartStationPo.getLevel()&& !baseInfoPartStationPo.getLevel().equals("")) {
			sqlSb.append(" and t.level like :level");
			params.put("level", "%" + baseInfoPartStationPo.getLevel()+ "%");
			sumSql.append(" and t.level like '%").append(baseInfoPartStationPo.getLevel()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPartStationPo.getOrgId() && !baseInfoPartStationPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPartStationPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPartStationPo.getOrgId()).append("%'");
		}
		//三公里范围内车站
		if (null != baseInfoPartStationPo.getRailId()&& !baseInfoPartStationPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_station_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPartStationPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_station_view where rail_stream_id= '").
			append(baseInfoPartStationPo.getRailId()).append("') ");
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
				//如果level不为null或者不为空，那么就放入list集合中
				Object level=row.get("level");
				if(level!=null&&!"".equals(level.toString().trim())){
					row.put("levelName", PubData.getDictName("PART_STATION_LEVEL", level.toString()));		
				}
				//如果nature不为null或者不为空，那么就放入list集合中
				Object nature=row.get("nature");
				if(nature!=null&&!"".equals(nature.toString().trim())){
					row.put("natureName", PubData.getDictName("PART_STATION_NATURE", nature.toString()));		
				}
				//如果railBureau不为null或者不为空，那么就放入list集合中
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
				}
				//如果state不为null或者不为空，那么就放入list集合中
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));		
				}
				//如果middle不为null或者不为空，那么就换算成千米、米的格式,再放入List集合中
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				
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
	 * 该方法是对增加车站信息的操作
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo 
	 * @return 增加的行数
	 */
	public int addBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo) {
		//得到计算后的公里标
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartStationPo.getMiddleKM(),baseInfoPartStationPo.getMiddleM());
		baseInfoPartStationPo.setMiddle(middle);
		// TODO 添加服务忌语
		String sql = "insert into base_info_part_station " +
				"(id, " +
				"name," +
				"number," +
				"level," +
				"nature," +
				"is_highspeed," +
				"rail_id," +
				"middle," +
				"state," +
				"rail_bureau," +
				"telephone," +
   				"org_id," +
				"lng," +
				"lat," +
				"the_geom," +
				"file_id," +
				"remark" +
				")values( " +
				":id, " +
				":name," +
				":number," +
				":level," +
				":nature," +
				":isHighspeed," +
				":railId," +
				":middle," +
				":state," +
				":railBureau," +
				":telephone," +
				":orgId," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":fileId," +
				":remark)";
		return util.editObject(sql, baseInfoPartStationPo);
	}
	/**
	 * 该方法是对更新车站信息的操作
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo 
	 * @return 更新的行数
	 */
	public int updateBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo) {
		//得到计算后的公里标
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartStationPo.getMiddleKM(),baseInfoPartStationPo.getMiddleM());
		baseInfoPartStationPo.setMiddle(middle);
		// TODO 更新车站信息
		String sql = "update base_info_part_station set " +
				"name=:name," +
				"number=:number," +
				"level=:level," +
				"nature=:nature," +
				"is_highspeed=:isHighspeed," +
				"rail_id=:railId," +
				"middle=:middle,"+
				"state=:state,"+
				"rail_bureau=:railBureau,"+
				"telephone=:telephone,"+
				"org_id=:orgId,"+
				"lng=:lng,"+
				"lat=:lat,"+
				"the_geom=st_geomfromtext(:theGeom),"+
				"file_id=:fileId," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoPartStationPo);
	}
	/**
	 * 该方法是对删除车站信息的操作
	 * @param idList
	 * @see List<String>
	 * @return 删除的行数
	 */
	public int[] deleteBaseInfoPartStation(List<String> idList) {
		// TODO 删除车站信息
		String delSql = "delete from base_info_part_station where id=?";
		String delViewSql = "delete from base_info_part_station_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPartStationAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_station_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPartStationAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_station_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
	
}
