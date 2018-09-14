package com.pc.busniess.baseInfoDefenceBroadcast.web.controller;

/**
 *  @author lyf
 */
import java.io.UnsupportedEncodingException;
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
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
/**
 *  D.Steven
 */

@Controller
public class BaseInfoDefenceBroadcastController {
	private static Logger logger = Logger.getLogger(BaseInfoDefenceBroadcastController.class);
	@Autowired
	private BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 
	 * @param req
	 * @return
	 * 打开广播警示柱信息页面
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefenceBroadcastInit", method = RequestMethod.GET)
	public String baseInfoDefenceBroadcastInit(HttpServletRequest req) {
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoDefenceBroadcast/baseInfoDefenceBroadcastInit";
	}
	/**
	 * 查询广播警示柱信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefenceBroadcastQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm, BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		return baseInfoDefenceBroadcastService.baseInfoDefenceBroadcastQueryList(dgm, baseInfoDefenceBroadcastPo);
	}

	/**
	 * 
	 * @param req
	 * @return 
	 * 弹出广播警示柱信息添加页面
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoDefenceBroadcastPopWin", method = RequestMethod.GET)
	public String addBaseInfoDefenceBroadcastPopWin(HttpServletRequest req) {
		List<Map<String, Object>> baseInfoDefenceBroadcastList = PubData.getDictList("BROADCAST_STATUS");
		req.setAttribute("baseInfoDefenceBroadcastList", baseInfoDefenceBroadcastList);
		List<Map<String, Object>> baseInfoDefenceBroadcastTypeList = PubData.getDictList("BROADCASTTYPE");
		req.setAttribute("baseInfoDefenceBroadcastTypeList", baseInfoDefenceBroadcastTypeList);
		return "pc/bussiness/baseInfoDefenceBroadcast/addBaseInfoDefenceBroadcastPopWin";
	}

	/**
	 * 
	 * @param baseInfoDefenceBroadcastPo
	 * @return 
	 * 保存广播警示柱信息
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoDefenceBroadcast", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceBroadcastPo.getLng();
			String lat=baseInfoDefenceBroadcastPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefenceBroadcastPo.setTheGeom(theGeom);
			baseInfoDefenceBroadcastPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("broa");
			baseInfoDefenceBroadcastPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[11];
					obj[0] = id;
					obj[1] = baseInfoDefenceBroadcastPo.getName();
					obj[2] = baseInfoDefenceBroadcastPo.getLng();
					obj[3] = baseInfoDefenceBroadcastPo.getLat();
					obj[4] = baseInfoDefenceBroadcastPo.getTheGeom();
					obj[5] = baseInfoDefenceBroadcastPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					obj[8] = baseInfoDefenceBroadcastPo.getNumber();
					obj[9] = baseInfoDefenceBroadcastPo.getPort();
					obj[10] = baseInfoDefenceBroadcastPo.getIp();
					objList.add(obj);
				}	
			}
			if (baseInfoDefenceBroadcastService.addBaseInfoDefenceBroadcast(baseInfoDefenceBroadcastPo,objList) > 0) {
				map.put("mes", "新增广播警示柱信息成功");
			} else {
				map.put("mes", "新增广播警示柱信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增广播警示柱信息失败", e);
			}
			map.put("mes", "新增广播警示柱信息失败");
		}
		return map;
	}

	/**
	 * 
	 * @param req
	 * @return 
	 * 保存广播警示柱信息
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefenceBroadcastPopWin", method = RequestMethod.GET)
	public String updateBaseInfoDefenceBroadcastPopWin(HttpServletRequest req) throws UnsupportedEncodingException {
		List<Map<String, Object>> baseInfoDefenceBroadcastList = PubData.getDictList("BROADCAST_STATUS");
		req.setAttribute("baseInfoDefenceBroadcastList", baseInfoDefenceBroadcastList);
		List<Map<String, Object>> baseInfoDefenceBroadcastTypeList = PubData.getDictList("BROADCASTTYPE");
		req.setAttribute("baseInfoDefenceBroadcastTypeList", baseInfoDefenceBroadcastTypeList);
		// 照片回显
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		
		return "pc/bussiness/baseInfoDefenceBroadcast/updateBaseInfoDefenceBroadcastPopWin";
	}

	/**
	 * 
	 * @param baseInfoDefenceBroadcastPo
	 * @return 
	 * 保存修改广播警示柱信息
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefenceBroadcast", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceBroadcastPo.getLng();
			String lat=baseInfoDefenceBroadcastPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefenceBroadcastPo.setTheGeom(theGeom);
			baseInfoDefenceBroadcastPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[11];
					obj[0] = baseInfoDefenceBroadcastPo.getId();
					obj[1] = baseInfoDefenceBroadcastPo.getName();
					obj[2] = baseInfoDefenceBroadcastPo.getLng();
					obj[3] = baseInfoDefenceBroadcastPo.getLat();
					obj[4] = baseInfoDefenceBroadcastPo.getTheGeom();
					obj[5] = baseInfoDefenceBroadcastPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					obj[8] = baseInfoDefenceBroadcastPo.getNumber();
					obj[9] = baseInfoDefenceBroadcastPo.getPort();
					obj[10] = baseInfoDefenceBroadcastPo.getIp();
					objList.add(obj);
				}	
			}
			if (baseInfoDefenceBroadcastService.updateBaseInfoDefenceBroadcast(baseInfoDefenceBroadcastPo,objList) > 0) {
				map.put("mes", "更新广播警示柱信息成功");
			} else {
				map.put("mes", "更新广播警示柱信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改广播警示柱信息失败", e);
			}
			map.put("mes", "修改广播警示柱信息失败");
		}
		return map;
	}
	@RequestMapping(value = "/baseInfo/checkBroOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkBroOrgId(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceBroadcastPo.getLng();
			String lat=baseInfoDefenceBroadcastPo.getLat();
			if(orgService.getOrg(lng, lat)!=""){
				map.put("mes", "");
			}else{
				map.put("mes", "请填写正确的经纬度坐标");
			}
		}catch (Exception e) {
			map.put("mes", "请填写正确的经纬度坐标");
		}
		return map; 
	}
	/**
	 * 
	 * @param idList
	 * @return 
	 * 删除广播警示柱信息
	 */
	@RequestMapping(value = "/baseInfo/deleteBaseInfoDefenceBroadcast", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoDefenceBroadcast(@RequestParam("idList") List<String> idList) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoDefenceBroadcastService.deleteBaseInfoDefenceBroadcast(idList);
			int sum = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j];
			}
			if (sum == idList.size()) {
				map.put("mes", "删除成功[" + sum + "]条广播警示柱信息");
			} else {
				map.put("mes", "删除广播警示柱信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除广播警示柱信息失败", e);
			}
			map.put("mes", "删除广播警示柱信息失败");
		}
		return map;
	}




}
