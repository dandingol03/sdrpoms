package com.pc.busniess.baseInfoPartBridge.web.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/**
 *  D.Steven
 */
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
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;
import com.pc.busniess.baseInfoPartBridge.service.BaseInfoPartBridgeService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;


/**
 * 该controller主要是用于接收和返回数据的
 * @author lyc
 * @version 1.0
 * @since 1.0 在1.0版本开始添加的
 */

@Controller
public class BaseInfoPartBridgeController {

	private static Logger logger = Logger.getLogger(BaseInfoPartBridgeController.class);
	//调用pc桥梁Service
	@Autowired
	private BaseInfoPartBridgeService baseInfoPartBridgeService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开桥梁信息页面
	 * @param req
	 * @return 桥梁信息初始化页面
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartBridgeInit",method=RequestMethod.GET)
	public String baseInfoPartBridgeInit(HttpServletRequest req){
		//得到数据字典中桥梁类别的数据,然后赋值到该集合中;
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_BRIDGE_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		//得到数据字典中行别的数据,然后赋值到该集合中;
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//得到数据字典中守护情况的数据,然后赋值到该集合中;
		List<Map<String,Object>> railGuardStatusList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("railGuardStatusList",railGuardStatusList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartBridge/baseInfoPartBridgeInit";
	}
	
	/**
	 * 查询桥梁信息
	 * @param dgm
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo DataGridModel
	 * @return 桥梁信息查询页面
	 */
	
	@RequestMapping(value = "/baseInfo/baseInfoPartBridgeQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoRailQueryList(DataGridModel dgm, BaseInfoPartBridgePo baseInfoPartBridgePo) {
		return baseInfoPartBridgeService.baseInfoPartBridgeQueryList(dgm,baseInfoPartBridgePo); 
	}
	
	/**
	 * 弹出桥梁信息添加页面
	 * @param req
	 * @return 桥梁信息添加页面
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartBridgePopWin",method=RequestMethod.GET)
	public String addBaseInfoPartBridgePopWin(HttpServletRequest req){
		//得到数据字典中桥梁类别的数据,然后赋值到该集合中;
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_BRIDGE_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		//得到数据字典中行别的数据,然后赋值到该集合中;
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//得到数据字典中守护情况的数据,然后赋值到该集合中;
		List<Map<String,Object>> railGuardStatusList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("railGuardStatusList",railGuardStatusList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
	    return "pc/bussiness/baseInfoPartBridge/addBaseInfoPartBridgePopWin";
	}
	
	/**
	 * 保存桥梁信息
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo 
	 * @return 桥梁信息添加页面
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoPartBridge",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartBridgePo.getLng();
		String lat=baseInfoPartBridgePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartBridgePo.setTheGeom(theGeom);
		baseInfoPartBridgePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("brid");
		baseInfoPartBridgePo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartBridgePo.getName();
				obj[2] = baseInfoPartBridgePo.getLng();
				obj[3] = baseInfoPartBridgePo.getLat();
				obj[4] = baseInfoPartBridgePo.getTheGeom();
				obj[5] = baseInfoPartBridgePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartBridgeService.addBaseInfoPartBridge(baseInfoPartBridgePo,objList) > 0){
				map.put("mes", "新增桥梁信息成功");
			}else{
				map.put("mes", "新增桥梁信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增桥梁信息失败",e);
			}
			map.put("mes", "新增桥梁信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return 桥梁信息修改页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartBridgePopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartBridgePopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//得到数据字典中桥梁类别的数据,然后赋值到该集合中;
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_BRIDGE_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		//得到数据字典中行别的数据,然后赋值到该集合中;
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//得到数据字典中守护情况的数据,然后赋值到该集合中;
		List<Map<String,Object>> railGuardStatusList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("railGuardStatusList",railGuardStatusList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartBridge/updateBaseInfoPartBridgePopWin";
	}
	
	/**
	 * 保存修改桥梁信息
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo
	 * @return 桥梁信息修改页面
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoPartBridge",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartBridgePo.getLng();
		String lat=baseInfoPartBridgePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartBridgePo.setTheGeom(theGeom);
		baseInfoPartBridgePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPartBridgePo.getId();
				obj[1] = baseInfoPartBridgePo.getName();
				obj[2] = baseInfoPartBridgePo.getLng();
				obj[3] = baseInfoPartBridgePo.getLat();
				obj[4] = baseInfoPartBridgePo.getTheGeom();
				obj[5] = baseInfoPartBridgePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartBridgeService.updateBaseInfoPartBridge(baseInfoPartBridgePo,objList) > 0){
				map.put("mes", "更新桥梁信息成功");
			}else{
				map.put("mes", "更新桥梁信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改桥梁信息失败",e);
			}
			map.put("mes", "修改桥梁信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除桥梁信息
	 * @param idList
	 * @see List<String>
	 * @return 桥梁信息删除页面
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartBridge",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoPartBridge(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPartBridgeService.deleteBaseInfoPartBridge(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条桥梁信息");
			}else{
				map.put("mes", "删除桥梁信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除桥梁信息失败",e);
			}
			map.put("mes", "删除桥梁信息失败");
		}
		return map;
	}
	
}
