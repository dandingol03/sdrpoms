package com.pc.busniess.baseInfoPeripheralPlace.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPeripheralPlace.dao.BaseInfoPeripheralPlaceDao;
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.service 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:45:02
 */
@Service("baseInfoPeripheralPlaceService")
public class BaseInfoPeripheralPlaceService {
	@Autowired
	private BaseInfoPeripheralPlaceDao baseInfoPeripheralPlaceDao;
	
	
	/**
	 * 根据id获取<strong>周边场所</strong>的详细信息
	 * @param id 周边场所ID
	 * @return 返回对应周边场所的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPeripheralPlaceDao.findById(id);
	}
	
	
	/**
	 * 	查询周边场所信息
	 * @param dgm
	 * @param baseInfoPeripheralPlacePo
	 * @return
	 */
	public Map<String,Object>	baseInfoPeripheralPlaceQueryList(DataGridModel dgm,BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo){
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPeripheralPlaceDao.baseInfoPeripheralPlaceQueryList(dgm, baseInfoPeripheralPlacePo,orgId);
	}
	/**
	 * 新增周边场所信息
	 * @param baseInfoPeripheralPlacePo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoPeripheralPlace(BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo, List<Object[]> objList){
		int result = baseInfoPeripheralPlaceDao.addBaseInfoPeripheralPlace(baseInfoPeripheralPlacePo);
		String id=baseInfoPeripheralPlacePo.getId();
		baseInfoPeripheralPlaceDao.deleteBaseInfoPeripheralPlaceAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPeripheralPlaceDao.saveBaseInfoPeripheralPlaceAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 更新周边场所信息
	 * @param baseInfoPeripheralPlacePo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoPeripheralPlace(BaseInfoPeripheralPlacePo baseInfoPeripheralPlacePo, List<Object[]> objList){
		int result = baseInfoPeripheralPlaceDao.updateBaseInfoPeripheralPlace(baseInfoPeripheralPlacePo);
		String id=baseInfoPeripheralPlacePo.getId();
		baseInfoPeripheralPlaceDao.deleteBaseInfoPeripheralPlaceAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPeripheralPlaceDao.saveBaseInfoPeripheralPlaceAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 删除周边场所信息
	 * @param idList
	 * @return
	 */
	public int[] delBaseInfoPeripheralPlace(List<String> idList){
		return baseInfoPeripheralPlaceDao.delBaseInfoPeripheralPlace(idList);
	}
}
