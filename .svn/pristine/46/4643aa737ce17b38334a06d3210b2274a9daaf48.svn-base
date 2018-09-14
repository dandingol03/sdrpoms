package com.mobile.busniess.mobileBaseInfoPartStation.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoPartStation.service.MobileBaseInfoPartStationService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;
import com.pc.busniess.baseInfoPartStation.service.BaseInfoPartStationService;
import com.pc.busniess.baseInfoPartStation.stationYard.po.BaseInfoStationYardPo;
import com.pc.busniess.baseInfoPartStation.stationYard.service.BaseInfoStationYardService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartStationController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoPartStationController.class);
	//调用pc车站Service
	@Autowired
	private BaseInfoPartStationService baseInfoPartStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartStationService mobilebaseInfoPartStationService;

	//站场Service
	@Autowired
	private BaseInfoStationYardService baseInfoStationYardService;
	/**
	 * 查询车站信息
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return 车站Service层查询车站信息
	 */
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPartStationQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfooPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo) {
		
		return mobilebaseInfoPartStationService.baseInfoPartStationQueryList(dgm,baseInfoPartStationPo); 
	}
	/**
	 * 保存车站信息
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo 
	 * @return 增加车站信息页面
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoPartStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartStationPo.getLng();
		String lat=baseInfoPartStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
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
		map.put("data",baseInfoPartStationPo);
		try {
			if(baseInfoPartStationService.addBaseInfoPartStation(baseInfoPartStationPo,objList) > 0){
				map.put("mes", "新增车站信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增车站信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增车站信息失败",e);
			}
			map.put("mes", "新增车站信息失败");
			map.put("success",false);
		}
		return map; 
	}
	/**
	 * 保存站场信息
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo 
	 * @return 增加站场信息页面
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoStationYard",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoStationYardPo.getLng();
		String lat=baseInfoStationYardPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		baseInfoStationYardPo.setTheGeom(theGeom);
		baseInfoStationYardPo.setOrgId(orgId);
		try {
			if(baseInfoStationYardService.addBaseInfoStationYard(baseInfoStationYardPo) > 0){
				map.put("mes", "新增站场信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增站场信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增站场信息失败",e);
			}
			map.put("mes", "新增站场信息失败");
			map.put("success",false);
		}
		return map; 
	}
}

