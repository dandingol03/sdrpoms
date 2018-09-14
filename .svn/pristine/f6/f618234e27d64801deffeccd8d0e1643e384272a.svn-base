package com.pc.busniess.baseInfoDefencePoliceHouse.web.controller;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefencePoliceHouse.po.BaseInfoDefencePoliceHousePo;
import com.pc.busniess.baseInfoDefencePoliceHouse.service.BaseInfoDefencePoliceHouseService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class BaseInfoDefencePoliceHouseController {

	private static Logger logger = Logger.getLogger(BaseInfoDefencePoliceHouseController.class);
	
	@Autowired
	private BaseInfoDefencePoliceHouseService baseInfoDefencePoliceHouseService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开派出所信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoDefencePoliceHouseInit",method=RequestMethod.GET)
	public String baseInfoDefencePoliceHouseInit(HttpServletRequest req){
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoDefencePoliceHouse/baseInfoDefencePoliceHouseInit";
	}
	/**
	 * 查询派出所信息
	 * @param dgm
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefencePoliceHouseQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoDefencePoliceHouseQueryList(DataGridModel dgm, BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		return baseInfoDefencePoliceHouseService.baseInfoDefencePoliceHouseQueryList(dgm,baseInfoDefencePoliceHousePo); 
	}
	/***
	 * 弹出派出所信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoDefencePoliceHousePopWin",method=RequestMethod.GET)
	public String addbaseInfoDefencePoliceHousePopWin(HttpServletRequest req){
		
	    return "pc/bussiness/baseInfoDefencePoliceHouse/addBaseInfoDefencePoliceHousePopWin";
	}
	/**
	 * 保存派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoDefencePoliceHouse",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addbaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoDefencePoliceHousePo.getLng();
		String lat=baseInfoDefencePoliceHousePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoDefencePoliceHousePo.setTheGeom(theGeom);
		baseInfoDefencePoliceHousePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("polH");
		baseInfoDefencePoliceHousePo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoDefencePoliceHousePo.getName();
				obj[2] = baseInfoDefencePoliceHousePo.getLng();
				obj[3] = baseInfoDefencePoliceHousePo.getLat();
				obj[4] = baseInfoDefencePoliceHousePo.getTheGeom();
				obj[5] = baseInfoDefencePoliceHousePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoDefencePoliceHouseService.addBaseInfoDefencePoliceHouse(baseInfoDefencePoliceHousePo,objList) > 0){
				map.put("mes", "新增派出所信息成功");
			}else{
				map.put("mes", "新增派出所信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增派出所信息失败",e);
			}
			map.put("mes", "新增派出所信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoDefencePoliceHousePopWin",method=RequestMethod.GET)
	public String updatebaseInfoDefencePoliceHousePopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoDefencePoliceHouse/updateBaseInfoDefencePoliceHousePopWin";
	}
	/**
	 * 保存修改派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefencePoliceHouse",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatebaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoDefencePoliceHousePo.getLng();
		String lat=baseInfoDefencePoliceHousePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoDefencePoliceHousePo.setTheGeom(theGeom);
		baseInfoDefencePoliceHousePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoDefencePoliceHousePo.getId();
				obj[1] = baseInfoDefencePoliceHousePo.getName();
				obj[2] = baseInfoDefencePoliceHousePo.getLng();
				obj[3] = baseInfoDefencePoliceHousePo.getLat();
				obj[4] = baseInfoDefencePoliceHousePo.getTheGeom();
				obj[5] = baseInfoDefencePoliceHousePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoDefencePoliceHouseService.updateBaseInfoDefencePoliceHouse(baseInfoDefencePoliceHousePo,objList) > 0){
				map.put("mes", "更新派出所信息成功");
			}else{
				map.put("mes", "更新派出所信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改派出所信息失败",e);
			}
			map.put("mes", "修改派出所信息失败");
		}
		return map; 
	}
	/**
	 * 删除派出所信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoDefencePoliceHouse",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletebaseInfoDefencePoliceHouse(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoDefencePoliceHouseService.deleteBaseInfoDefencePoliceHouse(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条派出所信息");
			}else{
				map.put("mes", "删除派出所信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除派出所信息失败",e);
			}
			map.put("mes", "删除派出所信息失败");
		}
		return map;
	}
}
