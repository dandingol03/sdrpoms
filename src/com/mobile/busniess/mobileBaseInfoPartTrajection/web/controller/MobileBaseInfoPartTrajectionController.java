package com.mobile.busniess.mobileBaseInfoPartTrajection.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoPartTrajection.service.MobileBaseInfoPartTrajectionService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;
import com.pc.busniess.baseInfoPartTrajection.service.BaseInfoPartTrajectionService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartTrajectionController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoPartTrajectionController.class);
	
	@Autowired
	private BaseInfoPartTrajectionService baseInfoHiddenTrajectionService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartTrajectionService mobilebaseInfoHiddenTrajectionService;
	
	/**
	 * 查询行人易穿行信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/BaseInfoPartTrajectionQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		return mobilebaseInfoHiddenTrajectionService.baseInfoHiddenTrajectionQueryList(dgm,baseInfoHiddenTrajectionPo); 
	}
	/**
	 * 保存行人易穿行信息
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addHiddenTrajection",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoHiddenTrajectionPo.getLng();
		String lat=baseInfoHiddenTrajectionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		baseInfoHiddenTrajectionPo.setTheGeom(theGeom);
		baseInfoHiddenTrajectionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("traj");
		baseInfoHiddenTrajectionPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[9];
				obj[0] = id;
				obj[1] = baseInfoHiddenTrajectionPo.getName();
				obj[2] = baseInfoHiddenTrajectionPo.getLng();
				obj[3] = baseInfoHiddenTrajectionPo.getLat();
				obj[4] = baseInfoHiddenTrajectionPo.getTheGeom();
				if(StringUtils.isBlank(baseInfoHiddenTrajectionPo.getRegion())){
					obj[5] =new ArrayList<String>().toString();
				}else{
					obj[5] =baseInfoHiddenTrajectionPo.getRegion();
				}
				obj[6] = baseInfoHiddenTrajectionPo.getOrgId();
				obj[7] = row.get("railId");
				obj[8] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		map.put("data",baseInfoHiddenTrajectionPo);
		try {
			if(baseInfoHiddenTrajectionService.addBaseInfoHiddenTrajection(baseInfoHiddenTrajectionPo,objList) > 0){
				map.put("mes", "新增行人易穿行部位信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增行人易穿行部位信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增行人易穿行部位信息失败",e);
			}
			map.put("mes", "新增行人易穿行部位信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
