package com.mobile.busniess.mobileBaseInfoDefencePoliceStation.web.controller;



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

import com.mobile.busniess.mobileBaseInfoDefencePoliceStation.service.MobileBaseInfoPoliceStationService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefencePoliceStation.po.BaseInfoPoliceStationPo;
import com.pc.busniess.baseInfoDefencePoliceStation.service.BaseInfoPoliceStationService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPoliceStationController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoPoliceStationController.class);
	
	@Autowired
	private BaseInfoPoliceStationService baseInfoPoliceStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPoliceStationService mobilebaseInfoPoliceStationService;

	//警务站查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPoliceStationQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		return mobilebaseInfoPoliceStationService.baseInfoPoliceStationQueryList(dgm,
						baseInfoPoliceStationPo);
	}
	/**
	 * 保存警务站信息
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoPoliceStation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addbaseInfoPoliceStation(BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPoliceStationPo.getLng();
		String lat=baseInfoPoliceStationPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
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
		map.put("data",baseInfoPoliceStationPo);
		try {
			if(baseInfoPoliceStationService.addBaseInfoPoliceStation(baseInfoPoliceStationPo,objList) > 0){
				map.put("mes", "新增警务站信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增警务站信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增警务站信息失败",e);
			}
			map.put("mes", "新增警务站信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
