package com.pc.busniess.baseInfoPartStation.web.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;
import com.pc.busniess.baseInfoPartStation.service.BaseInfoPartStationService;




import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;



/**
 *  D.Steven
 */
import java.util.HashMap;


/**
 * 该controller主要是用于接收和返回数据的
 * @author lyc
 * @version 1.0
 * @since 1.0 在1.0版本开始添加的
 */

@Controller
public class BaseInfoPartStationController {

	private static Logger logger = Logger.getLogger(BaseInfoPartStationController.class);
	//调用pc车站Service
	@Autowired
	private BaseInfoPartStationService baseInfoPartStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开车站信息页面
	 * @param req
	 * @return 车站初始化页面
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartStationInit",method=RequestMethod.GET)
	public String baseInfoPartStationlInit(HttpServletRequest req){
		//得到数据字典中车站等级的数据,然后赋值到该集合中;
		List<Map<String,Object>> levelList = PubData.getDictList("PART_STATION_LEVEL");
		req.setAttribute("levelList",levelList);
		//得到数据字典中车站性质的数据,然后赋值到该集合中;
		List<Map<String,Object>> natureList = PubData.getDictList("PART_STATION_NATURE");
		req.setAttribute("natureList",natureList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中运营状态的数据,然后赋值到该集合中;
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartStation/baseInfoPartStationInit";
	}
	
	/**
	 * 查询车站信息
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return 车站Service层查询车站信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoPartStationQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfooPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo) {
		return baseInfoPartStationService.baseInfoPartStationQueryList(dgm,baseInfoPartStationPo); 
	}
	
	/**
	 * 弹出车站信息添加页面
	 * @param req
	 * @return 增加车站信息页面
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartStationPopWin",method=RequestMethod.GET)
	public String addBaseInfoPartStationPopWin(HttpServletRequest req){
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		//得到数据字典中车站等级的数据,然后赋值到该集合中;
		List<Map<String,Object>> levelList = PubData.getDictList("PART_STATION_LEVEL");
		req.setAttribute("levelList",levelList);
		//得到数据字典中车站性质的数据,然后赋值到该集合中;
		List<Map<String,Object>> natureList = PubData.getDictList("PART_STATION_NATURE");
		req.setAttribute("natureList",natureList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中运营状态的数据,然后赋值到该集合中;
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
	    return "pc/bussiness/baseInfoPartStation/addBaseInfoPartStationPopWin";
	}
	
	/**
	 * 保存车站信息
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo 
	 * @return 增加车站信息页面
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoPartStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartStationPo.getLng();
		String lat=baseInfoPartStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartStationPo.setTheGeom(theGeom);
		baseInfoPartStationPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("psta");
		baseInfoPartStationPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartStationPo.getName();
				obj[2] = baseInfoPartStationPo.getLng();
				obj[3] = baseInfoPartStationPo.getLat();
				obj[4] = baseInfoPartStationPo.getTheGeom();
				obj[5] = baseInfoPartStationPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartStationService.addBaseInfoPartStation(baseInfoPartStationPo,objList) > 0){
				map.put("mes", "新增车站信息成功");
			}else{
				map.put("mes", "新增车站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增车站信息失败",e);
			}
			map.put("mes", "新增车站信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return 修改车站信息页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartStationPopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartStationPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		//得到数据字典中车站等级的数据,然后赋值到该集合中;
		List<Map<String,Object>> levelList = PubData.getDictList("PART_STATION_LEVEL");
		req.setAttribute("levelList",levelList);
		//得到数据字典中车站性质的数据,然后赋值到该集合中;
		List<Map<String,Object>> natureList = PubData.getDictList("PART_STATION_NATURE");
		req.setAttribute("natureList",natureList);
		//得到数据字典中隶属铁路局的数据,然后赋值到该集合中;
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//得到数据字典中运营状态的数据,然后赋值到该集合中;
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
		// 照片回显
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		
		return "pc/bussiness/baseInfoPartStation/updateBaseInfoPartStationPopWin";
	}
	
	/**
	 * 保存修改车站信息
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo
	 * @return 修改车站信息页面
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoPartStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartStationPo.getLng();
		String lat=baseInfoPartStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartStationPo.setTheGeom(theGeom);
		baseInfoPartStationPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPartStationPo.getId();
				obj[1] = baseInfoPartStationPo.getName();
				obj[2] = baseInfoPartStationPo.getLng();
				obj[3] = baseInfoPartStationPo.getLat();
				obj[4] = baseInfoPartStationPo.getTheGeom();
				obj[5] = baseInfoPartStationPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartStationService.updateBaseInfoPartStation(baseInfoPartStationPo,objList) > 0){
				map.put("mes", "更新车站信息成功");
			}else{
				map.put("mes", "更新车站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改车站信息失败",e);
			}
			map.put("mes", "修改车站信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除车站信息
	 * @param idList
	 * @see List<String>
	 * @return map
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartStation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoPartStation(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPartStationService.deleteBaseInfoPartStation(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条车站信息");
			}else{
				map.put("mes", "删除车站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除车站信息失败",e);
			}
			map.put("mes", "删除车站信息失败");
		}
		return map;
	}
}

