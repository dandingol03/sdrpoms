package com.mobile.busniess.mobileBaseInfoDefenceBroadcast.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoDefenceBroadcast.dao.MobileBaseInfoDefenceBroadcastDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;


@Service("mobileBaseInfoDefenceBroadcastService")
public class MobileBaseInfoDefenceBroadcastService {
	@Autowired
	private MobileBaseInfoDefenceBroadcastDao baseInfoDefenceBroadcastDao;
	
	/**TODO 
	 * 查询广播警示柱信息
	 */
	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm, BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefenceBroadcastDao.baseInfoDefenceBroadcastQueryList(dgm,baseInfoDefenceBroadcastPo,orgId);
	}

}
