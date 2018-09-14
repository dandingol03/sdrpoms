package com.pc.exhibition.map.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.Point;
import com.pc.bsp.common.util.ProportionLngLat;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
import com.pc.busniess.baseInfoRail.dao.BaseInfoRailDao;
import com.pc.exhibition.map.dao.ExMapPopWinDao;



/**
 * @author simple
 *
 */
@Service("exMapPopWinService")
public class ExMapPopWinService{
	@Autowired
	private BaseInfoRailDao baseInfoRailDao;
	@Autowired
	private ExMapPopWinDao exMapPopWinDao;

	//隐患临近线路
	public List<Map<String, Object>> findByDangerInfoId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByDangerInfoId(id);
	}
	//广播柱临近线路
	public List<Map<String, Object>> findByDefenceBroadcastId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByDefenceBroadcastId(id);
	}
	//工作站临近线路
	public List<Map<String, Object>> findByDefenceGuardStationId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByDefenceGuardStationId(id);
	}
	//宣传点临近线路
	public List<Map<String, Object>> findByDefencePropagandaId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByDefencePropagandaId(id);
	}
	//周边场所临近线路
	public List<Map<String, Object>> findByPeripheralPlaceId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPeripheralPlaceId(id);
	}
	//桥梁临近线路
	public List<Map<String, Object>> findByPartBridgeId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPartBridgeId(id);
	}
	//涵洞临近线路
	public List<Map<String, Object>> findByPartCulvertId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPartCulvertId(id);
	}
	//道口临近线路
	public List<Map<String, Object>> findByPartJunctionId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPartJunctionId(id);
	}
	//隧道临近线路
	public List<Map<String, Object>> findByPartTunnelId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPartTunnelId(id);
	}
	//车站临近线路
	public List<Map<String, Object>> findByPartStationId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByPartStationId(id);
	}
	//行人易穿行临近线路
	public List<Map<String, Object>> findByHiddenTrajectionId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByHiddenTrajectionId(id);
	}
	//监控临近线路
	public List<Map<String, Object>> findByVideoId(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByVideoId(id);
	}
	//铁路区和街乡镇的数量
	public Map<String, Object> getRailOrgCount(String orgId, String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.getRailOrgCount(orgId,id);
	}
	//铁路辖内里程
	public String getRailOrgKM(String orgId, String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.getRailOrgKM(orgId,id);
	}
	//进站信号机
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findById(id);
	}
	 /**
    * 查询 km的上下 记录
    * 
    * @param kilometerMark
    * @param baseInfoRailId
    * @return
    */
   public Map<String, Object> findLngLatKm(String baseInfoRailId,
	    String kilometerMark) {
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
	Map<String, Object> rail = new HashMap<String, Object>();
		List<Point> points = new ArrayList<Point>();
		List<Map<String, Object>> rowsList = exMapPopWinDao.findLngLatKm(
			baseInfoRailId, kilometerMark,orgId);
		if (rowsList != null && rowsList.size() > 0) {
		    for (int i = 0; i < rowsList.size(); i++) {
			Map<String, Object> row = rowsList.get(i);
			points.add(new Point(Double.parseDouble(row.get("lng")
				.toString()), Double.parseDouble(row.get("lat")
				.toString()), Double.parseDouble(row.get(
				"kilometerMark").toString())));
		    }
		    if(points.size()>1){
		    	System.out.println(points);
//		    	if(StringUtils.equals(rowsList.get(0).get("railId").toString(), rowsList.get(1).get("railId").toString())){
//					rail.put("lng", rowsList.get(0).get("lng").toString());
//					rail.put("lat", rowsList.get(0).get("lat").toString());
//					rail.put("km", CommonUtil.KM2MStrbyDec(rowsList.get(0).get("kilometerMark").toString()));
//					rail.put("type", "location");
//					 return rail;
//				}
				if(points.get(0).getKm()<points.get(1).getKm()){
				    Point point = ProportionLngLat
					    .EqualProportionCalculation(points.get(0), points.get(1),
						    Double.valueOf(kilometerMark)); 
				    if (point != null) {
					rail.put("lng", point.getLng());
					rail.put("lat", point.getLat());
					rail.put("km", CommonUtil.KM2MStrbyDec(kilometerMark));
					rail.put("type", "location");
				    } 
				    return rail;
				}else if(points.get(0).getKm()>points.get(1).getKm()){
				    Point point = ProportionLngLat
					    .EqualProportionCalculation(points.get(1), points.get(0),
						    Double.valueOf(kilometerMark)); 
				    if (point != null) {
					rail.put("lng", point.getLng());
					rail.put("lat", point.getLat());
					rail.put("km", CommonUtil.KM2MStrbyDec(kilometerMark));
					rail.put("type", "location");
				    } 
				    return rail;
				}else if(points.get(0).getKm()==points.get(1).getKm()){
					rail.put("lng", points.get(0).getLng());
					rail.put("lat", points.get(0).getLat());
					rail.put("km", CommonUtil.KM2MStrbyDec(kilometerMark));
					rail.put("type", "location");
					 return rail;
				}
		    }
		}
		Map<String, Object> row=baseInfoRailDao.findByMaxminKm(baseInfoRailId,orgId);
		row.put("type", "locationNo");
	return row;
   }
	public List<Map<String, Object>> getStreamRials() {
		// TODO Auto-generated method stub
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return exMapPopWinDao.getStreamRials(orgId);
	}
	public List<Map<String, String>> getNeighbourhoodListPopWin(String string,
			String string2, String r) {
		// TODO Auto-generated method stub
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return exMapPopWinDao.getNeighbourhoodListPopWin( string,
				 string2,r,orgId);
	}
	/**
	 * 
	 */
	public List<Map<String, Object>> getBridges() {
		return exMapPopWinDao.getBridges();
	}
	/**
	 * @param string
	 * @param string3 
	 * @param string2 
	 * @return 
	 */
	public Integer update(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.update(string,string2,string3);
	}
	
	//北京测试实地考察video
	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object> findByIdVideoCS(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByIdVideoCS(id);
	}
	/**
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>>  findByVideonearByRails(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByVideonearByRails(id);
	}
	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object> findByIdCSPoliceHouse(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByIdCSPoliceHouse(id);
	}
	/**
	 * @param id
	 * @return
	 */
	public Object findByCSPoliceHousenearByRails(String id) {
		// TODO Auto-generated method stub
		return exMapPopWinDao.findByCSPoliceHousenearByRails(id);
	}
	/**
	 * @param lng
	 * @param lat
	 * @param streamId 
	 * @return 
	 */
	public String getKm(String lng, String lat, String streamId) {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = exMapPopWinDao.findner(lng,lat,streamId);
//		System.out.println(mapList);
		//距离最近的点
		int orderNum1=ConverterUtils.toInt(mapList.get(0).get("order_number"));
		Map<String, String> map1=new HashMap<String, String>();//最近前一个
		Map<String, String> map2 = mapList.get(0);//最近
//		System.err.println("map2"+map2);
		//
		if(mapList!=null&&mapList.size()>0){
			for(int i1=0;i1<mapList.size();i1++){
				Map<String, String> row1=mapList.get(i1);
				String orderNum2= ConverterUtils.toString(orderNum1-1);
				if(orderNum2.equals(row1.get("order_number"))){
					map1.putAll(row1);
				}
			}
		}
		double disLen=0.0;
		//map1空
		if(map1.size()==0){
			disLen=Point.getDistance(ConverterUtils.toDouble(lat), ConverterUtils.toDouble(lng),ConverterUtils.toDouble(map2.get("lat").toString()) ,ConverterUtils.toDouble(map2.get("lng").toString()) );
			disLen+=ConverterUtils.toDouble(map2.get("kilometer_mark"));
//			System.out.println("map1空"+disLen+"map1空"+disLen);
			return ConverterUtils.toString(disLen);
		}
		
		//1
		double xx1=ConverterUtils.toDouble(map1.get("lng").toString());
		double yy1=	ConverterUtils.toDouble(map1.get("lat").toString());
		//2
		double xx2=ConverterUtils.toDouble(map2.get("lng").toString());
		double yy2=ConverterUtils.toDouble(map2.get("lat").toString());
		//3
		double xx3=ConverterUtils.toDouble(lng);
		double yy3=ConverterUtils.toDouble(lat);
		if(((xx1-xx2)*(xx3-xx2)+(yy1-yy2)*(yy3-yy2))>0) {
//			System.out.println("rui"+disLen);
			disLen=Point.getDistance(ConverterUtils.toDouble(lat), ConverterUtils.toDouble(lng),ConverterUtils.toDouble(map2.get("lat").toString()) ,ConverterUtils.toDouble(map2.get("lng").toString()) );
			disLen-=ConverterUtils.toDouble(map2.get("kilometer_mark"));
//			System.out.println("rui"+disLen+"disLen"+disLen);
			return ConverterUtils.toString(disLen);
		}else{
//			System.out.println("dun");
			disLen=Point.getDistance(ConverterUtils.toDouble(lat), ConverterUtils.toDouble(lng),ConverterUtils.toDouble(map2.get("lat").toString()) ,ConverterUtils.toDouble(map2.get("lng").toString()) );
			disLen+=ConverterUtils.toDouble(map2.get("kilometer_mark"));
//			System.out.println("dun"+disLen+"disLen"+disLen);
			return ConverterUtils.toString(disLen);
		}
	}
	
}
