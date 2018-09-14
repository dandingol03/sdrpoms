package com.mobile.bsp.menu.web.controller;

/**
 * @author D.Steven
 */

import java.sql.Statement;
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

import com.mobile.bsp.menu.po.MobileMenu;
import com.mobile.bsp.menu.po.ResourceMobileMenu;
import com.mobile.bsp.menu.service.MobileMenuService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.util.GenOrgDescID;
import com.pc.bsp.resource.po.Resource;
import com.pc.bsp.resource.service.ResourceService;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Controller
@RequestMapping("/mobile")
public class MobileMenuController {
	
	private static Logger logger = Logger.getLogger(MobileMenuController.class);
	@Autowired
	private MobileMenuService mobileMenuService;
	@Autowired
	private ResourceService resourceService;
	
	
	/**
	 * 通过菜单进入菜单管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menu",method=RequestMethod.GET)
    public String list(Model model){
        return "mobile/bsp/menu/mobileMenu";
    }
	
	/**
	 * 获取移动端系统菜单树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenu")
	@ResponseBody
    public List<Map<String, Object>> getMenu(){
        return mobileMenuService.getMobileMenuTreeList();
    }
	
	/**跳转菜单添加页面
	 * 
	 */
	@RequestMapping(value="/menu/addMenuPopWin",method=RequestMethod.GET)
	public String addMenuPopWin(){
		return "mobile/bsp/menu/addMobileMenuPopWin";
	}
	
	/**
	 * 保存移动端菜单
	 * @param mobileMenu
	 * @return
	 */
	@RequestMapping(value="/menu/addMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveMenu(MobileMenu mobileMenu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			//根据父节点和最大子节点计算菜单编号
			mobileMenu.setMobileMenuId(GenOrgDescID.get2NewId(mobileMenu.getMobileMenuPid(),
					mobileMenuService.getSubMenuMaxId(mobileMenu.getMobileMenuPid())));
			String menuType = "0";	//'0'为菜单
			if(mobileMenu.getMobileMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			mobileMenu.setMobileMenuType(menuType);
			
			if(mobileMenuService.saveMobileMenu(mobileMenu) > 0){
				map.put("mes", "添加移动端菜单成功");
			}else{
				map.put("mes", "添加移动端菜单失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("添加移动端菜单失败",e);
			}
			map.put("mes", "添加移动端菜单失败");
		}
		return map;//重定向
	}
	
	/**跳转移动端菜单更新页面
	 * 
	 */
	@RequestMapping(value="/menu/updateMenuPopWin",method=RequestMethod.GET)
	public String updateMenuPopWin(){
		return "mobile/bsp/menu/updateMobileMenuPopWin";
	}
	
	/**
	 * 更新移动端菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/updateMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateMenu(MobileMenu mobileMenu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			String menuType = "0";	//'0'为菜单
			if(mobileMenu.getMobileMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			mobileMenu.setMobileMenuType(menuType);
			if(mobileMenuService.updateMobileMenu(mobileMenu) > 0){
				map.put("mes", "修改移动端菜单成功");
			}else{
				map.put("mes", "修改移动端菜单失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改移动端菜单失败",e);
			}
			map.put("mes", "修改移动端菜单失败");
		}
		return map;//重定向
	}
	/**
	 * 获取（菜单+注册资源）树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenuAndRes")
	@ResponseBody
    public List<Map<String, Object>> getMenuAndRres(){
        return mobileMenuService.getMenuAndResTreeList();
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
			
			menuList = mobileMenuService.getUserMobileMenuTreeList(authString);
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
			menuList = mobileMenuService.getUserSecondLevelMobileMenuList(authString);
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
			
			menuList = mobileMenuService.getUserMobileMenuListBySecondLevelMobileMenu(authString,secondLevelMenuId);
			
		}
		return menuList;
    }
	
	/**
	 * 根据移动端菜单Id查询下一层菜单集合（根据用户权限-第一级为菜单根节点 并不进行查找）
	 * @return
	 */
	@RequestMapping(value="/menu/getNextLevelUserMenuListByMenuId")
	@ResponseBody
    public List<Map<String, Object>> getNextLevelUserMenuListByMenuId(
    		@RequestParam("menuId") String menuId){
		
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			List<String> authString = new ArrayList<String>();
			for(GrantedAuthority auth: auths){
				authString.add(auth.getAuthority());
			}
			
			menuList = mobileMenuService.getNextLevelUserMenuListByMenuId(authString,menuId);
			
		}
		return menuList;
    }
	
	/**
	 * 删除移动端菜单及关联数据
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/menu/delMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delMenus(@RequestParam("menuId") String menuId){
		Map<String, String> map = new HashMap<String, String>();
		try {
			ResourceMobileMenu resMenu = new ResourceMobileMenu();
			MobileMenu mobileMenu = new MobileMenu();
			resMenu.setMenuId(menuId);
			mobileMenu.setMobileMenuId(menuId);
			
			int result = 0;
			if(mobileMenuService.getMobileMenuCountByPid(menuId) > 0){
				map.put("mes", "无法删除，请先删除其下级菜单");
				return map;
			}else{
				result = mobileMenuService.delMobileMenus(mobileMenu, resMenu);
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
			return mobileMenuService.getReourceRelateMobileMenuList(dgm,menuId);
		}
		return null;
	}
	
	/**
	 * 跳转到添加（关联菜单资源）页面
	 * @return
	 */
	@RequestMapping(value = "/menu/addMenuResourcePopWin", method = RequestMethod.GET)
	public String addMenuResourcePopWin() {
		return "mobile/bsp/menu/addMobileMenuResourcePopWin";
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
			if(mobileMenuService.addMobileMenuResourceRelate(re,menuId)>0){
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
		return "mobile/bsp/menu/updateMenuResourcePopWin";
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
			int[] result = resourceService.delMobileResourceBatch(resourceIdList);
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
	/**
	 * 保存注册的[资源-菜单]对应关系
	 * @param menuId
	 * @param resMenu
	 * @return
	 */
	@RequestMapping(value="/resource/saveResMobileMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveResMobileMenu(@RequestParam("menuId") String menuId, ResourceMobileMenu resMobileMenu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(mobileMenuService.getMobileMenuCountByPid(menuId) > 0){
				map.put("mes", "请选择菜单叶节点进行注册，不要选择菜单目录");
				return map;
			}else{
				List<Object[]> objList = new ArrayList<Object[]>();
				
				//组装插入数据
				String[] resIds = resMobileMenu.getResourceId().split(",");
				for(int i = 0; i < resIds.length; i ++){
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = resIds[i];
					obj[2] = menuId;
					objList.add(obj);
				}
				if(objList.size() > 0 ){
					int[] result = resourceService.saveResMobileMenu(objList);
					int sum = 0;
					for(int j = 0; j < result.length; j ++){
						sum += result[j] == Statement.SUCCESS_NO_INFO ? 0 : result[j];
					}
					map.put("mes", "成功注册资源["+sum+"]条");
				}else{
					map.put("mes", "注册资源失败");
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("注册资源失败",e);
			}
			map.put("mes", "注册资源失败");
		}
		return map; 
	}
}
