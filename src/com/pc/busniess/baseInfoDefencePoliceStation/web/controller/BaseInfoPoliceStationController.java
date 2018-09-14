package com.pc.busniess.baseInfoDefencePoliceStation.web.controller;



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
import com.pc.busniess.baseInfoDefencePoliceStation.po.BaseInfoPoliceStationPo;
import com.pc.busniess.baseInfoDefencePoliceStation.service.BaseInfoPoliceStationService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class BaseInfoPoliceStationController {

	private static Logger logger = Logger.getLogger(BaseInfoPoliceStationController.class);
	
	@Autowired
	private BaseInfoPoliceStationService baseInfoPoliceStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开警务站信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoDefencePoliceStationInit",method=RequestMethod.GET)
	public String baseInfoPoliceStationInit(HttpServletRequest req){
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoDefencePoliceStation/baseInfoPoliceStationInit";
	}
	/**
	 * 查询警务站信息
	 * @param dgm
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefencePoliceStationQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoPoliceStationQueryList(DataGridModel dgm, BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		return baseInfoPoliceStationService.baseInfoPoliceStationQueryList(dgm,baseInfoPoliceStationPo); 
	}
	/***
	 * 弹出警务站信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoDefencePoliceStationPopWin",method=RequestMethod.GET)
	public String addbaseInfoPoliceStationPopWin(HttpServletRequest req){
		
	    return "pc/bussiness/baseInfoDefencePoliceStation/addBaseInfoPoliceStationPopWin";
	}
	/**
	 * 保存警务站信息
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoPoliceStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addbaseInfoPoliceStation(BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPoliceStationPo.getLng();
		String lat=baseInfoPoliceStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPoliceStationPo.setTheGeom(theGeom);
		baseInfoPoliceStationPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("polS");
		baseInfoPoliceStationPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPoliceStationPo.getName();
				obj[2] = baseInfoPoliceStationPo.getLng();
				obj[3] = baseInfoPoliceStationPo.getLat();
				obj[4] = baseInfoPoliceStationPo.getTheGeom();
				obj[5] = baseInfoPoliceStationPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPoliceStationService.addBaseInfoPoliceStation(baseInfoPoliceStationPo,objList) > 0){
				map.put("mes", "新增警务站信息成功");
			}else{
				map.put("mes", "新增警务站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增警务站信息失败",e);
			}
			map.put("mes", "新增警务站信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoDefencePoliceStationPopWin",method=RequestMethod.GET)
	public String updatebaseInfoPoliceStationPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoDefencePoliceStation/updateBaseInfoPoliceStationPopWin";
	}
	/**
	 * 保存修改警务站信息
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoPoliceStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatebaseInfoPoliceStation(BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPoliceStationPo.getLng();
		String lat=baseInfoPoliceStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPoliceStationPo.setTheGeom(theGeom);
		baseInfoPoliceStationPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPoliceStationPo.getId();
				obj[1] = baseInfoPoliceStationPo.getName();
				obj[2] = baseInfoPoliceStationPo.getLng();
				obj[3] = baseInfoPoliceStationPo.getLat();
				obj[4] = baseInfoPoliceStationPo.getTheGeom();
				obj[5] = baseInfoPoliceStationPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPoliceStationService.updateBaseInfoPoliceStation(baseInfoPoliceStationPo,objList) > 0){
				map.put("mes", "更新警务站信息成功");
			}else{
				map.put("mes", "更新警务站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改警务站信息失败",e);
			}
			map.put("mes", "修改警务站信息失败");
		}
		return map; 
	}
	/**
	 * 删除警务站信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoDefencePoliceStation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletebaseInfoPoliceStation(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPoliceStationService.deleteBaseInfoPoliceStation(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条警务站信息");
			}else{
				map.put("mes", "删除警务站信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除警务站信息失败",e);
			}
			map.put("mes", "删除警务站信息失败");
		}
		return map;
	}
}
