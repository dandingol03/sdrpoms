package com.mobile.busniess.mobileBaseInfoPeripheralPlace.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPeripheralPlace.dao.MobileBaseInfoPeripheralPlaceDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPeripheralPlace.po.BaseInfoPeripheralPlacePo;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.service 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:45:02
 */
@Service("mobileBaseInfoPeripheralPlaceService")
public class MobileBaseInfoPeripheralPlaceService {
	@Autowired
	private MobileBaseInfoPeripheralPlaceDao baseInfoPeripheralPlaceDao;
	
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
}
