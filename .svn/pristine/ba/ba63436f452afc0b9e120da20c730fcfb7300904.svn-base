package com.mobile.busniess.mobileBaseInfoDefencePoliceHouse.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoDefencePoliceHouse.dao.MobileBaseInfoDefencePoliceHouseDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefencePoliceHouse.po.BaseInfoDefencePoliceHousePo;
@Service("mobileBaseInfoDefencePoliceHouseService")
public class MobileBaseInfoDefencePoliceHouseService {

	@Autowired
	private MobileBaseInfoDefencePoliceHouseDao baseInfoDefencePoliceHouseDao;
	/**
	 * 查询派出所信息
	 * @param dgm
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	public Map<String, Object> baseInfoDefencePoliceHouseQueryList(DataGridModel dgm, BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		// TODO 查询派出所信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefencePoliceHouseDao.baseInfoDefencePoliceHouseQueryList(dgm,baseInfoDefencePoliceHousePo,orgId);
	}
}
