package com.pc.busniess.baseInfoDefenceGuardStation.web.controller;

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
import com.pc.busniess.baseInfoDefenceGuardStation.po.BaseInfoDefenceGuardStationPo;
import com.pc.busniess.baseInfoDefenceGuardStation.service.BaseInfoDefenceGuardStationService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class BaseInfoDefenceGuardStationController {

	private static Logger logger = Logger.getLogger(BaseInfoDefenceGuardStationController.class);

	@Autowired
	private BaseInfoDefenceGuardStationService baseInfoDefenceGuardStationService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;	
	/**
	 * 
	 * @param req
	 * @return 打开护路工作站信息页面
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefenceGuardStationInit", method = RequestMethod.GET)
	public String baseInfoDefenceGuardStationInit(HttpServletRequest req) {
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoDefenceGuardStation/baseInfoDefenceGuardStationInit";
	}

	/**
	 * 
	 * @param dgm
	 * @param baseInfoDefenceGuardStationPo
	 * @return 查询护路工作站信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefenceGuardStationQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoDefenceGuardStationQueryList(DataGridModel dgm,
			BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		return baseInfoDefenceGuardStationService.baseInfoDefenceGuardStationQueryList(dgm, baseInfoDefenceGuardStationPo);
	}
	
	
	@RequestMapping(value = "/baseInfo/checkGuardOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkGuardOrgId(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceGuardStationPo.getLng();
			String lat=baseInfoDefenceGuardStationPo.getLat();
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
	 * @param req
	 * @return 弹出护路工作站信息添加页面
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoDefenceGuardStationPopWin", method = RequestMethod.GET)
	public String addBaseInfoDefenceGuardStationPopWin(HttpServletRequest req) {

		List<Map<String, Object>> serviceMode = PubData.getDictList("GUARD_STATION_SERVICE_MODE");
		req.setAttribute("serviceMode", serviceMode);

		List<Map<String, Object>> isAcceptList = PubData.getDictList("ISACCEPT");
		req.setAttribute("isAcceptList", isAcceptList);

		return "pc/bussiness/baseInfoDefenceGuardStation/addBaseInfoDefenceGuardStationPopWin";
	}

	/**
	 * 
	 * @param baseInfoDefenceGuardStationPo
	 * @return 保存护路工作站信息
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoDefenceGuardStation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceGuardStationPo.getLng();
			String lat=baseInfoDefenceGuardStationPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefenceGuardStationPo.setTheGeom(theGeom);
			baseInfoDefenceGuardStationPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("gsta");
			baseInfoDefenceGuardStationPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = id;
					obj[1] = baseInfoDefenceGuardStationPo.getName();
					obj[2] = baseInfoDefenceGuardStationPo.getLng();
					obj[3] = baseInfoDefenceGuardStationPo.getLat();
					obj[4] = baseInfoDefenceGuardStationPo.getTheGeom();
					obj[5] = baseInfoDefenceGuardStationPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if (baseInfoDefenceGuardStationService.addBaseInfoDefenceGuardStation(baseInfoDefenceGuardStationPo,objList) > 0) {
				map.put("mes", "新增护路工作站信息成功");
			} else {
				map.put("mes", "新增护路工作站信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增护路工作站信息失败", e);
			}
			map.put("mes", "新增护路工作站信息失败");
		}
		return map;
	}

	/**
	 * 
	 * @param req
	 * @return 弹出修改信息添加页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefenceGuardStationPopWin", method = RequestMethod.GET)
	public String updateBaseInfoDefenceGuardStationPopWin(HttpServletRequest req) throws UnsupportedEncodingException {

		List<Map<String, Object>> serviceMode = PubData.getDictList("GUARD_STATION_SERVICE_MODE");
		req.setAttribute("serviceMode", serviceMode);

		List<Map<String, Object>> isAcceptList = PubData.getDictList("ISACCEPT");
		req.setAttribute("isAcceptList", isAcceptList);
		// 照片回显
		String photosName = req.getParameter("photosName");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
				

		return "pc/bussiness/baseInfoDefenceGuardStation/updateBaseInfoDefenceGuardStationPopWin";
	}

	/**
	 * 
	 * @param baseInfoDefenceGuardStationPo
	 * @return 保存修改护路工作站信息
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefenceGuardStation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefenceGuardStationPo.getLng();
			String lat=baseInfoDefenceGuardStationPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefenceGuardStationPo.setTheGeom(theGeom);
			baseInfoDefenceGuardStationPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = baseInfoDefenceGuardStationPo.getId();
					obj[1] = baseInfoDefenceGuardStationPo.getName();
					obj[2] = baseInfoDefenceGuardStationPo.getLng();
					obj[3] = baseInfoDefenceGuardStationPo.getLat();
					obj[4] = baseInfoDefenceGuardStationPo.getTheGeom();
					obj[5] = baseInfoDefenceGuardStationPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if (baseInfoDefenceGuardStationService.updateBaseInfoDefenceGuardStation(baseInfoDefenceGuardStationPo,objList) > 0) {
				map.put("mes", "更新护路工作站信息成功");
			} else {
				map.put("mes", "更新护路工作站信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改护路工作站信息失败", e);
			}
			map.put("mes", "修改护路工作站信息失败");
		}
		return map;
	}

	/**
	 * 
	 * @param idList
	 * @return 删除护路工作站信息
	 */
	@RequestMapping(value = "/baseInfo/deleteBaseInfoDefenceGuardStation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoDefenceGuardStation(@RequestParam("idList") List<String> idList) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoDefenceGuardStationService.deleteBaseInfoDefenceGuardStation(idList);
			int sum = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j];
			}
			if (sum == idList.size()) {
				map.put("mes", "删除成功[" + sum + "]条护路工作站信息");
			} else {
				map.put("mes", "删除护路工作站信息失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除护路工作站信息失败", e);
			}
			map.put("mes", "删除护路工作站信息失败");
		}
		return map;
	}



}
