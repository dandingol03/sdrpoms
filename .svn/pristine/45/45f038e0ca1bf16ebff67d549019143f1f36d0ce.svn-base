/**
 * 
 */
package com.mobile.bsp.user.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.bsp.user.po.MobileUserWorkAttendancePo;
import com.pc.bsp.common.util.DBUtil;

/**
 * @author simple
 *
 */
@Repository("mobileUserDao")
public class MobileUserDao {

	@Autowired
	private DBUtil util;

	/**
	 * @param mobileUserWorkAttendancePo 
	 * @return
	 */
	public Integer addUserWorkAttendance(MobileUserWorkAttendancePo mobileUserWorkAttendancePo) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO user_work_attendance (user_id,lng,lat,time,type,the_geom) VALUES (:userId,:lng,:lat,:time,:type,st_geomfromtext(:theGeom))";
		return util.editObject(sql,mobileUserWorkAttendancePo);
	}

}
