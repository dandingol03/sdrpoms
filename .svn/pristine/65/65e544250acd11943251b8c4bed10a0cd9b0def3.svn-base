package com.mobile.busniess.mobileBaseInfoDefencePropaganda.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileBaseInfoDefencePropaganda.service.MobileBaseInfoDefencePropagandaService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.org.service.OrgService;
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;
import com.pc.busniess.baseInfoDefencePropaganda.service.BaseInfoDefencePropagandaService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;

@Controller
public class MobileBaseInfoDefencePropagandaController {

	private static Logger logger = Logger.getLogger(MobileBaseInfoDefencePropagandaController.class);
	
	@Autowired
	private BaseInfoDefencePropagandaService baseInfoDefencePropagandaService;
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MobileBaseInfoDefencePropagandaService mobilebaseInfoDefencePropagandaService;
	
	//护路宣传点查询接口  ----权限
	@RequestMapping(value = "/mobile/baseInfo/baseInfoDefencePropagandaQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baseInfoGuardStationQueryList(DataGridModel dgm,HttpServletRequest request,
			BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		return mobilebaseInfoDefencePropagandaService.baseInfoDefencePropagandaQueryList(dgm,
						baseInfoDefencePropagandaPo);
	}
	/**
	 * 
	 * @param baseInfoDefencePropagandaPo
	 * @return
	 * 保存护路宣传点信息
	 */
	
	@RequestMapping(value = "/mobile/baseInfo/addBaseInfoDefencePropaganda",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String lng=baseInfoDefencePropagandaPo.getLng();
			String lat=baseInfoDefencePropagandaPo.getLat();
			String theGeom="POINT("+lng+" "+lat+")";
			String orgId=orgService.getOrg(lng, lat);
			if(orgId==""){
				map.put("mes", "请填写正确的经纬度坐标,范围:北京");
				map.put("success",false);
				return map;
			}
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
			map.put("data",baseInfoDefencePropagandaPo);
			if(baseInfoDefencePropagandaService.addBaseInfoDefencePropaganda(baseInfoDefencePropagandaPo,objList) > 0){
				map.put("mes", "新增护路宣传点位信息成功");
				map.put("success",true);
			}else{
				map.put("mes", "新增护路宣传点位信息失败");
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增护路宣传点位信息失败",e);
			}
			map.put("mes", "新增护路宣传点位信息失败");
			map.put("success",false);
		}
		return map; 
	}
}
