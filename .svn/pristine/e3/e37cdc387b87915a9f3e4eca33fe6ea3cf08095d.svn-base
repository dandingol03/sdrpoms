package com.mobile.busniess.mobileBaseInfoPartJunction.web.controller;
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

import com.mobile.busniess.mobileBaseInfoPartJunction.service.MobileBaseInfoPartJunctionService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;
import com.pc.busniess.baseInfoPartJunction.service.BaseInfoPartJunctionService;
import com.pc.busniess.baseInfoPartJunction.web.controller.BaseInfoPartJunctionController;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartJunctionController {
	
	private static Logger logger = Logger.getLogger(BaseInfoPartJunctionController.class);
	@Autowired
	private BaseInfoPartJunctionService baseInfoPartJunctionService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartJunctionService mobilebaseInfoPartJunctionService;

	//道口查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPartJunctionQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
			return mobilebaseInfoPartJunctionService.baseInfoPartJunctionQueryList(dgm,
						baseInfoPartJunctionPo);
	}
	
	/**
	 * 保存道口信息
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoPartJunction",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//TODO 保存道口信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartJunctionPo.getLng();
		String lat=baseInfoPartJunctionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		baseInfoPartJunctionPo.setTheGeom(theGeom);
		baseInfoPartJunctionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("junc");
		baseInfoPartJunctionPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartJunctionPo.getName();
				obj[2] = baseInfoPartJunctionPo.getLng();
				obj[3] = baseInfoPartJunctionPo.getLat();
				obj[4] = baseInfoPartJunctionPo.getTheGeom();
				obj[5] = baseInfoPartJunctionPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		map.put("data",baseInfoPartJunctionPo);
		try {
			if(baseInfoPartJunctionService.addBaseInfoPartJunction(baseInfoPartJunctionPo,objList) > 0){
				map.put("mes", "新增道口信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增道口信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增道口信息失败",e);
			}
			map.put("mes", "新增道口信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
