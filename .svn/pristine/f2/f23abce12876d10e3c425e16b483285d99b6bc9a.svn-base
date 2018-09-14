package com.pc.bsp.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pc.bsp.authority.po.Authority;
import com.pc.bsp.authority.po.AuthorityResource;
import com.pc.bsp.authority.service.AuthorityService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.service.MyInvocationSecurityMetadataSourceService;

/**
 * @author simple
 *
 */
@Controller
public class AuthorityController {

	private static Logger logger = Logger.getLogger(AuthorityController.class);

	@Autowired
	private AuthorityService authorityService;

	/**
	 * 通过菜单进入权限管理页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/authority", method = RequestMethod.GET)
	public String list(Model model) {
		return "pc/bsp/authority/authority";
	}

	/**
	 * 默认分页查询权限信息
	 * 
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value = "/authority/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Authority authority) {
		return authorityService.getPageList(dgm, authority);
	}

	/**
	 * 进入权限添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/authority/addPopWin", method = RequestMethod.GET)
	public String popWin4Add() {
		return "pc/bsp/authority/addAuthorityPopWin";
	}

	/**
	 * 保存添加用户
	 * 
	 * @param authority
	 * @return
	 */
	@RequestMapping(value = "/authority/addAuthority", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAuthority(Authority authority) {
		// 添加主键
		authority.setAuthorityId(NextID.getNextID("AUTH"));

		Map<String, String> map = new HashMap<String, String>();
		try {
			if (authorityService.saveAuthority(authority) > 0) {
				map.put("mes", "添加权限成功");
			} else {
				map.put("mes", "添加权限失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("添加权限失败", e);
			}

			map.put("mes", "添加权限失败");
		}
		return map;
	}

	/**
	 * 进入权限修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/authority/updatePopWin", method = RequestMethod.GET)
	public String popWin4Update() {
		return "pc/bsp/authority/updateAuthorityPopWin";
	}

	/**
	 * 保存修改后的权限信息
	 * 
	 * @param authority
	 * @return
	 */
	@RequestMapping(value = "/authority/updateAuthority", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateAuthority(Authority authority) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			if (authorityService.updateAuthority(authority) > 0) {
				map.put("mes", "更新权限成功");
			} else {
				map.put("mes", "更新权限失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("更新权限失败", e);
			}
			map.put("mes", "更新权限失败");
		}
		return map;
	}

	/**
	 * 批量删除权限
	 * 
	 * @param authorityIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authority/delAuthorities", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delAuthorities(
			@RequestParam("authorityId") List<String> authorityIdList) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = authorityService.delAuthorityBatch(authorityIdList);
			int sum = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j];
			}
			if (sum == authorityIdList.size()) {
				map.put("mes", "删除成功[" + sum + "]条权限记录");
			} else {
				map.put("mes", "删除权限失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除权限失败", e);
			}
			map.put("mes", "删除权限失败");
		}
		return map;// 重定向
	}

	/**
	 * 进入权限注册菜单页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/authority/updateResPopWin", method = RequestMethod.GET)
	public String popWin4Res(@RequestParam("authorityId") String authorityId,
			HttpServletRequest req) {
		req.setAttribute("checkedAuthId", authorityId);
		return "pc/bsp/authority/regResMenuPopWin";
	}

	/**
	 * 获取选中权限的所有资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/authority/getMyRes", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getMyRes(@RequestParam("checkedAuthId") String authorityId) {
		return authorityService.getAuthResList(authorityId);
	}

	/**
	 * 保存权限菜单资源对应关系
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/authority/updateAuthRes", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateAuthRes(
			@RequestParam("resourceIds") String resourceIds,
			@RequestParam("checkedAuthId") String authorityId,
			HttpSession session) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			// 组装AuthorityResource对象
			AuthorityResource authRes = new AuthorityResource();
			authRes.setAuthorityId(authorityId);

			List<Object[]> objList = new ArrayList<Object[]>();

			// 组装插入数据
			if (resourceIds.length() > 0) {
				String[] ids = resourceIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = authorityId;
					obj[2] = ids[i];

					objList.add(obj);
				}
			}

			if (authorityService.saveAuthRes(authRes, objList) >= 0) {
				// 更新成功，需要重新加载资源定义
				WebApplicationContext context = WebApplicationContextUtils
						.getWebApplicationContext(session.getServletContext());
				MyInvocationSecurityMetadataSourceService my = context
						.getBean(com.pc.bsp.security.service.MyInvocationSecurityMetadataSourceService.class);
				my.reloadResourceDefine();
				map.put("mes", "更新权限资源成功");
			} else {
				map.put("mes", "更新权限资源失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("更新权限资源失败", e);
			}
			map.put("mes", "更新权限资源失败");
		}
		return map;
	}

	/**
	 * 进入权限注册资源页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/authority/regResPopWin", method = RequestMethod.GET)
	public String popWin4RegResource(
			@RequestParam("authorityId") String authorityId, 
			HttpServletRequest req) {
		req.setAttribute("checkedAuthId", authorityId);
		return "pc/bsp/authority/regResAuthPopWin";
	}
	
	/**
	 * 查询该权限已经注册的资源列表
	 * 
	 * @param dgm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/authority/queryHasRegResList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryHasRegResList(DataGridModel dgm,
			@RequestParam(value="checkedAuthId",required=false) String authorityId) {
		if(authorityId!=null){
			return authorityService.queryHasRegResList(dgm, authorityId);
		}
		return null;
	}

	/**
	 * 查询该权限未注册的资源列表
	 * 
	 * @param dgm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/authority/queryNotRegResList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNotRegResList(DataGridModel dgm,
			@RequestParam(value="checkedAuthId",required=false) String authorityId) {
		if(authorityId!=null){
			return authorityService.queryNotRegResList(dgm, authorityId);
		}
		return null;
	}

	/**
	 * 删除某权限的资源对应关系
	 * @param resourceIdList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/authority/deleteAuthResource", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteAuthResource(
			@RequestParam("resourceId") List<String> resourceIdList,
			@RequestParam("checkedAuthId") String authorityId,
			HttpSession session) {

		Map<String, String> map = new HashMap<String, String>(1);
		try {
			authorityService.deleteAuthResource(resourceIdList,authorityId);
			WebApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(session.getServletContext());
			MyInvocationSecurityMetadataSourceService my = context
					.getBean(com.pc.bsp.security.service.MyInvocationSecurityMetadataSourceService.class);
			my.reloadResourceDefine();
			map.put("mes", "删除成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除资源失败", e);
			}
			map.put("mes", "删除资源失败");
		}
		return map;// 重定向
	}
	
	/**
	 * 增加某权限的资源对应关系
	 * @param resourceIdList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/authority/addAuthResource", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addAuthResource(
			@RequestParam("resourceId") List<String> resourceIdList,
			@RequestParam("checkedAuthId") String authorityId,
			HttpSession session) {

		Map<String, String> map = new HashMap<String, String>(1);
		try {
			authorityService.addAuthResource(resourceIdList,authorityId);
			WebApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(session.getServletContext());
			MyInvocationSecurityMetadataSourceService my = context
					.getBean(com.pc.bsp.security.service.MyInvocationSecurityMetadataSourceService.class);
			my.reloadResourceDefine();
			map.put("mes", "增加成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("增加资源失败", e);
			}
			map.put("mes", "增加资源失败");
		}
		return map;// 重定向
	}
}
