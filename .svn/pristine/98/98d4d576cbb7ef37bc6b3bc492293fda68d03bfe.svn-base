package com.pc.busniess.baseInfoPartCulvert.web.controller;

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
import com.pc.busniess.baseInfoPartCulvert.po.BaseInfoPartCulvertPo;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**
 * 该Controller为PC端处理涵洞基本信息的数据传输以及页面跳转
 * @author SSY
 * @version 1.0
 */
@Controller
public class BaseInfoPartCulvertController {

	private static Logger logger = Logger.getLogger(BaseInfoPartCulvertController.class);
	
	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开涵洞信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartCulvertInit",method=RequestMethod.GET)
	public String baseInfoPartCulvertInit(HttpServletRequest req) {
		// TODO 打开涵洞信息页面
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_CULVERT_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartCulvert/partCulvertInit";
	}
	
	/**
	 * 查询涵洞基本信息
	 * @param dgm
	 * @param baseInfoPartCulvertPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoPartCulvertQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoPartCulvertQueryList(DataGridModel dgm, BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 查询涵洞基本信息
		return baseInfoPartCulvertService.baseInfoPartCulvertQueryList(dgm,baseInfoPartCulvertPo); 
	}
	
	/**
	 * 弹出涵洞信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartCulvertPopWin",method=RequestMethod.GET)
	public String addBaseInfoPartCulvertPopWin(HttpServletRequest req){
		// TODO 弹出涵洞信息添加页面
		// 涵洞分类
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_CULVERT_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		
		// 行别
		List<Map<String,Object>> streamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("streamList",streamList);
		
		// 守护情况
		List<Map<String,Object>> guardStatusList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("guardStatusList",guardStatusList);
		
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
				
		// 参考铁路线
		req.setAttribute("railsInfo", baseInfoRailService.findAllRails());
	    return "pc/bussiness/baseInfoPartCulvert/addPartCulvertPopWin";
	}
	
	/**
	 * 保存添加涵洞基本信息
	 * @param baseInfoPartCulvertPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addPartCulvert",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 保存添加涵洞基本信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartCulvertPo.getLng();
		String lat=baseInfoPartCulvertPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartCulvertPo.setTheGeom(theGeom);
		baseInfoPartCulvertPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("culv");
		baseInfoPartCulvertPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartCulvertPo.getName();
				obj[2] = baseInfoPartCulvertPo.getLng();
				obj[3] = baseInfoPartCulvertPo.getLat();
				obj[4] = baseInfoPartCulvertPo.getTheGeom();
				obj[5] = baseInfoPartCulvertPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartCulvertService.addBaseInfoPartCulvert(baseInfoPartCulvertPo,objList) > 0){
				map.put("mes", "新增涵洞信息成功");
			}else{
				map.put("mes", "新增涵洞信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增涵洞信息失败",e);
			}
			map.put("mes", "新增涵洞信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartCulvertPopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartCulvertPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		// TODO 弹出修改信息添加页面
		// 涵洞分类
		List<Map<String,Object>> classifyList = PubData.getDictList("PART_CULVERT_CLASSIFY");
		req.setAttribute("classifyList",classifyList);
		
		// 行别
		List<Map<String,Object>> streamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("streamList",streamList);
		
		// 守护情况
		List<Map<String,Object>> guardStatusList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("guardStatusList",guardStatusList);
		
		//得到数据字典中穿跨形式的数据,然后赋值到该集合中;
		List<Map<String,Object>> crossSpanList = PubData.getDictList("CROSSSPAN");
		req.setAttribute("crossSpanList",crossSpanList);
		
		// 参考铁路线
		req.setAttribute("railsInfo", baseInfoRailService.findAllRails());
		
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartCulvert/updatePartCulvertPopWin";
	}
	
	/**
	 * 保存修改涵洞基本信息
	 * @param baseInfoPartCulvertPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updatePartCulvert",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 保存修改涵洞基本信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartCulvertPo.getLng();
		String lat=baseInfoPartCulvertPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartCulvertPo.setTheGeom(theGeom);
		baseInfoPartCulvertPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPartCulvertPo.getId();
				obj[1] = baseInfoPartCulvertPo.getName();
				obj[2] = baseInfoPartCulvertPo.getLng();
				obj[3] = baseInfoPartCulvertPo.getLat();
				obj[4] = baseInfoPartCulvertPo.getTheGeom();
				obj[5] = baseInfoPartCulvertPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartCulvertService.updateBaseInfoPartCulvert(baseInfoPartCulvertPo,objList) > 0){
				map.put("mes", "更新涵洞信息成功");
			}else{
				map.put("mes", "更新涵洞信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改涵洞信息失败",e);
			}
			map.put("mes", "修改涵洞信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除涵洞基本信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartCulvert",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoPartCulvert(@RequestParam("idList") List<String> idList){  
		// TODO 删除涵洞基本信息
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPartCulvertService.deleteBaseInfoPartCulvert(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条涵洞信息");
			}else{
				map.put("mes", "删除涵洞信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除涵洞信息失败",e);
			}
			map.put("mes", "删除涵洞信息失败");
		}
		return map;
	}
}
