/**
 * 
 */
package com.pc.bsp.org.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.po.ComboTreeModel;
import com.pc.bsp.org.po.Gps;
import com.pc.bsp.org.po.Org;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.org.service.OrgService;
import com.pc.bsp.org.util.GenOrgDescID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.bsp.user.service.UserService;

/**
 * @author simple
 *
 */
@Controller
public class OrgController {

	private static Logger logger = Logger.getLogger(OrgController.class);
	
	@Autowired
	private OrgService orgService;

	@Autowired
	private UserService userService;

	/**
	 * 通过菜单进入用户管理页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/org", method = RequestMethod.GET)
	public String list(Model model) {
		return "pc/bsp/org/org";
	}

	/**
	 * 根据当前登录用户过滤机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/getUserOrg")
	@ResponseBody
	public List<OrgDesc> getUserOrg() {
		// 获取当前登录用户信息
		String userOrg = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			userOrg = ((PubUsers) principal).getUserOrg();
		}
		return orgService.getUserOrgList(userOrg);
	}

	/**
	 * 根据当前登录用户过滤机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/comTree")
	@ResponseBody
	public void comTree(HttpServletResponse response, @RequestParam String pid) {

		List<OrgDesc> comOrg = orgService.getComOrgList(pid);

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
	 * 默认分页查询机构信息
	 * 
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value = "/org/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Org org) {
		return orgService.getPageList(dgm, org);
	}

	/**
	 * 进入机构添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/addPopWin", method = RequestMethod.GET)
	public String popWin4Add() {
		return "pc/bsp/org/addOrgPopWin";
	}

	/**
	 * 保存添加机构
	 * 
	 * @param org
	 * @return
	 */
	@RequestMapping(value = "/org/addOrg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrg(Org org) {

		// 生成机构主键(机构表和机构描述表共用)
		String orgId = NextID.getNextID("org");

		// 组装机构描述对象
		OrgDesc orgDesc = new OrgDesc();
		OrgDesc orgPDesc = orgService.getOrgDescByOrgId(org.getOrgId());
		String pId = orgPDesc.getId(); // 父节点，通过前台的树传过来时就是机构描述id
		String id = GenOrgDescID.get4NewId(pId, orgService.getSubOrgMaxId(pId)); // 机构描述码，根据父节点和最大子节点计算
		String orgLevel = id.length() / 4 + ""; // 机构级别，根据机构描述码长度计算
		String isParent = "0"; // 是否父节点，默认【否】，2014-05-08启用该字段，用于comboTree获取机构树的一个参数
		String open = "0"; // 节点是否打开，默认都是关闭
		orgDesc.setId(id);
		orgDesc.setOrgId(orgId);
		orgDesc.setOrgLevel(orgLevel);
		orgDesc.setpId(pId);
		orgDesc.setIsParent(isParent);
		orgDesc.setOpen(open);

		// 组装完善机构对象
		org.setOrgId(orgId); // 系统生成

		Map<String, String> map = new HashMap<String, String>();
		if (orgService.saveOrgDesc(org, orgDesc) > 0) {

			// 更新上级机构的IsParent状态，2014-05-08添加
			orgService.updateIsParent(pId);

			map.put("mes", "添加机构成功");
		} else {
			map.put("mes", "添加机构失败");
		}

		return map;
	}

	/**
	 * 进入机构修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/updatePopWin", method = RequestMethod.GET)
	public String popWin4Update(@RequestParam("orgDescPId") String orgDescPId,
			HttpServletRequest request) {
		request.setAttribute("orgDescPId",orgDescPId);
		if(!orgDescPId.equals("0")){
			OrgDesc orgDesc =orgService.getOrgDescById(orgDescPId); 
			String orgId = orgDesc.getOrgId().toString();
			String orgName = orgService.getOrgByOrgId(orgId).getOrgName().toString();
			request.setAttribute("orgPaId",orgId);
			request.setAttribute("orgPaName",orgName);
		}
		return "pc/bsp/org/updateOrgPopWin";
	}

	/**
	 * 保存修改后的机构信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/org/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOrg(
			@RequestParam("updesc") int upDesc,
			@RequestParam("oldDescId") String oldDescId,
			@RequestParam("orgPName") String orgNewPID, 
			Org org) {
		
		Map<String, String> map = new HashMap<String, String>();

		// 没有修改上级机构(只更新Org对象)
		if (upDesc == 0) {
			if (orgService.updateOrg(org) > 0) {
				map.put("mes", "修改机构成功");
			} else {
				map.put("mes", "修改机构失败");
			}
		} else { // 修改了上级机构
			OrgDesc orgDescNew = orgService.getOrgDescByOrgId(orgNewPID);
			String newDescPID = orgDescNew.getId();
			if (newDescPID.contains(oldDescId)) {
				map.put("mes", "不能将机构变更到自身或下级机构中");
				return map;
			}
			// 组装机构描述对象
			OrgDesc orgDesc = new OrgDesc();
			// 计算新的机构描述ID,根据父节点和最大子节点计算
			String newID = GenOrgDescID.get4NewId(newDescPID, orgService.getSubOrgMaxId(newDescPID));
			orgDesc.setId(newID);
			orgDesc.setOpen("0");
			orgDesc.setOrgLevel((newID.length() / 4) + "");
			orgDesc.setOrgId(org.getOrgId()); // 机构描述对象的orgId替换为参数传递过来的实际orgId
			orgDesc.setpId(newDescPID);

			int[] rst = orgService.updateOrgDescs(org, orgDesc);
			int sum = 1;
			for (int j = 0; j < rst.length; j++) {
				sum += rst[j] == Statement.SUCCESS_NO_INFO ? 0 : rst[j];
			}
			if (sum >= 0) {

				// 更新上级机构的IsParent状态，2014-05-08添加
				orgService.updateIsParent(newDescPID);
				orgService.updateIsParent(oldDescId);

				map.put("mes", "修改关联机构[" + sum + "]个");
			} else {
				map.put("mes", "修改机构失败");
			}
		}
		return map;
	}

	/**
	 * 批量删除机构
	 * 
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/org/delOrgs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delOrgs(@RequestParam("orgId") List<String> orgIdList) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			List<String> delList = new ArrayList<String>(); // 删除机构号列表
			String pidStr = "";

			String pid = "";
			for (int i = 0; i < orgIdList.size(); i++) {
				String orgId = orgIdList.get(i);
				Org org = orgService.getOrgByOrgId(orgId);
				OrgDesc orgDesc = orgService.getOrgDescByOrgId(orgId);
				pid = orgDesc.getpId();

				if (orgService.getSubOrgCount(orgDesc.getId()) > 0) {
					map.put("mes", "[" + org.getOrgName() + "] 不能删除，请先删除该机构的下属机构");
					return map;
				} else if (userService.getUserCountByOrgId(orgId) > 0) {
					map.put("mes", "[" + org.getOrgName() + "] 不能删除，请先删除该机构下的用户");
					return map;
				} else {
					delList.add(orgId);

					if (!pidStr.contains(pid)) {
						pidStr += pid + ",";
					}
				}
			}

			if (delList.size() > 0) {
				int[] result = orgService.delOrgs(orgIdList);
				int sum = 0;
				for (int j = 0; j < result.length; j++) {
					sum += result[j];
				}

				// 更新上级机构的IsParent状态，2014-05-08添加
				if (!pidStr.equals("")) {
					String[] pids = pidStr.split(",");
					for (int i = 0; i < pids.length; i++) {
						orgService.updateIsParent(pids[i]);
					}
				}

				if (sum == orgIdList.size()) {
					map.put("mes", "删除成功[" + sum + "]条机构记录");
				} else {
					map.put("mes", "删除机构失败");
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除机构失败",e);
			}
			map.put("mes", "删除机构失败");
		}
		return map;// 重定向
	}

	/**
	 * 进入机构树查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/treePopWin", method = RequestMethod.GET)
	public String popWin4Tree() {
		return "pc/bsp/org/treeOrgPopWin";
	}
	
	@RequestMapping(value = "/org/checkOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkGuardOrgId(HttpServletRequest req,Gps gps) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(orgService.getOrg(gps.getLng(), gps.getLat())!=""){
				map.put("mes", "");
			}else{
				map.put("mes", "请填写正确的经纬度坐标");
			}
		}catch (Exception e) {
			map.put("mes", "请填写正确的经纬度坐标");
		}
		return map; 
	}

}
