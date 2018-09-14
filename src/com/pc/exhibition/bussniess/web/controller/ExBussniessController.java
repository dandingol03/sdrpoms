package com.pc.exhibition.bussniess.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.PhoneUtils;
import com.pc.bsp.security.po.PubUsers;
import com.pc.bsp.user.service.UserService;
import com.pc.exhibition.bussniess.service.ExBussniessService;
import com.pc.exhibition.map.service.ExMapService;

@Controller
public class ExBussniessController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ExBussniessService exBussniessService;
	
	@Autowired
	private ExMapService exMapService;
	
	//private static Logger logger = Logger.getLogger(ExBussniessController.class);

	//跳转到大屏展示界面
	@RequestMapping(value="/exhibition/welcome",method=RequestMethod.GET)
    public String exWelcome(HttpSession session, HttpServletRequest request){
		// TODO 数据分析
		if(PhoneUtils.isFromMobile(request)) {
			return  "redirect:/welcome";
		}
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			if(user.getEnable().equals("0")){
				//用户锁定
				return "pc/bsp/security/lock";
			} else {
				//用户登录成功后将登录错误次数清零
				userService.updateEnable(user.getUserAccount(), "0", "1");
			}
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userAccount", user.getUserAccount());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userOrg", user.getUserOrg());
			
			//获得中心点和放大倍数
			Map<String,Object> orgZoomAndCenter = exMapService.getOrgZoomAndCenter(user.getUserOrg());
			request.setAttribute("orgZoomAndCenter", orgZoomAndCenter);
			//获得当前用户所属机构
			String orgId = user.getUserOrg().toLowerCase();
			request.setAttribute("orgId", orgId);
			/*//获得当前用户机构列表
			List<OrgDesc> orgs = orgService.getAreaOrgList(user.getUserOrg());
			request.setAttribute("orgs", orgs);*/
			return "pc/exhibition/mapexhibition";
		}else{
			//session过期或无session
			return "pc/bsp/security/logout";
		}
    }
	
	/**
	 * 获得当前用户下机构列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserOrgList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserOrgList(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId) {
		// TODO 获得当前用户下机构统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserOrgList(orgId);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 获得当前用户下机构统计信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserOrgListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserOrgListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId) {
		// TODO 获得当前用户下机构统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserOrgListInfo(orgId);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构下相关铁路统计信息
	 * @param request
	 * @param @param orgId
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserRailListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserRailListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId) {
		// TODO 根据当前用户机构下相关铁路统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserRailListInfo(orgId);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点部位统计信息(隐患点除外)
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserKeyPartListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserKeyPartListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 根据当前用户机构和铁路获得相关重点部位统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserKeyPartListInfo(orgId, railIdList);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得隐患点类型统计
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/getPlaceDangerListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPlaceDangerListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 根据当前用户机构和铁路获得隐患点类型统计
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getPlaceDangerListInfo(orgId, railIdList);
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关联防点位统计信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserJDenfenseListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserJDenfenseListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 根据当前用户机构和铁路获得相关联防点位统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserJDenfenseListInfo(orgId, railIdList);
			return resultMap;
			
			
		}
		return null;
	}
	
	/**
	 * 根据当前用户机构和铁路获得周边单位统计信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	@RequestMapping(value = "exhibition/getUserPeripheralPlaceListInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserPeripheralPlaceListInfo(HttpServletRequest request,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "railId", required = false) List<String> railIdList) {
		// TODO 根据当前用户机构和铁路获得周边单位统计信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			//获得当前用户所属机构
			if(orgId==null||orgId.equals("undefined")||orgId.equals("")){
				orgId = user.getUserOrg().toLowerCase();
			}
			Map<String,Object> resultMap = exBussniessService.getUserPeripheralPlaceListInfo(orgId, railIdList);
			return resultMap;
		}
		return null;
	}
}
