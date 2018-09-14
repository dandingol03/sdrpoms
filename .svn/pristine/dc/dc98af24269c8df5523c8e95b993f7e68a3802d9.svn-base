package com.pc.busniess.baseInfoKeyperson.web.controller;


/**
 * @author lyf
 *
 */
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
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoKeyperson.po.BaseInfoKeypersonPo;
import com.pc.busniess.baseInfoKeyperson.service.BaseInfoKeypersonService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
/**
 *  D.Steven
 */
@Controller
public class BaseInfoKeypersonController {

	private static Logger logger = Logger.getLogger(BaseInfoKeypersonController.class);
	
	@Autowired
	private BaseInfoKeypersonService baseInfoKeypersonService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;	
	/**
	 * 
	 * @param req
	 * @return
	 * 打开重点人信息页面
	 */
	@RequestMapping(value="/baseInfo/baseInfoKeypersonInit",method=RequestMethod.GET)
	public String baseInfoKeypersonInit(HttpServletRequest req){
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoKeyperson/baseInfoKeypersonInit";
	}
	/**
	 * 
	 * @param dgm
	 * @param baseInfoKeypersonPo
	 * @return
	 * 查询重点人信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoKeypersonQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoKeypersonQueryList(DataGridModel dgm, BaseInfoKeypersonPo baseInfoKeypersonPo) {
		return baseInfoKeypersonService.baseInfoKeypersonQueryList(dgm,baseInfoKeypersonPo); 
	}
	/**
	 * 
	 * @param req
	 * @return
	 * 弹出重点人信息添加页面
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoKeypersonPopWin",method=RequestMethod.GET)
	public String addBaseInfoKeypersonPopWin(HttpServletRequest req){
	    return "pc/bussiness/baseInfoKeyperson/addBaseInfoKeypersonPopWin";
	}
	
	@RequestMapping(value = "/baseInfo/checkPersonOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkPersonOrgId(BaseInfoKeypersonPo baseInfoKeypersonPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoKeypersonPo.getLng();
			String lat=baseInfoKeypersonPo.getLat();
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
	 * @param baseInfoKeypersonPo
	 * @return
	 * 保存重点人信息
	 */
	@RequestMapping(value = "/baseInfo/addBaseInfoKeyperson",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoKeyperson(BaseInfoKeypersonPo baseInfoKeypersonPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoKeypersonPo.getLng();
			String lat=baseInfoKeypersonPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoKeypersonPo.setTheGeom(theGeom);
			baseInfoKeypersonPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("keyp");
			baseInfoKeypersonPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = id;
					obj[1] = baseInfoKeypersonPo.getName();
					obj[2] = baseInfoKeypersonPo.getLng();
					obj[3] = baseInfoKeypersonPo.getLat();
					obj[4] =baseInfoKeypersonPo.getTheGeom();
					obj[5] = baseInfoKeypersonPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if(baseInfoKeypersonService.addBaseInfoKeyperson(baseInfoKeypersonPo,objList) > 0){
				map.put("mes", "新增重点人信息成功");
			}else{
				map.put("mes", "新增重点人信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增重点人信息失败",e);
			}
			map.put("mes", "新增重点人信息失败");
		}
		return map; 
	}
	/**
	 * 
	 * @param req
	 * @return
	 * 弹出修改信息添加页面
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoKeypersonPopWin",method=RequestMethod.GET)
	public String updateBaseInfoKeypersonPopWin(HttpServletRequest req){
		String photosName = req.getParameter("photosName");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoKeyperson/updateBaseInfoKeypersonPopWin";
	}
	/**
	 * 
	 * @param baseInfoKeypersonPo
	 * @return
	 * 保存修改重点人信息
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoKeyperson",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoKeyperson(BaseInfoKeypersonPo baseInfoKeypersonPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoKeypersonPo.getLng();
			String lat=baseInfoKeypersonPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoKeypersonPo.setTheGeom(theGeom);
			baseInfoKeypersonPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("keyp");
			baseInfoKeypersonPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = baseInfoKeypersonPo.getId();
					obj[1] = baseInfoKeypersonPo.getName();
					obj[2] = baseInfoKeypersonPo.getLng();
					obj[3] = baseInfoKeypersonPo.getLat();
					obj[4] = baseInfoKeypersonPo.getTheGeom();
					obj[5] = baseInfoKeypersonPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if(baseInfoKeypersonService.updateBaseInfoKeyperson(baseInfoKeypersonPo,objList) > 0){
				map.put("mes", "更新重点人信息成功");
			}else{
				map.put("mes", "更新重点人信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改重点人信息失败",e);
			}
			map.put("mes", "修改重点人信息失败");
		}
		return map; 
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * 删除重点人信息
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoKeyperson",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoKeyperson(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoKeypersonService.deleteBaseInfoKeyperson(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条重点人信息");
			}else{
				map.put("mes", "删除重点人信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除重点人信息失败",e);
			}
			map.put("mes", "删除重点人信息失败");
		}
		return map;
	}
	
}
