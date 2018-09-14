package com.mobile.busniess.mobileBaseInfoPartTrajection.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPartTrajection.dao.MobileBaseInfoPartTrajectionDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;

@Service("mobileBaseInfoPartTrajectionService")
public class MobileBaseInfoPartTrajectionService {

	@Autowired
	private MobileBaseInfoPartTrajectionDao baseInfoHiddenTrajectionDao;

	/**
	 * 查询行人易穿行部位信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		// TODO 查询行人易穿行部位信息
		//获取当前登录用户
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				PubUsers user = (PubUsers)principal;
				String orgId = user.getUserOrg();
				
		return baseInfoHiddenTrajectionDao.baseInfoHiddenTrajectionQueryList(dgm,baseInfoHiddenTrajectionPo,orgId);
	}
	
}
