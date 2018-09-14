package com.mobile.busniess.mobileBaseInfoPartCulvert.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartCulvert.dao.BaseInfoPartCulvertDao;
import com.pc.busniess.baseInfoPartCulvert.po.BaseInfoPartCulvertPo;
@Service("mobileBaseInfoPartCulvertService")
public class MobileBaseInfoPartCulvertService {

	@Autowired
	private BaseInfoPartCulvertDao baseInfoPartCulvertDao;

	/**
	 * 查询涵洞基本信息
	 * @param dgm DataGird表单查询结构体 @see DataGridModel
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @return 结果map 分为total 总数 rows 类型为ArrayList<Map<String,Object>的队列， 其中map结构为PO的索引
	 */
	public Map<String, Object> baseInfoPartCulvertQueryList(DataGridModel dgm, BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 查询涵洞基本信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartCulvertDao.baseInfoPartCulvertQueryList(dgm,baseInfoPartCulvertPo,orgId);
	}

}
