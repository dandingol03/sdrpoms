package com.pc.busniess.baseInfoDefencePropaganda.web.controller;

/**
 * @author lyf
 *
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
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;
import com.pc.busniess.baseInfoDefencePropaganda.service.BaseInfoDefencePropagandaService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
/**
 *  D.Steven
 */


@Controller
public class BaseInfoDefencePropagandaController {

	private static Logger logger = Logger.getLogger(BaseInfoDefencePropagandaController.class);
	
	@Autowired
	private BaseInfoDefencePropagandaService baseInfoDefencePropagandaService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	/**
	 * 
	 * @param req
	 * @return
	 * 打开护路宣传点信息页面
	 */
	@RequestMapping(value="/baseInfo/baseInfoDefencePropagandaInit",method=RequestMethod.GET)
	public String baseInfoDefencePropagandaInit(HttpServletRequest req){
		//铁路筛选
		req.setAttribute("rails",baseInfoRailService.findAllRails());
		return "pc/bussiness/baseInfoDefencePropaganda/baseInfoDefencePropagandaInit";
	}
	/**
	 * 
	 * @param dgm
	 * @param baseInfoDefencePropagandaPo
	 * @return
	 * 查询护路宣传点信息
	 */
	@RequestMapping(value = "/baseInfo/baseInfoDefencePropagandaQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoDefencePropagandaQueryList(DataGridModel dgm, BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		return baseInfoDefencePropagandaService.baseInfoDefencePropagandaQueryList(dgm,baseInfoDefencePropagandaPo); 
	}
	/**
	 * 
	 * @param req
	 * @return
	 * 弹出护路宣传点信息添加页面
	 */
	@RequestMapping(value="/baseInfo/addBaseInfoDefencePropagandaPopWin",method=RequestMethod.GET)
	public String addBaseInfoDefencePropagandaPopWin(HttpServletRequest req){
		List<Map<String,Object>> proTypeList = PubData.getDictList("DEFENCE_PRO_TYPE");
		req.setAttribute("proTypeList",proTypeList);
	    return "pc/bussiness/baseInfoDefencePropaganda/addBaseInfoDefencePropagandaPopWin";
	}
	/**
	 * 
	 * @param baseInfoDefencePropagandaPo
	 * @return
	 * 保存护路宣传点信息
	 */
	
	@RequestMapping(value = "/baseInfo/addBaseInfoDefencePropaganda",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefencePropagandaPo.getLng();
			String lat=baseInfoDefencePropagandaPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefencePropagandaPo.setTheGeom(theGeom);
			baseInfoDefencePropagandaPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			String id = NextID.getNextID("prop");
			baseInfoDefencePropagandaPo.setId(id);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = id;
					obj[1] = baseInfoDefencePropagandaPo.getName();
					obj[2] = baseInfoDefencePropagandaPo.getLng();
					obj[3] = baseInfoDefencePropagandaPo.getLat();
					obj[4] = baseInfoDefencePropagandaPo.getTheGeom();
					obj[5] = baseInfoDefencePropagandaPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if(baseInfoDefencePropagandaService.addBaseInfoDefencePropaganda(baseInfoDefencePropagandaPo,objList) > 0){
				map.put("mes", "新增护路宣传点位信息成功");
			}else{
				map.put("mes", "新增护路宣传点位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增护路宣传点位信息失败",e);
			}
			map.put("mes", "新增护路宣传点位信息失败");
		}
		return map; 
	}
	
	@RequestMapping(value = "/baseInfo/checkProOrgId",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkProOrgId(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefencePropagandaPo.getLng();
			String lat=baseInfoDefencePropagandaPo.getLat();
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
	 * @return
	 * 弹出修改信息添加页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/baseInfo/updateBaseInfoDefencePropagandaPopWin",method=RequestMethod.GET)
	public String updateBaseInfoBroadcastPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		List<Map<String,Object>> proTypeList = PubData.getDictList("DEFENCE_PRO_TYPE");
		req.setAttribute("proTypeList",proTypeList);
		String photosName = req.getParameter("photosName");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/baseInfoDefencePropaganda/updateBaseInfoDefencePropagandaPopWin";
	}
	/**
	 * 
	 * @param baseInfoDefencePropagandaPo
	 * @return
	 * 保存修改护路宣传点信息
	 */
	@RequestMapping(value = "/baseInfo/updateBaseInfoDefencePropaganda",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String lng=baseInfoDefencePropagandaPo.getLng();
			String lat=baseInfoDefencePropagandaPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			baseInfoDefencePropagandaPo.setTheGeom(theGeom);
			baseInfoDefencePropagandaPo.setOrgId(orgId);
			//id name lng lat the_geom org_id rail_id
			List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
			List<Object[]> objList = new ArrayList<Object[]>();
			if(regionalRails.size()>0&&regionalRails!=null){
				for (int i = 0; i < regionalRails.size(); i++) {
					Map<String, Object> row=regionalRails.get(i);
					//组装插入数据
					//id name lng lat the_geom org_id rail_id
					Object[] obj = new Object[8];
					obj[0] = baseInfoDefencePropagandaPo.getId();
					obj[1] = baseInfoDefencePropagandaPo.getName();
					obj[2] = baseInfoDefencePropagandaPo.getLng();
					obj[3] = baseInfoDefencePropagandaPo.getLat();
					obj[4] = baseInfoDefencePropagandaPo.getTheGeom();
					obj[5] = baseInfoDefencePropagandaPo.getOrgId();
					obj[6] = row.get("railId");
					obj[7] = row.get("railStreamId");
					objList.add(obj);
				}	
			}
			if(baseInfoDefencePropagandaService.updateBaseInfoDefencePropaganda(baseInfoDefencePropagandaPo,objList) > 0){
				map.put("mes", "更新护路宣传点位信息成功");
			}else{
				map.put("mes", "更新护路宣传点位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改护路宣传点位信息失败",e);
			}
			map.put("mes", "修改护路宣传点位信息失败");
		}
		return map; 
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * 删除护路宣传点信息
	 */
	@RequestMapping(value="/baseInfo/deleteBaseInfoDefencePropaganda",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteBaseInfoDefencePropaganda(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = baseInfoDefencePropagandaService.deleteBaseInfoDefencePropaganda(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条护路宣传点位信息");
			}else{
				map.put("mes", "删除护路宣传点位信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除护路宣传点位信息失败",e);
			}
			map.put("mes", "删除护路宣传点位信息失败");
		}
		return map;
	}
	
	//打开页面
	@RequestMapping(value="/baseInfo/fileUploadPropagandaPopWin",method=RequestMethod.GET)
	public String fileUploadPropagandaPopWin(HttpServletRequest req){
		return "pc/bussiness/baseInfoDefencePropaganda/fileUploadPopWin";
	}
	}
