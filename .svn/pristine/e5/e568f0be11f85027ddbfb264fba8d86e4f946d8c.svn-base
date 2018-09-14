package com.mobile.busniess.mobileBaseInfoDefenceBroadcast.web.controller;

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

import com.mobile.busniess.mobileBaseInfoDefenceBroadcast.service.MobileBaseInfoDefenceBroadcastService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoDefenceBroadcastController {
	
	private static Logger logger = Logger.getLogger(MobileBaseInfoDefenceBroadcastController.class);
	@Autowired
	private MobileBaseInfoDefenceBroadcastService mobilebaseInfoDefenceBroadcastService;
	@Autowired
	private BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	//警示柱查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoDefenceBroadcastQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		return mobilebaseInfoDefenceBroadcastService.baseInfoDefenceBroadcastQueryList(dgm,
				baseInfoDefenceBroadcastPo);
	}
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoDefenceBroadcast", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String lng=baseInfoDefenceBroadcastPo.getLng();
			String lat=baseInfoDefenceBroadcastPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			if(orgId==""){
				map.put("mes", "请填写正确的经纬度坐标,范围:北京");
				map.put("success",false);
				return map;
			}
			baseInfoDefenceBroadcastPo.setTheGeom(theGeom);
			baseInfoDefenceBroadcastPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("broa");
			baseInfoDefenceBroadcastPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[11];
					obj[0] = id;
					obj[1] = baseInfoDefenceBroadcastPo.getName();
					obj[2] = baseInfoDefenceBroadcastPo.getLng();
					obj[3] = baseInfoDefenceBroadcastPo.getLat();
					obj[4] = baseInfoDefenceBroadcastPo.getTheGeom();
					obj[5] = baseInfoDefenceBroadcastPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					obj[8] = baseInfoDefenceBroadcastPo.getNumber();
					obj[9] = baseInfoDefenceBroadcastPo.getPort();
					obj[10] = baseInfoDefenceBroadcastPo.getIp();
					objList.add(obj);
				}	
			}
			map.put("data",baseInfoDefenceBroadcastPo);
			if (baseInfoDefenceBroadcastService.addBaseInfoDefenceBroadcast(baseInfoDefenceBroadcastPo,objList) > 0) {
				map.put("mes", "新增广播警示柱信息成功");
				map.put("success",true);
			} else {
				map.put("mes", "新增广播警示柱信息失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增广播警示柱信息失败", e);
			}
			map.put("mes", "新增广播警示柱信息失败");
			map.put("success",false);
		}
		return map;
	}
}
