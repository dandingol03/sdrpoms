package com.pc.busniess.baseInfoPartStation.stationYard.web.controller;
import java.io.UnsupportedEncodingException;
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
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartStation.stationYard.po.BaseInfoStationYardPo;
import com.pc.busniess.baseInfoPartStation.stationYard.service.BaseInfoStationYardService;

@Controller
public class BaseInfoStationYardController {

	private static Logger logger = Logger.getLogger(BaseInfoStationYardController.class);
	//站场Service
	@Autowired
	private BaseInfoStationYardService baseInfoStationYardService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开站场信息页面
	 * @param req
	 * @return 站场初始化页面
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartStationYardRelInit",method=RequestMethod.GET)
	public String baseInfoStationYardInit(HttpServletRequest req){
		return "pc/bussiness/baseInfoPartStation/stationYard/baseInfoStationYardInit";
	}
	
	/**
	 * 查询站场信息
	 * @param dgm
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo DataGridModel
	 * @return 站场Service层查询站场信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoPartStationYardRelQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoStationYardQueryList(DataGridModel dgm,@RequestParam("stationId") String stationId,
			BaseInfoStationYardPo baseInfoStationYardPo) {
		baseInfoStationYardPo.setStationId(stationId);
		return baseInfoStationYardService.baseInfoStationYardQueryList(dgm,baseInfoStationYardPo); 
	}
	
	/**
	 * 弹出站场信息添加页面
	 * @param req
	 * @return 增加站场信息页面
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartStationYardRelPopWin",method=RequestMethod.GET)
	public String addBaseInfoStationYardPopWin(HttpServletRequest req){
	    return "pc/bussiness/baseInfoPartStation/stationYard/addBaseInfoStationYardPopWin";
	}
	
	/**
	 * 保存站场信息
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo 
	 * @return 增加站场信息页面
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoStationYard",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoStationYardPo.getLng();
		String lat=baseInfoStationYardPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoStationYardPo.setTheGeom(theGeom);
		baseInfoStationYardPo.setOrgId(orgId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoStationYardService.addBaseInfoStationYard(baseInfoStationYardPo) > 0){
				map.put("mes", "新增站场信息成功");
			}else{
				map.put("mes", "新增站场信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增站场信息失败",e);
			}
			map.put("mes", "新增站场信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return 修改站场信息页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartStationYardRelPopWin",method=RequestMethod.GET)
	public String updateBaseInfoStationYardPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		// 照片回显
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartStation/stationYard/updateBaseInfoStationYardPopWin";
	}
	
	/**
	 * 保存修改站场信息
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo
	 * @return 修改站场信息页面
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoStationYard",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoStationYardPo.getLng();
		String lat=baseInfoStationYardPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoStationYardPo.setTheGeom(theGeom);
		baseInfoStationYardPo.setOrgId(orgId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoStationYardService.updateBaseInfoStationYard(baseInfoStationYardPo) > 0){
				map.put("mes", "更新站场信息成功");
			}else{
				map.put("mes", "更新站场信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改站场信息失败",e);
			}
			map.put("mes", "修改站场信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除站场信息
	 * @param idList
	 * @see List<String>
	 * @return map
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartStationYardRel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoStationYard(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoStationYardService.deleteBaseInfoStationYard(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条站场信息");
			}else{
				map.put("mes", "删除站场信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除站场信息失败",e);
			}
			map.put("mes", "删除站场信息失败");
		}
		return map;
	}
}

