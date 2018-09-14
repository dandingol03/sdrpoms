package com.mobile.busniess.mobileBaseInfoDefencePropaganda.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileBaseInfoDefencePropaganda.dao.MobileBaseInfoDefencePropagandaDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;

@Service("mobileBaseInfoDefencePropagandaService")
public class MobileBaseInfoDefencePropagandaService {

	@Autowired
	private MobileBaseInfoDefencePropagandaDao baseInfoDefencePropagandaDao;
	
	/**
	 * TODO
	 * 查询护路宣传点信息
	 */
	public Map<String, Object> baseInfoDefencePropagandaQueryList(DataGridModel dgm, BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		 //获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefencePropagandaDao.baseInfoDefencePropagandaQueryList(dgm,baseInfoDefencePropagandaPo,orgId);
	}

}
