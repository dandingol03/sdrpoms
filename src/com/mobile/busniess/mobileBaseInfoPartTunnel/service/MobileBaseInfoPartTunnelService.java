package com.mobile.busniess.mobileBaseInfoPartTunnel.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPartTunnel.dao.MobileBaseInfoPartTunnelDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;

@Service("mobileBaseInfoPartTunnelService")
public class MobileBaseInfoPartTunnelService {
	@Autowired
	private MobileBaseInfoPartTunnelDao baseInfoPartTunnelDao;
	
	/**
	 * 查询隧道信息
	 * @param dgm
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	public Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 查询隧道信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartTunnelDao.baseInfoPartTunnelQueryList(dgm,baseInfoPartTunnelPo,orgId);
	}
}
