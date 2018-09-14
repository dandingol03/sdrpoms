package com.pc.bsp.resource.web.controller;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.menu.po.ResourceMenu;
import com.pc.bsp.menu.service.MenuService;
import com.pc.bsp.resource.po.Resource;
import com.pc.bsp.resource.service.ResourceService;

/**
 * @author simple
 *
 */
@Controller
public class ResourceController {
	
	private static Logger logger = Logger.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 通过菜单进入资源管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resource",method=RequestMethod.GET)
    public String list(Model model){
        return "pc/bsp/resource/resource";
    }
	
	/**
	 * 默认分页查询资源信息
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value="/resource/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Resource resource){
		return resourceService.getPageList(dgm, resource);
	}
	
	/**
	 * 进入资源添加页面
	 * @return
	 */
	@RequestMapping(value="/resource/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "pc/bsp/resource/addResourcePopWin";
	}
	
	/**
	 * 保存添加资源
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/resource/addResource",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveResource(Resource resource){
		
		resource.setResourceId(NextID.getNextID("res"));//主键
		resource.setPriority("0");	//优先级，默认为0
		resource.setIsSys("0");		//是否注册，默认否
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(resourceService.saveResource(resource) > 0){
				map.put("mes", "添加资源成功");
			}else{
				map.put("mes", "添加资源失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("添加资源失败",e);
			}
			map.put("mes", "添加资源失败");
		}
		return map; 
	}
	
	/**
	 * 进入资源修改页面
	 * @return
	 */
	@RequestMapping(value="/resource/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "pc/bsp/resource/updateResourcePopWin";
	}
	
	/**
	 * 保存修改后的资源信息
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/resource/updateResource",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateResource(Resource resource){
		resource.setPriority("0");
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			if(resourceService.updateResource(resource) > 0){
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
	 * @param resourceIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resource/delResources",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delResources(@RequestParam("resourceId") List<String> resourceIdList){
		
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
	
	/**
	 * 进入资源注册页面
	 * @return
	 */
	@RequestMapping(value="/resource/regResPopWin",method=RequestMethod.GET)
	public String popWin4Tree(){
		return "pc/bsp/resource/regResPopWin";
	}
	
	/**
	 * 保存注册的[资源-菜单]对应关系
	 * @param menuId
	 * @param resMenu
	 * @return
	 */
	@RequestMapping(value="/resource/saveResMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveResMenu(@RequestParam("menuId") String menuId, ResourceMenu resMenu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(menuService.getMenuCountByPid(menuId) > 0){
				map.put("mes", "请选择菜单叶节点进行注册，不要选择菜单目录");
				return map;
			}else{
				List<Object[]> objList = new ArrayList<Object[]>();
				
				//组装插入数据
				String[] resIds = resMenu.getResourceId().split(",");
				for(int i = 0; i < resIds.length; i ++){
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = resIds[i];
					obj[2] = menuId;
					objList.add(obj);
				}
				if(objList.size() > 0 ){
					int[] result = resourceService.saveResMenu(objList);
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
	
	/**
	 * 进入查看注册资源树页面
	 * @return
	 */
	@RequestMapping(value="/resource/resTreePopWin",method=RequestMethod.GET)
	public String popWin4ResTree(){
		return "pc/bsp/resource/menuResPopWin";
	}
	
	/**
	 * 进入资源注册页面
	 * @return
	 */
	@RequestMapping(value="/resource/regResMobilePopWin",method=RequestMethod.GET)
	public String popWin4MobileTree(){
		return "pc/bsp/resource/regResMobilePopWin";
	}
	
	/**
	 * 保存注册的[资源-菜单]对应关系
	 * @param menuId
	 * @param resMenu
	 * @return
	 */
	
	
	/**
	 * 进入查看注册资源树页面
	 * @return
	 */
	@RequestMapping(value="/resource/resMobileTreePopWin",method=RequestMethod.GET)
	public String popWin4ResMobileTree(){
		return "pc/bsp/resource/mobileMenuResPopWin";
	}

}
