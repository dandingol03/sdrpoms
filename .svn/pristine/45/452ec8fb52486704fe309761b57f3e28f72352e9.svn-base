package com.mobile.busniess.mobileBaseInfoKeyperson.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoKeyperson.service.MobileBaseInfoKeypersonService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoKeyperson.po.BaseInfoKeypersonPo;
import com.pc.busniess.baseInfoKeyperson.service.BaseInfoKeypersonService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoKeypersonController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoKeypersonController.class);
	
	@Autowired
	private BaseInfoKeypersonService baseInfoKeypersonService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;	
	@Autowired
	private MobileBaseInfoKeypersonService mobilebaseInfoKeypersonService;

	//重点人查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoKeypersonQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoKeypersonPo baseInfoKeypersonPo,@RequestBody String mobileJSON) {
		return mobilebaseInfoKeypersonService.baseInfoKeypersonQueryList(dgm,
						baseInfoKeypersonPo);
	}
	/**
	 * 
	 * @param baseInfoKeypersonPo
	 * @return
	 * 保存重点人信息
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoKeyperson",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoKeyperson(BaseInfoKeypersonPo baseInfoKeypersonPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String lng=baseInfoKeypersonPo.getLng();
			String lat=baseInfoKeypersonPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			if(orgId==""){
				map.put("success",false);
				return map;
			}
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
			map.put("data",baseInfoKeypersonPo);
			if(baseInfoKeypersonService.addBaseInfoKeyperson(baseInfoKeypersonPo,objList) > 0){
				map.put("mes", "新增重点人信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增重点人信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增重点人信息失败",e);
			}
			map.put("mes", "新增重点人信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
