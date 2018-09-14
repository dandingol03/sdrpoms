package com.mobile.busniess.mobileBaseInfoPeripheralPlace.web.controller;


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

import com.mobile.busniess.mobileBaseInfoPeripheralPlace.service.MobileBaseInfoPeripheralPlaceService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;
import com.pc.busniess.baseInfoPeripheralPlace.service.BaseInfoPeripheralPlaceService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.web.controller 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:44:44
 */
@Controller
public class MobileBaseInfoPeripheralPlaceController {
	
	private static Logger logger = Logger.getLogger(MobileBaseInfoPeripheralPlaceController.class);
	
	@Autowired
	private BaseInfoPeripheralPlaceService baseInfoPeripheralPlaceService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPeripheralPlaceService mobilebaseInfoPeripheralPlaceService;
	/**
	 * 查询
	 * @param dgm
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	@RequestMapping(value="/mobile/baseInfo/baseInfoPeripheralPlaceQueryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> baseInfoPeripheralPlaceQueryList(DataGridModel dgm,BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		return mobilebaseInfoPeripheralPlaceService.baseInfoPeripheralPlaceQueryList(dgm, baseInfoPeripheralPlacePo);
	}
	/**
	 * 保存新增数据
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	@RequestMapping(value="/mobile/baseInfo/addBaseInfoPeripheralPlace",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addBaseInfoPeripheralPlace(BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		Map<String,Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPeripheralPlacePo.getLng();
		String lat=baseInfoPeripheralPlacePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		baseInfoPeripheralPlacePo.setTheGeom(theGeom);
		baseInfoPeripheralPlacePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("plac");
		baseInfoPeripheralPlacePo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPeripheralPlacePo.getName();
				obj[2] = baseInfoPeripheralPlacePo.getLng();
				obj[3] = baseInfoPeripheralPlacePo.getLat();
				obj[4] = baseInfoPeripheralPlacePo.getTheGeom();
				obj[5] = baseInfoPeripheralPlacePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		try {
			map.put("data", baseInfoPeripheralPlacePo);
			if(baseInfoPeripheralPlaceService.addBaseInfoPeripheralPlace(baseInfoPeripheralPlacePo,objList)>0){
				map.put("mes", "新增周边场所信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增周边场所信息失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增周边场所信息失败",e);
			}
			map.put("mes", "新增周边场所信息失败");
			map.put("success",false);
		}
		return map;
	}
}
