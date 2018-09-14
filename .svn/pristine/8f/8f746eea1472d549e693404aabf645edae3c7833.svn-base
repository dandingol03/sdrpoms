/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月2日 下午2:34:35 
 */
package com.pc.exhibition.map.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;
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
import com.pc.busniess.baseInfoRail.baseInfoRailStream.service.BaseInfoRailStreamService;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
import com.pc.busniess.baseInfoRail.service.BaseInfoRailService;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;
import com.pc.busniess.patrolManagementTask.dao.PatrolDangerInfoDao;
import com.pc.busniess.videoMonitorInfo.service.VideoMonitorInfoService;
import com.pc.exhibition.bussniess.service.ExBussniessService;
import com.pc.exhibition.map.service.ExMapPopWinService;
import com.pc.socket.bean.Commands;
import com.pc.socket.service.handler.MultiSocketCMDHandler;

/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月2日 下午2:34:35 
 */
@Controller
public class ExMapPopWinController {
	
	private static Logger logger = Logger.getLogger(ExMapPopWinController.class);
	
	@Autowired
	private ExMapPopWinService exMapPopWinService;
	//监控
	@Autowired
	private VideoMonitorInfoService videoMonitorInfoService;
	//铁路
	@Autowired
	private BaseInfoRailService baseInfoRailService;
	//铁路数量信息
	@Autowired
	private ExBussniessService exBussniessService;
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
	//隐患点
	@Autowired
	private PatrolDangerInfoDao patrolDangerInfoDao;
	//专职队员
	//在线干部
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	@Autowired
	private BaseInfoRailStreamService baseInfoRailStreamService;
	//铁路
	@RequestMapping(value = "/exhibition/map/baseInfoRail", method = RequestMethod.GET)
	public String baseInfoRail(HttpServletRequest req, 
			@RequestParam(value="lnglat[]") String lnglat[],
			@RequestParam("id") String id, @RequestParam("orgId") String orgId) {
			String name="";
			String railName="";
			String classifyName="";
			List<Map<String, Object>> railLists=baseInfoRailService.find(id);
			if(railLists!=null&&railLists.size()>0){
				for(int i=0;i<railLists.size();i++){
					Map<String,Object> row=railLists.get(i);
					name=row.get("name").toString();
					railName=row.get("railName").toString();
					classifyName=row.get("classifyName").toString();
				}
			}
			ObjectMapper obj=new ObjectMapper();
			req.setAttribute("classifyName", classifyName);
			req.setAttribute("name", name);
			req.setAttribute("railName", railName);
			try {
				req.setAttribute("rails", obj.writeValueAsString(railLists));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//铁路相关数量信息
		List<String> railIdList=new ArrayList<String>();
		railIdList.add(id);
		//重点部位统计
		JSONObject railKeyPartJson = JSONObject.fromObject(exBussniessService.getUserKeyPartListInfo(orgId,railIdList));
		req.setAttribute("railKeyPartCount",railKeyPartJson);
		//联防点位统计
		JSONObject railJDenfenseJson = JSONObject.fromObject(exBussniessService.getUserJDenfenseListInfo(orgId,railIdList));
		req.setAttribute("railJDenfenseCount", railJDenfenseJson);
		//周边单位统计
		JSONObject railPeripheralPlaceJson = JSONObject.fromObject(exBussniessService.getUserPeripheralPlaceListInfo(orgId,railIdList));
		req.setAttribute("railPeripheralPlaceCount", railPeripheralPlaceJson);
		//GPS->km
		String lng = lnglat[0];
		String lat = lnglat[1];
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		if(StringUtils.equals(id.substring(0,4),"rail")){
			List<Map<String, Object>> rails= baseInfoRailStreamService.findById(id);
			for (int i = 0; i < rails.size(); i++) {
				Map<String,Object> rows=rails.get(i);
				String streamId=rows.get("id").toString();
//				String railName=rows.get("railName").toString();
				//铁路区和街乡镇的数量
				rows.put("railOrgCountMap", exMapPopWinService.getRailOrgCount(orgId,streamId));
				//辖内里程
				rows.put("railOrgKMMap", exMapPopWinService.getRailOrgKM(orgId,streamId));
				//
				rows.put("km",CommonUtil.KM2MStrbyDec(exMapPopWinService.getKm(lng, lat,streamId)));	
				list.add(rows);
			}
		}else{
			List<Map<String, Object>> rails= baseInfoRailStreamService.findById(id);
			for (int i = 0; i < rails.size(); i++) {
				Map<String,Object> rows=rails.get(i);
				String streamId=rows.get("id").toString();
//				String railName=rows.get("railName").toString();
				//铁路区和街乡镇的数量
				rows.put("railOrgCountMap", exMapPopWinService.getRailOrgCount(orgId,streamId));
				//辖内里程
				rows.put("railOrgKMMap", exMapPopWinService.getRailOrgKM(orgId,streamId));
				//
				rows.put("km",CommonUtil.KM2MStrbyDec(exMapPopWinService.getKm(lng, lat,streamId)));
				list.add(rows);
			}
		}
		try {
			req.setAttribute("railsInfo", obj.writeValueAsString(list));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pc/exhibition/map/getRailPopWin";
	}
	//重点人
	@RequestMapping(value = "/exhibition/map/keyperson", method = RequestMethod.GET)
	public String keyperson(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> keyperson = baseInfoKeypersonService.findById(id);
		req.setAttribute("keyperson", keyperson);
		return "pc/exhibition/map/getKeypersonPopWin";
	}
	
	//桥梁
	@RequestMapping(value = "/exhibition/map/baseInfoPartBridge", method = RequestMethod.GET)
	public String baseInfoPartBridge(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> partBridge = baseInfoPartBridgeService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPartBridgeId(id));
		req.setAttribute("partBridge", partBridge);
		return "pc/exhibition/map/getBridgePopWin";
	}
	
	//涵洞
	@RequestMapping(value = "/exhibition/map/baseInfoPartCulvert", method = RequestMethod.GET)
	public String baseInfoPartCulvert(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> partCulvert = baseInfoPartCulvertService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPartCulvertId(id));
		req.setAttribute("partCulvert", partCulvert);
		return "pc/exhibition/map/getCulvertPopWin";
	}

	//道口
	@RequestMapping(value = "/exhibition/map/baseInfoPartJunction", method = RequestMethod.GET)
	public String baseInfoPartJunction(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> partJunction = baseInfoPartJunctionService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPartJunctionId(id));
		req.setAttribute("partJunction", partJunction);
		return "pc/exhibition/map/getJunctionPopWin";
	}
	
	//隧道
	@RequestMapping(value = "/exhibition/map/baseInfoPartTunnel", method = RequestMethod.GET)
	public String baseInfoPartTunnel(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> tunnel = baseInfoPartTunnelService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPartTunnelId(id));
		req.setAttribute("tunnel", tunnel);
		return "pc/exhibition/map/getTunnelPopWin";
	}
	
	//车站
	@RequestMapping(value = "/exhibition/map/baseInfoPartStation", method = RequestMethod.GET)
	public String baseInfoPartStation(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> partStation = baseInfoPartStationService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPartStationId(id));
		req.setAttribute("partStation", partStation);
		return "pc/exhibition/map/getPartStationPopWin";
	}
	
	//行人易穿行
	@RequestMapping(value = "/exhibition/map/baseInfoHiddenTrajection", method = RequestMethod.GET)
	public String baseInfoHiddenTrajection(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> hiddenTrajection = baseInfoHiddenTrajectionService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByHiddenTrajectionId(id));
		req.setAttribute("hiddenTrajection", hiddenTrajection);
		return "pc/exhibition/map/getHiddenTrajectionPopWin";
	}
	
	//隐患
	@RequestMapping(value = "/exhibition/map/dangerInfo", method = RequestMethod.GET)
	public String dangerInfo(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> dangerInfo = patrolDangerInfoDao.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByDangerInfoId(id));
		req.setAttribute("dangerInfo", dangerInfo);
		return "pc/exhibition/map/getPatrolDangerInfoPopWin";
	}
	
	//护路工作站
	@RequestMapping(value = "/exhibition/map/baseInfoDefenceGuardStation", method = RequestMethod.GET)
	public String baseInfoDefenceGuardStation(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> defenceGuardStation = baseInfoDefenceGuardStationService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByDefenceGuardStationId(id));
		req.setAttribute("defenceGuardStation", defenceGuardStation);
		return "pc/exhibition/map/getGuardStationPopWin";
	}
	
	//护路宣传点
	@RequestMapping(value = "/exhibition/map/baseInfoDefencePropaganda", method = RequestMethod.GET)
	public String baseInfoDefencePropaganda(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> defencePropaganda = baseInfoDefencePropagandaService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByDefencePropagandaId(id));
		req.setAttribute("defencePropaganda", defencePropaganda);
		return "pc/exhibition/map/getPropagandaPopWin";
	}
	
	//广播警示柱
	@RequestMapping(value = "/exhibition/map/baseInfoDefenceBroadcast", method = RequestMethod.GET)
	public String baseInfoDefenceBroadcast(HttpServletRequest req, @RequestParam("id") String id) {
		
		Map<String, Object> defenceBroadcast = baseInfoDefenceBroadcastService.findById(id);
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userName = user.getUsername();
		defenceBroadcast.put("userName", userName);//报修人
		req.setAttribute("nearByRails", exMapPopWinService.findByDefenceBroadcastId(id));
		req.setAttribute("defenceBroadcast", defenceBroadcast);
		//临近线路
//		[-86, -122, 1, 0]
		byte[] tmpId=new byte[] {(byte)Integer.parseInt("-86",16),(byte)Integer.parseInt("-122",16),(byte)Integer.parseInt("1",16),(byte)Integer.parseInt("0",16)};
//		if(defenceBroadcast.get("number")!=null&&StringUtils.isNotBlank(defenceBroadcast.get("number").toString())){
//			String number=defenceBroadcast.get("number").toString();
//			String regex = "(.{2})";
//			number = number.replaceAll (regex, "$1 ");
//			String[] num=number.split(" ");
//			byte[] tmpId=new byte[] {(byte)Integer.parseInt(num[0],16),(byte)Integer.parseInt(num[1],16),(byte)Integer.parseInt(num[2],16),(byte)Integer.parseInt(num[3],16)};
//			try {
//				Commands.sendDatagramPacket(tmpId,Commands.TRIGGER_BROADCAST);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		return "pc/exhibition/map/getBroadcastPopWin";	
	}
	//广播警示柱(功能调节)
	@RequestMapping(value = "/exhibition/map/adjustmentBroadcast", method = RequestMethod.GET)
	public void Broadcast(HttpServletRequest req, @RequestParam("number") String number,@RequestParam("commands") String commands) {
			String regex = "(.{2})";
			number = number.replaceAll (regex, "$1 ");
			String[] num=number.split(" ");
			byte[] tmpId=new byte[] {(byte)Integer.parseInt(num[0],16),(byte)Integer.parseInt(num[1],16),(byte)Integer.parseInt(num[2],16),(byte)Integer.parseInt(num[3],16)};
			try {
				if(StringUtils.equals(commands, "TRIGGER_BROADCAST")){
					Commands.sendDatagramPacket(tmpId,Commands.TRIGGER_BROADCAST);//触发播报
				}else if(StringUtils.equals(commands,"ADD_TTS_VOLUMN" )){
					Commands.sendDatagramPacket(tmpId,Commands.ADD_TTS_VOLUMN);//TTS音量加
				}else if(StringUtils.equals(commands, "MINUS_TTS_VOLUMN")){
					Commands.sendDatagramPacket(tmpId,Commands.MINUS_TTS_VOLUMN);//TTS音量减
				}else if(StringUtils.equals(commands,"NEXT_SONG" )){
					Commands.sendDatagramPacket(tmpId,Commands.NEXT_SONG);//下一曲
				}else if(StringUtils.equals(commands, "PREV_SONG")){
					Commands.sendDatagramPacket(tmpId,Commands.PREV_SONG);//上一曲
				}else if(StringUtils.equals(commands, "ADD_VOLUMN")){
					Commands.sendDatagramPacket(tmpId,Commands.ADD_VOLUMN);//音量加
				}else if(StringUtils.equals(commands, "MINUS_VOLUMN")){
					Commands.sendDatagramPacket(tmpId,Commands.MINUS_VOLUMN);//音量减
				}else if(StringUtils.equals(commands, "PLAY")){
					Commands.sendDatagramPacket(tmpId,Commands.PLAY);//播放
				}else if(StringUtils.equals(commands, "VOLTAGE_INQUIRE")){
					Commands.sendDatagramPacket(tmpId,Commands.VOLTAGE_INQUIRE);//查询设备电压值 
				}else if(StringUtils.equals(commands, "LED_TEXT_INQUIRE")){
					Commands.sendDatagramPacket(tmpId,Commands.LED_TEXT_INQUIRE);//查询LED当前显示的文字
				}else if(StringUtils.equals(commands, "TRIGGER_COUNT_INQUIRE")){
					Commands.sendDatagramPacket(tmpId,Commands.TRIGGER_COUNT_INQUIRE);//查询触发次数
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
		
	// 监控页面
	@RequestMapping(value = "/exhibition/map/videoMonitorInfo", method = RequestMethod.GET)
	public String videoMonitorInfo(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> video = videoMonitorInfoService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByVideoId(id));
		req.setAttribute("video", video);
		return "pc/exhibition/map/vedioPopWin";
	}
	
	//派出所
	@RequestMapping(value = "/exhibition/map/baseInfoDefencePoliceHouse", method = RequestMethod.GET)
	public String baseInfoDefencePoliceHouse(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> defencePoliceHouse = baseInfoDefencePoliceHouseService.findById(id);
		req.setAttribute("defencePoliceHouse", defencePoliceHouse);
		return "pc/exhibition/map/getDefencePoliceHousePopWin";
	}
	
	//警务站
	@RequestMapping(value = "/exhibition/map/baseInfoPoliceStation", method = RequestMethod.GET)
	public String baseInfoPoliceStation(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> policeStation = baseInfoPoliceStationService.findById(id);
		req.setAttribute("policeStation", policeStation);
		return "pc/exhibition/map/getPoliceStationPopWin";
	}
	
	//在岗队员 
	@RequestMapping(value = "/exhibition/map/teamMemberInfo", method = RequestMethod.GET)
	public String teamMemberInfo(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> teamMemberInfo = partrolTeamUserRelationService.findById(id);
		req.setAttribute("teamMemberInfo", teamMemberInfo);
		return "pc/exhibition/map/getTeamMemberInfoPopWin";
	}
	
	//在线干部
	@RequestMapping(value = "/exhibition/map/cardeInfo", method = RequestMethod.GET)
	public String CardeInfo(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> cardeInfo = partrolTeamUserRelationService.findByCardeId(id);
		req.setAttribute("cardeInfo", cardeInfo);
		return "pc/exhibition/map/getCardeInfoPopWin";
	}
		
	//周边场所
	@RequestMapping(value = "/exhibition/map/baseInfoPeripheralPlace", method = RequestMethod.GET)
	public String baseInfoPeripheralPlace(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> peripheralPlace = baseInfoPeripheralPlaceService.findById(id);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByPeripheralPlaceId(id));
		req.setAttribute("peripheralPlace", peripheralPlace);
		return "pc/exhibition/map/getPeripheralPlacePopWin";
	}

	//站场
	@RequestMapping(value = "/exhibition/map/baseInfoStationYard", method = RequestMethod.GET)
	public String baseInfoStationYard(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> stationYard = baseInfoStationYardService.findById(id);
		req.setAttribute("stationYard", stationYard);
		return "pc/exhibition/map/getPartStationYardPopWin";
	}
	//进站信号机
	@RequestMapping(value = "/exhibition/map/baseInfoStationSignal", method = RequestMethod.GET)
	public String baseInfoStationSignal(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> stationSignal = exMapPopWinService.findById(id);
		req.setAttribute("stationSignal", stationSignal);
		return "pc/exhibition/map/getPartStationSignalPopWin";
	}
	 //给出公里标    求出经纬度
	@RequestMapping(value = "/exhibition/map/CalculationLnglat",method = RequestMethod.POST)
	@ResponseBody   
	public Map<String, Object> CalculationLnglat(HttpServletRequest req,
		@RequestParam("id") String baseInfoRailId,@RequestParam("km") String kilometerMark) {
//		List<Map<String, Object>>  ls=baseInfoRailService.findAllRails();
//		for (int i = 0; i < ls.size(); i++) {
//			Map<String, Object> row=ls.get(i);
//			 logger.info("rail start end"+exMapPopWinService.findLngLatKm(row.get("id").toString(),"-11111111111111111111"));
//		}
	    return  exMapPopWinService.findLngLatKm(baseInfoRailId,kilometerMark);
	}
	@RequestMapping(value = "/exhibition/map/getStreamRials",method = RequestMethod.POST)
	@ResponseBody   
	public List<Map<String, Object>> getStreamRials(HttpServletRequest req) {
	    return  exMapPopWinService.getStreamRials();
	}
	 //铁路详细信息PopWin km -> 经纬度
	@RequestMapping(value = "/exhibition/map/getKmLocationRailPopWin", method = RequestMethod.GET)
	public String getKmLocationPopWin(HttpServletRequest request,
			@RequestParam("id") String id,
			@RequestParam("km") String kilometerMark) {
		//经纬度公里标
		Map<String, Object> map = exMapPopWinService.findLngLatKm(id, kilometerMark);
		map.put("KM", CommonUtil.KM2MStrbyDec(kilometerMark));
		request.setAttribute("map", map);
		//定位点附近情况
		List<Map<String, String>> neighbourhoodList = exMapPopWinService.getNeighbourhoodListPopWin(map.get("lng").toString(), map.get("lat").toString(),BaseInfoRailPo.R);
		request.setAttribute("neighbourhoodList", neighbourhoodList);
		return "pc/exhibition/map/getKmLocationRailPopWin";
	}
	//进站信号机数据清洗--待删除
	@RequestMapping(value = "/exhibition/map/getgps", method = RequestMethod.GET)
	public String getgps() {
		List<Map<String, Object>> listm=exMapPopWinService.getBridges();
		for (int i = 0; i < listm.size(); i++) {
			Map<String, Object> row=listm.get(i);
			if(StringUtils.isEmpty(row.get("middle").toString())){
				continue;
			}
			Map<String, Object> map = exMapPopWinService.findLngLatKm(row.get("railId").toString(),row.get("middle").toString());
			if(map==null){continue;}
			System.out.println(map);
			if(!StringUtils.equals(map.get("type").toString(),"locationNo")){
				exMapPopWinService.update(map.get("lng").toString(),map.get("lat").toString(),row.get("id").toString());
			}
		}
		//经纬度公里标
		
		return null;
	}	//北京测试实地考察video
	@RequestMapping(value = "/exhibition/map/video", method = RequestMethod.GET)
	public String video(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> video = exMapPopWinService.findByIdVideoCS(id);
		req.setAttribute("video", video);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByVideonearByRails(id));
		return "pc/exhibition/map/getVideoPopWin";
	}
	@RequestMapping(value = "/exhibition/map/CSPoliceHouse", method = RequestMethod.GET)
	public String CSPoliceHouse(HttpServletRequest req, @RequestParam("id") String id) {
		Map<String, Object> CSPoliceHouse = exMapPopWinService.findByIdCSPoliceHouse(id);
		req.setAttribute("policeHouse", CSPoliceHouse);
		//临近线路
		req.setAttribute("nearByRails", exMapPopWinService.findByCSPoliceHousenearByRails(id));
		return "pc/exhibition/map/getCSPoliceHousePopWin";
	}
}