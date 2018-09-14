/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
package com.mobile.busniess.mobileOaBaseInfoAnnouncement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaBaseInfoAnnouncement.dao.MobileOaInfoAnnouncementDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.oaBaseInfoAnnouncement.po.OaInfoAnnouncementPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
@Service("mobileOaInfoAnnouncementService")
public class MobileOaInfoAnnouncementService {

	@Autowired
	private MobileOaInfoAnnouncementDao oaInfoAnnouncementDao;
	
	public Map<String, Object> oaInfoAnnouncementQueryList(DataGridModel dgm,OaInfoAnnouncementPo oaInfoAnnouncementPo) {
			// TODO 查询信息
			//获取当前登录用户
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PubUsers user = (PubUsers)principal;
			String orgId = user.getUserOrg();
		return oaInfoAnnouncementDao.oaInfoAnnouncementQueryList(dgm,oaInfoAnnouncementPo,orgId);
	}
}
