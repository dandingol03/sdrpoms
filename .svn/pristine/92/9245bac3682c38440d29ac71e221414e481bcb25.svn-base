package com.mobile.busniess.mobileBaseInfoDefenceGuardStation.web.controller;

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

import com.mobile.busniess.mobileBaseInfoDefenceGuardStation.service.MobileBaseInfoDefenceGuardStationService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefenceGuardStation.po.BaseInfoDefenceGuardStationPo;
import com.pc.busniess.baseInfoDefenceGuardStation.service.BaseInfoDefenceGuardStationService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoDefenceGuardStationController {
	
	private static Logger logger = Logger.getLogger(MobileBaseInfoDefenceGuardStationController.class);
	@Autowired
	private MobileBaseInfoDefenceGuardStationService mobilebaseInfoDefenceGuardStationService;
	@Autowired
	private BaseInfoDefenceGuardStationService baseInfoDefenceGuardStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;	
	//护路工作站查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfDefenceGuardStationQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		return mobilebaseInfoDefenceGuardStationService.baseInfoDefenceGuardStationQueryList(dgm,
						baseInfoDefenceGuardStationPo);
	}
	/**
	 * 
	 * @param baseInfoDefenceGuardStationPo
	 * @return 保存护路工作站信息
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoDefenceGuardStation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String lng=baseInfoDefenceGuardStationPo.getLng();
			String lat=baseInfoDefenceGuardStationPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			if(orgId==""){
				map.put("mes", "请填写正确的经纬度坐标,范围:北京");
				map.put("success",false);
				return map;
			}
			baseInfoDefenceGuardStationPo.setTheGeom(theGeom);
			baseInfoDefenceGuardStationPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("gsta");
			baseInfoDefenceGuardStationPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = id;
					obj[1] = baseInfoDefenceGuardStationPo.getName();
					obj[2] = baseInfoDefenceGuardStationPo.getLng();
					obj[3] = baseInfoDefenceGuardStationPo.getLat();
					obj[4] = baseInfoDefenceGuardStationPo.getTheGeom();
					obj[5] = baseInfoDefenceGuardStationPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			map.put("data",baseInfoDefenceGuardStationPo);
			if (baseInfoDefenceGuardStationService.addBaseInfoDefenceGuardStation(baseInfoDefenceGuardStationPo,objList) > 0) {
				map.put("mes", "新增护路工作站信息成功");
				map.put("success",true);
			} else {
				map.put("mes", "新增护路工作站信息失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增护路工作站信息失败", e);
			}
			map.put("mes", "新增护路工作站信息失败");
			map.put("success",false);
		}
		return map;
	}
}
