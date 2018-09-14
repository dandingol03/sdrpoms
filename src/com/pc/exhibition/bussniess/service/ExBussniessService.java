package com.pc.exhibition.bussniess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.Point;
import com.pc.bsp.common.util.ProportionLngLat;
import com.pc.exhibition.bussniess.dao.ExBussniessDao;

/**
 * @author simple
 *
 */
@Service("exBussniessService")
public class ExBussniessService{
	@Autowired
	private ExBussniessDao exBussniessDao;
	
	/**
	 * 获得机构统计信息
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getUserOrgList(String orgId){
		return exBussniessDao.getUserOrgList(orgId);
	}
	
	/**
	 * 获得机构统计信息
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getUserOrgListInfo(String orgId){
		return exBussniessDao.getUserOrgListInfo(orgId);
	}
	
	/**
	 * 根据机构和铁路ID获得铁路ID、名称和分类 以及数量 (全部类型以及数据字典类型)
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getUserRailListInfo(String orgId){
		return exBussniessDao.getUserRailListInfo(orgId);
	}
	
	/**
	 * 根据当前用户机构和铁路获得隐患点类型统计
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getPlaceDangerListInfo(String orgId,List<String> railIdList){
		return exBussniessDao.getPlaceDangerListInfo(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关重点部位统计信息
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserKeyPartListInfo(String orgId,List<String> railIdList){
		return exBussniessDao.getUserKeyPartListInfo(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得相关联防点位统计信息
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserJDenfenseListInfo(String orgId,List<String> railIdList){
		return exBussniessDao.getUserJDenfenseListInfo(orgId,railIdList);
	}
	
	/**
	 * 根据当前用户机构和铁路获得周边单位统计信息
	 * @param orgId
	 * @param railId
	 * @return
	 */
	public Map<String,Object> getUserPeripheralPlaceListInfo(String orgId,List<String> railIdList){
		return exBussniessDao.getUserPeripheralPlaceListInfo(orgId,railIdList);
	}
}
