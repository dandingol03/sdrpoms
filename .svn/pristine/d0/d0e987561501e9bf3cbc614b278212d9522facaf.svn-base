/**
 * 
 */
package com.pc.bsp.menu.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.menu.po.Menu;
import com.pc.bsp.menu.po.ResourceMenu;
import com.pc.bsp.menu.service.MenuService;
import com.pc.bsp.org.util.GenOrgDescID;
import com.pc.bsp.resource.po.Resource;
import com.pc.bsp.resource.service.ResourceService;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Controller
public class MenuController {
	
	private static Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;
	@Autowired
	private ResourceService resourceService;
	/**
	 * 通过菜单进入菜单管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menu",method=RequestMethod.GET)
    public String list(Model model){
        return "pc/bsp/menu/menu";
    }
	
	/**
	 * 获取系统菜单树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenu")
	@ResponseBody
    public List<Map<String, Object>> getMenu(){
        return menuService.getMenuTreeList();
    }
	
	/**
	 * 获取（菜单+注册资源）树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenuAndRes")
	@ResponseBody
    public List<Map<String, Object>> getMenuAndRres(){
        return menuService.getMenuAndResTreeList();
    }
	
	/**
	 * 获取当前登录用户的菜单树（根据用户权限）
	 * @return
	 */
	@RequestMapping(value="/menu/getUserMenu")
	@ResponseBody
    public List<Map<String, Object>> getUserMenuTree(){
		
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			List<String> authString = new ArrayList<String>();
			for(GrantedAuthority auth: auths){
				authString.add(auth.getAuthority());
			}
			
			menuList = menuService.getUserMenuTreeList(authString);
		}
		return menuList;
    }
	/**
	 * 获取当前登录用户的第二级菜单树（根据用户权限-第一级为菜单根节点）
	 * @return
	 */
	@RequestMapping(value="/menu/getUserSecondLevelMenu")
	@ResponseBody
    public List<Map<String, Object>> getUserSecondLevelMenuList(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			List<String> authString = new ArrayList<String>();
			for(GrantedAuthority auth: auths){
				authString.add(auth.getAuthority());
			}
			menuList = menuService.getUserSecondLevelMenuList(authString);
		}
		System.out.println(menuList);
		return menuList;
    }
	
	/**
	 * 获取当前登录用户的第二级菜单树（根据用户权限-第一级为菜单根节点）
	 * @return
	 */
	@RequestMapping(value="exhibition/menu/getUserSecondLevelMenu")
	@ResponseBody
    public List<Map<String, Object>> getUserExhibitionSecondLevelMenuList(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			List<String> authString = new ArrayList<String>();
			for(GrantedAuthority auth: auths){
				authString.add(auth.getAuthority());
			}
			menuList = menuService.getUserSecondLevelMenuList(authString);
		}
		return menuList;
    }
	
	/**
	 * 根据第二级菜单Id查询菜单集合（根据用户权限-第一级为菜单根节点）
	 * @return
	 */
	@RequestMapping(value="/menu/getUserMenuListBySecondLevelMenu")
	@ResponseBody
    public List<Map<String, Object>> getUserMenuListBySecondLevelMenu(
    		@RequestParam("secondLevelMenuId") String secondLevelMenuId){
		
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			List<String> authString = new ArrayList<String>();
			for(GrantedAuthority auth: auths){
				authString.add(auth.getAuthority());
			}
			
			menuList = menuService.getUserMenuListBySecondLevelMenu(authString,secondLevelMenuId);
			
		}
		return menuList;
    }
	
	/**跳转菜单添加页面
	 * 
	 */
	@RequestMapping(value="/menu/addMenuPopWin",method=RequestMethod.GET)
	public String addMenuPopWin(){
		return "pc/bsp/menu/addMenuPopWin";
	}
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/addMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveMenu(Menu menu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			//根据父节点和最大子节点计算菜单编号
			menu.setMenuId(GenOrgDescID.get2NewId(menu.getMenuPid(),
					menuService.getSubMenuMaxId(menu.getMenuPid())));
			String menuType = "0";	//'0'为菜单
			if(menu.getMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			menu.setMenuType(menuType);
			
			if(menuService.saveMenu(menu) > 0){
				map.put("mes", "添加菜单成功");
			}else{
				map.put("mes", "添加菜单失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("添加菜单失败",e);
			}
			map.put("mes", "添加菜单失败");
		}
		return map;//重定向
	}
	
	/**跳转菜单更新页面
	 * 
	 */
	@RequestMapping(value="/menu/updateMenuPopWin",method=RequestMethod.GET)
	public String updateMenuPopWin(){
		return "pc/bsp/menu/updateMenuPopWin";
	}
	
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/updateMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateMenu(Menu menu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			String menuType = "0";	//'0'为菜单
			if(menu.getMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			menu.setMenuType(menuType);
			if(menuService.updateMenu(menu) > 0){
				map.put("mes", "修改菜单成功");
			}else{
				map.put("mes", "修改菜单失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改菜单失败",e);
			}
			map.put("mes", "修改菜单失败");
		}
		return map;//重定向
	}
	
	/**
	 * 删除菜单及关联数据
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/menu/delMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delMenus(@RequestParam("menuId") String menuId){
		Map<String, String> map = new HashMap<String, String>();
		try {
			ResourceMenu resMenu = new ResourceMenu();
			Menu menu = new Menu();
			resMenu.setMenuId(menuId);
			menu.setMenuId(menuId);
			
			int result = 0;
			if(menuService.getMenuCountByPid(menuId) > 0){
				map.put("mes", "无法删除，请先删除其下级菜单");
				return map;
			}else{
				result = menuService.delMenus(menu, resMenu);
				if(result > 0){
					map.put("mes", "菜单删除成功");
				}else{
					map.put("mes", "菜单删除失败");
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("菜单删除失败",e);
			}
			map.put("mes", "菜单删除失败");
		}
		return map;//重定向
	}

	/**
	 * 通过菜单ID获得所有相关联的资源
	 * @param dgm
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/menu/queryMenuManageResList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMenuManageResList(DataGridModel dgm,
			@RequestParam(value="menuId",required = false) String menuId) {
		if(menuId!=null){
			return menuService.getReourceRelateMenuList(dgm,menuId);
		}
		return null;
	}
	
	/**
	 * 跳转到添加（关联菜单资源）页面
	 * @return
	 */
	@RequestMapping(value = "/menu/addMenuResourcePopWin", method = RequestMethod.GET)
	public String addMenuResourcePopWin() {
		return "pc/bsp/menu/addMenuResourcePopWin";
	}
	
	/**
	 * 添加关联菜单的资源
	 * @param re
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/menu/addMenuResourceRelate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addMenuResourceRelate(Resource re,
			@RequestParam("menuId") String menuId) {
		Map<String, String> map = new HashMap<String, String>();
		try{
			if(menuService.addMenuResourceRelate(re,menuId)>0){
				map.put("mes", "资源添加成功!");
			}else{
				map.put("mes", "资源添加失败!");
			}
		}catch(Exception e){
			map.put("mes", "资源添加失败!");
		}
		return map;
	}
	
	/**
	 * 添加到修改（关联菜单资源）页面
	 * @return
	 */
	@RequestMapping(value = "/menu/updateMenuResourcePopWin", method = RequestMethod.GET)
	public String updateMenuResourcePopWin() {
		return "pc/bsp/menu/updateMenuResourcePopWin";
	}
	
	/**
	 * 修改关联菜单的资源
	 * @param re
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/menu/updateMenuResourceRelate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateMenuResourceRelate(Resource re) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			re.setIsSys("1");
			re.setPriority("0");
			if(resourceService.updateResource(re) > 0){
				map.put("mes", "更新资源成功");
			}else{
				map.put("mes", "更新资源失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("更新资源失败",e);
			}
			map.put("mes", "更新资源失败");
		}
		return map; 
	}
	
	
	/**
	 * 批量删除资源
	 * @param menuIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menu/delRelateMenuResources",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delRelateMenuResources(
			@RequestParam("resourceId") List<String> resourceIdList){
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = resourceService.delResourceBatch(resourceIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == resourceIdList.size()){
				map.put("mes", "删除成功["+sum+"]条资源记录");
			}else{
				map.put("mes", "删除资源失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除资源失败",e);
			}
			map.put("mes", "删除资源失败");
		}
		return map;//重定向
	}
}
