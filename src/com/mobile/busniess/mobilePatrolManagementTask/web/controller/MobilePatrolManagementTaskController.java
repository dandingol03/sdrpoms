/**   
 * @Package: com.mobile.patrolManagementTask.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:10:57 
 */
package com.mobile.busniess.mobilePatrolManagementTask.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PhoneUtils;
import com.pc.busniess.patrolManagementTask.po.PatrolTeamInfoPo;
import com.pc.busniess.patrolManagementTask.service.PatrolManagementTaskService;
import com.pc.busniess.patrolManagementTask.web.controller.PatrolManagementTaskController;

/**   
 * @Package: com.mobile.patrolManagementTask.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:10:57 
 * 
 */
@Controller
public class MobilePatrolManagementTaskController {
	
	private static Logger logger = Logger.getLogger(PatrolManagementTaskController.class);
	
	@Autowired
	private PatrolManagementTaskService patrolManagementTaskService;

	//队伍信息查询  ----权限
	@RequestMapping(value = "/mobile/patrol/patrolTeamInfoQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> patrolManagementTaskQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo,HttpServletRequest request) {
			return patrolManagementTaskService.patrolManagementTaskQueryList(dgm,patrolTeamInfoPo);				
	}
	//添加队伍信息
	@RequestMapping(value = "/mobile/baseInfo/addPatrolManagementTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPatrolManagementTask(
			PatrolTeamInfoPo patrolTeamInfoPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("date",patrolTeamInfoPo);
			if (patrolManagementTaskService
					.addPatrolManagementTask(patrolTeamInfoPo) > 0) {
				map.put("mes", "新增巡防队伍信息成功");
				map.put("success", true);
			} else {
				map.put("mes", "新增巡防队伍信息失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增巡防队伍信息失败", e);
			}
			map.put("mes", "新增巡防队伍信息失败");
			map.put("success",false );
		}
		return map;
	}

	//修改队伍信息
	@RequestMapping(value = "/mobile/baseInfo/updatePatrolManagementTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePatrolManagementTask(PatrolTeamInfoPo patrolTeamInfoPo) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("date",patrolTeamInfoPo);
				if (patrolManagementTaskService
						.updatePatrolManagementTask(patrolTeamInfoPo) > 0) {
					map.put("mes", "更新巡防队伍信息成功");
					map.put("success", true);
				} else {
					map.put("mes", "更新巡防队伍信息失败");
					map.put("success", false);
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("修改巡防队伍信息失败", e);
				}
				map.put("mes", "修改巡防队伍信息失败");
				map.put("success",false );
			}
			return map;
	}

	//删除队伍信息  ----权限
	@RequestMapping(value = "/mobile/patrol/deletePatrolTeamInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletePatrolManagementTask(
			@RequestParam("idList") List<String> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int a=patrolManagementTaskService.checkPatrolData(idList.get(0));
			if(a==0){
				map.put("mes", "请先删除该巡防队伍的巡防队员信息!");
				map.put("success", false);
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
				map.put("success", true);
			} else {
				map.put("mes", "删除巡防队伍信息失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除巡防队伍信息失败", e);
			}
			map.put("mes", "删除巡防队伍信息失败");
			map.put("success", false);
		}
		return map;
	}
}
