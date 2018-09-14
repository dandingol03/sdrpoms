package com.mobile.busniess.mobilePatrolTrackInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobilePatrolTrackInfo.dao.MobilePatrolTrackInfoDao;
import com.mobile.busniess.mobilePatrolTrackInfo.po.MobilePatrolTrackInfoPo;
import com.pc.bsp.common.util.NextID;


@Service("mobilePatrolTrackInfoService")
public class MobilePatrolTrackInfoService {

	@Autowired
	private MobilePatrolTrackInfoDao patrolTrackInfoDao;

	/**
	 * 巡线轨迹
	 * @param mobilePatrolTrackInfoPo
	 * @return
	 */
	public int addPatrolTrackInfo(
			MobilePatrolTrackInfoPo mobilePatrolTrackInfoPo) {
		// TODO Auto-generated method stub
		String id = NextID.getNextID("trac");
		mobilePatrolTrackInfoPo.setId(id);
		return patrolTrackInfoDao.addPatrolTrackInfo(mobilePatrolTrackInfoPo);
	}
}
