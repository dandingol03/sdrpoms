/**
 * 
 */
package com.pc.bsp.menu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.menu.po.Menu;
import com.pc.bsp.menu.po.ResourceMenu;

/**
 * @author simple
 *
 */
@Repository("menuDao")
public class MenuDao{
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 获取菜单树
	 */
	public List<Map<String, Object>> getMenuTreeList(){
		String sql = "select " +
					 "menu_id as \"id\", " +
					 "menu_pid as \"pId\", " +
					 "concat(concat(concat(menu_name,'['),menu_desc),']') as \"name\", " +
					 "menu_url as \"title\", " +
					 "menu_desc as \"mdesc\", " +
					 "open_type as \"otype\" " +
					 "from pub_menu " +
					 "order by menu_desc,menu_id ";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{});
	}
	
	/**
	 * 获取注册资源树
	 * @return
	 */
	public List<Map<String, Object>> getResTreeList(){
		String sql = "select " +
					 "rm.resource_id as \"id\", " +
					 "rm.menu_id as \"pId\", " +
					 "r.resource_name as \"name\", " +
					 "'' as \"title\", " +
					 "'' as \"mdesc\", " +
					 "'' as \"otype\" " +
					 "from pub_resources_menus rm, pub_resources r " +
					 "where rm.resource_id = r.resource_id and r.is_sys = '1'";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{});
	}
	
	/**
	 * 根据用户权限集合获取菜单集合
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserMenuList(List<String> authString){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		String sql = "select " +
				"distinct(m.menu_id) as \"id\", " +
				"m.menu_desc as \"menuDesc\", " +
				"m.menu_pid as \"pId\", " +
				"m.menu_name as \"name\", " +
				"concat(concat(m.open_type, '_'), m.menu_url) as \"title\", " +
				"'' as \"url\" " +
				"from pub_menu m, " +
				"pub_resources_menus rm, " +
				"pub_authorities_resources ar, " +
				"pub_authorities a " +
				"where m.menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) " +
				"order by m.menu_desc, m.menu_id";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	
	/**
	 * 根据权限集合和第二级菜单Id查询菜单集合
	 * @param authString
	 * @param secondLevelMenuId
	 * @return
	 */
	public List<Map<String, Object>> getUserMenuListBySecondLevelMenu(List<String> authString,String secondLevelMenuId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		params.put("secondLevelMenuId", secondLevelMenuId+"%");
		String sql = "select " +
				"distinct(m.menu_id) as \"id\", " +
				"m.menu_desc as \"menuDesc\", " +
				"m.menu_pid as \"pId\", " +
				"m.menu_name as \"name\", " +
				"concat(concat(m.open_type, '_'), m.menu_url) as \"title\", " +
				"'' as \"url\" from pub_menu m, " +
				"pub_resources_menus rm, " +
				"pub_authorities_resources ar, " +
				"pub_authorities a " +
				"where m.menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) " +
				"and m.menu_pid like :secondLevelMenuId " +
				"order by m.menu_desc,m.menu_id ";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	/**
	 * 根据用户权限集合获取第二级菜单内容（第一级为菜单根节点）
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserSecondLevelMenuList(List<String> authString){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authString", authString);
		String sql = "select " +
				"menu_id as \"id\", " +
				"menu_desc as \"menuDesc\", " +
				"menu_pid as \"pId\", " +
				"menu_name as \"name\" " +
				"from pub_menu " +
				"where " +
				"menu_id in( select distinct substr(m.menu_id,1,4) as menu_id  " +
				"from pub_menu m, pub_resources_menus rm, pub_authorities_resources ar, pub_authorities a " +
				"where m.menu_id = rm.menu_id " +
				"and rm.resource_id = ar.resource_id " +
				"and ar.authority_id = a.authority_id " +
				"and a.authority_id in (:authString) ) " +
				"order by menu_desc, menu_id";
		return (List<Map<String, Object>>)util.getMapList(sql, params);
	}
	
	/**
	 * 获取父节点菜单
	 * @return
	 */
	public List<Map<String, Object>> getParentMenuList(String pId){
		String sql = "select " +
				"menu_id as \"id\", " +
				"menu_pid as \"pId\", " +
				"menu_name as \"name\" " +
				"from pub_menu " +
				"where menu_id = ?";
		return (List<Map<String, Object>>)util.getMapList(sql, new Object[]{pId});
	}
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	public int saveMenu(Menu menu){
		String sql = "insert into pub_menu (" +
				"menu_id, " +
				"menu_name, " +
				"menu_url, " +
				"menu_type, " +
				"menu_pid, " +
				"menu_desc, " +
				"open_type) " +
				"values (" +
				":menuId, " +
				":menuName, " +
				":menuUrl, " +
				":menuType, " +
				":menuPid, " +
				":menuDesc, " +
				":openType)";
		return util.editObject(sql, menu);
	}
	
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu){
		String sql = "update pub_menu set " +
				"menu_name = :menuName, " +
				"menu_url = :menuUrl, " +
				"menu_type = :menuType, " +
				"menu_desc = :menuDesc, " +
				"open_type = :openType " +
				"where menu_id = :menuId";
		return util.editObject(sql, menu);
	}
	
	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	public int delMenu(Menu menu){
		String sql = "delete from pub_menu where menu_id = :menuId";
		return util.editObject(sql, menu);
	}
	
	/**
	 * 删除[菜单-资源]关系
	 * @param resMenu
	 * @return
	 */
	public int delResMenu(ResourceMenu resMenu){
		String sql = "delete from pub_resources_menus where menu_id = :menuId";
		return util.editObject(sql, resMenu);
	}
	
	/**
	 * 查询子菜单的数量
	 * @param menuId
	 * @return
	 */
	public int getMenuCountByPid(String menuId){
		String sql = "select count(1) from pub_menu where menu_pid = '"+menuId+"'";
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
	public Map<String,Object> getReourceRelateMenuList(DataGridModel dgm,String menuId){
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_resources r,pub_resources_menus rm " +
				"where rm.resource_id=r.resource_id ");
		sumSql.append("and rm.menu_id='").append(menuId).append("'");
		
		String quSql = "select " +
				"rm.menu_id as \"menuId\", " +
				"r.resource_id as \"resourceId\", " +
				"r.resource_name as \"resourceName\", " +
				"r.resource_string as \"resourceString\", " +
				"r.resource_desc as \"resourceDesc\", " +
				"r.enable as \"enable\" " +
				"from pub_resources r,pub_resources_menus rm " +
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
