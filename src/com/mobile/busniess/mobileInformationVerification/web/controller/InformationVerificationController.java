/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.web.controller 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:40:20 
 */
package com.mobile.busniess.mobileInformationVerification.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileInformationVerification.po.InformationVerification;
import com.mobile.busniess.mobileInformationVerification.service.InformationVerificationService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.web.controller 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:40:20 
 */
@Controller
public class InformationVerificationController {
	
	private static Logger logger = Logger.getLogger(InformationVerificationController.class);
	
	@Autowired
	private OrgService orgService;	
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	@Autowired
	private InformationVerificationService informationVerificationService;
	
	//根据GPS获取监控,按距离排序 video_information
	@RequestMapping(value = "/mobile/getInformationVerificationInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInformationVerificationInfo(DataGridModel dgm,HttpServletRequest req,InformationVerification informationVerification) {
		return informationVerificationService.getInformationVerificationInfo(dgm,informationVerification);
	}
	//根据GPS获取广播警示柱,按距离排序 
	
	//修改监控经纬度以及相关信息
	@RequestMapping(value = "/mobile/updateInformationVerificationInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateInformationVerificationInfo(InformationVerification informationVerification) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("data",informationVerification);
			String lng=informationVerification.getLng();
			String lat=informationVerification.getLat();
			if(StringUtils.isNotBlank(lng)&&StringUtils.isNotBlank(lat)){
			String orgId=orgService.getOrg(lng, lat);
			if(orgId==""){
				map.put("mes", "请填写正确的经纬度坐标,范围:北京");
				map.put("success",false);
				return map; 
			}
				String theGeom="POINT("+lng+" "+lat+")";
				informationVerification.setTheGeom(theGeom);
			}
			if(informationVerificationService.updateInformationVerificationInfo(informationVerification) > 0){
				map.put("mes", "成功");
				map.put("success",true);
			}else{
				map.put("mes", "失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("失败",e);
				map.put("success",false);
			}
			map.put("mes", "失败");
			map.put("success",false);
		}
		return map; 
	}

	//添加审查结果 /mobile/addInformationVerification  post  po{videoId,reviewResults} video_review_results 
	@RequestMapping(value="/mobile/addInformationVerification",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addInformationVerification(InformationVerification informationVerification){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("data",informationVerification);
			if(informationVerificationService.addInformationVerification(informationVerification)>0){
				map.put("mes", "添加审查结果成功");
				map.put("success",true);
			}else{
				map.put("mes", "添加审查结果失败");
				map.put("success",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("添加审查结果失败",e);
			}
			map.put("mes", "添加审查结果失败");
			map.put("success",false);
		}
		return map;
	}
	
}
