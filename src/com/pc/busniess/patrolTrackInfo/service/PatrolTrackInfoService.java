package com.pc.busniess.patrolTrackInfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.patrolManagementTask.po.PatrolManagementTaskPo;
import com.pc.busniess.patrolTrackInfo.dao.PatrolTrackInfoDao;


/**
 * 这个Service主要是对巡防轨迹的业务处理
 * @author jiawenlong
 */
@Service("PatrolTrackInfoService")
public class PatrolTrackInfoService {

	@Autowired
	private PatrolTrackInfoDao patrolTrackInfoDao;
	/**
	 * 这个方法是查询巡防轨迹信息
	 * @param dgm
	 * @param patrolManagementTaskPo
	 * @return Dao层查询巡防轨迹信息
	 */
	public Map<String, Object> PatrolTrackInfoQueryList(DataGridModel dgm,
			PatrolManagementTaskPo patrolManagementTaskPo) {
		// TODO 查询巡防轨迹信息
		dgm.setSort("end_time");
		dgm.setOrder("desc");
		
		return patrolTrackInfoDao.PatrolTrackInfoQueryList(dgm,
				patrolManagementTaskPo);
	}
	/**
	 * 这个方法是删除巡防轨迹信息
	 * @param idList
	 * @return Dao层删除巡防轨迹信息
	 */
	public int[] deletePatrolTrackInfo(List<String> idList) {
		// TODO 删除巡防轨迹信息
		return patrolTrackInfoDao.deletePatrolTrackInfo(idList);
	}
	/**
	 * 这个方法是查询所有人信息
	 * @return list
	 */
	public List<Map<String, Object>> queryPers() {
		// TODO 所有人信息
		List<Map<String, Object>> list = patrolTrackInfoDao.queryPers();
		return list;
	}
}
