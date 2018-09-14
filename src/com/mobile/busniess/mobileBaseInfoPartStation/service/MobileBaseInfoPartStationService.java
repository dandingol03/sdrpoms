package com.mobile.busniess.mobileBaseInfoPartStation.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPartStation.dao.MobileBaseInfoPartStationDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;

@Service("mobileBaseInfoPartStationService")
public class MobileBaseInfoPartStationService {

	@Autowired
	private MobileBaseInfoPartStationDao baseInfoPartStationDao;
	
	/**
	 * 该方法是对车站信息的查询
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return 车站dao层车站信息查询
	 */
	public Map<String, Object> baseInfoPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo) {
		// TODO 查询车站信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();

		return baseInfoPartStationDao.baseInfoPartStationQueryList(dgm,baseInfoPartStationPo,orgId);
	}

}