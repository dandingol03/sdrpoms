package com.pc.busniess.videoMonitorInfo.web.controller;


/**
 *  jwl
 *  监控Controller 
 */

import java.util.ArrayList;
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
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.videoMonitorInfo.po.VideoMonitorInfoPo;
import com.pc.busniess.videoMonitorInfo.service.VideoMonitorInfoService;

@Controller
public class VideoMonitorInfoController {

	private static Logger logger = Logger.getLogger(VideoMonitorInfoController.class);
	
	@Autowired
	private VideoMonitorInfoService videoMonitorInfoService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开监控信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/videoMonitorInfoInit",method=RequestMethod.GET)
	public String videoMonitorInfoInit(HttpServletRequest req){
		return "pc/bussiness/videoMonitorInfo/videoMonitorInfoInit";
	}
	/**
	 * 查询监控信息
	 * @param dgm
	 * @param videoMonitorInfoPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/videoMonitorInfoQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> videoMonitorInfoQueryList(DataGridModel dgm, VideoMonitorInfoPo videoMonitorInfoPo) {
		return videoMonitorInfoService.videoMonitorInfoQueryList(dgm,videoMonitorInfoPo); 
	}
	/***
	 * 弹出监控信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addVideoMonitorInfoPopWin",method=RequestMethod.GET)
	public String addvideoMonitorInfoPopWin(HttpServletRequest req){
		//监控类型
		List<Map<String,Object>> videoTypeList = PubData.getDictList("VIDEO_TYPE");
		req.setAttribute("videoTypeList",videoTypeList);
	    return "pc/bussiness/videoMonitorInfo/addVideoMonitorInfoPopWin";
	}
	/**
	 * 保存监控信息
	 * @param videoMonitorInfoPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addVideoMonitorInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addvideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=videoMonitorInfoPo.getLng();
		String lat=videoMonitorInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		videoMonitorInfoPo.setTheGeom(theGeom);
		videoMonitorInfoPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("vide");
		videoMonitorInfoPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = videoMonitorInfoPo.getName();
				obj[2] = videoMonitorInfoPo.getLng();
				obj[3] = videoMonitorInfoPo.getLat();
				obj[4] = videoMonitorInfoPo.getTheGeom();
				obj[5] = videoMonitorInfoPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(videoMonitorInfoService.addvideoMonitorInfo(videoMonitorInfoPo,objList) > 0){
				map.put("mes", "新增监控信息成功");
			}else{
				map.put("mes", "新增监控信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增监控信息失败",e);
			}
			map.put("mes", "新增监控信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/updateVideoMonitorInfoPopWin",method=RequestMethod.GET)
	public String updatevideoMonitorInfoPopWin(HttpServletRequest req){
		//监控类型
		List<Map<String,Object>> videoTypeList = PubData.getDictList("VIDEO_TYPE");
		req.setAttribute("videoTypeList",videoTypeList);
		return "pc/bussiness/videoMonitorInfo/updateVideoMonitorInfoPopWin";
	}
	/**
	 * 保存修改监控信息
	 * @param videoMonitorInfoPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateVideoMonitorInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatevideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=videoMonitorInfoPo.getLng();
		String lat=videoMonitorInfoPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		videoMonitorInfoPo.setTheGeom(theGeom);
		videoMonitorInfoPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = videoMonitorInfoPo.getId();
				obj[1] = videoMonitorInfoPo.getName();
				obj[2] = videoMonitorInfoPo.getLng();
				obj[3] = videoMonitorInfoPo.getLat();
				obj[4] = videoMonitorInfoPo.getTheGeom();
				obj[5] = videoMonitorInfoPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(videoMonitorInfoService.updatevideoMonitorInfo(videoMonitorInfoPo,objList) > 0){
				map.put("mes", "更新监控信息成功");
			}else{
				map.put("mes", "更新监控信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改监控信息失败",e);
			}
			map.put("mes", "修改监控信息失败");
		}
		return map; 
	}
	/**
	 * 删除监控信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteVideoMonitorInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletevideoMonitorInfo(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = videoMonitorInfoService.deletevideoMonitorInfo(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条监控信息");
			}else{
				map.put("mes", "删除监控信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除监控信息失败",e);
			}
			map.put("mes", "删除监控信息失败");
		}
		return map;
	}
}
