package com.pc.exhibition.map.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.exhibition.map.dao.ExMapDao;



/**
 * @author simple
 *
 */
@Service("exMapService")
public class ExMapService{
	@Autowired
	private ExMapDao exMapDao;

	/**
	 * 获取当前机构下的中心点和放大倍数
	 * @param orgId
	 * @return Map<String,Object> X Y Zoom XY 中心点坐标 Zoom 放大倍数
	 */
	public Map<String,Object> getOrgZoomAndCenter(String orgId){
		return exMapDao.getOrgZoomAndCenter(orgId);
	}
	/**
	 * 根据当前机构获得相关安保区
	 * @param request
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> getSecurityareaBJBorderInfoList(String orgId){
		return exMapDao.getSecurityareaBJBorderInfoList(orgId);
	}
	/**
	 * 根据当前用户获得相关边界
	 * @param request
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> getBJBorderInfoList(String orgId){
		return exMapDao.getBJBorderInfoList(orgId);
	}
	
	/**
	 * 获得当前用户下涉路机构详细信息
	 * @param orgId
	 * @param level 层级 0 第一级 1 第二级 2 第三级
	 * @return
	 */
	public List<Map<String,Object>> getUserOrgListInfo(String orgId,String level){
		return exMapDao.getUserOrgListInfo(orgId,level);
	}
	
	
	/**
	 * 根据当前用户机构和铁路获得相关铁路信息
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJRailWayInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJRailWayInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关桥涵信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJBridgeCulvertInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJBridgeCulvertInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关道口信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJJunctionInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJJunctionInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关隧道信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTunneltInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJTunneltInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关车站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJStationInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJStationInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关行人易穿行信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTrajectionInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJTrajectionInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点人信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJKeypersonInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJKeypersonInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关隐患点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJDangerInfoList(String orgId,List<String> railIdList,String dangerType){
		return exMapDao.getBJDangerInfoList(orgId,railIdList,dangerType);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关工作站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJGuardStationInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJGuardStationInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关宣传点信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPropagandaInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJPropagandaInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关警示柱信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJBroadcastInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJBroadcastInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关摄像头信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJVideoMonitorInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJVideoMonitorInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关派出所信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPoliceHouseInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJPoliceHouseInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关警务站信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJPoliceStationInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJPoliceStationInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关专职队员信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJTeamMemberInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJTeamMemberInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关在线干部信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public List<Map<String,Object>> getBJCardeInfoList(String orgId,List<String> railIdList){
		return exMapDao.getBJCardeInfoList(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得周边单位信息
	 * @param request
	 * @param orgId
	 * @param railId
	 * @param perPlaceId
	 * @return
	 */
	public List<Map<String,Object>> getUserPeripheralPlaceInfoList(String orgId,List<String> railIdList,List<String> perPlaceIdList,String isImportant){
		return exMapDao.getUserPeripheralPlaceInfoList(orgId,railIdList,perPlaceIdList,isImportant);
	}
	
	//北京测试实地考察
	public List<Map<String,Object>> getBJVideoInfoList(){
		return exMapDao.getBJVideoInfoList();
	}
	public List<Map<String, Object>> getBJCSPoliceHouseInfoList() {
		// TODO Auto-generated method stub
		return exMapDao.getBJCSPoliceHouseInfoList();
	}
}
