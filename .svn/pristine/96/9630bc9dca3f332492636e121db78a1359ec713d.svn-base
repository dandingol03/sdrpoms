/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.service 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:40:47 
 */
package com.mobile.busniess.mobileInformationVerification.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileInformationVerification.dao.InformationVerificationDao;
import com.mobile.busniess.mobileInformationVerification.po.InformationVerification;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;

/**   
 * @Package: com.mobile.busniess.mobileInformationVerification.service 
 * @author: jwl   
 * @date: 2018年8月28日 下午2:40:47 
 */
@Service("informationVerificationService")
public class InformationVerificationService {
	
	@Autowired
	private InformationVerificationDao informationVerificationDao;

	//根据GPS获取监控,按距离排序 video_information
	public Map<String, Object> getInformationVerificationInfo(
			DataGridModel dgm, InformationVerification informationVerification) {
		// TODO Auto-generated method stub
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return informationVerificationDao.getInformationVerificationInfo(dgm,informationVerification,orgId);
	}

	//修改监控经纬度以及相关信息
	public int updateInformationVerificationInfo(
			InformationVerification informationVerification) {
		// TODO Auto-generated method stub
		return informationVerificationDao.updateInformationVerificationInfo(informationVerification);
	}

	//添加审查结果 video_review_results
	public int addInformationVerification(
			InformationVerification informationVerification) {
		// TODO Auto-generated method stub
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		PubUsers user = (PubUsers)principal;
//		String userId = user.getUserId();
//		informationVerification.setUserId(userId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day=new Date();
        String date=df.format(day);
        informationVerification.setCreateDate(date);
		return informationVerificationDao.addInformationVerification(informationVerification);
	}
	
}
