
package com.mobile.bsp.user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.bsp.user.dao.MobileUserDao;
import com.mobile.bsp.user.po.MobileUserWorkAttendancePo;

/**
 * @author simple
 *
 */
@Service("mobileUserService")
public class MobileUserService{
	
	@Autowired
	private MobileUserDao mobileOrgDao;

	/**
	 * @param mobileUserWorkAttendancePo 
	 * @return
	 */
	public Integer addUserWorkAttendance(MobileUserWorkAttendancePo mobileUserWorkAttendancePo) {
		// TODO Auto-generated method stub
		return mobileOrgDao.addUserWorkAttendance(mobileUserWorkAttendancePo);
	}
	

}
