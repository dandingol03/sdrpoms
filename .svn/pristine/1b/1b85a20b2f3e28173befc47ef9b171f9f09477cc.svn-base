/**
 * 
 */
package com.pc.bsp.sysparam.web.controller;

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
import com.pc.bsp.sysparam.po.SysParam;
import com.pc.bsp.sysparam.service.SysParamService;

/**
 * @author simple
 *
 */
@Controller
public class SysParamController {
	
	private static Logger logger = Logger.getLogger(SysParamController.class);
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * 通过菜单进入系统参数管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sysparam",method=RequestMethod.GET)
    public String list(Model model){
        return "pc/bsp/sysparam/sysparam";
    }
	
	/**
	 * 默认分页查询系统参数信息
	 * @param dgm
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/sysparam/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, SysParam param){
		return sysParamService.getPageList(dgm, param);
	}
	
	/**
	 * 进入系统参数添加页面
	 * @return
	 */
	@RequestMapping(value="/sysparam/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "pc/bsp/sysparam/addSysParamPopWin";
	}
	
	/**
	 * 保存添加系统参数
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/sysparam/addSysParam",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveSysParam(SysParam param){
		//添加主键
		param.setParamId(NextID.getUUID());
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(sysParamService.saveSysParam(param) > 0){
				map.put("mes", "添加系统参数成功");
			}else{
				map.put("mes", "添加系统参数失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("添加系统参数失败",e);
			}
			map.put("mes", "添加系统参数失败");
		}
		return map; 
	}
	
	/**
	 * 进入系统参数修改页面
	 * @return
	 */
	@RequestMapping(value="/sysparam/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "pc/bsp/sysparam/updateSysParamPopWin";
	}
	
	/**
	 * 保存修改后的系统参数信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/sysparam/updateSysParam",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateSysParam(SysParam param){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			if(sysParamService.updateSysParam(param) > 0){
				map.put("mes", "更新系统参数成功");
			}else{
				map.put("mes", "更新系统参数失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("更新系统参数失败",e);
			}
			map.put("mes", "更新系统参数失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除角色
	 * @param paramIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sysparam/delSysParams",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delSysParams(@RequestParam("paramId") List<String> paramIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = sysParamService.delSysParamBatch(paramIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == paramIdList.size()){
				map.put("mes", "删除成功["+sum+"]条系统参数记录");
			}else{
				map.put("mes", "删除系统参数失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除系统参数失败",e);
			}
			map.put("mes", "删除系统参数失败");
		}
		return map;//重定向
	}

}
