package com.pc.busniess.patrolManagementTask.web.controller;

import java.io.UnsupportedEncodingException;
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

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;
import com.pc.busniess.patrolManagementTask.po.PatrolManagementTaskPo;
import com.pc.busniess.patrolManagementTask.service.PatrolDangerInfoService;
import com.pc.busniess.patrolManagementTask.service.PatrolManagementTaskService;

/**
 * 巡防队伍信息
 * @author jiawenlong
 * 巡防队伍信息
 * 隐患处置上报  调用 手机端接口
 */
@Controller
public class PatrolDangerInfoController {

	private static Logger logger = Logger
			.getLogger(PatrolDangerInfoController.class);

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
	/****************CRUD**************************** 隐患查询pc *****************************************************/
	/**
	 * 隐患处置上报查询
	 * @param dgm
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/dangerInfoDisposeQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dangerInfoDisposeQueryList(DataGridModel dgm,HttpServletRequest request,
			PatrolDangerInfoPo patrolDangerInfoPo,@RequestBody String mobileJSON) {
				return patrolDangerInfoService.dangerInfoQueryList(dgm,
						patrolDangerInfoPo);
	}
	/****************CRUD**************************** 隐患上报 *****************************************************/
	/**
	 * 查询未读count
	 */
	@RequestMapping(value = "/patrol/dangerCount",method = RequestMethod.POST)
	@ResponseBody
	public  Integer dangerCount(PatrolDangerInfoPo patrolDangerInfoPo) {
		return patrolDangerInfoService.dangerCount(patrolDangerInfoPo); 
	}
	/**
	 * 隐患上报界面打开
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/patrol/patrolDangerInfoInit", method = RequestMethod.GET)
	public String patrolDangerInfoInit(HttpServletRequest req) {
		return "/pc/bussiness/patrolManagementTask/dangerInfo/dangerInfoInit";
	}
	/**
	 * 隐患上报添加页面打开
	 * @param req
	 * @return
	 * 2018-01-04
	 * 添加隐患类别
	 */
	@RequestMapping(value = "/patrol/addDangerInfoPopWin", method = RequestMethod.GET)
	public String addDangerInfoPopWin(HttpServletRequest req) {
		// 参考铁路线 调用铁路信息
		req.setAttribute("railsInfo", baseInfoPartCulvertService.queryRails());
		//隐患类别
		List<Map<String,Object>> dangerTypeList = PubData.getDictList("DANGERS_TYPE");
		req.setAttribute("dangerTypeList",dangerTypeList);
		return "/pc/bussiness/patrolManagementTask/dangerInfo/addDangerInfoPopWin";
	}
	/**
	 * 隐患上报添加
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/addDangerInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addDangerInfo(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=patrolDangerInfoPo.getLng();
		String lat=patrolDangerInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
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
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (patrolDangerInfoService
					.addDangerInfo(patrolDangerInfoPo,objList) > 0) {
				map.put("mes", "新增隐患信息成功");
			} else {
				map.put("mes", "新增隐患信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增隐患信息失败", e);
			}
			map.put("mes", "新增隐患信息失败");
		}
		return map;
	}
	/**
	 * 隐患上报修改页面打开
	 * @param req
	 * @return
	 * 2018-01-04
	 * 添加隐患类别
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/patrol/updateDangerInfoPopWin", method = RequestMethod.GET)
	public String updateDangerInfoPopWin(HttpServletRequest req) throws UnsupportedEncodingException {
		//隐患类别
		List<Map<String,Object>> dangerTypeList = PubData.getDictList("DANGERS_TYPE");
		req.setAttribute("dangerTypeList",dangerTypeList);
		
		req.setAttribute("railsInfo", baseInfoPartCulvertService.queryRails());
		// 照片回显
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		
		return "/pc/bussiness/patrolManagementTask/dangerInfo/updateDangerInfoPopWin";
	}
	/**
	 * 保存修改隐患信息
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/updateDangerInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateDangerInfo(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=patrolDangerInfoPo.getLng();
		String lat=patrolDangerInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		patrolDangerInfoPo.setTheGeom(theGeom);
		patrolDangerInfoPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id type danger_type handle_status
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id type danger_type handle_status
				Object[] obj = new Object[11];
				obj[0] = patrolDangerInfoPo.getId();
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
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (patrolDangerInfoService
					.updateDangerInfo(patrolDangerInfoPo,objList) > 0) {
				map.put("mes", "更新隐患信息成功");
			} else {
				map.put("mes", "更新隐患信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改隐患信息失败", e);
			}
			map.put("mes", "修改隐患信息失败");
		}
		return map;
	}
	/**
	 * 删除隐患信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/patrol/deleteDangerInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteDangerInfo(
			@RequestParam("idList") List<String> idList) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = patrolDangerInfoService
					.deleteDangerInfo(idList);
			int sum = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j];
			}
			if (sum == idList.size()) {
				map.put("mes", "删除成功[" + sum + "]条隐患信息");
			} else {
				map.put("mes", "删除隐患信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除隐患信息失败", e);
			}
			map.put("mes", "删除隐患信息失败");
		}
		return map;
	}

	/***************RU***************************** 隐患处置 *****************************************************/
	/**
	 * 隐患处置主页面打开
	 * @param req
	 * @param dgm
	 * @param patrolManagementTaskPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/dangerInfoDisposeInit", method = RequestMethod.GET)
	public String dangerInfoDisposeInit(HttpServletRequest req, DataGridModel dgm,
			PatrolManagementTaskPo patrolManagementTaskPo) {
		return "/pc/bussiness/patrolManagementTask/danger/dangerInfoDisposeInit";
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/patrol/updateDangerInfoDisposePopWin", method = RequestMethod.GET)
	public String updateDangerInfoDisposePopWin(HttpServletRequest req) throws UnsupportedEncodingException {
		// 隐患状态
		List<Map<String, Object>> patrolHandleStatus = PubData
				.getDictList("PATROL_HANDLE_STATUS");
		req.setAttribute("patrolHandleStatusList", patrolHandleStatus);
		String photosName = new String(req.getParameter("photossName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "/pc/bussiness/patrolManagementTask/danger/updateDangerInfoDisposePopWin";
	}
	/**
	 * 保存修改隐患信息
	 * @param patrolDangerInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/updateDangerInfoDispose", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateDangerInfoDispose(
			PatrolDangerInfoPo patrolDangerInfoPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (patrolDangerInfoService
					.updateDangerInfoDispose(patrolDangerInfoPo) > 0) {
				map.put("mes", "更新隐患信息成功");
			} else {
				map.put("mes", "更新隐患信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改隐患信息失败", e);
			}
			map.put("mes", "修改隐患信息失败");
		}
		return map;
	}

}
