package com.mobile.busniess.mobileBaseInfoPartBridge.web.controller;


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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoPartBridge.service.MobileBaseInfoPartBridgeService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;
import com.pc.busniess.baseInfoPartBridge.service.BaseInfoPartBridgeService;
import com.pc.busniess.baseInfoPartBridge.web.controller.BaseInfoPartBridgeController;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartBridgeController {

	private static Logger logger = Logger.getLogger(BaseInfoPartBridgeController.class);
	//调用pc桥梁Service
	@Autowired
	private BaseInfoPartBridgeService baseInfoPartBridgeService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartBridgeService mobilebaseInfoPartBridgeService;
	
	//桥梁查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPartBridgeQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoPartBridgePo baseInfoPartBridgePo) {
		return mobilebaseInfoPartBridgeService.baseInfoPartBridgeQueryList(dgm,
							baseInfoPartBridgePo);			
	}
	/**
	 * 保存桥梁信息
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo 
	 * @return 桥梁信息添加页面
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoPartBridge",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartBridgePo.getLng();
		String lat=baseInfoPartBridgePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
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
		map.put("data",baseInfoPartBridgePo);
		try {
			if(baseInfoPartBridgeService.addBaseInfoPartBridge(baseInfoPartBridgePo,objList) > 0){
				map.put("mes", "新增桥梁信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增桥梁信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增桥梁信息失败",e);
			}
			map.put("mes", "新增桥梁信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
