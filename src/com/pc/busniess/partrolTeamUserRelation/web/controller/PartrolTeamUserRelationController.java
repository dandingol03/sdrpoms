package com.pc.busniess.partrolTeamUserRelation.web.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.partrolTeamUserRelation.po.PartrolTeamUserRelationPo;
import com.pc.busniess.partrolTeamUserRelation.po.ResponsibilityLinePo;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;
import com.pc.busniess.patrolManagementTask.service.PatrolManagementTaskService;

/**
 * 巡防人
 * 这个Controller主要用于接收和返回数据的
 * @author jiawenlong
 * @version 1.0
 * @since 1.0
 */
@Controller
public class PartrolTeamUserRelationController {

	private static Logger logger = Logger.getLogger(PartrolTeamUserRelationController.class);
	
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private PatrolManagementTaskService patrolManagementTaskService;
	/*--------------------------------队员责任区段---start-------------------------------*/
	/**
	 * 打开责任区段页面
	 */
	@RequestMapping(value="/baseInfo/responsibilityLineInit",method=RequestMethod.GET)
	public String responsibilityLinePopWin(HttpServletRequest req){
		return "pc/bussiness/patrolResponsibilityLine/responsibilityLineInit";		
	}
	
	@RequestMapping(value = "/baseInfo/responsibilityLineQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> responsibilityLineQueryList(@RequestParam("userId") String userId,DataGridModel dgm,
			ResponsibilityLinePo responsibilityLinePo) {
		if(!StringUtils.isEmpty(userId)) {		
			responsibilityLinePo.setUserId(userId);
		}
		return partrolTeamUserRelationService.responsibilityLineQueryList(dgm,responsibilityLinePo); 
	}
	@RequestMapping(value = "/baseInfo/findRail",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> findRail(ResponsibilityLinePo responsibilityLinePo) {
		return baseInfoRailService.findById(responsibilityLinePo.getRailId()); 
	}
	@RequestMapping(value="/baseInfo/addResponsibilityLinePopWin",method=RequestMethod.GET)
	public String addResponsibilityLinePopWin(HttpServletRequest req){
		//
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		return "pc/bussiness/patrolResponsibilityLine/addResponsibilityLinePopWin";		
	}
	
	@RequestMapping(value = "/baseInfo/addResponsibilityLine",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addResponsibilityLine(ResponsibilityLinePo responsibilityLinePo) {
		String railId=responsibilityLinePo.getRailId();
		//起点换算
		String end=CommonUtil.KM2MStrbyDec(responsibilityLinePo.getEndKM(),responsibilityLinePo.getEndM());
		//终点换算
		String start=CommonUtil.KM2MStrbyDec(responsibilityLinePo.getStartKM(),responsibilityLinePo.getStartM());
		responsibilityLinePo.setStart(start);
		responsibilityLinePo.setEnd(end);
		List<Double[]> listArr=partrolTeamUserRelationService.findlnglat(railId,start,end);
		List<String> list=new ArrayList<String>();
		for (Double[] doubles : listArr) {
			list.add(Arrays.asList(doubles).toString());
		}
		responsibilityLinePo.setRange(list.toString());
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(partrolTeamUserRelationService.addResponsibilityLine(responsibilityLinePo) > 0){
				map.put("mes", "新增信息成功");
			}else{
				map.put("mes", "新增信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增信息失败",e);
			}
			map.put("mes", "新增信息失败");
		}
		return map; 
	}
	
	@RequestMapping(value="/baseInfo/updateResponsibilityLinePopWin",method=RequestMethod.GET)
	public String updateResponsibilityLinePopWin(HttpServletRequest req){
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		String photosName = req.getParameter("filesName");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("filesName", photosName); 
		}else{
		    req.setAttribute("filesName", "请选择照片"); 
		}
		return "pc/bussiness/patrolResponsibilityLine/updateResponsibilityLinePopWin";		
	}
	
	@RequestMapping(value = "/baseInfo/updateResponsibilityLine",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateResponsibilityLine(ResponsibilityLinePo responsibilityLinePo) {
		String railId=responsibilityLinePo.getRailId();
		//起点换算
		String end=CommonUtil.KM2MStrbyDec(responsibilityLinePo.getEndKM(),responsibilityLinePo.getEndM());
		//终点换算
		String start=CommonUtil.KM2MStrbyDec(responsibilityLinePo.getStartKM(),responsibilityLinePo.getStartM());
		responsibilityLinePo.setStart(start);
		responsibilityLinePo.setEnd(end);
		List<Double[]> listArr=partrolTeamUserRelationService.findlnglat(railId,start,end);
		List<String> list=new ArrayList<String>();
		for (Double[] doubles : listArr) {
			list.add(Arrays.asList(doubles).toString());
		}
		responsibilityLinePo.setRange(list.toString());
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(partrolTeamUserRelationService.updateResponsibilityLine(responsibilityLinePo) > 0){
				map.put("mes", "更新信息成功");
			}else{
				map.put("mes", "更新信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改信息失败",e);
			}
			map.put("mes", "修改信息失败");
		}
		return map; 
	}
	
	@RequestMapping(value="/baseInfo/deleteResponsibilityLine",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteResponsibilityLine(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = partrolTeamUserRelationService.deleteResponsibilityLine(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条信息");
			}else{
				map.put("mes", "删除信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除信息失败",e);
			}
			map.put("mes", "删除信息失败");
		}
		return map;
	}
	/*--------------------------------队员责任区段---end-------------------------------*/
	/**
	 * 打开队员队伍信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/patrol/patrolTeamUserRelationInit",method=RequestMethod.GET)
	public String PartrolTeamUserRelationInit(HttpServletRequest req){
			return "pc/bussiness/patrolTeamUserRelation/patrolTeamUserRelationInit";		
	}
	/**
	 * 查询队员队伍信息
	 * @param dgm
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return 返回巡防Service的查询方法
	 */
	@RequestMapping(value = "/patrol/patrolTeamUserRelationQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> PartrolTeamUserRelationQueryList(@RequestParam("teamInfoId") String teamInfoId,DataGridModel dgm, PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		if(!StringUtils.isEmpty(teamInfoId)) {		
			partrolTeamUserRelationPo.setTeamInfoId(teamInfoId);
		}
		return partrolTeamUserRelationService.PartrolTeamUserRelationQueryList(dgm,partrolTeamUserRelationPo); 
	}
	/**
	 * 弹出队员队伍信息添加页面
	 * @param req
	 * @return 巡防添加界面
	 */
	@RequestMapping(value="/patrol/addPatrolTeamUserRelationPopWin",method=RequestMethod.GET)
	public String addPartrolTeamUserRelationPopWin(HttpServletRequest req){
			req.setAttribute("queryPers", partrolTeamUserRelationService.queryPers());
			//性别
			List<Map<String, Object>> taskGender = PubData.getDictList("TASK_GENDER");
			req.setAttribute("taskGender", taskGender);
			//学历
			List<Map<String, Object>> education = PubData.getDictList("EDUCATION");
			req.setAttribute("education", education);
			//户口
			List<Map<String, Object>> registerrd = PubData.getDictList("REGISTERRD");
			req.setAttribute("registerrd", registerrd);
			return "pc/bussiness/patrolTeamUserRelation/addPatrolTeamUserRelationPopWin";
	}
	
	/**
	 * 保存队员队伍信息
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return map
	 */
	@RequestMapping(value = "/baseInfo/addPartrolTeamUserRelation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addPartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(partrolTeamUserRelationService.addPartrolTeamUserRelation(partrolTeamUserRelationPo) > 0){
				map.put("mes", "新增队员队伍信息成功");
			}else{
				map.put("mes", "新增队员队伍信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增队员队伍信息失败",e);
			}
			map.put("mes", "新增队员队伍信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return 巡防修改界面
	 */
	@RequestMapping(value="/patrol/updatePatrolTeamUserRelationPopWin",method=RequestMethod.GET)
	public String updatePartrolTeamUserRelationPopWin(HttpServletRequest req){
			req.setAttribute("queryPers", partrolTeamUserRelationService.queryPers());
			//性别
			List<Map<String, Object>> taskGender = PubData.getDictList("TASK_GENDER");
			req.setAttribute("taskGender", taskGender);
			//学历
			List<Map<String, Object>> education = PubData.getDictList("EDUCATION");
			req.setAttribute("education", education);
			//户口
			List<Map<String, Object>> registerrd = PubData.getDictList("REGISTERRD");
			req.setAttribute("registerrd", registerrd);
			return "pc/bussiness/patrolTeamUserRelation/updatePatrolTeamUserRelationPopWin";
	}
	/**
	 * 保存修改队员队伍信息
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return map
	 */
	@RequestMapping(value = "/baseInfo/updatePartrolTeamUserRelation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(partrolTeamUserRelationService.updatePartrolTeamUserRelation(partrolTeamUserRelationPo) > 0){
				map.put("mes", "更新队员队伍信息成功");
			}else{
				map.put("mes", "更新队员队伍信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改队员队伍信息失败",e);
			}
			map.put("mes", "修改队员队伍信息失败");
		}
		return map; 
	}
	/**
	 * 删除队员队伍信息
	 * @param idList
	 * @return map
	 */
	@RequestMapping(value="/patrol/deletePatrolTeamUserRelation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletePartrolTeamUserRelation(@RequestParam("idList") List<String> idList ,@RequestParam("userList") List<String> userList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int a=partrolTeamUserRelationService.checkTeamData(idList.get(0),userList.get(0));
			if(a==0){
				map.put("mes", "请先删除该巡防队员的巡防轨迹与责任区段信息!");
				return map;
			}
			int[] result = partrolTeamUserRelationService.deletePartrolTeamUserRelation(idList,userList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条队员队伍信息");
			}else{
				map.put("mes", "删除队员队伍信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除队员队伍信息失败",e);
			}
			map.put("mes", "删除队员队伍信息失败");
		}
		return map;
	}
	@RequestMapping(value="/news/exportSafeCheckExcel",method=RequestMethod.POST)
	@ResponseBody
    public Map<String, String> exportSafeCheckExcel(HttpServletRequest request,
    		HttpServletResponse response,PartrolTeamUserRelationPo partrolTeamUserRelationPo,
    		@RequestParam("fileName") String fileName,@RequestParam("teamInfoId") String teamInfoId){
		Map<String, String> map = new HashMap<String, String>();
		try{
			map.put("fileId",partrolTeamUserRelationService.exportSafeCheckExcel(partrolTeamUserRelationPo,fileName,teamInfoId));
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("导出Excel失败",e);
			}
		}
		return map;
    }
	
	/**
	 * 弹出更换队伍添加页面
	 * @param req
	 * @return 巡防修改界面
	 */
	@RequestMapping(value="/patrol/updatePatrolUserBlongTeamPopWin",method=RequestMethod.GET)
	public String updatePatrolUserBlongTeamPopWin(HttpServletRequest req){
			req.setAttribute("teamInfo", patrolManagementTaskService.queryTeamList());
			return "pc/bussiness/patrolTeamUserRelation/updatePatrolUserBlongTeamPopWin";
			
	}
	/**
	 * 保存更换队伍信息
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return map
	 */
	@RequestMapping(value = "/baseInfo/updatePatrolUserBlongTeam",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePatrolUserBlongTeam(PartrolTeamUserRelationPo partrolTeamUserRelationPo,@RequestParam("teamId") String teamId ) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(partrolTeamUserRelationService.updatePatrolUserBlongTeam(partrolTeamUserRelationPo,teamId) > 0){
				map.put("mes", "更新队员队伍信息成功");
			}else{
				map.put("mes", "更新队员队伍信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("更新队员队伍信息失败",e);
			}
			map.put("mes", "更新队员队伍信息失败");
		}
		return map; 
	}
}
