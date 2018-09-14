package com.mobile.busniess.mobileVideoMonitorInfo.web.controller;


/**
 *  jwl
 *  监控Controller 
 */

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.videoMonitorInfo.po.VideoMonitorInfoPo;
import com.pc.busniess.videoMonitorInfo.service.VideoMonitorInfoService;

@Controller
public class MobileVideoMonitorInfoController {

	private static Logger logger = Logger.getLogger(MobileVideoMonitorInfoController.class);
	
	@Autowired
	private VideoMonitorInfoService videoMonitorInfoService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 保存监控信息
	 * @param videoMonitorInfoPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addVideoMonitorInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addvideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=videoMonitorInfoPo.getLng();
		String lat=videoMonitorInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		videoMonitorInfoPo.setTheGeom(theGeom);
		videoMonitorInfoPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("vide");
		videoMonitorInfoPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = videoMonitorInfoPo.getName();
				obj[2] = videoMonitorInfoPo.getLng();
				obj[3] = videoMonitorInfoPo.getLat();
				obj[4] = videoMonitorInfoPo.getTheGeom();
				obj[5] = videoMonitorInfoPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		map.put("data",videoMonitorInfoPo);
		try {
			if(videoMonitorInfoService.addvideoMonitorInfo(videoMonitorInfoPo,objList) > 0){
				map.put("mes", "新增监控信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增监控信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增监控信息失败",e);
			}
			map.put("mes", "新增监控信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
