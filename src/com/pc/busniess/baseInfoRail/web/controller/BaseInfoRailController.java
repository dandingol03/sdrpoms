package com.pc.busniess.baseInfoRail.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
/**
 * 铁路
 * @author jiawenlong
 * Controller
 */
@Controller
public class BaseInfoRailController {

	private static Logger logger = Logger.getLogger(BaseInfoRailController.class);
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	
	/**
	 * 打开铁路信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoRailInit",method=RequestMethod.GET)
	public String baseInfoRailInit(HttpServletRequest req){
		//字典铁路分类
		List<Map<String,Object>> classifyList = PubData.getDictList("RAIL_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
			return "pc/bussiness/baseInfoRail/baseInfoRailInit";		
	}
	/**
	 * 查询铁路信息
	 * @param dgm
	 * @param baseInfoRailPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoRailQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoRailQueryList(DataGridModel dgm, BaseInfoRailPo baseInfoRailPo) {
		return baseInfoRailService.baseInfoRailQueryList(dgm,baseInfoRailPo); 
	}
	/**
	 * 弹出铁路信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoRailPopWin",method=RequestMethod.GET)
	public String addBaseInfoRailPopWin(HttpServletRequest req){
		//字典铁路分类
		List<Map<String,Object>> classifyList = PubData.getDictList("RAIL_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		//线别
		List<Map<String,Object>> railLevelList = PubData.getDictList("RAIL_LEVEL");
		req.setAttribute("railLevelList",railLevelList);
		//状态
		List<Map<String,Object>> operationStateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("operationStateList",operationStateList);
			return "pc/bussiness/baseInfoRail/addBaseInfoRailPopWin";
	}
	
	/**
	 * 保存铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoRail",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoRailService.addBaseInfoRail(baseInfoRailPo) > 0){
				map.put("mes", "新增铁路信息成功");
			}else{
				map.put("mes", "新增铁路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增铁路信息失败",e);
			}
			map.put("mes", "新增铁路信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoRailPopWin",method=RequestMethod.GET)
	public String updateBaseInfoRailPopWin(HttpServletRequest req){
		//字典铁路分类
		List<Map<String,Object>> classifyList = PubData.getDictList("RAIL_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		
		//线别
		List<Map<String,Object>> railLevelList = PubData.getDictList("RAIL_LEVEL");
		req.setAttribute("railLevelList",railLevelList);
		//状态
		List<Map<String,Object>> operationStateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("operationStateList",operationStateList);
			return "pc/bussiness/baseInfoRail/updateBaseInfoRailPopWin";
		
		
	}
	/**
	 * 保存修改铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	
	@RequestMapping(value = "/baseInfo/updateBaseInfoRail",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoRailService.updateBaseInfoRail(baseInfoRailPo) > 0){
				map.put("mes", "更新铁路信息成功");
			}else{
				map.put("mes", "更新铁路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改铁路信息失败",e);
			}
			map.put("mes", "修改铁路信息失败");
		}
		return map; 
	}
	/**
	 * 删除铁路信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoRail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoRail(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoRailService.deleteBaseInfoRail(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条铁路信息");
			}else{
				map.put("mes", "删除铁路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除铁路信息失败",e);
			}
			map.put("mes", "删除铁路信息失败");
		}
		return map;
	}
}
