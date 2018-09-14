package com.pc.busniess.baseInfoPeripheralPlace.web.controller;


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
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;
import com.pc.busniess.baseInfoPeripheralPlace.service.BaseInfoPeripheralPlaceService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.web.controller 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:44:44
 */
@Controller
public class BaseInfoPeripheralPlaceController {
	
	private static Logger logger = Logger.getLogger(BaseInfoPeripheralPlaceController.class);
	
	@Autowired
	private BaseInfoPeripheralPlaceService baseInfoPeripheralPlaceService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPeripheralPlaceInit",method=RequestMethod.GET)
	public String baseInfoPeripheralPlaceInit(HttpServletRequest req){
		//周边场所类别 peripheral_place
		List<Map<String,Object>> peripherallList = PubData.getDictList("PERIPHERAL_PLACE");
		req.setAttribute("peripherallList", peripherallList);
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPeripheralPlace/baseInfoPeripheralPlaceInit";
	}
	/**
	 * 查询
	 * @param dgm
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPeripheralPlaceQueryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> baseInfoPeripheralPlaceQueryList(DataGridModel dgm,BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		return baseInfoPeripheralPlaceService.baseInfoPeripheralPlaceQueryList(dgm, baseInfoPeripheralPlacePo);
	}
	/**
	 * 新增页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPeripheralPlacePopWin",method=RequestMethod.GET)
	public String addBaseInfoPeripheralPlacePopWin(HttpServletRequest req){
		//周边场所类别 peripheral_place
		List<Map<String,Object>> peripherallList = PubData.getDictList("PERIPHERAL_PLACE");
		req.setAttribute("peripherallList", peripherallList);
		return "pc/bussiness/baseInfoPeripheralPlace/addBaseInfoPeripheralPlacePopWin";
	}
	/**
	 * 保存新增数据
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPeripheralPlace",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addBaseInfoPeripheralPlace(BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPeripheralPlacePo.getLng();
		String lat=baseInfoPeripheralPlacePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPeripheralPlacePo.setTheGeom(theGeom);
		baseInfoPeripheralPlacePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("plac");
		baseInfoPeripheralPlacePo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[10];
				obj[0] = id;
				obj[1] = baseInfoPeripheralPlacePo.getName();
				obj[2] = baseInfoPeripheralPlacePo.getLng();
				obj[3] = baseInfoPeripheralPlacePo.getLat();
				obj[4] = baseInfoPeripheralPlacePo.getTheGeom();
				obj[5] = baseInfoPeripheralPlacePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				obj[8] = baseInfoPeripheralPlacePo.getCategory();
				obj[9] = baseInfoPeripheralPlacePo.getType();
				objList.add(obj);
			}	
		}
		Map<String,String> map = new HashMap<String, String>();
		try {
			if(baseInfoPeripheralPlaceService.addBaseInfoPeripheralPlace(baseInfoPeripheralPlacePo,objList)>0){
				map.put("mes", "新增周边场所信息成功");
			}else{
				map.put("mes", "新增周边场所信息失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增失败",e);
			}
			map.put("mes", "新增失败");
		}
		return map;
	}
	/**
	 * 修改页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPeripheralPlacePopWin",method=RequestMethod.GET)
	public String updateBaseInfoPeripheralPlacePopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//周边场所类别
		List<Map<String,Object>> peripherallList = PubData.getDictList("PERIPHERAL_PLACE");
		req.setAttribute("peripherallList", peripherallList);
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPeripheralPlace/updateBaseInfoPeripheralPlacePopWin";
	}
	/**
	 * 保存修改信息
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPeripheralPlace",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateBaseInfoPeripheralPlace(BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPeripheralPlacePo.getLng();
		String lat=baseInfoPeripheralPlacePo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPeripheralPlacePo.setTheGeom(theGeom);
		baseInfoPeripheralPlacePo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[10];
				obj[0] = baseInfoPeripheralPlacePo.getId();
				obj[1] = baseInfoPeripheralPlacePo.getName();
				obj[2] = baseInfoPeripheralPlacePo.getLng();
				obj[3] = baseInfoPeripheralPlacePo.getLat();
				obj[4] = baseInfoPeripheralPlacePo.getTheGeom();
				obj[5] = baseInfoPeripheralPlacePo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				obj[8] = baseInfoPeripheralPlacePo.getCategory();
				obj[9] = baseInfoPeripheralPlacePo.getType();
				objList.add(obj);
			}	
		}
		Map<String,String> map = new HashMap<String, String>();
		try {
			if(baseInfoPeripheralPlaceService.updateBaseInfoPeripheralPlace(baseInfoPeripheralPlacePo,objList)>0){
				map.put("mes", "修改周边场所信息成功");
			}else{
				map.put("mes", "修改周边场所信息失败");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改失败",e);
			}
			map.put("mes", "修改失败");
		}
		return map;
	}
	/**
	 * 删除周边场所信息
	 * @param idList
	 * @return
	 */
		@RequestMapping(value="/baseInfo/delBaseInfoPeripheralPlace",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,String> delBaseInfoPeripheralPlace(@RequestParam("idList") List<String> idList){
			Map<String,String> map = new HashMap<String,String>();
			try {
				int[] result = baseInfoPeripheralPlaceService.delBaseInfoPeripheralPlace(idList);
				int sum = 0;
				for(int j = 0; j < result.length; j ++){
					sum += result[j];
				}
				if(sum==idList.size()){
					map.put("mes", "删除成功["+sum+"]条周边场所信息");
				}else{
					map.put("mes", "删除周边场所失败");
				}
			} catch (Exception e) {
				if(logger.isDebugEnabled()){
					logger.debug("删除失败",e);
				}
				map.put("mes", "删除失败");
			}
			return map;
		}
}
