package com.pc.busniess.baseInfoPartBridge.dao;


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
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;

/**
 * 这个DAO主要是用来对数据库的操作
 * @author lyc
 * @version 1.0
 * @since 1.0 这个从1.0版本开始
 */
@Repository("baseInfoPartBridgeDao")
public class BaseInfoPartBridgeDao{
	
	@Autowired
	private DBUtil util;
	


	/**
	 * 根据id获取<strong>桥梁</strong>的详细信息
	 * @param id 桥梁ID
	 * @return 返回对应的桥梁的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		String sql ="select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.classify as \"classify\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.rail_id as \"railId\", " +
				"c.name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.is_shared as \"isShared\", " +  
				"t.purpose as \"purpose\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"t.cross_span as \"crossSpan\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_bridge t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//如果crossSpan不为null或者不为空,就放入List集合中
		Object crossSpan=row.get("crossSpan");
		if(crossSpan!=null&&!"".equals(crossSpan.toString().trim())){
			row.put("crossSpanName", PubData.getDictName("CROSSSPAN", crossSpan.toString()));		
		}
		//如果classify不为null或者不为空,就放入List集合中
		Object classify=row.get("classify");
		if(classify!=null&&!"".equals(classify.toString().trim())){
			row.put("classifyName", PubData.getDictName("PART_BRIDGE_CLASSIFY", classify.toString()));		
		}
		//如果stream不为null或者不为空,就放入List集合中
		Object stream=row.get("stream");
		if(stream!=null&&!"".equals(stream.toString().trim())){
			row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));		
		}
		//如果guardStatus不为null或者不为空,就放入List集合中
		Object guardStatus=row.get("guardStatus");
		if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
			row.put("railGuardStatus", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));		
		}
		//如果railBureau不为null或者不为空,就放入List集合中
		Object railBureau=row.get("railBureau");
		if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
			row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
		}
		//如果middle不为null或者不为空,就转换成千米、米的格式,再放入List集合中
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
	 * 查询所有的铁路信息（参考铁路线时需要）
	 * @return Map集合
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
	 * 该方法是对桥梁查询的操作
	 * @param dgm
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo DataGridModel
	 * @return result
	 */
	public Map<String, Object> baseInfoPartBridgeQueryList(DataGridModel dgm, BaseInfoPartBridgePo baseInfoPartBridgePo,String orgId) {
		// TODO 查询桥梁信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_bridge t where 1=1 ");
		//统计数据详情语句
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.classify as \"classify\", " +
				"t.rail_bureau as \"railBureau\", " +
				"t.rail_id as \"railId\", " +
				"s.rail_name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.is_shared as \"isShared\", " +  
				"t.purpose as \"purpose\", " +
				"t.guard_status as \"guardStatus\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"t.cross_span as \"crossSpan\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from base_info_part_bridge t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail_stream as s ON t.rail_id = s.id "+
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询桥梁信息
		if (null != baseInfoPartBridgePo.getName()&& !baseInfoPartBridgePo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartBridgePo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartBridgePo.getName()).append("%'");
		}
		
		//分类
		if (null != baseInfoPartBridgePo.getClassify() && !baseInfoPartBridgePo.getClassify().equals("")) {
			sqlSb.append(" and t.classify like :classify");
			params.put("classify", "%" + baseInfoPartBridgePo.getClassify()+ "%");
			sumSql.append(" and t.classify like '%").append(baseInfoPartBridgePo.getClassify()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPartBridgePo.getOrgId() && !baseInfoPartBridgePo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPartBridgePo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPartBridgePo.getOrgId()).append("%'");
		}
		//三公里范围内桥梁
		if (null != baseInfoPartBridgePo.getRailId()&& !baseInfoPartBridgePo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_bridge_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPartBridgePo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_bridge_view where rail_stream_id= '").
			append(baseInfoPartBridgePo.getRailId()).append("') ");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//循环遍历数据字典中的选项
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//如果classify不为null或者不为空,就放入List集合中
				Object classify=row.get("classify");
				if(classify!=null&&!"".equals(classify.toString().trim())){
					row.put("classifyName", PubData.getDictName("PART_BRIDGE_CLASSIFY", classify.toString()));		
				}
				//如果stream不为null或者不为空,就放入List集合中
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));		
				}
				//如果guardStatus不为null或者不为空,就放入List集合中
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("railGuardStatus", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));		
				}
				//如果railBureau不为null或者不为空,就放入List集合中
				Object railBureau=row.get("railBureau");
				if(railBureau!=null&&!"".equals(railBureau.toString().trim())){
					row.put("railBureauName", PubData.getDictName("RAIL_BUREAU", railBureau.toString()));		
				}
				//如果crossSpan不为null或者不为空,就放入List集合中
				Object crossSpan=row.get("crossSpan");
				if(crossSpan!=null&&!"".equals(crossSpan.toString().trim())){
					row.put("crossSpanName", PubData.getDictName("CROSSSPAN", crossSpan.toString()));		
				}
				//如果middle不为null或者不为空,就转换成千米、米的格式,再放入List集合中
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
	 * 新增桥梁信息
	 * @param baseInfoPartBridgePo
	 * @param objList 
	 * @see BaseInfoPartBridgePo 
	 * @return 增加的行数
	 */
	public int addBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo) {
		//得到计算后的公里标
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartBridgePo.getMiddleKM(),baseInfoPartBridgePo.getMiddleM());
		baseInfoPartBridgePo.setMiddle(middle);
		// TODO 添加服务忌语
		String sql = "insert into base_info_part_bridge" +
				"(id, " +
				"number," +
				"name," +
				"classify," +
				"rail_bureau," +
				"rail_id," +
				"middle," +
				"length," +
				"stream," +
				"is_shared," +
				"guard_status," +
				"telephone," +
				"purpose," +
				"org_id," +
				"lng," +
				"lat," +
				"the_geom," +
				"file_id," +
				"cross_span," +
				"is_oversize," +
				"remark" +
				")values( " +
				":id," +
				":number," +
				":name," +
				":classify," +
				":railBureau," +
				":railId," +
				":middle," +
				":length," +
				":stream," +
				":isShared," +
				":guardStatus," +
				":telephone," +
				":purpose," +
				":orgId," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom)," +
				":fileId," +
				":crossSpan," +
				":isOversize," +
				":remark)";
		return util.editObject(sql, baseInfoPartBridgePo);
	}
	/**
	 * 更新桥梁信息
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo 
	 * @return 更新的行数
	 */
	public int updateBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo) {
		//得到计算后的公里标
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartBridgePo.getMiddleKM(),baseInfoPartBridgePo.getMiddleM());
		baseInfoPartBridgePo.setMiddle(middle);
		// TODO 更新桥梁信息
		String sql = "update base_info_part_bridge set " +
				"number=:number," +
				"name=:name," +
				"classify=:classify," +
				"rail_bureau=:railBureau," +
				"rail_id=:railId," +
				"middle=:middle," +
				"length=:length," +
				"stream=:stream," +
				"is_shared=:isShared," +
				"guard_status=:guardStatus," +
				"telephone=:telephone," +
				"purpose=:purpose," +
				"org_id=:orgId," +
				"lng=:lng," +
				"lat=:lat," +
				"the_geom=st_geomfromtext(:theGeom)," +
				"file_id=:fileId," +
				"cross_span=:crossSpan," +
				"is_oversize=:isOversize," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoPartBridgePo);
	}
	/**
	 * 删除桥梁信息
	 * @param idList
	 * @see List<String>
	 * @return 删除的行数
	 */
	public int[] deleteBaseInfoPartBridge(List<String> idList) {
		// TODO 删除桥梁信息
		String delSql = "delete from base_info_part_bridge where id=?";
		String delViewSql = "delete from base_info_part_bridge_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPartBridgeAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_bridge_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPartBridgeAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_bridge_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
