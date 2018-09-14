package com.mobile.busniess.mobileBaseInfoPartJunction.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoPartJunction.dao.MobileBaseInfoPartJunctionDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;

@Service("mobileBaseInfoPartJunctionService")
public class MobileBaseInfoPartJunctionService {
	@Autowired
	private MobileBaseInfoPartJunctionDao baseInfoPartJunctionDao;
	
	/**
	 * 查询道口信息
	 * @param dgm
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	public Map<String, Object> baseInfoPartJunctionQueryList(DataGridModel dgm, BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 查询道口信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartJunctionDao.baseInfoPartJunctionQueryList(dgm,baseInfoPartJunctionPo,orgId);
	}

}
