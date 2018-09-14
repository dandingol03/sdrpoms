package com.mobile.bsp.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.bsp.menu.dao.MobileMenuDao;
import com.mobile.bsp.menu.po.MobileMenu;
import com.mobile.bsp.menu.po.ResourceMobileMenu;
import com.pc.bsp.common.util.DaoException;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.resource.dao.ResourceDao;
import com.pc.bsp.resource.po.Resource;


/**
 * @author D.Steven
 *
 */
@Service("mobileMenuService")
public class MobileMenuService {

	@Autowired
	private MobileMenuDao mobielMenuDao;

	@Autowired
	private ResourceDao resourceDao;

	/**
	 * 获取移动端菜单树
	 */
	public List<Map<String, Object>> getMobileMenuTreeList() {
		return mobielMenuDao.getMobileMenuTreeList();
	}

	/**
	 * 获取（菜单+注册资源）树
	 */
	public List<Map<String, Object>> getMenuAndResTreeList() {
		List<Map<String, Object>> menuTree = mobielMenuDao.getMobileMenuTreeList();
		List<Map<String, Object>> resTree = mobielMenuDao.getMobileResTreeList();
		menuTree.addAll(resTree);
		return menuTree;
	}

	/**
	 * 根据登录用户权限集合获取菜单集合
	 * 
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserMobileMenuTreeList(List<String> authString) {
		// 定义返回的List
		List<Map<String, Object>> finalMenuList = new ArrayList<Map<String, Object>>();

		// 根据用户权限获取菜单集合
		List<Map<String, Object>> menuList = mobielMenuDao.getUserMobileMenuList(authString);

		// 对获取的菜单进行过滤（已排序），保留属于不同菜单目录的菜单的pid
		List<String> diffPidList = new ArrayList<String>();
		String tmpId = ((Map<String, Object>) menuList.get(0)).get("pId")
				.toString();
		diffPidList.add(tmpId);
		for (int i = 1; i < menuList.size(); i++) {
			Map<String, Object> menuMap = menuList.get(i);
			if (!menuMap.get("pId").toString().equals(tmpId)) {
				diffPidList.add(menuMap.get("pId").toString());
				tmpId = menuMap.get("pId").toString();
			}
		}

		// 根据过滤后的pid一级一级向上查找菜单目录，截止到根目录
		List<Map<String, Object>> midMenuList = new ArrayList<Map<String, Object>>();
		String newIdStr = "";
		for (int j = 0; j < diffPidList.size(); j++) {
			String pId = diffPidList.get(j);
			// System.out.println("++++>["+pId+"]");
			int count = pId.length() / 2;
			for (int k = count; k > 1; k--) {
				// 循环查询上级父节点
				String menuId = "00" + pId.substring(2, k * 2);
				// System.out.println("---->["+menuId+"]");
				// System.out.println("====>["+newIdStr+"]");
				if (!newIdStr.contains("[" + menuId + "]")) {
					midMenuList.addAll(mobielMenuDao.getParentMobileMenuList("00"
							+ pId.substring(2, k * 2)));
					newIdStr += "[" + menuId + "]";
				}
			}
		}

		// 单独查询根目录，然后依次添加目录和菜单
		finalMenuList.addAll(mobielMenuDao.getParentMobileMenuList("00"));
		finalMenuList.addAll(midMenuList);
		finalMenuList.addAll(menuList);

		return finalMenuList;
	}

	/**
	 * 根据权限集合和第二级菜单Id查询菜单集合
	 * @param authString
	 * @param secondLevelMenuId
	 * @return
	 */
	public List<Map<String, Object>> getUserMobileMenuListBySecondLevelMobileMenu(
			List<String> authString, String secondLevelMenuId) {
		
		// 定义返回的List
		List<Map<String, Object>> finalMenuList = new ArrayList<Map<String, Object>>();

		// 根据用户权限获取菜单集合
		List<Map<String, Object>> menuList = mobielMenuDao.getUserMobileMenuListBySecondLevelMobileMenu(authString,secondLevelMenuId);

		// 对获取的菜单进行过滤（已排序），保留属于不同菜单目录的菜单的pid
		List<String> diffPidList = new ArrayList<String>();
		
		for (int i = 0; i < menuList.size(); i++) {
			if (!menuList.get(i).get("pId").toString().equals(secondLevelMenuId)) {
				diffPidList.add(menuList.get(i).get("pId").toString());
			}
		}

		// 根据过滤后的pid一级一级向上查找菜单目录，截止到当前节点
		List<Map<String, Object>> midMenuList = new ArrayList<Map<String, Object>>();
		String newIdStr = "";
		for (int i = 0; i < diffPidList.size(); i++) {
			String pId = diffPidList.get(i);
			int count = pId.length() / 2;
			int pConut = secondLevelMenuId.length()/2;
			for (int k = count; k > pConut; k--) {
				// 循环查询上级父节点
				String menuId = pId.substring(0, k * 2);
				if (!newIdStr.contains("[" + menuId + "]")) {
					midMenuList.addAll(mobielMenuDao.getParentMobileMenuList(pId.substring(0, k * 2)));
					newIdStr += "[" + menuId + "]";
				}
			}
		}

		// 单独查询根目录，然后依次添加目录和菜单
		finalMenuList.addAll(midMenuList);
		finalMenuList.addAll(menuList);

		return finalMenuList;
	}

	/**
	 * 根据移动端菜单Id查询下一层菜单集合（根据用户权限-第一级为菜单根节点 并不进行查找）
	 * @param authString
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> getNextLevelUserMenuListByMenuId(
			List<String> authString, String menuId) {
		
		return mobielMenuDao.getNextLevelUserMenuListByMenuId(authString,menuId);
	}
	/**
	 * 根据用户权限集合获取第二级菜单内容（第一级为菜单根节点）
	 * 
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserSecondLevelMobileMenuList(
			List<String> authString) {
		return mobielMenuDao.getUserSecondLevelMobileMenuList(authString);
	}

	/**
	 * 保存菜单树
	 * 
	 * @param menu
	 * @return
	 */
	public int saveMobileMenu(MobileMenu mobileMenu) {
		return mobielMenuDao.saveMobileMenu(mobileMenu);
	}

	/**
	 * 更新移动端菜单树
	 * 
	 * @param mobileMenu
	 * @return
	 */
	public int updateMobileMenu(MobileMenu mobileMenu) {
		return mobielMenuDao.updateMobileMenu(mobileMenu);
	}

	/**
	 * 删除菜单及关联数据
	 * 
	 * @param menu
	 * @param resMenu
	 * @return
	 */
	public int delMobileMenus(MobileMenu mobileMenu, ResourceMobileMenu resMobileMenu) {
		// 更新已经注册到菜单的资源状态为'未注册'
		resourceDao.updateMobileResIsSys(mobileMenu.getMobileMenuId());
		// 删除[资源-菜单]对应关系
		mobielMenuDao.delMobileResMenu(resMobileMenu);
		return mobielMenuDao.delMobileMenu(mobileMenu);
	}

	/**
	 * 获取直接下级菜单节点中id最大的，返回其最后2位字符串的整形值
	 * 
	 * @param id
	 * @return
	 */
	public int getSubMenuMaxId(String id) {
		String sql = "select max(mobile_menu_id) as \"maxId\" from pub_menu_mobile where mobile_menu_pid = ?";
		Map<String, Object> idMap = (Map<String, Object>) mobielMenuDao.getMap(sql,
				new Object[] { id });
		int orgCount = 0;
		String maxId = (String) idMap.get("maxId");
		if (null != maxId) {
			orgCount = Integer.parseInt(maxId.substring(maxId.length() - 2));
		} else {
			orgCount = -1;
		}

		return orgCount;
	}

	/**
	 * 查询子菜单的数量
	 * 
	 * @param menuId
	 * @return
	 */
	public int getMobileMenuCountByPid(String menuId) {
		return mobielMenuDao.getMobileMenuCountByPid(menuId);
	}
	
	/**
	 * 根据菜单ID获得关联资源
	 * @param dgm
	 * @param menuId
	 * @return
	 */
	public Map<String,Object> getReourceRelateMobileMenuList(DataGridModel dgm,String menuId){
		return mobielMenuDao.getReourceRelateMobileMenuList(dgm,menuId);
	}
	
	/**
	 * 添加注册菜单资源
	 * @param re
	 * @param menuId
	 * @return
	 */
	public int addMobileMenuResourceRelate(Resource re,String menuId){
		try{
			String reId = NextID.getNextID("res");
			re.setResourceId(reId);
			re.setPriority("0");
			re.setIsSys("1");
			re.setEnable("1");
			if(resourceDao.saveResource(re)>0){
				List<Object[]> objList = new ArrayList<Object[]>();;
				Object[] obj = new Object[3];
				obj[0] = NextID.getUUID();
				obj[1] = reId;
				obj[2] = menuId;
				objList.add(obj);
				resourceDao.saveResMobileMenu(objList);
			}
			return 1; 
		}catch(Exception e){
			throw new DaoException("菜单页面添加资源失败",e);
		}
	}
	
}
