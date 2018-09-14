package com.mobile.busniess.mobileBaseInfoDefenceGuardStation.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoDefenceGuardStation.dao.MobileBaseInfoDefenceGuardStationDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefenceGuardStation.po.BaseInfoDefenceGuardStationPo;


@Service("mobileBaseInfoDefenceGuardStationService")
public class MobileBaseInfoDefenceGuardStationService {
	@Autowired
	private MobileBaseInfoDefenceGuardStationDao baseInfoDefenceGuardStationDao;
	
	/**
	 * TODO 
	 * 查询护路工作站信息
	 */
	public Map<String, Object> baseInfoDefenceGuardStationQueryList(DataGridModel dgm, BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		//获取当前登录用户
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefenceGuardStationDao.baseInfoDefenceGuardStationQueryList(dgm,baseInfoDefenceGuardStationPo,orgId);
	}

}
