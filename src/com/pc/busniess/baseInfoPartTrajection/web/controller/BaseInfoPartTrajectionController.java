package com.pc.busniess.baseInfoPartTrajection.web.controller;


/**
 *  D.Steven
 */
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;
import com.pc.busniess.baseInfoPartTrajection.service.BaseInfoPartTrajectionService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
/**
 * 行人易穿行基本信息的数据传输以及页面跳转
 * @author mdf
 * @version 1.0
 */

@Controller
public class BaseInfoPartTrajectionController {

	private static Logger logger = Logger.getLogger(BaseInfoPartTrajectionController.class);
	
	@Autowired
	private BaseInfoPartTrajectionService baseInfoHiddenTrajectionService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开行人易穿行信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartTrajectionInit",method=RequestMethod.GET)
	public String baseInfoHiddenTrajectionInit(HttpServletRequest req){
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartTrajection/baseInfoPartTrajectionInit";
	}
	
	/**
	 * 查询行人易穿行信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/BaseInfoPartTrajectionQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		return baseInfoHiddenTrajectionService.baseInfoHiddenTrajectionQueryList(dgm,baseInfoHiddenTrajectionPo); 
	}
	
	/**
	 * 弹出行人易穿行信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartTrajectionPop",method=RequestMethod.GET)
	public String addBaseInfoPartTrajectionPopWin(HttpServletRequest req){
		//GUARDRAIL_CONDITION 护栏情况
		List<Map<String,Object>> conditionList = PubData.getDictList("GUARDRAIL_CONDITION");
		req.setAttribute("conditionList",conditionList);
		
		List<Map<String,Object>> dangerLevelList = PubData.getDictList("PLACE_DANGER_LEVEL");
		req.setAttribute("dangerLevelList",dangerLevelList);
		List<Map<String,Object>> statusList = PubData.getDictList("HIDDEN_TRAJECTION_STATUS");
		req.setAttribute("statusList",statusList);
	    return "pc/bussiness/baseInfoPartTrajection/addBaseInfoPartTrajectionPopWin";
	}
	
	/**
	 * 保存行人易穿行信息
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addHiddenTrajection",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoHiddenTrajectionPo.getLng();
		String lat=baseInfoHiddenTrajectionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoHiddenTrajectionPo.setTheGeom(theGeom);
		baseInfoHiddenTrajectionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("traj");
		baseInfoHiddenTrajectionPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[9];
				obj[0] = id;
				obj[1] = baseInfoHiddenTrajectionPo.getName();
				obj[2] = baseInfoHiddenTrajectionPo.getLng();
				obj[3] = baseInfoHiddenTrajectionPo.getLat();
				obj[4] = baseInfoHiddenTrajectionPo.getTheGeom();
				if(StringUtils.isBlank(baseInfoHiddenTrajectionPo.getRegion())){
					obj[5] =new ArrayList<String>().toString();
				}else{
					obj[5] =baseInfoHiddenTrajectionPo.getRegion();
				}
				obj[6] = baseInfoHiddenTrajectionPo.getOrgId();
				obj[7] = row.get("railId");
				obj[8] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoHiddenTrajectionService.addBaseInfoHiddenTrajection(baseInfoHiddenTrajectionPo,objList) > 0){
				map.put("mes", "新增行人易穿行部位信息成功");
			}else{
				map.put("mes", "新增行人易穿行部位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增行人易穿行部位信息失败",e);
			}
			map.put("mes", "新增行人易穿行部位信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartTrajectionPopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartTrajectionPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//GUARDRAIL_CONDITION 护栏情况
		List<Map<String,Object>> conditionList = PubData.getDictList("GUARDRAIL_CONDITION");
		req.setAttribute("conditionList",conditionList);
		
		List<Map<String,Object>> dangerLevelList = PubData.getDictList("PLACE_DANGER_LEVEL");
		req.setAttribute("dangerLevelList",dangerLevelList);
		List<Map<String,Object>> statusList = PubData.getDictList("HIDDEN_TRAJECTION_STATUS");
		req.setAttribute("statusList",statusList);
		// 照片回显
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartTrajection/updateBaseInfoPartTrajectionPopWin";
	}
	
	/**
	 * 保存修改行人易穿行信息
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateHiddenTrajection",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoHiddenTrajectionPo.getLng();
		String lat=baseInfoHiddenTrajectionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoHiddenTrajectionPo.setTheGeom(theGeom);
		baseInfoHiddenTrajectionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[9];
				obj[0] = baseInfoHiddenTrajectionPo.getId();
				obj[1] = baseInfoHiddenTrajectionPo.getName();
				obj[2] = baseInfoHiddenTrajectionPo.getLng();
				obj[3] = baseInfoHiddenTrajectionPo.getLat();
				obj[4] = baseInfoHiddenTrajectionPo.getTheGeom();
				if(StringUtils.isBlank(baseInfoHiddenTrajectionPo.getRegion())){
					obj[5] =new ArrayList<String>().toString();
				}else{
					obj[5] =baseInfoHiddenTrajectionPo.getRegion();
				}
				obj[6] = baseInfoHiddenTrajectionPo.getOrgId();
				obj[7] = row.get("railId"); 
				obj[8] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoHiddenTrajectionService.updateBaseInfoHiddenTrajection(baseInfoHiddenTrajectionPo,objList) > 0){
				map.put("mes", "更新行人易穿行部位信息成功");
			}else{
				map.put("mes", "更新行人易穿行部位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改行人易穿行部位信息失败",e);
			}
			map.put("mes", "修改行人易穿行部位信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除行人易穿行信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartTrajection",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoHiddenTrajection(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoHiddenTrajectionService.deleteBaseInfoHiddenTrajection(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条行人易穿行部位信息");
			}else{
				map.put("mes", "删除行人易穿行部位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除行人易穿行部位信息失败",e);
			}
			map.put("mes", "删除行人易穿行部位信息失败");
		}
		return map;
	}
	
}
