package com.mobile.bsp.menu.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.bsp.menu.po.MobileMenu;
import com.mobile.bsp.menu.po.ResourceMobileMenu;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;


/**
 * @author D.Steven
 *
 */
@Repository("mobileMenuDao")
public class MobileMenuDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 获取移动端菜单树
	 */
	public List<Map<String, Object>> getMobileMenuTreeList(){
		String sql = "select " +
					 "mobile_menu_id as \"id\", " +
					 "mobile_menu_pid as \"pId\", " +
					 "concat(concat(concat(mobile_menu_name,'['),mobile_menu_desc),']') as \"name\", " +
					 "mobile_menu_url as \"title\", " +
					 "mobile_menu_desc as \"mdesc\", " +
					 "mobile_open_type as \"otype\", " +
					 "mobile_menu_icon as \"micon\" " +
					 "from pub_menu_mobile " +
					 "order by mobile_menu_desc,mobile_menu_id ";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{});
	}
	
	/**
	 * 保存移动端菜单
	 * @param mobileMenu
	 * @return
	 */
	public int saveMobileMenu(MobileMenu mobileMenu){
		String sql = "insert into pub_menu_mobile (" +
				"mobile_menu_id, " +
				"mobile_menu_name, " +
				"mobile_menu_url, " +
				"mobile_menu_type, " +
				"mobile_menu_pid, " +
				"mobile_menu_desc, " +
				"mobile_open_type," +
				"mobile_menu_icon) " +
				"values (" +
				":mobileMenuId, " +
				":mobileMenuName, " +
				":mobileMenuUrl, " +
				":mobileMenuType, " +
				":mobileMenuPid, " +
				":mobileMenuDesc, " +
				":mobileOpenType," +
				":mobileMenuIcon)";
		return util.editObject(sql, mobileMenu);
	}
	
	/**
	 * 更新移动端菜单
	 * @param mobileMenu
	 * @return
	 */
	public int updateMobileMenu(MobileMenu mobileMenu){
		String sql = "update pub_menu_mobile set " +
				"mobile_menu_name = :mobileMenuName, " +
				"mobile_menu_url = :mobileMenuUrl, " +
				"mobile_menu_type = :mobileMenuType, " +
				"mobile_menu_desc = :mobileMenuDesc, " +
				"mobile_open_type = :mobileOpenType," +
				"mobile_menu_icon = :mobileMenuIcon " +
				"where mobile_menu_id = :mobileMenuId";
		return util.editObject(sql, mobileMenu);
	}
	
	/**
	 * 删除移动端菜单
	 * @param mobileMenu
	 * @return
	 */
	public int delMobileMenu(MobileMenu mobileMenu){
		String sql = "delete from pub_menu_mobile where mobile_menu_id = :mobileMenuId";
		return util.editObject(sql, mobileMenu);
	}
	
	/**
	 * 获取移动端注册资源树
	 * @return
	 */
	public List<Map<String, Object>> getMobileResTreeList(){
		String sql = "select " +
					 "rm.resource_id as \"id\", " +
					 "rm.menu_id as \"pId\", " +
					 "r.resource_name as \"name\", " +
					 "'' as \"title\", " +
					 "'' as \"mdesc\", " +
					 "'' as \"otype\" " +
					 "from pub_resources_menus_mobile rm, pub_resources r " +
					 "where rm.resource_id = r.resource_id and r.is_sys = '1'";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{});
	}
	
	/**
	 * 根据用户权限集合获取移动端菜单集合
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserMobileMenuList(List<String> authString){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		String sql = "select " +
				"distinct(m.mobile_menu_id) as \"id\", " +
				"m.mobile_menu_desc as \"menuDesc\", " +
				"m.mobile_menu_pid as \"pId\", " +
				"m.mobile_menu_name as \"name\", " +
				"concat(concat(m.mobile_open_type, '_'), m.mobile_menu_url) as \"title\", " +
				"'' as \"url\" " +
				"from pub_menu_mobile m, " +
				"pub_resources_menus_mobile rm, " +
				"pub_authorities_resources ar, " +
				"pub_authorities a " +
				"where m.mobile_menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) " +
				"order by m.mobile_menu_desc, m.mobile_menu_id";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	
	/**
	 * 根据权限集合和第二级菜单Id查询菜单集合
	 * @param authString
	 * @param secondLevelMenuId
	 * @return
	 */
	public List<Map<String, Object>> getUserMobileMenuListBySecondLevelMobileMenu(List<String> authString,String secondLevelMenuId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		params.put("secondLevelMenuId", secondLevelMenuId+"%");
		String sql = "select " +
				"distinct(m.mobile_menu_id) as \"id\", " +
				"m.mobile_menu_desc as \"menuDesc\", " +
				"m.mobile_menu_pid as \"pId\", " +
				"m.mobile_menu_name as \"name\", " +
				"concat(concat(m.mobile_open_type, '_'), m.mobile_menu_url) as \"title\", " +
				"'' as \"url\" from pub_menu_mobile m, " +
				"pub_resources_menus rm, " +
				"pub_authorities_resources ar, " +
				"pub_authorities a " +
				"where m.mobile_menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) " +
				"and m.mobile_menu_pid like :secondLevelMenuId " +
				"order by m.mobile_menu_desc,mobile_m.menu_id ";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	/**
	 * 根据用户权限集合获取第二级菜单内容（第一级为菜单根节点）
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserSecondLevelMobileMenuList(List<String> authString){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		String sql = "select " +
				"mobile_menu_id as \"id\", " +
				"mobile_menu_url as \"menuUrl\", " +
				"mobile_menu_icon as \"menuIcon\", " +
				"mobile_menu_desc as \"menuDesc\", " +
				"mobile_menu_pid as \"pId\", " +
				"mobile_menu_name as \"name\" " +
				"from pub_menu_mobile " +
				"where " +
				"mobile_menu_id in( select distinct substr(m.mobile_menu_id,1,4) as mobile_menu_id  " +
				"from pub_menu_mobile m, pub_resources_menus_mobile rm, pub_authorities_resources ar, pub_authorities a " +
				"where m.mobile_menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) ) " +
				"order by mobile_menu_desc, mobile_menu_id";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	
	/**
	 * 根据移动端菜单Id查询下一层菜单集合（根据用户权限-第一级为菜单根节点 并不进行查找）
	 * @param authString
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> getNextLevelUserMenuListByMenuId(List<String> authString,String menuId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		int idLength = menuId.length()+2;
		String sql = "select " +
				"mobile_menu_id as \"id\", " +
				"mobile_menu_url as \"menuUrl\", " +
				"mobile_menu_icon as \"menuIcon\", " +
				"mobile_menu_desc as \"menuDesc\", " +
				"mobile_menu_pid as \"pId\", " +
				"mobile_menu_name as \"name\" " +
				"from pub_menu_mobile " +
				"where mobile_menu_pid='"+menuId+"'"+
				"and mobile_menu_id in(select distinct substr(m.mobile_menu_id,1,"+idLength+") as mobile_menu_id  " +
				"from pub_menu_mobile m, pub_resources_menus_mobile rm, pub_authorities_resources ar, pub_authorities a " +
				"where m.mobile_menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) ) " +
				"order by mobile_menu_desc, mobile_menu_id";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	
	/**
	 * 获取父节点菜单
	 * @return
	 */
	public List<Map<String, Object>> getParentMobileMenuList(String pId){
		String sql = "select " +
				"mobile_menu_id as \"id\", " +
				"mobile_menu_pid as \"pId\", " +
				"mobile_menu_name as \"name\" " +
				"from pub_menu_mobile " +
				"where mobile_menu_id = ?";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{pId});
	}
	
	
	
	/**
	 * 删除[菜单-资源]关系
	 * @param resMenu
	 * @return
	 */
	public int delMobileResMenu(ResourceMobileMenu resMenu){
		String sql = "delete from pub_resources_menus_mobile where menu_id = :menuId";
		return util.editObject(sql, resMenu);
	}
	
	/**
	 * 查询子菜单的数量
	 * @param menuId
	 * @return
	 */
	public int getMobileMenuCountByPid(String menuId){
		String sql = "select count(1) from pub_menu_mobile where mobile_menu_pid = '"+menuId+"'";
		return util.getObjCount(sql);
	}
	
	/**
	 * 获取指定的属性值
	 * @param sql
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(String sql, Object[] obj){
		return (Map<String, Object>)util.getMap(sql, obj);
	}

	/**
	 * 根据菜单ID获得关联资源
	 * @param dgm
	 * @param menuId
	 * @return
	 */
	public Map<String,Object> getReourceRelateMobileMenuList(DataGridModel dgm,String menuId){
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_resources r,pub_resources_menus_mobile rm " +
				"where rm.resource_id=r.resource_id ");
		sumSql.append("and rm.menu_id='").append(menuId).append("'");
		
		String quSql = "select " +
				"rm.menu_id as \"menuId\", " +
				"r.resource_id as \"resourceId\", " +
				"r.resource_name as \"resourceName\", " +
				"r.resource_string as \"resourceString\", " +
				"r.resource_desc as \"resourceDesc\", " +
				"r.enable as \"enable\" " +
				"from pub_resources r,pub_resources_menus_mobile rm " +
				"where rm.resource_id=r.resource_id " +
				"and rm.menu_id=?";
		
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		List<Map<String, Object>> rowsList = util.getMapList(pageQuerySql, new Object[]{menuId});
		result.put("rows", rowsList);
		return result;
	}
}
