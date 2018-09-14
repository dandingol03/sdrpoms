package com.pc.busniess.patrolManagementTask.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.patrolManagementTask.dao.PatrolManagementTaskDao;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;
import com.pc.busniess.patrolManagementTask.po.PatrolTeamInfoPo;
/**
 * 巡防队伍信息
 * @author jiawenlong
 *Service
 */
@Service("PatrolManagementTaskService")
public class PatrolManagementTaskService {
	@Autowired
	private PatrolManagementTaskDao patrolManagementTaskDao;
	/**
	 * 队伍信息查询
	 * @param dgm
	 * @param patrolTeamInfoPo
	 * @return
	 */
	public Map<String, Object> patrolManagementTaskQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO 队伍信息查询
		 //获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return patrolManagementTaskDao.patrolManagementTaskQueryList(dgm, patrolTeamInfoPo,orgId);
	}
	/**
	 * 添加队伍信息
	 * @param patrolTeamInfoPo
	 * @return
	 */
	public int addPatrolManagementTask(
			PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO 添加队伍信息
		String id = NextID.getNextID("Team");
		patrolTeamInfoPo.setId(id);
		return patrolManagementTaskDao
				.addPatrolManagementTask(patrolTeamInfoPo);
	}
	/**
	 * 修改队伍信息
	 * @param patrolTeamInfoPo
	 * @return
	 */
	public int updatePatrolManagementTask(
			PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO 修改队伍信息
		return patrolManagementTaskDao
				.updatePatrolManagementTask(patrolTeamInfoPo);
	}
	/**
	 * 删除队伍信息
	 * @param idList
	 * @return
	 */
	public int[] deletePatrolManagementTask(List<String> idList) {
		// TODO 删除队伍信息
		return patrolManagementTaskDao.deletePatrolManagementTask(idList);
	}
	
	/**
	 * 队伍信息查询全部地图
	 * @param dgm
	 * @return
	 */
	public List<Map<String, Object>> patrolManagementTaskQueryList() {
		// TODO Auto-generated method stub
		
		return patrolManagementTaskDao.patrolManagementTaskQueryList();
	}
	/**
	 * @param dgm
	 * @param partrolTeamUserRelationPo
	 * @param patrolTeamInfoPo
	 * @return
	 */
	public Map<String, Object> taskAndUserQueryList(DataGridModel dgm,
			PatrolTeamInfoPo patrolTeamInfoPo) {
		// TODO Auto-generated method stub
		 //获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return patrolManagementTaskDao.taskAndUserQueryList(dgm,patrolTeamInfoPo,orgId);
	}
	public int checkPatrolData(String id) {
		// TODO Auto-generated method stub
		return patrolManagementTaskDao.checkPatrolData(id);
	}
	public List<Map<String, Object>> queryTeamList() {
		// TODO 查询所有的涵洞基本信息（参考涵洞基本线时需要）
		List<Map<String, Object>> list = patrolManagementTaskDao.queryTeamList();
		return list;
	}
}
