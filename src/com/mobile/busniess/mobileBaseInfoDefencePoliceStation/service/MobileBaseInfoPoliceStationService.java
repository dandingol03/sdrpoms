package com.mobile.busniess.mobileBaseInfoDefencePoliceStation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoDefencePoliceStation.dao.MobileBaseInfoPoliceStationDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefencePoliceStation.po.BaseInfoPoliceStationPo;
@Service("mobileBaseInfoPoliceStationService")
public class MobileBaseInfoPoliceStationService {

	@Autowired
	private MobileBaseInfoPoliceStationDao baseInfoPoliceStationDao;
	
	/**
	 * 查询警务站信息
	 * @param dgm
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	public Map<String, Object> baseInfoPoliceStationQueryList(DataGridModel dgm, BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		// TODO 查询警务站信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return baseInfoPoliceStationDao.baseInfoPoliceStationQueryList(dgm,baseInfoPoliceStationPo,orgId);
	}
}
