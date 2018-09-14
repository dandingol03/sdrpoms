package com.pc.busniess.baseInfoPartJunction.web.controller;
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
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;
import com.pc.busniess.baseInfoPartJunction.service.BaseInfoPartJunctionService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**
 * 该Controller为PC端处理道口基本信息的数据传输以及页面跳转
 * @author CaoLu
 * @version 1.0
 * @since
 *
 */

@Controller
public class BaseInfoPartJunctionController {
private static Logger logger = Logger.getLogger(BaseInfoPartJunctionController.class);
	
	@Autowired
	private BaseInfoPartJunctionService baseInfoPartJunctionService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开道口信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartJunctionInit",method=RequestMethod.GET)
	public String baseInfoPartJunctionInit(HttpServletRequest req){
		//TODO 打开道口信息初始化init界面
		/**
		 * 加载数据字典开始
		 */
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//守护情况
		List<Map<String,Object>> guardStreamList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("guardStreamList",guardStreamList);
		//隶属铁路局(公司)
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//运营状态
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
		//道路类别
		List<Map<String,Object>> roadClassifyList = PubData.getDictList("PART_JUNCTION_ROAD_CLASSIFY");
		req.setAttribute("roadClassifyList",roadClassifyList);
		//铺面材料
		List<Map<String,Object>> materialList = PubData.getDictList("PART_JUNCTION_MATERIAL");
		req.setAttribute("materialList",materialList);
		//道口等级
		List<Map<String,Object>> levelList = PubData.getDictList("PART_JUNCTION_LEVEL");
		req.setAttribute("levelList",levelList);
		/**
		 * 加载数据字典结束
		 */
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartJunction/baseInfoPartJunctionInit";

	}

	/**
	 * 查询道口信息                          
	 * @param dgm
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoPartJunctionQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoPartJunctionQueryList(DataGridModel dgm, BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 查询道口信息                        
		return baseInfoPartJunctionService.baseInfoPartJunctionQueryList(dgm,baseInfoPartJunctionPo);
	}
	
	/**
	 * 弹出道口信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartJunctionPopWin",method=RequestMethod.GET)
	public String addBaseInfoPartJunctionPopWin(HttpServletRequest req){
		//TODO 弹出道口信息添加页面
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//守护情况
		List<Map<String,Object>> guardStreamList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("guardStreamList",guardStreamList);
		//隶属铁路局(公司)
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//运营状态
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
		//道路类别
		List<Map<String,Object>> roadClassifyList = PubData.getDictList("PART_JUNCTION_ROAD_CLASSIFY");
		req.setAttribute("roadClassifyList",roadClassifyList);
		//铺面材料
		List<Map<String,Object>> materialList = PubData.getDictList("PART_JUNCTION_MATERIAL");
		req.setAttribute("materialList",materialList);
		//道口等级
		List<Map<String,Object>> levelList = PubData.getDictList("PART_JUNCTION_LEVEL");
		req.setAttribute("levelList",levelList);
		//查询铁路相关信息
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		
	    return "pc/bussiness/baseInfoPartJunction/addBaseInfoPartJunctionPopWin";
	}
	
	/**
	 * 保存道口信息
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoPartJunction",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 保存道口信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartJunctionPo.getLng();
		String lat=baseInfoPartJunctionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartJunctionPo.setTheGeom(theGeom);
		baseInfoPartJunctionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("junc");
		baseInfoPartJunctionPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartJunctionPo.getName();
				obj[2] = baseInfoPartJunctionPo.getLng();
				obj[3] = baseInfoPartJunctionPo.getLat();
				obj[4] = baseInfoPartJunctionPo.getTheGeom();
				obj[5] = baseInfoPartJunctionPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartJunctionService.addBaseInfoPartJunction(baseInfoPartJunctionPo,objList) > 0){
				map.put("mes", "新增道口信息成功");
			}else{
				map.put("mes", "新增道口信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增道口信息失败",e);
			}
			map.put("mes", "新增道口信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息修改页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartJunctionPopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartJunctionPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//TODO 弹出修改信息修改页面
		/**
		 * 加载数据字典开始
		 */
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//守护情况
		List<Map<String,Object>> guardStreamList = PubData.getDictList("GUARD_STATUS");
		req.setAttribute("guardStreamList",guardStreamList);
		//隶属铁路局(公司)
		List<Map<String,Object>> railBureauList = PubData.getDictList("RAIL_BUREAU");
		req.setAttribute("railBureauList",railBureauList);
		//运营状态
		List<Map<String,Object>> stateList = PubData.getDictList("OPERATION_STATE");
		req.setAttribute("stateList",stateList);
		//道路类别
		List<Map<String,Object>> roadClassifyList = PubData.getDictList("PART_JUNCTION_ROAD_CLASSIFY");
		req.setAttribute("roadClassifyList",roadClassifyList);
		//铺面材料
		List<Map<String,Object>> materialList = PubData.getDictList("PART_JUNCTION_MATERIAL");
		req.setAttribute("materialList",materialList);
		//道口等级
		List<Map<String,Object>> levelList = PubData.getDictList("PART_JUNCTION_LEVEL");
		req.setAttribute("levelList",levelList);
		/**
		 * 加载数据字典结束
		 */
		//查询铁路相关信息
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartJunction/updateBaseInfoPartJunctionPopWin";
	}
	
	/**
	 * 保存修改道口信息
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoPartJunction",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 保存修改道口信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartJunctionPo.getLng();
		String lat=baseInfoPartJunctionPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartJunctionPo.setTheGeom(theGeom);
		baseInfoPartJunctionPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPartJunctionPo.getId();
				obj[1] = baseInfoPartJunctionPo.getName();
				obj[2] = baseInfoPartJunctionPo.getLng();
				obj[3] = baseInfoPartJunctionPo.getLat();
				obj[4] = baseInfoPartJunctionPo.getTheGeom();
				obj[5] = baseInfoPartJunctionPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartJunctionService.updateBaseInfoPartJunction(baseInfoPartJunctionPo,objList) > 0){
				map.put("mes", "更新道口信息成功");
			}else{
				map.put("mes", "更新道口信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改道口信息失败",e);
			}
			map.put("mes", "修改道口信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除道口信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartJunction",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoPartJunction(@RequestParam("idList") List<String> idList){  
		//TODO 删除道口信息
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPartJunctionService.deleteBaseInfoPartJunction(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条道口信息");
			}else{
				map.put("mes", "删除道口信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除道口信息失败",e);
			}
			map.put("mes", "删除道口信息失败");
		}
		return map;
	}
}
