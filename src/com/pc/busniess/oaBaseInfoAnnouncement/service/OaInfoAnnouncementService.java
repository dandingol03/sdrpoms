/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
package com.pc.busniess.oaBaseInfoAnnouncement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.oaBaseInfoAnnouncement.dao.OaInfoAnnouncementDao;
import com.pc.busniess.oaBaseInfoAnnouncement.po.OaInfoAnnouncementPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
@Service("oaInfoAnnouncementService")
public class OaInfoAnnouncementService {

	@Autowired
	private OaInfoAnnouncementDao oaInfoAnnouncementDao;
	
	public Map<String, Object> oaInfoAnnouncementQueryList(DataGridModel dgm,OaInfoAnnouncementPo oaInfoAnnouncementPo) {
		// TODO 查询信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
	return oaInfoAnnouncementDao.oaInfoAnnouncementQueryList(dgm,oaInfoAnnouncementPo,orgId);
	}
	public int addOaInfoAnnouncement(OaInfoAnnouncementPo oaInfoAnnouncementPo) {
			// TODO 添加信息
			String id = NextID.getNextID("news");
			oaInfoAnnouncementPo.setId(id);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PubUsers user = (PubUsers)principal;
			String userId = user.getUserId();
			oaInfoAnnouncementPo.setEditor(userId); //用户id
		return oaInfoAnnouncementDao.addOaInfoAnnouncement(oaInfoAnnouncementPo);
	}
	public int updateOaInfoAnnouncement(OaInfoAnnouncementPo oaInfoAnnouncementPo) {
			// TODO 修改信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userId = user.getUserId();
		oaInfoAnnouncementPo.setEditor(userId); //用户id
		return oaInfoAnnouncementDao.updateOaInfoAnnouncement(oaInfoAnnouncementPo);
	}
	public int[] deleteOaInfoAnnouncement(List<String> idList) {
			// TODO 删除信息
		return oaInfoAnnouncementDao.deleteOaInfoAnnouncement(idList);
	}
}
