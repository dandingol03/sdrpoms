package com.pc.busniess.baseInfoDefenceGuardStation.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefenceGuardStation.dao.BaseInfoDefenceGuardStationDao;
import com.pc.busniess.baseInfoDefenceGuardStation.po.BaseInfoDefenceGuardStationPo;


@Service("baseInfoDefenceGuardStationService")
public class BaseInfoDefenceGuardStationService {
	@Autowired
	private BaseInfoDefenceGuardStationDao baseInfoDefenceGuardStationDao;
	/**
	 * 根据id获取护路工作站的详细信息
	 * @param id 护路工作站ID
	 * @return 返回对应的护路工作站的详细信息
	 * @author lyf
	 * 
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoDefenceGuardStationDao.findById(id);
	}
	public Map<String, Object> baseInfoDefenceGuardStationQueryList(DataGridModel dgm, BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo) {
		/**
		 * TODO 
		 * 查询护路工作站信息
		 */
		//获取当前登录用户
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
				
		return baseInfoDefenceGuardStationDao.baseInfoDefenceGuardStationQueryList(dgm,baseInfoDefenceGuardStationPo,orgId);
	}

	public int addBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo, List<Object[]> objList) {
		/**
		 * TODO 
		 * 添加护路工作站信息
		 */
		int result = baseInfoDefenceGuardStationDao.addBaseInfoDefenceGuardStation(baseInfoDefenceGuardStationPo);

		if(objList.size() > 0){
			int[] saveCount = baseInfoDefenceGuardStationDao.saveBaseInfoDefenceGuardStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int updateBaseInfoDefenceGuardStation(BaseInfoDefenceGuardStationPo baseInfoDefenceGuardStationPo, List<Object[]> objList) {
		/**
		 * TODO 
		 * 修改护路工作站信息
		 */
		int result = baseInfoDefenceGuardStationDao.updateBaseInfoDefenceGuardStation(baseInfoDefenceGuardStationPo);
		String id=baseInfoDefenceGuardStationPo.getId();
		baseInfoDefenceGuardStationDao.deleteBaseInfoDefenceGuardStationAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoDefenceGuardStationDao.saveBaseInfoDefenceGuardStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int[] deleteBaseInfoDefenceGuardStation(List<String> idList) {
		/**
		 * TODO
		 * 删除护路工作站信息
		 */
		return baseInfoDefenceGuardStationDao.deleteBaseInfoDefenceGuardStation(idList);
	}
}
