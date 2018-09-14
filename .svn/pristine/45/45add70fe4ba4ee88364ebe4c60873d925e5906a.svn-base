package com.pc.busniess.baseInfoDefencePropaganda.dao;

/**
 * @author lyf
 *
 */
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
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;
@Repository("BaseInfoDefencePropagandaDao")
public class BaseInfoDefencePropagandaDao{
	@Autowired
	private DBUtil util;
	
	/**
	 * 根据id获取<strong>护路宣传点位</strong>的详细信息
	 * @param id 护路宣传点位ID
	 * @return 返回对应的护路宣传点位的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		String sql = "select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.adress as \"adress\", " +
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.photos as \"photos\", " +
					   "t.org_id as \"orgId\", " +
					   "t.remark as \"remark\", " +
					   "t.construction_unit as \"constructionUnit\", " +
					   "t.content as \"content\", " +
					   "t.pro_type as \"proType\", " +
					   "t.pro_time as \"proTime\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "b.ORG_NAME as \"orgName\" " +
					   "from base_info_defence_propaganda t " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		Object proType=row.get("proType");
		if(proType!=null&&!"".equals(proType.toString().trim())){
			row.put("proTypeName", PubData.getDictName("DEFENCE_PRO_TYPE", proType.toString()));		
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
	
	
	public Map<String, Object> baseInfoDefencePropagandaQueryList(DataGridModel dgm, BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo,String orgId) {
		/**
		 * TODO
		 * 查询护路宣传点位信息
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
		    String quSql="";
			    sumSql.append("select count(1) from base_info_defence_propaganda t where 1=1 " );
		       quSql = "select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.adress as \"adress\", " +
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.photos as \"photos\", " +
					   "t.org_id as \"orgId\", " +
					   "t.remark as \"remark\", " +
					   "t.construction_unit as \"constructionUnit\", " +
					   "t.content as \"content\", " +
					   "t.pro_type as \"proType\", " +
					   "t.pro_time as \"proTime\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "b.ORG_NAME as \"orgName\" " +
					   "from base_info_defence_propaganda t " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
						"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 ";

		/**
		 * 组装查询条件
		 */
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		/**
		 * 按照名称护路宣传点位信息
		 */
		if (null != baseInfoDefencePropagandaPo.getName()&& !baseInfoDefencePropagandaPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefencePropagandaPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefencePropagandaPo.getName()).append("%'");
		}
		if (null != baseInfoDefencePropagandaPo.getAdress()&& !baseInfoDefencePropagandaPo.getAdress().equals("")) {
			sqlSb.append(" and t.adress like :adress");
			params.put("adress", "%" + baseInfoDefencePropagandaPo.getAdress()+ "%");
			sumSql.append(" and t.adress like '%").append(baseInfoDefencePropagandaPo.getAdress()).append("%'");
		}
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoDefencePropagandaPo.getOrgId() && !baseInfoDefencePropagandaPo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoDefencePropagandaPo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoDefencePropagandaPo.getOrgId()).append("%'");
		}
		//三公里范围内护路宣传点位
		if (null != baseInfoDefencePropagandaPo.getRailId()&& !baseInfoDefencePropagandaPo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_defence_propaganda_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoDefencePropagandaPo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_defence_propaganda_view where rail_stream_id= '").
			append(baseInfoDefencePropagandaPo.getRailId()).append("') ");
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
				Object proType=row.get("proType");
				if(proType!=null&&!"".equals(proType.toString().trim())){
					row.put("proTypeName", PubData.getDictName("DEFENCE_PRO_TYPE", proType.toString()));		
				}
				/**
				 * 记录文件列表
				 */
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
						designProfileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>" +
								tempFileList.get(j).get("FILE_NAME")+"</a>"+
								"</br>";
						photosName+=tempFileList.get(j).get("FILE_NAME");
					}
					row.put("profileName1",designProfileName.toString());
					row.put("photosName",photosName.toString());
				}
			}
		}
		/**
		 * 绑定查询结果('total'和'rows'名称不能修改)
		 */
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",rowsList);

		return result;
	}
	
	
	
	public int addBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		/**
		 * TODO
		 * 添加服务忌语
		 */
		String sql = "insert into base_info_defence_propaganda " +
					 "(id, " +
					 "number,"+
					 "name," +
					 "adress," +
					 "lng,"+
					 "lat,"+
					 "photos,"+
					 "org_id,"+
					 "pro_type,"+
					 "pro_time,"+
					 "construction_unit,"+
					 "content,"+
					 "the_geom,"+
					 "remark"+
					 ")values( " +
					 ":id," +
					 ":number," +
					 ":name," +
					 ":adress," +
					 ":lng," +
					 ":lat," +
					 ":photos,"+
					 ":orgId," +
					 ":proType," +
					 ":proTime," +
					 ":constructionUnit," +
					 ":content," +
					 "st_geomfromtext(:theGeom),"+
					 ":remark)";
		return util.editObject(sql, baseInfoDefencePropagandaPo);
	}
	public int updateBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		/**
		 * TODO
		 * 更新护路宣传点信息
		 */
		String sql = "update base_info_defence_propaganda set " +
					 "number=:number," +
					 "name=:name," +
					 "adress=:adress," +
					 "lng=:lng," +
					 "lat=:lat," +
					 "photos=:photos," +
					 "org_id=:orgId," +
					 "pro_type=:proType,"+
					 "pro_time=:proTime,"+
					 "construction_unit=:constructionUnit,"+
					 "content=:content,"+
					 "the_geom=st_geomfromtext(:theGeom),"+
					 "remark=:remark "+
					 "where id=:id";
		return util.editObject(sql, baseInfoDefencePropagandaPo);
	}
	public int[] deleteBaseInfoDefencePropaganda(List<String> idList) {
		/**
		 * TODO
		 * 删除护路宣传点信息
		 */
		String delSql = "delete from base_info_defence_propaganda where id=?";
		String delViewSql = "delete from base_info_defence_propaganda_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoDefencePropagandaAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_defence_propaganda_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoDefencePropagandaAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_defence_propaganda_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}