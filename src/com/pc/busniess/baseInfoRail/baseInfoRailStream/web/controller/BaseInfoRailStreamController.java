package com.pc.busniess.baseInfoRail.baseInfoRailStream.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
import com.pc.busniess.baseInfoRail.baseInfoRailStream.service.BaseInfoRailStreamService;
/**
 * 线路坐标
 * @author xb
 */
@Controller
public class BaseInfoRailStreamController {
	
	@Autowired
	private BaseInfoRailStreamService baseInfoRailStreamService;
	/**
	 * 打开线路明细信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoRailStreamInit",method=RequestMethod.GET)
	public String pubMapRailDataInit(HttpServletRequest req){
		return "pc/bussiness/baseInfoRail/baseInfoRailStream/baseInfoRailStreamInit";
	}
	/**
	 * 查询线路明细信息
	 * @param baseInfoRailId
	 * @param dgm
	 * @param pubMapRailDataPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoRailStreamQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> pubMapRailDataQueryList(@RequestParam("id") String id,
			DataGridModel dgm, BaseInfoRailDataPo pubMapRailDataPo) {
		if(!StringUtils.isEmpty(id)) {
			pubMapRailDataPo.setRailId(id);
		}
		return baseInfoRailStreamService.baseInfoRailStreamQueryList(dgm,pubMapRailDataPo); 
	}
}
