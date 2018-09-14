package com.mobile.busniess.mobileBaseInfoKeyperson.service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoKeyperson.dao.MobileBaseInfoKeypersonDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoKeyperson.po.BaseInfoKeypersonPo;

@Service("mobileBaseInfoKeypersonService")
public class MobileBaseInfoKeypersonService {
	@Autowired
	private MobileBaseInfoKeypersonDao baseInfoKeypersonDao;
	
	/**
	 *  TODO
	 *  查询重点人信息
	 */
	public Map<String, Object> baseInfoKeypersonQueryList(DataGridModel dgm, BaseInfoKeypersonPo baseInfoKeypersonPo) {
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoKeypersonDao.baseInfoKeypersonQueryList(dgm,baseInfoKeypersonPo,orgId);
	}


}
