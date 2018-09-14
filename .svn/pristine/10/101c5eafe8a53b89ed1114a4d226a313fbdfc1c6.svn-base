package com.pc.busniess.baseInfoPartJunction.dao;

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
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;
/**
 * 该Dao层是道口基本信息的相关逻辑处理
 * @author CaoLu
 * @version 1.0
 * @since
 *
 */
@Repository("baseInfoPartJunctionDao")
public class BaseInfoPartJunctionDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 根据id获取道口的详细信息
	 * @param id 道口ID
	 * @return 返回对应的道口的详细信息
	 * @author Caolu
	 */
	public Map<String, Object> findById(String id) {
		String sql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.is_height_limit as \"isHeightLimit\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.road_classify as \"roadClassify\", " +
				"t.train_num as \"trainNum\", " +
				"t.state as \"state\", " +
				"t.number as \"number\", " +
				"t.width as \"width\", " +
				"t.material as \"material\", " +
				"t.level as \"level\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_junction t " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//行别
		Object stream=row.get("stream");
		if(stream!=null&&!"".equals(stream.toString().trim())){
			row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));
		}
		//守护情况
		Object guardStatus=row.get("guardStatus");
		if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
			row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));
		}	
		//中心里程
		Object middle=row.get("middle");
		if(null!=middle&&!"".equals(middle.toString().trim())){
			row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
			String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
			row.put("middleKM", middleArr[0]);
			row.put("middleM", middleArr[1]);
		}
		//隶属铁路局(公司)
		Object railBureau=row.get("railBureau");
		if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
			row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));
		}
		//运营状态
		Object state=row.get("state");
		if(state!=null&&!"".equals(state.toString().trim())){
			row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));
		}
		//道路类别
		Object roadClassify=row.get("roadClassify");
		if(roadClassify!=null&&!"".equals(roadClassify.toString().trim())){
			row.put("roadClassifyName", PubData.getDictName("PART_JUNCTION_ROAD_CLASSIFY", roadClassify.toString()));
		}
		//铺面材料
		Object material=row.get("material");
		if(material!=null&&!"".equals(material.toString().trim())){
			row.put("materialName", PubData.getDictName("PART_JUNCTION_MATERIAL", material.toString()));
		}
		//道口等级
		Object level=row.get("level");
		if(level!=null&&!"".equals(level.toString().trim())){
			row.put("levelName", PubData.getDictName("PART_JUNCTION_LEVEL", level.toString()));
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
	 * 查询所有的铁路信息（参考铁路线时需要）
	 * @return 含有参考铁路线信息的List集合
	 */
	public List<Map<String, Object>> queryRails() {
		//TODO 查询所有的铁路信息（参考铁路线时需要）
		//统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\" " +
				"from base_info_rail t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	
	/**
	 * 查询道口基本信息
	 * @param dgm @see DataGridModel
	 * @param baseInfoPartJunctionPo @see BaseInfoPartJunctionPo
	 * @return 组合条件查询出带有分页的道口基本信息的Map集合
	 */
	public Map<String, Object> baseInfoPartJunctionQueryList(DataGridModel dgm, BaseInfoPartJunctionPo baseInfoPartJunctionPo,String orgId) {
		//TODO 查询道口基本信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_junction t where 1=1 ");
		//统计数据详情语句
	    quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\", " +
				"t.rail_id as \"railId\", " +
				"s.rail_name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.is_height_limit as \"isHeightLimit\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.road_classify as \"roadClassify\", " +
				"t.train_num as \"trainNum\", " +
				"t.state as \"state\", " +
				"t.number as \"number\", " +
				"t.width as \"width\", " +
				"t.material as \"material\", " +
				"t.level as \"level\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_junction t " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail_stream as s ON t.rail_id = s.id "+
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询道口信息
		if (null != baseInfoPartJunctionPo.getName()&& !baseInfoPartJunctionPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartJunctionPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartJunctionPo.getName()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPartJunctionPo.getOrgId() && !baseInfoPartJunctionPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPartJunctionPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPartJunctionPo.getOrgId()).append("%'");
		}
		//三公里范围内道口
		if (null != baseInfoPartJunctionPo.getRailId()&& !baseInfoPartJunctionPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_junction_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPartJunctionPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_junction_view where rail_stream_id= '").
			append(baseInfoPartJunctionPo.getRailId()).append("') ");
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
				//行别
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));
				}
				//守护情况
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));
				}	
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				//隶属铁路局(公司)
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));
				}
				//运营状态
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("OPERATION_STATE", state.toString()));
				}
				//道路类别
				Object roadClassify=row.get("roadClassify");
				if(roadClassify!=null&&!"".equals(roadClassify.toString().trim())){
					row.put("roadClassifyName", PubData.getDictName("PART_JUNCTION_ROAD_CLASSIFY", roadClassify.toString()));
				}
				//铺面材料
				Object material=row.get("material");
				if(material!=null&&!"".equals(material.toString().trim())){
					row.put("materialName", PubData.getDictName("PART_JUNCTION_MATERIAL", material.toString()));
				}
				//道口等级
				Object level=row.get("level");
				if(level!=null&&!"".equals(level.toString().trim())){
					row.put("levelName", PubData.getDictName("PART_JUNCTION_LEVEL", level.toString()));
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
	 * 添加道口基本信息
	 * @param baseInfoPartJunctionPo @see BaseInfoPartJunctionPo
	 * @return 是否操作成功
	 */
	public int addBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 添加道口基本信息
		//对中心里程的处理
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartJunctionPo.getMiddleKM(),baseInfoPartJunctionPo.getMiddleM());
		baseInfoPartJunctionPo.setMiddle(middle);
		
		String sql = "insert into base_info_part_junction " +
				"(id, " +
				"name," +
				"rail_id," +
				"middle," +
				"length," +
				"stream," +
				"guard_status," +
				"is_height_limit," +
				"telephone," +
				"org_id," +
				"rail_bureau," +
				"road_classify," +
				"train_num," +
				"state," +
				"number," +
				"width," +
				"material," +
				"level," +
				"lng," +
				"lat," +
				"the_geom," +
				"file_id," + 
				"remark" +
				")values( " +
				":id," +
				":name," +
				":railId," +
				":middle," +
				":length," +
				":stream," +
				":guardStatus," +
				":isHeightLimit," +
				":telephone," +
				":orgId," +
				":railBureau," +
				":roadClassify," +
				":trainNum," +
				":state," +
				":number," +
				":width," +
				":material," +
				":level," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":fileId," +
				":remark)";
		return util.editObject(sql, baseInfoPartJunctionPo);
	}
	
	/**
	 * 更新道口基本信息
	 * @param baseInfoPartJunctionPo @see BaseInfoPartJunctionPo
	 * @return 是否操作成功
	 */
	public int updateBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 更新道口基本信息
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartJunctionPo.getMiddleKM(),baseInfoPartJunctionPo.getMiddleM());
		baseInfoPartJunctionPo.setMiddle(middle);
		String sql = "update base_info_part_junction set " +
				"name=:name," +
				"rail_id=:railId," +
				"middle=:middle," +
				"length=:length," +
				"stream=:stream," +
				"guard_status=:guardStatus," +
				"is_height_limit=:isHeightLimit," +
				"telephone=:telephone," +
				"org_id=:orgId," +
				"rail_bureau=:railBureau," +
				"road_classify=:roadClassify," +
				"train_num=:trainNum," +
				"state=:state," +
				"number=:number," +
				"width=:width," +
				"material=:material," +
				"level=:level," +
				"lng=:lng," +
				"lat=:lat," +
				"the_geom=st_geomfromtext(:theGeom)," +
				"file_id=:fileId," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoPartJunctionPo);
	}
	
	/**
	 * 删除道口基本信息
	 * @param idList
	 * @return 是否操作成功
	 */
	public int[] deleteBaseInfoPartJunction(List<String> idList) {
		//TODO 删除道口基本信息
		String delSql = "delete from base_info_part_junction where id=?";
		String delViewSql = "delete from base_info_part_junction_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPartJunctionAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_junction_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPartJunctionAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_junction_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
