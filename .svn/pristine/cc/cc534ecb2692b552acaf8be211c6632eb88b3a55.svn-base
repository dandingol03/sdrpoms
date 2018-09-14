package com.pc.busniess.patrolManagementTask.web.controller;

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
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.patrolManagementTask.po.PatrolTeamInfoPo;
import com.pc.busniess.patrolManagementTask.service.PatrolDangerInfoService;
import com.pc.busniess.patrolManagementTask.service.PatrolManagementTaskService;

/**
 * 巡防队伍信息
 * @author jiawenlong
 * 巡防队伍信息
 * 隐患处置上报  调用 手机端接口
 */
@Controller
public class PatrolManagementTaskController {

	private static Logger logger = Logger
			.getLogger(PatrolManagementTaskController.class);

	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;//注入涵洞
	@Autowired
	private PatrolManagementTaskService patrolManagementTaskService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private PatrolDangerInfoService patrolDangerInfoService;
	
	/**************** patrol_team_info***********************巡防队伍信息表 **************************************************/
	/**
	 * 打开队伍展示页面
	 * @param req
	 * @param dgm
	 * @param patrolTeamInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/patrolTeamInfoInit", method = RequestMethod.GET)
	public String patrolTeamInfoInit(HttpServletRequest req,
			DataGridModel dgm, PatrolTeamInfoPo patrolTeamInfoPo) {
		return "/pc/bussiness/patrolManagementTask/patrolManagementTaskInit";
	}

	/**
	 * 队伍信息查询
	 * @param dgm
	 * @param patrolTeamInfoPo
	 * @return
	 */
	@RequestMapping(value = "/patrol/patrolTeamInfoQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> patrolTeamInfoQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo,HttpServletRequest request) {
				return patrolManagementTaskService.patrolManagementTaskQueryList(dgm,
						patrolTeamInfoPo);
	}
	/**
	 * 队伍人员信息查询全部地图
	 * @param dgm
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/taskAndUserQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> taskAndUserQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo) {
		return patrolManagementTaskService.taskAndUserQueryList(dgm,
				patrolTeamInfoPo);
	}
	/**跳转添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/patrol/addPatrolTeamInfoPopWin", method = RequestMethod.GET)
	public String addPatrolTeamInfoPopWin(HttpServletRequest req) {
		return "pc/bussiness/patrolManagementTask/addPatrolManagementTaskPopWin";
	}

	/**添加队伍信息
	 * @param PatrolManagementTaskPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addPatrolManagementTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addPatrolManagementTask(
			PatrolTeamInfoPo patrolTeamInfoPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (patrolManagementTaskService
					.addPatrolManagementTask(patrolTeamInfoPo) > 0) {
				map.put("mes", "新增巡防队伍信息成功");
			} else {
				map.put("mes", "新增巡防队伍信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增巡防队伍信息失败", e);
			}
			map.put("mes", "新增巡防队伍信息失败");
		}
		return map;
	}

	/**打开队伍修改页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/patrol/updatePatrolTeamInfoPopWin", method = RequestMethod.GET)
	public String updatePatrolTeamInfoPopWin(HttpServletRequest req) {
		return "pc/bussiness/patrolManagementTask/updatePatrolManagementTaskPopWin";
	}

	/**修改队伍信息
	 * @param PatrolManagementTaskPo
	 * @return
	 */

	@RequestMapping(value = "/baseInfo/updatePatrolManagementTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePatrolManagementTask(
			PatrolTeamInfoPo patrolTeamInfoPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (patrolManagementTaskService
					.updatePatrolManagementTask(patrolTeamInfoPo) > 0) {
				map.put("mes", "更新巡防队伍信息成功");
			} else {
				map.put("mes", "更新巡防队伍信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改巡防队伍信息失败", e);
			}
			map.put("mes", "修改巡防队伍信息失败");
		}
		return map;
	}

	/**删除队伍信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/patrol/deletePatrolTeamInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletePatrolTeamInfo(
			@RequestParam("idList") List<String> idList) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int a=patrolManagementTaskService.checkPatrolData(idList.get(0));
			if(a==0){
				map.put("mes", "请先删除该巡防队伍的巡防队员信息!");
				return map;
			}
			int[] result = patrolManagementTaskService
					.deletePatrolManagementTask(idList);
			int sum = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j];
			}
			if (sum == idList.size()) {
				map.put("mes", "删除成功[" + sum + "]条巡防队伍信息");
			} else {
				map.put("mes", "删除巡防队伍信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除巡防队伍信息失败", e);
			}
			map.put("mes", "删除巡防队伍信息失败");
		}
		return map;
	}
}
