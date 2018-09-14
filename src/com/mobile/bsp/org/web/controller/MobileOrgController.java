/**   
 * @Package: com.mobile.bsp.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:14:41 
 */
package com.mobile.bsp.org.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.bsp.org.po.ComboTreeModel;
import com.mobile.bsp.org.service.MobileOrgService;
import com.pc.bsp.org.po.Gps;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.org.service.OrgService;
import com.pc.bsp.org.web.controller.OrgController;


/**   
 * @Package: com.mobile.bsp.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:14:41 
 */
@Controller
public class MobileOrgController {
	private static Logger logger = Logger.getLogger(OrgController.class);
	
	@Autowired
	private MobileOrgService mobileOrgService;
	@Autowired
	private OrgService orgService;
	/**
	 * 根据当前登录用户过滤机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mobile/org/comTree")
	@ResponseBody
	public void comTree(HttpServletResponse response, @RequestParam String pid) {

		List<OrgDesc> comOrg = mobileOrgService.getComOrgList(pid);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e);
		}
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();

		for (int i = 0; i < comOrg.size(); i++) {

			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comOrg.get(i).getId());
			ctm.setText(comOrg.get(i).getName());
			ctm.setOrgLevel(comOrg.get(i).getOrgLevel());
			String isParent = comOrg.get(i).getIsParent();
			if (isParent.equals("1")) {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("closed");
				list.add(ctm);
			} else {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("open");
				list.add(ctm);
			}
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
	}
	/**
	 * 根据orgName获取机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mobile/org/findByOrgName",method=RequestMethod.POST)
	@ResponseBody
	public void findByOrgName(HttpServletResponse response,@RequestParam String orgName) {
		List<OrgDesc> comOrg = mobileOrgService.findByOrgName(orgName);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e);
		}
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();

		for (int i = 0; i < comOrg.size(); i++) {

			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comOrg.get(i).getId());
			ctm.setText(comOrg.get(i).getName());
			ctm.setOrgLevel(comOrg.get(i).getOrgLevel());
			String isParent = comOrg.get(i).getIsParent();
			if (isParent.equals("1")) {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("closed");
				list.add(ctm);
			} else {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("open");
				list.add(ctm);
			}
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
	}
	@RequestMapping(value = "/mobile/org/checkOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkGuardOrgId(HttpServletRequest req,Gps gps) {
		Map<String,Object> map=new HashMap<String, Object>(2);
		try {
			if(orgService.getOrg(gps.getLng(), gps.getLat())!=""){
				map.put("success", true);
				return map;
			}else{
				map.put("success", false);
				return map;
			}
		}catch (Exception e) {
			map.put("success", false);
			return map;
		}
	}
}
