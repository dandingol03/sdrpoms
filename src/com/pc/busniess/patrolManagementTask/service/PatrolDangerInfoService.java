package com.pc.busniess.patrolManagementTask.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.patrolManagementTask.dao.PatrolDangerInfoDao;
import com.pc.busniess.patrolManagementTask.po.PatrolDangerInfoPo;

@Service("patrolDangerInfoService")
public class PatrolDangerInfoService {
	
	@Autowired
	private PatrolDangerInfoDao patrolDangerInfoDao;
	
	/**
	 * 根据id 查询隐患信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id){
		return patrolDangerInfoDao.findById(id);
	}
	/**
	 * 
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public Integer dangerCount(PatrolDangerInfoPo patrolDangerInfoPo) {
	    // TODO Auto-generated method stub
	    return patrolDangerInfoDao.dangerCount(patrolDangerInfoPo);
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return  patrolDangerInfoDao.dangerInfoQueryList(dgm,patrolDangerInfoPo,orgId);
	}
	/**
	 * 隐患上报添加
	 * @param patrolDangerInfoPo
	 * @param objList 
	 * @return
	 */
	public int addDangerInfo(PatrolDangerInfoPo patrolDangerInfoPo, List<Object[]> objList) {
		// TODO 隐患上报添加
		int result = patrolDangerInfoDao.addDangerInfo(patrolDangerInfoPo);
		
		if(objList.size() > 0){
			int[] saveCount = patrolDangerInfoDao.saveDangerInfoAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 隐患上报修改
	 * @param patrolDangerInfoPo
	 * @param objList 
	 * @return
	 */
	public int updateDangerInfo(PatrolDangerInfoPo patrolDangerInfoPo, List<Object[]> objList) {
		// TODO 隐患上报修改
		int result = patrolDangerInfoDao.updateDangerInfo(patrolDangerInfoPo);
		String id=patrolDangerInfoPo.getId();
		patrolDangerInfoDao.deleteDangerInfoAuths(id);
		if(objList.size() > 0){
			int[] saveCount = patrolDangerInfoDao.saveDangerInfoAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 隐患删除
	 * @param idList
	 * @return
	 */
	public int[] deleteDangerInfo(List<String> idList) {
		// TODO 隐患删除
		return patrolDangerInfoDao.deleteDangerInfo(idList);
	}
	/**
	 *  隐患处置修改
	 * @param patrolDangerInfoPo
	 * @return
	 */
	public int updateDangerInfoDispose(PatrolDangerInfoPo patrolDangerInfoPo) {
		// TODO 隐患处置修改
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userId = user.getUserId();
		patrolDangerInfoPo.setTreatUserId(userId);
		//修改隐患关联表的处理结果状态
		int i=patrolDangerInfoDao.updateDangerInfoDisposeAuths(patrolDangerInfoPo);
		if(i<=0){
			return 0;	
		}
		return patrolDangerInfoDao.updateDangerInfoDispose(patrolDangerInfoPo);
	}
}
