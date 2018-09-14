package com.mobile.busniess.mobilePatrolTrackInfo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.busniess.mobilePatrolTrackInfo.po.MobilePatrolTrackInfoPo;
import com.pc.bsp.common.util.DBUtil;


@Repository("mobilePatrolTrackInfoDao")
public class MobilePatrolTrackInfoDao{
	
	@Autowired
	private DBUtil util;

	/**
	 * 巡防信息添加
	 * @param mobilePatrolTrackInfoPo 
	 * @return
	 */
	public int addPatrolTrackInfo(MobilePatrolTrackInfoPo mobilePatrolTrackInfoPo) {
		// TODO Auto-generated method stub
		String sql = "insert into patrol_track_info " +
				"(id, " +
				"user_id," +
				"start_time," +
				"end_time," +
				"track," +
				"remark" +
				")values( " +
				":id," +
				":userId," +
				":startTime," +
				":endTime," +
				":track," +
				":remark)";
		return util.editObject(sql, mobilePatrolTrackInfoPo);
	}
	
}
