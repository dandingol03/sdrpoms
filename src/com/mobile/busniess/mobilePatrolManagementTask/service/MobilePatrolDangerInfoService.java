package com.mobile.busniess.mobilePatrolManagementTask.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobilePatrolManagementTask.dao.MobilePatrolDangerInfoDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;

@Service("mobilePatrolDangerInfoService")
public class MobilePatrolDangerInfoService {
	
	@Autowired
	private MobilePatrolDangerInfoDao patrolDangerInfoDao;
	
	/**
	 * 根据id 查询隐患信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id){
		return patrolDangerInfoDao.findById(id);
	}
	/**
	 * 隐患上报查询
	 * @param dgm
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public Map<String, Object> dangerInfoQueryList(DataGridModel dgm,
			PatrolDangerInfoPo patrolDangerInfoPo) {
		// TODO 隐患上报查询

		//获取当前登录用户
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		PubUsers user = (PubUsers)principal; user.getUserOrg()
		String orgId ="110";
		return  patrolDangerInfoDao.dangerInfoQueryList(dgm,patrolDangerInfoPo,orgId);
	}
	/**
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public Map<String, Object> findByDangerId(String id) {
		// TODO Auto-generated method stub
		return patrolDangerInfoDao.findByDangerId(id);
	}
}
