/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月3日 下午2:56:02 
 */
package com.pc.exhibition.map.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.Point;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.busniess.baseInfoDefenceGuardStation.service.BaseInfoDefenceGuardStationService;
import com.pc.busniess.baseInfoDefencePoliceHouse.service.BaseInfoDefencePoliceHouseService;
import com.pc.busniess.baseInfoDefencePoliceStation.service.BaseInfoPoliceStationService;
import com.pc.busniess.baseInfoDefencePropaganda.service.BaseInfoDefencePropagandaService;
import com.pc.busniess.baseInfoKeyperson.service.BaseInfoKeypersonService;
import com.pc.busniess.baseInfoPartBridge.service.BaseInfoPartBridgeService;
import com.pc.busniess.baseInfoPartCulvert.service.BaseInfoPartCulvertService;
import com.pc.busniess.baseInfoPartJunction.service.BaseInfoPartJunctionService;
import com.pc.busniess.baseInfoPartStation.service.BaseInfoPartStationService;
import com.pc.busniess.baseInfoPartStation.stationYard.service.BaseInfoStationYardService;
import com.pc.busniess.baseInfoPartTrajection.service.BaseInfoPartTrajectionService;
import com.pc.busniess.baseInfoPartTunnel.service.BaseInfoPartTunnelService;
import com.pc.busniess.baseInfoPeripheralPlace.service.BaseInfoPeripheralPlaceService;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.videoMonitorInfo.service.VideoMonitorInfoService;

/**
 * 接口测试
 * 
 * @Package: com.pc.exhibition.map.web.controller
 * @author: jwl
 * @date: 2018年4月3日 下午2:56:02
 */
@Controller
public class TestInterfaceController {
	//监控
	@Autowired
	private VideoMonitorInfoService videoMonitorInfoService;
	//铁路
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	//周边场所
	@Autowired
	private BaseInfoPeripheralPlaceService baseInfoPeripheralPlaceService;
	//隧道
	@Autowired
	private BaseInfoPartTunnelService baseInfoPartTunnelService;
	//行人易穿行
	@Autowired
	private BaseInfoPartTrajectionService baseInfoHiddenTrajectionService;
	//车站
	@Autowired
	private BaseInfoPartStationService baseInfoPartStationService;
	//站场
	@Autowired
	private BaseInfoStationYardService baseInfoStationYardService;
	//道口
	@Autowired
	private BaseInfoPartJunctionService baseInfoPartJunctionService;
	//涵洞
	@Autowired
	private BaseInfoPartCulvertService baseInfoPartCulvertService;
	//桥梁
	@Autowired
	private BaseInfoPartBridgeService baseInfoPartBridgeService;
	//重点人
	@Autowired
	private BaseInfoKeypersonService baseInfoKeypersonService;
	//护路宣传点
	@Autowired
	private BaseInfoDefencePropagandaService baseInfoDefencePropagandaService;
	//警务站
	@Autowired
	private BaseInfoPoliceStationService baseInfoPoliceStationService;
	//派出所
	@Autowired
	private BaseInfoDefencePoliceHouseService baseInfoDefencePoliceHouseService;
	//护路工作站
	@Autowired
	private BaseInfoDefenceGuardStationService baseInfoDefenceGuardStationService;
	//广播警示柱
	@Autowired
	private BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;

	@RequestMapping(value = "/baseInfo/test", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> videoMonitorInfoInit() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("监控",videoMonitorInfoService.findById("vide_150476333795011"));
//		map.put("铁路",baseInfoRailService.findById("rail_1500000000002"));
//		map.put("周边",baseInfoPeripheralPlaceService.findById("dang_1050001"));
//		map.put("隧道",baseInfoPartTunnelService.findById("tunn_1505977244545"));
//		map.put("行人易穿行",baseInfoHiddenTrajectionService.findById("traj_1506344739994"));
//		map.put("车站",baseInfoPartStationService.findById("psta_150006"));
//		map.put("站场",baseInfoStationYardService.findById("staY_150006"));
//		map.put("道口",baseInfoPartJunctionService.findById("junc_1230000000035"));
//		map.put("涵洞",baseInfoPartCulvertService.findById("culv_1648284000"));
//		map.put("桥梁",baseInfoPartBridgeService.findById("brid_1000000000008"));
//		map.put("重点人",baseInfoKeypersonService.findById("keyp_1525748648250"));
//		map.put("警示柱",baseInfoDefencePropagandaService.findById("prop_1505465037595"));
//		map.put("警务站",baseInfoPoliceStationService.findById("polS_1525683557643"));
//		map.put("派出所",baseInfoDefencePoliceHouseService.findById("poli_1525684430397"));
//		map.put("护路工作站",baseInfoDefenceGuardStationService.findById("gsta_1508239"));
//		map.put("广播警示柱",baseInfoDefenceBroadcastService.findById("broa_15010003"));
		return map;

	}
	public static void main(String[] args) {
//		System.err
//		.println(10 + ((90 / (154.92020897394417)) * 103));
//		for (int i = 3; i > 0; i--) {
//		System.out.println(i);
//		}
		
//		System.out.println(shape(45144.167833536885
//				,45294.24749172287
//				,45336.888163738775));
//		116.3647024333477	39.855697453022
//		116.36489018797874	39.85577657818794
//		116.36507391929626	39.85586240887642
//		double a=(Math.sqrt((116.3647024333477-116.36489018797874)*(116.3647024333477-116.36489018797874)+(39.855697453022-39.85577657818794)*(39.855697453022-39.85577657818794)))*
//    			(Math.sqrt((116.36507391929626-116.36489018797874)*(116.36507391929626-116.36489018797874)+(39.85586240887642-39.85577657818794)*(39.85586240887642-39.85577657818794)));
//		System.out.println(a);
//		double b=(116.3647024333477*116.36507391929626)+(39.855697453022*39.85586240887642)
//    			;
//		System.out.println(b/a);
//		18271.09999999999 
		//18229.19999999999  
//		18251.69999999999  
//			//O(0,0)A(x1,y1)  B(x2,y2) 
    	//x1x2+y1y2/|OA|*|OB|    >   0是锐角 
		double a=Math.pow((18229.19999999999-18271.09999999999), 2)+Math.pow((18229.19999999999-18251.69999999999), 2);
		System.out.println(a);
		double b=Math.pow((18251.69999999999-18271.09999999999), 2);
		System.out.println(b);
		
//		ystem.err.println(map);//取出最近的点的前一个点
//		double a=Point.getDistance(ConverterUtils.toDouble(lat),ConverterUtils.toDouble(lng),ConverterUtils.toDouble(map2.get(lat)),ConverterUtils.toDouble(map2.get(lng)));
//		double b=Point.getDistance(ConverterUtils.toDouble(map.get(lng)),ConverterUtils.toDouble(map.get(lng)),ConverterUtils.toDouble(map2.get(lng)),ConverterUtils.toDouble(map2.get(lng)));
//		double c=Point.getDistance(ConverterUtils.toDouble(lat),ConverterUtils.toDouble(lng),ConverterUtils.toDouble(map.get(lat)),ConverterUtils.toDouble(map.get(lng)));
//		System.out.println();
//		System.out.println(Math.pow((b), 2));
//		System.out.println(Math.pow((c), 2));
//		if(Math.pow((a), 2)+Math.pow((b), 2)>Math.pow((c), 2)){
//			
//		}
	}

}
