package com.mobile.busniess.mobileBaseInfoDefencePoliceHouse.web.controller;



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

import com.mobile.busniess.mobileBaseInfoDefencePoliceHouse.service.MobileBaseInfoDefencePoliceHouseService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefencePoliceHouse.po.BaseInfoDefencePoliceHousePo;
import com.pc.busniess.baseInfoDefencePoliceHouse.service.BaseInfoDefencePoliceHouseService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoDefencePoliceHouseController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoDefencePoliceHouseController.class);
	
	@Autowired
	private MobileBaseInfoDefencePoliceHouseService mobilebaseInfoDefencePoliceHouseService;
	@Autowired
	private BaseInfoDefencePoliceHouseService baseInfoDefencePoliceHouseService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	//派出所查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoDefencePoliceHouseQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		return mobilebaseInfoDefencePoliceHouseService.baseInfoDefencePoliceHouseQueryList(dgm,
						baseInfoDefencePoliceHousePo);
	}
	/**
	 * 保存派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoDefencePoliceHouse",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addbaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoDefencePoliceHousePo.getLng();
		String lat=baseInfoDefencePoliceHousePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
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
		map.put("data",baseInfoDefencePoliceHousePo);
		try {
			if(baseInfoDefencePoliceHouseService.addBaseInfoDefencePoliceHouse(baseInfoDefencePoliceHousePo,objList) > 0){
				map.put("mes", "新增派出所信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增派出所信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增派出所信息失败",e);
			}
			map.put("mes", "新增派出所信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
