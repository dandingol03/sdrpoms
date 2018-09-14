package com.pc.busniess.baseInfoPartTunnel.web.controller;
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
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;
import com.pc.busniess.baseInfoPartTunnel.service.BaseInfoPartTunnelService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

/**
 * 该Controller为PC端处理隧道基本信息的数据传输以及页面跳转
 * @author CaoLu
 * @version 1.0
 * @since
 */
@Controller
public class BaseInfoPartTunnelController {
	
	private static Logger logger = Logger.getLogger(BaseInfoPartTunnelController.class);
	
	@Autowired
	private BaseInfoPartTunnelService baseInfoPartTunnelService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 打开隧道信息页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/baseInfoPartTunnelInit",method=RequestMethod.GET)
	public String baseInfoPartTunnelInit(HttpServletRequest req){
		//TODO 打开隧道信息初始化init页面
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoPartTunnel/baseInfoPartTunnelInit";
	}

	/**
	 * 查询隧道信息                          
	 * @param dgm
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/baseInfoPartTunnelQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 查询隧道信息                        
		return baseInfoPartTunnelService.baseInfoPartTunnelQueryList(dgm,baseInfoPartTunnelPo);
	}
	
	/**
	 * 弹出隧道信息添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoPartTunnelPopWin",method=RequestMethod.GET)
	public String addBaseInfoPartTunnelPopWin(HttpServletRequest req){
		//TODO 弹出隧道信息添加页面
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//查询关联铁路信息
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		
	    return "pc/bussiness/baseInfoPartTunnel/addBaseInfoPartTunnelPopWin";
	}
	
	/**
	 * 保存隧道信息
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoPartTunnel",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 保存隧道信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartTunnelPo.getLng();
		String lat=baseInfoPartTunnelPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartTunnelPo.setTheGeom(theGeom);
		baseInfoPartTunnelPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("tunn");
		baseInfoPartTunnelPo.setId(id);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = id;
				obj[1] = baseInfoPartTunnelPo.getName();
				obj[2] = baseInfoPartTunnelPo.getLng();
				obj[3] = baseInfoPartTunnelPo.getLat();
				obj[4] = baseInfoPartTunnelPo.getTheGeom();
				obj[5] = baseInfoPartTunnelPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartTunnelService.addBaseInfoPartTunnel(baseInfoPartTunnelPo,objList) > 0){
				map.put("mes", "新增隧道信息成功");
			}else{
				map.put("mes", "新增隧道信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增隧道信息失败",e);
			}
			map.put("mes", "新增隧道信息失败");
		}
		return map; 
	}
	
	/**
	 * 弹出修改信息修改页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoPartTunnelPopWin",method=RequestMethod.GET)
	public String updateBaseInfoPartTunnelPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//TODO 弹出修改信息修改页面
		//行别
		List<Map<String,Object>> railStreamList = PubData.getDictList("RAIL_STREAM");
		req.setAttribute("railStreamList",railStreamList);
		//查询关联铁路信息
		req.setAttribute("railsInfo",baseInfoRailService.findAllRails());
		String photosName = new String(req.getParameter("photosName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		// 照片回显
		String filePhotosName = new String(req.getParameter("filePhotosName").getBytes("iso8859-1"), "utf-8");
		if(!(filePhotosName.equals("undefined"))){
		    req.setAttribute("filePhotosName", filePhotosName); 
		}else{
		    req.setAttribute("filePhotosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoPartTunnel/updateBaseInfoPartTunnelPopWin";
	}
	
	/**
	 * 保存修改隧道信息
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoPartTunnel",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 保存修改隧道信息
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartTunnelPo.getLng();
		String lat=baseInfoPartTunnelPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		baseInfoPartTunnelPo.setTheGeom(theGeom);
		baseInfoPartTunnelPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		List<Object[]> objList = new ArrayList<Object[]>();
		if(regionalRails.size()>0&&regionalRails!=null){
			for (int i = 0; i < regionalRails.size(); i++) {
				Map<String, Object> row=regionalRails.get(i);
				//组装插入数据
				//id name lng lat the_geom org_id rail_id
				Object[] obj = new Object[8];
				obj[0] = baseInfoPartTunnelPo.getId();
				obj[1] = baseInfoPartTunnelPo.getName();
				obj[2] = baseInfoPartTunnelPo.getLng();
				obj[3] = baseInfoPartTunnelPo.getLat();
				obj[4] = baseInfoPartTunnelPo.getTheGeom();
				obj[5] = baseInfoPartTunnelPo.getOrgId();
				obj[6] = row.get("railId");
				obj[7] = row.get("railStreamId");
				objList.add(obj);
			}	
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(baseInfoPartTunnelService.updateBaseInfoPartTunnel(baseInfoPartTunnelPo,objList) > 0){
				map.put("mes", "更新隧道信息成功");
			}else{
				map.put("mes", "更新隧道信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改隧道信息失败",e);
			}
			map.put("mes", "修改隧道信息失败");
		}
		return map; 
	}
	
	/**
	 * 删除隧道信息
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoPartTunnel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoPartTunnel(@RequestParam("idList") List<String> idList){  
		//TODO 删除隧道信息
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoPartTunnelService.deleteBaseInfoPartTunnel(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条隧道信息");
			}else{
				map.put("mes", "删除隧道信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除隧道信息失败",e);
			}
			map.put("mes", "删除隧道信息失败");
		}
		return map;
	}
}
