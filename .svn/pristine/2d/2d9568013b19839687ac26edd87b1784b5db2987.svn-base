package com.mobile.busniess.mobileBaseInfoPartTunnel.web.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoPartTunnel.service.MobileBaseInfoPartTunnelService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;
import com.pc.busniess.baseInfoPartTunnel.service.BaseInfoPartTunnelService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoPartTunnelController {
	
	private static Logger logger = Logger.getLogger(MobileBaseInfoPartTunnelController.class);
	
	@Autowired
	private BaseInfoPartTunnelService baseInfoPartTunnelService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoPartTunnelService mobilebaseInfoPartTunnelService;

	/**
	 * 查询隧道信息                          
	 * @param dgm
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/baseInfoPartTunnelQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 查询隧道信息                        
		return mobilebaseInfoPartTunnelService.baseInfoPartTunnelQueryList(dgm,baseInfoPartTunnelPo);
	}
	/**
	 * 保存隧道信息
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoPartTunnel",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 保存隧道信息
		Map<String, Object> map = new HashMap<String, Object>();
		//POINT(116.436740528506 39.9012398573984)
		String lng=baseInfoPartTunnelPo.getLng();
		String lat=baseInfoPartTunnelPo.getLat();
		String theGeom="POINT("+lng+" "+lat+")";
		String orgId=orgService.getOrg(lng, lat);
		if(orgId==""){
			map.put("mes", "请填写正确的经纬度坐标,范围:北京");
			map.put("success",false);
			return map;
		}
		baseInfoPartTunnelPo.setTheGeom(theGeom);
		baseInfoPartTunnelPo.setOrgId(orgId);
		//id name lng lat the_geom org_id rail_id
		List<Map<String, Object>> regionalRails=baseInfoRailService.findRegionalRail(theGeom,BaseInfoRailPo.R);
		String id = NextID.getNextID("psta");
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
		map.put("data",baseInfoPartTunnelPo);
		try {
			if(baseInfoPartTunnelService.addBaseInfoPartTunnel(baseInfoPartTunnelPo,objList) > 0){
				map.put("mes", "新增隧道信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增隧道信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增隧道信息失败",e);
			}
			map.put("mes", "新增隧道信息失败");
			map.put("success",false);
		}
		return map; 
	}
	
}
