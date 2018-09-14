package com.mobile.busniess.mobilePatrolTrackInfo.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobilePatrolTrackInfo.po.MobilePatrolTrackInfoPo;
import com.mobile.busniess.mobilePatrolTrackInfo.service.MobilePatrolTrackInfoService;

@Controller
public class MobilePatrolTrackInfoController {

	
	@Autowired
	private MobilePatrolTrackInfoService patrolTrackInfoService;
	//添加巡线轨迹
	@RequestMapping(value = "/mobile/baseInfo/addPatrolTrackInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPatrolTrackInfo(
			MobilePatrolTrackInfoPo mobilePatrolTrackInfoPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",mobilePatrolTrackInfoPo);
		try {
			if (patrolTrackInfoService
					.addPatrolTrackInfo(mobilePatrolTrackInfoPo) > 0) {
				map.put("mes", "新增巡线轨迹成功");
				map.put("success", true);
			} else {
				map.put("mes", "新增巡线轨迹失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mes", "新增巡线轨迹失败");
			map.put("success", false);
		}
		return map;
	}
}
