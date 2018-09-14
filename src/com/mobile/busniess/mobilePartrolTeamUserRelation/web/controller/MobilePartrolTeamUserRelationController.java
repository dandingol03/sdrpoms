package com.mobile.busniess.mobilePartrolTeamUserRelation.web.controller;


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
import com.pc.busniess.partrolTeamUserRelation.po.PartrolTeamUserRelationPo;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

/**
 * 巡防人
 * @author jiawenlong
 * @version 1.0
 * @since 1.0
 */
@Controller
public class MobilePartrolTeamUserRelationController {

	private static Logger logger = Logger.getLogger(MobilePartrolTeamUserRelationController.class);
	
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	/**
	 * 查询巡防人员信息
	 * @param dgm
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return 返回巡防Service的查询方法
	 */
	@RequestMapping(value = "/mobile/patrol/patrolTeamUserRelationQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> PartrolTeamUserRelationQueryList(@RequestParam(value="teamInfoId",required=false) String teamInfoId,
			DataGridModel dgm, PartrolTeamUserRelationPo partrolTeamUserRelationPo,@RequestBody String mobileJSON,HttpServletRequest request) {
		if(!StringUtils.isEmpty(teamInfoId)) {		
			partrolTeamUserRelationPo.setTeamInfoId(teamInfoId);
		}
		try {
			if(PhoneUtils.isFromMobile(request)) {
				if(StringUtils.isNotEmpty(mobileJSON)){
					JSONObject json = JSONObject.fromObject(mobileJSON);
					DataGridModel parsed=(DataGridModel)JSONObject.toBean(json,DataGridModel.class);
					return partrolTeamUserRelationService.PartrolTeamUserRelationQueryList(parsed,partrolTeamUserRelationPo); 		
				}else{
					return partrolTeamUserRelationService.PartrolTeamUserRelationQueryList(dgm,partrolTeamUserRelationPo); 			
				}
			}else {
				return partrolTeamUserRelationService.PartrolTeamUserRelationQueryList(dgm,partrolTeamUserRelationPo); 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 保存巡防人员信息
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return map
	 */
	@RequestMapping(value = "/mobile/baseInfo/addPartrolTeamUserRelation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("data",partrolTeamUserRelationPo);
			if(partrolTeamUserRelationService.addPartrolTeamUserRelation(partrolTeamUserRelationPo) > 0){
				map.put("mes", "新增巡防人员信息成功");
				map.put("success", true);
			}else{
				map.put("mes", "新增巡防人员信息失败");
				map.put("success", false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增巡防人员信息失败",e);
				map.put("success", false);
			}
			map.put("mes", "新增巡防人员信息失败");
			map.put("success", false);
		}
		return map; 
	}
	/**
	 * 保存修改巡防人员信息
	 * @param PatrolManagementTaskPo
	 * @see PartrolTeamUserRelationPo
	 * @return map
	 */
	@RequestMapping(value = "/mobile/baseInfo/updatePartrolTeamUserRelation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePartrolTeamUserRelation(PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(partrolTeamUserRelationService.updatePartrolTeamUserRelation(partrolTeamUserRelationPo) > 0){
				map.put("mes", "更新巡防人员信息成功");
				map.put("success", true);
			}else{
				map.put("mes", "更新巡防人员信息失败");
				map.put("success", false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改巡防人员信息失败",e);
				map.put("success", false);
			}
			map.put("mes", "修改巡防人员信息失败");
			map.put("success", false);
		}
		return map; 
	}
	/**
	 * 删除巡防人员信息
	 * @param idList
	 * @return map
	 */
	@RequestMapping(value="/mobile/patrol/deletePatrolTeamUserRelation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletePartrolTeamUserRelation(@RequestParam("idList") List<String> idList ,@RequestParam("userList") List<String> userList){  
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int a=partrolTeamUserRelationService.checkTeamData(idList.get(0),userList.get(0));
			if(a==0){
				map.put("mes", "请先删除该巡防队员的巡防轨迹与责任区段信息!");
				map.put("error", true);
				return map;
			}
			int[] result = partrolTeamUserRelationService.deletePartrolTeamUserRelation(idList,userList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条巡防人员信息");
				map.put("error", false);
			}else{
				map.put("mes", "删除巡防人员信息失败");
				map.put("error", true);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除巡防人员信息失败",e);
				map.put("error", true);
			}
			map.put("mes", "删除巡防人员信息失败");
			map.put("error", true);
		}
		return map;
	}
}
