package com.mobile.busniess.mobileBaseInfoPartCulvert.web.controller;

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

import com.mobile.busniess.mobileBaseInfoPartCulvert.service.MobileBaseInfoPartCulvertService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartCulvert.po.BaseInfoPartCulvertPo;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartCulvertController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoPartCulvertController.class);
	
	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartCulvertService mobilebaseInfoPartCulvertService;
	
	//涵洞查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPartCulvertQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		return mobilebaseInfoPartCulvertService.baseInfoPartCulvertQueryList(dgm,
						baseInfoPartCulvertPo);
	}
	/**
	 * 保存添加涵洞基本信息
	 * @param baseInfoPartCulvertPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addPartCulvert",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// TODO 保存添加涵洞基本信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartCulvertPo.getLng();
		String lat=baseInfoPartCulvertPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("success",false);
			return map;
		}
		baseInfoPartCulvertPo.setTheGeom(theGeom);
		baseInfoPartCulvertPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("culv");
		baseInfoPartCulvertPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartCulvertPo.getName();
				obj[2] = baseInfoPartCulvertPo.getLng();
				obj[3] = baseInfoPartCulvertPo.getLat();
				obj[4] = baseInfoPartCulvertPo.getTheGeom();
				obj[5] = baseInfoPartCulvertPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		map.put("data",baseInfoPartCulvertPo);
		try {
			if(baseInfoPartCulvertService.addBaseInfoPartCulvert(baseInfoPartCulvertPo,objList) > 0){
				map.put("mes", "新增涵洞信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增涵洞信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增涵洞信息失败",e);
			}
			map.put("mes", "新增涵洞信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
