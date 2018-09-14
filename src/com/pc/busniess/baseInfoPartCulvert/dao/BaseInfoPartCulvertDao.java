package com.pc.busniess.baseInfoPartCulvert.dao;

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
import com.pc.busniess.baseInfoPartCulvert.po.BaseInfoPartCulvertPo;

/**
 * 该dao主要封装涵洞铁路基本信息的增删改查，对其对应的数据库操作
 * @author SSY
 * @version 1.0
 * @since
 */
@Repository("baseInfoPartCulvertDao")
public class BaseInfoPartCulvertDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 根据id获取<strong>涵洞</strong>的详细信息
	 * @param id 涵洞ID
	 * @return 返回对应的涵洞的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		String sql = "select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.classify as \"classify\", " +
					   "t.rail_id as \"railId\", " +
					   "t.name as \"railName\", " +							//铁路名称
					   "t.middle as \"middle\", " +
					   "t.length as \"length\", " +
					   "t.width as \"width\", " +
					   "t.height as \"height\", " +
					   "t.inradium as \"inradium\", " +
					   "t.culvert_function as \"culvertFunction\", " +
					   "t.is_seeper as \"isSeeper\", " +
					   "t.stream as \"stream\", " +
					   "t.guard_status as \"guardStatus\", " +
					   "t.telephone as \"telephone\", " +
					   "t.org_id as \"orgId\", " +
					   "b.ORG_NAME as \"orgName\", " +						//所属机构名称
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.cross_span as \"crossSpan\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "t.file_id as \"fileId\", " +
					   "t.remark as \"remark\" " +
				       "from base_info_part_culvert t " +
					   "LEFT OUTER JOIN base_info_rail AS r ON r.id = t.rail_id " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		// 分类
		Object classify=row.get("classify");
		if(classify!=null&&!"".equals(classify.toString().trim())){
			row.put("classifyName", PubData.getDictName("PART_CULVERT_CLASSIFY", classify.toString()));		
		}
		
		// 行别
		Object stream=row.get("stream");
		if(stream!=null&&!"".equals(stream.toString().trim())){
			row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));		
		}
	
		// 守护情况
		Object guardStatus=row.get("guardStatus");
		if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
			row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));		
		}
		
		//穿跨形式
		Object crossSpan=row.get("crossSpan");
		if(crossSpan!=null&&!"".equals(crossSpan.toString().trim())){
			row.put("crossSpanName", PubData.getDictName("CROSSSPAN", crossSpan.toString()));		
		}
		
		// 中心里程
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
	 * 查询所有的涵洞基本信息（参考涵洞基本线时需要）
	 * @return 返回查询到的铁路结果集
	 */
	public List<Map<String, Object>> queryRails() {
		// TODO 查询所有的涵洞基本信息（参考涵洞基本线时需要）
		// 统计数据详情语句
		String quSql = "select " +
				"t.id as \"id\", " +
				"t.name as \"name\" " +
				"from base_info_rail t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	
	/**
	 * 查询涵洞基本信息
	 * @param dgm DataGird表单查询结构体 @see DataGridModel
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @return 结果map 分为total 总数 rows 类型为ArrayList<Map<String,Object>的队列， 其中map结构为PO的索引
	 */
	public Map<String, Object> baseInfoPartCulvertQueryList(DataGridModel dgm, BaseInfoPartCulvertPo baseInfoPartCulvertPo,String orgId) {
		// TODO 查询涵洞基本信息
		
		// 结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		// 统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_culvert t where 1=1 ");
		// 统计数据详情语句
	           quSql = "select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.classify as \"classify\", " +
					   "t.rail_id as \"railId\", " +
					   "s.rail_name as \"railName\", " +						//铁路名称
					   "t.middle as \"middle\", " +
					   "t.length as \"length\", " +
					   "t.width as \"width\", " +
					   "t.height as \"height\", " +
					   "t.inradium as \"inradium\", " +
					   "t.culvert_function as \"culvertFunction\", " +
					   "t.is_seeper as \"isSeeper\", " +
					   "t.stream as \"stream\", " +
					   "t.guard_status as \"guardStatus\", " +
					   "t.telephone as \"telephone\", " +
					   "t.org_id as \"orgId\", " +
					   "b.ORG_NAME as \"orgName\", " +						//所属机构名称
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.cross_span as \"crossSpan\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "t.file_id as \"fileId\", " +
					   "t.remark as \"remark\" " +
				       "from base_info_part_culvert t " +
					   "LEFT OUTER JOIN base_info_rail AS r ON r.id = t.rail_id " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
						"LEFT OUTER JOIN base_info_rail_stream as s ON t.rail_id = s.id "+
					   "where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		// 按照名称查询涵洞基本信息
		if (null != baseInfoPartCulvertPo.getName()&& !baseInfoPartCulvertPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartCulvertPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartCulvertPo.getName()).append("%'");
		}
		
		// 分类
		if (null != baseInfoPartCulvertPo.getClassify() && !baseInfoPartCulvertPo.getClassify().equals("")) {
			sqlSb.append(" and t.classify like :classify");
			params.put("classify", "%" + baseInfoPartCulvertPo.getClassify()+ "%");
			sumSql.append(" and t.classify like '%").append(baseInfoPartCulvertPo.getClassify()).append("%'");
		}  
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPartCulvertPo.getOrgId() && !baseInfoPartCulvertPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPartCulvertPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPartCulvertPo.getOrgId()).append("%'");
		}
		//三公里范围内道口
		if (null != baseInfoPartCulvertPo.getRailId()&& !baseInfoPartCulvertPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_culvert_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPartCulvertPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_culvert_view where rail_stream_id= '").
			append(baseInfoPartCulvertPo.getRailId()).append("') ");
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
				
				// 分类
				Object classify=row.get("classify");
				if(classify!=null&&!"".equals(classify.toString().trim())){
					row.put("classifyName", PubData.getDictName("PART_CULVERT_CLASSIFY", classify.toString()));		
				}
				
				// 行别
				Object stream=row.get("stream");
				if(stream!=null&&!"".equals(stream.toString().trim())){
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));		
				}
			
				// 守护情况
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));		
				}
				
				//穿跨形式
				Object crossSpan=row.get("crossSpan");
				if(crossSpan!=null&&!"".equals(crossSpan.toString().trim())){
					row.put("crossSpanName", PubData.getDictName("CROSSSPAN", crossSpan.toString()));		
				}
				
				// 中心里程
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
	 * 添加涵洞基本信息
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @return 
	 */
	public int addBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 添加涵洞基本信息 
		
		// 中心里程
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartCulvertPo.getMiddleKM(),baseInfoPartCulvertPo.getMiddleM());
		baseInfoPartCulvertPo.setMiddle(middle);
		
		String sql = "insert into base_info_part_culvert " +
					 "(id, " +
					 "number," +
					 "name," +
					 "classify," +
					 "rail_id," +
					 "middle," +
					 "length," +
					 "width," +
					 "height," +
					 "inradium," +
					 "culvert_function," +
					 "is_seeper," +
					 "stream," +
					 "guard_status," +
					 "telephone," +
					 "org_id," +
					 "lng," +
					 "lat," +
					 "the_geom,"+
					 "file_id," +
					 "cross_span," +
					 "remark" +
					 ")values( " +
					 ":id," +
					 ":number," +
					 ":name," +
					 ":classify," +
					 ":railId," +
					 ":middle," +
					 ":length," +
					 ":width," +
					 ":height," +
					 ":inradium," +
					 ":culvertFunction," +
					 ":isSeeper," +
					 ":stream," +
					 ":guardStatus," +
					 ":telephone," +
					 ":orgId," +
					 ":lng," +
					 ":lat," +
					 "st_geomfromtext(:theGeom),"+
					 ":fileId," +
					 ":crossSpan," +
					 ":remark)";
		return util.editObject(sql, baseInfoPartCulvertPo);
	}
	/**
	 * 更新涵洞基本信息
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @return
	 */
	public int updateBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 更新涵洞基本信息 
		//中心里程
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartCulvertPo.getMiddleKM(),baseInfoPartCulvertPo.getMiddleM());
		baseInfoPartCulvertPo.setMiddle(middle);
		
		String sql = "update base_info_part_culvert set " +
					 "number=:number," +
					 "name=:name," +
					 "classify=:classify," +
					 "rail_id=:railId," +
					 "middle=:middle," +
					 "length=:length," +
					 "width=:width," +
					 "height=:height," +
					 "inradium=:inradium," +
					 "culvert_function=:culvertFunction," +
					 "is_seeper=:isSeeper," +
					 "stream=:stream," +
					 "guard_status=:guardStatus," +
					 "telephone=:telephone," +
					 "org_id=:orgId," +
					 "lng=:lng," +
					 "lat=:lat," +
					 "the_geom=st_geomfromtext(:theGeom)," +
					 "file_id=:fileId," +
					 "cross_span=:crossSpan," +
					 "remark=:remark "+
					 "where id=:id";
		return util.editObject(sql, baseInfoPartCulvertPo);
	}
	
	/**
	 * 删除涵洞基本信息  及关联关系
	 * @param idList 批量删除集合id
	 * @return
	 */
	public int[] deleteBaseInfoPartCulvert(List<String> idList) {
		// TODO 删除涵洞基本信息
		String delSql = "delete from base_info_part_culvert where id=?";
		String delViewSql = "delete from base_info_part_culvert_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPartCulvertAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_culvert_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPartCulvertAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_culvert_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
