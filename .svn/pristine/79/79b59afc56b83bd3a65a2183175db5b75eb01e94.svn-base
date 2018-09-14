package com.pc.busniess.baseInfoPeripheralPlace.dao;

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
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.dao 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:45:35
 */
@Repository("baseInfoPeripheralPlaceDao")
public class BaseInfoPeripheralPlaceDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 根据id获取<strong>周边场所</strong>的详细信息
	 * @param id 周边场所ID
	 * @return 返回对应的周边场所的详细信息
	 * @author 
	 */
	public Map<String, Object> findById(String id) {
		String sql ="select t.id \"id\", "
				+ "t.name \"name\", "
				+ "t.category \"category\", "
				+ "t.address \"address\", "
				+ "t.charger \"charger\", "
				+ "t.telephone \"telephone\", "
				+ "t.photos \"photos\", "
				+ "t.org_id \"orgId\", "
				+ "t.type \"type\", "
				+ "a.ORG_NAME as \"orgName\", "
				+ "t.lng \"lng\", "
				+ "t.lat \"lat\", "
				+"o.ORG_NAME as \"descOrgName\", " 
				+ "t.place_description \"description\", "
				+ "t.remark \"remark\" "
				+ "from base_info_peripheral_place t "
				+"LEFT OUTER JOIN pub_org AS a ON t.org_id = a.ORG_ID " 
				+"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " 
				+"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " 
				+ "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		//周边场所类别
		Object category=row.get("category");
		if(category!=null&&!"".equals(category.toString().trim())){
			row.put("peripheralName", PubData.getDictName("PERIPHERAL_PLACE", category.toString()));		
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
	 * 查询周边场所所有信息
	 * @param dgm
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	public Map<String, Object> baseInfoPeripheralPlaceQueryList(DataGridModel dgm,
			BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo, String orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sumSql = new StringBuffer();
		
		String quSql="";
		    sumSql.append("select count(1) from base_info_peripheral_place t where 1=1 ");
		 quSql = 
				"select t.id \"id\", "
				+ "t.name \"name\", "
				+ "t.category \"category\", "
				+ "t.address \"address\", "
				+ "t.charger \"charger\", "
				+ "t.telephone \"telephone\", "
				+ "t.photos \"photos\", "
				+ "t.org_id \"orgId\", "
				+ "t.type \"type\", "
				+ "a.ORG_NAME as \"orgName\", "
				+ "t.lng \"lng\", "
				+ "t.lat \"lat\", "
				+"o.ORG_NAME as \"descOrgName\", " 
				+ "t.place_description \"description\", "
				+ "t.remark \"remark\" "
				+ "from base_info_peripheral_place t "
				+"LEFT OUTER JOIN pub_org AS a ON t.org_id = a.ORG_ID " 
				+"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " 
				+"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " 
				+ "where 1=1 ";
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != baseInfoPeripheralPlacePo.getName()
				&& !baseInfoPeripheralPlacePo.equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoPeripheralPlacePo.getName() + "%");
			sumSql.append("and t.name like '%").append(baseInfoPeripheralPlacePo.getName()).append("%'");
		}
		if (null != baseInfoPeripheralPlacePo.getCategory()&& !baseInfoPeripheralPlacePo.equals("")) {
			sqlSb.append(" and t.category like :category");
			params.put("category", "%" + baseInfoPeripheralPlacePo.getCategory() + "%");
			sumSql.append("and t.category like '%").append(baseInfoPeripheralPlacePo.getCategory()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoPeripheralPlacePo.getOrgId() && !baseInfoPeripheralPlacePo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoPeripheralPlacePo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoPeripheralPlacePo.getOrgId()).append("%'");
		}
		//三公里范围内桥梁
		if (null != baseInfoPeripheralPlacePo.getRailId()&& !baseInfoPeripheralPlacePo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_peripheral_place_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoPeripheralPlacePo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_peripheral_place_view where rail_stream_id= '").
			append(baseInfoPeripheralPlacePo.getRailId()).append("') ");
		}
		String order = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			order = SqlUtil.getOrderbySql(dgm);
		}

		String sql = quSql + sqlSb.toString() + order;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//周边场所类别
				Object category=row.get("category");
				if(category!=null&&!"".equals(category.toString().trim())){
					row.put("peripheralName", PubData.getDictName("PERIPHERAL_PLACE", category.toString()));		
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
		map.put("total", util.getObjCount(sumSql.toString()));
		map.put("rows", rowsList);
		return map;

	}

	/**
	 * 增加周边场所信息
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	public int addBaseInfoPeripheralPlace(
			BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo) {
		String sql = "insert into "
				+ "base_info_peripheral_place "
				+ "(id,"
				+ "name,"
				+ "category,"
				+ "address,"
				+ "charger,"
				+ "telephone,"
				+ "photos,"
				+ "type,"
				+ "org_id,"
				+ "lng,"
				+ "lat,"
				+ "place_description,"
				+ "the_geom," 
				+ "remark"
				+ ") values ("
				+ ":id,"
				+ ":name,"
				+ ":category,"
				+ ":address,"
				+ ":charger,"
				+ ":telephone,"
				+ ":photos,"
				+ ":type,"
				+ ":orgId,"
				+ ":lng,"
				+ ":lat,"
				+ ":description,"
				+ "st_geomfromtext(:theGeom),"
				+ ":remark)";
		return util.editObject(sql, baseInfoPeripheralPlacePo);
	}
	/**
	 * 更新周边场所信息
	 * @param baseInfoPeripheralPlacePo @see BaseInfoPeripheralPlacePo
	 * @return 更新成功几条数据
	 */
	public int updateBaseInfoPeripheralPlace(
			BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo) {
		// TODO 更新周边场所
		String sql = "update base_info_peripheral_place set "
				+ "name=:name,"
				+ "category=:category,"
				+ "address=:address,"
				+ "charger=:charger,"
				+ "telephone=:telephone,"
				+ "photos=:photos,"
				+ "type=:type,"
				+ "org_id=:orgId,"
				+ "lng=:lng,"
				+ "lat=:lat,"
				+ "place_description=:description,"
				+" the_geom=st_geomfromtext(:theGeom)," 
				+ "remark=:remark "
				+ "where id=:id";
		return util.editObject(sql, baseInfoPeripheralPlacePo);
	}
	/**
	 * 删除周边场所信息
	 * @param list
	 * @return
	 */
	public int[] delBaseInfoPeripheralPlace(List<String> idList) {
		String delSql = "delete from base_info_peripheral_place where id=?";
		String delViewSql = "delete from base_info_peripheral_place_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoPeripheralPlaceAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_peripheral_place_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoPeripheralPlaceAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_peripheral_place_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id,category,type) values (?,?,?,?,st_geomfromtext(?),?,?,?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
