package com.pc.busniess.baseInfoPartTunnel.dao;

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
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;
/**
 * 该Dao层是隧道基本信息的相关逻辑处理
 * @author CaoLu
 * @version 1.0
 * @since
 */
@Repository("baseInfoPartTunnelDao")
public class BaseInfoPartTunnelDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 根据id获取隧道的详细信息
	 * @param id 隧道ID
	 * @return 返回对应的隧道的详细信息
	 * @author Caolu
	 */
	public Map<String, Object> findById(String id) {
		String sql ="select " +
					"t.id as \"id\", " +
					"t.number as \"number\", " +
					"t.name as \"name\", " +
					"t.rail_id as \"railId\", " +
					"c.name as \"railName\", " +
					"t.middle as \"middle\", " +
					"t.length as \"length\", " +
					"t.stream as \"stream\", " +
					"t.telephone as \"telephone\", " +
					"t.org_id as \"orgId\", " +
					"b.ORG_NAME as \"orgName\", " +
					"t.lng as \"lng\", " +
					"t.lat as \"lat\", " +
					"t.file_id as \"fileId\", " +
					"t.photos as \"photos\", " +
					"o.ORG_NAME as \"descOrgName\", " +
					"t.remark as \"remark\" " +
					"from base_info_part_tunnel t " +
					"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
					"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					"where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//行别
		Object stream=row.get("stream");
		if(stream!=null&&!"".equals(stream.toString().trim())){
			//将指定值放到对应键中
			row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));//行别
		}
		//守护情况
		Object guardStatus=row.get("guardStatus");
		if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
			row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));//守护情况
		}	
		//中心里程
		Object length=row.get("length");
		if(null!=length&&!"".equals(length.toString().trim())){
			row.put("lengthStr", CommonUtil.KM2MStrbyDec(length.toString()));
		}
		//中心里程
		Object middle=row.get("middle");
		if(null!=middle&&!"".equals(middle.toString().trim())){
			row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
			String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
			row.put("middleKM", middleArr[0]);
			row.put("middleM", middleArr[1]);
		}
		//隧道入口照片
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
		}
		//隧道出口照片
		Object file=row.get("photos");
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
	 * 查询隧道基本信息
	 * @param dgm @see DataGridModel
	 * @param baseInfoPartTunnelPo @see BaseInfoPartTunnelPo
	 * @return 组合条件查询出带有分页的道口基本信息的Map集合
	 */
	public Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo,String orgId) {
		//TODO 查询隧道基本信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_part_tunnel t where 1=1 ");
		//统计数据详情语句
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.rail_id as \"railId\", " +
				"s.rail_name as \"railName\", " +
				"t.middle as \"middle\", " +
				"t.length as \"length\", " +
				"t.stream as \"stream\", " +
				"t.telephone as \"telephone\", " +
				"t.org_id as \"orgId\", " +
				"b.ORG_NAME as \"orgName\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.file_id as \"fileId\", " +
				"t.photos as \"photos\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +
				"from base_info_part_tunnel t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"LEFT OUTER JOIN base_info_rail_stream as s ON t.rail_id = s.id "+
				"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询隧道信息
		if (null != baseInfoPartTunnelPo.getName()&& !baseInfoPartTunnelPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPartTunnelPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoPartTunnelPo.getName()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPartTunnelPo.getOrgId() && !baseInfoPartTunnelPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPartTunnelPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPartTunnelPo.getOrgId()).append("%'");
		}
		//三公里范围内车站
		if (null != baseInfoPartTunnelPo.getRailId()&& !baseInfoPartTunnelPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_part_tunnel_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPartTunnelPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_part_tunnel_view where rail_stream_id= '").
			append(baseInfoPartTunnelPo.getRailId()).append("') ");
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
					//将指定值放到对应键中
					row.put("streamName", PubData.getDictName("RAIL_STREAM", stream.toString()));//行别
				}
				//守护情况
				Object guardStatus=row.get("guardStatus");
				if(guardStatus!=null&&!"".equals(guardStatus.toString().trim())){
					row.put("guardStatusName", PubData.getDictName("GUARD_STATUS", guardStatus.toString()));//守护情况
				}	
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				//隧道入口照片
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
					row.put("fileName",fileName.toString());
					row.put("filePhotosName",filePhotosName.toString());
				}
				//隧道出口照片
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
	 * 添加隧道基本信息
	 * @param baseInfoPartTunnelPo @see BaseInfoPartTunnelPo
	 * @return 是否操作成功
	 */
	public int addBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 添加隧道基本信息
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartTunnelPo.getMiddleKM(),baseInfoPartTunnelPo.getMiddleM());
		baseInfoPartTunnelPo.setMiddle(middle);
		String sql = "insert into base_info_part_tunnel " +
				"(id, " +
				"number," +
				"name," +
				"rail_id," +
				"middle," +
				"length," +
				"stream," +
				"telephone," +
				"org_id," +
				"lng," +
				"lat," +
				"the_geom," +
				"file_id," +
				"photos," +
				"remark" +
				")values( " +
				":id," +
				":number," +
				":name," +
				":railId," +
				":middle," +
				":length," +
				":stream," +
				":telephone," +
				":orgId," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":fileId," +
				":photos," +
				":remark)";
		return util.editObject(sql, baseInfoPartTunnelPo);
	}
	
	/**
	 * 更新隧道基本信息
	 * @param baseInfoPartTunnelPo @see BaseInfoPartTunnelPo
	 * @return 是否操作成功
	 */
	public int updateBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 更新隧道基本信息 
		String middle=CommonUtil.KM2MStrbyDec(baseInfoPartTunnelPo.getMiddleKM(),baseInfoPartTunnelPo.getMiddleM());
		baseInfoPartTunnelPo.setMiddle(middle);
		String sql = "update base_info_part_tunnel set " +
				"number=:number," +
				"name=:name," +
				"rail_id=:railId," +
				"middle=:middle," +
				"length=:length," +
				"stream=:stream," +
				"telephone=:telephone," +
				"org_id=:orgId," +
				"lng=:lng," +
				"lat=:lat," +
				"the_geom=st_geomfromtext(:theGeom)," +
				"file_id=:fileId," +
				"photos=:photos," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, baseInfoPartTunnelPo);
	}
	
	/**
	 * 删除隧道基本信息
	 * @param idList
	 * @return 是否操作成功
	 */
	public int[] deleteBaseInfoPartTunnel(List<String> idList) {
		//TODO 删除隧道基本信息
		String delSql = "delete from base_info_part_tunnel where id=?";
		String delViewSql = "delete from base_info_part_tunnel_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPartTunnelAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_part_tunnel_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPartTunnelAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_part_tunnel_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
