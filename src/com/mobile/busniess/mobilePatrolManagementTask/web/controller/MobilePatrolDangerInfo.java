/**   
 * @Package: com.mobile.busniess.mobilePatrolManagementTask.web.controller 
 * @author: jwl   
 * @date: 2018年5月17日 上午9:49:13 
 */
package com.mobile.busniess.mobilePatrolManagementTask.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobilePatrolManagementTask.service.MobilePatrolDangerInfoService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;
import com.pc.busniess.patrolManagementTask.service.PatrolDangerInfoService;
import com.pc.busniess.patrolManagementTask.service.PatrolManagementTaskService;

/**   
 * @Package: com.mobile.busniess.mobilePatrolManagementTask.web.controller 
 * @author: jwl   
 * @date: 2018年5月17日 上午9:49:13 
 */
@Controller
public class MobilePatrolDangerInfo {
	
	private static Logger logger = Logger.getLogger(MobilePatrolDangerInfo.class);

	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;//注入涵洞
	@Autowired
	private PatrolManagementTaskService patrolManagementTaskService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private PatrolDangerInfoService patrolDangerInfoService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private MobilePatrolDangerInfoService mobilepatrolDangerInfoService;
	
	//隐患查询接口  ----权限
	@RequestMapping(value = "/mobile/patrol/dangerInfoDisposeQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,PatrolDangerInfoPo patrolDangerInfoPo) {
		return mobilepatrolDangerInfoService.dangerInfoQueryList(dgm,patrolDangerInfoPo);
	}
	/**
	 * 隐患上报添加
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/patrol/addDangerInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDangerInfo(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=patrolDangerInfoPo.getLng();
		String lat=patrolDangerInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		patrolDangerInfoPo.setTheGeom(theGeom);
		patrolDangerInfoPo.setOrgId(orgId);
		patrolDangerInfoPo.setType("1");//预警状态 0无 1有
		//id name lng lat the_geom org_id rail_id type danger_type handle_status
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("Dang");
		patrolDangerInfoPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id type danger_type handle_status
				Object[] obj = new Object[11];
				obj[0] = id;
				obj[1] = patrolDangerInfoPo.getName();
				obj[2] = patrolDangerInfoPo.getLng();
				obj[3] = patrolDangerInfoPo.getLat();
				obj[4] = patrolDangerInfoPo.getTheGeom();
				obj[5] = patrolDangerInfoPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = patrolDangerInfoPo.getType();
				obj[8] = patrolDangerInfoPo.getDangerType();
				obj[9] = patrolDangerInfoPo.getHandleStatus();
				obj[10] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		map.put("data",patrolDangerInfoPo);
		try {
			if (patrolDangerInfoService
					.addDangerInfo(patrolDangerInfoPo,objList) > 0) {
				map.put("mes", "新增隐患信息成功");
				map.put("success",true);
			} else {
				map.put("mes", "新增隐患信息失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增隐患信息失败", e);
			}
			map.put("mes", "新增隐患信息失败");
			map.put("success",false);
		}
		return map;
	}
	
	/**
	 * 保存修改隐患信息
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/patrol/updateDangerInfoDispose", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDangerInfoDispose(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",patrolDangerInfoPo);
		try {
			if (patrolDangerInfoService.updateDangerInfoDispose(patrolDangerInfoPo) > 0) {
				map.put("mes", "更新隐患信息成功");
				map.put("success",true);
			} else {
				map.put("mes", "更新隐患信息失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改隐患信息失败", e);
			}
			map.put("mes", "修改隐患信息失败");
			map.put("success",false);
		}
		return map;
	}
	//隐患findbyid
	@RequestMapping(value = "/mobile/patrol/findByDangerId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByDangerId(@RequestParam("id") String id) {
		return mobilepatrolDangerInfoService.findByDangerId(id);
	}
}
