package com.pc.busniess.patrolTrackInfo.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.patrolManagementTask.po.PatrolManagementTaskPo;
import com.pc.busniess.patrolTrackInfo.service.PatrolTrackInfoService;

/**
 * 这个Controller主要是用来接收和返回数据的
 * @author jiawenlong
 */
@Controller
public class PatrolTrackInfoController {

	private static Logger logger = Logger.getLogger(PatrolTrackInfoController.class);
	
	@Autowired
	private PatrolTrackInfoService patrolTrackInfoService;
	/**
	 * 打开巡防管理信息信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/patrol/patroltrackInfoInit",method=RequestMethod.GET)
	public String PatrolTrackInfoInit(HttpServletRequest req){
			return "pc/bussiness/patrolTrackInfo/patrolTrackInfoInit";		
	}
	/**
	 * 查询巡防管理信息信息
	 * @param dgm
	 * @param PatrolManagementTaskPo
	 * @see PatrolManagementTaskPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/PatrolTrackInfoQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> PatrolTrackInfoQueryList(@RequestParam("userId") String userId,DataGridModel dgm,PatrolManagementTaskPo patrolManagementTaskPo) {
		// 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0 ,注意在 StringUtils 中空格作非空处理
		if(!StringUtils.isEmpty(userId)) {		
			patrolManagementTaskPo.setUserId(userId);
		}
		return patrolTrackInfoService.PatrolTrackInfoQueryList(dgm,patrolManagementTaskPo); 
	}
	
	/**
	 * 删除巡防管理信息信息
	 * @param idList
	 * @return map
	 */
	@RequestMapping(value="/baseInfo/deletePatrolTrackInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletePatrolTrackInfo(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = patrolTrackInfoService.deletePatrolTrackInfo(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条巡防管理信息信息");
			}else{
				map.put("mes", "删除巡防管理信息信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除巡防管理信息信息失败",e);
			}
			map.put("mes", "删除巡防管理信息信息失败");
		}
		return map;
	}
	
}
