package com.pc.busniess.baseInfoRail.baseInfoRailData.web.controller;

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
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
import com.pc.busniess.baseInfoRail.baseInfoRailData.service.BaseInfoRailDataService;
import com.pc.busniess.baseInfoRail.web.controller.BaseInfoRailController;
/**
 * 线路坐标
 * @author xb
 */
@Controller
public class BaseInfoRailDataController {
	private static Logger logger = Logger.getLogger(BaseInfoRailController.class);
	
	@Autowired
	private BaseInfoRailDataService pubMapRailDataService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开线路信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoRailDataInit",method=RequestMethod.GET)
	public String pubMapRailDataInit(HttpServletRequest req){
		return "pc/bussiness/baseInfoRail/baseInfoRailData/baseInfoRailDataInit";
	}
	/**
	 * 查询线路信息
	 * @param baseInfoRailId
	 * @param dgm
	 * @param pubMapRailDataPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoRailDataQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> pubMapRailDataQueryList(@RequestParam("baseInfoRailId") String baseInfoRailId,
			DataGridModel dgm, BaseInfoRailDataPo pubMapRailDataPo) {
		if(!StringUtils.isEmpty(baseInfoRailId)) {
			pubMapRailDataPo.setRailId(baseInfoRailId);
		}
		return pubMapRailDataService.pubMapRailDataQueryList(dgm,pubMapRailDataPo); 
	}
	
	/**
	 * 弹出线路信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoRailDataPopWin",method=RequestMethod.GET)
	public String addBaseInfoRailDataPopWin(HttpServletRequest req){
		//单双行
		req.setAttribute("streams", PubData.getDictList("RAIL_STREAM"));
	    return "pc/bussiness/baseInfoRail/baseInfoRailData/addBaseInfoRailDataPopWin";
	}
	/**
	 * //保存线路信息
	 * @param pubMapRailDataPo
	 * @return
	 */
	
	@RequestMapping(value = "/pubMapRailData/addPubMapRailData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addPubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=pubMapRailDataPo.getLng();
		String lat=pubMapRailDataPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		pubMapRailDataPo.setTheGeom(theGeom);
		pubMapRailDataPo.setOrgId(orgId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(pubMapRailDataService.addPubMapRailData(pubMapRailDataPo) > 0){
				map.put("mes", "新增线路信息成功");
			}else{
				map.put("mes", "新增线路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增线路信息失败",e);
			}
			map.put("mes", "新增线路信息失败");
		}
		return map; 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoRailDataPopWin",method=RequestMethod.GET)
	public String updateBaseInfoRailDataPopWin(HttpServletRequest req){
		List<Map<String,Object>> list = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("streams", list);
		return "pc/bussiness/baseInfoRail/baseInfoRailData/updateBaseInfoRailDataPopWin";
	}
	/**
	 * 保存修改线路信息
	 * @param pubMapRailDataPo
	 * @return
	 */
	@RequestMapping(value = "/pubMapRailData/updatePubMapRailData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=pubMapRailDataPo.getLng();
		String lat=pubMapRailDataPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		pubMapRailDataPo.setTheGeom(theGeom);
		pubMapRailDataPo.setOrgId(orgId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(pubMapRailDataService.updatePubMapRailData(pubMapRailDataPo) > 0){
				map.put("mes", "更新线路信息成功");
			}else{
				map.put("mes", "更新线路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改线路信息失败",e);
			}
			map.put("mes", "修改线路信息失败");
		}
		return map; 
	}
	/**
	 * 删除线路信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoRailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deletePubMapRailData(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = pubMapRailDataService.deletePubMapRailData(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条线路信息");
			}else{
				map.put("mes", "删除线路信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除线路信息失败",e);
			}
			map.put("mes", "删除线路信息失败");
		}
		return map;
	}
}
