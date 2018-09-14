package com.pc.exhibition.map.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.security.po.PubUsers;
import com.pc.exhibition.map.service.ExMapService;

@Controller
public class ExMapController {
	@Autowired
	private ExMapService exMapService;
	/**
	 * 根据当前机构获得相关安保区
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getSecurityareaBJBorderInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSecurityareaBJBorderInfoList(HttpServletRequest request,@RequestParam(value = "orgId") String orgId) {
		// TODO 
		List<Map<String,Object>> resultListMap = exMapService.getSecurityareaBJBorderInfoList(orgId);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", resultListMap);
		return resultMap;
	}
	/**
	 * 根据当前用户获得相关边界
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJBorderInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJBorderInfoList(HttpServletRequest request) {
		// TODO 铁路
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			String orgId = user.getUserOrg().toLowerCase();
			List<Map<String,Object>> resultListMap = exMapService.getBJBorderInfoList(orgId);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			resultMap.put("orgId", orgId);
			Map<String,Object> tempInfoMap = exMapService.getOrgZoomAndCenter(orgId);
			resultMap.put("lng", tempInfoMap.get("X"));
			resultMap.put("lat", tempInfoMap.get("Y"));
			resultMap.put("zoom", tempInfoMap.get("Zoom"));
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 获得当前用户下涉路机构详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getUserOrgListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserOrgListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "orgLevel", required = false) String level) {
		// TODO 获得当前用户下机构统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			if(level==null||level.equals("undefined")||level.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getUserOrgListInfo(orgId,level);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关铁路信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJRailWayInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJRailWayInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 铁路
		if(railIdList==null){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", "");
			return resultMap;
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJRailWayInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关桥涵信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJBridgeCulvertInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJBridgeCulvertInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 桥涵
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJBridgeCulvertInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关道口信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJJunctionInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJJunctionInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 道口
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJJunctionInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关隧道信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJTunneltInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJTunneltInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 隧道
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJTunneltInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关车站以及站场信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJStationInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJStationInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 车站
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJStationInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关行人易穿行信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJTrajectionInfoList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getBJTrajectionInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 行人易穿行
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJTrajectionInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			JSONObject resultJson = JSONObject.fromObject(resultMap);
			return resultJson;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点人信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJKeypersonInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJKeypersonInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 重点人
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJKeypersonInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关隐患点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJDangerInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJDangerInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList,
			@RequestParam(value = "dangerType", required = false) String dangerType) {
		// TODO 隐患点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJDangerInfoList(orgId, railIdList,dangerType);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关工作站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJGuardStationInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJGuardStationInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 工作站
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJGuardStationInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关宣传点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJPropagandaInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJPropagandaInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 宣传点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJPropagandaInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关警示柱信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJBroadcastInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJBroadcastInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 隐患点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJBroadcastInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关摄像头信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJVideoMonitorInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJVideoMonitorInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 隐患点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJVideoMonitorInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关派出所信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJPoliceHouseInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJPoliceHouseInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 派出所
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJPoliceHouseInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关警务站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJPoliceStationInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJPoliceStationInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 隐患点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJPoliceStationInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关专职队员信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJTeamMemberInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJTeamMemberInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 专职队员
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJTeamMemberInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关在线干部信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getBJCardeInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJCardeInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 隐患点
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getBJCardeInfoList(orgId, railIdList);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得周边单位信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/map/getUserPeripheralPlaceInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserPeripheralPlaceInfoList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList,
			@RequestParam(value = "perPlaceId", required = false) List<String> perPlaceIdList,
			@RequestParam(value = "isImportant", required = false) String isImportant) {
		// TODO 根据当前用户机构和铁路获得周边单位信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			List<Map<String,Object>> resultListMap = exMapService.getUserPeripheralPlaceInfoList(orgId, railIdList,perPlaceIdList,isImportant);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", resultListMap);
			return resultMap;
		}
		return null;
	}
	//北京测试实地考察
	@RequestMapping(value = "exhibition/map/getBJVideoInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJVideoInfoList() {
		List<Map<String,Object>> resultListMap = exMapService.getBJVideoInfoList();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", resultListMap);
		return resultMap;
	}
	@RequestMapping(value = "exhibition/map/getBJCSPoliceHouseInfoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBJCSPoliceHouseInfoList() {
		List<Map<String,Object>> resultListMap = exMapService.getBJCSPoliceHouseInfoList();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", resultListMap);
		return resultMap;
	}
}