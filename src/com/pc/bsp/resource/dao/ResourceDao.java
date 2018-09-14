package com.pc.bsp.resource.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.resource.po.Resource;

/**
 * @author simple
 *
 */
@Repository("resourceDao")
public class ResourceDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 分页查询资源
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Resource resource){
		
		//查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_resources where 1=1");
		
		//获取结果集
		String quSql = "select " +
						"resource_id as \"uid\", " +
						"resource_id as \"resourceId\", " +
						"resource_name as \"resourceName\", " +
						"resource_type as \"resourceType\", " +
						"priority as \"priority\", " +
						"resource_string as \"resourceString\", " +
						"resource_desc as \"resourceDesc\", " +
						"enable as \"enable\", " +
						"is_sys as \"isSys\" " +
						"from pub_resources where 1=1";
		
		//组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String,Object>();
		
		
		//点击查询按钮时组装查询语句
		if(null != resource.getResourceName() && !resource.getResourceName().equals("")){
			sqlSb.append(" and resource_name like :resourceName");
			params.put("resourceName", "%"+resource.getResourceName()+"%");
			sumSql.append(" and resource_name like '%").append(resource.getResourceName()).append("%'");
		}
		if(null != resource.getResourceString() && !resource.getResourceString().equals("")){
			sqlSb.append(" and resource_string like :resourceString");
			params.put("resourceString", "%"+resource.getResourceString()+"%");
			sumSql.append(" and resource_string like '%").append(resource.getResourceString()).append("%'");
		}
		
		//组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		//组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		//绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));
			
		return result;
	}
	
	/**
	 * 保存资源
	 */
	public int saveResource(Resource resource){
		String sql = "insert into pub_resources " +
					"(resource_id, " +
					"resource_name, " +
					"resource_type, " +
					"priority, " +
					"resource_string, " +
					"resource_desc, " +
					"enable, " +
					"is_sys) " +
					"values " +
					"(:resourceId, " +
					":resourceName, " +
					":resourceType, " +
					":priority, " +
					":resourceString, " +
					":resourceDesc, " +
					":enable, " +
					":isSys)";
		return util.editObject(sql, resource);
	}
	
	/**
	 * 批量保存[资源-菜单]对应关系
	 * @param objList
	 * @return
	 */
	public int[] saveResMenu(List<Object[]> objList){
		String sql = "insert into pub_resources_menus (id, resource_id, menu_id) values (?, ?, ?)";
		return util.batchOperate(sql, objList);
	}
	
	/**
	 * 批量保存[资源-移动端菜单]对应关系
	 * @param objList
	 * @return
	 */
	public int[] saveResMobileMenu(List<Object[]> objList){
		String sql = "insert into pub_resources_menus_mobile (id, resource_id, menu_id) values (?, ?, ?)";
		return util.batchOperate(sql, objList);
	}
	
	/**
	 * 修改资源
	 */
	public int updateResource(Resource resource){
		String sql = "update pub_resources set " +
					"resource_name = :resourceName, " +
					"resource_type = :resourceType, " +
					"priority = :priority, " +
					"resource_string = :resourceString, " +
					"resource_desc = :resourceDesc, " +
					"enable = :enable " +
					"where resource_id = :resourceId";
		return util.editObject(sql, resource);
	}
	
	/**
	 * 批量更新资源注册状态
	 * @param objList
	 * @param status
	 * @return
	 */
	public int[] updateIsSysBatch(List<Object[]> objList, String status){
		String sql = "update pub_resources set is_sys = '"+status+"' where resource_id = ?";
		return util.batchOperate(sql, objList);
	}
	
	/**
	 * 删除菜单时更新已经注册到菜单的资源状态为'未注册'
	 * @param resMenu
	 * @return
	 */
	public int updateResIsSys(String menuId){
		String sql = "update pub_resources set " +
					"is_sys = '0' where resource_id in " +
					 "(select resource_id from pub_resources_menus where menu_id = '"+menuId+"')";
		return util.updateObject(sql);
	}
	
	/**
	 * 删除移动端菜单时更新已经注册到菜单的资源状态为'未注册'
	 * @param resMenu
	 * @return
	 */
	public int updateMobileResIsSys(String menuId){
		String sql = "update pub_resources set " +
					"is_sys = '0' where resource_id in " +
					 "(select resource_id from pub_resources_menus_mobile where menu_id = '"+menuId+"')";
		return util.updateObject(sql);
	}
	
	/**
	 * 批量删除资源
	 */
	public int[] delResourceBatch(List<String> idList){
		String sql = "delete from pub_resources where resource_id = ?";
		return util.batchDelete(sql, idList);
	}
	
	/**
	 * 批量删除[权限-资源]对应关系
	 * @param idList
	 * @return
	 */
	public int[] delAuthRes(List<String> idList){
		String sql = "delete from pub_authorities_resources where resource_id = ?";
		return util.batchDelete(sql, idList);
	}
	
	/**
	 * 批量删除[资源-菜单对应关系]
	 * @param idList
	 * @return
	 */
	public int[] delResMenu(List<String> idList){
		String sql = "delete from pub_resources_menus where resource_id = ?";
		return util.batchDelete(sql, idList);
	}
	
	/**
	 * 批量删除[资源-移动端菜单对应关系]
	 * @param idList
	 * @return
	 */
	public int[] delResMobileMenu(List<String> idList){
		String sql = "delete from pub_resources_menus_mobile where resource_id = ?";
		return util.batchDelete(sql, idList);
	}

}
