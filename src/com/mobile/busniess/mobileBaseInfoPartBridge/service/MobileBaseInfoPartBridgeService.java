package com.mobile.busniess.mobileBaseInfoPartBridge.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPartBridge.dao.MobileBaseInfoPartBridgeDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;

@Service("mobileBaseInfoPartBridgeService")
public class MobileBaseInfoPartBridgeService {
	
	@Autowired
	private MobileBaseInfoPartBridgeDao baseInfoPartBridgeDao;
	
	/**
	 * 该方法是对桥梁信息的查询
	 * @param dgm
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo DataGridModel
	 * @return 桥梁dao层桥梁信息查询
	 */
	public Map<String, Object> baseInfoPartBridgeQueryList(DataGridModel dgm, BaseInfoPartBridgePo baseInfoPartBridgePo) {
		// TODO 查询桥梁信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartBridgeDao.baseInfoPartBridgeQueryList(dgm,baseInfoPartBridgePo,orgId);
	}
}
